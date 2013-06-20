package org.splevo.vpm.analyzer.semantic.lucene.analyzer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math.linear.RealVector;
import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.util.BytesRef;
import org.splevo.vpm.analyzer.semantic.Constants;
import org.splevo.vpm.analyzer.semantic.StructuredMap;

/**
 * To allow adding new evaluation methods easily, this class has been created.
 * 
 * @author Daniel Kojic
 *
 */
public abstract class AbstractRelationshipFinder {
	
	/** The logger for this class. */
    private Logger logger = Logger.getLogger(AbstractRelationshipFinder.class);
    
    /** The reader containing the content to be matched. */
    protected DirectoryReader reader;
    
    public AbstractRelationshipFinder(DirectoryReader reader){
    	this.reader = reader;
    }

	/**
	 * Calculated the similarity between all nodes from the {@link DirectoryReader}'s index.
	 * 
	 * @param reader The reader addressing the index.
	 * @return A {@link StructuredMap} storing all found relationships.
	 */
	public abstract StructuredMap findSimilarEntries();
	
	/**
	 * Calculates the cosine similarity between v1 and v2.
	 * @param v1 The first vector.
	 * @param v2 The second vector.
	 * @return The cosine similarity.
	 */
	protected double getCosineSimilarity(RealVector v1, RealVector v2) {
        return (v1.dotProduct(v2)) / (v1.getNorm() * v2.getNorm());
    }
	
	/**
	 * Calculates the cosine similarity between v1 and v2.
	 * @param v1 The first vector.
	 * @param v2 The second vector.
	 * @return The cosine similarity.
	 */
	protected double getEuclideanDistance(RealVector v1, RealVector v2) {
        return v1.getDistance(v2);
    }
	
	/**
     * Extracts the frequencies of all {@link Term}s in the specified {@link Document}.
     * 
     * @param reader The {@link IndexReader} containing the document.
     * @param docId The ID of the {@link Document} to extract the {@link Term}s from.
     * @return A {@link Map} containing the terms as the key and the related frequencies as {@link Integer} value.
     */
    protected Map<String, Integer> getTermFrequencies(int docId, Set<String> terms) {
        Map<String, Integer> frequencies = new HashMap<String, Integer>();
        
		try {
			Terms vector = reader.getTermVector(docId, Constants.INDEX_CONTENT);
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
}
