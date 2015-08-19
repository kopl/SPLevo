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
import org.splevo.ui.editors.UnifiedDiffEditor;
import org.splevo.ui.editors.UnifiedDiffEditorInput;
import org.splevo.ui.refinementbrowser.VPMRefinementBrowser;
import org.splevo.ui.sourceconnection.UnifiedDiffConnector;
import org.splevo.ui.sourceconnection.UnifiedDiffConnector.ConnectionMethod;
import org.splevo.ui.vpexplorer.explorer.VPExplorer;
import org.splevo.vpm.variability.Variant;

/**
 * Handler for opening the unified diff of selected variants.
 */
public class OpenUnifiedDiffHandler extends OpenSourceHandlerBase {
    /** Logger instance. */
    private static final Logger LOGGER = Logger.getLogger(OpenUnifiedDiffHandler.class);

    @Override
    protected void handle(Set<Variant> variants, IWorkbenchPart sendingPart) {
        SPLevoProject splevoProject = getSPLevoProjectFromWorkbenchPart(sendingPart);
        if (splevoProject != null) {
            handle(variants, splevoProject);            
        }
    }

    private void handle(Set<Variant> variants, SPLevoProject splevoProject) {
        // create unified difference with UnifiedDiffConnector
        File tmpFile = createUnifedDiffWorkspace(splevoProject);
        UnifiedDiffConnector diffConnector = new UnifiedDiffConnector(splevoProject, variants, tmpFile);
        diffConnector.connect(ConnectionMethod.BY_CHUNKS);
        
        // get IFile for working copy
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IPath loc = org.eclipse.core.runtime.Path.fromOSString(tmpFile.getPath()); 
        IFile ifile = workspace.getRoot().getFileForLocation(loc);
        try {
            // refresh workspace (updates file link)
            ifile.refreshLocal(IResource.DEPTH_ZERO, null);
            // open working copy in UnifiedDiffEditor
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
            .openEditor(new UnifiedDiffEditorInput(ifile, diffConnector), UnifiedDiffEditor.ID);
        } catch (PartInitException e) {
            LOGGER.error("Error initializing the unified difference editor.", e);
        } catch (CoreException e) {
            LOGGER.error("Error refreshing the local project workspace.", e);
        }
    }

    /**
     * Creates working copy within a temporary workspace for the given SPLevo project.
     * 
     * @param splevoProject
     *            the given SPLevo project.
     * @return a reference to the working copy.
     */
    private File createUnifedDiffWorkspace(SPLevoProject splevoProject) {
        // create folder that contains temporary unified difference files
        String absWorkspacePath = WorkspaceUtil.getAbsoluteFromWorkspaceRelativePath(splevoProject.getWorkspace());
        File tmpFilesFolder = new File(absWorkspacePath + "unified difference");
        if (!tmpFilesFolder.exists()) {
            if (!tmpFilesFolder.mkdir()) {
                LOGGER.warn("Create temporary working folder at \"" + tmpFilesFolder.getAbsolutePath()
                        + "\" was not successful.");
            }
        }
        
        return createUnifiedDiffWorkingCopy(tmpFilesFolder);
    }

    /**
     * Creates the unified difference working copy within the given folder.
     * 
     * @param folder
     *            the given folder.
     * @return a reference to the working copy.
     */
    private File createUnifiedDiffWorkingCopy(File folder) {
        // create virtual temporary unified difference file
        int index = 1;
        String extention = ".java"; // MUST be *.java or else java syntax highlighting won't work
        String tmpFileName = "unifiedDiff" + extention;
        File tmpFile = new File(combineToPath(folder, tmpFileName));

        // create physical temporary unified difference file
        if (tmpFile.exists()) {
            do {
                tmpFileName = "unifiedDiff" + (index++) + extention;
                tmpFile = new File(combineToPath(folder, tmpFileName));
            } while (tmpFile.exists());
        }
        try {
            if (!tmpFile.createNewFile()) {
                LOGGER.warn("Create temporary working copy at \"" + tmpFile.getAbsolutePath()
                        + "\" was not successful.");
            }

        } catch (IOException e) {
            LOGGER.error("Could not create temporary working copy at \"" + tmpFile.getAbsolutePath() + "\"", e);
        }

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
     */
    private String combineToPath(File parent, String childPath) {
        File file = new File(parent, childPath);
        return file.getPath();
    }
    
    private static SPLevoProject getSPLevoProjectFromWorkbenchPart(IWorkbenchPart workbenchPart) {
        if (workbenchPart instanceof VPExplorer) {
            return ((VPExplorer) workbenchPart).getSPLevoProject();
        }
        if (workbenchPart instanceof VPMRefinementBrowser) {
            return ((VPMRefinementBrowser) workbenchPart).getSPLevoProjectEditor().getSplevoProject();
        } else {
            LOGGER.warn("Got called from unknown source. Developers have to fix this.");
            return null;
        }
    }
}
