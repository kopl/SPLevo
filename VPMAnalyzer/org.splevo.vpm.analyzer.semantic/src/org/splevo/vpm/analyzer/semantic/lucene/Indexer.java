package org.splevo.vpm.analyzer.semantic.lucene;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
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
import org.splevo.vpm.variability.VariationPoint;

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
public class Indexer {
	
	/** The logger for this class. */
    private Logger logger = Logger.getLogger(Indexer.class);
	
	/** The {@link Analyzer} code gets indexed with. */
	private Analyzer contentAnalyzer;
	
	/** The {@link Analyzer} comments get indexed with. */
	private Analyzer commentAnalyzer;
	
	/** The {@link IndexWriterConfig} for code. */
	private IndexWriterConfig contentConfig;

	/** The {@link IndexWriterConfig} for comments. */
	private IndexWriterConfig commentConfig;
	
	/** The {@link Directory} for the index. */
	private Directory directory;
	
	/** Singleton instance. */
	private static Indexer instance;
	
    /** Indexed, tokenized, stored. */
    private static final FieldType TYPE_STORED = new FieldType();

    // Define the Field-Type the text gets added to the index.
    // To allow term exrtaction, DOCS_AND_FREQS has to be stored
    // in the index.
    static {
    	TYPE_STORED.setIndexed(true);
        TYPE_STORED.setTokenized(true);
        TYPE_STORED.setStored(true);
        TYPE_STORED.setStoreTermVectors(true);
        TYPE_STORED.setIndexOptions(IndexOptions.DOCS_AND_FREQS);
    }
	
	/**
	 *  Private constructor to prevent this	class from being instantiated multiple times.
	 *  
	 * @throws IOException Throws an {@link IOException} if there are problems opening the index.
	 */
	private Indexer() {
		// Define the Analyzers.
		contentAnalyzer = Constants.CONTENT_ANALYZER;
		commentAnalyzer = Constants.COMMENT_ANALYZER;
		
		// Use RAMDirectory to use an in-memory index. 
		directory = new RAMDirectory();
		
		// Derive the configurations from the Analyzers.
		contentConfig = new IndexWriterConfig(Version.LUCENE_42, contentAnalyzer);
		commentConfig = new IndexWriterConfig(Version.LUCENE_42, commentAnalyzer);
	}
	
	/**
	 * Gets the singleton instance.
	 * @return The singleton instance.
	 */
	public static Indexer getInstance() {
		// Return singleton, create new if not existing.
		return instance == null ? instance = new Indexer() : instance;
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
	 */
	public void addCodeToIndex(String variationPointId, String content){
		if(variationPointId == null || content == null){
			throw new IllegalArgumentException();
		}
		
		addToIndex(variationPointId, content, contentConfig);
	}

	/**
	 * This method adds the given text to the index. 
	 * This Method is specialized to store comments.
	 * 
	 * @param variationPointId The contents id.
	 * @param content The content to be indexed.
	 */
	public void addCommentToIndex(String variationPointId, String comment){
		if(variationPointId == null || comment == null){
			throw new IllegalArgumentException();
		}
		
		addToIndex(variationPointId, comment, commentConfig);
	}
	
	/**
	 * Adds content to the main index.
	 * 
	 * @param variationPointId The ID of the {@link VariationPoint} to be linked with its content.
	 * @param content The text content of the {@link VariationPoint}.
	 * @param indexConfiguration The {@link IndexWriterConfig} the {@link IndexWriter} gets initialized with. 
	 */
	private void addToIndex(String variationPointId, String content, IndexWriterConfig indexConfiguration){
		if(variationPointId == null || content == null || indexConfiguration == null){
			throw new IllegalArgumentException();
		}
		
		if(content.length() == 0 || variationPointId.length() == 0){
			logger.error("Invalid content or id. Empty String not allowed.");
		}
		
		try {
			addDocument(variationPointId, content, indexConfiguration);
		} catch (IOException e) {
			logger.error("Error while adding data to Index.");
		}		
	}

	/**
	 * Adds a {@link Document} to the index with the given VP ID and the text.
	 * 
	 * @param variationPointId The ID to be stored.
	 * @param content The text to be added to the index.
	 * @param indexConfiguration The {@link IndexWriterConfig} that is used to open the {@link IndexWriter}.
	 */
	private void addDocument(String variationPointId, String content,
			IndexWriterConfig indexConfiguration) throws IOException {
		IndexWriter indexWriter = new IndexWriter(directory, indexConfiguration);
		Document doc = new Document();
		doc.add(new Field(Constants.INDEX_VARIATIONPOINT, variationPointId, TYPE_STORED));
		doc.add(new Field(Constants.INDEX_CONTENT, content, TYPE_STORED));
		indexWriter.addDocument(doc);
		indexWriter.close();
	}
	
	/**
	 * Deletes all contents from the main index.
	 * @throws IOException 
	 */
	public void clearIndex() throws IOException{
		IndexWriter indexWriter = new IndexWriter(directory, contentConfig);
		indexWriter.deleteAll();
		indexWriter.close();
	}
}
