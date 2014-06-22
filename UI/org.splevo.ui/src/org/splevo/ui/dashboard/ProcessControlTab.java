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
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.editors.listener.GotoTabMouseListener;
import org.splevo.ui.listeners.DiffSourceModelListener;
import org.splevo.ui.listeners.GenerateFeatureModelListener;
import org.splevo.ui.listeners.InitVPMListener;
import org.splevo.ui.listeners.OpenDiffModelListener;
import org.splevo.ui.listeners.OpenVPEditorListener;
import org.splevo.ui.listeners.StartRefactoringListener;
import org.splevo.ui.listeners.VPMAnalysisListener;
import org.splevo.ui.util.WorkspaceUtil;

/**
 * The tab to control the consolidation process.
 */
public class ProcessControlTab extends AbstractDashboardTab {

    /** Button Diffing. */
    private Button diffingBtn;

    /** Button Init VPM. */
    private Button initVpmBtn;

    /** Button Analyze VPM. */
    private Button analyzeVPMBtn;

    /** Button Start Refactoring. */
    private Button startRefactoringBtn;

    /** Button Generate feature model. */
    private Button generateFMBtn;

    /** Button Open Diff. */
    private Button openDiffBtn;

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

        Composite processControlContainer = new Composite(tabFolder, SWT.NONE);
        tbtmProcessControl.setControl(processControlContainer);

        Label lblSplevoDashboard = new Label(processControlContainer, SWT.NONE);
        lblSplevoDashboard.setAlignment(SWT.CENTER);
        lblSplevoDashboard.setFont(SWTResourceManager.getFont("Arial", 14, SWT.BOLD));
        lblSplevoDashboard.setBounds(76, 10, 570, 30);
        lblSplevoDashboard.setText("SPLevo Dashboard");

        Label activityStart = new Label(processControlContainer, SWT.NONE);
        activityStart.setAlignment(SWT.CENTER);
        activityStart.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/bullet_green.png"));
        activityStart.setBounds(20, 66, 30, 30);

        Label activityFlow0 = new Label(processControlContainer, SWT.NONE);
        activityFlow0.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/arrow_right.png"));
        activityFlow0.setAlignment(SWT.CENTER);
        activityFlow0.setBounds(44, 66, 30, 30);

        Button selectProjectBtn = new Button(processControlContainer, SWT.WRAP);
        selectProjectBtn.addMouseListener(new GotoTabMouseListener(tabFolder,
                SPLevoProjectEditor.TABINDEX_PROJECT_SELECTION));
        selectProjectBtn.setText("Project Selection");
        selectProjectBtn.setBounds(75, 59, 78, 45);

        Label activityFlow3 = new Label(processControlContainer, SWT.NONE);
        activityFlow3.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/arrow_right.png"));
        activityFlow3.setAlignment(SWT.CENTER);
        activityFlow3.setBounds(159, 66, 30, 30);

        diffingBtn = new Button(processControlContainer, SWT.WRAP);
        diffingBtn.addMouseListener(new DiffSourceModelListener(getSplevoProjectEditor()));
        diffingBtn.setText("Diffing");
        diffingBtn.setBounds(195, 58, 72, 45);

        openDiffBtn = new Button(processControlContainer, SWT.NONE);
        openDiffBtn.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/page_white_go.png"));
        openDiffBtn.setBounds(221, 109, 26, 30);
        openDiffBtn.addMouseListener(new OpenDiffModelListener(getSplevoProjectEditor()));

        Label activityFlow4 = new Label(processControlContainer, SWT.NONE);
        activityFlow4.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/arrow_right.png"));
        activityFlow4.setAlignment(SWT.CENTER);
        activityFlow4.setBounds(273, 65, 30, 30);

        initVpmBtn = new Button(processControlContainer, SWT.WRAP);
        initVpmBtn.addMouseListener(new InitVPMListener(getSplevoProjectEditor()));
        initVpmBtn.setText("Init VPM");
        initVpmBtn.setBounds(309, 58, 72, 45);

        openVPMBtn = new Button(processControlContainer, SWT.NONE);
        openVPMBtn.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/page_white_go.png"));
        openVPMBtn.setBounds(331, 109, 26, 30);
        openVPMBtn.addMouseListener(new OpenVPEditorListener(getSplevoProjectEditor()));

        Label activityFlow5 = new Label(processControlContainer, SWT.NONE);
        activityFlow5.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/arrow_right.png"));
        activityFlow5.setAlignment(SWT.CENTER);
        activityFlow5.setBounds(387, 65, 30, 30);

        analyzeVPMBtn = new Button(processControlContainer, SWT.WRAP);
        analyzeVPMBtn.addMouseListener(new VPMAnalysisListener(getSplevoProjectEditor()));
        final Image circleImage = ResourceManager.getPluginImage("org.splevo.ui", "icons/arrow_circle.png");
        analyzeVPMBtn.addPaintListener(new PaintListener() {
            public void paintControl(PaintEvent e) {
                e.gc.drawImage(circleImage, 51, 24);
            }
        });
        analyzeVPMBtn.setText("Analyze VPM");
        analyzeVPMBtn.setBounds(420, 58, 70, 45);

        Label activityFlow6 = new Label(processControlContainer, SWT.NONE);
        activityFlow6.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/arrow_right.png"));
        activityFlow6.setAlignment(SWT.CENTER);
        activityFlow6.setBounds(495, 65, 30, 30);

        startRefactoringBtn = new Button(processControlContainer, SWT.WRAP);
        startRefactoringBtn.addMouseListener(new StartRefactoringListener(getSplevoProjectEditor()));
        startRefactoringBtn.setText("Start Refactoring");
        startRefactoringBtn.setBounds(532, 58, 93, 45);

        Label activityFlow7 = new Label(processControlContainer, SWT.NONE);
        activityFlow7.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/arrow_right.png"));
        activityFlow7.setAlignment(SWT.CENTER);
        activityFlow7.setBounds(630, 65, 30, 30);

        generateFMBtn = new Button(processControlContainer, SWT.WRAP);
        generateFMBtn.addMouseListener(new GenerateFeatureModelListener(getSplevoProjectEditor()));
        generateFMBtn.setText("Generate Feature Model");
        generateFMBtn.setBounds(670, 58, 118, 45);

    }

    /**
     * Enable Buttons if the information for the action is available.
     */
    public void enableButtonsIfInformationAvailable() {
        disableAllButtonsExceptProjectSelection();

        if (projectsSelected()) {
            diffingBtn.setEnabled(true);
        } else {
            return;
        }

        if (diffModelAvailable()) {
            initVpmBtn.setEnabled(true);
            openDiffBtn.setEnabled(true);
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
     * Check if a diff model is set in the project file and if it can be read.
     *
     * @return true if the diff model is available.
     */
    private boolean diffModelAvailable() {
        SPLevoProject splevoProject = getSPLevoProject();
        String basePath = WorkspaceUtil.getAbsoluteWorkspacePath();
        return splevoProject.getDiffingModelPath() != null
                && new File(basePath + splevoProject.getDiffingModelPath()).canRead();
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
        List<Button> buttons = new ArrayList<Button>();
        buttons.add(diffingBtn);
        buttons.add(openDiffBtn);
        buttons.add(generateFMBtn);
        buttons.add(initVpmBtn);
        buttons.add(analyzeVPMBtn);
        buttons.add(startRefactoringBtn);
        buttons.add(openVPMBtn);

        for (Button button : buttons) {
            button.setEnabled(false);
        }
    }

}
