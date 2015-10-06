package org.splevo.ui.commons.project;

/**
 * Base interface for listeners that are notified about availability changes of SPLevoProjects in
 * the workspace.
 */
public interface SPLevoProjectWorkspaceListener {

    /**
     * Called if the available project files changed. The available projects can be determined from
     * the given observer.
     * 
     * @param obserable
     *            The observable that called the listener.
     */
    void availableProjectFilesChanged(SPLevoProjectWorkspaceObserver obserable);

}
