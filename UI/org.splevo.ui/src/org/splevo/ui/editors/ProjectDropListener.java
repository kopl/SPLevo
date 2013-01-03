package org.splevo.ui.editors;

import java.io.File;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.TransferData;

/**
 * Realizes the drop mechanism to add projects to the project list.
 * 
 * @see ProjectDropEvent
 */
public class ProjectDropListener extends ViewerDropAdapter {

    /** The SPLevo project to access the data in. */
    private List<String> projectListContainer;

    /**
     * Constructor to link the listener with the viewer and the dashboard to modify.
     * 
     * @param viewer
     *            The viewer to listen to.
     * @param projectListContainer
     *            The names of the projects to add to the viewer.
     */
    public ProjectDropListener(Viewer viewer, List<String> projectListContainer) {
        super(viewer);
        this.projectListContainer = projectListContainer;
    }

    @Override
    public boolean performDrop(Object data) {

        if (data instanceof String[]) {
            for (String fqnPath : ((String[]) data)) {
                String projectName = new File(fqnPath).getName();
                IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
                if (project != null && !projectListContainer.contains(project.getName())) {
                    String name = project.getName();
                    projectListContainer.add(name);
                }

            }
        }

        getViewer().setInput(projectListContainer);

        return true;
    }
    
    @Override
    public boolean validateDrop(Object target, int operation, TransferData transferType) {
        return true;

    }
}
