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
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * Second page of the New Consolidation Project Wizard in which leading and integration projects
 * have to be specified.
 * 
 * @author Radoslav Yankov
 */
public class ProjectsSelectionWizardPage extends WizardPage {
    
    private Text leadingProjectsVariantNameField;
    private Text integrationProjectsVariantNameField; 
    
    private Table leadingProjectsTable;
    private Table integrationProjectsTable;
    
    private boolean isLeadingProjectsVariantNameFieldFilled;
    private boolean isIntegrationProjectsVariantNameFieldFilled;
    
    private int chosenLeadingProjectsCount = 0;
    private int chosenIntegrationProjectsCount = 0;

    /**
     * Constructor preparing the wizard page infrastructure.
     */
    public ProjectsSelectionWizardPage() {
        super("Projects Selection Page");
        setTitle("Projects selection");
        setDescription("Define the projects to be consolidated.");     
        
        setPageComplete(false);
    }

    @Override
    public void createControl(Composite parent) {
        GridLayout layout = new GridLayout(4, false);
        layout.verticalSpacing = 15;
        layout.horizontalSpacing = 10;

        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(layout);

        GridData gridDataSpanCells = new GridData(GridData.FILL_HORIZONTAL);
        gridDataSpanCells.horizontalSpan = 2;
        gridDataSpanCells.grabExcessHorizontalSpace = true;

        Label leadingProjectsLabel = new Label(container, SWT.NONE);
        leadingProjectsLabel.setText("Leading projects:");
        leadingProjectsLabel.setLayoutData(gridDataSpanCells);

        Label integrationProjectsLabel = new Label(container, SWT.NONE);
        integrationProjectsLabel.setText("Integration projects:");
        integrationProjectsLabel.setLayoutData(gridDataSpanCells);

        GridData gridDataSecondRow = new GridData(GridData.FILL_HORIZONTAL);

        Label leadingProjectsVariantNameLabel = new Label(container, SWT.NONE);
        leadingProjectsVariantNameLabel.setText("Variant name:");

        leadingProjectsVariantNameField = new Text(container, SWT.BORDER);
        leadingProjectsVariantNameField.setLayoutData(gridDataSecondRow);
        leadingProjectsVariantNameField.addListener(SWT.Modify, new Listener() {

            @Override
            public void handleEvent(Event event) {
                if (getLeadingProjectsVariantName() != null 
                        && !getLeadingProjectsVariantName().equals("")) {
                    isLeadingProjectsVariantNameFieldFilled = true;
                } else {
                    isLeadingProjectsVariantNameFieldFilled = false;
                }
                
                setPageComplete(isProjectSelectionPageComplete());
            }            
        });
        
        Label integrationProjectsVariantNameLabel = new Label(container, SWT.NONE);
        integrationProjectsVariantNameLabel.setText("Variant name:");

        integrationProjectsVariantNameField = new Text(container, SWT.BORDER);
        integrationProjectsVariantNameField.setLayoutData(gridDataSecondRow);
        integrationProjectsVariantNameField.addListener(SWT.Modify, new Listener() {

            @Override
            public void handleEvent(Event event) {
                if (getIntegrationProjectsVariantName() != null
                        && !getIntegrationProjectsVariantName().equals("")) {
                    isIntegrationProjectsVariantNameFieldFilled = true;
                } else {
                    isIntegrationProjectsVariantNameFieldFilled = false;
                }
                
                setPageComplete(isProjectSelectionPageComplete());
            }            
        });
        
        Label projectsLabel1 = new Label(container, SWT.NONE);
        projectsLabel1.setText("Projects:");
        projectsLabel1.setLayoutData(gridDataSpanCells);
        
        Label projectsLabel2 = new Label(container, SWT.NONE);
        projectsLabel2.setText("Projects:");
        projectsLabel2.setLayoutData(gridDataSpanCells);
        
        GridData gridDataLastRow = new GridData(SWT.FILL, SWT.FILL, true, true);
        gridDataLastRow.horizontalSpan = 2;        
        
        TableViewer leadingProjectsTableViewer = new TableViewer(container, SWT.BORDER | SWT.CHECK);
        leadingProjectsTableViewer.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                IProject project = (IProject) element;
                return project.getName();
            }
        });
        leadingProjectsTableViewer.setContentProvider(ArrayContentProvider.getInstance());
        leadingProjectsTableViewer.setInput(getProjectsFromWorkspace());          
        
        leadingProjectsTable = leadingProjectsTableViewer.getTable();
        leadingProjectsTable.setLayoutData(gridDataLastRow);          
        leadingProjectsTable.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event event) {
                if (event.detail == SWT.CHECK) {
                    TableItem  tableItem = (TableItem) event.item;
                    boolean isChecked = tableItem.getChecked();
                    
                    if (isChecked) {
                        chosenLeadingProjectsCount++;
                    } else {
                        chosenLeadingProjectsCount--;
                    }
                    
                    setPageComplete(isProjectSelectionPageComplete());
                }                                 
            }            
        });
        
        TableViewer integrationProjectsTableViewer = new TableViewer(container, SWT.BORDER | SWT.CHECK);
        integrationProjectsTableViewer.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                IProject project = (IProject) element;
                return project.getName();
            }                       
        });
        integrationProjectsTableViewer.setContentProvider(ArrayContentProvider.getInstance());
        integrationProjectsTableViewer.setInput(getProjectsFromWorkspace());
        
        integrationProjectsTable = integrationProjectsTableViewer.getTable();
        integrationProjectsTable.setLayoutData(gridDataLastRow);  
        integrationProjectsTable.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event event) {
                if (event.detail == SWT.CHECK) {
                    TableItem  tableItem = (TableItem) event.item;
                    boolean isChecked = tableItem.getChecked();
                    
                    if (isChecked) {
                        chosenIntegrationProjectsCount++;
                    } else {
                       chosenIntegrationProjectsCount--;
                    }
                    
                    setPageComplete(isProjectSelectionPageComplete());
                }                
            }            
        });
        
        setControl(container);        
    }

    private IProject[] getProjectsFromWorkspace() {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();

        IProject[] projects = root.getProjects();      

        return projects;
    }
    
    private boolean isProjectSelectionPageComplete() {
        if (isLeadingProjectsVariantNameFieldFilled && isIntegrationProjectsVariantNameFieldFilled
                && chosenLeadingProjectsCount > 0 && chosenIntegrationProjectsCount > 0) {
            return true;
        }
        return false;
    }

    /**
     * Get the value of the field for variant name of leading projects.
     * 
     * @return variant name field value for leading projects
     */
    public String getLeadingProjectsVariantName() {
        return leadingProjectsVariantNameField.getText().trim();
    }

    /**
     * Get the value of the field for variant name of integration projects.
     * 
     * @return variant name field value for integration projects
     */
    public String getIntegrationProjectsVariantName() {
        return integrationProjectsVariantNameField.getText().trim();
    }

    /**
     * Get the names of the chosen leading projects.
     * 
     * @return List with the names of the chosen leading projects.
     */
    public List<String> getChosenLeadingProjectsNames() {                
        return getChosenProjectsNames(leadingProjectsTable);
    }

    /**
     * Get the names of the chosen integration projects.
     * 
     * @return List with the names of the chosen integration projects.
     */
    public List<String> getChosenIntegrationProjectsNames() {       
        return getChosenProjectsNames(integrationProjectsTable);
    }
    
    private List<String> getChosenProjectsNames(Table projectsTable) {
        TableItem[] allProjects = projectsTable.getItems();
        List<String> chosenProjectsNames = new ArrayList<String>();
        
        for (TableItem project : allProjects) {
            if (project.getChecked()) {
                chosenProjectsNames.add(project.getText());
            }
        }
        return chosenProjectsNames;
    }
}