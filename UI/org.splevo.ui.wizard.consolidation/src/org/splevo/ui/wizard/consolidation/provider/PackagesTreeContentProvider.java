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

package org.splevo.ui.wizard.consolidation.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Content provider for the packages tree viewer.
 * 
 * @author Radoslav Yankov
 */
public class PackagesTreeContentProvider implements ITreeContentProvider {

    private SortedSet<IPackageFragment> javaPackages;    
    
    /**
     * Set the java packages which are in the class path of the chosen projects.
     * 
     * @param javaPackages
     *            All java packages which are in the class path of the chosen projects.
     */
    public void setJavaPackages(SortedSet<IPackageFragment> javaPackages) {
        this.javaPackages = javaPackages;
    }

    @Override
    public void dispose() {
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }

    @Override
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof IPackageFragment[]) {
            return (IPackageFragment[]) inputElement;
        }
        return new Object[0];
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof IPackageFragment) {
            IPackageFragment[] subPackages = getSubPackages((IPackageFragment) parentElement);

            if (subPackages != null) {
                return subPackages;
            }
            return new Object[0];
        }
        return new Object[0];
    }

    @Override
    public Object getParent(Object element) {
        if (element instanceof IPackageFragment) {
            IPackageFragment parentPackage = getParentPackage((IPackageFragment) element);
            if (parentPackage != null) {
                return parentPackage;
            }
            return new Object[0];
        }
        return new Object[0];
    }

    @Override
    public boolean hasChildren(Object element) {
        if (element instanceof IPackageFragment) {
            return hasSubPackage((IPackageFragment) element);
        }
        return false;
    }

    /**
     * Get all direct sub packages of the given package. E.g. A direct sub package of the package
     * org.splevo will be org.splevo.ui and not org.splevo.ui.wizard!
     * 
     * @param parentPackage
     *            The package whose sub packages have to be found.
     * @return Array with all sub packages of the given package or null if there are no sub
     *         packages.
     */
    private IPackageFragment[] getSubPackages(IPackageFragment parentPackage) {
        List<IPackageFragment> subPackages = new ArrayList<IPackageFragment>();

        for (IPackageFragment javaPackage : javaPackages) {
            if (javaPackage.getElementName().matches(parentPackage.getElementName() + "\\.\\w+")) {
                subPackages.add(javaPackage);
            }
        }

        if (subPackages.size() > 0) {
            return subPackages.toArray(new IPackageFragment[subPackages.size()]);
        }

        return null;
    }

    /**
     * Get the parent package of the given package.
     * 
     * @param childPackage
     *            The package whose parent is to be found.
     * @return The parent package of the given package or null if there is no parent package.
     */
    public IPackageFragment getParentPackage(IPackageFragment childPackage) {
        String childPackageName = childPackage.getElementName();
        
        if (childPackageName.contains(".")) {
            String parentPackageName = childPackageName.substring(0, childPackageName.lastIndexOf("."));
            
            for (IPackageFragment javaPackage : javaPackages) {
                if (javaPackage.getElementName().equals(parentPackageName)) {
                    return javaPackage;
                }
            }
        }
        return null;
    }
    
    /**
     * Check if the given package has at least one sub package.
     * 
     * @param parentPackage
     *            The package which has to be checked
     * @return true if the given package has at least one sub package, otherwise false.
     */
    private boolean hasSubPackage(IPackageFragment parentPackage) {
        for (IPackageFragment javaPackage : javaPackages) {
            if (javaPackage.getElementName().matches(parentPackage.getElementName() + "\\.\\w+")) {
                return true;
            }
        }
        return false;
    }
}
