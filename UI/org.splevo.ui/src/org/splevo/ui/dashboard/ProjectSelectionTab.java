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
package org.splevo.ui.dashboard;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.mihalis.opal.header.Header;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.SPLevoUIPlugin;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.editors.listener.MarkDirtyListener;
import org.splevo.ui.editors.listener.ProjectDropListener;

/**
 * Tab for configuring the projects to be consolidated.
 */
public class ProjectSelectionTab extends AbstractDashboardTab {

    /** The available transfer types for the drag and drop support. */
    private Transfer[] transferTypes = new Transfer[] { FileTransfer.getInstance(),
            LocalSelectionTransfer.getTransfer() };

    /** The supported drag and drop operations. */
    private int dragNDropOperations = DND.DROP_MOVE;

    /** The text input for the name of the leading variant. */
    private Text inputVariantNameLeading;

    /** The text input for the name of the variant to be integrated. */
    private Text inputVariantNameIntegration;

    /**
     * Create the tab to handle the SPL profile configuration.
     *
     * @param splevoProjectEditor
     *            The editor to access when configuration is modified.
     * @param tabFolder
     *            The folder to add the tab to.
     *
     * @param tabIndex
     *            The index of the tab within the parent tab folder.
     */
    public ProjectSelectionTab(SPLevoProjectEditor splevoProjectEditor, TabFolder tabFolder, int tabIndex) {
        super(splevoProjectEditor);
        createTab(tabFolder, tabIndex);
    }

    /**
     * Create the tab.
     *
     * @param tabFolder
     *            The folder to add the tab to.
     * @param tabIndex
     *            The index of the tab within the parent tab folder.
     */
    private void createTab(TabFolder tabFolder, int tabIndex) {

        final SPLevoProject splevoProject = getSPLevoProject();

        // SOURCE PROJECT SELECTION TAB

        TabItem tbtmProjectSelection = new TabItem(tabFolder, SWT.NONE, tabIndex);
        tbtmProjectSelection.setText("Source Projects");         
        
        ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.V_SCROLL);
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setExpandVertical(true);
        tbtmProjectSelection.setControl(scrolledComposite);

        Composite composite = new Composite(scrolledComposite, SWT.FILL);
        composite.setLayout(new GridLayout(1, false));
        composite.setLayoutData(new GridData(GridData.FILL, GridData.VERTICAL_ALIGN_BEGINNING, true, false));
        
        createHeader(composite);
        
        
        
        
        
        
        Composite comp = new Composite(composite, SWT.NONE);

        
        scrolledComposite.setContent(composite);
        scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        //tbtmProjectSelection.setControl(comp);
        comp.setLayout(null);
        

        Label lblNewLabel = new Label(comp, SWT.NONE);
        lblNewLabel.setToolTipText("Select the source entity models of the leading projects");
        lblNewLabel.setBounds(10, 10, 740, 20);
        lblNewLabel.setText("Define the projects to be consolidated");

        Label labelVariantNameLeading = new Label(comp, SWT.NONE);
        labelVariantNameLeading.setBounds(10, 37, 106, 20);
        labelVariantNameLeading.setText("Variant Name:");

        Label labelVariantNameIntegration = new Label(comp, SWT.NONE);
        labelVariantNameIntegration.setText("Variant Name:");
        labelVariantNameIntegration.setBounds(430, 36, 106, 20);

        inputVariantNameLeading = new Text(comp, SWT.BORDER);
        inputVariantNameLeading.setBounds(122, 36, 238, 26);

        inputVariantNameIntegration = new Text(comp, SWT.BORDER);
        inputVariantNameIntegration.setBounds(542, 36, 238, 26);

        // / LEADING PROJECT LIST

        final TableViewer viewerLeadingProjects = new TableViewer(comp, SWT.BORDER | SWT.FULL_SELECTION);
        viewerLeadingProjects.setContentProvider(ArrayContentProvider.getInstance());
        viewerLeadingProjects.setInput(splevoProject.getLeadingProjects());
        ProjectDropListener dropListenerLeadingProjects = new ProjectDropListener(getSplevoProjectEditor(),
                viewerLeadingProjects, splevoProject.getLeadingProjects(), inputVariantNameLeading);
        viewerLeadingProjects.addDropSupport(dragNDropOperations, transferTypes, dropListenerLeadingProjects);

        Table tableLeadingProjects = viewerLeadingProjects.getTable();
        tableLeadingProjects.setHeaderVisible(true);
        tableLeadingProjects.setLinesVisible(true);
        tableLeadingProjects.setBounds(10, 63, 350, 209);

