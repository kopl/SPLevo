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
package org.splevo.jamopp.diffing.scope;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * This class is responsible to check a java model element if it is located in a package that should
 * be ignored.
 * 
 * Internally, it makes use of a visitor based on a EMF generated switch for the java model to
 * improve the performance of the EObject analysis.
 * 
 */
public class PackageIgnoreChecker {

    /** The internal switch to check for ignoring an element. */
    private IgnoreSwitch ignoreSwitch = null;

    /**
     * Constructor requires to set the list of packages to be ignored.
     * 
     * @param ignorePackages
     *            The list of packages to be ignored. Regular expressions are excepted.
     */
    public PackageIgnoreChecker(List<String> ignorePackages) {
        ignoreSwitch = new IgnoreSwitch(ignorePackages);
    }

    /**
     * Check if an element is in a package that should be ignored.
     * 
     * @param element
     *            The element to check.
     * @return TRUE, if it should be ignored; FALSE
     */
    public Boolean isInIgnorePackage(EObject element) {
        return ignoreSwitch.doSwitch(element);
    }

}
