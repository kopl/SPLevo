package org.splevo.ui.views.taskview;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.splevo.jamopp.refactoring.JaMoPPTodoTagCustomizer;

/**
 * With TaskCreater can tasks created and filtered with respect to the toDoTaskTag.
 */
public class TaskCreator {	
	
	/**
	 * Default constructor.
	 */
	public TaskCreator() {

	}
	
	/**
     * Create and filter tasks with respect to the toDoTaskTag.
     *
     * @return tasks
     * @throws CoreException
     * 			contain a status object describing the cause of the exception.
     */
	public Task[] getTasks() throws CoreException {
		List<Task> tasks = new ArrayList<Task>();
		
		IResource resource = ResourcesPlugin.getWorkspace().getRoot();
		
		IMarker[] toDoTasks = resource.findMarkers(IMarker.TASK, true, IResource.DEPTH_INFINITE);
		
		for (IMarker toDoTask:toDoTasks) {
			String toDoTaskDescritpion = (String) toDoTask.getAttribute(IMarker.MESSAGE);
			
			if (toDoTaskDescritpion.contains(JaMoPPTodoTagCustomizer.getTodoTaskTag())) {
				tasks.add(new Task(toDoTaskDescritpion, 
						           toDoTask.getResource().getName(), 
						           toDoTask.getResource().getFullPath().toString(), 
						           (Integer) toDoTask.getAttribute(IMarker.LINE_NUMBER)));
			}
		}
		
		return tasks.toArray(new Task[tasks.size()]);
	}
	
}

