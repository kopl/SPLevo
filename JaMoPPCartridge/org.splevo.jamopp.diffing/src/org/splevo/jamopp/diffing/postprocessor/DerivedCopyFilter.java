/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.jamopp.diffing.postprocessor;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.emf.compare.Match;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.types.TypeReference;
import org.splevo.jamopp.diffing.jamoppdiff.ClassChange;
import org.splevo.jamopp.diffing.jamoppdiff.ConstructorChange;
import org.splevo.jamopp.diffing.jamoppdiff.FieldChange;
import org.splevo.jamopp.diffing.jamoppdiff.ImportChange;
import org.splevo.jamopp.diffing.jamoppdiff.MethodChange;

import com.google.common.collect.Lists;

/**
 * Filters for false positives resulting from derived copy instances and could be clearly identified
 * and ignored.
 */
public class DerivedCopyFilter {

    private static Logger logger = Logger.getLogger(DerivedCopyFilter.class);

    private boolean cleanImports = true;
    private boolean cleanFields = true;
    private boolean cleanMethods = true;

    /**
     * Constructor to configure the detailed behavior of elements to be filtered.
     *
     * @param cleanImports
     *            Flag to filter imports or not.
     * @param cleanFields
     *            Flag to filter fields or not.
     * @param cleanMethods
     *            Flag to filter methods or not.
     */
    public DerivedCopyFilter(boolean cleanImports, boolean cleanFields, boolean cleanMethods) {
        this.cleanImports = cleanImports;
        this.cleanFields = cleanFields;
        this.cleanMethods = cleanMethods;
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
    public void cleanUpDerivedCopies(Comparison comparison) {

        List<Diff> falsePositivesToRemove = Lists.newArrayList();

        int counterClasses = 0;
        int counterImports = 0;
        int counterMethods = 0;
        int counterFields = 0;

        int counterTotalImports = 0;
        int counterTotalMethods = 0;
        int counterTotalFields = 0;

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

                    if (cleanImports) {
                        List<ImportChange> importsToIgnore = identifyParentImportDeletes(change);
                        falsePositivesToRemove.addAll(importsToIgnore);
                        counterImports += importsToIgnore.size();
                    }

                    if (cleanFields) {
                        List<Diff> fieldsToIgnore = identifyParentFieldDeletes(change);
                        falsePositivesToRemove.addAll(fieldsToIgnore);
                        counterFields += fieldsToIgnore.size();
                    }

                    if (cleanMethods) {
                        List<Diff> methodsToIgnore = identifyParentMethodDeletes(change);
                        falsePositivesToRemove.addAll(methodsToIgnore);
                        counterMethods += methodsToIgnore.size();
                    }

                    counterTotalImports += countTotalImports(change);
                    counterTotalFields += countTotalFields(change);
                    counterTotalMethods += countTotalMethods(change);
                }
            }
        }

        logger.debug(String.format("Derived Copy Cleanup: Classes: %s, Imports: %s/%s, Fields: %s/%s, Methods: %s/%s",
                counterClasses, counterImports, counterTotalImports, counterFields, counterTotalFields, counterMethods,
                counterTotalMethods));

        for (Diff diff : falsePositivesToRemove) {
            diff.getMatch().getDifferences().remove(diff);
        }
    }

    private int countTotalMethods(ClassChange change) {
        Class originalClass = (Class) change.getMatch().getRight();
        int methodCount = originalClass.getMethods().size();
        int constructorCount = originalClass.getConstructors().size();
        return methodCount + constructorCount;
    }

    private int countTotalFields(ClassChange change) {
        Class originalClass = (Class) change.getMatch().getRight();
        return originalClass.getFields().size();
    }

    private int countTotalImports(ClassChange change) {
        Match cuMatch = findCompilationUnitParentMatch(change);
        if (cuMatch.getRight() != null) {
            CompilationUnit cu = (CompilationUnit) cuMatch.getRight();
            return cu.getImports().size();
        } else {
            return 0;
        }
    }

    private List<Diff> identifyParentMethodDeletes(ClassChange change) {

        Class copiedClass = (Class) change.getMatch().getLeft();
        List<Diff> changesToIgnore = Lists.newLinkedList();

        for (Diff diff : change.getMatch().getDifferences()) {

            if (diff.getKind() != DifferenceKind.DELETE) {
                continue;
            }

            if (diff instanceof MethodChange) {
                Method changedMethod = ((MethodChange) diff).getChangedMethod();
                if (changedMethod.isPublic() || changedMethod.isProtected()) {
                    changesToIgnore.add(diff);
                }
            }

            if (diff instanceof ConstructorChange) {
                Constructor origConstructor = ((ConstructorChange) diff).getChangedConstructor();
                if (hasEquivalentConstructor(copiedClass, origConstructor)) {
                    changesToIgnore.add(diff);
                    continue;
                }
            }
        }

        return changesToIgnore;
    }

    /**
     * Check all constructors of the copied class if there is one with a parameter combination
     * matching the combination of the original constructor.
     *
     * The constructors are tested one after each other if there is an indicator, that it is not
     * matching. If no false indicator matches, the constructor is an equivalent one and true is
     * returned. If all constructors are skipped due to matching indicators false is returned.
     *
     * @param copiedClass
     *            The class to check the constructors of.
     * @param origConstructor
     *            The reference constructor to test.
     * @return if there is an constructor with a similar parameter combination as the provided one.
     */
    private boolean hasEquivalentConstructor(Class copiedClass, Constructor origConstructor) {
        EList<Parameter> origParameters = origConstructor.getParameters();

        EList<Constructor> copyConstructors = copiedClass.getConstructors();
        if (origParameters.size() == 0 && copyConstructors.size() == 0) {
            return true;
        }

        for (Constructor copiedConstructor : copyConstructors) {
            EList<Parameter> copiedParameters = copiedConstructor.getParameters();

            if (copiedParameters.size() != origParameters.size()) {
                continue;
            }

            for (int i = 0; i < origParameters.size(); i++) {
                Parameter copiedParam = copiedParameters.get(i);
                Parameter origParam = origParameters.get(i);
                TypeReference copiedTypeRef = copiedParam.getTypeReference();
                TypeReference origTypeRef = origParam.getTypeReference();

                if (copiedTypeRef.getPureClassifierReference() != null
                        && origTypeRef.getPureClassifierReference() != null) {
                    Classifier copiedType = copiedTypeRef.getPureClassifierReference().getTarget();
                    Classifier origType = origTypeRef.getPureClassifierReference().getTarget();
                    if (!copiedType.getName().equals(origType.getName())) {
                        continue;
                    }
                } else {
                    if (origTypeRef.getTarget().eClass() != copiedTypeRef.getTarget().eClass()) {
                        continue;
                    }
                }
            }

            return true;
        }
        return false;
    }

    private List<Diff> identifyParentFieldDeletes(ClassChange change) {

        List<Diff> changesToIgnore = Lists.newLinkedList();

        for (Diff diff : change.getMatch().getDifferences()) {

            if (diff.getKind() != DifferenceKind.DELETE) {
                continue;
            }

            if (diff instanceof FieldChange) {
                Field changedField = ((FieldChange) diff).getChangedField();
                if (changedField.isPublic() || changedField.isProtected()) {
                    changesToIgnore.add(diff);
                }
            }
        }

        return changesToIgnore;
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
     * comparison model returns a single side match only when asked for a match of an EObject:
     * Comparison#getMatch(EObject).
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

        // TODO: Check Cleanup primary Match
        // as we no longer use duplicate matches for derived copies
        // we might be able to use the cuMatch directly
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

        // The extended class and the original class must be manually matched,
        // as they represent the same class in case of a derived copy, but result
        // from different source models. So their instances are not the same any
        Class superClass = (Class) classExtends.getPureClassifierReference().getTarget();
        if (!superClass.getQualifiedName().equals(originalClass.getQualifiedName())) {
            return false;
        }

        logger.debug("Derived Copy Detected: " + originalClass.getQualifiedName());

        return true;
    }

}
