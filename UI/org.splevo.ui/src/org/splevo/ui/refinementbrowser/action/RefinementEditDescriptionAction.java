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
import org.splevo.ui.commons.wizard.EObjectEAttributeWrapper;
import org.splevo.ui.commons.wizard.WizardRunner;
import org.splevo.ui.commons.wizard.description.EditCustomizableDescriptionWizard;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.variability.variabilityPackage;

/**
 * Description editing action for a refinement in the refinement browser.
 */
public class RefinementEditDescriptionAction extends RefinementBrowserSingleElementAction {

    private static final String LABEL = "Edit Description";

    /**
     * Constructs a new description editing action for a refinement.
     * 
     * @param treeViewer
     *            The tree viewer containing the refinements.
     */
    public RefinementEditDescriptionAction(TreeViewer treeViewer) {
        super(LABEL, treeViewer);
    }

    @Override
    protected boolean isCorrectlyTyped(Object element) {
        // we cannot use CustomizableDescriptionHaving here because we do not want
        // edit operations on all of these files (some of them are lost during the
        // refinement).
        return element instanceof Refinement;
    }

    @Override
    protected boolean perform(Object element) {
        Refinement refinement = (Refinement) element;
        EObjectEAttributeWrapper<String> wrapper = new EObjectEAttributeWrapper<String>("Refinement", refinement,
                variabilityPackage.eINSTANCE.getCustomizableDescriptionHaving_Description());
        EditCustomizableDescriptionWizard wizard = new EditCustomizableDescriptionWizard(wrapper, false);
        return WizardRunner.run(wizard);
    }

}
