package org.splevo.vpm.analyzer.semantic.lucene.analyzer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.lucene.index.DirectoryReader;
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
    
    /**
	 * Initializations. Queries the content of the
	 * given {@link DirectoryReader}.
	 * 
	 * @param reader The {@link DirectoryReader}.
	 */
    public AbstractRelationshipFinder(DirectoryReader reader) {
    	this.reader = reader;
    }

	/**
	 * Calculated the similarity between all nodes from the {@link DirectoryReader}'s index.
	 * 
	 * @return A {@link StructuredMap} storing all found relationships.
	 */
	public abstract StructuredMap findSimilarEntries();
	
	/**
     * Extracts the frequencies of all {@link Term}s in the specified {@link Document}.
     * 
     * @param docId The ID of the {@link Document} to extract the {@link Term}s from.
     * @param terms Contains all terms of the given document.
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
