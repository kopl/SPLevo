package org.splevo.diffing.emfcompare.diff;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.diff.engine.IMatchManager;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.match.metamodel.Side;
import org.eclipse.emf.compare.match.metamodel.UnmatchElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;
import org.splevo.diffing.emfcompare.java2kdmdiff.ClassDelete;
import org.splevo.diffing.emfcompare.java2kdmdiff.ClassInsert;
import org.splevo.diffing.emfcompare.java2kdmdiff.ImportDelete;
import org.splevo.diffing.emfcompare.java2kdmdiff.ImportInsert;
import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffFactory;
import org.splevo.diffing.emfcompare.java2kdmdiff.PackageDelete;
import org.splevo.diffing.emfcompare.java2kdmdiff.PackageInsert;

/**
 * A processor to handle unmatched elements according to their type of element.
 * 
 * The processor uses an internal switch based on the generated emf switch for the modisco java
 * model.
 */
public class UnmatchedElementProcessor {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(UnmatchedElementProcessor.class);

    /** The match manager to find matching elements. */
    private IMatchManager matchManager = null;

    /**
     * Constructor requiring to set the match manager.
     * 
     * @param matchManager
     *            The match manager to set.
     */
    public UnmatchedElementProcessor(IMatchManager matchManager) {
        this.matchManager = matchManager;
    }

    /**
     * Process an element to check for a model specific diff element.
     * 
     * @param unmatchElement
     *            the unmatch element
     * @return The DiffElement or null if no specific diff type exists.
     */
    public DiffElement process(UnmatchElement unmatchElement) {
        ProcessorSwitch processorSwitch = new ProcessorSwitch(unmatchElement, matchManager);
        return processorSwitch.doSwitch(unmatchElement.getElement());
    }

    /**
     * Internal processor switch to handle the unmatched element according to the type of element
     * the unmatch element is about.
     */
    private class ProcessorSwitch extends JavaSwitch<DiffElement> {

        /** The match manager to find matching elements. */
        private IMatchManager matchManager = null;

        /** The unmatch element currently processed. */
        private UnmatchElement unmatchElement = null;

        /**
         * Constructor requiring the UnmatchElement according to which the switchg should be done.
         * 
         * @param unmatchElement
         *            the unmatch element to process
         * @param matchManager
         *            the match manager
         */
        public ProcessorSwitch(UnmatchElement unmatchElement, IMatchManager matchManager) {
            this.matchManager = matchManager;
            this.unmatchElement = unmatchElement;
        }

        /**
         * Process an unmatched element which is about an import declaration.
         * 
         * Builds one of the alternatives:
         * <ul>
         * <li>For a right match: it builds an ImportInsert</li>
         * <li>For a left match: it builds an ImportDelete</li>
         * </ul>
         * 
         * @param element
         *            The ImportDeclaration to handle the switch case for.
         * @return The DiffElement derived from the import declaration.
         */
        @Override
        public DiffElement caseImportDeclaration(ImportDeclaration element) {

            if (unmatchElement.getSide() == Side.LEFT) {
                // add ImportInsert
                final ImportInsert importInsert = Java2KDMDiffFactory.eINSTANCE.createImportInsert();
                importInsert.setImportLeft(element);
                return importInsert;
            } else {
                // add ImportDelete
                final ImportDelete importDelete = Java2KDMDiffFactory.eINSTANCE.createImportDelete();
                importDelete.setImportRight(element);
                EObject leftContainer = matchManager.getMatchedEObject(element.getOriginalCompilationUnit());
                if (leftContainer != null) {
                    importDelete.setLeftContainer((CompilationUnit) leftContainer);
                }
                return importDelete;
            }
        }

        /**
         * Process an unmatched element which is about a class declaration.
         * 
         * Builds one of the alternatives:
         * <ul>
         * <li>For a right match: it builds a ClassInsert</li>
         * <li>For a left match: it builds an ClassDelete</li>
         * </ul>
         * 
         * @param classDeclaration
         *            The ClassDeclaration to handle the switch case for.
         * @return The DiffElement derived from the import declaration.
         */
        @Override
        public DiffElement caseClassDeclaration(ClassDeclaration classDeclaration) {

            if (unmatchElement.getSide() == Side.LEFT) {
                return createClassInsert(classDeclaration);
            } else {
                return createClassDelete(classDeclaration);
            }
        }

