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
package org.splevo.ui.sourceconnection.handler;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.splevo.ui.sourceconnection.jdt.JavaEditorConnector;
import org.splevo.vpm.software.JavaSoftwareElement;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Action to open the location of a selected variation point in the java editor.
 */
public class OpenVariationPointLocationHandler extends AbstractHandler {

    /** The logger for this class. */
    @SuppressWarnings("unused")
    private Logger logger = Logger.getLogger(OpenVariationPointLocationHandler.class);

    /** The connector to the java editor. */
    protected JavaEditorConnector javaEditorConnector = new JavaEditorConnector();

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection curSelection = HandlerUtil.getCurrentSelection(event);
        if (curSelection != null & curSelection instanceof IStructuredSelection) {
            IStructuredSelection selection = (IStructuredSelection) curSelection;
            if (selection.getFirstElement() instanceof VariationPoint) {
                openVariationPoint((VariationPoint) selection.getFirstElement());
            }
        }
        return null;
    }

    /**
     * Open the location of a variationPoint.
     *
     * @param variationPoint
     *            The variationPoint to show the code for.
     */
    private void openVariationPoint(VariationPoint variationPoint) {
        SoftwareElement softwareElement = variationPoint.getLocation();
        if (softwareElement instanceof JavaSoftwareElement) {
            IEditorPart editor = javaEditorConnector.openEditor((JavaSoftwareElement) softwareElement);
            javaEditorConnector.highlightInTextEditor(editor, softwareElement, softwareElement.getLabel());
        }
    }
}
