package org.splevo.diffing.emfcompare.diff;

import org.eclipse.emf.compare.match.metamodel.UnmatchElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.ConstructorDeclaration;
import org.eclipse.gmt.modisco.java.InterfaceDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.TextElement;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;
import org.splevo.diffing.emfcompare.util.PackageIgnoreChecker;

/**
 * Filter to check if an UnmatchElement should be filtered or not.
 */
public class UnmatchedElementFilter {

    /** The package ignore checker to use. */
    private PackageIgnoreChecker packageIgnoreChecker = null; 
    
    /** The element switch to work with. */
    private FilterSwitch filterSwitch = new FilterSwitch();

    /**
     * Filter requiring the packages to ignore to be set.
     * 
     * @param packageIgnoreChecker
     *            The checker to prove for packages to ignore.
     */
    public UnmatchedElementFilter(PackageIgnoreChecker packageIgnoreChecker) {
        super();
        this.packageIgnoreChecker = packageIgnoreChecker;
    }

    /**
     * Check the element the unmatch element is about.
     * 
     * @param unmatchElement
     *            the unmatch element to check
     * @return The result whether to filter out or not.
     */
    public Boolean filter(UnmatchElement unmatchElement) {
        return filterSwitch.doSwitch(unmatchElement.getElement());
    }

    /**
     * The internal switch to check the unmatch element filtering according to the model element the
     * unmatch element is about. It is based on the emf switch utilitiy class for the modisco java
     * model.
     * 
     */
    private class FilterSwitch extends JavaSwitch<Boolean> {

        /**
         * Decide to always filter text elements. {@inheritDoc}
         */
        @Override
        public Boolean caseTextElement(TextElement object) {
            return Boolean.TRUE;
        }

        @Override
        public Boolean defaultCase(EObject object) {
            return Boolean.FALSE;
        }

        @Override
        public Boolean caseSingleVariableDeclaration(SingleVariableDeclaration object) {

            // TODO Optimize to directly push the eContainer to the package ignore checker
            
            if (object.eContainer() instanceof ConstructorDeclaration) {
                ConstructorDeclaration constructor = (ConstructorDeclaration) object.eContainer();
                if (constructor.eContainer() instanceof ClassDeclaration) {
                    ClassDeclaration classDeclaration = (ClassDeclaration) constructor.eContainer();
                    Boolean result = packageIgnoreChecker.isInIgnorePackage(classDeclaration);
                    if (result != null) {
                        return result;
                    }
                }
            }
            
            if (object.eContainer() instanceof MethodDeclaration) {
                MethodDeclaration method = (MethodDeclaration) object.eContainer();
                if (method.eContainer() instanceof ClassDeclaration) {
                    ClassDeclaration classDeclaration = (ClassDeclaration) method.eContainer();
                    Boolean result = packageIgnoreChecker.isInIgnorePackage(classDeclaration);
                    if (result != null) {
                        return result;
                    }
                }
                if (method.eContainer() instanceof InterfaceDeclaration) {
                    InterfaceDeclaration classDeclaration = (InterfaceDeclaration) method.eContainer();
                    Boolean result = packageIgnoreChecker.isInIgnorePackage(classDeclaration);
                    if (result != null) {
                        return result;
                    }
                }
            }

            return super.caseSingleVariableDeclaration(object);
        }

    }
}
