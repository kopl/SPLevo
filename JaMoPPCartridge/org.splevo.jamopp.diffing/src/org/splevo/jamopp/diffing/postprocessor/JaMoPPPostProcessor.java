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

import java.io.File;
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
import org.splevo.jamopp.diffing.jamoppdiff.StatementChange;
import org.splevo.jamopp.util.JaMoPPElementUtil;

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

    /**
     * Option to ignore import deletes which are not really deleted but no real edit operation as
     * the copy still extends the original class.
     */
    public static final String OPTION_DIFF_CLEANUP_DERIVED_COPIES_CLEAN_IMPORTS = "JaMoPP.Differ.Derived.Copy.Cleanup.Imports";

    /**
     * Option to ignore field deletes which are not really deleted but no real edit operation as the
     * copy still extends the original class.
     */
    public static final String OPTION_DIFF_CLEANUP_DERIVED_COPIES_CLEAN_FIELDS = "JaMoPP.Differ.Derived.Copy.Cleanup.Fields";

    /**
     * Option to ignore method (and constructor) deletes which are not really deleted but no real
     * edit operation as the copy still extends the original class.
     */
    public static final String OPTION_DIFF_CLEANUP_DERIVED_COPIES_CLEAN_METHODS = "JaMoPP.Differ.Derived.Copy.Cleanup.Methods";

    /** Default constructor setting the post processors default options. */
    public JaMoPPPostProcessor() {
        options.put(OPTION_DIFF_STATISTICS_LOG_DIR, null);
        options.put(OPTION_DIFF_CLEANUP_DERIVED_COPIES, null);
        options.put(OPTION_DIFF_CLEANUP_DERIVED_COPIES_CLEAN_IMPORTS, "true");
        options.put(OPTION_DIFF_CLEANUP_DERIVED_COPIES_CLEAN_FIELDS, "true");
        options.put(OPTION_DIFF_CLEANUP_DERIVED_COPIES_CLEAN_METHODS, "true");
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

            Match parentMatch = getParentMatch(diff.getMatch());
            while (parentMatch != null) {

                if (parentMatch.getDifferences().size() > 0 && atLeastOneDiffIsStatement(parentMatch)
                        && oneIsParent(parentMatch, diff) && noClassElement(parentMatch)) {
                    diffsToRemove.add(diff);
                    break;
                }

                parentMatch = getParentMatch(parentMatch);
            }
        }

        for (Diff diff : diffsToRemove) {
            diff.getMatch().getDifferences().remove(diff);
        }
    }

    private boolean oneIsParent(Match parentMatch, Diff diff) {

        if (!(diff instanceof StatementChange)) {
            return false;
        }
        Statement childStmt = ((StatementChange) diff).getChangedStatement();

        for (Diff parentDiff : parentMatch.getDifferences()) {
            if (parentDiff instanceof StatementChange) {

                Statement parentStmt = ((StatementChange) parentDiff).getChangedStatement();
                if (JaMoPPElementUtil.isParentOf(parentStmt, childStmt)) {
                    return true;
                }

                // test opposite statement to support ADDs and DELETEs
                if (parentMatch.getRight() == parentStmt) {
                    if (JaMoPPElementUtil.isParentOf((Statement) parentMatch.getLeft(), childStmt)) {
                        return true;
                    }
                } else if (parentMatch.getLeft() == parentStmt) {
                    if (JaMoPPElementUtil.isParentOf((Statement) parentMatch.getRight(), childStmt)) {
                        return true;
                    }
                }

            }
        }

        return false;
    }

    private boolean atLeastOneDiffIsStatement(Match parentMatch) {
        return parentMatch.getLeft() instanceof Statement || parentMatch.getRight() instanceof Statement;
    }

    /**
     * Check that none of the provided match's left or right elements is a class element.
     *
     * @param match
     *            The match element to check left and right for.
     * @return True if none of them is a class element.
     */
    private boolean noClassElement(Match parentMatch) {
        EObject left = parentMatch.getLeft();
        EObject right = parentMatch.getRight();
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
                DifferenceStatisticLogger.log(comparison, (String) logDir + File.separator + "postcomparison");
            }
        }

        if (isCleanUpDerivedCopiesActivated()) {

            boolean cleanImports = isOptionNotNull(OPTION_DIFF_CLEANUP_DERIVED_COPIES_CLEAN_IMPORTS);
            boolean cleanFields = isOptionNotNull(OPTION_DIFF_CLEANUP_DERIVED_COPIES_CLEAN_FIELDS);
            boolean cleanMethods = isOptionNotNull(OPTION_DIFF_CLEANUP_DERIVED_COPIES_CLEAN_METHODS);

            DerivedCopyFilter filter = new DerivedCopyFilter(cleanImports, cleanFields, cleanMethods);
            filter.cleanUpDerivedCopies(comparison);

            if (logDir != null && logDir instanceof String) {
                String logDirPath = (String) logDir;
                if (!logDirPath.trim().isEmpty()) {
                    DifferenceStatisticLogger
                            .log(comparison, ((String) logDir) + File.separator + "after-derived-copy");
                }
            }

        }

        ComparisonModelCleanUp.cleanMatches(comparison.getMatches());
    }

    /**
     * Check if the option to clean up derived copies is activated in the diffing options.
     *
     * @return True if derived copies should be detected and cleaned up.
     */
    private boolean isCleanUpDerivedCopiesActivated() {
        return isOptionNotNull(OPTION_DIFF_CLEANUP_DERIVED_COPIES);
    }

    /**
     * Check if an option is set and not null.
     *
     * @param optionKey
     *            The key of the option to check.
     * @return True if the option is set and not null.
     */
    private boolean isOptionNotNull(String optionKey) {
        boolean cleanUpDerivedImportsActive = false;
        Object cleanUpDerivedCopies = options.get(optionKey);
        if (cleanUpDerivedCopies != null && cleanUpDerivedCopies instanceof String) {
            String cleanUpDerivedCopiesString = (String) cleanUpDerivedCopies;
            if (!cleanUpDerivedCopiesString.trim().isEmpty()) {
                cleanUpDerivedImportsActive = true;
            }
        }
        return cleanUpDerivedImportsActive;
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
