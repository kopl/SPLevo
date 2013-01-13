package org.splevo.diffing.emfcompare.diff;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.FactoryException;
import org.eclipse.emf.compare.diff.engine.IMatchManager;
import org.eclipse.emf.compare.diff.engine.check.ReferencesCheck;
import org.eclipse.emf.compare.diff.metamodel.DiffFactory;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;
import org.eclipse.emf.compare.diff.metamodel.ReferenceChangeLeftTarget;
import org.eclipse.emf.compare.diff.metamodel.ReferenceChangeRightTarget;
import org.eclipse.emf.compare.diff.metamodel.UpdateReference;
import org.eclipse.emf.compare.match.internal.statistic.ResourceSimilarity;
import org.eclipse.emf.compare.match.metamodel.Match2Elements;
import org.eclipse.emf.compare.util.EFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.ClassInstanceCreation;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.MethodInvocation;
import org.eclipse.gmt.modisco.java.NamedElement;
import org.eclipse.gmt.modisco.java.SingleVariableAccess;
import org.eclipse.gmt.modisco.java.TagElement;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VariableDeclaration;
import org.eclipse.gmt.modisco.java.emf.impl.ImportDeclarationImpl;
import org.splevo.diffing.emfcompare.similarity.SimilarityChecker;
import org.splevo.diffing.emfcompare.util.PackageIgnoreChecker;

/**
 * A java model specific reference check to interpret model references which can be ignored.
 * 
 * 
 * Note: This adapts only the 2-way diffing process. 3-way diffing is not in the focus of our
 * diffing.
 * 
 */
@SuppressWarnings("restriction")
public class JavaModelReferenceCheck extends ReferencesCheck {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(JavaModelReferenceCheck.class);

    /** The package ignore visitor instance to be used. */
    private PackageIgnoreChecker packageIgnoreVisitor = null;

    /** The similarity checker. */
    private SimilarityChecker similarityChecker = new SimilarityChecker();

    /**
     * Constructor requiring a match manager to access required match objects.
     * 
     * @param manager
     *            The match manager to use.
     * 
     * @param packageIgnoreChecker
     *            The checker to prove for packages to ignore.
     */
    public JavaModelReferenceCheck(IMatchManager manager, PackageIgnoreChecker packageIgnoreChecker) {
        super(manager);
        this.packageIgnoreVisitor = packageIgnoreChecker;
    }

