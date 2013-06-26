package org.splevo.vpm.analyzer.semantic.lucene;

import java.io.IOException;

import org.apache.lucene.index.DirectoryReader;
import org.splevo.vpm.analyzer.semantic.StructuredMap;
import org.splevo.vpm.analyzer.semantic.lucene.analyzer.FinderExecutor;
import org.splevo.vpm.analyzer.semantic.lucene.analyzer.OverallSimilarityFinder;
import org.splevo.vpm.analyzer.semantic.lucene.analyzer.RareTermFinder;
import org.splevo.vpm.analyzer.semantic.lucene.analyzer.TopNTermFinder;

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
	 * @param matchComments Indicates whether to include comments for analysis or not.
	 * @param useRareTermFinder Determines weather to use RareTermFinder or not.
	 * @param useOverallSimilarityFinder Determines weather to use OverallSimilarityFinder or not.
	 * @param useTopNFinder Determines weather to use TopNTermFinder or not.
	 * @param maxPercentage The max. share of terms for the Rare-Finder.
	 * @param minSimilarity The minimum overall similarity.
	 * @param leastDocFreq The minimum document frequency share.
	 * @param n Specifies the max. number of terms to search for.
	 * @return A {@link Map} containing the {@link VariationPoint} IDs having a relationship.
	 * @throws IOException Throws an exception if there is already an open writer to the index.
	 */
	public static StructuredMap findSemanticRelationships(
			boolean matchComments, 
			boolean useRareTermFinder, 
			boolean useOverallSimilarityFinder, 
			boolean useTopNFinder, 
			double minSimilarity, 
			double maxPercentage,
			double leastDocFreq,
			int n
			) throws IOException {
		if (!useRareTermFinder && !useOverallSimilarityFinder) {
			throw new IllegalStateException();
		}
		
		// Open the Directory reader for the main index.
		DirectoryReader reader = Indexer.getInstance().getIndexReader();
		
		// This class executes the analysis.
		FinderExecutor analysisExecutor = new FinderExecutor();
		
		// Add finders here:
		if (useRareTermFinder) {
			analysisExecutor.addAnalyzer(new RareTermFinder(reader, matchComments, maxPercentage));
		}
		
		if (useOverallSimilarityFinder) {
			analysisExecutor.addAnalyzer(new OverallSimilarityFinder(reader, matchComments, minSimilarity));
		}

		if (useTopNFinder) {
			analysisExecutor.addAnalyzer(new TopNTermFinder(reader, matchComments, leastDocFreq, n));
		}		
		
		// Store found relationships in this StructuredMap.
		StructuredMap result = analysisExecutor.executeAnalysis();
		
		// Close reader.
		reader.close();
		
		return result;
	}
}
