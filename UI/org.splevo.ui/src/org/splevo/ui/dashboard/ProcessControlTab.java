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
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.wb.swt.ResourceManager;
import org.mihalis.opal.header.Header;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.SPLevoUIPlugin;
import org.splevo.ui.commons.util.WorkspaceUtil;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.listeners.GenerateFeatureModelListener;
import org.splevo.ui.listeners.InitVPMListener;
import org.splevo.ui.listeners.OpenVPMListener;
import org.splevo.ui.listeners.StartRefactoringListener;
import org.splevo.ui.listeners.VPMAnalysisListener;

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
        initVpmBtn.setToolTipText("Initializes the variation point model."
                + " First, the differences between the consolidation projects are calculated."
                + " Afterwards, the differences are stored in variation points and variation point groups.");
        getGridDataForButtons(initVpmBtn);

        addActivityFlowButton(g);

        analyzeVPMBtn = new Button(g, SWT.WRAP);
        analyzeVPMBtn.addMouseListener(new VPMAnalysisListener(getSplevoProjectEditor()));
        analyzeVPMBtn.setText("Refine\nVPM");
        analyzeVPMBtn.setToolTipText("Refine the variation point model by grouping and merging variation points."
                + " You can select various analyses that suggest such refinements.");
        getGridDataForButtons(analyzeVPMBtn);

        addActivityFlowButton(g);

        startRefactoringBtn = new Button(g, SWT.WRAP);
        startRefactoringBtn.addMouseListener(new StartRefactoringListener(getSplevoProjectEditor()));
        startRefactoringBtn.setText("Refactor\nCopies");
        startRefactoringBtn
                .setToolTipText("Refactor the copies based on the current variation point model."
                        + " The variation points are integrated into the leading project"
                        + " The refactoring mechanisms are chosen based on the characteristics of the variation point.");
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
        openVPMBtn.setToolTipText("Opens the most recent variation point model for this project.");
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
            String vpmPath = WorkspaceUtil.getAbsoluteFromWorkspaceRelativePath(Iterables.getLast(splevoProject
                    .getVpmModelReferences()).getPath());
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
        header.setDescription("Overview and controls for the consolidation process.");
        header.setLayoutData(new GridData(GridData.FILL, GridData.VERTICAL_ALIGN_BEGINNING, true, false));
    }
}