    /**
     * Check if a reference should be ignored. This method is an overloaded version of the
     * shouldBeIgnored(EReference) to also interpret the referencing element.
     * 
     * @param reference
     *            The reference to check.
     * @param mapping
     *            The mapping under study.
     * @return true/false whether to ignore the reference or not.
     */
    protected boolean shouldBeIgnored(EReference reference, Match2Elements mapping) {

        EObject referencingElementLeft = mapping.getLeftElement();
        EObject referencingElementRight = mapping.getRightElement();

        // ************************************
        // Ignore Backlinks in bi-directional references
        // if a class is used in an import or by another type access
        // this is represented by a bi-directional reference.
        // this bi-derectional reference should be ignored for the "usagesIn.." side
        // ************************************
        if ("usagesInTypeAccess".equals(reference.getName())) {
            return true;
        } else if ("usagesInImports".equals(reference.getName())) {
            return true;
        } else if ("usagesInPackageAccess".equals(reference.getName())) {
            return true;
        } else if ("usageInVariableAccess".equals(reference.getName())) {
            return true;
        } else if ("usages".equals(reference.getName())) {
            return true;
        } else if ("usagesInDocComments".equals(reference.getName())) {
            return true;
        } 
        
        if ("originalCompilationUnit".equals(reference.getName())) {
            return true;
        } 
        
        
                
        // the redifinition relationship is not of interest
        // because only the new defined element itself is detected
        // the reference itself has not to be detected separately
        if ("redefinitions".equals(reference.getName())) {
            return true;
        }

        // ************************************
        // ignore references to comments while
        // changes to comments are ignored at all
        // ************************************
        if ("commentList".equals(reference.getName())) {
            return true;
        }
        if ("comments".equals(reference.getName())) {
            return true;
        }

        /* ***********************************************************
         * Ignore references from elements in ignored packages This is done after the reference name
         * based filtering because it requires more resources than simply checking the reference
         * name
         * 
         * We cannot simply ignore all type references because a replaced import in a class will
         * occur only as a changed type reference of the TypeAccess in the ImportDeclaration. The
         * ImportDeclaration itself will not change from a model perspective.
         * ************************************************************
         */
        if ("type".equals(reference.getName())) {
            Boolean result = checkTypeReference(referencingElementLeft, referencingElementRight);
            if (result != null) {
                return result.booleanValue();
            }
        }
        // types referenced in a compilation unit
        if ("types".equals(reference.getName())) {
            Boolean result = checkTypesReference(referencingElementLeft, referencingElementRight);
            if (result != null) {
                return result.booleanValue();
            }
        }
        if ("bodyDeclarations".equals(reference.getName())) {
            Boolean ignore = packageIgnoreVisitor.isInIgnorePackage(referencingElementLeft);
            if (Boolean.TRUE.equals(ignore)) {
                return true;
            }
        }
        if ("importedElement".equals(reference.getName())) {
            Boolean result = checkImportedElementReference(referencingElementLeft, referencingElementRight);
            if (result != null) {
                return result.booleanValue();
            }
        }
        if ("method".equals(reference.getName())) {
            Boolean result = checkMethodReference(referencingElementLeft, referencingElementRight);
            if (result != null) {
                return result.booleanValue();
            }
        }
        if ("variable".equals(reference.getName())) {
            Boolean result = checkVariableReference(referencingElementLeft, referencingElementRight);
            if (result != null) {
                return result.booleanValue();
            }
        }

        // filter references of elements located in ignore packages
        Boolean ignore = packageIgnoreVisitor.isInIgnorePackage(referencingElementLeft);
        if (Boolean.TRUE.equals(ignore)) {
            return true;
        }

        return super.shouldBeIgnored(reference);
    }

    /**
     * Check type reference for similarity.
     * 
     * @param referencingElementLeft
     *            the left referencing element
     * @param referencingElementRight
     *            the right referencing element
     * @return true / false or null if it could not be decided
     */
    private Boolean checkTypeReference(EObject referencingElementLeft, EObject referencingElementRight) {

        // better check of similarity of referenced type
        if (referencingElementLeft instanceof TypeAccess && referencingElementRight instanceof TypeAccess) {

            TypeAccess typeAccess1 = (TypeAccess) referencingElementLeft;
            TypeAccess typeAccess2 = (TypeAccess) referencingElementRight;
            Type type1 = typeAccess1.getType();
            Type type2 = typeAccess2.getType();

            Boolean result = similarityChecker.isSimilar(type1, type2);
            if (result != null) {
                return result.booleanValue();
            } else {
                logger.warn("type reference to type not supported in similarity check: "
                        + type1.getClass().getSimpleName());
            }
        }
        return null;
    }

    /**
     * Check type reference for similarity.
     * 
     * @param referencingElementLeft
     *            the left referencing element
     * @param referencingElementRight
     *            the right referencing element
     * @return true / false or null if it could not be decided
     */
    private Boolean checkTypesReference(EObject referencingElementLeft, EObject referencingElementRight) {

        // better check of similarity of referenced type
        if (referencingElementLeft instanceof CompilationUnit && referencingElementRight instanceof CompilationUnit) {

            CompilationUnit compilationUnit1 = (CompilationUnit) referencingElementLeft;
            CompilationUnit compilationUnit2 = (CompilationUnit) referencingElementRight;

            List<AbstractTypeDeclaration> types1 = compilationUnit1.getTypes();
            List<AbstractTypeDeclaration> types2 = compilationUnit2.getTypes();

            // check that the same number of types is included in the compilation unit
            if (types1.size() != types2.size()) {
                return false;
            }

            // check that the same types are in the compilation unit
            // this currently assumes an unchanged order of types.
            // This assumption might be to strong.
            for (int i = 0; i < types1.size(); i++) {
                Boolean result = similarityChecker.isSimilar(types1.get(i), types2.get(i));
                if (result != null && result == Boolean.FALSE) {
                    return false;
                }
            }

            return true;
        }
        return null;
    }

