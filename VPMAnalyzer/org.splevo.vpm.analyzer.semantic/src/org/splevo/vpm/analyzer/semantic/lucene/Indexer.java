package org.splevo.vpm.analyzer.semantic.lucene;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.FieldInfo.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.splevo.vpm.analyzer.semantic.Constants;

/**
 * This class handles the indexing. It creates one single main index.
 * By providing two {@link Analyzer}s Comments can be handled different
 * than regular code. 
 * This class offers Methods to add text to an index, clear the main index
 * and get reading access to an index.
 * 
 * This class is a singleton. This is because there should be only one index that holds
 * all informations, at any time. This class has the ability to create that index. To ensure 
 * there is only one Index, the singleton pattern is used.
 *  
 * @author Daniel Kojic
 * 
 */
public final class Indexer {
	
	/** The logger for this class. */
    private Logger logger = Logger.getLogger(Indexer.class);
	
    /** The writer configuration. */
	private IndexWriterConfig config;
	
	/** The {@link Directory} for the index. */
	private Directory directory;
	
	/** Singleton instance. */
	private static Indexer instance;
	
    /** Specifies how to store the code fragments in the index. */
    private static final FieldType TYPE_STORED_CONTENT = new FieldType();

    /** Specifies how to store the comment fragments in the index. */
    private static final FieldType TYPE_STORED_ID = new FieldType();
    
    /** Define the Field-Type the text gets added to the index.
     * To allow term extraction, DOCS_AND_FREQS has to be stored
     * in the index. */
    static {
    	TYPE_STORED_CONTENT.setIndexed(true);
        TYPE_STORED_CONTENT.setTokenized(true);
        TYPE_STORED_CONTENT.setStored(true);
        TYPE_STORED_CONTENT.setStoreTermVectors(true);
        TYPE_STORED_CONTENT.setIndexOptions(IndexOptions.DOCS_AND_FREQS);
    }
    
    /** Define the Field-Type the IDs get added to the index. */
    static {
    	TYPE_STORED_ID.setIndexed(false);
    	TYPE_STORED_ID.setTokenized(false);
        TYPE_STORED_ID.setStored(true);
        TYPE_STORED_ID.setStoreTermVectors(false);
        TYPE_STORED_ID.setIndexOptions(IndexOptions.DOCS_ONLY);
    }
	
	/**
	 *  Private constructor to prevent this	class from being instantiated multiple times.
	 */
	private Indexer() {
		this.config = new IndexWriterConfig(Version.LUCENE_43, 
				CustomPerFieldAnalyzerWrapper.getWrapper(Constants.DEFAULT_STOP_WORDS));	
		
		// Use RAMDirectory to use an in-memory index. 
		directory = new RAMDirectory();
	}
	
	/**
	 * Sets the stop-words that should be used by the {@link LuceneCodeAnalyzer}.
	 * 
	 * @param stopWords The stop-words.
	 */
	public void setStopWords(String[] stopWords) {
		this.config = new IndexWriterConfig(Version.LUCENE_43, 
				CustomPerFieldAnalyzerWrapper.getWrapper(Constants.DEFAULT_STOP_WORDS));
	}
	
	/**
	 * Gets the singleton instance.
	 * @return The singleton instance.
	 */
	public static Indexer getInstance() {
		// Return singleton, create new if not existing.
		if (instance == null) {
			instance = new Indexer();
		}
		
		return instance;
	}
	
	/**
	 * @return A {@link DirectoryReader} to search the main index.
	 * @throws IOException Throws an {@link IOException} if there are problems opening the index.
	 */
	public DirectoryReader getIndexReader() throws IOException {
		return DirectoryReader.open(directory);
	}
	
	/**
	 * Adds content to the main index.
	 * 
	 * @param variationPointId The ID of the {@link VariationPoint} to be linked with its content.
	 * @param content The text content of the {@link VariationPoint}.
	 * @param comments The text comments of the {@link VariationPoint}.
	 * @return True if something was indexed; False otherwise.
	 * @throws IOException 
	 */
	public boolean addToIndex(String variationPointId, String content, String comments) throws IOException {
		if (variationPointId == null || variationPointId.length() == 0 || (content == null && comments == null)) {
			throw new IllegalArgumentException();
		}
		
		if ((content != null && content.length() > 0) || (comments != null && comments.length() > 0)) {
			try {
				addDocument(variationPointId, content, comments);
				return true;
			} catch (IOException e) {
				logger.error("Error while adding the document to the index.", e);
				return false;
			}
		}		
		
		return false;
	}

	/**
	 * Adds a {@link Document} to the index with the given VP ID and the text.
	 * 
	 * @param variationPointId The ID to be stored.
	 * @param content The text to be added to the index.
	 * @param comments The text comments of the {@link VariationPoint}.
	 * @throws IOException If the index cannot be opened or there are problems adding the document to the index.
	 */
	private void addDocument(String variationPointId, String content, String comments) throws IOException {
		if (variationPointId == null || (content == null && comments == null)) {
			throw new IllegalArgumentException();
		}
		
		// Create the document with two fields: the content and its ID.
		Document doc = new Document();
		doc.add(new Field(Constants.INDEX_VARIATIONPOINT, variationPointId, TYPE_STORED_ID));
		
		if (content != null && content.length() > 0) {
			doc.add(new Field(Constants.INDEX_CONTENT, content, TYPE_STORED_CONTENT));
		} 
		if (comments != null && comments.length() > 0) {
			doc.add(new Field(Constants.INDEX_COMMENT, comments, TYPE_STORED_CONTENT));
		}
		
		if (doc.getFields().size() < 2) {
			return;
		}
		
		// Add the document to the index through a new IndexWriter.
		IndexWriter indexWriter = new IndexWriter(directory, config);
		indexWriter.addDocument(doc);
		indexWriter.close();
	}

	/**
	 * Deletes all contents from the main index.
	 * @throws IOException 
	 */
	public void clearIndex() throws IOException {
		IndexWriter writer = new IndexWriter(directory, config);
		writer.deleteAll();
		writer.close();
	}
	
	/**
	 * Just for testing purposes.
	 */
	public void printIndexContents(){
		try {
			File file = new File("C:\\Users\\Daniel\\Desktop\\log.txt");
			file.createNewFile();
			PrintWriter out = new PrintWriter(file);
			
			DirectoryReader reader = getIndexReader();
			for (int i = 0; i < reader.maxDoc(); i++) {
				Document doc;

				doc = reader.document(i);
				String id = doc.get(Constants.INDEX_VARIATIONPOINT);
				String cont = doc.get(Constants.INDEX_CONTENT);
				String com = doc.get(Constants.INDEX_COMMENT);
				if (id != null && cont != null){
					out.println("##########" + id + "##########");
					if(cont != null)
						out.println("Content: " + cont);
					if(com != null)
						out.println("Comment: " + com);
				}
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
