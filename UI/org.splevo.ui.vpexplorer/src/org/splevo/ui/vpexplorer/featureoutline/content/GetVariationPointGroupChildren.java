package org.splevo.ui.vpexplorer.featureoutline.content;

import org.splevo.vpm.variability.VariationPointGroup;

/**
 * Interface for content provider to vary between how children are showed.
 */
public interface GetVariationPointGroupChildren {
    
    /**
     * Returns the children of the variation point group.
     * @param vpg the variation point group
     * @return the children of the variation point group
     */
    public Object[] getChildren(VariationPointGroup vpg);  
    
    
    

}