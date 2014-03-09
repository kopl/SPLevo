package org.splevo.ui.refinementbrowser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * The Content provider for the refinement tree.
 * 
 * @author Christian Busch - based heavily on work of Benjamin Klatt
 * 
 */
public class CompleteRefinementTreeContentProvider implements ITreeContentProvider {

    /** The refinements to be performed. */
    private List<Refinement> refinements = new ArrayList<Refinement>();

    @Override
    public void dispose() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
        this.refinements = (List<Refinement>) newInput;
    }

    @Override
    public Object[] getElements(final Object inputElement) {
        return this.refinements.toArray();
    }

    /**
     * Return the child elements of the selected tree node. First check the type of the selected
     * element, then return the type specific children.
     * 
     * {@inheritDoc}
     */
    @Override
    public Object[] getChildren(final Object parentElement) {
        if (parentElement instanceof Refinement) {
            return ((Refinement) parentElement).getVariationPoints().toArray();
        } else if (parentElement instanceof VariationPoint) {
            return ((VariationPoint) parentElement).getVariants().toArray();
        } else if (parentElement instanceof Variant) {
            return ((Variant) parentElement).getImplementingElements().toArray();
        } else {
            return new Object[] {};
        }
    }

    @Override
    public Object getParent(final Object element) {
        return null;
    }

    @Override
    public boolean hasChildren(final Object element) {
        // TODO: cleanup when ui is tested
        // if (element instanceof VPMRefinementAnalyzer) {
        // return !refinements.get(element).isEmpty();
        // }
        if (element instanceof Refinement || element instanceof VariationPoint) {
            return true;
        }
        return false;
    }

}
