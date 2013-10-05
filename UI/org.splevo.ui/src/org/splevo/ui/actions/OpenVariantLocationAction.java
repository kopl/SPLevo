package org.splevo.ui.actions;

import org.apache.log4j.Logger;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.jface.action.IAction;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.splevo.modisco.java.vpm.software.MoDiscoJavaSoftwareElement;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;

/**
 * Action to open the implementing locations of a selected variant in the java editor.
 * 
 * @author Benjamin Klatt
 */
public class OpenVariantLocationAction extends AbstractOpenSourceLocationAction<Variant> {

    /** The logger for this class. */
    @SuppressWarnings("unused")
    private Logger logger = Logger.getLogger(OpenVariantLocationAction.class);

    /**
     * Get the model of the selected variant according to it's type (leading or integration) and
     * trigger the java editor connector for each implementing element. {@inheritDoc}
     */
    @Override
    public void run(IAction action) {

        // TODO Refactor to work with JavaSoftwareElements in general
        for (SoftwareElement softwareElement : selectedElement.getSoftwareEntities()) {
            if (softwareElement instanceof MoDiscoJavaSoftwareElement) {
                ASTNode locationNode = ((MoDiscoJavaSoftwareElement) softwareElement).getAstNode();
                JavaApplication javaApplication = getJavaModel(selectedElement);
                javaEditorConnector.openEditor(locationNode, javaApplication);
            }
        }
    }
}
