/*******************************************************************************
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.ui.editors.listener;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.splevo.ui.editors.SPLevoProjectEditor;

/**
 * Realizes the drop mechanism to add projects to the project list. If the variant name is empty, it
 * will be filled with the name of the first project.
 * 
 * @see ProjectDropEvent
 */
public class ProjectDropListener extends ViewerDropAdapter {
	
	private Logger logger = Logger.getLogger(ProjectDropListener.class);

    /** The SPLevo project to access the data in. */
    private List<String> projectListContainer;

    /** The input field for the variant name. */
    private Text inputVariantName;

    /** The SPLevo Project Editor. */
	private SPLevoProjectEditor spLevoProjectEditor;

    /**
     * Constructor to link the listener with the viewer and the dashboard to modify.
     * @param spLevoProjectEditor 
     * 
     * @param viewer
     *            The viewer to listen to.
     * @param projectListContainer
     *            The names of the projects to add to the viewer.
     * @param inputVariantName
     *            The input for the variant name to be filled if empty before.
     */
    public ProjectDropListener(SPLevoProjectEditor spLevoProjectEditor, Viewer viewer, List<String> projectListContainer, Text inputVariantName) {
        super(viewer);
		this.spLevoProjectEditor = spLevoProjectEditor;
        this.projectListContainer = projectListContainer;
        this.inputVariantName = inputVariantName;
    }

	@Override
	public boolean performDrop(Object selection) {

		List<IJavaProject> javaProjects = getJavaProjectsFromSelection(selection);
		
		if (javaProjects.size() > 0) {
			addProjectsToTheListContainer(javaProjects);
			getViewer().setInput(projectListContainer);
			spLevoProjectEditor.updateUI();
			return true;
		}

		Shell shell = spLevoProjectEditor.getEditorSite().getShell();
		MessageDialog.openWarning(shell, "Cannot be added to list.",
				"Only Java Projects can be added to the list.");
		return false;
	}

	/**
	 * Add Java projects to the list container.
	 * @param javaProjects the Java Projects
	 */
	private void addProjectsToTheListContainer(List<IJavaProject> javaProjects) {
		for (IJavaProject javaProject : javaProjects) {
			addProjectToListAndSetVariantName(javaProject.getElementName());
		}
	}

	/**
	 * Get the java projects from a selection.
	 * @param selectionObject the selection object
	 * @return  the java projects from a selection
	 */
	private List<IJavaProject> getJavaProjectsFromSelection(Object selectionObject) {
		
		List<IJavaProject> javaProjects = new ArrayList<IJavaProject>();
		
		if (selectionObject instanceof TreeSelection) {
			addJavaProjectsFromSelectionToList(javaProjects, (TreeSelection) selectionObject);
		}
		
		return javaProjects;
	}

	/**
	 * Add Java Projects from the selection to the list.
	 * @param javaProjects the list of projects to that the projects from the selection are added
	 * @param selection the selection
	 */
	
	private void addJavaProjectsFromSelectionToList(
			List<IJavaProject> javaProjects, TreeSelection selection) {
		
		for (Object selectedElement : selection.toList()) {
		
			if (selectedElement instanceof IJavaProject) {
				IJavaProject project = (IJavaProject) selectedElement;
				javaProjects.add(project);
			}
			
			if (selectedElement instanceof IProject) {
				IProject project = (IProject) selectedElement;
				try {
					IProjectNature nature = project.getNature(JavaCore.NATURE_ID);
					if (isNatureNotNullAndInstanceOfIJavaProject(nature)) {
						javaProjects.add((IJavaProject) nature);
					}
				} catch (CoreException e) {
					logger.warn("Project could not be converted into a JavaProject so drop failed.");
				}
			}
		}
	}


	/**
	 * Returns if the nature is not null and instance of IJavaProject.
	 * @param nature the nature of a project
	 * @return whether the nature is not null and instance of IJavaProject.
	 */
	private boolean isNatureNotNullAndInstanceOfIJavaProject(IProjectNature nature) {
		return nature != null && nature instanceof IJavaProject;
	}

    /**
     * Adds the name of the project to the list (if not already contained) and
     * if sets the variant name (if not already set).
     * @param name the name of the project
     */
	private void addProjectToListAndSetVariantName(String name) {
		if (!projectListContainer.contains(name)) {
			projectListContainer.add(name);
		}
		if (inputVariantName.getText() == null
				|| inputVariantName.getText().equals("")) {
			inputVariantName.setText(name);
		}
	}

    @Override
    public boolean validateDrop(Object target, int operation, TransferData transferType) {
        return true;

    }
}