    /**
     * Check imported element reference for similarity.
     * 
     * @param referencingElementLeft
     *            the left referencing element
     * @param referencingElementRight
     *            the right referencing element
     * @return true / false or null if it could not be decided
     */
    private Boolean checkImportedElementReference(EObject referencingElementLeft, EObject referencingElementRight) {

        // better check of similarity of referenced type
        if (referencingElementLeft instanceof ImportDeclaration && referencingElementRight instanceof ImportDeclaration) {

            ImportDeclaration importDeclaration1 = (ImportDeclaration) referencingElementLeft;
            ImportDeclaration importDeclaration2 = (ImportDeclaration) referencingElementRight;

            NamedElement importedElement1 = importDeclaration1.getImportedElement();
            NamedElement importedElement2 = importDeclaration2.getImportedElement();

            Boolean result = similarityChecker.isSimilar(importedElement1, importedElement2);
            if (result != null) {
                return result.booleanValue();
            } else {
                logger.warn("importedElement reference target not supported in similarity check: "
                        + importedElement1.getClass().getSimpleName());
            }
        }
        return null;
    }

    /**
     * Check imported element reference for similarity.
     * 
     * @param referencingElementLeft
     *            the left referencing element
     * @param referencingElementRight
     *            the right referencing element
     * @return true / false or null if it could not be decided
     */
    private Boolean checkVariableReference(EObject referencingElementLeft, EObject referencingElementRight) {

        // better check of similarity of referenced type
        if (referencingElementLeft instanceof SingleVariableAccess
                && referencingElementRight instanceof SingleVariableAccess) {

            SingleVariableAccess singleVariableAccess1 = (SingleVariableAccess) referencingElementLeft;
            SingleVariableAccess singleVariableAccess2 = (SingleVariableAccess) referencingElementRight;

            VariableDeclaration variableDeclaration1 = singleVariableAccess1.getVariable();
            VariableDeclaration variableDeclaration2 = singleVariableAccess2.getVariable();

            Boolean result = similarityChecker.isSimilar(variableDeclaration1, variableDeclaration2);
            if (result != null) {
                return result.booleanValue();
            }
        }
        return null;
    }

    /**
     * Check method reference for similarity.
     * 
     * @param referencingElementLeft
     *            the left referencing element
     * @param referencingElementRight
     *            the right referencing element
     * @return true / false or null if it could not be decided
     */
    private Boolean checkMethodReference(EObject referencingElementLeft, EObject referencingElementRight) {

        // check for reference in MethodInvocation
        if (referencingElementLeft instanceof MethodInvocation && referencingElementRight instanceof MethodInvocation) {

            MethodInvocation methodInvocation1 = (MethodInvocation) referencingElementLeft;
            MethodInvocation methodInvocation2 = (MethodInvocation) referencingElementRight;

            AbstractMethodDeclaration method1 = methodInvocation1.getMethod();
            AbstractMethodDeclaration method2 = methodInvocation2.getMethod();

            Boolean result = similarityChecker.isSimilar(method1, method2);
            if (result != null) {
                return result.booleanValue();
            }
        }

        // check for reference in ClassInstanceCreation
        if (referencingElementLeft instanceof ClassInstanceCreation
                && referencingElementRight instanceof ClassInstanceCreation) {

            ClassInstanceCreation classInstanceCreation1 = (ClassInstanceCreation) referencingElementLeft;
            ClassInstanceCreation classInstanceCreation2 = (ClassInstanceCreation) referencingElementRight;

            AbstractMethodDeclaration method1 = classInstanceCreation1.getMethod();
            AbstractMethodDeclaration method2 = classInstanceCreation2.getMethod();

            Boolean result = similarityChecker.isSimilar(method1, method2);
            if (result != null) {
                return result.booleanValue();
            } else {
                logger.warn("method reference target not supported in similarity check: "
                        + method1.getClass().getSimpleName());
            }
        }

        return null;
    }

