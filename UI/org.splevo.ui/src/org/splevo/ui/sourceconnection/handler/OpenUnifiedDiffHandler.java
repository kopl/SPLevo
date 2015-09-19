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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.commons.util.WorkspaceUtil;
import org.splevo.ui.editors.UnifiedDiffEditorInput;
import org.splevo.ui.refinementbrowser.VPMRefinementBrowser;
import org.splevo.ui.sourceconnection.UnifiedDiffConnector;
import org.splevo.ui.sourceconnection.UnifiedDiffConnector.DiffMethod;
import org.splevo.ui.util.CollectionUtil;
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
     * Handles the set of variants which have been selected by the user to calculate the unifed
     * difference.
     * 
     * @param variants
     *            the set of selected variants.
     * @param splevoProject
     *            the SPLevo project.
     * @author André Wengert
     */
    private void handle(Set<Variant> variantsOfInterest, SPLevoProject splevoProject) {
        // process each selected variant
        List<String> fileNames = extractNamesOfFilesToProcess(variantsOfInterest);
        for (String fileName : fileNames) {
            // create unified difference workspace (working copy)
            File tmpWorkingCopyFile = null;
            try {
                tmpWorkingCopyFile = createUnifedDiffWorkspace(fileName, splevoProject);
                tmpWorkingCopyFile.deleteOnExit();
            } catch (IOException exception) {
                LOGGER.error("An error occured while creating the unified difference workspace!", exception);
            }
            
            // calculate unified difference
            Set<Variant> variantsToConsider = extractVariantsToConsider(fileName, variantsOfInterest);
            if (tmpWorkingCopyFile != null) {
                UnifiedDiffConnector diffConnector = new UnifiedDiffConnector(splevoProject, variantsToConsider,
                        tmpWorkingCopyFile, fileName);
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
    }


    /**
     * Creates a containing directory and temporary working copy for the given SPLevo project.
     * 
     * @param fileName
     *            the file name to process.
     * @param splevoProject
     *            the given SPLevo project.
     * @return the working copy.
     * @throws IOException
     *             in case an error occurred while creating the working directory or the temporary
     *             working copy.
     * @author André Wengert
     */
    private File createUnifedDiffWorkspace(String fileName, SPLevoProject splevoProject) throws IOException {
        // create folder that contains temporary unified difference files
        String absWorkspacePath = WorkspaceUtil.getAbsoluteFromWorkspaceRelativePath(splevoProject.getWorkspace());
        File directory = new File(absWorkspacePath + "unified difference");
        if (!directory.exists()) {
            if (!directory.mkdir()) {
                throw new IOException("Error creating the temporary working directory at \""
                        + directory.getAbsolutePath() + ".");
            }
        }

        return createUnifiedDiffWorkingCopy(fileName, directory);
    }

    /**
     * Creates the temporary working copy for the given file name within the given directory.
     * 
     * @param fileName
     *            the file name to process.
     * @param directory
     *            the given folder.
     * @return the working copy.
     * @throws IOException
     *             in case an error occurred while writing the file to disk.
     * @author André Wengert
     */
    private File createUnifiedDiffWorkingCopy(String fileName, File directory) throws IOException {
        int index = 1;
        String tmpFileName = UIConstants.TMP_FILE_NAME_WIHOUT_EXTENTION + fileName + UIConstants.TMP_FILE_EXTENTION;
        File tmpFile = new File(combineToPath(directory, tmpFileName));
        if (tmpFile.exists()) {
            do {
                tmpFileName = UIConstants.TMP_FILE_NAME_WIHOUT_EXTENTION + fileName + (index++) + UIConstants.TMP_FILE_EXTENTION;
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

    /**
     * Extracts the file names which are to be processed from the given variants.
     * 
     * @param variants
     *            the given variants.
     * @return the file names.
     */
    private List<String> extractNamesOfFilesToProcess(Set<Variant> variants) {
        List<String> fileNames = new ArrayList<String>();
        for (Variant variant : variants) {
            String fileName = FilenameUtils.getBaseName(getFilePathFor(variant));
            if (!CollectionUtil.containsString(fileName, fileNames)) {
                fileNames.add(fileName);
            }
        }
        return fileNames;
    }

    /**
     * Extracts all variants that correspond to the given file name.
     * 
     * @param fileName
     *            the given file name.
     * @param variants
     *            the source variants.
     * @return the varaints to consider.
     */
    private Set<Variant> extractVariantsToConsider(String fileName, Set<Variant> variants) {
        Set<Variant> variantsToConsider = new HashSet<Variant>();
        for (Variant variant : variants) {
            String variantFileName = FilenameUtils.getBaseName(getFilePathFor(variant));
            if (variantFileName.equals(fileName)) {
                variantsToConsider.add(variant);
            }
        }
        return variantsToConsider;
    }
    
    /**
     * Gets the enveloping file for the given variant.
     * 
     * @param variant
     *            the given variant.
     * @return the file path of the enveloping file.
     */
    private String getFilePathFor(Variant variant) {
        EObject locElement = variant.getVariationPoint().getLocation().getWrappedElement();
        return locElement.eResource().getURI().toFileString();
    }
}
