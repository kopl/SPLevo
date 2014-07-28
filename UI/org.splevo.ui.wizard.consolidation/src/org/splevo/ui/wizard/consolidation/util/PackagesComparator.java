/*******************************************************************************
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/

package org.splevo.ui.wizard.consolidation.util;

import java.util.Comparator;

import org.eclipse.jdt.core.IPackageFragment;

/**
 * Comparator which compares the names of two packages.
 * 
 * @author Radoslav Yankov
 */
public class PackagesComparator implements Comparator<IPackageFragment> {
    
    @Override
    public int compare(IPackageFragment packageFragment1, IPackageFragment packageFragment2) {
        return packageFragment1.getElementName().compareTo(packageFragment2.getElementName());
    }
}