    /* ************************************************************************************ */
    /* ****** METHODS OVERRIDDEN TO PASS ADDITIONAL ATTRIBUTES TO shouldBeIgnored() ******* */
    /* ************************************************************************************ */

    /**
     * Checks if a reference has been changed.<br/>
     * <p>
     * A reference is considered as changed if its value(s) are different (either removal or
     * addition of a value if the reference is multi-valued or change of a single-valued reference)
     * between the left and the right model.
     * </p>
     * 
     * Note: The method has been overriden to pass an additional element to the shouldBeIgnored
     * method. This also required to override this method to hand-over the origin element of the
     * mapping
     * 
     * @param root
     *            {@link DiffGroup root} of the {@link DiffElement} to create.
     * @param mapping
     *            Contains informations about the left and right model elements we have to compare.
     * @throws FactoryException
     *             Thrown if we cannot fetch the references' values.
     */
    public void checkReferencesUpdates(DiffGroup root, Match2Elements mapping) throws FactoryException {
        final EClass eClass = mapping.getLeftElement().eClass();
        final List<EReference> eclassReferences = eClass.getEAllReferences();

        Iterator<EReference> it = eclassReferences.iterator();
        while (it.hasNext()) {
            final EReference next = it.next();
            try {

                // submit the addtional referencing element to the shouldBeIgnored method
                if (!shouldBeIgnored(next, mapping)) {
                    checkReferenceUpdates(root, mapping, next);
                }

                /*
                 * if a reference should be ignored, really ignore it and also ignore the order of
                 * it's elements
                 * 
                 * } else if (next.isContainment() && next.isOrdered()) {
                 * checkContainmentReferenceOrderChange(root, mapping, next); }
                 */
            } catch (IllegalStateException e) {
                e.printStackTrace();
                throw e;
            }

        }
    }

    /* ************************************************************************************ */
    /* ************************** METHODS OVERRIDDEN TO FIX BUGS ************************** */
    /* ***************** https://bugs.eclipse.org/bugs/show_bug.cgi?id=397428 ************* */
    /* ************************************************************************************ */

    /**
     * This will check that the values of the given reference from the objects contained by mapping
     * has been modified.
     * 
     * @param root
     *            {@link DiffGroup root} of the {@link DiffElement} to create.
     * @param mapping
     *            Contains informations about the left and right model elements we have to compare.
     * @param reference
     *            The reference we need to check for differences.
     * @throws FactoryException
     *             Thrown if one of the checks fails.
     */
    protected void checkReferenceUpdates(DiffGroup root, Match2Elements mapping, EReference reference)
            throws FactoryException {
        createNonConflictingReferencesUpdate(root, reference, mapping.getLeftElement(), mapping.getRightElement());
    }

