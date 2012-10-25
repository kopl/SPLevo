package org.splevo.ui.editors;

import java.util.HashMap;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.VPMRefinementAnalyzer;

public class RefinementTreeContentProvider implements ITreeContentProvider {

	/** The refinements to be performed. */
	HashMap<VPMRefinementAnalyzer, List<Refinement>> refinements = new HashMap<VPMRefinementAnalyzer, List<Refinement>>();

	@Override
	public void dispose() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.refinements = (HashMap<VPMRefinementAnalyzer, List<Refinement>>) newInput;
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
		if (parentElement instanceof VPMRefinementAnalyzer) {
			List<Refinement> selectedRefinements = refinements.get((VPMRefinementAnalyzer) parentElement);
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
		if (element instanceof VPMRefinementAnalyzer) {
			return !refinements.get(element).isEmpty();
		} else if (element instanceof Refinement) {
			return !((Refinement) element).getVariationPoints().isEmpty();
		}
		return false;
	}

}
