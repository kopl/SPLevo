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
import org.splevo.ui.listeners.GenerateFeatureModelListener;
import org.splevo.ui.listeners.InitVPMListener;
import org.splevo.ui.listeners.OpenVPEditorListener;
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

        Composite processControlContainer = new Composite(tabFolder, SWT.NONE);
        tbtmProcessControl.setControl(processControlContainer);

        Label lblSplevoDashboard = new Label(processControlContainer, SWT.NONE);
        lblSplevoDashboard.setAlignment(SWT.CENTER);
        lblSplevoDashboard.setFont(SWTResourceManager.getFont("Arial", 14, SWT.BOLD));
        lblSplevoDashboard.setBounds(0, 10, 550, 30);
        lblSplevoDashboard.setText("SPLevo Dashboard");

        int xOffset = 20;
        int offsetActivityStart = 24;
        int offset = 5;
        int offsetFlow = 30 + offset;
        int indentSubButton = 22;

        Label activityStart = new Label(processControlContainer, SWT.NONE);
        activityStart.setAlignment(SWT.CENTER);
        activityStart.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/bullet_green.png"));
        activityStart.setBounds(xOffset, 66, 30, 30);
        xOffset += offsetActivityStart;

        addActivityFlowButton(processControlContainer, xOffset);
        xOffset += offsetFlow;

        initVpmBtn = new Button(processControlContainer, SWT.WRAP);
        initVpmBtn.addMouseListener(new InitVPMListener(getSplevoProjectEditor()));
        initVpmBtn.setText("Init VPM");
        initVpmBtn.setBounds(xOffset, 58, 72, 45);
        xOffset += initVpmBtn.getBounds().width + offset;

        addActivityFlowButton(processControlContainer, xOffset);
        xOffset += offsetFlow;

        analyzeVPMBtn = new Button(processControlContainer, SWT.WRAP);
        analyzeVPMBtn.addMouseListener(new VPMAnalysisListener(getSplevoProjectEditor()));
        final Image circleImage = ResourceManager.getPluginImage("org.splevo.ui", "icons/arrow_circle.png");
        analyzeVPMBtn.addPaintListener(new PaintListener() {
            public void paintControl(PaintEvent e) {
                e.gc.drawImage(circleImage, 51, 24);
            }
        });
        analyzeVPMBtn.setText("Refine VPM");
        analyzeVPMBtn.setBounds(xOffset, 58, 72, 45);

        openVPMBtn = new Button(processControlContainer, SWT.NONE);
        openVPMBtn.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/page_white_go.png"));
        openVPMBtn.setBounds(xOffset + indentSubButton, 109, 26, 30);
        openVPMBtn.addMouseListener(new OpenVPEditorListener(getSplevoProjectEditor()));

        xOffset += analyzeVPMBtn.getBounds().width + offset;

        addActivityFlowButton(processControlContainer, xOffset);
        xOffset += offsetFlow;

        startRefactoringBtn = new Button(processControlContainer, SWT.WRAP);
        startRefactoringBtn.addMouseListener(new StartRefactoringListener(getSplevoProjectEditor()));
        startRefactoringBtn.setText("Refactor Copies");
        startRefactoringBtn.setBounds(xOffset, 58, 72, 45);
        xOffset += startRefactoringBtn.getBounds().width + offset;

        addActivityFlowButton(processControlContainer, xOffset);
        xOffset += offsetFlow;

        generateFMBtn = new Button(processControlContainer, SWT.WRAP);
        generateFMBtn.addMouseListener(new GenerateFeatureModelListener(getSplevoProjectEditor()));
        generateFMBtn.setText("Export SPL");
        generateFMBtn.setBounds(xOffset, 58, 72, 45);
        xOffset += generateFMBtn.getBounds().width + offset;

    }

    private void addActivityFlowButton(Composite container, int xOffset) {
        Label iconLabel = new Label(container, SWT.NONE);
        iconLabel.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/arrow_right.png"));
        iconLabel.setAlignment(SWT.CENTER);
        iconLabel.setBounds(xOffset, 66, 30, 30);
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
        List<Button> buttons = new ArrayList<Button>();
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