    /**
     * This will check the given <code>reference</code> for modification between
     * <code>leftElement</code> and <code>rightElement</code> and create the corresponding
     * {@link DiffElement}s under the given {@link DiffGroup}.
     * 
     * @param root
     *            {@link DiffGroup Root} of the {@link DiffElement}s to create.
     * @param reference
     *            {@link EReference} to check for modifications.
     * @param leftElement
     *            Element corresponding to the final value for the given reference.
     * @param rightElement
     *            Element corresponding to the initial value for the given reference.
     * @throws FactoryException
     *             Thrown if we cannot fetch <code>reference</code>'s values for either the left or
     *             the right element.
     */
    @SuppressWarnings("deprecation")
    private void createNonConflictingReferencesUpdate(DiffGroup root, EReference reference, EObject leftElement,
            EObject rightElement) throws FactoryException {
        final List<Object> leftElementObjReferences = convertFeatureMapList(EFactory.eGetAsList(leftElement,
                reference.getName()));
        final List<Object> rightElementObjReferences = convertFeatureMapList(EFactory.eGetAsList(rightElement,
                reference.getName()));

        // All values should be EObjects
        final List<EObject> leftElementReferences = new ArrayList<EObject>();
        final List<EObject> rightElementReferences = new ArrayList<EObject>();
        for (Object left : leftElementObjReferences) {
            leftElementReferences.add((EObject) left);
        }
        for (Object right : rightElementObjReferences) {
            rightElementReferences.add((EObject) right);
        }

        final List<EObject> deletedReferences = computeDeletedReferences(leftElementReferences, rightElementReferences);
        final List<EObject> addedReferences = computeAddedReferences(leftElementReferences, rightElementReferences);

        // REFERENCES UPDATES
        if (!reference.isMany()) {
            EObject addedValue = null;
            EObject deletedValue = null;

            if (addedReferences.size() > 0) {
                addedValue = addedReferences.get(0);
            }
            if (deletedReferences.size() > 0) {
                deletedValue = deletedReferences.get(0);
            }

            if (getMatchManager().isUnmatched(addedValue) || getMatchManager().isUnmatched(deletedValue)
                    || areDistinct(addedValue, deletedValue)) {
                root.getSubDiffElements()
                        .add(createUpdatedReferenceOperation(leftElement, rightElement, reference, addedValue,
                                deletedValue));
            }
        } else {
            // check that added references are not in deleted reference
            final Iterator<EObject> addedIterator = addedReferences.iterator();
            while (addedIterator.hasNext()) {
                final EObject added = addedIterator.next();
                final Iterator<EObject> deletedIterator = deletedReferences.iterator();
                boolean addedRemoved = false;
                while (deletedIterator.hasNext()) {
                    final EObject deleted = deletedIterator.next();
                    if (!areDistinct(added, deleted)) {
                        if (!addedRemoved) {
                            addedIterator.remove();
                            addedRemoved = true;
                        }
                        deletedIterator.remove();
                    }
                }
            }

            final List<ReferenceChangeLeftTarget> addedReferencesDiffs = new ArrayList<ReferenceChangeLeftTarget>(
                    addedReferences.size());
            final List<ReferenceChangeRightTarget> removedReferencesDiffs = new ArrayList<ReferenceChangeRightTarget>(
                    deletedReferences.size());
            // REFERENCES ADD
            if (addedReferences.size() > 0) {
                addedReferencesDiffs.addAll(createNewReferencesOperation(root, leftElement, rightElement, reference,
                        addedReferences));
            }
            // REFERENCES DEL
            if (deletedReferences.size() > 0) {
                removedReferencesDiffs.addAll(createRemovedReferencesOperation(root, leftElement, rightElement,
                        reference, deletedReferences));
            }
            // Check for references order changes
            if (reference.isOrdered()) {
                checkReferenceOrderChange(root, reference, leftElement, rightElement, addedReferencesDiffs,
                        removedReferencesDiffs);
            }
        }
    }

