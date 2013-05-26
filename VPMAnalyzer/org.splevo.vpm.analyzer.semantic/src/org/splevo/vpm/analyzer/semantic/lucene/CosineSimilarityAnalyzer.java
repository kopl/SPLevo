package org.splevo.vpm.analyzer.semantic.lucene;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.log4j.Logger;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.util.BytesRef;
import org.splevo.vpm.analyzer.semantic.Constants;

public class CosineSimilarityAnalyzer implements IRelationshipAnalyzer {
	
	/** The logger for this class. */
    private Logger logger = Logger.getLogger(CosineSimilarityAnalyzer.class);

	private final Set<String> terms = new HashSet<String>();
    private RealVector v1;
    private RealVector v2;
    
	@Override
	public double calculateSimilarity(DirectoryReader reader, int docID1, int docID2) {
		Map<String, Integer> f1 = getTermFrequencies(reader, docID1);
        Map<String, Integer> f2 = getTermFrequencies(reader, docID2);
        v1 = toRealVector(f1);
        v2 = toRealVector(f2);
        
		return getCosineSimilarity();
	}
	
	private double getCosineSimilarity() {
        return (v1.dotProduct(v2)) / (v1.getNorm() * v2.getNorm());
    }

    Map<String, Integer> getTermFrequencies(IndexReader reader, int docId)
             {
        Terms vector;
        Map<String, Integer> frequencies = new HashMap<String, Integer>();
        
		try {
			vector = reader.getTermVector(docId, Constants.CONTENT_INDEX_ID);
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
