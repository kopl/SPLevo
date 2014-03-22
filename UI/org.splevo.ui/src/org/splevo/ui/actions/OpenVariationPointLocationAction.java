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
        SoftwareElement softwareElement = selectedElement.getLocation();
        if (softwareElement instanceof JavaSoftwareElement) {
            IEditorPart editor = javaEditorConnector.openEditor((JavaSoftwareElement) softwareElement);
            javaEditorConnector.highlightInTextEditor(editor, softwareElement, softwareElement.getLabel());
        }
    }
}
