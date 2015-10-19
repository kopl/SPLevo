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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.ResourceManager;
import org.mihalis.opal.header.Header;
import org.splevo.project.SPLevoProject;
import org.splevo.project.VPMModelReference;
import org.splevo.ui.SPLevoUIPlugin;
import org.splevo.ui.commons.util.WorkspaceUtil;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.listeners.GenerateFeatureModelListener;
import org.splevo.ui.listeners.InitVPMListener;
import org.splevo.ui.listeners.OpenVPMListener;
import org.splevo.ui.listeners.StartRefactoringListener;
import org.splevo.ui.listeners.VPMAnalysisListener;
import org.splevo.ui.vpexplorer.util.VPMUIUtil;

import com.google.common.collect.Iterables;

/**
 * The tab to control the consolidation process.
 */
public class ProcessControlTab extends AbstractDashboardTab {

    /** Button Init VPM. */
    private Button initVpmBtn;

    /** Button Analyze VPM. */
    private Button analyzeVPMBtn;

    /** Button Start Refactoring. */
    private Button startRefactoringBtn;

    /** Button Generate feature model. */
    private Button generateFMBtn;

    /** Button Open VPM. */
    private Button openVPMBtn;

    private TableViewer tableViewer;

    private List<VPMModelReference> vpmInput;

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
    public ProcessControlTab(SPLevoProjectEditor splevoProjectEditor, TabFolder tabFolder, int tabIndex) {
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

        TabItem tbtmProcessControl = new TabItem(tabFolder, SWT.NONE, tabIndex);
        tbtmProcessControl.setText("Process Control");

        ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.V_SCROLL);
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setExpandVertical(true);
        tbtmProcessControl.setControl(scrolledComposite);

        Composite composite = new Composite(scrolledComposite, SWT.FILL);
        composite.setLayout(new GridLayout(1, false));
        composite.setLayoutData(new GridData(GridData.FILL, GridData.VERTICAL_ALIGN_BEGINNING, true, false));

        scrolledComposite.setContent(composite);

