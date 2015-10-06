package org.splevo.ui.commons.project;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.splevo.project.utils.SPLevoProjectUtil;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Observer for available SPLevoProjects in the Eclipse workspace. If a project is opened/closed,
 * the observer will notify its listeners. It holds a list of available projects with the SPLevo
 * nature.
 */
public class SPLevoProjectWorkspaceObserver {

    private static final Logger LOGGER = Logger.getLogger(SPLevoProjectWorkspaceObserver.class);

    private final IResourceChangeListener projecListener = createProjectOpenCloseListener();
    private final Set<SPLevoProjectWorkspaceListener> subscribers = Sets.newHashSet();
    private final List<IProject> projects = Lists.newArrayList();

    /**
     * Registers a subscriber.
     * @param listener The subscriber to register.
     */
    public void registerSubscriber(SPLevoProjectWorkspaceListener listener) {
        synchronized (subscribers) {
            subscribers.add(listener);
        }
    }

    /**
     * Unregisters a subscriber.
     * @param listener The subscriber to unregister.
     */
    public void unregisterSubscriber(SPLevoProjectWorkspaceListener listener) {
        synchronized (subscribers) {
            subscribers.remove(listener);
        }
    }

    /**
     * Starts the observer.
     */
    public void startObserver() {
        synchronized (projects) {
            projects.clear();
            Iterables.addAll(projects, determineSPLevoProjects());
            ResourcesPlugin.getWorkspace().addResourceChangeListener(projecListener);
        }
    }

    /**
     * Stops the observer.
     */
    public void stopObserver() {
        synchronized (projects) {
            ResourcesPlugin.getWorkspace().removeResourceChangeListener(projecListener);
            projects.clear();
        }
    }

    /**
     * @return The current state of the observer.
     */
    public Iterable<IProject> getCurrentState() {
        synchronized (projects) {
            return projects;
        }
    }

    private IResourceChangeListener createProjectOpenCloseListener() {
        return new IResourceChangeListener() {
            @Override
            public void resourceChanged(IResourceChangeEvent event) {
                if (event.getType() == IResourceChangeEvent.PRE_CLOSE) {
                    handleClosedProject((IProject) event.getResource());
                    return;
                }

                if (event.getType() == IResourceChangeEvent.POST_CHANGE) {
                    try {
                        event.getDelta().accept(new IResourceDeltaVisitor() {
                            @Override
                            public boolean visit(IResourceDelta delta) throws CoreException {
                                if (delta.getResource() instanceof IWorkspaceRoot) {
                                    return true;
                                }

                                if ((delta.getFlags() & IResourceDelta.OPEN) != 0
                                        && delta.getResource() instanceof IProject) {
                                    IProject project = (IProject) delta.getResource();
                                    if (project.isOpen()) {
                                        handleOpenedProject(project);
                                    }
                                }

                                return false;
                            }

                        });
                    } catch (CoreException e) {
                        LOGGER.warn(
                                "Error processing a resource change. The VPM loading composite might not be up to date anymore.",
                                e);
                    }
                }
            }
        };
    }

    private void handleOpenedProject(final IProject project) {
        synchronized (projects) {
            if (SPLevoProjectUtil.isSPLevoProject(project)) {
                projects.add(project);
                notifySubscribers();
            }
        }
    }

    private void handleClosedProject(final IProject project) {
        synchronized (projects) {
            if (projects.remove(project)) {
                notifySubscribers();
            }
        }
    }

    private void notifySubscribers() {
        synchronized (subscribers) {
            for (SPLevoProjectWorkspaceListener subscriber : subscribers) {
                subscriber.availableProjectFilesChanged(this);
            }
        }
    }

    private Iterable<IProject> determineSPLevoProjects() {
        return SPLevoProjectUtil.findAllSPLevoProjectsInWorkspace(true);
    }


}
