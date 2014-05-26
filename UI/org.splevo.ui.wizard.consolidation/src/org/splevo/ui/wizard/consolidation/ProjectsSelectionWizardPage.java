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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

/**
 * Second page of the New Consolidation Project Wizard in which leading and integration projects
 * have to be specified.
 * 
 * @author Radoslav Yankov
 */
public class ProjectsSelectionWizardPage extends WizardPage {
    
    /**
     * Constructor preparing the wizard page infrastructure.
     */
    public ProjectsSelectionWizardPage() {
        super("Projects Selection Page");
        setTitle("Projects selection");
        setDescription("Define the projects to be consolidated.");
    }

    @Override
    public void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);                     
        container.setLayout(null);
    
        Label leadingProjectsLabel = new Label(container, SWT.NONE);        
        leadingProjectsLabel.setBounds(10, 10, 106, 20);
        leadingProjectsLabel.setText("Leading projects:");
        
        Label integrationProjectsLabel = new Label(container, SWT.NONE);
        integrationProjectsLabel.setBounds(255, 10, 106, 20);
        integrationProjectsLabel.setText("Integration projects:");
        
        Label leadingProjectsVariantNameLabel = new Label(container, SWT.NONE);
        leadingProjectsVariantNameLabel.setBounds(10, 50, 80, 20);
        leadingProjectsVariantNameLabel.setText("Variant name:");                
        
        Text leadingProjectsVariantNameField = new Text(container, SWT.BORDER);
        leadingProjectsVariantNameField.setBounds(92, 48, 150, 20); 
        
        Label integrationProjectsVariantNameLabel = new Label(container, SWT.NONE);
        integrationProjectsVariantNameLabel.setBounds(255, 50, 80, 20);
        integrationProjectsVariantNameLabel.setText("Variant name:");
        
        Text integrationProjectsVariantNameField = new Text(container, SWT.BORDER);
        integrationProjectsVariantNameField.setBounds(335, 48, 150, 20);        
        
        Label projectsLabel1 = new Label(container, SWT.NONE);
        projectsLabel1.setBounds(10, 80, 80, 20);
        projectsLabel1.setText("Projects:");
        
        Label projectsLabel2 = new Label(container, SWT.NONE);
        projectsLabel2.setBounds(255, 80, 80, 20);
        projectsLabel2.setText("Projects:");
        
        TableViewer leadingProjectsTableViewer = new TableViewer(container, SWT.BORDER | SWT.CHECK);
        leadingProjectsTableViewer.setContentProvider(ArrayContentProvider.getInstance());
        leadingProjectsTableViewer.setInput(getProjectsFromWorkspace());
        
        Table leadingProjectsTable = leadingProjectsTableViewer.getTable();
        leadingProjectsTable.setBounds(10, 100, 230, 300);
        
        TableViewer integrationProjectsTableViewer = new TableViewer(container, SWT.BORDER | SWT.CHECK);
        integrationProjectsTableViewer.setContentProvider(ArrayContentProvider.getInstance());
        integrationProjectsTableViewer.setInput(getProjectsFromWorkspace());
        
        Table integrationProjectsTable = integrationProjectsTableViewer.getTable();
        integrationProjectsTable.setBounds(255, 100, 230, 300);
        
        setControl(container);
        setPageComplete(false);
      }   
    
    private IProject[] getProjectsFromWorkspace() {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();
        
        IProject[] projects = root.getProjects();
        
        return projects;
    }
}