package org.splevo.vpm.analyzer.semantic.lucene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.splevo.vpm.analyzer.semantic.Constants;
import org.splevo.vpm.analyzer.semantic.StructuredMap;
import org.splevo.vpm.analyzer.semantic.lucene.analyzer.IRelationshipAnalyzer;
import org.splevo.vpm.variability.VariationPoint;

/**
 * This class is responsible for search-related tasks for the main index.
 * 
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
	}
	
	/**
	 * Gets the singleton instance.
	 * @return The singleton instance.
	 */
	public static Searcher getInstance() {
		// Return singleton, create new if not existing.
		return instance == null ? instance = new Searcher() : instance;
	}
	
	/**
	 * Finds relationships between documents.
	 * 
	 * @param analyzer The analyzer to be used to calculate the metric.
	 * @param minSimilarity The minimum similarity. Links below this value are not part of the result.
	 * @return A {@link Map} containing the {@link VariationPoint} IDs having a relationship.
	 * @throws IOException Throws an exception if there is already an open writer to the index.
	 */
	public Map<String, Set<String>> findSemanticRelationships(IRelationshipAnalyzer analyzer, double minSimilarity) throws IOException{
		// Open reader
		reader = Indexer.getInstance().getIndexReader();
		
		StructuredMap results = new StructuredMap();
		
		for (int i=0; i<reader.maxDoc(); i++) {			
		    Document doc1;
			try {
				doc1 = reader.document(i);
			} catch (Exception e) {
				logger.error("Failure while reading the first document.");
				continue;
			}
		    String docId1 = doc1.get(Constants.INDEX_VARIATIONPOINT);
		    
		    for (int q=0; q<reader.maxDoc(); q++) {
		    	if(i == q){
		    		continue;
		    	}
		    	Document doc2;
		    	try {
					doc2 = reader.document(q);
				} catch (IOException e) {
					logger.error("Failure while reading the second document.");
					continue;
				}
		        String docId2 = doc2.get(Constants.INDEX_VARIATIONPOINT);
		        
		        double similarity = analyzer.calculateSimilarity(reader, i, q);
		        //System.out.println("++++++++++++++++++++++++++++++++++" + similarity);
		        if(Double.isNaN(similarity)){
		        	continue;
		        }
		        if(similarity >= minSimilarity){
		        	results.addLink(docId1, docId2);		        	
		        }
		    }
		}
		reader.close();
		return results.getAllLinks();
	}
}
