/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christian Busch
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.ui.vpexplorer.handler.vpmedit;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Handler to rename the selected variation point model element.
 * 
 */
public abstract class RenameVariationPointElementHandler extends AbstractHandler {
    
    private static Logger logger = Logger.getLogger(RenameVariationPointElementHandler.class);
    
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        
        IStructuredSelection strucSelection;

        ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (selection != null && selection instanceof IStructuredSelection) {
            strucSelection = (IStructuredSelection) selection;
        } else {
            logger.debug("Wrong type of selection!");
            return null; // wrong selection type
        }

        if (strucSelection.toList().size() != 1) {
            logger.debug("Wrong number of selected elements! Expected 1 got " + strucSelection.toList().size() + ".");
            return null; // only one element may be selected
        }

        Wizard vpRenameWizard = createWizardFor(strucSelection.getFirstElement());
        if (vpRenameWizard == null) {
            return null;
        }
        logger.debug("Selection is of right type!");
        
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        WizardDialog wizardDialog = new WizardDialog(shell, vpRenameWizard);
        if (wizardDialog.open() != Window.OK) {
            logger.debug("Variation Point rename canceled");
        }

        return null;
    }
    
    /**
     * Creates a wizard for the given object.
     * @param selectedObject The object to change.
     * @return The wizard or null if the given object has no supported type.
     */
    protected abstract Wizard createWizardFor(Object selectedObject);
    
}
