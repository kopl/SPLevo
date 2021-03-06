/*******************************************************************************
 * Copyright (c) 2013
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
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
     * The provided comparison model should be the result of a JavaModel diffing. This will be
     * checked by the builder and null will be returned if this is not valid.
     *
     * @param comparisonModel
     *            The comparison model to interpret.
     * @param leadingVariantName
     *            The name of the leading software variant.
     * @param integrationVariantName
     *            The name of the software variant to be integrated.
     * @param builderOptions
     *            A set of configurations for the builder.
     * @return The resulting VariationPointModel.
     * @throws VPMBuildException
     *             An error during the vpm build process.
     *
     */
    public VariationPointModel buildVPM(Comparison comparisonModel, String leadingVariantName,
            String integrationVariantName, Map<String, Object> builderOptions) throws VPMBuildException;

    /**
     * Get the list of available variation point model builders.
     *
     * @return The list of builders.
     */
    public List<VPMBuilder> getVPMBuilders();

}
