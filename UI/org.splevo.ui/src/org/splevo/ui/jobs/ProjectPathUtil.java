package org.splevo.ui.jobs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;

public class ProjectPathUtil {

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
