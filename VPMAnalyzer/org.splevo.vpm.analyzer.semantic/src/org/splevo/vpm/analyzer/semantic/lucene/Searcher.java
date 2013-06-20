package org.splevo.vpm.analyzer.semantic.lucene;

import java.io.IOException;

import org.apache.lucene.index.DirectoryReader;
import org.splevo.vpm.analyzer.semantic.StructuredMap;
import org.splevo.vpm.analyzer.semantic.lucene.analyzer.FinderExecutor;
import org.splevo.vpm.analyzer.semantic.lucene.analyzer.OverallSimilarityFinder;
import org.splevo.vpm.analyzer.semantic.lucene.analyzer.RareTermFinder;

/**
 * Use this class to find dependencies between documents of the main index.
 * 
 * @author Daniel Kojic
 *
 */
public class Searcher {
	
	/**
	 * Searches the index (the one hold by the Indexer singleton) to
	 * find relationships between the Cartesian product of all documents.
	 * 
	 * @param useRareTermFinder Determines weather to use RareTermFinder or not.
	 * @param useOverallSimilarityFinder Determines weather to use OverallSimilarityFinder or not.
	 * @return A {@link Map} containing the {@link VariationPoint} IDs having a relationship.
	 * @throws IOException Throws an exception if there is already an open writer to the index.
	 */
	public static StructuredMap findSemanticRelationships(boolean useRareTermFinder, 
			boolean useOverallSimilarityFinder) throws IOException {
		if (!useRareTermFinder && !useOverallSimilarityFinder) {
			throw new IllegalStateException();
		}
		
		// Open the Directory reader for the main index.
		DirectoryReader reader = Indexer.getInstance().getIndexReader();
		
		// This class executes the analysis.
		FinderExecutor analysisExecutor = new FinderExecutor();
		
		// Add wanted analysis options here:
		if (useRareTermFinder) {
			analysisExecutor.addAnalyzer(new RareTermFinder(reader));
		}
		
		if (useOverallSimilarityFinder) {
			analysisExecutor.addAnalyzer(new OverallSimilarityFinder(reader));
		}
				
		// Store found relationships in this StructuredMap.
		StructuredMap result = analysisExecutor.executeAnalysis();
		reader.close();
		return result;
	}
}