    /**
     * Creates the {@link DiffGroup} corresponding to a reference's value addition under the given
     * {@link DiffGroup}.<br/>
     * The parameters include the list of added references which can be computed using
     * {@link #computeAddedReferences(List, List)}.
     * 
     * @param root
     *            {@link DiffGroup root} of the {@link DiffElement}s to create.
     * @param left
     *            Left element of the reference change.
     * @param right
     *            Right element of the reference change.
     * @param reference
     *            {@link EReference} target of the operation.
     * @param addedReferences
     *            {@link List} of reference values that have been added in the <code>right</code>
     *            element since the <code>left</code> element.
     * @return The list of created differences, an empty list if none.
     */
    private List<ReferenceChangeLeftTarget> createNewReferencesOperation(DiffGroup root, EObject left, EObject right,
            EReference reference, List<EObject> addedReferences) {
        final List<ReferenceChangeLeftTarget> result = new ArrayList<ReferenceChangeLeftTarget>();
        final Iterator<EObject> addedReferenceIterator = addedReferences.iterator();
        while (addedReferenceIterator.hasNext()) {
            final EObject eobj = addedReferenceIterator.next();
            final ReferenceChangeLeftTarget addOperation = DiffFactory.eINSTANCE.createReferenceChangeLeftTarget();
            addOperation.setRightElement(right);
            addOperation.setLeftElement(left);
            addOperation.setReference(reference);
            addOperation.setLeftTarget(eobj);
            if (getMatchManager().getMatchedEObject(eobj) != null) {
                addOperation.setRightTarget(getMatchManager().getMatchedEObject(eobj));
            }
            root.getSubDiffElements().add(addOperation);
            result.add(addOperation);
        }
        return result;
    }

    /**
     * Creates the {@link DiffGroup} corresponding to a reference's value removal under the given
     * {@link DiffGroup}.<br/>
     * The parameters include the list of removed references which can be computed using
     * {@link #computeDeletedReferences(List, List)}.
     * 
     * @param root
     *            {@link DiffGroup root} of the {@link DiffElement}s to create.
     * @param left
     *            Left element of the reference change.
     * @param right
     *            Right element of the reference change.
     * @param reference
     *            {@link EReference} target of the operation.
     * @param deletedReferences
     *            {@link List} of reference values that have been removed in the <code>right</code>
     *            element since the <code>left</code> element.
     * @return The list of created differences, an empty list if none.
     */
    private List<ReferenceChangeRightTarget> createRemovedReferencesOperation(DiffGroup root, EObject left,
            EObject right, EReference reference, List<EObject> deletedReferences) {
        final List<ReferenceChangeRightTarget> result = new ArrayList<ReferenceChangeRightTarget>();
        final Iterator<EObject> deletedReferenceIterator = deletedReferences.iterator();
        while (deletedReferenceIterator.hasNext()) {
            final EObject eobj = deletedReferenceIterator.next();
            final ReferenceChangeRightTarget delOperation = DiffFactory.eINSTANCE.createReferenceChangeRightTarget();
            delOperation.setRightElement(right);
            delOperation.setLeftElement(left);
            delOperation.setReference(reference);
            delOperation.setRightTarget(eobj);
            if (getMatchManager().getMatchedEObject(eobj) != null) {
                delOperation.setLeftTarget(getMatchManager().getMatchedEObject(eobj));
            }
            root.getSubDiffElements().add(delOperation);
            result.add(delOperation);
        }
        return result;
    }

