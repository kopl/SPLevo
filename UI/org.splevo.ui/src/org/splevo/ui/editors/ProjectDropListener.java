package org.splevo.ui.editors;

import java.io.File;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.widgets.Text;

/**
 * Realizes the drop mechanism to add projects to the project list. If the variant name is empty, it
 * will be filled with the name of the first project.
 * 
 * @see ProjectDropEvent
 */
public class ProjectDropListener extends ViewerDropAdapter {

    /** The SPLevo project to access the data in. */
    private List<String> projectListContainer;

    /** The input field for the variant name. */
    private Text inputVariantName;

    /**
     * Constructor to link the listener with the viewer and the dashboard to modify.
     * 
     * @param viewer
     *            The viewer to listen to.
     * @param projectListContainer
     *            The names of the projects to add to the viewer.
     * @param inputVariantName
     *            The input for the variant name to be filled if empty before.
     */
    public ProjectDropListener(Viewer viewer, List<String> projectListContainer, Text inputVariantName) {
        super(viewer);
        this.projectListContainer = projectListContainer;
        this.inputVariantName = inputVariantName;
    }

    @Override
    public boolean performDrop(Object data) {

        if (data instanceof String[]) {
            for (String fqnPath : ((String[]) data)) {
                String projectName = new File(fqnPath).getName();
                IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
                if (project != null) {
                    String name = project.getName();
                    if (!projectListContainer.contains(project.getName())) {
                        projectListContainer.add(name);
                    }
                    if (inputVariantName.getText() == null || inputVariantName.getText().equals("")) {
                        inputVariantName.setText(name);
                    }
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
