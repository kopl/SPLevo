package org.splevo.modisco.java.diffing.match;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.Javadoc;
import org.eclipse.gmt.modisco.java.LineComment;
import org.eclipse.gmt.modisco.java.PackageAccess;
import org.eclipse.gmt.modisco.java.TagElement;
import org.eclipse.gmt.modisco.java.TextElement;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.splevo.diffing.match.HierarchicalMatchEngine.IgnoreStrategy;
import org.splevo.modisco.java.diffing.util.PackageIgnoreChecker;

/**
 * A strategy to identify elements to ignore during the matching.
 */
@SuppressWarnings("restriction")
public class MoDiscoJavaIgnoreStrategy implements IgnoreStrategy {

    /** Switch for elements which should be ignored at all. */
    private ElementsToIgnoreSwitch elementsIgnoreSwitch = new ElementsToIgnoreSwitch();

    /** The check to identify elements not in a relevant package. */
    private PackageIgnoreChecker packageIgnoreChecker;

    /**
     * Constructor to set the required dependencies.
     * 
     * @param packageIgnoreChecker
     *            The checker for package settings.
     */
    public MoDiscoJavaIgnoreStrategy(PackageIgnoreChecker packageIgnoreChecker) {
        this.packageIgnoreChecker = packageIgnoreChecker;
    }

	@Override
    public boolean ignore(EObject element) {

        Boolean ignoredElement = elementsIgnoreSwitch.doSwitch(element);
        if (ignoredElement == Boolean.TRUE) {
            return true;
        }

        Boolean inIgnorePackage = packageIgnoreChecker.isInIgnorePackage(element);
        if (inIgnorePackage == Boolean.TRUE) {
            return true;
        }

        if (element instanceof JavaApplication) {
            return true;
        }

        return false;
    }

    /**
     * A switch to decide if an element is part of the match scope or not.
     */
    private class ElementsToIgnoreSwitch extends JavaSwitch<Boolean> {

        /**
         * Static flag to consider comments and not. At the moment, this is used to centralize the
         * behavior. Later on it might be required to make it configurable.
         */
        private static final boolean IGNORE_COMMENTS = true;

        /** The name of the package info files to ignore. */
        private static final String PACKAGE_INFO_FILENAME = "package-info.java";

        /**
         * Tag elements are included in JavaDoc and might refer to java elements. For example, link
         * tags include type accesses or others. Annotations are held separately.
         * 
         * @param object
         *            the tag element
         * @return the boolean
         */
        @Override
        public Boolean caseTagElement(TagElement object) {
            return IGNORE_COMMENTS;
        }

        /**
         * Check if JavaDoc should be included in the scope.
         * 
         * 
         * TODO: Improve JavaDoc filtering: Failed tests if all JavaDoc elements are filtered
         * JavaDoc contains TagElements contains fragments contains SingleVariableAccess This todo
         * resulted from the EMF Compare 1.x version. It should be checked for the new as well.
         * 
         * @param object
         *            the object
         * @return the boolean
         */
        @Override
        public Boolean caseJavadoc(Javadoc object) {
            return IGNORE_COMMENTS;
        }

        @Override
        public Boolean caseLineComment(LineComment object) {
            return IGNORE_COMMENTS;
        }

        @Override
        public Boolean caseTextElement(TextElement object) {
            return IGNORE_COMMENTS;
        }

        /**
         * Ignore package-info java files. They are not relevant for the functional differencing.
         * {@inheritDoc}
         */
        @Override
        public Boolean caseCompilationUnit(CompilationUnit compUnit) {
            if (PACKAGE_INFO_FILENAME.equals(compUnit.getName())) {
                return true;
            }
            return super.caseCompilationUnit(compUnit);
        }

        /**
         * Ignore package accesses contained in TypeAccess (qualifier references). MoDiscos behavior
         * for these packages access (qualifiers) depends on the order consuming the type accesses
         * for a specific type. However, this qualifier is not relevant for the differencing because
         * TypeAccesses are compared based on their accessed type which is fully qualified anyway.
         * 
         * {@inheritDoc}
         */
        @Override
        public Boolean casePackageAccess(PackageAccess packageAccess) {
            if (packageAccess != null && packageAccess.eContainer() != null
                    && packageAccess.eContainer() instanceof TypeAccess) {
                return true;
            }
            return false;
        }

        /**
         * Default case is to not ignore an element. An explicit value is returned to prevent any
         * parent switches.
         */
        @Override
        public Boolean defaultCase(EObject object) {
            return false;
        }
    }

}
