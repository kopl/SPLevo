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
import org.splevo.ui.commons.wizard.rename.RenameEObjectEAttributeWrapper;
import org.splevo.ui.commons.wizard.rename.RenameElementWizard;
import org.splevo.vpm.variability.CustomizableNameHaving;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.variabilityPackage;

/**
 * Handler to rename the selected variation point group.
 */
public class RenameVariationPointGroupHandler extends ChangeSingleElementHandler {

    @Override
    protected Wizard createWizardFor(Object selectedObject) {
        if (!(selectedObject instanceof VariationPointGroup)) {
            return null;
        }

        RenameEObjectEAttributeWrapper wrapper = new RenameEObjectEAttributeWrapper("Variation Point Group",
                (VariationPointGroup) selectedObject, variabilityPackage.eINSTANCE.getCustomizableNameHaving_Name()) {
                    @Override
                    public String getAttributeValue() {
                        return ((CustomizableNameHaving) getElement()).getName();
                    }
        };

        return new RenameElementWizard(wrapper);
    }

}
