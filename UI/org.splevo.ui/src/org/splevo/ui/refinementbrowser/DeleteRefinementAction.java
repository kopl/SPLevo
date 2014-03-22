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
package org.splevo.ui.refinementbrowser;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;
import org.splevo.vpm.refinement.Refinement;

/**
 * This is the action for the context-menu in the refinement browser to delete
 * one ore more refinements from the list.
 * 
 * @author Daniel Kojic
 * 
 */
public class DeleteRefinementAction extends Action {

	/**
	 * The label of the menu entry.
	 */
	private static final String TEXT = "Delete";
	
	/**
	 * The corresponding {@link TreeViewer} that has the refinements.
	 */
	private TreeViewer treeViewer;
	
	/**
	 * The {@link TreeViewer} that has the details view.
	 */
	private RefinementDetailsView detailsView;

	/**
	 * Initializes this {@link Action} with two {@link TreeViewer}s. One that has the refinements, the other that has the details.
	 * 
	 * @param treeViewer The refinement {@link TreeViewer}
	 * @param detailsView The details {@link TreeViewer}.
	 */
	public DeleteRefinementAction(TreeViewer treeViewer,
			RefinementDetailsView detailsView) {
		this.treeViewer = treeViewer;
		this.detailsView = detailsView;
	}

	@Override
	public String getText() {
		return TEXT;
	}

	@Override
	public boolean isEnabled() {
		if (treeViewer.getTree().getSelection().length < 1) {
			return false;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		List<Refinement> input = (List<Refinement>) treeViewer.getInput();
		TreeItem[] selection = treeViewer.getTree().getSelection();
		for (TreeItem treeItem : selection) {
			Refinement refinement = (Refinement) treeItem.getData();
			input.remove(refinement);
		}
		treeViewer.refresh();
		detailsView.setEnabled(false);
	}
}
