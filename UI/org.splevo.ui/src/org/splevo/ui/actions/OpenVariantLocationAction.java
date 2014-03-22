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
package org.splevo.ui.actions;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IEditorPart;
import org.splevo.vpm.software.JavaSoftwareElement;
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
        for (SoftwareElement softwareElement : selectedElement.getImplementingElements()) {
            if (softwareElement instanceof JavaSoftwareElement) {
                IEditorPart editor = javaEditorConnector.openEditor((JavaSoftwareElement) softwareElement);
                javaEditorConnector.highlightInTextEditor(editor, softwareElement, softwareElement.getLabel());
            }
        }
    }
}
