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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.listeners.GenerateFeatureModelListener;
import org.splevo.ui.listeners.InitVPMListener;
import org.splevo.ui.listeners.OpenVPMListener;
import org.splevo.ui.listeners.StartRefactoringListener;
import org.splevo.ui.listeners.VPMAnalysisListener;
import org.splevo.ui.util.WorkspaceUtil;

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

        Composite mainContainer = new Composite(tabFolder, SWT.NONE);
        tbtmProcessControl.setControl(mainContainer);
        GridLayout layout = new GridLayout(9, false);
        layout.horizontalSpacing = 5;
        layout.verticalSpacing = 1;
        mainContainer.setLayout(layout);

        Label lblSplevoDashboard = new Label(mainContainer, SWT.NONE);
        GridData d = new GridData(SWT.CENTER, SWT.CENTER, false, false, 9, 1);
        d.heightHint = lblSplevoDashboard.computeSize(SWT.DEFAULT, SWT.DEFAULT).y * 2;
        lblSplevoDashboard.setLayoutData(d);
        lblSplevoDashboard.setFont(SWTResourceManager.getFont("Arial", 14, SWT.BOLD));
        lblSplevoDashboard.setText("SPLevo Dashboard");

        Composite filler1 = new Composite(mainContainer, SWT.NONE);
        GridData layoutData = new GridData(SWT.CENTER, SWT.CENTER, false, false, 4, 1);
        layoutData.heightHint = 0;
        filler1.setLayoutData(layoutData);
        
        final Image circleImage = ResourceManager.getPluginImage("org.splevo.ui", "icons/arrow_circle.png");
        Label circleLbl = new Label(mainContainer, SWT.NONE);
        circleLbl.setImage(circleImage);
        circleLbl.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, false, false));

        Composite filler2 = new Composite(mainContainer, SWT.NONE);
        filler2.setLayoutData(layoutData);
        
        Label activityStart = new Label(mainContainer, SWT.NONE);
        activityStart.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/bullet_green.png"));

        addActivityFlowButton(mainContainer);

        initVpmBtn = new Button(mainContainer, SWT.WRAP);
        initVpmBtn.addMouseListener(new InitVPMListener(getSplevoProjectEditor()));
        initVpmBtn.setText("Init\nVPM");
        getGridDataForButtons(initVpmBtn);

        addActivityFlowButton(mainContainer);

        analyzeVPMBtn = new Button(mainContainer, SWT.WRAP);
        analyzeVPMBtn.addMouseListener(new VPMAnalysisListener(getSplevoProjectEditor()));
        analyzeVPMBtn.setText("Refine\nVPM");
        getGridDataForButtons(analyzeVPMBtn);

        addActivityFlowButton(mainContainer);

        startRefactoringBtn = new Button(mainContainer, SWT.WRAP);
        startRefactoringBtn.addMouseListener(new StartRefactoringListener(getSplevoProjectEditor()));
        startRefactoringBtn.setText("Refactor\nCopies");
        getGridDataForButtons(startRefactoringBtn);

        addActivityFlowButton(mainContainer);

        generateFMBtn = new Button(mainContainer, SWT.WRAP);
        generateFMBtn.addMouseListener(new GenerateFeatureModelListener(getSplevoProjectEditor()));
        generateFMBtn.setText("Export\nSPL");
        getGridDataForButtons(generateFMBtn);

        Composite filler3 = new Composite(mainContainer, SWT.NONE);
        filler3.setLayoutData(layoutData);

        openVPMBtn = new Button(mainContainer, SWT.NONE);
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
        String basePath = WorkspaceUtil.getAbsoluteWorkspacePath();
        return splevoProject.getVpmModelPaths().size() > 0
                && new File(basePath + splevoProject.getVpmModelPaths().get(0)).canRead();
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
}
