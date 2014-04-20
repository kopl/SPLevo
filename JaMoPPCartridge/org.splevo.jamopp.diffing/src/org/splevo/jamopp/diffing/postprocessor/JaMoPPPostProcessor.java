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
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.types.TypeReference;
import org.splevo.diffing.postprocessor.ComparisonModelCleanUp;
import org.splevo.jamopp.diffing.diff.JaMoPPChangeFactory;
import org.splevo.jamopp.diffing.jamoppdiff.ClassChange;
import org.splevo.jamopp.diffing.jamoppdiff.ImportChange;
import org.splevo.jamopp.diffing.jamoppdiff.StatementChange;
import org.splevo.jamopp.util.JaMoPPElementUtil;

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
            cleanUpDerivedCopies(comparison);

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
     * <p>
     * Clean up false positive deletes of any members that are available in the copy due to an
     * existing extends relationship between left and right models.
     * </p>
     *
     * <p>
     * When a derived copy is detected, the following clean up strategies are applied:
     * <ul>
     * <li>Remove the class change as it is about the extends relationship</li>
     * <li>Remove import deletes as the sub class does not require any imports used for the super
     * class implementation only.</li>
     * <li>Constructors doing super call only (NOT YET IMPLEMENTED)</li>
     * </ul>
     * </p>
     *
     * @param comparison
     *            The comparison model to clean up.
     */
    private void cleanUpDerivedCopies(Comparison comparison) {

        List<Diff> falsePositivesToRemove = Lists.newArrayList();

        int counterClasses = 0;
        int counterImports = 0;

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
                    falsePositivesToRemove.add(change);
                    counterClasses++;

                    List<ImportChange> importsToIgnore = identifyParentImportDeletes(change);
                    falsePositivesToRemove.addAll(importsToIgnore);
                    counterImports += importsToIgnore.size();

                }
            }
        }

        logger.debug(String.format("Derived Copy Cleanup: Classes: %s, Imports: %s", counterClasses, counterImports));

        for (Diff diff : falsePositivesToRemove) {
            diff.getMatch().getDifferences().remove(diff);
        }

    }

    /**
     * <p>
     * In case of a derived copy, the imports used in the parent only must not be present in the sub
     * class and such false positives can be ignored.
     * </p>
     *
     * <p>
     * To detect them, the parent match identifying the compilation unit containing the changed
     * class's container is searched, and all differences at this location which are
     * {@link ImportChange}s with {@link DifferenceKind#DELETE} are returned as ignorable.
     * </p>
     *
     * @param change
     *            The change to get the enclosing compilation unit change for.
     * @return The list of import deletes to ignore.
     */
    private List<ImportChange> identifyParentImportDeletes(ClassChange change) {

        Match cuMatch = findCompilationUnitParentMatch(change);

        if (cuMatch != null) {
            return getImportDeleteDiffs(cuMatch);
        } else {
            return Lists.newArrayList();
        }
    }

    /**
     * Detect the import deletes that can be ignored.
     *
     * Due to the derived copy match, the original CompilationUnit is matched twice to it's
     * counterpart in the copy and to the derived CompilationUnit in the copy.
     *
     * <table>
     * <tr>
     * <th>Original</th>
     * <th>Copy</th>
     * </tr>
     * <tr>
     * <td>BaseClass.java</td>
     * <td>BaseClass.java</td>
     * </tr>
     * <tr>
     * <td>BaseClass.java</td>
     * <td>BaseClassCustom.java</td>
     * </tr>
     * </table>
     * <p>
     * The derived copy typically is a sub match of the match between the BaseClass and the
     * BaseClassCustom (in the example above). However, differences identified are typically
     * registered for the primary match of the original source. This seems to happen because the
     * comparison model returns a single match only when asked for a match of an EObject:
     * {@link Comparison#getMatch(EObject)}.
     * </p>
     * <p>
     * DesignDecision: Using primary compilation unit match.
     * </p>
     * <p>
     * To be able to access the import deletes, the comparison model is asked for the compilation
     * unit match instead of just using the one provided as parameter.
     * </p>
     *
     * @param cuMatch
     *            The compilation unit match to search import deletes for.
     * @return The list of import deletes
     */
    private List<ImportChange> getImportDeleteDiffs(Match cuMatch) {

        CompilationUnit originalCU = (CompilationUnit) cuMatch.getRight();
        Match primaryMatch = cuMatch.getComparison().getMatch(originalCU);

        List<ImportChange> ignoreImports = Lists.newArrayList();

        for (Diff diff : primaryMatch.getDifferences()) {
            if (diff instanceof ImportChange && diff.getKind() == DifferenceKind.DELETE) {
                ignoreImports.add((ImportChange) diff);
            }
        }
        return ignoreImports;
    }

    private Match findCompilationUnitParentMatch(ClassChange change) {
        Match cuMatch = change.getMatch();
        while (cuMatch != null && !(cuMatch.getRight() instanceof CompilationUnit)) {
            cuMatch = getParentMatch(cuMatch);
        }
        return cuMatch;
    }

    /**
     * Check a {@link ClassChange} if it is part of a derived copy pattern.
     *
     * Check if the extended class matches to the same class as the derived one. This is an
     * indicator for a derived copy pattern.
     *
     * Only ClassChanges of type CHANGE are considered. DELETE and ADD are not about changed class
     * signatures (i.e. extends).
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
