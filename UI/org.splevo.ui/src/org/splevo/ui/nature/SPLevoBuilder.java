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
package org.splevo.ui.nature;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.project.SPLevoProject;
import org.splevo.project.SPLevoProjectUtil;

/**
 * Builder for the SPLevo project nature.
 *
 * The builder uses internal visitors to react on incremental and full builds.
 *
 * Basic checks for project validity are performed.
 */
public class SPLevoBuilder extends IncrementalProjectBuilder {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(SPLevoBuilder.class);

    /** The id of the builder. */
    public static final String BUILDER_ID = "org.splevo.ui.splevobuilder";

    /** The builder specific marker type id. */
    private static final String MARKER_TYPE = "org.splevo.ui.splevoConfigurationProblem";

    /**
     * Create a marker for a specific file.
     *
     * @param file
     *            The file to mark.
     * @param message
     *            The error message.
     * @param lineNumber
     *            The line to set the marker at.
     * @param severity
     *            The severitiy of the error.
     */
    private void addMarker(IFile file, String message, int lineNumber, int severity) {
        try {
            IMarker marker = file.createMarker(MARKER_TYPE);
            marker.setAttribute(IMarker.MESSAGE, message);
            marker.setAttribute(IMarker.SEVERITY, severity);
            if (lineNumber == -1) {
                lineNumber = 1;
            }
            marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
        } catch (CoreException e) {
            logger.warn("Failed to add a marker.", e);
        }
    }

    /**
     * Build the project. {@inheritDoc}
     */
    @SuppressWarnings("rawtypes")
    protected IProject[] build(int kind, Map args, IProgressMonitor monitor) throws CoreException {
        if (kind == FULL_BUILD) {
            fullBuild(monitor);
        } else {
            IResourceDelta delta = getDelta(getProject());
            if (delta == null) {
                fullBuild(monitor);
            } else {
                incrementalBuild(delta, monitor);
            }
        }
        return null;
    }

    /***
     * Check a resource of being a valid SPLevo project file.
     *
     * @param resource
     *            The resource to check.
     */
    private void checkProjectSettings(IResource resource) {
        if (resource instanceof IFile && resource.getName().endsWith(SPLevoProjectUtil.SPLEVO_FILE_EXTENSION)) {
            IFile file = (IFile) resource;
            deleteMarkers(file);
            SPLevoProject splEvoProject = null;
            try {
                splEvoProject = SPLevoProjectUtil.loadSPLevoProjectModel(new File(resource.getFullPath().toString()));
            } catch (IOException e) {
                logger.warn("Failed to check project settings.", e);
            } finally {
                if (splEvoProject == null) {
                    addMarker(file, "Project configuration file could not be loaded", -1, IMarker.SEVERITY_ERROR);
                }
            }
        }
    }

    /**
     * Reset the markers of the file.
     *
     * @param file
     *            The file to reset the markers for
     */
    private void deleteMarkers(IFile file) {
        try {
            file.deleteMarkers(MARKER_TYPE, false, IResource.DEPTH_ZERO);
        } catch (CoreException ce) {
            logger.warn("Builder failed to delete a marker.", ce);
        }
    }

    /**
     * Perform a full build of the project.
     *
     * @param monitor
     *            The monitor to report the progress to.
     * @throws CoreException
     *             Any exception thrown during the build.
     */
    protected void fullBuild(final IProgressMonitor monitor) throws CoreException {
        try {
            getProject().accept(new SPLevoProjectFileResourceVisitor());
        } catch (CoreException e) {
            logger.warn("Builder failed to perform a full build.", e);
        }
    }

    /**
     * Perform an incremental build of the project.
     *
     * @param delta
     *            The modified resource
     * @param monitor
     *            The monitor to report the progress to.
     * @throws CoreException
     *             Any exception thrown during the build.
     */
    protected void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor) throws CoreException {
        // the visitor does the work.
        delta.accept(new SPLevoProjectFileDeltaVisitor());
    }

    /**
     * Visitor for incremental builds of a project with SPLevo nature.
     */
    class SPLevoProjectFileDeltaVisitor implements IResourceDeltaVisitor {
        /**
         * {@inheritDoc}
         */
        public boolean visit(IResourceDelta delta) throws CoreException {
            IResource resource = delta.getResource();
            switch (delta.getKind()) {
            case IResourceDelta.ADDED:
                // handle added resource
                checkProjectSettings(resource);
                break;
            case IResourceDelta.REMOVED:
                // handle removed resource
                break;
            case IResourceDelta.CHANGED:
                // handle changed resource
                checkProjectSettings(resource);
                break;
            default:
                break;
            }
            // return true to continue visiting children.
            return true;
        }
    }

    /**
     * Visitor for full builds.
     */
    class SPLevoProjectFileResourceVisitor implements IResourceVisitor {

        @Override
        public boolean visit(IResource resource) {
            checkProjectSettings(resource);
            // return true to continue visiting children.
            return true;
        }
    }

}
