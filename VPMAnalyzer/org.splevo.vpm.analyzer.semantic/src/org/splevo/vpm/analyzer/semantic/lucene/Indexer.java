package org.splevo.vpm.analyzer.semantic.lucene;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
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
 * This class handles the indexing. It creates one single main index. The class provides
 * methods to add content to the index using customizable {@link Analyzer}s. Via a {@link DirectoryReader}
 * the {@link Indexer} class provides access to the index.
 * 
 * @author Daniel Kojic
 * 
 */
public class Indexer {
	
	/** The logger for this class. */
    private Logger logger = Logger.getLogger(Indexer.class);
	
	/** The {@link Analyzer} everything gets indexed with. */
	private Analyzer analyzer;
	
	/** The {@link Directory} for the index. */
	private Directory directory;
	
	/** The {@link IndexWriterConfig}. */
	private IndexWriterConfig config;
	
	/** Singleton instance. */
	private static Indexer instance;
	
    /** Indexed, tokenized, stored. */
    private static final FieldType TYPE_STORED = new FieldType();

    static {
    	// Setup of fields beeing indexed. Store TermVectors for later analysis.
        TYPE_STORED.setIndexed(true);
        TYPE_STORED.setTokenized(true);
        TYPE_STORED.setStored(true);
        TYPE_STORED.setStoreTermVectors(true);
        TYPE_STORED.setStoreTermVectorPositions(true);
        TYPE_STORED.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
    }
	
	/**
	 *  Private constructor to prevent this	class from being
	 *  instantiated multiple times.
	 *  The default analyzer will be the StandardAnalyzer.
	 * @throws IOException Throws an {@link IOException} if there are problems opening the index.
	 */
	private Indexer() {
		// Set default values.
		analyzer = new StandardAnalyzer(Version.LUCENE_42);
		directory = new RAMDirectory();
		config = new IndexWriterConfig(Version.LUCENE_42, analyzer);
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
	 * Adds content to the main index.
	 * 
	 * @param variationPointId The ID of the {@link VariationPoint} to be linked with its content.
	 * @param content The text content of the {@link VariationPoint}.
	 */
	public void addToIndex(String variationPointId, String content){
		try {
			IndexWriter indexWriter = new IndexWriter(directory, config);
			Document doc = new Document();
			doc.add(new Field(Constants.Index_VARIATIONPOINT, variationPointId, TYPE_STORED));
			doc.add(new Field(Constants.INDEX_CONTENT, content, TYPE_STORED));
			indexWriter.addDocument(doc);
			indexWriter.close();
		} catch (IOException e) {
			logger.error("Error while adding data to Index.");
		}
	}
	
	/**
	 * Deletes the main index with all the content and opens a new, empty index.
	 */
	public void clearIndex(){
		try {
			directory.close();
		} catch (IOException e) {
			logger.error("Cannot close Index. Check for open filehandles.");
		}
		directory = new RAMDirectory();
	}
		
	/**
	 * Just for testing purposes.
	 */
	public void printIndexContents(){
		try {
			DirectoryReader reader = getIndexReader();
			for (int i = 0; i < reader.maxDoc(); i++) {
				Document doc;

				doc = reader.document(i);
				String cont = doc.get(Constants.INDEX_CONTENT);
				if (cont.length() != 0)
					System.out.println(cont);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
