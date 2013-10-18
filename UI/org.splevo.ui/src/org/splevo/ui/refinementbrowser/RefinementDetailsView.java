package org.splevo.ui.refinementbrowser;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Details view to show information about a currently selected refinement.
 * 
 * @author Benjamin Klatt
 * 
 */
public class RefinementDetailsView extends Composite {

    /** The internal tree viewer to present the refined variation points. */
    private TreeViewer variationPointTreeViewer = null;

    /**
     * Constructor to create the view.
     * 
     * @param parent
     *            The parent ui element to present the view in.
     */
    public RefinementDetailsView(Composite parent) {
        super(parent, SWT.FILL);
        setLayout(new FillLayout(SWT.HORIZONTAL));

        variationPointTreeViewer = new TreeViewer(this, SWT.BORDER);
        variationPointTreeViewer.setLabelProvider(new ViewerLabelProvider());
        variationPointTreeViewer.setContentProvider(new TreeContentProvider());

        initContextMenu();
    }

    /**
     * Create contents of the details page.
     * 
     * @param parent
     *            The parent ui element to create the view in.
     */
    public void createContents(Composite parent) {
        parent.setEnabled(true);

    }

    /**
     * Trigger the presentation of a specific refinement in the view.
     * 
     * @param refinement
     *            The refinement to show.
     */
    public void showRefinement(Refinement refinement) {
        variationPointTreeViewer.setInput(refinement);
    }

    /**
     * The content provider for the tree providing access to the variation points and their child
     * elements.
     */
    private static class TreeContentProvider implements ITreeContentProvider {

        /** The refinement to display in the tree. */
        private Refinement refinement = null;

        @Override
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            this.refinement = (Refinement) newInput;
        }

        @Override
        public Object[] getElements(Object inputElement) {
            return refinement.getVariationPoints().toArray();
        }

        @Override
        public Object[] getChildren(Object parentElement) {
            if (parentElement instanceof Refinement) {
                return ((Refinement) parentElement).getVariationPoints().toArray();
            } else if (parentElement instanceof VariationPoint) {
                return ((VariationPoint) parentElement).getVariants().toArray();
            } else if (parentElement instanceof Variant) {
                return ((Variant) parentElement).getSoftwareEntities().toArray();
            } else {
                return new Object[] {};
            }
        }

        @Override
        public Object getParent(Object element) {
            return null;
        }

        @Override
        public boolean hasChildren(Object element) {
            return getChildren(element).length > 0;
        }

        @Override
        public void dispose() {
        }
    }

    /**
     * Label Provider for a variation point element.
     */
    private static class ViewerLabelProvider extends LabelProvider {

        /**
         * Get the text label for a supplied element. Adapted to interpret the information of the
         * specific type (VariationPoint, Variant, etc.)
         * 
         * {@inheritDoc}
         */
        @Override
        public String getText(Object element) {
            if (element instanceof VariationPoint) {
                return buildVariationPointLabel((VariationPoint) element);
            }

            if (element instanceof Variant) {
                return "Variant: " + ((Variant) element).getVariantId();
            }

            if (element instanceof SoftwareElement) {
                return ((SoftwareElement) element).getLabel();
            }

            // default label
            return super.getText(element);
        }

        /**
         * Builds the variation point label.
         * 
         * @param variationPoint
         *            the element
         * @return the string
         */
        private String buildVariationPointLabel(VariationPoint variationPoint) {
            SoftwareElement softwareElement = variationPoint.getEnclosingSoftwareEntity();
            return String.format("VariationPoint in %s", softwareElement.getLabel());
        }
    }

    /**
     * initialize the context menu.
     */
    private void initContextMenu() {

        MenuManager menuMgr = new MenuManager();
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(new IMenuListener() {

            @Override
            public void menuAboutToShow(IMenuManager manager) {

                // trigger the action to navigate to the source
                // location and open the Java Editor at the according location
                Action action = new OpenSourceInEditorAction(variationPointTreeViewer);
                manager.add(action);
            }
        });
        Menu menu = menuMgr.createContextMenu(variationPointTreeViewer.getTree());
        variationPointTreeViewer.getTree().setMenu(menu);

    }
}