        createHeader(composite);
        createActions(composite);
        createVPMTable(composite);
    }

    private void createVPMTable(Composite composite) {
        Group vpmGroup = new Group(composite, SWT.FILL);
        vpmGroup.setText("Variation Point Models");
        vpmGroup.setLayout(new GridLayout(1, false));
        vpmGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));
        TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(10));
        tableLayout.addColumnData(new ColumnWeightData(10));
        tableLayout.addColumnData(new ColumnWeightData(10));
        Table exampleTable = new Table(vpmGroup, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL);
        exampleTable.setLinesVisible(true);
        exampleTable.setHeaderVisible(true);
        exampleTable.setLayout(tableLayout);

        createTable(exampleTable);

    }

    private void createTable(Table exampleTable) {
        tableViewer = new TableViewer(exampleTable);
        tableViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));
        TableViewerColumn vpmColumn = new TableViewerColumn(tableViewer, SWT.NONE);
        vpmColumn.getColumn().setText("VPM");
        vpmColumn.getColumn().setResizable(false);
        vpmColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (!(element instanceof VPMModelReference)) {
                    return "";
                }
                VPMModelReference vpm = (VPMModelReference) element;
                return FilenameUtils.getBaseName(vpm.getPath());
            }
        });
        TableViewerColumn dateColumn = new TableViewerColumn(tableViewer, SWT.NONE);
        dateColumn.getColumn().setText("Date");
        dateColumn.getColumn().setResizable(false);
        dateColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                return "";
            }
        });
        TableViewerColumn actionColumn = new TableViewerColumn(tableViewer, SWT.NONE);
        actionColumn.getColumn().setText("Action");
        actionColumn.getColumn().setResizable(false);
        actionColumn.setLabelProvider(new CellButtonLabelProvider());
        tableViewer.setContentProvider(new ArrayContentProvider());
        vpmInput = getSPLevoProject().getVpmModelReferences();
        tableViewer.setInput(vpmInput);
    }
    /**
     * A LabelProvider to display buttons and removing them if they are not needed anymore. 
     */
    private class CellButtonLabelProvider extends CellLabelProvider {
        private Map<Object, Button> reverseButtons = new HashMap<Object, Button>();
        private static final String VPM_DATA = "vpm_data";
        private static final String BUTTON_EDITOR = "button_editor";

        @Override
        public void update(final ViewerCell cell) {

            TableItem item = (TableItem) cell.getItem();

            final Button button;

            if (reverseButtons.containsKey(cell.getElement())) {
                button = reverseButtons.get(cell.getElement());
            } else {
                button = new Button((Composite) cell.getViewerRow().getControl(), SWT.NONE);
                button.setData(VPM_DATA, item.getData());
                button.setText("Revert to this version");
                button.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseUp(MouseEvent event) {
                        Shell shell = event.widget.getDisplay().getActiveShell();
                        if (!(event.getSource() instanceof Button)) {
                            return;
                        }
                        Button button = (Button) event.getSource();
                        if (!(button.getData(VPM_DATA) instanceof VPMModelReference)) {
                            return;
                        }
                        VPMModelReference vpm = (VPMModelReference) button.getData(VPM_DATA);
                        boolean confirmed = MessageDialog
                                .openQuestion(shell, "Switch Back VPM Version",
                                        String.format("You want to switch back to %s, which removes all later versions. "
                                                + "Do you want to continue?", FilenameUtils.getBaseName(vpm.getPath())));
                        if (confirmed) {
                            VPMUIUtil.switchBackVPMVersion(getSPLevoProject(), vpm);
                            tableViewer.setInput(vpmInput);
                            tableViewer.refresh();
                            disposeButtons();
                        }
                    }

                    private void disposeButtons() {
                        for (Button button : reverseButtons.values()) {
                            if (button.isDisposed()) {
                                continue;
                            }
                            Object o = button.getData(VPM_DATA);
                            if (!vpmInput.contains(o)) {
                                TableEditor e = (TableEditor) button.getData(BUTTON_EDITOR);
                                e.getEditor().dispose();
                                e.dispose();
                                reverseButtons.remove(cell.getElement());
                            }
                        }
                    }

                    @Override
                    public void mouseDown(MouseEvent e) {
                    }

                    @Override
                    public void mouseDoubleClick(MouseEvent e) {
                    }
                });
                reverseButtons.put(cell.getElement(), button);
            }
            TableEditor editor = new TableEditor(item.getParent());
            editor.grabHorizontal = true;
            editor.grabVertical = true;
            editor.setEditor(button, item, cell.getColumnIndex());
            button.setData(BUTTON_EDITOR, editor);
            editor.layout();
        }
    }

    /**
     * Refreshes the table after data changes.
     */
    public void refreshViewer() {
        if (tableViewer != null) {
            tableViewer.refresh();
            tableViewer.getControl().getParent().layout();
            tableViewer.getControl().getParent().getParent().layout();            
        }        
    }

    private void createActions(Composite composite) {

        Group g = new Group(composite, SWT.FILL);
        g.setText("Actions");
        g.setLayout(new GridLayout(9, false));
        g.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));

        Composite filler1 = new Composite(g, SWT.NONE);
        GridData layoutData = new GridData(SWT.CENTER, SWT.CENTER, false, false, 4, 1);
        layoutData.heightHint = 0;
        filler1.setLayoutData(layoutData);

        final Image circleImage = ResourceManager.getPluginImage("org.splevo.ui", "icons/arrow_circle.png");
        Label circleLbl = new Label(g, SWT.NONE);
        circleLbl.setImage(circleImage);
        circleLbl.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, false, false));

        Composite filler2 = new Composite(g, SWT.NONE);
        filler2.setLayoutData(layoutData);

        Label activityStart = new Label(g, SWT.NONE);
        activityStart.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/bullet_green.png"));

        addActivityFlowButton(g);

        initVpmBtn = new Button(g, SWT.WRAP);
        initVpmBtn.addMouseListener(new InitVPMListener(getSplevoProjectEditor()));
        initVpmBtn.setText("Init\nVPM");
        initVpmBtn.setToolTipText("Init VPM");
        getGridDataForButtons(initVpmBtn);

        addActivityFlowButton(g);

        analyzeVPMBtn = new Button(g, SWT.WRAP);
        analyzeVPMBtn.addMouseListener(new VPMAnalysisListener(getSplevoProjectEditor()));
        analyzeVPMBtn.setText("Refine\nVPM");
        analyzeVPMBtn.setToolTipText("Refine VPM");
        getGridDataForButtons(analyzeVPMBtn);

        addActivityFlowButton(g);

        startRefactoringBtn = new Button(g, SWT.WRAP);
        startRefactoringBtn.addMouseListener(new StartRefactoringListener(getSplevoProjectEditor()));
        startRefactoringBtn.setText("Refactor\nCopies");
        startRefactoringBtn.setToolTipText("Refactor");
        getGridDataForButtons(startRefactoringBtn);

        addActivityFlowButton(g);

        generateFMBtn = new Button(g, SWT.WRAP);
        generateFMBtn.addMouseListener(new GenerateFeatureModelListener(getSplevoProjectEditor()));
        generateFMBtn.setText("Export\nSPL");
        generateFMBtn.setToolTipText("At any time, the designed software product line can be exportet to the format "
                + "specified in the Configuration section of the dashboard.");
        getGridDataForButtons(generateFMBtn);

        Composite filler3 = new Composite(g, SWT.NONE);
        filler3.setLayoutData(layoutData);

        openVPMBtn = new Button(g, SWT.NONE);
        openVPMBtn.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/splevo.gif"));
        openVPMBtn.addMouseListener(new OpenVPMListener(getSplevoProjectEditor()));
        openVPMBtn.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, true));
        openVPMBtn.setAlignment(SWT.CENTER);
    }

    private void getGridDataForButtons(Button button) {
        GridData gridData = new GridData();
        gridData.widthHint = button.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
        button.setLayoutData(gridData);
    }

    private void addActivityFlowButton(Composite container) {
        Label iconLabel = new Label(container, SWT.NONE);
        iconLabel.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/arrow_right.png"));
        iconLabel.setAlignment(SWT.CENTER);
    }

    /**
     * Enable Buttons if the information for the action is available.
     */
    public void enableButtonsIfInformationAvailable() {
        disableAllButtonsExceptProjectSelection();

        if (projectsSelected()) {
            initVpmBtn.setEnabled(true);
        } else {
            return;
        }

        if (vpmAvailable()) {
            openVPMBtn.setEnabled(true);
            analyzeVPMBtn.setEnabled(true);
            startRefactoringBtn.setEnabled(true);
            generateFMBtn.setEnabled(true);
        } else {
            return;
        }
    }

    /**
     * Check if at least one variation point model is set and can be accessed.
     * 
     * @return True if an accessible vpm exists.
     */
    private boolean vpmAvailable() {
        SPLevoProject splevoProject = getSPLevoProject();
        if (splevoProject.getVpmModelReferences().size() > 0) {
            String vpmPath = WorkspaceUtil.getAbsoluteFromWorkspaceRelativePath(Iterables.getLast(
                    splevoProject.getVpmModelReferences()).getPath());
            return new File(vpmPath).canRead();
        }
        return false;
    }

    /**
     * Checks if both input models have more than one project.
     * 
     * @return true, if both input models have more than one project, else false
     */
    private boolean projectsSelected() {
        SPLevoProject splevoProject = getSPLevoProject();
        return splevoProject.getLeadingProjects().size() > 0 && splevoProject.getIntegrationProjects().size() > 0;
    }

    /**
     * Disable all buttons except the Project Selection button.
     */
    private void disableAllButtonsExceptProjectSelection() {
        List<Control> buttons = new ArrayList<Control>();
        buttons.add(generateFMBtn);
        buttons.add(initVpmBtn);
        buttons.add(analyzeVPMBtn);
        buttons.add(startRefactoringBtn);
        buttons.add(openVPMBtn);

        for (Control button : buttons) {
            button.setEnabled(false);
        }
    }

    private void createHeader(Composite composite) {
        final Header header = new Header(composite, SWT.NONE);
        header.setTitle("SPLevo Dashboard");
        header.setImage(ResourceManager.getPluginImage(SPLevoUIPlugin.PLUGIN_ID, "icons/kopl_dash.png"));
        header.setDescription("Control the consolidation process.");
        header.setLayoutData(new GridData(GridData.FILL, GridData.VERTICAL_ALIGN_BEGINNING, true, false));
    }
}
