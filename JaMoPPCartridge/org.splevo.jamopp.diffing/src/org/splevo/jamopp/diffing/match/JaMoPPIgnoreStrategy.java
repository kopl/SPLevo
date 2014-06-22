/*******************************************************************************
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.jamopp.diffing.match;

import org.eclipse.emf.ecore.EObject;
import org.emftext.commons.layout.LayoutInformation;
import org.emftext.language.java.containers.CompilationUnit;
import org.splevo.diffing.match.HierarchicalMatchEngine.IgnoreStrategy;
import org.splevo.jamopp.diffing.scope.PackageIgnoreChecker;

/**
 * A strategy to identify elements to ignore during the matching.
 */
public class JaMoPPIgnoreStrategy implements IgnoreStrategy {

    /** The name of the package info files to ignore. */
    private static final String PACKAGE_INFO_FILENAME = "package-info.java";

    /** The check to identify elements not in a relevant package. */
    private PackageIgnoreChecker packageIgnoreChecker;

    /**
     * Constructor to set the required dependencies.
     *
     * @param packageIgnoreChecker
     *            The checker for package settings.
     */
    public JaMoPPIgnoreStrategy(PackageIgnoreChecker packageIgnoreChecker) {
        this.packageIgnoreChecker = packageIgnoreChecker;
    }

    @Override
    public boolean ignore(EObject element) {

        if (element instanceof LayoutInformation) {
            return true;
        }

        if (element instanceof CompilationUnit) {
            if (PACKAGE_INFO_FILENAME.equals(((CompilationUnit) element).getName())) {
                return true;
            }
        }

        Boolean inIgnorePackage = packageIgnoreChecker.isInIgnorePackage(element);
        if (inIgnorePackage == Boolean.TRUE) {
            return true;
        }

        return false;
    }
}
