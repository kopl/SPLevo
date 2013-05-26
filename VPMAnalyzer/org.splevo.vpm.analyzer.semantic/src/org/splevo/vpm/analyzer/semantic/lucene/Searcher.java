package org.splevo.vpm.analyzer.semantic.lucene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.splevo.vpm.analyzer.semantic.Constants;

/**
 * @author Daniel Kojic
 *
 */
public class Searcher {
	
	/** The logger for this class. */
    private Logger logger = Logger.getLogger(Indexer.class);
    
    /** The Directory reader for the main index. */
	private DirectoryReader reader;
	
	/** Singleton instance. */
	private static Searcher instance;

	/**
	 *  Private constructor to prevent this	class from being
	 *  instantiated multiple times.
	 */
	private Searcher(){
		try {
			reader = Indexer.getInstance().getIndexReader();
		} catch (IOException e) {
			logger.error("Error while reading Index.");
		}
	}
	
	/**
	 * Gets the singleton instance.
	 * @return The singleton instance.
	 */
	public static Searcher getInstance() {
		// Return singleton, create new if not existing.
		return instance == null ? instance = new Searcher() : instance;
	}
	
	public Map<String, String> findSemanticRelationships(IRelationshipAnalyzer analyzer, double minSimilarity){
		HashMap<String, String> results = new HashMap<String, String>();
		
		for (int i=0; i<reader.maxDoc(); i++) {
			
		    Document doc1;
			try {
				doc1 = reader.document(i);
			} catch (Exception e) {
				continue;
			}
		    String docId1 = doc1.get(Constants.VARIATIONPOINT_INDEX_ID);
		    
		    for (int q=0; q<reader.maxDoc(); q++) {
		    	if(i == q){
		    		continue;
		    	}
		    	Document doc2;
		    	try {
					doc2 = reader.document(q);
				} catch (IOException e) {
					continue;
				}
		        String docId2 = doc2.get(Constants.VARIATIONPOINT_INDEX_ID);
		        
		        double similarity = analyzer.calculateSimilarity(reader, i, q);
		        if(Double.isNaN(similarity)){
		        	continue;
		        }
		        if(similarity >= minSimilarity){
		        	results.put(docId1, docId2);
		        }
		    }
		}

		return results;
	}
}
