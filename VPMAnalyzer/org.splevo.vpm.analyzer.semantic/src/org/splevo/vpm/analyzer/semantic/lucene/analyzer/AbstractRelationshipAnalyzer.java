package org.splevo.vpm.analyzer.semantic.lucene.analyzer;

import org.apache.lucene.index.DirectoryReader;
import org.splevo.vpm.analyzer.semantic.StructuredMap;

/**
 * To allow adding new evaluation methods easily, this class has been created.
 * 
 * @author Daniel Kojic
 *
 */
public abstract class AbstractRelationshipAnalyzer {

	/**
	 * Calculated the similarity between all nodes from the {@link DirectoryReader}'s index.
	 * 
	 * @param reader The reader addressing the index.
	 * @return A {@link StructuredMap} storing all found relationships.
	 */
	public abstract StructuredMap findSimilarEntries(DirectoryReader reader);

}
