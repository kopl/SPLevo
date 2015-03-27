/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.ui.commons.wizard;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * Utility class to run wizards.
 */
public final class WizardRunner {

    private WizardRunner() {
        
    }
    
    /**
     * Runs a given wizard.
     * 
     * @param wizard
     *            The wizard to run.
     * @return If the wizard is configured to block after opening, the result represents the return
     *         code of the wizard. Otherwise it determines whether the wizard has been opened
     *         successfully.
     */
    public static boolean run(Wizard wizard) {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        WizardDialog wizardDialog = new WizardDialog(shell, wizard);
        return wizardDialog.open() == Window.OK;
    }

}