        @Override
        public DiffElement casePackage(Package packageElement) {
            logger.debug("Unmatched Package (" + unmatchElement.getSide() + "): " + packageElement.getName());

            if (unmatchElement.getSide() == Side.LEFT) {

                return createPackageInsert(packageElement);

            } else {
                final PackageDelete packageDelete = Java2KDMDiffFactory.eINSTANCE.createPackageDelete();
                packageDelete.setPackageRight(packageElement);
                EObject leftContainer = matchManager.getMatchedEObject(packageElement.getPackage());
                if (leftContainer != null) {
                    packageDelete.setLeftContainer((Package) leftContainer);
                }

                Collection<DiffElement> subDiffElements = buildSubDiffElements(packageDelete);
                packageDelete.getSubDiffElements().addAll(subDiffElements);

                return packageDelete;
            }
        }

        /**
         * Build the sub diff elements for a package insert.
         * 
         * @param packageInsert
         *            The package insert referencing to the new package.
         * @return The collection of sub diff elements.
         */
        private Collection<DiffElement> buildSubDiffElements(PackageInsert packageInsert) {

            logger.debug("buildSubDiffElements: " + packageInsert.getPackageLeft().getName());
            
            ArrayList<DiffElement> subDiffs = new ArrayList<DiffElement>();

            Package packageElement = packageInsert.getPackageLeft();
            for (AbstractTypeDeclaration typeDeclaration : packageElement.getOwnedElements()) {
                if (typeDeclaration instanceof ClassDeclaration) {
                    ClassInsert classInsert = createClassInsert((ClassDeclaration) typeDeclaration);
                    subDiffs.add(classInsert);
                } else {
                    // TODO Support enumerations
                    logger.warn("unsupported package owned element: " + typeDeclaration.toString());
                }
            }

            for (Package subPackage : packageElement.getOwnedPackages()) {
                subDiffs.add(createPackageInsert(subPackage));
            }

            return subDiffs;
        }

        /**
         * Build the sub diff elements for a package insert.
         * 
         * @param packageDelete
         *            The package delete referencing the deleted package.
         * @return The collection of sub diff elements.
         */
        private Collection<DiffElement> buildSubDiffElements(PackageDelete packageDelete) {

            ArrayList<DiffElement> subDiffs = new ArrayList<DiffElement>();

            Package packageElement = packageDelete.getPackageRight();
            for (AbstractTypeDeclaration typeDeclaration : packageElement.getOwnedElements()) {
                if (typeDeclaration instanceof ClassDeclaration) {
                    ClassDelete classDelete = createClassDelete((ClassDeclaration) typeDeclaration);
                    subDiffs.add(classDelete);
                } else {
                    // TODO Support sub packages and enumerations
                    logger.warn("unsupported package owned element: " + typeDeclaration.toString());
                }
            }

            return subDiffs;
        }

        /**
         * Create a package insert diff element for a given package element.
         * 
         * @param packageElement
         *            The inserted package.
         * @return The prepared diff element.
         */
        private PackageInsert createPackageInsert(Package packageElement) {
            final PackageInsert packageInsert = Java2KDMDiffFactory.eINSTANCE.createPackageInsert();
            packageInsert.setPackageLeft(packageElement);

            Collection<DiffElement> subDiffElements = buildSubDiffElements(packageInsert);
            packageInsert.getSubDiffElements().addAll(subDiffElements);

            return packageInsert;
        }

        /**
         * Create a class insert diff element for a class declaration.
         * 
         * @param classDeclaration
         *            The class declaration to create the insert diff for.
         * @return The prepared class insert.
         */
        private ClassInsert createClassInsert(ClassDeclaration classDeclaration) {
            final ClassInsert classInsert = Java2KDMDiffFactory.eINSTANCE.createClassInsert();
            classInsert.setClassLeft(classDeclaration);
            return classInsert;
        }

        /**
         * Create a class delete element for a class declaration.
         * 
         * @param classDeclaration
         *            The class declaration to create the delete for.
         * @return The diff element for the deleted class.
         */
        private ClassDelete createClassDelete(ClassDeclaration classDeclaration) {
            final ClassDelete classDelete = Java2KDMDiffFactory.eINSTANCE.createClassDelete();
            classDelete.setClassRight(classDeclaration);
            EObject leftContainer = matchManager.getMatchedEObject(classDeclaration.getPackage());
            if (leftContainer != null) {
                classDelete.setLeftContainer((Package) leftContainer);
            }
            return classDelete;
        }

    }
}
