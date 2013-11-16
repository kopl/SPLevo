package org.splevo.modisco.java.diffing.match;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.compare.CompareFactory;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.compare.MatchResource;
import org.eclipse.emf.compare.match.IMatchEngine;
import org.eclipse.emf.compare.match.resource.IResourceMatcher;
import org.eclipse.emf.compare.match.resource.StrategyResourceMatcher;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.compare.utils.IEqualityHelper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import com.google.common.collect.Iterators;

/**
 * The default match engine does not take the hierarchical structure of the model into account. As
 * the location of model elements is essential for our match strategies and moved elements are
 * treated as separate ADD and DELETE, this match engine implements a "level by level" process.
 * <p>
 * <b>Two-Way Comparison only</b><br>
 * This match engine does not support three-way matches. So the origin part of the scop is ignored
 * at all.
 * </p>
 */
public class HierarchicalMatchEngine implements IMatchEngine {

    /** The class logger to use. */
    private static Logger logger = Logger.getLogger(HierarchicalMatchEngine.class);

    /** The equality helper for the model. */
    private IEqualityHelper equalityHelper = null;

    /** The strategy to be used to check for equality. */
    private EqualityStrategy equalityStrategy = null;

    /** The strategy to decide which elements to ignore. */
    private IgnoreStrategy ignoreStrategy = null;

    /**
     * Constructor to set the required dependencies.
     * 
     * @param equalityHelper
     *            The equality helper to check equality and to be wired with the comparison model.
     * @param equalityStrategy
     *            The equality strategy to use.
     * @param ignoreStrategy
     *            the strategy which elements must not be matched and can be ignored.
     */
    public HierarchicalMatchEngine(IEqualityHelper equalityHelper, EqualityStrategy equalityStrategy,
            IgnoreStrategy ignoreStrategy) {
        this.equalityHelper = equalityHelper;
        this.equalityStrategy = equalityStrategy;
        this.ignoreStrategy = ignoreStrategy;
    }

    @Override
    public Comparison match(IComparisonScope scope, Monitor monitor) {

        final Notifier left = scope.getLeft();
        final Notifier right = scope.getRight();
        
        Comparison comparison = createComparison();
        comparison.setThreeWay(false);

        match(comparison, scope, left, right, monitor);

        return comparison;
    }

    /**
     * This methods will delegate to the proper "match(T, T, T)" implementation according to the
     * types of {@code left}, {@code right} and {@code origin}.
     * 
     * @param comparison
     *            The comparison to which will be added detected matches.
     * @param scope
     *            The comparison scope that should be used by this engine to determine the objects
     *            to match.
     * @param left
     *            The left {@link Notifier}.
     * @param right
     *            The right {@link Notifier}.
     * @param monitor
     *            The monitor to report progress or to check for cancellation
     */
    protected void match(Comparison comparison, IComparisonScope scope, final Notifier left, final Notifier right,
            Monitor monitor) {
        if (left instanceof ResourceSet || right instanceof ResourceSet) {
            match(comparison, scope, (ResourceSet) left, (ResourceSet) right, monitor);
        } else if (left instanceof Resource || right instanceof Resource) {
            match(comparison, scope, (Resource) left, (Resource) right, monitor);
        } else if (left instanceof EObject || right instanceof EObject) {
            match(comparison, scope, (EObject) left, (EObject) right, monitor);
        } else {
            throw new IllegalArgumentException("Unhandled type of elements to match. (" + left + ", " + right + ")");
        }
    }

    /**
     * This will be used to match the given {@link ResourceSet}s. This default implementation will
     * query the comparison scope for these resource sets children, then delegate to an
     * {@link IResourceMatcher} to determine the resource mappings.
     * 
     * The content of the matched resources is then pushed into the resource specific match method.<br>
     * 
     * <b>Note:</b><br>
     * In contrast to the DefaultMatchEngine, the match for eObjects recursively iterates through
     * sub elements of the models. As a result, only the root elements of the resources are matched
     * and not the plain list of all all contained elements.
     * 
     * @param comparison
     *            The comparison to which will be added detected matches.
     * @param scope
     *            The comparison scope that should be used by this engine to determine the objects
     *            to match.
     * @param left
     *            The left {@link ResourceSet}.
     * @param right
     *            The right {@link ResourceSet}.
     * @param monitor
     *            The monitor to report progress or to check for cancellation
     */
    protected void match(Comparison comparison, IComparisonScope scope, ResourceSet left, ResourceSet right, Monitor monitor) {
        final Iterator<? extends Resource> leftChildren = scope.getCoveredResources(left);
        final Iterator<? extends Resource> rightChildren = scope.getCoveredResources(right);
        final Iterator<? extends Resource> originChildren = Iterators.emptyIterator();

        final IResourceMatcher resourceMatcher = createResourceMatcher();
        final Iterable<MatchResource> mappings = resourceMatcher.createMappings(leftChildren, rightChildren,
                originChildren);

        for (MatchResource mapping : mappings) {
            comparison.getMatchedResources().add(mapping);

            final Resource leftRes = mapping.getLeft();
            final Resource rightRes = mapping.getRight();

            match(comparison, leftRes, rightRes, monitor);

        }
    }

