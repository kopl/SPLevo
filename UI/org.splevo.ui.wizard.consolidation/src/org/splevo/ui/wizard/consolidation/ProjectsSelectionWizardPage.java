/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt, Radoslav Yankov
 *******************************************************************************/
package org.splevo.ui.wizard.consolidation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.IWizardPage;
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
import org.splevo.project.SPLevoProject;
import org.splevo.ui.wizard.consolidation.provider.ProjectLabelProvider;

/**
 * Second page of the New Consolidation Project Wizard in which leading and integration projects
 * have to be specified. 
 */
public class ProjectsSelectionWizardPage extends WizardPage {

    private SPLevoProject projectConfiguration;
    
    private Text leadingVariantNameField;
    private Text integrationVariantNameField;

    private Table leadingProjectsTable;
    private Table integrationProjectsTable;        

    /**
     * Constructor preparing the wizard page infrastructure.
     * 
     * @param projectConfiguration
     *            The SPLevo project model.
     */
    public ProjectsSelectionWizardPage(SPLevoProject projectConfiguration) {
        super("Projects Selection Page");
        setTitle("Projects selection");
        setDescription("Define the projects to be consolidated.");

        setPageComplete(false);
        
        this.projectConfiguration = projectConfiguration;
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

        createLabel(container, "Leading projects:", gridDataSpanCells);
        createLabel(container, "Integration projects:", gridDataSpanCells);        

        createLabel(container, "Variant name:", null);
        leadingVariantNameField = createVariantNameField(container, new CheckPageCompletedListener());

        createLabel(container, "Variant name:", null);
        integrationVariantNameField = createVariantNameField(container, new CheckPageCompletedListener());

        leadingProjectsTable = createProjectsTable(container, new LeadingProjectsTableListener());
        integrationProjectsTable = createProjectsTable(container, new IntegrationProjectsTableListener());

        setControl(container);
    }
    
    @Override
    public IWizardPage getNextPage() {
        saveChosenProjects();
        return super.getNextPage();                
    }

    /**
     * Save the chosen projects and the specified variation names to the SPLevo configuration.
     */
    private void saveChosenProjects() {
        projectConfiguration.setVariantNameLeading(leadingVariantNameField.getText().trim());
        projectConfiguration.setVariantNameIntegration(integrationVariantNameField.getText().trim());
        
        projectConfiguration.getLeadingProjects().clear();
        for (String chosenLeadingProjectName : getChosenProjectsNames(leadingProjectsTable)) {  
            projectConfiguration.getLeadingProjects().add(chosenLeadingProjectName);
        }
        projectConfiguration.getIntegrationProjects().clear();
        for (String chosenIntegrationProjectName : getChosenProjectsNames(integrationProjectsTable)) {  
            projectConfiguration.getIntegrationProjects().add(chosenIntegrationProjectName);
        }
    }

    /**
     * Create a label
     * 
     * @param container
     *            Container where the label has to be placed
     * @param labelText
     *            text of the label
     * @param layoutData
     *            Properties for the label specified through GridData object. It can be null if no
     *            special properties are needed.
     */
    private void createLabel(Composite container, String labelText, GridData layoutData) {
        Label label = new Label(container, SWT.NONE);
        label.setText(labelText);

        if (layoutData != null) {
            label.setLayoutData(layoutData);
        }
    }

    /**
     * Create a text field in which a variant name has to be entered
     * 
     * @param container
     *            Container where the field has to be placed
     * @param listener
     *            Listener to react on events
     * @return The created text field
     */
    private Text createVariantNameField(Composite container, Listener listener) {
        Text variantNameField = new Text(container, SWT.BORDER);
        variantNameField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        variantNameField.addListener(SWT.Modify, listener);

        return variantNameField;
    }

    /**
     * Create a table where the projects will be displayed.
     * 
     * @param container
     *            Container where the table has to be created
     * @param listener
     *            Listener to react on events
     * @return The created table
     */
    private Table createProjectsTable(Composite container, Listener listener) {
        Composite innerContainer = new Composite(container, SWT.NONE);
        innerContainer.setLayout(new GridLayout(1, false));
        GridData twoColumns = new GridData(SWT.FILL, SWT.FILL, true, true);
        twoColumns.horizontalSpan = 2;
        innerContainer.setLayoutData(twoColumns);

        Label label = new Label(innerContainer, SWT.NONE);
        label.setText("Projects:");

        Table projectsTable = createProjectsTableViewer(innerContainer).getTable();
        projectsTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        projectsTable.addListener(SWT.Selection, listener);

        return projectsTable;
    }

