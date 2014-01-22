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
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Statement;
import org.splevo.diffing.postprocessor.ComparisonModelCleanUp;
import org.splevo.jamopp.diffing.diff.JaMoPPChangeFactory;

/**
 * A JaMoPP specific post processor to refine the comparison model.
 *
 * <h2>Post Diff Processing</h2>
 * <p>
 * After the diff process has been finished, too detailed differences are detected and refined diffs
 * for the next uper level of more coarse grained diffs are created. For example, if a diff is for
 * an expression, the parent matches are walked up to find an appropriate AST element (e.g. a
 * FieldDeclaration). A {@link FieldChange} will be created and marked as refined by the original
 * diff.
 * </p>
 */
public class JaMoPPPostProcessor implements IPostProcessor {

    /** The class logger to be used. */
    private Logger logger = Logger.getLogger(JaMoPPPostProcessor.class);

    /** The factory to create custom changes. */
    private JaMoPPChangeFactory customChangeFactory = new JaMoPPChangeFactory();

    /** Cache for already refined higher level Diffs */
    private Map<EObject, Diff> refinedDiffCache = new LinkedHashMap<EObject, Diff>();

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

        // refine to detailed diffs
        for (Diff diff : comparison.getDifferences()) {
            Diff refinedDiff = refineToDetailedDiff(diff);
            if (refinedDiff != null) {
                Match match = diff.getMatch();
                match.getDifferences().remove(diff);
                logger.info("PostDiff: Refined to detailed Diff: " + diff);
            }
        }

        // clean up nested statement diffs
        Set<Diff> diffsToRemove = new HashSet<Diff>();
        for (Diff diff : comparison.getDifferences()) {
            if (diff.getMatch().getLeft() instanceof Statement || diff.getMatch().getRight() instanceof Statement) {
                for (Diff subDiff : diff.getMatch().getAllDifferences()) {
                    if (!diff.getMatch().getDifferences().contains(subDiff)) {
                        if (subDiff.getMatch() == null) {
                            logger.debug("subDiff without match: " + subDiff);
                        }
                        diffsToRemove.add(subDiff);
                    }
                }
            }
        }

        for (Diff diff : diffsToRemove) {
            diff.getMatch().getDifferences().remove(diff);
        }

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
     * {@inheritDoc}
     */
    @Override
    public void postComparison(Comparison comparison, Monitor monitor) {

        ComparisonModelCleanUp.cleanMatches(comparison.getMatches());
    }

    /**
     * Get the parent match of a match.
     *
     * @param match
     *            The match to get the parent match for.
     * @return If the container is null or not a match, return null.
     */
    private Match getParentMatch(Match match) {
        if (match.eContainer() instanceof Match) {
            return (Match) match.eContainer();
        }
        return null;
    }

}