    /**
     * Checks whether the two given values are distinct.
     * 
     * @param addedValue
     *            The value that's been added to a reference.
     * @param deletedValue
     *            The value that's been removed from a reference.
     * @return <code>true</code> if there is a diff between the two values, <code>false</code>
     *         otherwise.
     */
    private boolean areDistinct(EObject addedValue, EObject deletedValue) {
        final double similarReferenceURIThreshold = 0.8d;
        boolean createDiff = false;

        // One of the two value is null, reference has been unset
        if (addedValue == deletedValue) {
            createDiff = false;
        } else if ((addedValue == null || deletedValue == null) && addedValue != deletedValue) {
            createDiff = true;
        } else if (getMatchManager().isUnmatched(addedValue) && getMatchManager().isUnmatched(deletedValue)) {
            createDiff = true;
        } else if (addedValue != null && deletedValue != null) {
            final EObject matchAdded = getMatchManager().getMatchedEObject(addedValue);
            if (matchAdded != null && matchAdded != deletedValue) {
                createDiff = true;

                // ****************************************************
                // TODO Insert similarity check for method declaration
                // ****************************************************

            } else if (getMatchManager().getMatchedEObject(deletedValue) != null) {
                // the deleted object has a match. At this point it can only be distinct from the
                // added
                // value since this added value itself has no match.
                createDiff = true;
            } else {
                final URI addedURI = EcoreUtil.getURI(addedValue);
                final URI deletedURI = EcoreUtil.getURI(deletedValue);
                if (addedValue.eIsProxy() && deletedValue.eIsProxy()) {
                    // Strict equality
                    createDiff = !addedURI.equals(deletedURI);
                } else if (addedValue.eIsProxy() || deletedValue.eIsProxy()) {
                    // Only one of them is a proxy, thus different since one of the two cannot be
                    // resolved
                    createDiff = true;
                } else if (ResourceSimilarity.computeURISimilarity(addedURI, deletedURI) < similarReferenceURIThreshold) {
                    createDiff = true;
                }
            }
        }

        return createDiff;
    }

    /**
     * Creates the {@link DiffElement} corresponding to an unique reference's value update.
     * 
     * @param left
     *            Left element of the reference change.
     * @param right
     *            Right element of the reference change.
     * @param reference
     *            {@link EReference} target of the operation.
     * @param addedValue
     *            Value which has been added for the reference.
     * @param deletedValue
     *            Value that has been deleted from the reference.
     * @return The {@link DiffElement} corresponding to an unique reference's value update
     */
    private UpdateReference createUpdatedReferenceOperation(EObject left, EObject right, EReference reference,
            EObject addedValue, EObject deletedValue) {
        final UpdateReference operation = DiffFactory.eINSTANCE.createUpdateReference();
        operation.setLeftElement(left);
        operation.setRightElement(right);
        operation.setReference(reference);

        EObject leftTarget = getMatchManager().getMatchedEObject(deletedValue);
        EObject rightTarget = getMatchManager().getMatchedEObject(addedValue);
        // checks if target are defined remotely
        if (leftTarget == null && deletedValue != null) {
            leftTarget = deletedValue;
        }
        if (rightTarget == null && addedValue != null) {
            rightTarget = addedValue;
        }

        operation.setLeftTarget(leftTarget);
        operation.setRightTarget(rightTarget);

        return operation;
    }

    /**
     * This will create and populate a {@link List} with all the references from the
     * <code>leftReferences</code> {@link List} that cannot be matched in the
     * <code>rightReferences</code> {@link List}.
     * 
     * @param leftReferences
     *            List of the left element reference values.
     * @param rightReferences
     *            List of the right element reference values.
     * @return {@link List} of all the references that have been added in the left (local) element
     *         since the right (distant) element.
     */
    private List<EObject> computeAddedReferences(List<EObject> leftReferences, List<EObject> rightReferences) {
        final List<EObject> deletedReferences = new ArrayList<EObject>();
        final List<EObject> addedReferences = new ArrayList<EObject>();
        final List<EObject> deletedProxies = new ArrayList<EObject>();

        if (leftReferences != null) {
            addedReferences.addAll(leftReferences);
        }
        if (rightReferences != null) {
            for (EObject obj : rightReferences) {
                if (obj.eIsProxy()) {
                    deletedProxies.add(obj);
                } else {
                    deletedReferences.add(obj);
                }
            }
        }
        final List<EObject> matchedOldReferences = getMatchedReferences(deletedReferences);

        // If the same object is both in left and right, it is neither added nor removed.
        addedReferences.removeAll(deletedReferences);
        // "Added" references are the references from the left element that
        // have no matching "right" counterpart
        addedReferences.removeAll(matchedOldReferences);

        // Double check for proxies
        final Iterator<EObject> addedCandidates = addedReferences.iterator();
        while (addedCandidates.hasNext() && !deletedProxies.isEmpty()) {
            final EObject added = addedCandidates.next();
            if (added.eIsProxy()) {
                final URI addedURI = ((InternalEObject) added).eProxyURI();
                boolean hasMatch = false;
                final Iterator<EObject> candidateMatches = deletedProxies.iterator();
                while (!hasMatch && candidateMatches.hasNext()) {
                    final EObject candidate = candidateMatches.next();
                    if (addedURI.equals(((InternalEObject) candidate).eProxyURI())) {
                        hasMatch = true;
                        candidateMatches.remove();
                    }
                }
                if (hasMatch) {
                    addedCandidates.remove();
                }
            }
        }

        return addedReferences;
    }

