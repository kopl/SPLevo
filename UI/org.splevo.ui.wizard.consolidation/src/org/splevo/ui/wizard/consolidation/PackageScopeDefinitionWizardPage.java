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
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.PackageFragment;
import org.eclipse.jdt.ui.ISharedImages;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.splevo.jamopp.diffing.JaMoPPDiffer;
import org.splevo.project.SPLevoProject;

/**
 * Third page of the New Consolidation Project Wizard in which java packages to be ignored have to
 * be selected.
 * 
 * @author Radoslav Yankov
 */
@SuppressWarnings("restriction")
public class PackageScopeDefinitionWizardPage extends WizardPage {

    private SPLevoProject projectConfiguration;

    private TableViewer packagesTableViewer;
    private Table packagesTable;

    /**
     * Constructor preparing the wizard page infrastructure.
     * 
     * @param projectConfiguration
     *            The SPLevo project model.
     */
    public PackageScopeDefinitionWizardPage(SPLevoProject projectConfiguration) {
        super("Package Scope Definition Page");
        setTitle("Package Scope Definition");
        setDescription("Select the java packages to be ignored.");

        this.projectConfiguration = projectConfiguration;
    }

    @Override
    public void createControl(Composite parent) {
        GridLayout layout = new GridLayout();
        layout.verticalSpacing = 15;

        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(layout);

        Label label = new Label(container, SWT.NONE);
        label.setText("Select java packages to ignore:");

        packagesTable = createPackagesTableViewer(container).getTable();
        packagesTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true)); 
        packagesTable.addListener(SWT.Selection, new Listener() {
            
            @Override
            public void handleEvent(Event event) { 
                if (event.item instanceof TableItem) {
                    TableItem tableItem = (TableItem) event.item;
                    if (tableItem.getChecked()) {
                        for (String subPackageName : getSubPackagesNames(tableItem.getText())) {
                            for (TableItem javaPackage : packagesTable.getItems()) {
                                if (subPackageName.equals(javaPackage.getText())) {
                                    javaPackage.setChecked(true);
                                    break;
                                }
                            }
                        }
                    }
                }                              
            }
        });
        setControl(container);
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);

        if (visible) {
            packagesTableViewer.setInput(getJavaPackages());
        }
    }

    /**
     * Create a table viewer with check box elements.
     * 
     * @param container
     *            Container in which the table viewer will be created.
     * @return The created table viewer.
     */
    private TableViewer createPackagesTableViewer(Composite container) {
        packagesTableViewer = new TableViewer(container, SWT.BORDER | SWT.CHECK);
        packagesTableViewer.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(Object element) {
                if (element instanceof IPackageFragment) {
                    return ((IPackageFragment) element).getElementName();
                }
                return null;
            }

            @Override
            public Image getImage(Object element) {
                return JavaUI.getSharedImages().getImage(ISharedImages.IMG_OBJS_PACKAGE);
            }
        });
        packagesTableViewer.setContentProvider(ArrayContentProvider.getInstance());        

        return packagesTableViewer;
    }
    
    /**
     * Set the chosen packages to the SPLevo project configuration.
     */
    public void setChosenPackages() {
        List<String> chosenPackages = getChosenPackages();
        
        String packagesString = "";
        
        if (chosenPackages.size() > 0) {
            for (String chosenPackage : chosenPackages) {
                packagesString = packagesString.concat(chosenPackage + "\n");
            }  
            projectConfiguration.getDifferOptions().put(JaMoPPDiffer.OPTION_JAVA_IGNORE_PACKAGES, 
                    packagesString.trim());
        }
    }

    /**
     * Get the names of the packages the user has chosen.
     * 
     * @return List with the names of the chosen packages or a empty list if the user has not chosen
     *         any packages.
     */
    private List<String> getChosenPackages() {
        TableItem[] allPackages = packagesTable.getItems();
        List<String> chosenPackagesNames = new ArrayList<String>();

        for (TableItem javaPackage : allPackages) {
            if (javaPackage.getChecked()) {
                chosenPackagesNames.add(javaPackage.getText());
            }                        
        }
        return chosenPackagesNames;
    }

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
    
    private void removeDuplicates(List<IPackageFragment> javaPackages) {
        SortedSet<IPackageFragment> javaPackagesSet = new TreeSet<IPackageFragment>(new Comparator<IPackageFragment>() {

            @Override
            public int compare(IPackageFragment packageFragment1, IPackageFragment packageFragment2) {
                return packageFragment1.getElementName().compareTo(packageFragment2.getElementName());
            }
        });

        Iterator<IPackageFragment> iterator = javaPackages.iterator();
        while (iterator.hasNext()) {
            javaPackagesSet.add(iterator.next());
        }

        javaPackages.clear();
        javaPackages.addAll(javaPackagesSet);
    }
    
    private void orderPackagesByName(List<IPackageFragment> packages) {
        Collections.sort(packages, new Comparator<IPackageFragment>() {

            @Override
            public int compare(IPackageFragment packageFragment1, IPackageFragment packageFragment2) {
                return packageFragment1.getElementName().compareTo(packageFragment2.getElementName());
            }
        });
    }
    
    private List<String> getSubPackagesNames(String packageName) {
        IPackageFragment javaPackage = null;
        List<String> subPackagesNames = new ArrayList<String>();
        
        for (IPackageFragment packageFragment : getJavaPackages()) {
            if (packageFragment.getElementName().equals(packageName)) {
                javaPackage = packageFragment;
                break;
            }
        }
        
        if (javaPackage != null) {
            IJavaElement[] packageChildren = null;
            try {
                packageChildren = ((IPackageFragmentRoot) javaPackage.getParent()).getChildren();
            } catch (JavaModelException e) {                
                e.printStackTrace();
            }
            String[] names = ((PackageFragment) javaPackage).names;
            
            int namesLength = names.length;
            
            newPackage: for (int i = 0, length = packageChildren.length; i < length; i++) {
                String[] otherNames = ((PackageFragment) packageChildren[i]).names;
                
                if (otherNames.length <= namesLength) {
                    continue;
                }
                
                for (int j = 0; j < namesLength; j++) {
                    if (!names[j].equals(otherNames[j])) {
                        continue newPackage;
                    }
                }
                subPackagesNames.add(packageChildren[i].getElementName());
            }

        }
        return subPackagesNames;
    }
}