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
package org.splevo.ui.refinementbrowser.action;

import org.eclipse.jface.viewers.TreeViewer;
import org.splevo.ui.commons.wizard.WizardRunner;
import org.splevo.ui.commons.wizard.rename.RenameEObjectEAttributeWrapper;
import org.splevo.ui.commons.wizard.rename.RenameElementWizard;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementPackage;

/**
 * Rename action for a refinement in the refinement browser.
 */
public class RenameRefinementAction extends RefinementBrowserRenameAction {

    /**
     * Constructs a new rename action for a refinement.
     * @param treeViewer The tree viewer containing the refinements.
     */
    public RenameRefinementAction(TreeViewer treeViewer) {
        super(treeViewer);
    }

    @Override
    protected boolean isCorrectlyTyped(Object element) {
        return element instanceof Refinement;
    }

    @Override
    protected boolean renameElement(Object element) {
        Refinement refinement = (Refinement) element;
        RenameEObjectEAttributeWrapper wrapper = new RenameEObjectEAttributeWrapper(
                "Refinement", refinement, RefinementPackage.eINSTANCE.getRefinement_Id());
        RenameElementWizard wizard = new RenameElementWizard(wrapper, false);
        return WizardRunner.run(wizard);
    }

}
