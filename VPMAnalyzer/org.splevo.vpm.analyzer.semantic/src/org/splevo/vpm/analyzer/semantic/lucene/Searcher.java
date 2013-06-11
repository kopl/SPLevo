package org.splevo.vpm.analyzer.semantic.lucene;

import java.io.IOException;
import org.apache.lucene.index.DirectoryReader;
import org.splevo.vpm.analyzer.semantic.Constants;
import org.splevo.vpm.analyzer.semantic.StructuredMap;
import org.splevo.vpm.analyzer.semantic.lucene.analyzer.AnalysisExecutor;
import org.splevo.vpm.analyzer.semantic.lucene.analyzer.CosineSimilarityAnalyzer;

/**
 * Use this class to find dependencies between documents of the main index.
 * 
 * @author Daniel Kojic
 *
 */
public class Searcher {
	
	/** The minimum cosine similarity. */
	private double minCosineSimilarity;
	
	/**
	 * The default constructor of this class. Initializes the search configurations.
	 */
	public Searcher() {
		minCosineSimilarity = Constants.DEFAULT_MIN_COSINE_SIMILARITY;
	}
	
	/**
	 * Searches the index (the one hold by the Indexer singleton) to
	 * find relationships between the Cartesian product of all documents.
	 * 
	 * @return A {@link Map} containing the {@link VariationPoint} IDs having a relationship.
	 * @throws IOException Throws an exception if there is already an open writer to the index.
	 */
	public StructuredMap findSemanticRelationships() throws IOException {
		// Open the Directory reader for the main index.
		DirectoryReader reader = Indexer.getInstance().getIndexReader();
		
		// This class executes the analysis.
		AnalysisExecutor analysisExecutor = new AnalysisExecutor(reader);
		
		// Add wanted analysis options here:
		analysisExecutor.addAnalyzer(new CosineSimilarityAnalyzer(minCosineSimilarity));
		//analysisExecutor.addAnalyzer(new TestAnalyzer());
		
		// Store found relationships in this StructuredMap.
		return analysisExecutor.executeAnalysis();
	}
	
	/**
	 * Setter for the minimum cosine similarity.
	 * 
	 * @param sim The similarity.
	 */
	public void setMinCosineSimilarity(double sim) {
		this.minCosineSimilarity = sim;
	}
}
