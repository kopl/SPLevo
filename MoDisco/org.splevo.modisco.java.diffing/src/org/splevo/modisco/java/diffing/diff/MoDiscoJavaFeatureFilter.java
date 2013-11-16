package org.splevo.modisco.java.diffing.diff;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.compare.diff.FeatureFilter;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.ClassInstanceCreation;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.MethodInvocation;
import org.eclipse.gmt.modisco.java.SingleVariableAccess;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VariableDeclaration;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.splevo.modisco.java.diffing.util.PackageIgnoreChecker;
import org.splevo.modisco.util.SimilarityChecker;

/**
 * A filter for model element features specific for the MoDisco java model.
 */
public class MoDiscoJavaFeatureFilter extends FeatureFilter {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(MoDiscoJavaFeatureFilter.class);

    /** The package ignore visitor instance to be used. */
    private PackageIgnoreChecker packageIgnoreChecker = null;

    /** The similarity checker. */
    private SimilarityChecker similarityChecker = new SimilarityChecker();

    /**
     * Constructor requires to set the list of packages to be ignored.
     * 
     * @param ignorePackages
     *            The list of packages to be ignored. Regular expressions are excepted.
     */
    public MoDiscoJavaFeatureFilter(List<String> ignorePackages) {
        packageIgnoreChecker = new PackageIgnoreChecker(ignorePackages);
    }

    @Override
    protected boolean isIgnoredAttribute(EAttribute attribute) {

        switch (attribute.getFeatureID()) {
        case JavaPackage.COMPILATION_UNIT__ORIGINAL_FILE_PATH:
        case JavaPackage.MODEL__NAME:
        case JavaPackage.ABSTRACT_TYPE_QUALIFIED_EXPRESSION__QUALIFIER:
        case JavaPackage.MEMBER_REF__QUALIFIER:
            return true;

        default:
            return super.isIgnoredAttribute(attribute);
        }
    }
    
    /**
     * Check if a reference should be ignored or not. TODO: Refactor the many string comparisons to
     * make use of integer-based feature id comparisons {@inheritDoc}
     */
    @Override
    protected boolean isIgnoredReference(Match match, EReference reference) {

        EObject referencingElementLeft = match.getLeft();
        EObject referencingElementRight = match.getRight();

        // ************************************
        // Ignore Backlinks in bi-directional references
        // if a class is used in an import or by another type access
        // this is represented by a bi-directional reference.
        // this bi-derectional reference should be ignored for the "usagesIn.." side
        // ************************************
        String[] backLinkNames = { "usagesInTypeAccess", "usagesInImports", "usagesInPackageAccess",
                "usageInVariableAccess", "usages", "usagesInDocComments", "originalCompilationUnit", "orginalClassFile" };
        if (Arrays.asList(backLinkNames).contains(reference.getName())) {
            return true;
        }

        // Changed compilation units within the model should be
        // ignored. This changed should be identified logically
        // as changed classes or interfaces within packages
        if ("compilationUnits".equals(reference.getName())) {
            return true;
        }

        // the redefinition relationship is not of interest
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
        if ("types".equals(reference.getName())) {
            if (referencingElementLeft instanceof CompilationUnit || referencingElementRight instanceof CompilationUnit) {
                return Boolean.TRUE;
            }
            Boolean result = checkTypesReference(referencingElementLeft, referencingElementRight);
            if (result != null) {
                return result.booleanValue();
            }
        }
        // package reference from a compilation unit
        if ("package".equals(reference.getName())) {
            if (referencingElementLeft instanceof CompilationUnit || referencingElementRight instanceof CompilationUnit) {
                return Boolean.TRUE;
            }
        }
        if ("bodyDeclarations".equals(reference.getName())) {
            if (isOneInIgnorePackage(referencingElementLeft, referencingElementRight)) {
                return true;
            }
        }
        if ("importedElement".equals(reference.getName())) {
            return true;
            // Boolean result = checkImportedElementReference(referencingElementLeft,
            // referencingElementRight);
            // if (result != null) {
            // return result.booleanValue();
            // }
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

        // filter qualifier for TypeAccess
        if ("qualifier".equals(reference.getName())) {
            if (referencingElementLeft instanceof TypeAccess || referencingElementRight instanceof TypeAccess) {
                return true;
            }
        }

        // filter references of elements located in ignore packages
        if (isOneInIgnorePackage(referencingElementLeft, referencingElementRight)) {
            return true;
        }

        // filter java application model references
        if (referencingElementLeft instanceof JavaApplication || referencingElementRight instanceof JavaApplication) {
            return true;
        }

        return super.isIgnoredReference(match, reference);
    }

    @Override
    public boolean checkForOrderingChanges(EStructuralFeature feature) {
        return false;
    }

    /**
     * Check if one of the elements is in an ignore package.
     * 
     * If the left element is not null it is checked. Otherwise the right one is checked if it is
     * not null. If both are null, null is returned.
     * 
     * @param left
     *            The left element to check.
     * @param right
     *            The right element to check.
     * @return True/False if one is in an ignore package. Null if both inputs are null.
     */
    private boolean isOneInIgnorePackage(EObject left, EObject right) {
        if (left != null && packageIgnoreChecker.isInIgnorePackage(left) == Boolean.TRUE) {
            return true;
        } else {
            return (right != null && packageIgnoreChecker.isInIgnorePackage(right) == Boolean.TRUE);
        }
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

}
