package org.splevo.diffing.emfcompare.diff;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.ArrayType;
import org.eclipse.gmt.modisco.java.InterfaceDeclaration;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.PrimitiveType;
import org.eclipse.gmt.modisco.java.TypeParameter;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;
import org.splevo.diffing.emfcompare.util.JavaModelUtil;

/**
 * A visitor to check a java model element if it is located in a package that should be ignored.
 * 
 * The visitor is based on a EMF generated switch of the java model to improve the performance of
 * the EObject analysis.
 * 
 */
public class PackageIgnoreVisitor extends JavaSwitch<Boolean> {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(PackageIgnoreVisitor.class);

    /** The packages to be ignored. */
    private List<String> ignorePackages = new ArrayList<String>();

    /**
     * Constructor requires to set the list of packages to be ignored.
     * 
     * @param ignorePackages
     *            The list of packages to be ignored. Regular expressions are excepted.
     */
    public PackageIgnoreVisitor(List<String> ignorePackages) {
        this.ignorePackages.addAll(ignorePackages);
    }

    /**
     * Check a class declaration whether it is located in one of the packages to ignore.
     * 
     * Distinguishes between real classes contained in packages and
     * inner classes contained in other classes.
     * 
     * @param object
     *            The class declaration to check.
     * @return the boolean
     */
    @Override
    public Boolean caseAbstractTypeDeclaration(AbstractTypeDeclaration object) {

        // Handle first level classes which are included in a package
        if (object.getPackage() != null) {
            String packagePath = JavaModelUtil.buildPackagePath(object.getPackage());
            return checkIgnorePackage(packagePath);
        }

        // Handle inner classes which are contained in parent class
        if (object.eContainer() != null) {
            return this.doSwitch(object.eContainer());
        } else {
            logger.error("Unhandled type of AbstractTypeDeclaration to handle " + object);
            return null;
        }
    }

    /**
     * Check a package path whether it matches one of the ignore package patterns.
     * 
     * @param packagePath
     *            the package path to check
     * @return true/false whether it should be ignored or not.
     */
    public Boolean checkIgnorePackage(String packagePath) {

        for (String regex : ignorePackages) {
            if (packagePath.matches(regex)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * Check an interface declaration whether it is located in one of the packages to ignore.
     * 
     * @param object
     *            The interface declaration to check.
     * @return the boolean
     */
    @Override
    public Boolean caseInterfaceDeclaration(InterfaceDeclaration object) {
        String packagePath = JavaModelUtil.buildPackagePath(object.getPackage());
        for (String regex : ignorePackages) {
            if (packagePath.matches(regex)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * Check a package declaration whether it is one of the packages to ignore or located in one of
     * them..
     * 
     * @param object
     *            The interface declaration to check.
     * @return the boolean
     */
    @Override
    public Boolean casePackage(Package object) {
        String packagePath = JavaModelUtil.buildPackagePath(object);
        for (String regex : ignorePackages) {
            if (packagePath.matches(regex)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * Type parameters are always ignored because they are relative to the local code construct and
     * do not have any important change except of code beautifying 
     * 
     * TODO might need to be configurable if code beautifying should be considered.
     * 
     * @param object
     *            The type parameter declaration to check.
     * @return the boolean
     */
    @Override
    public Boolean caseTypeParameter(TypeParameter object) {
        return Boolean.TRUE;
    }

    /**
     * Check an array type by checking its element types. If there is no element type accessible
     * return False by default.
     * 
     * @param object
     *            The array type declaration to check.
     * @return the boolean
     */
    @Override
    public Boolean caseArrayType(ArrayType object) {
        if (object.getElementType() != null) {
            return doSwitch(object.getElementType().getType());
        }
        return Boolean.FALSE;
    }

    /**
     * Primitive types are always ignored.
     * 
     * @param object
     *            The primitive type declaration to check.
     * @return the boolean
     */
    @Override
    public Boolean casePrimitiveType(PrimitiveType object) {
        return Boolean.TRUE;
    }

    /**
     * The default case is to not ignore the supplied element.
     * 
     * @param object
     *            The object to check.
     * @return Whether to ignore it (always false).
     */
    @Override
    public Boolean defaultCase(EObject object) {
        return Boolean.FALSE;
    }

}
