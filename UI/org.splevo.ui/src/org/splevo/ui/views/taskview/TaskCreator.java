package org.splevo.ui.views.taskview;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.splevo.refactoring.TodoTagCustomizer;
import org.splevo.refactoring.TodoTagCustomizerRegistry;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

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
		
		Iterable<String> todoTags = Iterables.concat(Iterables.transform(TodoTagCustomizerRegistry.getInstance().getElements(), new Function<TodoTagCustomizer, Iterable<String>>() {
            @Override
            public Iterable<String> apply(TodoTagCustomizer input) {
                return input.getTodoTags();
            }}));
		
		for (IMarker toDoTask : toDoTasks) {
			final String toDoTaskDescritpion = (String) toDoTask.getAttribute(IMarker.MESSAGE);
			
			Optional<String> todoTag = Iterables.tryFind(todoTags, new Predicate<String>() {
                @Override
                public boolean apply(String input) {
                    return toDoTaskDescritpion.contains(input);
                }});
			
			if (todoTag.isPresent()) {
			    String variationPointID = toDoTaskDescritpion.replace(todoTag.get() + " ", "");
				tasks.add(new Task(variationPointID, 
						           toDoTask.getResource().getName(), 
						           toDoTask.getResource().getFullPath().toString(), 
						           (Integer) toDoTask.getAttribute(IMarker.LINE_NUMBER)));
			}
		}
		
		return tasks.toArray(new Task[tasks.size()]);
	}
	
}

