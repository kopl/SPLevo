package org.splevo.diffing.emfcompare.match;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.match.engine.IMatchScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.Javadoc;
import org.eclipse.gmt.modisco.java.LineComment;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.TextElement;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;
import org.splevo.diffing.emfcompare.util.JavaModelUtil;

/**
 * A match scope difining which elements should be included in the java model diffing.
 * 
 * TODO: Make text element ignoring configurable
 * TODO: Add package filter to match scope
 * 
 * @author Benjamin Klatt
 * 
 */
public class JavaModelMatchScope extends JavaSwitch<Boolean> implements IMatchScope {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(JavaModelMatchScope.class);

    /** Regular expressions defining packages to be ignored. */
    private List<String> ignorePackages = new ArrayList<String>();
    
    /**
     * Constructor requiring to specify the packages to ignore.
     * 
     * @param ignorePackages The regex patterns of packages to ignore.
     */
    public JavaModelMatchScope(List<String> ignorePackages) {
        this.ignorePackages.addAll(ignorePackages);
    }

    @Override
    public boolean isInScope(EObject eObject) {
        Boolean similar = doSwitch(eObject);
        return similar.booleanValue();
    }

    @Override
    public boolean isInScope(Resource resource) {
        logger.debug("isInScope(Resource resource) called");
        return true;
    }
    
    @Override
    public Boolean caseJavadoc(Javadoc object) {
        return false;
    }
    
    @Override
    public Boolean caseLineComment(LineComment object) {
        return false;
    }
    
    @Override
    public Boolean caseTextElement(TextElement object) {
        return false;
    }
    
    @Override
    public Boolean defaultCase(EObject object) {
        return true;
    }
    
    @Override
    public Boolean caseAbstractTypeDeclaration(AbstractTypeDeclaration object) {
        String packagePath = JavaModelUtil.buildPackagePath(object.getPackage());
        return !ignorePackage(packagePath);
    }
    
    @Override
    public Boolean casePackage(Package object) {
        String packagePath = JavaModelUtil.buildPackagePath(object);
        return !ignorePackage(packagePath);
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
