package org.splevo.ui.views.taskview;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;
import org.splevo.jamopp.refactoring.JaMoPPTodoTagCustomizer;

public class TaskCreator {	
	public TaskCreator() {

	}
	
	public Task[] getTasks() throws CoreException {
		List<Task> tasks = new ArrayList<Task>();
		
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IResource resource = workspace.getRoot();
		
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

