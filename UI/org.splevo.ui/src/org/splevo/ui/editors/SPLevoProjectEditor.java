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
package org.splevo.ui.editors;

import java.io.File;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.splevo.project.SPLevoProject;
import org.splevo.project.SPLevoProjectUtil;
import org.splevo.ui.dashboard.ConfigurationTab;
import org.splevo.ui.dashboard.ProcessControlTab;
import org.splevo.ui.dashboard.ProjectSelectionTab;
import org.splevo.ui.dashboard.SPLProfileTab;

/**
 * The SPLevo dash board to control the consolidation process as well as editing the SPLevo project
 * configuration.
 */
public class SPLevoProjectEditor extends EditorPart {

    /** The id of the editor. */
    public static final String ID = "org.splevo.ui.editors.SPLevoProjectEditor"; //$NON-NLS-1$

    public static final int TABINDEX_PROCESS_CONTROL = 0;
    public static final int TABINDEX_PROJECT_SELECTION = TABINDEX_PROCESS_CONTROL + 1;
    public static final int TABINDEX_CONFIGURATION = TABINDEX_PROJECT_SELECTION + 1;
    public static final int TABINDEX_SPL_PROFILE = TABINDEX_CONFIGURATION + 1;

    /** The splevo project manipulated in the editor instance. */
    private SPLevoProject splevoProject = null;

    /** The tab to control the consolidation process. */
    private ProcessControlTab processControlTab;

    /** Flag identifying the dirty state of the editor. */
    private boolean dirtyFlag = false;

    /**
     * Default constructor setting the icon in the editor title.
     */
    public SPLevoProjectEditor() {
        setTitleImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/splevo.gif"));
    }

    @Override
    public void createPartControl(Composite parent) {
        parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        GridLayout glParent = new GridLayout(1, false);
        glParent.verticalSpacing = 0;
        glParent.marginWidth = 0;
        glParent.marginHeight = 0;
        glParent.horizontalSpacing = 0;
        parent.setLayout(glParent);

        // init the data tables
        TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
        tabFolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        GridData gdTabFolder = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        gdTabFolder.widthHint = 837;
        gdTabFolder.heightHint = 353;
        tabFolder.setLayoutData(gdTabFolder);

        // create tabs
        processControlTab = new ProcessControlTab(this, tabFolder, TABINDEX_PROCESS_CONTROL);
        ProjectSelectionTab projectSelectionTab = new ProjectSelectionTab(this, tabFolder, TABINDEX_PROJECT_SELECTION);
        new ConfigurationTab(this, tabFolder, TABINDEX_CONFIGURATION);
        new SPLProfileTab(tabFolder, TABINDEX_SPL_PROFILE);

        projectSelectionTab.initDataBindings();

        processControlTab.enableButtonsIfInformationAvailable();

    }

    @Override
    public void setFocus() {
        // Set the focus
    }

    /**
     * Get the editor input resource. This methods overrides the super types method to return a more
     * specific IFileEditorInput.
     *
     * @return The editor input converted to an IFileEditorInput or null if not possible.
     */
    @Override
    public IFileEditorInput getEditorInput() {
        if (super.getEditorInput() instanceof IFileEditorInput) {
            return (IFileEditorInput) super.getEditorInput();
        }
        return null;
    }

    /**
     * Save the project configuration to the currently edited file.
     *
     * @param monitor
     *            The progress monitor to report to.
     */
    @Override
    public void doSave(IProgressMonitor monitor) {

        Shell shell = getEditorSite().getShell();

        // check workspace setting
        if (getSplevoProject().getWorkspace() == null || getSplevoProject().getWorkspace().equals("")) {
            MessageDialog.openError(shell, "Workspace Missing",
                    "You need to specify a workspace directory for the project.");
            return;
        }
        File filePath = getCurrentFilePath();
        try {
            SPLevoProjectUtil.save(splevoProject, filePath);
        } catch (Exception e) {
            MessageDialog.openError(shell, "Save error", "Unable to save the project file to " + filePath);
            e.printStackTrace();
        }

        dirtyFlag = false;
        firePropertyChange(IEditorPart.PROP_DIRTY);
    }

    /**
     * Get the absolute path of the current editor input file.
     *
     * @return The file path derived from the editor input.
     */
    private File getCurrentFilePath() {
        FileEditorInput fileInput = (FileEditorInput) getEditorInput();
        String filePath = fileInput.getFile().getFullPath().toOSString();
        return new File(filePath);
    }

    @Override
    public void doSaveAs() {
        // do save as is not supported
    }

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {

        setSite(site);
        setInput(input);

        if (input instanceof IFileEditorInput) {
            IFileEditorInput fileInput = (IFileEditorInput) input;
            if (fileInput.getName().endsWith(SPLevoProjectUtil.SPLEVO_FILE_EXTENSION)) {
                File projectFile = new File(fileInput.getFile().getFullPath().toString());

                try {
                    this.splevoProject = SPLevoProjectUtil.loadSPLevoProjectModel(projectFile);
                } catch (Exception e) {
                    throw new PartInitException("Unable to load SPLevo project file in editor", e);
                }
            }
        }
    }

    /**
     * @return the splevoProject
     */
    public SPLevoProject getSplevoProject() {
        return splevoProject;
    }

    @Override
    public boolean isDirty() {
        return dirtyFlag;
    }

    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

    /**
     * Mark the editor as dirty.
     */
    public void markAsDirty() {
        dirtyFlag = true;
        firePropertyChange(IEditorPart.PROP_DIRTY);
    }

    /**
     * Update the ui and present the submitted message in an information dialog. If the provided
     * message is null, no dialog will be opened.
     *
     * @param message
     *            The optional message to be presented.
     */
    public void updateUI(String message) {
        updateUI();
        if (message != null) {
            Shell shell = getEditorSite().getShell();
            MessageDialog.openInformation(shell, "SPLevo Info", message);
        }
    }

    /**
     * Update the user interface.
     */
    public void updateUI() {

        markAsDirty();
        doSave(new NullProgressMonitor());

        processControlTab.enableButtonsIfInformationAvailable();

    }
}
