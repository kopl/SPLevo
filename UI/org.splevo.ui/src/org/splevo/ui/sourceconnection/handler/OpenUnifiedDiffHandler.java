/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.ui.sourceconnection.handler;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.commons.util.WorkspaceUtil;
import org.splevo.ui.editors.UnifiedDiffEditorInput;
import org.splevo.ui.refinementbrowser.VPMRefinementBrowser;
import org.splevo.ui.sourceconnection.UnifiedDiffConnector;
import org.splevo.ui.sourceconnection.UnifiedDiffConnector.DiffMethod;
import org.splevo.ui.util.UIConstants;
import org.splevo.ui.vpexplorer.explorer.VPExplorer;
import org.splevo.vpm.variability.Variant;

/**
 * Handler for opening the unified difference of selected variants.
 */
public class OpenUnifiedDiffHandler extends OpenSourceHandlerBase {
    /** The Logger instance. */
    private static final Logger LOGGER = Logger.getLogger(OpenUnifiedDiffHandler.class);

    /**
     * {@inheritDoc}
     * @author Unknown
     */
    @Override
    protected void handle(Set<Variant> variants, IWorkbenchPart sendingPart) {
        SPLevoProject splevoProject = getSPLevoProjectFromWorkbenchPart(sendingPart);
        if (splevoProject != null) {
            handle(variants, splevoProject);            
        } else {
            LOGGER.warn("Could not get the current SPLevo project!");
        }
    }

    /**
     * Gets the SPLevo project from the given workbench.
     * 
     * @param workbenchPart
     *            the given workbench.
     * @return the SPLevo project.
     * 
     * @author Unknown
     */
    private SPLevoProject getSPLevoProjectFromWorkbenchPart(IWorkbenchPart workbenchPart) {
        if (workbenchPart instanceof VPExplorer) {
            return ((VPExplorer) workbenchPart).getSPLevoProject();
        }
        if (workbenchPart instanceof VPMRefinementBrowser) {
            return ((VPMRefinementBrowser) workbenchPart).getSPLevoProjectEditor().getSplevoProject();
        } else {
            LOGGER.warn("Handler was called from unknown source!");
            return null;
        }
    }
    
    /**
     * Handles the set of variants which have been selected by the user.
     * 
     * @param variants
     *            the set of selected variants.
     * @param splevoProject
     *            the SPLevo project.
     * @author André Wengert
     */
    private void handle(Set<Variant> variants, SPLevoProject splevoProject) {
        // create unified difference workspace (working copy)
        File tmpWorkingCopyFile = null;
        try {
            tmpWorkingCopyFile = createUnifedDiffWorkspace(splevoProject);
            tmpWorkingCopyFile.deleteOnExit();
        } catch (IOException exception) {
            LOGGER.error("An error occured while creating the unified difference workspace!", exception);
        }
        
        // calculate unified difference
        if (tmpWorkingCopyFile != null) {
            UnifiedDiffConnector diffConnector = new UnifiedDiffConnector(splevoProject, variants, tmpWorkingCopyFile);
            diffConnector.calculateDifference(DiffMethod.BY_BLOCKS);
            
            // get IFile for working copy
            IWorkspace workspace = ResourcesPlugin.getWorkspace();
            IPath loc = org.eclipse.core.runtime.Path.fromOSString(tmpWorkingCopyFile.getPath()); 
            IFile ifile = workspace.getRoot().getFileForLocation(loc);
            try {
                // refresh workspace (updates file link)
                ifile.refreshLocal(IResource.DEPTH_ZERO, null);
                
                // open working copy in UnifiedDiffEditor
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .openEditor(new UnifiedDiffEditorInput(ifile, diffConnector), UIConstants.UNIFIED_DIFF_EDITOR_ID);
            } catch (PartInitException exception) {
                LOGGER.error("Error initializing the unified difference editor.", exception);
            } catch (CoreException exception) {
                LOGGER.error("Error refreshing the local project workspace.", exception);
            }
        }
    }

    /**
     * Creates a containing directory and temporary working copy for the given SPLevo project.
     * 
     * @param splevoProject
     *            the given SPLevo project.
     * @return the working copy.
     * @throws IOException
     *             in case an error occured while creating the working directory or the temporary
     *             working copy.
     * @author André Wengert
     */
    private File createUnifedDiffWorkspace(SPLevoProject splevoProject) throws IOException {
        // create folder that contains temporary unified difference files
        String absWorkspacePath = WorkspaceUtil.getAbsoluteFromWorkspaceRelativePath(splevoProject.getWorkspace());
        File directory = new File(absWorkspacePath + "unified difference");
        if (!directory.exists()) {
            if (!directory.mkdir()) {
                throw new IOException("Error creating the temporary working directory at \""
                        + directory.getAbsolutePath() + ".");
            }
        }

        return createUnifiedDiffWorkingCopy(directory);
    }

    /**
     * Creates the temporary working copy within the given directory.
     * 
     * @param directory
     *            the given folder.
     * @return the working copy.
     * @throws IOException
     *             in case an error occurred while writing the file to disk.
     * @author André Wengert
     */
    private File createUnifiedDiffWorkingCopy(File directory) throws IOException {
        int index = 1;
        String tmpFileName = UIConstants.TMP_FILE_NAME_WIHOUT_EXTENTION + UIConstants.TMP_FILE_EXTENTION;
        File tmpFile = new File(combineToPath(directory, tmpFileName));
        if (tmpFile.exists()) {
            do {
                tmpFileName = UIConstants.TMP_FILE_NAME_WIHOUT_EXTENTION + (index++) + UIConstants.TMP_FILE_EXTENTION;
                tmpFile = new File(combineToPath(directory, tmpFileName));
            } while (tmpFile.exists());
        }

        // write file to disk
        tmpFile.createNewFile();
        return tmpFile;
    }
    
    /**
     * Combines the path of a given parenting file and a given child path.
     * 
     * @param parent
     *            the given parent file.
     * @param childPath
     *            the given child path.
     * @return the absolute path of the result.
     * @author André Wengert
     */
    private String combineToPath(File parent, String childPath) {
        File file = new File(parent, childPath);
        return file.getPath();
    }
}
