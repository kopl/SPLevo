/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.ui.commons.util;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.splevo.project.SPLevoProject;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * Utility class to interact with the SPLevo workspace.
 */
public final class WorkspaceUtil {

    /** Disable constructor for utility class. */
    private WorkspaceUtil() {
    }

    /**
     * Constructs an absolute path based on a given project relative path. The project's path is
     * determined by the given splevoProject. The result is a portable string, so you have to
     * convert it before showing it to the user. Anyway, the output is just fine for initializing a
     * File object.
     * 
     * @param projectRelativePath
     *            The path relative to the SPLevo project given as another parameter.
     * @param splevoProject
     *            The SPLevo project defining the project.
     * @return The absolute path to the file as portable string.
     */
    public static String getAbsoluteFromProjectRelativePath(String projectRelativePath, SPLevoProject splevoProject) {
        String workspaceRelativePath = String
                .format("%s%s%s", StringUtils.removeEndIgnoreCase(splevoProject.getWorkspace(), "/"),
                        projectRelativePath.startsWith("/") ? "" : "/", projectRelativePath);

        return getAbsoluteFromWorkspaceRelativePath(workspaceRelativePath);
    }

    /**
     * Constructs an absolute path based on a given workspace relative path. The result is a
     * portable string, so you have to convert it before showing it to the user. Anyway, the output
     * is just fine for initializing a File object.
     * 
     * @param relativePath
     *            The path relative to the workspace. A workspace relative path starts with the
     *            project name and contains the full path to the wanted file afterwards.
     * @return The absolute path to the file as portable string.
     */
    public static String getAbsoluteFromWorkspaceRelativePath(String relativePath) {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        return String.format("%s%s%s", root.getLocation().toPortableString(), relativePath.startsWith("/") ? "" : "/",
                relativePath);
    }

    // TODO check to use an absolute base path for the splevo project workspace.
    /**
     * Get the absolute URI of a variants source model assuming the workspace is located within the
     * eclipse workspace.
     * 
     * @param splevoProject
     *            The splevo project defining the workspace.
     * @param variantName
     *            The variant to get the source model path for.
     * @return The absolute URI describing the workspace.
     */
    public static String getSourceModelPathWithinEclipse(SPLevoProject splevoProject, String variantName) {
        return getAbsoluteFromProjectRelativePath("models/sourcemodels/" + variantName, splevoProject);
    }

    /**
     * Determine the absolute OS specific path of the workspace.
     * 
     * @return The absolute path.
     */
    public static String getAbsoluteWorkspacePath() {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        String basePath = workspace.getRoot().getRawLocation().toOSString() + "/";
        return basePath;
    }

    /**
     * Maps a list of given project names to a list of projects. If a project with a given name does
     * not exist, it will be omitted in the output.
     * 
     * @param projectNames
     *            The names of the projects.
     * @return The workspace projects matching the given names.
     */
    public static Iterable<IProject> transformProjectNamesToProjects(Iterable<String> projectNames) {
        Iterable<IProject> projects = Iterables.transform(projectNames, new Function<String, IProject>() {
            @Override
            public IProject apply(String input) {
                return ResourcesPlugin.getWorkspace().getRoot().getProject(input);
            }
        });
        return Iterables.filter(projects, Predicates.notNull());
    }

}
