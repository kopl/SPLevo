package org.splevo.ui.vpexplorer.featureoutline.content;

import java.util.ArrayList;
import java.util.List;

import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;

/**
 * Class that returns the children of variation point groups in the form of VariationWrapper.
 */
public class GetVariationPointGroupVariations implements GetVariationPointGroupChildren {

    @Override
    public Object[] getChildren(VariationPointGroup vpg) {
        List<VariationPoint> vps = vpg.getVariationPoints();
        List<VariantWrapper> variant = new ArrayList<VariantWrapper>();
        for (VariationPoint vp : vps) {
            List<Variant> v = vp.getVariants();                       
            for (Variant var : v) {   
                int index = contains(variant, var); 
                if (index < 0) {
                    variant.add(new VariantWrapper(var, var.getVariationPoint()));
                } else {
                    variant.get(index).getVariationPoints().add(var.getVariationPoint());
                }
            }
        }
        return variant.toArray();
    }
    
    
    private int contains(List<VariantWrapper> variants, Variant v) {
        for (int i = 0; i < variants.size(); i++) {
            if (variants.get(i).getVariant().getId().equals(v.getId())) {
                return i;
            }
        }
        return -1;
    }
   
}