        TableColumn tblclmnLeadingProjects = new TableColumn(tableLeadingProjects, SWT.NONE);
        tblclmnLeadingProjects.setWidth(tblclmnLeadingProjects.getParent().getBounds().width);
        tblclmnLeadingProjects.setText("Leading Projects");

        // / INTEGRATION PROJECT LIST

        final TableViewer viewerIntegrationProjects = new TableViewer(comp, SWT.BORDER | SWT.FULL_SELECTION);
        viewerIntegrationProjects.setContentProvider(ArrayContentProvider.getInstance());
        viewerIntegrationProjects.setInput(splevoProject.getIntegrationProjects());
        ProjectDropListener dropListenerIntegrationProjects = new ProjectDropListener(getSplevoProjectEditor(),
                viewerIntegrationProjects, splevoProject.getIntegrationProjects(), inputVariantNameIntegration);
        viewerIntegrationProjects.addDropSupport(dragNDropOperations, transferTypes, dropListenerIntegrationProjects);

        Table tableIntegrationProjects = viewerIntegrationProjects.getTable();
        tableIntegrationProjects.setHeaderVisible(true);
        tableIntegrationProjects.setLinesVisible(true);
        tableIntegrationProjects.setBounds(430, 63, 350, 209);

        TableColumn tblclmnIntegrationProjects = new TableColumn(tableIntegrationProjects, SWT.NONE);
        tblclmnIntegrationProjects.setWidth(tblclmnIntegrationProjects.getParent().getBounds().width);
        tblclmnIntegrationProjects.setText("Integration Projects");

        Button btnClear = new Button(comp, SWT.NONE);
        btnClear.setGrayed(true);
        btnClear.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/cross.png"));
        btnClear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent e) {
                splevoProject.getLeadingProjects().clear();
                viewerLeadingProjects.refresh();
                getSplevoProjectEditor().markAsDirty();
            }
        });
        btnClear.setBounds(366, 63, 30, 30);

        Button btnClearList = new Button(comp, SWT.NONE);
        btnClearList.setToolTipText("Clear the list of source projects to integrate.");
        btnClearList.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/cross.png"));
        btnClearList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent e) {
                splevoProject.getIntegrationProjects().clear();
                viewerIntegrationProjects.refresh();
                getSplevoProjectEditor().markAsDirty();
            }
        });
        btnClearList.setBounds(786, 63, 30, 30);

        comp.setTabList(new Control[] { tableLeadingProjects, tableIntegrationProjects });

    }

    /**
     * initializing the data bindings for the UI.
     *
     * @return The prepared context to be bound to the ui.
     */
    public DataBindingContext initDataBindings() {
        DataBindingContext bindingContext = new DataBindingContext();

        SPLevoProject splevoProject = getSPLevoProject();

        MarkDirtyListener markDirtyListener = new MarkDirtyListener(getSplevoProjectEditor());
        //
        IObservableValue observeTextInputVariantNameLeadingObserveWidget = WidgetProperties.text(SWT.Modify).observe(
                inputVariantNameLeading);
        IObservableValue variantNameLeadingGetSplevoProjectObserveValue = PojoProperties.value("variantNameLeading")
                .observe(splevoProject);
        variantNameLeadingGetSplevoProjectObserveValue.addChangeListener(markDirtyListener);
        bindingContext.bindValue(observeTextInputVariantNameLeadingObserveWidget,
                variantNameLeadingGetSplevoProjectObserveValue, null, null);
        //
        IObservableValue observeTextInputVariantNameIntegrationObserveWidget = WidgetProperties.text(SWT.Modify)
                .observe(inputVariantNameIntegration);
        IObservableValue variantNameIntegrationGetSplevoProjectObserveValue = PojoProperties.value(
                "variantNameIntegration").observe(splevoProject);
        variantNameIntegrationGetSplevoProjectObserveValue.addChangeListener(markDirtyListener);
        bindingContext.bindValue(observeTextInputVariantNameIntegrationObserveWidget,
                variantNameIntegrationGetSplevoProjectObserveValue, null, null);

        //
        return bindingContext;
    }
    
    private void createHeader(Composite composite) {
        final Header header = new Header(composite, SWT.NONE);
        header.setTitle("Source Projects");
        header.setImage(ResourceManager.getPluginImage(SPLevoUIPlugin.PLUGIN_ID, "icons/configure.png"));
        header.setDescription("Configuration of the projects that take part in the consolidation.");
        header.setLayoutData(new GridData(GridData.FILL, GridData.VERTICAL_ALIGN_BEGINNING, true, false));
    }

}
