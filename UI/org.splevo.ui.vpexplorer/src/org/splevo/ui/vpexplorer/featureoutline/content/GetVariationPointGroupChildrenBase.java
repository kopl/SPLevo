package org.splevo.ui.vpexplorer.featureoutline.content;

import org.splevo.vpm.variability.VariationPointGroup;

/**
 * Class that returns the children of variation point groups in the form of their variation points.
 */
public class GetVariationPointGroupChildrenBase implements GetVariationPointGroupChildren {

    @Override
    public Object[] getChildren(VariationPointGroup vpg) {
        return vpg.getVariationPoints().toArray();
    }
}
