package org.splevo.vpm.analyzer.semantic.lucene.analyzer;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;

/**
 * To allow adding new evaluation methods easily, this class has been created.
 * 
 * @author Daniel Kojic
 *
 */
public interface IRelationshipAnalyzer {

	/**
	 * Use this method to calculate a distance metric between the two given documents.
	 * The {@link Document}s must be part of the index of the given reader.
	 * 
	 * @param reader The {@link DirectoryReader} of the index to be searched.
	 * @param docID1 The first {@link Document}.
	 * @param docID2 The second {@link Document}.
	 * @return A {@link Double} value representing the similarity. The meaning of the value may depend on the analysis technique.
	 */
	public double calculateSimilarity(DirectoryReader reader, int docID1, int docID2);

}
