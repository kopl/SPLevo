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
 *******************************************************************************/
package org.splevo.ui.vpexplorer.handler.vpmedit;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.splevo.ui.vpexplorer.wizards.rename.RenameVPGroupWizard;
import org.splevo.vpm.variability.VariationPointGroup;

/**
 * Handler to rename the selected VariationPointGroup.
 * 
 */
public class RenameVariationPointGroupHandler extends AbstractHandler {

    private static Logger logger = Logger.getLogger(RenameVariationPointGroupHandler.class);

    /**
     * Renames the selected VariationPointGroup and saves the model.
     * 
     * {@inheritDoc}
     */
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

        if (strucSelection.getFirstElement() instanceof VariationPointGroup) {
            logger.debug("Selection is of right type!");
            VariationPointGroup selectedGroup = (VariationPointGroup) strucSelection.getFirstElement();
            RenameVPGroupWizard groupRenameWizard = new RenameVPGroupWizard(selectedGroup);
            Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

            WizardDialog wizardDialog = new WizardDialog(shell, groupRenameWizard);

            if (wizardDialog.open() != Window.OK) {
                logger.debug("Variation Point Group rename canceled");
            }

        }

        return null;
    }
}