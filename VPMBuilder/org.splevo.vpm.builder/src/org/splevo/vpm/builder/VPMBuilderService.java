package org.splevo.vpm.builder;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.compare.Comparison;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Service to build a variation point model from a difference model.
 */
public interface VPMBuilderService {

    /**
     * Build a new VariationPointModel based on a DiffModel.
     * 
     * The provided comparison model should be the result of a JavaModel diffing. This will be checked by
     * the builder and null will be returned if this is not valid.
     * 
     * @param comparisonModel
     *            The comparison model to interpret.
     * @param leadingVariantName
     *            The name of the leading software variant.
     * @param integrationVariantName
     *            The name of the software variant to be integrated.
     * @param builderOptions
     *            A set of configurations for the builder.
     * @return The resulting VariationPointModel or null if the DiffModel is not a about a MoDisco
     *         JavaModel.
     * @throws VPMBuildException
     *             An error during the vpm build process.
     * 
     */
    public VariationPointModel buildVPM(Comparison comparisonModel, String leadingVariantName, String integrationVariantName,
            Map<String, Object> builderOptions) throws VPMBuildException;

    public List<VPMBuilder> getVPMBuilders();

}
