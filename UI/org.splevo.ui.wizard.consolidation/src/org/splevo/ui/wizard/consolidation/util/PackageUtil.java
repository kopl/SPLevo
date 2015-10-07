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
package org.splevo.ui.wizard.consolidation.util;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.wizard.consolidation.provider.PackagesTreeContentProvider;

/**
 * Utility class of the consolidation wizard.
 */
public class PackageUtil {
    /**
     * Get all projects the user has chosen on the previous page.
     * @param projectConfiguration The SPLevo project model
     * @return List with all chosen projects.
     */
    public static List<IProject> getAllChosenProjects(SPLevoProject projectConfiguration) {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();

        List<IProject> allChosenProjects = new ArrayList<IProject>();

        for (String chosenProjectName : projectConfiguration.getLeadingProjects()) {
            allChosenProjects.add(root.getProject(chosenProjectName));
        }

        for (String chosenProjectName : projectConfiguration.getIntegrationProjects()) {
            allChosenProjects.add(root.getProject(chosenProjectName));
        }

        return allChosenProjects;
    }
    
    /**
     * Get all root packages which have to be shown in the tree.
     * @param packagesTreeContentProvider tree content provider for packages
     * @param javaPackages the java packages to find their root packages
     * @return Array with all root packages.
     */
    public static List<IPackageFragment> getRootpackages(PackagesTreeContentProvider packagesTreeContentProvider, 
            SortedSet<IPackageFragment> javaPackages) {
        List<IPackageFragment> rootPackages = new ArrayList<IPackageFragment>();

        for (IPackageFragment javaPackage : javaPackages) {
            if (packagesTreeContentProvider.getParentPackage(javaPackage) == null) {
                rootPackages.add(javaPackage);
            }
        }

        return rootPackages;
    }
    /**
     * Get all java packages which are in the class path of the chosen projects without duplicates
     * and ordered by name (ascending).
     * @param projectConfiguration The SPLevo project model
     * 
     * @return A sorted set with all java packages.
     */
    public static SortedSet<IPackageFragment> getJavaPackages(SPLevoProject projectConfiguration) {
        SortedSet<IPackageFragment> javaPackagesSet = new TreeSet<IPackageFragment>(new PackagesComparator());

        for (IProject project : getAllChosenProjects(projectConfiguration)) {
            try {
                if (project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
                    for (IPackageFragment packageFragment : JavaCore.create(project).getPackageFragments()) {
                        if (!packageFragment.getElementName().equals("")) {
                            javaPackagesSet.add(packageFragment);
                        }
                    }
                }
            } catch (Exception e) {
                /*logger.error("Exception thrown during getting all java packages "
                        + "which are in the class path of the chosen projects", e);*/
            }
        }
        return javaPackagesSet;
    }
    
    /**
     * Returns the name of the given package.
     * @param packageFragment the given package
     * @return the name of the given package
     */
    public static String getName(IPackageFragment packageFragment) {
        return packageFragment != null ? packageFragment.getElementName() : "";
    }
}
