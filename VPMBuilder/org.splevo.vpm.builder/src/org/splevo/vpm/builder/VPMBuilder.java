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

import org.eclipse.emf.compare.Comparison;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * A builder to create a VariationPointModel with elements for a diff model elements supported.
 */
public interface VPMBuilder {

    /**
     * Build a variation point model for all supported diff elements of a DiffModel.
     *
     * @param comparisonModel
     *            The diff model to read supported DiffElements from.
     * @param variantIDLeading
     *            The id for the leading variants.
     * @param variantIDIntegration
     *            The id for the integration variants.
     *
     * @return The created variation point model.
     * @throws VPMBuildException
     *             An exception thrown because of an error during the build process.
     */
    public VariationPointModel buildVPM(Comparison comparisonModel, String variantIDLeading, String variantIDIntegration) throws VPMBuildException;

    /**
     * Get the identifier of the builder. This should be unique compared to all
     * other loaded builders in the same instance.
     *
     * @return The internal id of the builder.
     */
    String getId();

    /**
     * The label of the builder to represent it for the user.
     *
     * @return The representative label.
     */
    String getLabel();
}
