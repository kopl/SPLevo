package org.splevo.vpm.analyzer.semantic.lucene.finder;

import java.util.LinkedList;
import java.util.List;

import org.splevo.vpm.analyzer.semantic.StructuredMap;

/**
 * This class provides the ability to execute multiple {@link AbstractRelationshipFinder}.
 * 
 * @author Daniel Kojic
 *
 */
public class FinderExecutor {
	
	/** A {@link List} containign all {@link AbstractRelationshipFinder}s. */
	private List<AbstractRelationshipFinder> finders;
	
	/**
	 * Initialization.
	 */
	public FinderExecutor() {
		finders = new LinkedList<AbstractRelationshipFinder>();
	}
	
	/**
	 * @param finder Adds a {@link AbstractRelationshipFinder} to the executor.
	 */
	public void addAnalyzer(AbstractRelationshipFinder finder) {
		if (finder == null) {
			throw new IllegalArgumentException();
		}
		
		finders.add(finder);
	}
	
	/**
	 * Executes the search for all given {@link AbstractRelationshipFinder}.
	 * 
	 * @return A {@link StructuredMap} containing the found matches.
	 */
	public StructuredMap executeAnalysis() {
		StructuredMap result = new StructuredMap();
		
		for (AbstractRelationshipFinder analyzer : finders) {
			StructuredMap tmpResult = analyzer.findSimilarEntries();
			result = StructuredMap.merge(result, tmpResult);
		}
		
		return result;
	}
}
