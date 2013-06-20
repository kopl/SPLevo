package org.splevo.vpm.analyzer.semantic.lucene;

import java.io.IOException;

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
	
    /** The writer configuration for code fragments. */
	private IndexWriterConfig contentConfig;
	
    /** The writer configuration for comment fragments. */
	private IndexWriterConfig commentConfig;
	
	/** The {@link Directory} for the index. */
	private Directory directory;
	
	/** Singleton instance. */
	private static Indexer instance;
	
    /** Indexed, tokenized, stored. */
    private static final FieldType TYPE_STORED_CONTENT = new FieldType();

    /** Indexed, tokenized, stored. */
    private static final FieldType TYPE_STORED_ID = new FieldType();
    
    // Define the Field-Type the text gets added to the index.
    // To allow term exrtaction, DOCS_AND_FREQS has to be stored
    // in the index.
    static {
    	TYPE_STORED_CONTENT.setIndexed(true);
        TYPE_STORED_CONTENT.setTokenized(true);
        TYPE_STORED_CONTENT.setStored(false);
        TYPE_STORED_CONTENT.setStoreTermVectors(true);
        TYPE_STORED_CONTENT.setIndexOptions(IndexOptions.DOCS_AND_FREQS);
    }
    
    // Define the Field-Type the IDs get added to the index.
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
		contentConfig = new IndexWriterConfig(Version.LUCENE_43, Constants.CODE_ANALYZER);
		commentConfig = new IndexWriterConfig(Version.LUCENE_43, Constants.COMMENT_ANALYZER);		
		
		// Use RAMDirectory to use an in-memory index. 
		directory = new RAMDirectory();
//		try {
//			File file = new File(Constants.INDEX_DIRECTORY);
//			if(!file.exists())
//				file.mkdir();
//			directory = FSDirectory.open(file);
//			clearIndex();
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
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
	 * This method adds the given text to the index. 
	 * This Method is specialized to store code fragments.
	 * 
	 * @param variationPointId The contents id.
	 * @param content The content to be indexed.
	 * @return True if something was indexed; False otherwise.
	 * @throws IOException 
	 */
	public boolean addCodeToIndex(String variationPointId, String content) throws IOException {
		if (variationPointId == null || content == null) {
			throw new IllegalArgumentException();
		}
		
		return addToIndex(variationPointId, content, false);
	}

	/**
	 * This method adds the given text to the index. 
	 * This Method is specialized to store comments.
	 * 
	 * @param variationPointId The contents id.
	 * @param comment The content to be indexed.
 	 * @return True if something was indexed; False otherwise.
	 * @throws IOException 
	 */
	public boolean addCommentToIndex(String variationPointId, String comment) throws IOException {
		if (variationPointId == null || comment == null) {
			throw new IllegalArgumentException();
		}
		
		return addToIndex(variationPointId, comment, true);
	}
	
	/**
	 * Adds content to the main index.
	 * 
	 * @param variationPointId The ID of the {@link VariationPoint} to be linked with its content.
	 * @param content The text content of the {@link VariationPoint}.
	 * @param isComment Determines weather the content is a comment or code.
	 * @return True if something was indexed; False otherwise.
	 * @throws IOException 
	 */
	private boolean addToIndex(String variationPointId, String content, boolean isComment) throws IOException {
		if (variationPointId == null || content == null) {
			throw new IllegalArgumentException();
		}
		
		if (variationPointId.length() == 0) {
			logger.error("Invalid id. Empty String not allowed.");
			return false;
		}

		if (content.length() <= 2) {
			// Only index text with length greater than 2.
			return false;
		}
		
		try {
			addDocument(variationPointId, content, isComment);
		} catch (IOException e) {
			logger.error("Error while adding data to Index.");
			return false;
		}	
		
		return true;
	}

	/**
	 * Adds a {@link Document} to the index with the given VP ID and the text.
	 * 
	 * @param variationPointId The ID to be stored.
	 * @param content The text to be added to the index.
	 * @param isComment Determines weather the content is a comment or code.
	 * @throws IOException If the index cannot be opened or there are problems adding the document to the index.
	 */
	private void addDocument(String variationPointId, String content, boolean isComment) throws IOException {
		Document doc = new Document();
		doc.add(new Field(Constants.INDEX_VARIATIONPOINT, variationPointId, TYPE_STORED_ID));
		doc.add(new Field(Constants.INDEX_CONTENT, content, TYPE_STORED_CONTENT));
		IndexWriter indexWriter = new IndexWriter(directory, isComment ? commentConfig : contentConfig);
		indexWriter.addDocument(doc);
		indexWriter.close();
	}

	/**
	 * Deletes all contents from the main index.
	 * @throws IOException 
	 */
	public void clearIndex() throws IOException {
		IndexWriter writer = new IndexWriter(directory, contentConfig);
		writer.deleteAll();
		writer.close();
	}
}
