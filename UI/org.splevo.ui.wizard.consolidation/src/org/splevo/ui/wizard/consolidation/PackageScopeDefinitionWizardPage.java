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
package org.splevo.ui.wizard.consolidation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.wizard.consolidation.provider.PackageLabelProvider;

/**
 * Third page of the New Consolidation Project Wizard in which java packages to be ignored have to
 * be selected.
 * 
 * @author Radoslav Yankov
 */
public class PackageScopeDefinitionWizardPage extends WizardPage {

    private SPLevoProject projectConfiguration;
    private CheckboxTreeViewer packagesTreeViewer;    
 
    /**
     * Constructor preparing the wizard page infrastructure.
     * 
     * @param projectConfiguration
     *            The SPLevo project model.
     */
    public PackageScopeDefinitionWizardPage(SPLevoProject projectConfiguration) {
        super("Package Scope Definition Page");
        setTitle("Package Scope Definition");
        setDescription("Select java packages which you want to be ignored.");

        this.projectConfiguration = projectConfiguration;
    }

    @Override
    public void createControl(Composite parent) {
        GridLayout layout = new GridLayout();
        layout.verticalSpacing = 15;

        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(layout);

        Label label = new Label(container, SWT.NONE);
        label.setText("Select java packages to be ignored:");

        packagesTreeViewer = createPackagesTreeViewer(container);

        setControl(container);
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);

        if (visible) {            
           packagesTreeViewer.setInput(getRootpackages()); 
        }
    }        

    /**
     * Create a check box tree viewer for the packages.
     * 
     * @param container
     *            Container in which the tree viewer will be created.
     * @return The created tree viewer.
     */
    private CheckboxTreeViewer createPackagesTreeViewer(Composite container) {
        packagesTreeViewer = new CheckboxTreeViewer(container); 
        packagesTreeViewer.getTree().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        packagesTreeViewer.setLabelProvider(new PackageLabelProvider());
        packagesTreeViewer.setContentProvider(new PackagesTreeContentProvider());        

        return packagesTreeViewer;
    }
    
    /**
     * Get all java packages which are in the class path of the chosen projects without duplicates
     * and ordered by name.
     * 
     * @return List with all java packages.
     */
    private List<IPackageFragment> getJavaPackages() {

        List<IPackageFragment> javaPackages = new ArrayList<IPackageFragment>();

        for (IProject project: getAllChosenProjects()) {
            try {
                if (project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {

                    for (IPackageFragment packageFragment : JavaCore.create(project).getPackageFragments()) {
                        if (!packageFragment.getElementName().equals("")) {
                            javaPackages.add(packageFragment);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
        
        removeDuplicates(javaPackages);        
        orderPackagesByName(javaPackages);

        return javaPackages;
    }

    /**
     * Get all projects the user has chosen on the previous page.
     * 
     * @return List with all chosen projects.
     */
    private List<IProject> getAllChosenProjects() {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();

        List<String> chosenProjectsNames = new ArrayList<String>();
        chosenProjectsNames.addAll(projectConfiguration.getLeadingProjects());
        chosenProjectsNames.addAll(projectConfiguration.getIntegrationProjects());

        List<IProject> allChosenProjects = new ArrayList<IProject>();

        for (String chosenProjectName : chosenProjectsNames) {
            allChosenProjects.add(root.getProject(chosenProjectName));
        }

        return allChosenProjects;
    }
    
    /**
     * Remove duplicates from the give packages list.
     * 
     * @param javaPackages The packages list which has to be checked for duplicates.
     */
    private void removeDuplicates(List<IPackageFragment> javaPackages) {
        SortedSet<IPackageFragment> javaPackagesSet = new TreeSet<IPackageFragment>(new PackagesComparator());

        Iterator<IPackageFragment> iterator = javaPackages.iterator();
        while (iterator.hasNext()) {
            javaPackagesSet.add(iterator.next());
        }

        javaPackages.clear();
        javaPackages.addAll(javaPackagesSet);
    }
    
    /**
     * Oder the given packages list by name (ascending).
     * 
     * @param packages The packages list which has to be ordered.
     */
    private void orderPackagesByName(List<IPackageFragment> packages) {
        Collections.sort(packages, new PackagesComparator());
    }
    
    /**
     * Get all root packages which have to be shown in the tree.
     *  
     * @return Array with all root packages.
     */
    private IPackageFragment[] getRootpackages() {
        List<IPackageFragment> rootPackages = new ArrayList<IPackageFragment>();
        
        for (IPackageFragment javaPackage : getJavaPackages()) {
            if (getParentPackage(javaPackage) == null) {
                rootPackages.add(javaPackage);
            } 
        } 
        
        return rootPackages.toArray(new IPackageFragment[rootPackages.size()]);
    }
    
    /**
     * Get all sub packages of the given package .
     * 
     * @param parentPackage
     *            The package whose sub packages have to be found.
     * @return Array with all sub packages of the given package or null if there are no sub
     *         packages.
     */
    private IPackageFragment[] getSubPackages(IPackageFragment parentPackage) {        
        List<IPackageFragment> subPackages = new ArrayList<IPackageFragment>();        
        for (IPackageFragment javaPackage : getJavaPackages()) {
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
    private IPackageFragment getParentPackage(IPackageFragment childPackage) {
        String childPackageName = childPackage.getElementName();
        if (childPackageName.contains(".")) {
            String parentPackageName = childPackageName.substring(0, childPackageName.lastIndexOf("."));            
            for (IPackageFragment javaPackage : getJavaPackages()) {
                if (javaPackage.getElementName().equals(parentPackageName)) {
                    return javaPackage;                
                }
            }     
        }                           
        return null;
    }

    /**
     * Comparator which compares the names of two packages.
     */
    private class PackagesComparator implements Comparator<IPackageFragment> {

        @Override
        public int compare(IPackageFragment packageFragment1, IPackageFragment packageFragment2) {
            return packageFragment1.getElementName().compareTo(packageFragment2.getElementName());
        }   
    }
    
    /**
     * Content provider for the packages tree viewer.
     */
    private class PackagesTreeContentProvider implements ITreeContentProvider {

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
                return getSubPackages((IPackageFragment) element) != null;
            }            
            return false;           
        }        
    }
}