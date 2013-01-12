package org.splevo.diffing.emfcompare.diff;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.FactoryException;
import org.eclipse.emf.compare.diff.engine.IMatchManager;
import org.eclipse.emf.compare.diff.engine.check.AttributesCheck;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;
import org.eclipse.emf.compare.match.metamodel.Match2Elements;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.AnonymousClassDeclaration;
import org.eclipse.gmt.modisco.java.Comment;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.Javadoc;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;
import org.splevo.diffing.emfcompare.util.JavaModelUtil;

/**
 * An attribute checker specific to the java model to define if a specific attribute should be
 * ignored or not.
 * 
 */
public class JavaModelAttributesCheck extends AttributesCheck {

    /** The logger. */
    private Logger logger = Logger.getLogger(JavaModelAttributesCheck.class);

    /** The switch to decide about ignoring an attribute based on the attributes containing element. **/
    private IgnoreAttributeSwitch ignoreAttributeSwitch = null;

    /**
     * Constructor to set the match manager to be used.
     * 
     * @param manager
     *            The match manager to use.
     * @param ignorePackages
     *            the ignore packages
     */
    public JavaModelAttributesCheck(IMatchManager manager, List<String> ignorePackages) {
        super(manager);
        ignoreAttributeSwitch = new IgnoreAttributeSwitch(ignorePackages);
    }

    /**
     * An adapted attribute check to not only check the class attribute as defined in the meta model
     * but in combination with it's containing element.
     * 
     * @param root
     *            {@link DiffGroup root} of the {@link DiffElement} to create if one of the
     *            attributes has actually been changed.
     * @param mapping
     *            This contains the mapping information about the elements we need to check.
     * @throws FactoryException
     *             Thrown if one of the checks fails.
     */
    public void checkAttributesUpdates(DiffGroup root, Match2Elements mapping) throws FactoryException {
        final EClass eClass = mapping.getLeftElement().eClass();

        // check if the attribute should be ignored because of it's containing element
        Boolean ignoreAttribute = ignoreAttributeSwitch.doSwitch(mapping.getLeftElement());
        if (ignoreAttribute == Boolean.TRUE) {
            return;
        }

        final List<EAttribute> eclassAttributes = eClass.getEAllAttributes();
        // for each feature, compare the value
        final Iterator<EAttribute> it = eclassAttributes.iterator();
        while (it.hasNext()) {
            final EAttribute next = it.next();
            if (!shouldBeIgnored(next)) {
                checkAttributeUpdates(root, mapping, next);
            }
        }
    }

    /**
     * Model specific check whether an attribute should be ignored or not.
     * 
     * @param attribute
     *            The EAttribute to check.
     * @return The decision whether to ignore the attribute or not.
     */
    @Override
    protected boolean shouldBeIgnored(EAttribute attribute) {

        // ignore CompilationUnit.originalFilePath
        if ("originalFilePath".equals(attribute.getName())) {
            if (CompilationUnit.class.getCanonicalName().equals(attribute.getEContainingClass().getInstanceTypeName())) {
                return true;
            }
        }

        // ignore JavaDoc.comment and Comment.comment
        if ("content".equals(attribute.getName())) {
            String containerTypeName = attribute.getEContainingClass().getInstanceTypeName();
            if (Javadoc.class.getCanonicalName().equals(containerTypeName)
                    || Comment.class.getCanonicalName().equals(containerTypeName)) {
                return true;
            }
        }

        // ignore all attributes of the Model itself
        if (Model.class.getCanonicalName().equals(attribute.getEContainingClass().getInstanceTypeName())) {
            return true;
        }

        return super.shouldBeIgnored(attribute);
    }

    /**
     * Internal switch class to check the attribute containing element.
     */
    private class IgnoreAttributeSwitch extends JavaSwitch<Boolean> {

        /** Regular expressions defining packages to be ignored. */
        private List<String> ignorePackages = new ArrayList<String>();

        /**
         * Instantiates a new ignore attribute switch with the packages to ignore as parameter.
         * 
         * @param ignorePackages
         *            the ignore packages
         */
        public IgnoreAttributeSwitch(List<String> ignorePackages) {
            this.ignorePackages = ignorePackages;
        }

        @Override
        public Boolean caseAbstractTypeDeclaration(AbstractTypeDeclaration object) {

            // check the containing compilation unit
            if (object.getPackage() != null) {
                Package packageElement = object.getPackage();
                String packagePath = JavaModelUtil.buildPackagePath(packageElement);
                Boolean result = ignorePackage(packagePath);
                return result;
            } else {
                return doSwitch(object.eContainer());
            }
        }
        
        @Override
        public Boolean caseCompilationUnit(CompilationUnit object) {
            if (object.getPackage() != null) {
                Package packageElement = object.getPackage();
                String packagePath = JavaModelUtil.buildPackagePath(packageElement);
                Boolean result = ignorePackage(packagePath);
                return result;
            } else {
                logger.warn("Compilation unit without package: " + object.getName());
            }
            return super.caseCompilationUnit(object);
        }

        @Override
        public Boolean caseImportDeclaration(ImportDeclaration object) {
            if (object.getOriginalCompilationUnit() != null) {
                return caseCompilationUnit(object.getOriginalCompilationUnit());
            } else {
                logger.warn("ImportDeclaration without compilation unit: " + object.getImportedElement().getName());
            }
            return super.caseImportDeclaration(object);
        }

        @Override
        public Boolean caseAnonymousClassDeclaration(AnonymousClassDeclaration object) {
            return doSwitch(object.eContainer());
        }

        @Override
        public Boolean caseVariableDeclarationFragment(VariableDeclarationFragment object) {
            return doSwitch(object.eContainer());
        }

        @Override
        public Boolean caseFieldDeclaration(FieldDeclaration object) {
            return doSwitch(object.eContainer());
        }

        @Override
        public Boolean defaultCase(EObject object) {
            //logger.debug("unhandled attribute container: " + object);
            return super.defaultCase(object);
        }

        /**
         * Check a package path whether it matches one of the ignore package patterns.
         * 
         * @param packagePath
         *            the package path to check
         * @return true/false whether it should be ignored or not.
         */
        public Boolean ignorePackage(String packagePath) {

            for (String regex : ignorePackages) {
                if (packagePath.matches(regex)) {
                    return Boolean.TRUE;
                }
            }
            return Boolean.FALSE;
        }

    }

}
