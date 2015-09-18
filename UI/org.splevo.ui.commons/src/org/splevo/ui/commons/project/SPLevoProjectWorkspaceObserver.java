package org.splevo.ui.commons.project;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.splevo.project.SPLevoProjectUtil;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;


public class SPLevoProjectWorkspaceObserver {

    private static final Logger LOGGER = Logger.getLogger(SPLevoProjectWorkspaceObserver.class);
    
    private final IResourceChangeListener projecListener = createProjectOpenCloseListener();
    private final Set<SPLevoProjectWorkspaceListener> subscribers = Sets.newHashSet();
    private final List<IFile> projectFiles = Lists.newArrayList();
    
    public void registerSubscriber(SPLevoProjectWorkspaceListener listener) {
        subscribers.add(listener);
    }
    
    public void unregisterSubscriber(SPLevoProjectWorkspaceListener listener) {
        subscribers.remove(listener);
    }
    
    public void startObserver() {
        projectFiles.clear();
        Iterables.addAll(projectFiles, determineSPLevoProjectFiles());
        ResourcesPlugin.getWorkspace().addResourceChangeListener(projecListener);
    }

    public void stopObserver() {
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(projecListener);
        projectFiles.clear();
    }
    
    public Iterable<IFile> getCurrentState() {
        return projectFiles;
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

        IFile projectFile = SPLevoProjectUtil.getSPLevoProjectModelFromProject(project);
        if (projectFile != null) {
            projectFiles.add(projectFile);
            notifySubscribers();
        }
    }
    
    private void handleClosedProject(final IProject project) {
        if (Iterables.removeIf(projectFiles, new Predicate<IFile>() {
            @Override
            public boolean apply(IFile input) {
                return project.equals(input.getProject());
            }
        })) {
            notifySubscribers();            
        }
    }
    
    private void notifySubscribers() {
        for (SPLevoProjectWorkspaceListener subscriber : subscribers) {
            subscriber.availableProjectFilesChanged(this);
        }
    }
    
    private Iterable<IFile> determineSPLevoProjectFiles() {
        return SPLevoProjectUtil.findAllSPLevoProjectFilesInWorkspace(true);
    }
    
}
