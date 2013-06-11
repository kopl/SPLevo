package org.splevo.vpm.analyzer.semantic.lucene;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.splevo.vpm.analyzer.semantic.Constants;
import org.splevo.vpm.analyzer.semantic.StructuredMap;
import org.splevo.vpm.analyzer.semantic.lucene.analyzer.AbstractRelationshipAnalyzer;

/**
 * Use this class to find dependencies between documents of the main index.
 * 
 * @author Daniel Kojic
 *
 */
public class Searcher {
	
	/** The logger for this class. */
    private static Logger logger = Logger.getLogger(Searcher.class);
	
	/**
	 * Searches the index (the one hold by the Indexer singleton) to
	 * find relationships between the Cartesian product of all documents.
	 * 
	 * @param analyzer The analyzer to be used to calculate the metric.
	 * @param minSimilarity The minimum similarity. Links below this value are not part of the result.
	 * @return A {@link Map} containing the {@link VariationPoint} IDs having a relationship.
	 * @throws IOException Throws an exception if there is already an open writer to the index.
	 */
	public static StructuredMap findSemanticRelationships(AbstractRelationshipAnalyzer analyzer, double minSimilarity) throws IOException {
		// Open the Directory reader for the main index.
		DirectoryReader reader = Indexer.getInstance().getIndexReader();
		
		// Store found relationships in this StructuredMap.
		return analyzer.findSimilarEntries(reader, minSimilarity);
	}
}
