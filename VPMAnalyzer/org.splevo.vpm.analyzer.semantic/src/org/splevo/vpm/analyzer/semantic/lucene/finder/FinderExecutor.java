package org.splevo.vpm.analyzer.semantic.lucene.finder;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
			result = merge(result, tmpResult);
		}
		
		return result;
	}

	/**
	 * Merges the content of two containers.
	 * 
	 * @param m1 The first container.
	 * @param m2 The second container.
	 * @return A {@link StructuredMap} containing all links given by the argument maps.
	 */
	private StructuredMap merge(StructuredMap m1, StructuredMap m2) {
		if (m1 == null || m2 == null) {
			throw new IllegalArgumentException();
		}
		
		StructuredMap result = new StructuredMap();
			
		for (String key : m1.getAllLinks().keySet()) {
			result.addLinks(key, m1.getAllLinks().get(key));
		}
		
		for (String key : m2.getAllLinks().keySet()) {
			result.addLinks(key, m2.getAllLinks().get(key));
		}
		
		Map<String, Set<String>> m1Exp = m1.getExplanations();
		Map<String, Set<String>> m2Exp = m2.getExplanations();
		
		for (String key : m2Exp.keySet()) {
			if (m1Exp.get(key) == null) {
				m1Exp.put(key, new HashSet<String>());
			}
			m1Exp.get(key).addAll(m2Exp.get(key));
		}
		
		result.setExplanations(m1Exp);
		
		return result;
	}
}
