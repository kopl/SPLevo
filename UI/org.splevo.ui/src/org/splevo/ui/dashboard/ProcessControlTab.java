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

import org.apache.log4j.Logger;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.editors.listener.GotoTabMouseListener;
import org.splevo.ui.listeners.DiffSourceModelListener;
import org.splevo.ui.listeners.GenerateFeatureModelListener;
import org.splevo.ui.listeners.InitVPMListener;
import org.splevo.ui.listeners.OpenVPEditorListener;
import org.splevo.ui.listeners.VPMAnalysisListener;
import org.splevo.ui.util.WorkspaceUtil;

/**
 * The tab to control the consolidation process.
 */
public class ProcessControlTab {

    private static Logger logger = Logger.getLogger(ProcessControlTab.class);

    private SPLevoProjectEditor splevoProjectEditor;

    /** Button Diffing. */
    private Button diffingBtn;

    /** Button Init VPM. */
    private Button initVpmBtn;

    /** Button Analyze VPM. */
    private Button analyzeVPMBtn;

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
        this.splevoProjectEditor = splevoProjectEditor;
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
        selectProjectBtn.addMouseListener(new GotoTabMouseListener(tabFolder, SPLevoProjectEditor.TABINDEX_PROJECT_SELECTION));
        selectProjectBtn.setBounds(75, 59, 78, 45);
        selectProjectBtn.setText("Project Selection");

        Label activityFlow3 = new Label(processControlContainer, SWT.NONE);
        activityFlow3.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/arrow_right.png"));
        activityFlow3.setAlignment(SWT.CENTER);
        activityFlow3.setBounds(159, 66, 30, 30);

        diffingBtn = new Button(processControlContainer, SWT.WRAP);
        diffingBtn.addMouseListener(new DiffSourceModelListener(splevoProjectEditor));
        diffingBtn.setBounds(195, 58, 72, 45);
        diffingBtn.setText("Diffing");

        Label activityFlow4 = new Label(processControlContainer, SWT.NONE);
        activityFlow4.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/arrow_right.png"));
        activityFlow4.setAlignment(SWT.CENTER);
        activityFlow4.setBounds(273, 65, 30, 30);

        initVpmBtn = new Button(processControlContainer, SWT.WRAP);
        initVpmBtn.addMouseListener(new InitVPMListener(splevoProjectEditor));
        initVpmBtn.setBounds(309, 58, 72, 45);
        initVpmBtn.setText("Init VPM");

        Label activityFlow5 = new Label(processControlContainer, SWT.NONE);
        activityFlow5.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/arrow_right.png"));
        activityFlow5.setAlignment(SWT.CENTER);
        activityFlow5.setBounds(387, 65, 30, 30);

        analyzeVPMBtn = new Button(processControlContainer, SWT.WRAP);
        analyzeVPMBtn.addMouseListener(new VPMAnalysisListener(splevoProjectEditor));
        analyzeVPMBtn.setText("Analyze VPM");
        analyzeVPMBtn.setBounds(419, 58, 72, 45);

        Label label = new Label(processControlContainer, SWT.NONE);
        label.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/arrow_right.png"));
        label.setAlignment(SWT.CENTER);
        label.setBounds(497, 65, 30, 30);

        generateFMBtn = new Button(processControlContainer, SWT.WRAP);
        generateFMBtn.addMouseListener(new GenerateFeatureModelListener(splevoProjectEditor));
        generateFMBtn.setText("Generate Feature Model");
        generateFMBtn.setBounds(528, 58, 118, 45);

        openDiffBtn = new Button(processControlContainer, SWT.NONE);
        openDiffBtn.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/page_white_go.png"));
        openDiffBtn.setBounds(221, 109, 26, 30);
        openDiffBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseUp(MouseEvent e) {
                String diffingModelPath = splevoProjectEditor.getSplevoProject().getDiffingModelPath();
                if (diffingModelPath != null && diffingModelPath.length() > 0) {
                    String basePath = WorkspaceUtil.getAbsoluteWorkspacePath();
                    File fileToOpen = new File(basePath + File.separator + diffingModelPath);
                    IFileStore fileStore = EFS.getLocalFileSystem().getStore(fileToOpen.toURI());
                    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

                    try {
                        IDE.openEditorOnFileStore(page, fileStore);
                    } catch (PartInitException pie) {
                        logger.error("failed to open diff file.");
                    }
                }
            }
        });

        openVPMBtn = new Button(processControlContainer, SWT.NONE);
        openVPMBtn.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/page_white_go.png"));
        openVPMBtn.setBounds(331, 109, 26, 30);
        openVPMBtn.addMouseListener(new OpenVPEditorListener(splevoProjectEditor));

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
        SPLevoProject splevoProject = splevoProjectEditor.getSplevoProject();
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
        SPLevoProject splevoProject = splevoProjectEditor.getSplevoProject();
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
        SPLevoProject splevoProject = splevoProjectEditor.getSplevoProject();
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
        buttons.add(openVPMBtn);

        for (Button button : buttons) {
            button.setEnabled(false);
        }
    }

}
