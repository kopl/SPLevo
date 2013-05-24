package org.splevo.vpm.analyzer.semantic;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

/**
 * @author Daniel
 * 
 * This class handles the indexing. It creates one single main index. The class provides
 * methods to add content to the index using customizable {@link Analyzer}s. Via a {@link DirectoryReader}
 * the {@link Indexer} class provides access to the index.
 */
public class Indexer {
	
	/** The {@link Analyzer} everything gets indexed with. */
	private Analyzer analyzer;
	
	/** The {@link Directory} for the index. */
	private Directory directory;
	
	/** The {@link IndexWriterConfig}. */
	private IndexWriterConfig config;
	
	/** The {@link IndexWriter} responsible for the main index. */
	private IndexWriter indexWriter;
	
	/** Singleton instance. */
	private static Indexer instance;
	
	/**
	 *  Private constructor to prevent this	class from being
	 *  instantiated multiple times.
	 *  The default analyzer will be the StandardAnalyzer.
	 * @throws IOException Throws an {@link IOException} if there are problems opening the index.
	 */
	private Indexer() throws IOException{
		// Set default values.
		analyzer = new StandardAnalyzer(Version.LUCENE_42);
		directory = new RAMDirectory();
		config = new IndexWriterConfig(Version.LUCENE_42, analyzer);
		indexWriter = new IndexWriter(directory, config);
	}
	
	/**
	 * Gets the singleton instance.
	 * @return The singleton instance.
	 * @throws IOException Throws an {@link IOException} if there are problems opening the index.
	 */
	public static Indexer getInstance() throws IOException{
		return instance == null ? instance = new Indexer() : instance;
	}
	
	/**
	 * @return A {@link DirectoryReader} to search the main index.
	 * @throws IOException
	 */
	public DirectoryReader getIndexReader() throws IOException {
		return DirectoryReader.open(directory);
	}
	
	public void addToIndex(String fieldName, String content){
		Document doc = new Document();
		doc.add(new StringField(fieldName, content, Field.Store.YES));
	}
}