    /**
     * Build the matches for two given resources. The direct sub elements will be detected and
     * pushed into a match process for sub elements.
     * 
     * @param comparison
     *            The comparison model to feed.
     * @param leftRes
     *            The changed resource.
     * @param rightRes
     *            The original resource.
     * @param monitor
     *            The progress monitor.
     */
    protected void match(Comparison comparison, final Resource leftRes, final Resource rightRes, Monitor monitor) {

        if (leftRes == null || rightRes == null) {
            logger.warn("Null resource provided vor comparison (Left: " + leftRes + ", Right: " + rightRes + ")");
            return;
        }

        List<EObject> leftElements = leftRes.getContents();
        List<EObject> rightElements = rightRes.getContents();

        List<Match> matches = match(comparison, leftElements, rightElements, monitor);
        comparison.getMatches().addAll(matches);
    }

    /**
     * Create matches for the provided elements and trigger a match process for the child elements
     * in case of a match.
     * 
     * @param comparison
     *            The comparison to fill up.
     * @param leftElements
     *            The left elements to find matches for.
     * @param rightElements
     *            The right elements to find matches for.
     * @param monitor
     *            The monitor to track the progress.
     * @return The list of created matches.
     */
    private List<Match> match(Comparison comparison, List<EObject> leftElements, List<EObject> rightElements,
            Monitor monitor) {

        List<Match> matches = new ArrayList<Match>();

        List<EObject> leftElementsInScope = filterIgnoredElements(leftElements);
        List<EObject> rightElementsInScope = filterIgnoredElements(rightElements);

        for (EObject leftElement : leftElementsInScope) {

            Match match = CompareFactory.eINSTANCE.createMatch();
            match.setLeft(leftElement);

            for (EObject rightElement : rightElementsInScope) {
                if (equalityStrategy.areEqual(leftElement, rightElement)) {
                    match.setRight(rightElement);
                    rightElementsInScope.remove(rightElement);
                    List<Match> subMatches = match(comparison, leftElement.eContents(), rightElement.eContents(), monitor);
                    match.getSubmatches().addAll(subMatches);
                    break;
                }
            }
            
            matches.add(match);
        }

        for (EObject rightElement : rightElementsInScope) {
            Match match = CompareFactory.eINSTANCE.createMatch();
            match.setRight(rightElement);
            matches.add(match);
        }

        return matches;
    }

    /**
     * Get a filtered list of elements without any elements that are not in scope.
     * 
     * @param leftElements
     *            The list to filter.
     * @return The filtered list.
     */
    private List<EObject> filterIgnoredElements(List<EObject> leftElements) {
        List<EObject> filteredList = new ArrayList<EObject>();

        for (EObject object : leftElements) {
            if (!ignoreStrategy.ignore(object)) {
                filteredList.add(object);
            }
        }

        return filteredList;
    }

    /**
     * This will be used to create the resource matcher that will be used by this match engine.
     * 
     * @return An {@link IResourceMatcher} that can be used to retrieve the {@link MatchResource}s
     *         for this comparison.
     */
    protected IResourceMatcher createResourceMatcher() {
        return new StrategyResourceMatcher();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.compare.match.IComparisonFactory#createComparison()
     */
    private Comparison createComparison() {
        Comparison comparison = CompareFactory.eINSTANCE.createComparison();

        comparison.eAdapters().add(equalityHelper);
        equalityHelper.setTarget(comparison);

        return comparison;
    }

    /**
     * A strategy function to check if two elements are equal.<br>
     * The strategy can assume that the compared elements are in the same model hierarchy.
     */
    public interface EqualityStrategy {

        /**
         * Check if two elements are equal. They are located at a matching position. So container
         * similarity can be assumed.
         * 
         * @param left
         *            The element of the left model.
         * @param right
         *            The element within the right model.
         * @return True if they can be assumed as equal, false if not.
         */
        public boolean areEqual(EObject left, EObject right);
    }

    /**
     * A strategy function to check if an element should be ignored from matching.
     */
    public interface IgnoreStrategy {

        /**
         * Check if an element should be ignored during the matching
         * 
         * @param element
         *            The element to check.
         * @return True/False whether it should be ignored.
         */
        public boolean ignore(EObject element);

    }

}
