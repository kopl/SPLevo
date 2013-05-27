package org.splevo.vpm.analyzer.semantic.lucene.analyzer;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.util.BytesRef;
import org.splevo.vpm.analyzer.semantic.Constants;

/**
 * This class analyzer calculates the Cosine Similarity.
 * See <a href="http://en.wikipedia.org/wiki/Cosine_similarity" />
 * 
 * @author Daniel
 *
 */
public class CosineSimilarityAnalyzer implements IRelationshipAnalyzer {
	
	/** The logger for this class. */
    private Logger logger = Logger.getLogger(CosineSimilarityAnalyzer.class);

	/** This set contains all Terms. */
	private final Set<String> terms = new HashSet<String>();
    private RealVector v1;
    private RealVector v2;
    
    
	@Override
	public double calculateSimilarity(DirectoryReader reader, int docID1, int docID2) {
		// Get the Term frequencies from the documents.
		Map<String, Integer> f1 = getTermFrequencies(reader, docID1);
        Map<String, Integer> f2 = getTermFrequencies(reader, docID2);
        
        // Transform the frequencies into vectors.
        v1 = toRealVector(f1);
        v2 = toRealVector(f2);
        
		return getCosineSimilarity();
	}
	
	/**
	 * @return The cosine similarity between the Vectors v1 and v2.
	 */
	private double getCosineSimilarity() {
        return (v1.dotProduct(v2)) / (v1.getNorm() * v2.getNorm());
    }

    /**
     * Extracts the frequencies of all {@link Term}s in the specified {@link Document}.
     * 
     * @param reader The {@link IndexReader} containing the document.
     * @param docId The ID of the {@link Document} to extract the {@link Term}s from.
     * @return A {@link Map} containing the terms as the key and the related frequencies as {@link Integer} value.
     */
    Map<String, Integer> getTermFrequencies(IndexReader reader, int docId)
             {
        Terms vector;
        Map<String, Integer> frequencies = new HashMap<String, Integer>();
        
		try {
			vector = reader.getTermVector(docId, Constants.INDEX_CONTENT);
			if(vector == null){
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

}