    /**
     * This will create and populate a {@link List} with all the references from the
     * <code>rightReferences</code> {@link List} that cannot be matched in the
     * <code>leftReferences</code> {@link List}.
     * 
     * @param leftReferences
     *            List of the left element reference values.
     * @param rightReferences
     *            List of the right element reference values.
     * @return {@link List} of all the references that have been deleted from the left (local)
     *         element since the right (distant) element.
     */
    private List<EObject> computeDeletedReferences(List<EObject> leftReferences, List<EObject> rightReferences) {
        final List<EObject> deletedReferences = new ArrayList<EObject>();
        final List<EObject> addedReferences = new ArrayList<EObject>();
        final List<EObject> addedProxies = new ArrayList<EObject>();

        if (leftReferences != null) {
            for (EObject obj : leftReferences) {
                if (obj.eIsProxy()) {
                    addedProxies.add(obj);
                } else {
                    addedReferences.add(obj);
                }
            }
        }
        if (rightReferences != null) {
            deletedReferences.addAll(rightReferences);
        }
        final List<EObject> matchedNewReferences = getMatchedReferences(addedReferences);

        // If the same object is both in left and right, it is neither added nor removed.
        deletedReferences.removeAll(addedReferences);
        // "deleted" references are the references from the right element that
        // have no counterpart in the left element
        deletedReferences.removeAll(matchedNewReferences);

        // Double check for proxies
        final Iterator<EObject> deletedCandidates = deletedReferences.iterator();
        while (deletedCandidates.hasNext() && !addedProxies.isEmpty()) {
            final EObject deleted = deletedCandidates.next();
            if (deleted.eIsProxy()) {
                final URI deletedURI = ((InternalEObject) deleted).eProxyURI();
                boolean hasMatch = false;
                final Iterator<EObject> candidateMatches = addedProxies.iterator();
                while (!hasMatch && candidateMatches.hasNext()) {
                    final EObject candidate = candidateMatches.next();
                    if (deletedURI.equals(((InternalEObject) candidate).eProxyURI())) {
                        hasMatch = true;
                        candidateMatches.remove();
                    }
                }
                if (hasMatch) {
                    deletedCandidates.remove();
                }
            }
        }

        return deletedReferences;
    }

    /**
     * Returns the list of references from the given list that can be matched on either right or
     * left {@link EObject}s.
     * 
     * @param references
     *            {@link List} of the references to match.
     * @return The list of references from the given list that can be matched on either right or
     *         left {@link EObject}s.
     */
    private List<EObject> getMatchedReferences(List<EObject> references) {
        final List<EObject> matchedReferences = new ArrayList<EObject>();
        final Iterator<EObject> refIterator = references.iterator();
        while (refIterator.hasNext()) {
            final Object currentReference = refIterator.next();
            if (currentReference != null) {
                final EObject currentMapped = getMatchManager().getMatchedEObject((EObject) currentReference);
                if (currentMapped != null) {
                    matchedReferences.add(currentMapped);
                }
            }
        }
        return matchedReferences;
    }

}
