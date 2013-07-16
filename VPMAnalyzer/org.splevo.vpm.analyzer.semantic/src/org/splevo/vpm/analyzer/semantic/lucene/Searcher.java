package org.splevo.vpm.analyzer.semantic.lucene;

import java.io.IOException;

import org.apache.lucene.index.DirectoryReader;
import org.splevo.vpm.analyzer.semantic.VPLinkContainer;
import org.splevo.vpm.analyzer.semantic.lucene.finder.FinderExecutor;
import org.splevo.vpm.analyzer.semantic.lucene.finder.OverallSimilarityFinder;
import org.splevo.vpm.analyzer.semantic.lucene.finder.RareTermFinder;
import org.splevo.vpm.analyzer.semantic.lucene.finder.TopNTermFinder;

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
	 * @param searchConfig The search configurations.
	 * @return A {@link Map} containing the {@link VariationPoint} IDs having a relationship.
	 * @throws IOException Throws an exception if there is already an open writer to the index.
	 */
	public static VPLinkContainer findSemanticRelationships(RelationShipSearchConfiguration searchConfig) throws IOException {
		if (!searchConfig.isUseOverallSimilarityFinder() 
				&& 	!searchConfig.isUseRareTermFinder() 
				&&  !searchConfig.isUseTopNFinder()) {
			throw new IllegalStateException();
		}
		
		// Open the Directory reader for the main index.
		DirectoryReader reader = Indexer.getInstance().getIndexReader();
		
		// This class executes the analysis.
		FinderExecutor analysisExecutor = new FinderExecutor();
		
		// Add finders here:
		if (searchConfig.isUseRareTermFinder()) {
			analysisExecutor.addAnalyzer(new RareTermFinder(reader, searchConfig.isMatchComments(), 
					searchConfig.getMaxPercentage()));
		}
		
		if (searchConfig.isUseOverallSimilarityFinder()) {
			analysisExecutor.addAnalyzer(new OverallSimilarityFinder(reader, searchConfig.isMatchComments(), 
					searchConfig.getMinSimilarity()));
		}

		if (searchConfig.isUseTopNFinder()) {
			analysisExecutor.addAnalyzer(new TopNTermFinder(reader, searchConfig.isMatchComments(), 
					searchConfig.getLeastDocFreq(), searchConfig.getN()));
		}		
		
		// Store found relationships in this StructuredMap.
		VPLinkContainer result = analysisExecutor.executeAnalysis();
		
		// Close reader.
		reader.close();
		
		return result;
	}
}
