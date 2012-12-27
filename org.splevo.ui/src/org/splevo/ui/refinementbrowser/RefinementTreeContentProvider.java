package org.splevo.ui.refinementbrowser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.splevo.vpm.refinement.Refinement;

/**
 * The Content provider for the refinement tree.
 * @author benjamin
 *
 */
public class RefinementTreeContentProvider implements ITreeContentProvider {

	/** The refinements to be performed. */
	private List<Refinement> refinements = new ArrayList<Refinement>();

	@Override
	public void dispose() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.refinements = (List<Refinement>) newInput;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return refinements.toArray();
	}

	/**
	 * Return the child elements of the selected tree node.
	 * First check the type of the selected element, 
	 * then return the type specific children.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Object[] getChildren(Object parentElement) {
// TODO: cleanup when ui is tested
//		if (parentElement instanceof VPMRefinementAnalyzer) {
//			List<Refinement> selectedRefinements = refinements.get((VPMRefinementAnalyzer) parentElement);
//			return selectedRefinements.toArray();
//		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
// TODO: cleanup when ui is tested
//		if (element instanceof VPMRefinementAnalyzer) {
//			return !refinements.get(element).isEmpty();
//		}
		return false;
	}

}
