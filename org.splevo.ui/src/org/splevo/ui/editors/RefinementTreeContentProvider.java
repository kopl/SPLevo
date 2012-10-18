package org.splevo.ui.editors;

import java.util.HashMap;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.splevo.ui.workflow.VPMAnalysisConfiguration;
import org.splevo.vpm.refinement.Refinement;

public class RefinementTreeContentProvider implements ITreeContentProvider {

	/** The refinements to be performed. */
	HashMap<VPMAnalysisConfiguration, List<Refinement>> refinements = new HashMap<VPMAnalysisConfiguration, List<Refinement>>();

	@Override
	public void dispose() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.refinements = (HashMap<VPMAnalysisConfiguration, List<Refinement>>) newInput;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return refinements.keySet().toArray();
	}

	/**
	 * Return the child elements of the selected tree node.
	 * First check the type of the selected element, 
	 * then return the type specific children.
	 */
	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof VPMAnalysisConfiguration) {
			List<Refinement> selectedRefinements = refinements.get((VPMAnalysisConfiguration) parentElement);
			return selectedRefinements.toArray();
		} else if (parentElement instanceof Refinement) {
			return ((Refinement) parentElement).getVariationPoints().toArray();
	    }
		return null;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof VPMAnalysisConfiguration) {
			return !refinements.get(element).isEmpty();
		} else if (element instanceof Refinement) {
			return !((Refinement) element).getVariationPoints().isEmpty();
		}
		return false;
	}

}
