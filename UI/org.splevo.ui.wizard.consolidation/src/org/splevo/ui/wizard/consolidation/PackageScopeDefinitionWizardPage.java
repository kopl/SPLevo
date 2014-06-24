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

        packagesTable = createPackagesTableViewer(container).getTable();
        packagesTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        packagesTable.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event event) {
                if (event.item instanceof TableItem) {
                    TableItem tableItem = (TableItem) event.item;
                    for (TableItem packageTableItem : findPackagesInTable(getSubPackages(
                            tableItem.getText()))) {                       
                        if (tableItem.getChecked()) {                             
                            tableItem.setChecked(true);                          
                            packageTableItem.setChecked(true);                           
                        } else if (!tableItem.getChecked()) {                             
                            tableItem.setChecked(false);
                            tableItem.setGrayed(false);
                            packageTableItem.setChecked(false);                                                        
                        }
                    }
                    
                    for (TableItem parentPackageTableItem : findPackagesInTable(getParentPackages(tableItem.getText()))) {
                        List<TableItem> subPackagesTableItems = findPackagesInTable(getSubPackages(
                                parentPackageTableItem.getText()));                        
                        if (getCountOfCheckedItems(subPackagesTableItems) == subPackagesTableItems.size()) {
                            parentPackageTableItem.setChecked(true);                            
                        } else if (getCountOfCheckedItems(subPackagesTableItems) > 0) {                         
                            parentPackageTableItem.setChecked(true);
                            parentPackageTableItem.setGrayed(true);
                        } else {                           
                            parentPackageTableItem.setChecked(false);
                            parentPackageTableItem.setGrayed(false);
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
            if (javaPackage.getChecked() && !javaPackage.getGrayed()) {
                chosenPackagesNames.add(javaPackage.getText());
            }                        
        }
        return chosenPackagesNames;
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
     * Get all chosen projects on previous page.
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
     * Get all sub packages of the given package specified by name.
     * 
     * @param packageName Name of the package.
     * @return All sub packages of the given package.
     */
    private List<IPackageFragment> getSubPackages(String packageName) {        
        List<IPackageFragment> subPackages = new ArrayList<IPackageFragment>();
        
        for (IPackageFragment javaPackage : getJavaPackages()) {
            if (javaPackage.getElementName().startsWith(packageName) 
                    && !javaPackage.getElementName().equals(packageName)) {
                subPackages.add(javaPackage);
            } 
        }                
        return subPackages;
    }
    
    /**
     * Get all parent packages of the given package specified by name, ordered by name descending.
     * 
     * @param packageName Name of the package.
     * @return All parent packages of the given package.
     */
    private List<IPackageFragment> getParentPackages(String packageName) {
        List<IPackageFragment> parentPackages = new ArrayList<IPackageFragment>();
        
        String[] split = packageName.split("\\.");        
        
        for (IPackageFragment javaPackage : getJavaPackages()) {
            String parentName = "";
            for (int  i = 0; i < split.length - 1; i++) {                  
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
     * Find the given packages in the table.
     * 
     * @param packages List with packages which has to be found.
     * @return The found packages as table items.
     */
    private List<TableItem> findPackagesInTable(List<IPackageFragment> packages) {
        List<TableItem> packagesAsTableItems = new ArrayList<TableItem>();
        
        for (IPackageFragment packageFragment : packages) {            
            for (TableItem javaPackage : packagesTable.getItems()) {
                if (packageFragment.getElementName().equals(javaPackage.getText())) {
                    packagesAsTableItems.add(javaPackage);
                    break;
                }
            }
        }
        return packagesAsTableItems;
    }
    
    /**
     * Get the count of the checked items.
     * 
     * @param tableItems The table items.
     * @return Count of the checked table items.
     */
    private int getCountOfCheckedItems(List<TableItem> tableItems) {
        List<TableItem> chechedItems = new ArrayList<TableItem>();
        
        for (TableItem tableItem : tableItems) {
            if (tableItem.getChecked()) {
                chechedItems.add(tableItem);
            }
        }
        
        return chechedItems.size();
    }
    
    /**
     * Comparator which compares the names of packages.
     */
    private class PackagesComparator implements Comparator<IPackageFragment> {

        @Override
        public int compare(IPackageFragment packageFragment1, IPackageFragment packageFragment2) {
            return packageFragment1.getElementName().compareTo(packageFragment2.getElementName());
        }   
    }
}