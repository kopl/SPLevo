package org.splevo.vpm.analyzer.semantic.lucene;

import org.apache.lucene.index.DirectoryReader;

public interface IRelationshipAnalyzer {

	public double calculateSimilarity(DirectoryReader reader, int docID1, int docID2);

}
