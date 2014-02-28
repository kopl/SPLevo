/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.jamopp.diffing.postprocessor;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.compare.AttributeChange;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.compare.ReferenceChange;
import org.eclipse.emf.compare.postprocessor.IPostProcessor;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.types.TypeReference;
import org.splevo.diffing.postprocessor.ComparisonModelCleanUp;
import org.splevo.jamopp.diffing.diff.JaMoPPChangeFactory;
import org.splevo.jamopp.diffing.jamoppdiff.ClassChange;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * A JaMoPP specific post processor to refine the comparison model.
 *
 * <h2>Post Diff Processing</h2>
 * <p>
 * After the diff process has been finished, too detailed differences are detected and refined diffs
 * for the next uper level of more coarse grained diffs are created. For example, if a diff is for
 * an expression, the parent matches are walked up to find an appropriate AST element (e.g. a
 * FieldDeclaration). A FieldChange will be created and marked as refined by the original diff.
 * </p>
 */
public class JaMoPPPostProcessor implements IPostProcessor {

    /** The class logger to be used. */
    private Logger logger = Logger.getLogger(JaMoPPPostProcessor.class);

    /** The factory to create custom changes. */
    private JaMoPPChangeFactory customChangeFactory = new JaMoPPChangeFactory();

    /** Cache for already refined higher level Diffs */
    private Map<EObject, Diff> refinedDiffCache = new LinkedHashMap<EObject, Diff>();

    /** Options to configure the post processing. */
    private Map<String, Object> options = Maps.newHashMap();

    /**
     * Option to log informations about the found differences to the log file provided with this
     * option.
     *
     * If the option is null (default) no log files will be produced.
     */
    public static final String OPTION_DIFF_STATISTICS_LOG_DIR = "JaMoPP.Differ.Statistics.Log.Directory";

    /**
     * Option to clean up false positive differences based on derived copies. A derived copy means
     * the developer has not only copied code, but also introduced an "extends"-relationship between
     * the copy and the original. As a result, public, protected or package fields, present in the
     * original but not in the copy are not deleted but derived from the parent.
     *
     * If the option is null (default) no clean up will be performed. The configuration currently
     * supports only strings. So any not null and not empty string activates this option.
     */
    public static final String OPTION_DIFF_CLEANUP_DERIVED_COPIES = "JaMoPP.Differ.Derived.Copy.Cleanup";

    /** Default constructor setting the post processors default options. */
    public JaMoPPPostProcessor() {
        options.put(OPTION_DIFF_STATISTICS_LOG_DIR, null);
        options.put(OPTION_DIFF_CLEANUP_DERIVED_COPIES, null);
    }

    /**
     * Constructor to set specific options for the post processor.
     *
     * @param options
     *            The options to overwrite the default options.
     */
    public JaMoPPPostProcessor(Map<String, String> options) {
        this();
        if (options != null) {
            for (String key : options.keySet()) {
                this.options.put(key, options.get(key));
            }
        }
    }

    /**
     * Handle one side matches corresponding to a change type.<br>
     * {@inheritDoc}
     */
    @Override
    public void postMatch(Comparison comparison, Monitor monitor) {

    }

    /**
     * Clean up the resulting diff model.
     * <ol>
     * <li>
     * Scan the differences and build refined Diff elements for to detailed ones. (e.g. Expressions
     * etc.)</li>
     * <li>
     * Remove nested diffs for statement changes. E.g. if an if statement has been changed, no
     * additional (nested) diffs must be specified inside this.<br>
     * If a more fine-grained level than statements is intended this needs to be adapted.</li>
     * </ol>
     *
     * {@inheritDoc}
     */
    @Override
    public void postDiff(Comparison comparison, Monitor monitor) {

        refineTooDetailedDiffs(comparison);
        cleanNestedDifferences(comparison);
    }

    /**
     * Differences should be detected on a minimum granularity. This minimum granularity is aligned
     * with is supported later on by the variability technique used.
     *
     * The difference analysis done before should not have produced any more fine grained
     * differences. However, to ensure a valid model, this is rechecked here. If such a too fine
     * grained difference is detected, a log is produce to improve the difference done before.
     *
     * @param comparison
     *            The comparison model to clean up.
     */
    private void refineTooDetailedDiffs(Comparison comparison) {
        for (Diff diff : comparison.getDifferences()) {
            Diff refinedDiff = refineToDetailedDiff(diff);
            if (refinedDiff != null) {
                Match match = diff.getMatch();
                match.getDifferences().remove(diff);
                logger.info("PostDiff: Refined to detailed Diff: " + diff);
            }
        }
    }

