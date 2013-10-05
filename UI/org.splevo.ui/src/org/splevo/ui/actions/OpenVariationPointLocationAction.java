package org.splevo.ui.actions;

import org.apache.log4j.Logger;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.jface.action.IAction;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.splevo.modisco.java.vpm.software.MoDiscoJavaSoftwareElement;
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

        // TODO Refactor to work with JavaSoftwareElements in general
        SoftwareElement softwareElement = selectedElement.getEnclosingSoftwareEntity();
        if (softwareElement instanceof MoDiscoJavaSoftwareElement) {
            ASTNode locationNode = ((MoDiscoJavaSoftwareElement) softwareElement).getAstNode();
            JavaApplication javaApplication = getJavaModel(selectedElement);
            javaEditorConnector.openEditor(locationNode, javaApplication);
        }
    }
}
