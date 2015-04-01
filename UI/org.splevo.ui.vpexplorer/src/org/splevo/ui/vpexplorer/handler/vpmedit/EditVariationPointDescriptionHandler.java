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
package org.splevo.ui.vpexplorer.handler.vpmedit;

import org.eclipse.jface.wizard.Wizard;
import org.splevo.ui.commons.wizard.EObjectEAttributeWrapper;
import org.splevo.ui.commons.wizard.description.EditCustomizableDescriptionWizard;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.variabilityPackage;

/**
 * Handler to edit the description of a variation point.
 */
public class EditVariationPointDescriptionHandler extends ChangeSingleElementHandler {

    @Override
    protected Wizard createWizardFor(Object selectedObject) {
        if (!(selectedObject instanceof VariationPoint)) {
            return null;
        }

        EObjectEAttributeWrapper<String> wrapper = new EObjectEAttributeWrapper<String>("Variation Point",
                (VariationPoint) selectedObject,
                variabilityPackage.eINSTANCE.getCustomizableDescriptionHaving_Description());

        return new EditCustomizableDescriptionWizard(wrapper);
    }

}
