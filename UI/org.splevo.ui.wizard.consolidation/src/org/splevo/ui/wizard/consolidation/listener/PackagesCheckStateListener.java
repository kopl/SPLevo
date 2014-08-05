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

package org.splevo.ui.wizard.consolidation.listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;

import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.splevo.ui.wizard.consolidation.util.PackagesComparator;

/**
 * Check state listener for the packages tree viewer.
 * 
 * @author Radoslav Yankov
 */
public class PackagesCheckStateListener implements ICheckStateListener {

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
    public void checkStateChanged(CheckStateChangedEvent event) {        
        CheckboxTreeViewer packagesTreeViewer = (CheckboxTreeViewer) event.getSource();        
        packagesTreeViewer.setSubtreeChecked(event.getElement(), event.getChecked());                
        
        for (IPackageFragment parentPackage : getParentPackages(((IPackageFragment) event.getElement()))) {            
            int countOfCheckedSubpackages = getCountOfCheckedSubpackages(parentPackage, packagesTreeViewer);
            
            if (countOfCheckedSubpackages == getAllSubPackages(parentPackage).size()) {                
                packagesTreeViewer.setGrayed(parentPackage, false);
                packagesTreeViewer.setChecked(parentPackage, true);
            } else if (countOfCheckedSubpackages > 0) {
                packagesTreeViewer.setChecked(parentPackage, true);
                packagesTreeViewer.setGrayed(parentPackage, true);
            } else {
                packagesTreeViewer.setChecked(parentPackage, false);
                packagesTreeViewer.setGrayed(parentPackage, false);
            }
        }
    }

    /**
     * Get all parent packages of the given package, ordered by name descending.
     * 
     * @param childPackage
     *            The package whose parents have to be found.
     * @return A list with all parent packages of the given package.
     */
    private List<IPackageFragment> getParentPackages(IPackageFragment childPackage) {
        List<IPackageFragment> parentPackages = new ArrayList<IPackageFragment>();

        String[] split = childPackage.getElementName().split("\\.");

        for (IPackageFragment javaPackage : javaPackages) {            
            String parentName = "";

            for (int i = 0; i < split.length - 1; i++) {
                if (i == 0) {
                    parentName = parentName.concat(split[i]);
                } else {
                    parentName = parentName.concat("." + split[i]);
                }
                if (javaPackage.getElementName().equals(parentName)) {
                    parentPackages.add(javaPackage);
                }
            }
        }
        Collections.sort(parentPackages, Collections.reverseOrder(new PackagesComparator()));

        return parentPackages;
    }

    /**
     * Get the count of the all checked sub packages of the given package.
     * 
     * @param parentPackage
     *            The package whose checked sub packages have to be counted.
     * @param packagesTreeViewer
     *            The packages tree viewer
     * @return The number of the checked sub packages.
     */
    private int getCountOfCheckedSubpackages(IPackageFragment parentPackage, CheckboxTreeViewer packagesTreeViewer) {
        int countOfCheckedSubpackages = 0;
        
        for (IPackageFragment javaPackage : javaPackages) {             
            if (javaPackage.getElementName().startsWith(parentPackage.getElementName())
                    && !javaPackage.getElementName().equals(parentPackage.getElementName())
                    && packagesTreeViewer.getChecked(javaPackage)) {
                countOfCheckedSubpackages++;
            }
        }
        return countOfCheckedSubpackages;
    }

    /**
     * Get all sub packages (not only the direct ones) of the given package specified by name.
     * 
     * @param parentPackage
     *            The package whose sub packages have to be found.
     * @return A list with all sub packages of the given package.
     */
    private List<IPackageFragment> getAllSubPackages(IPackageFragment parentPackage) {
        List<IPackageFragment> subPackages = new ArrayList<IPackageFragment>();
        
        for (IPackageFragment javaPackage : javaPackages) {
            String parentPackageName = parentPackage.getElementName();
            
            if (javaPackage.getElementName().startsWith(parentPackageName)
                    && !javaPackage.getElementName().equals(parentPackageName)) {
                subPackages.add(javaPackage);
            }
        }
        return subPackages;
    }
}
