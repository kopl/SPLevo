package org.splevo.vpm.analyzer.semantic.lucene.analyzer;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.RealVector;
import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.util.BytesRef;
import org.splevo.vpm.analyzer.semantic.Constants;
import org.splevo.vpm.analyzer.semantic.StructuredMap;

/**
 * This class analyzer calculates the Cosine Similarity.
 * See <a href="http://en.wikipedia.org/wiki/Cosine_similarity" />
 * 
 * @author Daniel Kojic
 *
 */
public class CosineSimilarityAnalyzer extends AbstractRelationshipAnalyzer {
	
	/** The logger for this class. */
    private Logger logger = Logger.getLogger(CosineSimilarityAnalyzer.class);

	/** This set contains all Terms. */
	private final Set<String> terms = new HashSet<String>();	

    /**
     * Extracts the frequencies of all {@link Term}s in the specified {@link Document}.
     * 
     * @param reader The {@link IndexReader} containing the document.
     * @param docId The ID of the {@link Document} to extract the {@link Term}s from.
     * @return A {@link Map} containing the terms as the key and the related frequencies as {@link Integer} value.
     */
    private Map<String, Integer> getTermFrequencies(IndexReader reader, int docId) {
        Terms vector;
        Map<String, Integer> frequencies = new HashMap<String, Integer>();
        
		try {
			vector = reader.getTermVector(docId, Constants.INDEX_CONTENT);
			if (vector == null) {
				return frequencies;
			}
			TermsEnum termsEnum = null;
			termsEnum = vector.iterator(termsEnum);
	        BytesRef text = null;
	        while ((text = termsEnum.next()) != null) {
	            String term = text.utf8ToString();
	            int freq = (int) termsEnum.totalTermFreq();
	            frequencies.put(term, freq);
	            terms.add(term);
	        }
		} catch (IOException e) {
			logger.error("Failure while extracting Term Frequencies.");
		}        
        return frequencies;
    }    
    
    /**
     * Transforms the given {@link Map} into an vector.
     * 
     * @param map The {@link Map} to be transformed.
     * @return The vector representing the given {@link Map}.
     */
    RealVector toRealVector(Map<String, Integer> map) {
        RealVector vector = new ArrayRealVector(terms.size());
        int i = 0;
        for (String term : terms) {
            int value = map.containsKey(term) ? map.get(term) : 0;
            vector.setEntry(i++, value);
        }
        return (RealVector) vector.mapDivide(vector.getL1Norm());
    }

	@Override
	public StructuredMap findSimilarEntries(DirectoryReader reader, double minSimilarity) {
		StructuredMap result = new StructuredMap();
		for (int i = 0; i < reader.maxDoc(); i++) {			
		    Document doc1;
			try {
				doc1 = reader.document(i);
			} catch (Exception e) {
				logger.error("Failure while reading the first document.");
				continue;
			}
		    String docId1 = doc1.get(Constants.INDEX_VARIATIONPOINT);
		    
		    for (int q = 0; q < reader.maxDoc(); q++) {
		    	if (i == q) {
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
		        
		        // Get the Term frequencies from the documents.
				Map<String, Integer> f1 = getTermFrequencies(reader, i);
		        Map<String, Integer> f2 = getTermFrequencies(reader, q);
		        
		        // Transform the frequencies into vectors.
		        RealVector v1 = toRealVector(f1);
		        RealVector v2 = toRealVector(f2);
		        
		        terms.clear();
		        
		        double similarity =  getCosineSimilarity(v1, v2);

		        if (Double.isNaN(similarity)) {
		        	logger.warn("Invalid similarity calculated.");
		        	continue;
		        }
		        if (similarity >= minSimilarity) {
		        	result.addLink(docId1, docId2);		        	
		        }
		    }
		}
		
		return result;
	}
	
	/**
	 * Calculates the cosine similarity between v1 and v2.
	 * @param v1 The first vector.
	 * @param v2 The second vector.
	 * @return The cosine similarity.
	 */
	protected double getCosineSimilarity(RealVector v1, RealVector v2) {
        return (v1.dotProduct(v2)) / (v1.getNorm() * v2.getNorm());
    }

}
