package org.splevo.ui.vpexplorer.featureoutline.content;

import java.util.ArrayList;
import java.util.List;

import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Wrapper Class for Variants and variation points, that have the same variant id.
 *
 */
public class VariantWrapper {
    private Variant variant;
    private List<VariationPoint> vps;
    
    /**
     * Creates a new wrapper.
     * @param variant the variant included in the wrapper
     * @param vp the variation point included in the wrapper
     */
    protected VariantWrapper(Variant variant, VariationPoint vp) {
        this.variant = variant;
        vps = new ArrayList<VariationPoint>();
        vps.add(vp);
    }
    
    /**
     * Returns the variant.
     * @return the variant
     */
    public Variant getVariant() { 
        return variant;
    }
    
    /**
     * Returns the variation points.
     * @return the variation points
     */
    public List<VariationPoint> getVariationPoints() {
        return vps;
    }

}
