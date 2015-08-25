package org.splevo.ui.vpexplorer.handler.vpmedit;

import org.eclipse.jface.wizard.Wizard;
import org.splevo.ui.commons.wizard.rename.RenameEObjectEAttributeWrapper;
import org.splevo.ui.commons.wizard.rename.RenameElementWizard;
import org.splevo.vpm.variability.CustomizableNameHaving;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.variabilityPackage;

/**
 * Handler to rename the selected variation point group or variation point.
 */
public class RenameVariationHandler extends ChangeSingleElementHandler {

    @Override
    protected Wizard createWizardFor(Object selectedObject) {
        if (selectedObject instanceof VariationPointGroup) {
            return renameVariationPointGroup((VariationPointGroup) selectedObject);
        } else if (selectedObject instanceof VariationPoint) {
            return renameVariationPoint((VariationPoint) selectedObject);
        }
        return null;
    }
    
    private Wizard renameVariationPointGroup(VariationPointGroup selectedObject) {
        
        RenameEObjectEAttributeWrapper wrapper = new RenameEObjectEAttributeWrapper("Variation Point Group",
                selectedObject, variabilityPackage.eINSTANCE.getCustomizableNameHaving_Name()) {
                    @Override
                    public String getAttributeValue() {
                        return ((CustomizableNameHaving) getElement()).getName();
                    }
        };

        return new RenameElementWizard(wrapper);
    }

    private Wizard renameVariationPoint(VariationPoint selectedObject) {
        RenameEObjectEAttributeWrapper wrapper = new RenameEObjectEAttributeWrapper("Variation Point",
                selectedObject, variabilityPackage.eINSTANCE.getCustomizableNameHaving_Name());

        return new RenameElementWizard(wrapper);
    }
}
