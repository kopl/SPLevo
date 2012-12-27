package org.splevo.ui.listeners;

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
 */
public class ProjectDropListener extends ViewerDropAdapter {

    /** The splevo project to access the data in */
    private List<String> projectListContainer;

    /**
     * Constructor to link the listener with the viewer and the dashboard to modify.
     * @param viewer The viewer to listen to.
     * @param splEvoDashboardView The dashboard to modify.
     */
    public ProjectDropListener(Viewer viewer, List<String> projectListContainer) {
        super(viewer);
        this.projectListContainer = projectListContainer;
    }

    @Override
    /**
     * Only files ending with java2kdm.xmi are added to the the model list
     */
    public boolean performDrop(Object data) {
    	
    	if (data instanceof String[]) {
    		for (String fqnPath : ((String[]) data)) {
        		String projectName = new File(fqnPath).getName();
        		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
        		if(project != null && !projectListContainer.contains(project.getName())){
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
