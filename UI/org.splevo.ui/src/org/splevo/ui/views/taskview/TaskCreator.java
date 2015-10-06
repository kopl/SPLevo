package org.splevo.ui.views.taskview;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.splevo.refactoring.TodoTagCustomizer;
import org.splevo.refactoring.TodoTagCustomizerRegistry;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * With TaskCreater can tasks created and filtered with respect to the toDoTaskTag.
 */
public class TaskCreator {	

	/**
     * Create and filter tasks with respect to the toDoTaskTag.
	 * @param relevantProjects 
     *
     * @return tasks
     * @throws CoreException
     * 			contain a status object describing the cause of the exception.
     */
	public Task[] getTasks(final Iterable<IProject> relevantProjects) throws CoreException {
		List<Task> tasks = Lists.newArrayList();
		
        final Iterable<String> todoTags = Iterables.concat(Iterables.transform(TodoTagCustomizerRegistry.getInstance()
                .getElements(), new Function<TodoTagCustomizer, Iterable<String>>() {
            @Override
            public Iterable<String> apply(TodoTagCustomizer input) {
                return input.getTodoTags();
            }
        }));
        
        for (IProject relevantProject : relevantProjects) {
            Iterable<IMarker> toDoTasks = Lists.newArrayList(relevantProject.findMarkers(IMarker.TASK, true,
                    IResource.DEPTH_INFINITE));
            for (IMarker toDoTask : toDoTasks) {
                final String toDoTaskDescritpion = (String) toDoTask.getAttribute(IMarker.MESSAGE);
                
                Optional<String> todoTag = Iterables.tryFind(todoTags, new Predicate<String>() {
                    @Override
                    public boolean apply(String input) {
                        return toDoTaskDescritpion.contains(input);
                    } });
                
                if (todoTag.isPresent()) {
                    String variationPointID = toDoTaskDescritpion.replace(todoTag.get() + " ", "");
                    tasks.add(new Task(variationPointID, 
                                       toDoTask.getResource().getName(), 
                                       toDoTask.getResource().getFullPath().toString(), 
                                       (Integer) toDoTask.getAttribute(IMarker.LINE_NUMBER)));
                }
            }
        }

		return tasks.toArray(new Task[tasks.size()]);
	}
	
}