    /**
     * Create a table viewer with check box elements.
     *
     * @param container
     *            Container in which the table viewer will be created.
     * @return The created table viewer.
     */
    private TableViewer createProjectsTableViewer(Composite container) {
        TableViewer projectsTableViewer = new TableViewer(container, SWT.BORDER | SWT.CHECK);
        projectsTableViewer.setLabelProvider(new ProjectLabelProvider());
        projectsTableViewer.setContentProvider(ArrayContentProvider.getInstance());
        projectsTableViewer.setInput(getProjectsFromWorkspace());

        return projectsTableViewer;
    }

    /**
     * Get all projects that are currently in the workspace.
     *
     * @return List with all projects from the workspace.
     */
    private IProject[] getProjectsFromWorkspace() {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();

        IProject[] projects = root.getProjects();

        return projects;
    }

    /**
     * @return true if all fields are filled out and at least one leading and one integration project is
     *         chosen, otherwise false.
     */
    private boolean isProjectSelectionPageComplete() {
        boolean isLeadingProjectSelected = isAtLeastOneItemSelected(leadingProjectsTable);
        boolean isIntergrationProjectSelected = isAtLeastOneItemSelected(integrationProjectsTable);
        boolean isIntegrationVariantNameFilled = isFilled(integrationVariantNameField);
        boolean isLeadingVariantNameFilled = isFilled(leadingVariantNameField);                

        return isLeadingVariantNameFilled && isIntegrationVariantNameFilled && isLeadingProjectSelected
                && isIntergrationProjectSelected;
    }

    /**
     * Check if the given text field is filled out.
     * 
     * @param textField
     *            The text field that has to be checked
     * @return true is the given text field is filled out, otherwise false
     */
    private boolean isFilled(Text textField) {
        return textField.getText() != null && textField.getText().trim().length() > 0;
    }

    /**
     * Check if at least one table item is selected.
     * 
     * @param table
     *            The table whose items have to be checked
     * @return true if at least one item is selected, otherwise false
     */
    private boolean isAtLeastOneItemSelected(Table table) {
        boolean leadingProjectSelected = false;
        for (TableItem item : table.getItems()) {
            if (item.getChecked()) {
                leadingProjectSelected = true;
                break;
            }
        }
        return leadingProjectSelected;
    }

    /**
     * Get the names of the projects the user has chosen.
     *
     * @param projectsTable
     *            The table where the projects are placed.
     * @return List with the names of the chosen projects.
     */
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

    /**
     * Listener to react on events and trigger the page to check its completeness.
     */
    private class CheckPageCompletedListener implements Listener {
        @Override
        public void handleEvent(Event event) {            
            setPageComplete(isProjectSelectionPageComplete());
        }
    }
    
    /**
     * Listener to react on events and trigger the page to check its completeness and to
     * set the leading projects variant name if it has not been already set.
     */
    private class LeadingProjectsTableListener extends CheckPageCompletedListener {
                
        @Override        
        public void handleEvent(Event event) {
            if (!isFilled(leadingVariantNameField) && (event.detail == SWT.CHECK)) {
                List<String> chosenLeadingProjects = getChosenProjectsNames(leadingProjectsTable);
                int lastElementPosition = chosenLeadingProjects.size() - 1;
                leadingVariantNameField.setText(chosenLeadingProjects.get(lastElementPosition));
            }
            super.handleEvent(event);
        } 
    }
     
    /**
     * Listener to react on events and trigger the page to check its completeness and to
     * set the integration projects variant name if it has not been already set.
     */
    private class IntegrationProjectsTableListener extends CheckPageCompletedListener {
        
        @Override
        public void handleEvent(Event event) {
            if (!isFilled(integrationVariantNameField) && (event.detail == SWT.CHECK)) {
                List<String> chosenIntegrationProjects = getChosenProjectsNames(integrationProjectsTable);
                int lastElementPosition = chosenIntegrationProjects.size() - 1;
                integrationVariantNameField.setText(chosenIntegrationProjects.get(lastElementPosition));
            }
            super.handleEvent(event);
        }
    }
}