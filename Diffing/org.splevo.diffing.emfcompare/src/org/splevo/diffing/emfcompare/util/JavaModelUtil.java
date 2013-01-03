package org.splevo.diffing.emfcompare.util;

import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.Package;

/**
 * Utilities to work with the modisco java model.
 */
public class JavaModelUtil {

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
        StringBuilder packagePathBuilder = new StringBuilder();
        JavaModelUtil.buildPackagePath(packageElement, packagePathBuilder);
        return packagePathBuilder.toString();
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
    public static void buildPackagePath(Package packageElement, StringBuilder builder) {
        if (packageElement != null) {
            buildPackagePath(packageElement.getPackage(), builder);
            if (builder.length() > 0) {
                builder.append(".");
            }
            builder.append(packageElement.getName());
        }
    }

    /**
     * Build the full qualified name of a declared type.
     * 
     * @param type
     *            The type to get the fqn for.
     * @return The fqn according to package.package.typename
     */
    public static String buildFullQualifiedName(AbstractTypeDeclaration type) {
        StringBuilder fqnBuilder = new StringBuilder();
        JavaModelUtil.buildPackagePath(type.getPackage(), fqnBuilder);
        fqnBuilder.append(".");
        fqnBuilder.append(type.getName());
        return fqnBuilder.toString();
    }

}
