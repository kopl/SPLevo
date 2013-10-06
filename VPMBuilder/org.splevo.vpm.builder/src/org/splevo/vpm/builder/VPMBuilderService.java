package org.splevo.vpm.builder;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Service to build a variation point model from a difference model.
 */
public interface VPMBuilderService {

    /**
     * Build a new VariationPointModel based on a DiffModel.
     * 
     * The provided diff model should be the result of a JavaModel diffing. This will be checked by
     * the builder and null will be returned if this is not valid.
     * 
     * @param diffModel
     *            The diff model to interpret.
     * @param leadingVariantName
     *            The name of the leading software variant.
     * @param integrationVariantName
     *            The name of the software variant to be integrated.
     * @param builderOptions
     *            A set of configurations for the diffing.
     * @return The resulting VariationPointModel or null if the DiffModel is not a about a modisco
     *         JavaModel.
     * @throws VPMBuildException
     *             An error during the vpm build process.
     * 
     */
    public VariationPointModel buildVPM(DiffModel diffModel, String leadingVariantName, String integrationVariantName,
            Map<String, Object> builderOptions) throws VPMBuildException;

    public List<VPMBuilder> getVPMBuilders();

}
