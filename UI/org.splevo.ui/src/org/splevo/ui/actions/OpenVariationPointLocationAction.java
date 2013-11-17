package org.splevo.ui.actions;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IEditorPart;
import org.splevo.vpm.software.JavaSoftwareElement;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Action to open the location of a selected variation point in the java editor.
 * 
 * @author Benjamin Klatt
 */
public class OpenVariationPointLocationAction  extends AbstractOpenSourceLocationAction<VariationPoint> {

    /** The logger for this class. */
    @SuppressWarnings("unused")
    private Logger logger = Logger.getLogger(OpenVariationPointLocationAction.class);

    @Override
    public void run(IAction action) {
        SoftwareElement softwareElement = selectedElement.getEnclosingSoftwareEntity();
        if (softwareElement instanceof JavaSoftwareElement) {
            IEditorPart editor = javaEditorConnector.openEditor((JavaSoftwareElement) softwareElement);
            javaEditorConnector.highlightInTextEditor(editor, softwareElement, softwareElement.getLabel());
        }
    }
}
