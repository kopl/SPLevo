package org.splevo.diffing.match;

import org.eclipse.emf.compare.match.IMatchEngine;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryImpl;
import org.eclipse.emf.compare.match.resource.IResourceMatcher;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.compare.utils.IEqualityHelper;
import org.splevo.diffing.match.HierarchicalMatchEngine.EqualityStrategy;
import org.splevo.diffing.match.HierarchicalMatchEngine.IgnoreStrategy;

/**
 * Factory specific for hierarchical match engines.
 */
public class HierarchicalMatchEngineFactory extends MatchEngineFactoryImpl {

	/** The equality helper to wire with the comparison model. */
	private IEqualityHelper equalityHelper;
	/** The equality strategy to use for element matching. */
	private EqualityStrategy equalityStrategy;
	/** The strategy to use to ignore elements. */
	private IgnoreStrategy ignoreStrategy;

	/** The mode which resource matcher to use. */
	private IResourceMatcher resourceMatcher;

	/**
	 * Constructor to set the required match engine dependencies.
	 * 
	 * @param equalityHelper
	 *            The equality helper to wire with the comparison model.
	 * @param equalityStrategy
	 *            The equality strategy to use for element matching.
	 * @param ignoreStrategy
	 *            The strategy to use to ignore elements.
	 * @param resourceMatcher
	 *            The resource matcher to identify matching resources.
	 */
	public HierarchicalMatchEngineFactory(IEqualityHelper equalityHelper,
			EqualityStrategy equalityStrategy, IgnoreStrategy ignoreStrategy,
			IResourceMatcher resourceMatcher) {
		this.equalityHelper = equalityHelper;
		this.equalityStrategy = equalityStrategy;
		this.ignoreStrategy = ignoreStrategy;
		this.resourceMatcher = resourceMatcher;
	}

	@Override
	public boolean isMatchEngineFactoryFor(IComparisonScope scope) {
		return true;
	}

	@Override
	public IMatchEngine getMatchEngine() {
		return new HierarchicalMatchEngine(equalityHelper, equalityStrategy,
				ignoreStrategy, resourceMatcher);
	}

}
