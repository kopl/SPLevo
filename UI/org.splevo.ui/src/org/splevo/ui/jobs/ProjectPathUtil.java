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
package org.splevo.ui.jobs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;

/**
 * Utility class for common project paths handling.
 */
public final class ProjectPathUtil {

    /** Disable constructor for utility class. */
    private ProjectPathUtil() {
    }

    /**
     * Build the absolute URIs for a list of projects identified by their names.
     *
     * @param projectNames
     *            The project names.
     * @return The list of URIs identifying the projects.
     */
    public static List<String> buildProjectPaths(List<String> projectNames) {
        List<String> projectURIs = new ArrayList<String>();
        for (String projectName : projectNames) {
            String path = buildProjectPath(projectName);
            projectURIs.add(path);
        }
        return projectURIs;
    }

    /**
     * Build the absolute URI for a project identified by its name.
     *
     * @param projectName
     *            The project name.
     * @return The URI identifying the project.
     */
    public static String buildProjectPath(String projectName) {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IProject project = root.getProject(projectName);
        String path = project.getLocation().toPortableString();
        return path;
    }

}