    /**
     * Remove nested diffs from the model. No nested variation points are supported yet. So we are
     * not able to handle nested differences in the downstream process.
     *
     * @param comparison
     *            The comparison model to clean up.
     */
    private void cleanNestedDifferences(Comparison comparison) {
        Set<Diff> diffsToRemove = new HashSet<Diff>();
        for (Diff diff : comparison.getDifferences()) {

            Match parentMatch = diff.getMatch();
            parentMatch = getParentMatch(parentMatch);
            while (parentMatch != null) {

                if (parentMatch.getDifferences().size() > 0) {
                    EObject left = parentMatch.getLeft();
                    EObject right = parentMatch.getRight();
                    if ((left instanceof Statement || right instanceof Statement) && noClassElement(left, right)) {
                        diffsToRemove.add(diff);
                        break;
                    }
                }

                parentMatch = getParentMatch(parentMatch);
            }
        }

        for (Diff diff : diffsToRemove) {
            diff.getMatch().getDifferences().remove(diff);
        }
    }

    /**
     * Check that non of the provided elements is a class element.
     *
     * @param left
     *            The first element to check.
     * @param right
     *            The second element to check.
     * @return True if none of them is a class element.
     */
    private boolean noClassElement(EObject left, EObject right) {
        return !(isClassElement(left) || isClassElement(right));
    }

    /**
     * Check if an object is an instance of a JaMoPP Class
     *
     * @param object
     *            The object to check.
     * @return True if it is a class.
     */
    private boolean isClassElement(EObject object) {
        return object instanceof org.emftext.language.java.classifiers.Class;
    }

    /**
     * Detect and refine to detailed diffs.
     *
     * @param diff
     *            The diff to check and refine if reasonable.
     * @return The refined diff, if one has been created.
     */
    private Diff refineToDetailedDiff(Diff diff) {
        if (diff instanceof ReferenceChange) {
            return refineToDetailedReferenceChange((ReferenceChange) diff);
        } else if (diff instanceof AttributeChange) {
            return refineToDetailedAttributeChange((AttributeChange) diff);
        }

        return null;
    }

    /**
     * Detect and refine to detailed {@link ReferenceChange}.
     *
     * @param referenceChange
     *            The {@link ReferenceChange} to check and refine if reasonable.
     * @return The refined diff, if one has been created.
     */
    private Diff refineToDetailedReferenceChange(ReferenceChange referenceChange) {
        EObject changedValue = referenceChange.getValue();

        if (changedValue instanceof Expression) {
            Match parentMatch = referenceChange.getMatch();
            Diff refinedDiff = null;
            while (refinedDiff == null && parentMatch != null) {

                EObject parentElement = null;
                if (parentMatch.getRight() != null) {
                    parentElement = parentMatch.getRight();
                } else {
                    parentElement = parentMatch.getLeft();
                }

                if (refinedDiffCache.containsKey(parentElement)) {
                    refinedDiff = refinedDiffCache.get(parentElement);
                    refinedDiff.getRefinedBy().add(referenceChange);
                    return refinedDiff;
                }

                refinedDiff = customChangeFactory.doSwitch(parentElement);
                if (refinedDiff != null) {
                    Match nextParentMatch = getParentMatch(parentMatch);
                    refinedDiff.setKind(DifferenceKind.CHANGE);
                    refinedDiff.setMatch(nextParentMatch);
                    refinedDiff.getRefinedBy().add(referenceChange);
                    nextParentMatch.getDifferences().add(refinedDiff);
                    refinedDiffCache.put(parentElement, refinedDiff);
                    return refinedDiff;
                }

                parentMatch = getParentMatch(parentMatch);
            }
        }

        return null;
    }

    /**
     * Detect and refine to detailed {@link AttributeChange}.
     *
     * @param attributeChange
     *            The {@link AttributeChange} to check and refine if reasonable.
     * @return The refined diff, if one has been created.
     */
    private Diff refineToDetailedAttributeChange(AttributeChange attributeChange) {
        logger.warn("Unhandled AttributeChange (" + attributeChange + ")");
        return null;
    }

    @Override
    public void postRequirements(Comparison comparison, Monitor monitor) {
    }

    @Override
    public void postEquivalences(Comparison comparison, Monitor monitor) {
    }

    @Override
    public void postConflicts(Comparison comparison, Monitor monitor) {
    }

