package org.splevo.diffing.emfcompare.match;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.match.engine.IMatchScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.Javadoc;
import org.eclipse.gmt.modisco.java.LineComment;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.TagElement;
import org.eclipse.gmt.modisco.java.TextElement;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;

/**
 * A match scope difining which elements should be included in the java model diffing.
 * 
 * TODO: Make text element ignoring configurable<br>
 * 
 * @author Benjamin Klatt
 * 
 */
public class JavaModelMatchScope extends JavaSwitch<Boolean> implements IMatchScope {

    /** The name of the package info files to ignore. */
    private static final String PACKAGE_INFO_FILENAME = "package-info.java";

    /** The logger for this class. */
    @SuppressWarnings("unused")
    private Logger logger = Logger.getLogger(JavaModelMatchScope.class);

    /** Regular expressions defining packages to be ignored. */
    private List<String> ignorePackages = new ArrayList<String>();

    /**
     * Constructor requiring to specify the packages to ignore.
     * 
     * @param ignorePackages
     *            The regex patterns of packages to ignore.
     */
    public JavaModelMatchScope(List<String> ignorePackages) {
        this.ignorePackages.addAll(ignorePackages);
    }

    @Override
    public boolean isInScope(EObject eObject) {
        Boolean inScope = doSwitch(eObject);
        return inScope.booleanValue();
    }

    /**
     * Always return true for any resource. It is assumed that only resources which should be
     * matched are submitted to the MatchEngine.
     * 
     * @param resource
     *            The resource to check.
     * @return true
     */
    @Override
    public boolean isInScope(Resource resource) {
        return true;
    }

    /**
     * Tag elements might be java docs referring a local variable or a return value. But, they could
     * also represent annotations with functional relevance.
     * 
     * TODO: Filter tag elements which are not relevant for the processing.
     * 
     * @param object
     *            the tag element
     * @return the boolean
     */
    @Override
    public Boolean caseTagElement(TagElement object) {
        return true;
    }

    /**
     * Check if JavaDoc should be included in the scope.
     * 
     * 
     * TODO: Improve JavaDoc filtering: Failed tests if all JavaDoc elements are filtered JavaDoc
     * contains TagElements contains fragments contains SingleVariableAccess
     * 
     * @param object
     *            the object
     * @return the boolean
     */
    @Override
    public Boolean caseJavadoc(Javadoc object) {
        return true;
    }

    @Override
    public Boolean caseLineComment(LineComment object) {
        return false;
    }

    @Override
    public Boolean caseTextElement(TextElement object) {
        return false;
    }

    /**
     * Ignore package-info java files. They are not relevant for the functional differencing.
     * {@inheritDoc}
     */
    @Override
    public Boolean caseCompilationUnit(CompilationUnit compUnit) {
        if (PACKAGE_INFO_FILENAME.equals(compUnit.getName())) {
            return false;
        }
        return super.caseCompilationUnit(compUnit);
    }

    @Override
    public Boolean defaultCase(EObject object) {
        return true;
    }

    // @Override
    // public Boolean caseMethodDeclaration(MethodDeclaration object) {
    //
    // if (object.getAbstractTypeDeclaration() != null) {
    // if (object.getAbstractTypeDeclaration().getPackage() != null) {
    // String packagePath =
    // JavaModelUtil.buildPackagePath(object.getAbstractTypeDeclaration().getPackage());
    // return !ignorePackage(packagePath);
    // }
    // }
    //
    // return super.caseMethodDeclaration(object);
    // }

    // /**
    // * SingleVariableDeclarations in source elements located in the ignorePackage list are not in
    // * scope.
    // *
    // * @param object
    // * the object
    // * @return the boolean
    // */
    // @Override
    // public Boolean caseSingleVariableDeclaration(SingleVariableDeclaration object) {
    //
    // if (object.eContainer() instanceof BodyDeclaration) {
    // BodyDeclaration bodyDeclaration = (BodyDeclaration) object.eContainer();
    //
    // if (bodyDeclaration.getAbstractTypeDeclaration() != null) {
    // String fullQualifiedName = JavaModelUtil.buildFullQualifiedName(bodyDeclaration
    // .getAbstractTypeDeclaration());
    // return !ignorePackage(fullQualifiedName);
    // }
    // }
    //
    // return super.caseSingleVariableDeclaration(object);
    // }

    @Override
    public Boolean caseAbstractTypeDeclaration(AbstractTypeDeclaration object) {
        return true;
        // String packagePath = JavaModelUtil.buildPackagePath(object.getPackage());
        // boolean result = !ignorePackage(packagePath);
        // return result;
    }

    @Override
    public Boolean casePackage(Package object) {
        // TODO Check why tests fail when package and abstract type declaration filters are
        // activated
        return true;
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