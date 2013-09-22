package org.splevo.diffing.emfcompare.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.AnonymousClassDeclaration;
import org.eclipse.gmt.modisco.java.ClassInstanceCreation;
import org.eclipse.gmt.modisco.java.Package;

/**
 * Utilities to work with the modisco java model.
 */
public class JavaModelUtil {

    /** The logger for this class. */
    private static Logger logger = Logger.getLogger(JavaModelUtil.class);

    /** The separator for packages. */
    private static final String PACKAGE_SEPARATOR = ".";

    /** Map to register the package paths for faster lookup. */
    private static Map<Package, String> packagePathMap = new HashMap<Package, String>();

    /** Map to register the package paths for faster lookup. */
    private static Map<AbstractTypeDeclaration, String> abstractDataTypePathMap = new HashMap<AbstractTypeDeclaration, String>();

    /**
     * Build the full qualified name of a declared type.
     * 
     * @param type
     *            The type to get the fqn for.
     * @return The fqn according to package.package.typename
     */
    public static String buildFullQualifiedName(AbstractTypeDeclaration type) {

        if (!abstractDataTypePathMap.containsKey(type)) {
            // check for null package
            if (type.getPackage() == null) {
                abstractDataTypePathMap.put(type, type.getName());
            } else {
                abstractDataTypePathMap.put(type, JavaModelUtil.buildPackagePath(type.getPackage()) + PACKAGE_SEPARATOR
                        + type.getName());
            }
        }
        return abstractDataTypePathMap.get(type);
    }

    /**
     * Build the full qualified name of a declared type.
     * 
     * @param type
     *            The type to get the fqn for.
     * @return The fqn according to package.package.typename
     */
    public static String buildFullQualifiedName(AnonymousClassDeclaration type) {

        if (type == null) {
            return null;
        }

        if (type.eContainer() != null) {
            if (type.eContainer() instanceof AbstractTypeDeclaration) {
                return buildFullQualifiedName((AbstractTypeDeclaration) type.eContainer()) + "$" + type;
            } else if (type.eContainer() instanceof AnonymousClassDeclaration) {
                return buildFullQualifiedName((AnonymousClassDeclaration) type.eContainer()) + "$" + type;
            }
            // TODO handle AnonymousClassDeclaration containers which are not supported yet 
            // (e.g. ClassInstanceCreation)
        }

        return null;

    }

    /**
     * Get the complete path of a package with all parent packages separated by a dot. This method
     * fills up a provided string builder for a better performance.
     * 
     * The name of the provided package will be included as well.
     * 
     * @param packageElement
     *            The packageElement to build the complete package path for.
     * @return The complete package path.
     */
    public static String buildPackagePath(Package packageElement) {
        if (!packagePathMap.containsKey(packageElement)) {
            StringBuilder packagePathBuilder = new StringBuilder();
            JavaModelUtil.buildPackagePath(packageElement, packagePathBuilder);
            packagePathMap.put(packageElement, packagePathBuilder.toString());
        }
        return packagePathMap.get(packageElement);
    }

    /**
     * Get the complete path of a package with all parent packages separated by a dot. This method
     * fills up a provided string builder for a better performance.
     * 
     * The name of the provided package will be included as well.
     * 
     * @param packageElement
     *            The packageElement to build the complete package path for.
     * @param builder
     *            The string builder to be filled.
     */
    private static void buildPackagePath(Package packageElement, StringBuilder builder) {
        if (packageElement != null) {
            buildPackagePath(packageElement.getPackage(), builder);
            if (builder.length() > 0) {
                builder.append(PACKAGE_SEPARATOR);
            }
            builder.append(packageElement.getName());
        }
    }

}
