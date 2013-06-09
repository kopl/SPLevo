package org.splevo.ui.actions;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.splevo.ui.jdt.JavaEditorConnector;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * The abstract super class for actions to open a source location in the java editor.
 * 
 * @param <T>
 *            The class specifying the element type the action is able to open the Java editor for.
 * 
 * @author Benjamin Klatt
 * 
 */
public abstract class AbstractOpenSourceLocationAction<T extends Object> implements IObjectActionDelegate {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(AbstractOpenSourceLocationAction.class);

    /** The connector to the java editor. */
    protected JavaEditorConnector javaEditorConnector = new JavaEditorConnector();

    /** The selected element to open the source code location for. */
    protected T selectedElement = null;

    /**
     * Check if the selected item is a variation point. If yes: save the selection reference. If
     * not: clean the old selection reference.
     * 
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        selectedElement = null;
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            if (!structuredSelection.isEmpty()) {
                try {
                    selectedElement = (T) structuredSelection.getFirstElement();
                } catch (ClassCastException cce) {
                    logger.error("Valid element selection not ensured by extension point configuration: "
                            + cce.getMessage());
                }
            }
        }
    }

    /**
     * Get the leading java model for a variation point.
     * 
     * @param vp
     *            The variation point to get the java model for.
     * @return The identified java application model.
     */
    protected JavaApplication getJavaModel(VariationPoint vp) {
        return vp.getGroup().getModel().getIntegrationModel();
    }

    /**
     * Get the java application model for a variant. This takes the variant (integration or leading)
     * of the variant into account.
     * 
     * @param variant
     *            The variant to get the model for.
     * @return The identified model including the inventory information.
     */
    protected JavaApplication getJavaModel(Variant variant) {
        VariationPointModel vpm = variant.getVariationPoint().getGroup().getModel();
        if (variant.getLeading()) {
            return vpm.getLeadingModel();
        } else {
            return vpm.getIntegrationModel();
        }
    }

    @Override
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        // no active part required.
    }

}