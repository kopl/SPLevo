package org.splevo.modisco.java.diffing.match;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.Javadoc;
import org.eclipse.gmt.modisco.java.LineComment;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.PackageAccess;
import org.eclipse.gmt.modisco.java.TagElement;
import org.eclipse.gmt.modisco.java.TextElement;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;

/**
 * A switch to decide if an element is part of the match scope or not.
 */
public class IsInScopeSwitch extends JavaSwitch<Boolean> {

    /** 
     * Static flag to consider comments and not. 
     * At the moment, this is used to centralize the behavior. 
     * Later on it might be required to make it configurable. 
     */
    private static final boolean CONSIDER_COMMENTS = false;

    /** The name of the package info files to ignore. */
    private static final String PACKAGE_INFO_FILENAME = "package-info.java";

    /**
     * Tag elements are included in JavaDoc and might refer to java elements. For example, link tags
     * include type accesses or others. Annotations are held separately.
     * 
     * @param object
     *            the tag element
     * @return the boolean
     */
    @Override
    public Boolean caseTagElement(TagElement object) {
        return CONSIDER_COMMENTS;
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
        return CONSIDER_COMMENTS;
    }

    @Override
    public Boolean caseLineComment(LineComment object) {
        return CONSIDER_COMMENTS;
    }

    @Override
    public Boolean caseTextElement(TextElement object) {
        return CONSIDER_COMMENTS;
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
    public Boolean caseAbstractTypeDeclaration(AbstractTypeDeclaration object) {
        return true;
    }

    @Override
    public Boolean casePackage(Package object) {
        return true;
    }

    /**
     * Ignore package accesses contained in TypeAccess (qualifier references). MoDiscos behavior for
     * these packages access (qualifiers) depends on the order consuming the type accesses for a
     * specific type. However, this qualifier is not relevant for the differencing because
     * TypeAccesses are compared based on their accessed type which is fully qualified anyway.
     * 
     * {@inheritDoc}
     */
    @Override
    public Boolean casePackageAccess(PackageAccess packageAccess) {
        if (packageAccess != null && packageAccess.eContainer() != null
                && packageAccess.eContainer() instanceof TypeAccess) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean defaultCase(EObject object) {
        return true;
    }
}