    /**
     * The comparison represents the original models hierarchies with match elements. While only
     * those subtrees containing diff elements are relevant for the downstream process, the model is
     * larger then needed. This post processor step removes all match element (subtrees) that do not
     * contain any diff element.<br>
     *
     * If the {@link OPTION_DIFF_STATISTICS_LOG_DIR} option is set, statistics about the Diff result
     * will be logged.
     *
     * {@inheritDoc}
     */
    @Override
    public void postComparison(Comparison comparison, Monitor monitor) {

        Object logDir = options.get(OPTION_DIFF_STATISTICS_LOG_DIR);
        if (logDir != null && logDir instanceof String) {
            String logDirPath = (String) logDir;
            if (!logDirPath.trim().isEmpty()) {
                DifferenceStatisticLogger.log(comparison, (String) logDir);
            }
        }

        if (isCleanUpDerivedCopiesActivated()) {
            cleanUpDerivedCopies(comparison);
        }

        ComparisonModelCleanUp.cleanMatches(comparison.getMatches());
    }

    /**
     * Check if the option to clean up derived copies is activated in the diffing options.
     *
     * @return True if derived copies should be detected and cleaned up.
     */
    private boolean isCleanUpDerivedCopiesActivated() {
        boolean cleanUpDerivedCopiesActive = false;
        Object cleanUpDerivedCopies = options.get(OPTION_DIFF_CLEANUP_DERIVED_COPIES);
        if (cleanUpDerivedCopies != null && cleanUpDerivedCopies instanceof String) {
            String cleanUpDerivedCopiesString = (String) cleanUpDerivedCopies;
            if (!cleanUpDerivedCopiesString.trim().isEmpty()) {
                cleanUpDerivedCopiesActive = true;
            }
        }
        return cleanUpDerivedCopiesActive;
    }

    /**
     * Clean up false positive deletes of any members that are available in the copy due to an
     * existing extends relationship between left and right models.
     *
     * @param comparison
     *            The comparison model to clean up.
     */
    private void cleanUpDerivedCopies(Comparison comparison) {

        List<Diff> falsePositivesToRemove = Lists.newArrayList();

        // CLASS CHANGES
        // find class changes about changed extends / default extends
        // Identify the super and the sub class
        // ignore deleted references of the super class
        // ignore added references similar to the sub class
        for (Diff diff : comparison.getDifferences()) {

            if (diff instanceof ClassChange) {

                ClassChange change = (ClassChange) diff;

                boolean derivedCopyDetected = false;

                derivedCopyDetected = checkDerivedCopyPattern(comparison, change);

                if (derivedCopyDetected) {
                    falsePositivesToRemove.add(diff);
                }
            }
        }

        for (Diff diff : falsePositivesToRemove) {
            diff.getMatch().getDifferences().remove(diff);
        }

    }

    /**
     * Check a {@link ClassChange} if it is part of a derived copy pattern.
     *
     * Check if the extended class matches to the same class as the derived one. This is an
     * indicator for a derived copy pattern.
     *
     * Only ClassChanges of type CHANGE are considered. DELETE and ADD are not about
     * changed class signatures (i.e. extends).
     *
     * If the class does not extend any specific class but only the default object class, this does
     * not identify a pattern match and false will be returned.
     *
     * @param comparison
     *            The comparison model to look up matches.
     * @param change
     *            The class change to analyze.
     * @return True if a derived copy pattern is detected.
     */
    private boolean checkDerivedCopyPattern(Comparison comparison, ClassChange change) {

        if (!(change.getMatch().getRight() instanceof Class)) {
            return false;
        }

        Class originalClass = (Class) change.getMatch().getRight();
        Class changedClass = change.getChangedClass();
        TypeReference classExtends = changedClass.getExtends();

        if (classExtends == null) {
            return false;
        }

        Match extendedClassMatch = comparison.getMatch(classExtends.getTarget());
        if (extendedClassMatch != null) {
            if (extendedClassMatch.getRight() == originalClass) {
                logger.debug("Derived Copy Detected: " + originalClass.getName());
                return true;
            }
        }
        return false;
    }

    /**
     * Get the parent match of a match.
     *
     * @param match
     *            The match to get the parent match for.
     * @return If the container is null or not a match, return null.
     */
    private Match getParentMatch(Match match) {
        if (match != null && match.eContainer() instanceof Match) {
            return (Match) match.eContainer();
        }
        return null;
    }

}
