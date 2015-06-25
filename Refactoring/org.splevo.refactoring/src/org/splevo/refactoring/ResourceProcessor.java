/*******************************************************************************
 * Copyright (c) 2015
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.refactoring;

import org.eclipse.emf.ecore.resource.Resource;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * A resource processor that is triggered during the refactoring process. The exact processing
 * depends on the concrete implementations. This interface is generic by intention to decouple the
 * model-specific from the model-agnostic parts.
 */
public interface ResourceProcessor {

    /**
     * Processes a given resource before the refactoring process has been started.
     * 
     * @param resource
     *            The resource to be processed.
     */
    void processBeforeRefactoring(Resource resource);

    /**
     * Processes the given VPM before the refactoring process has been started.
     * 
     * @param variationPointModel
     *            The VPM to be processed.
     */
    void processVPMBeforeRefactoring(VariationPointModel variationPointModel);

    /**
     * Processes a given resource after the refactoring process has been carried out.
     * 
     * @param resource
     *            The resource to be processed.
     */
    void processAfterRefactoring(Resource resource);

}
