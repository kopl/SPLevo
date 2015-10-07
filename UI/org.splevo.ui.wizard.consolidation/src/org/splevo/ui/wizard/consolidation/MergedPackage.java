/*******************************************************************************
 * Copyright (c) 2015
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thomas Czogalik
 *******************************************************************************/
package org.splevo.ui.wizard.consolidation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.IPackageFragment;
import org.splevo.ui.wizard.consolidation.util.PackageUtil;

/**
 * Class to wrap two packages into one MergedPackage.
 */
public class MergedPackage {
    
    private IPackageFragment firstPackage;
    private IPackageFragment secondPackage;
    
    /**
     * Creates a new MergedPackage.
     * @param firstPackage the first package of the MergedPackage
     * @param secondPackage the second package of the MergedPackage
     */
    public MergedPackage(IPackageFragment firstPackage, IPackageFragment secondPackage) {
        this.firstPackage = firstPackage;
        this.secondPackage = secondPackage;
    }
    
    /**
     * Returns the packages of this MergedPackage.
     * @return the packages of this MergedPackage
     */
    public List<IPackageFragment> getPackages() {
        List<IPackageFragment> packages = new ArrayList<IPackageFragment>();
        packages.add(firstPackage);
        packages.add(secondPackage);
        return packages;
    }
    
    @Override
    public String toString() {        
        return PackageUtil.getName(firstPackage) + "|" + PackageUtil.getName(secondPackage);        
    }    
}
