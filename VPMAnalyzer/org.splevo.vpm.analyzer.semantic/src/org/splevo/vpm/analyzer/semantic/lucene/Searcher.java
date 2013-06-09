package org.splevo.vpm.analyzer.semantic.lucene;

import java.io.IOException;
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
 * Use this class to find dependencies between documents of the main index.
 * 
 * @author Daniel Kojic
 *
 */
public class Searcher {
	
	/** The logger for this class. */
    private static Logger logger = Logger.getLogger(Searcher.class);
	
	/**
	 * Searches the index (the one hold by the Indexer singleton) to
	 * find relationships between the Cartesian product of all documents.
	 * 
	 * @param analyzer The analyzer to be used to calculate the metric.
	 * @param minSimilarity The minimum similarity. Links below this value are not part of the result.
	 * @return A {@link Map} containing the {@link VariationPoint} IDs having a relationship.
	 * @throws IOException Throws an exception if there is already an open writer to the index.
	 */
	public static Map<String, Set<String>> findSemanticRelationships(IRelationshipAnalyzer analyzer, double minSimilarity) throws IOException{
		// Open the Directory reader for the main index.
		DirectoryReader reader = Indexer.getInstance().getIndexReader();
		
		// Store found relationships in this StructuredMap.
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

		        if(Double.isNaN(similarity)){
		        	continue;
		        }
		        if(similarity >= minSimilarity){
		        	results.addLink(docId1, docId2);		        	
		        }
		    }
		}
		
		// Close reader and return results.
		reader.close();
		return results.getAllLinks();
	}
}
