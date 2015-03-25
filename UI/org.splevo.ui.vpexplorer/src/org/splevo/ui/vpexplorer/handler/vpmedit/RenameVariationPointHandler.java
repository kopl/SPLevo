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
import org.splevo.ui.vpexplorer.wizards.rename.RenameVPElementWizard;
import org.splevo.ui.vpexplorer.wizards.rename.RenameVPElementWrapper;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Handler to rename the selected variation point.
 */
public class RenameVariationPointHandler extends RenameVariationPointElementHandler {

    @Override
    protected Wizard createWizardFor(Object selectedObject) {
        if (!(selectedObject instanceof VariationPoint)) {
            return null;
        }
        
        RenameVPElementWrapper<VariationPoint> wrapper = 
                new RenameVPElementWrapper<VariationPoint>((VariationPoint) selectedObject) {

            @Override
            public String getElementToRenameTypeName() {
                return "Variation Point";
            }

            @Override
            public String getElementName() {
                return elementToRename.getDisplayLabel();
            }

            @Override
            public void setElementName(String newName) {
                elementToRename.setDisplayLabel(newName);
            }
        };

        return new RenameVPElementWizard(wrapper);
    }

}
