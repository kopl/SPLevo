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
import org.eclipse.jdt.internal.ui.viewsupport.JavaUILabelProvider;
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
import org.eclipse.swt.widgets.Text;

/**
 * Second page of the New Consolidation Project Wizard in which leading and integration projects
 * have to be specified.
 *
 * @author Radoslav Yankov
 */
@SuppressWarnings("restriction")
public class ProjectsSelectionWizardPage extends WizardPage {

    private Text leadingVariantNameField;
    private Text integrationVariantNameField;

    private Table leadingProjectsTable;
    private Table integrationProjectsTable;

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

        createLabel(container, "Leading projects:", gridDataSpanCells);
        createLabel(container, "Integration projects:", gridDataSpanCells);

        GridData gridDataSecondRow = new GridData(GridData.FILL_HORIZONTAL);

        createLabel(container, "Variant name:", null);
        leadingVariantNameField = createVariantNameField(container, gridDataSecondRow, new CheckPageCompletedListener());

        createLabel(container, "Variant name:", null);
        integrationVariantNameField = createVariantNameField(container, gridDataSecondRow,
                new CheckPageCompletedListener());

        leadingProjectsTable = createProjectsTable(container, new CheckPageCompletedListener());
        integrationProjectsTable = createProjectsTable(container, new CheckPageCompletedListener());

        setControl(container);
    }

    private void createLabel(Composite container, String labelText, GridData layoutData) {
        Label label = new Label(container, SWT.NONE);
        label.setText(labelText);

        if (layoutData != null) {
            label.setLayoutData(layoutData);
        }
    }

    private Text createVariantNameField(Composite container, GridData layoutData, Listener listener) {
        Text variantNameField = new Text(container, SWT.BORDER);
        variantNameField.setLayoutData(layoutData);
        variantNameField.addListener(SWT.Modify, listener);

        return variantNameField;
    }

    private Table createProjectsTable(Composite container, Listener listener) {

        Composite innerContainer = new Composite(container, SWT.NONE);
        innerContainer.setLayout(new GridLayout(1, false));
        GridData twoColumns = new GridData(SWT.FILL, SWT.FILL, true, true);
        twoColumns.horizontalSpan = 2;
        innerContainer.setLayoutData(twoColumns);

        Label label = new Label(innerContainer, SWT.NONE);
        label.setText("Projects:");

        Table projectsTable = createProjectsTableViewer(innerContainer).getTable();
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
        projectsTableViewer.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(Object element) {
                IProject project = (IProject) element;
                return project.getName();
            }

            @Override
            public Image getImage(Object element) {
                JavaUILabelProvider provider = new JavaUILabelProvider();
                return provider.getImage(element);
            }
        });
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
     * @return true if all fields are filled and at least one leading and one integration project is
     *         chosen, otherwise false.
     */
    private boolean isProjectSelectionPageComplete() {
        boolean isLeadingProjectSelected = atLeastOneItemSelected(leadingProjectsTable);
        boolean isIntergrationProjectSelected = atLeastOneItemSelected(integrationProjectsTable);
        boolean isIntegrationVariantNameFilled = isNotEmpty(integrationVariantNameField);
        boolean isLeadingVariantNameFilled = isNotEmpty(leadingVariantNameField);

        return isLeadingVariantNameFilled && isIntegrationVariantNameFilled && isLeadingProjectSelected
                && isIntergrationProjectSelected;
    }

    private boolean isNotEmpty(Text textField) {
        return textField.getText() != null && textField.getText().trim().length() > 0;
    }

    private boolean atLeastOneItemSelected(Table table) {
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
     * Get the value of the field for variant name of leading projects.
     *
     * @return variant name field value for leading projects
     */
    public String getLeadingProjectsVariantName() {
        return leadingVariantNameField.getText().trim();
    }

    /**
     * Get the value of the field for variant name of integration projects.
     *
     * @return variant name field value for integration projects
     */
    public String getIntegrationProjectsVariantName() {
        return integrationVariantNameField.getText().trim();
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
     * Listener to react on events and trigger the page to check it's completeness.
     */
    private class CheckPageCompletedListener implements Listener {
        @Override
        public void handleEvent(Event event) {
            setPageComplete(isProjectSelectionPageComplete());
        }
    }
}