/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *    Daniel Kojic
 *******************************************************************************/
package org.splevo.refactoring;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.splevo.commons.interfaces.IdHavingElement;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.variability.VariationPoint;

/**
 * A refactoring to consolidate the variants of a variation point into a single code base using a
 * specific variability realization technique.
 */
public interface VariabilityRefactoring extends IdHavingElement<String> {

    /**
     * Get the variability mechanism realized by this refactoring.
     * 
     * @return The variability mechanism supported by this refactoring.
     * */
    public VariabilityMechanism getVariabilityMechanism();

    /**
     * Refactor a variation point respectively it's variants to a single code base.
     * 
     * @param variationPoint
     *            The variation point to refactor.
     * @param refactoringConfigurations
     *            Refactoring configurations.
     * @return A {@link List} of the changed {@link Resource}s.
     */
    public List<Resource> refactor(VariationPoint variationPoint, Map<String, Object> refactoringConfigurations);

    /**
     * Check if the refactoring can be applied to a specific variation point.
     * 
     * @param variationPoint
     *            The variation point to check the applicability for.
     * @return Flag is the refactoring can be applied.
     * 
     */
    public boolean canBeAppliedTo(VariationPoint variationPoint);

    /**
     * Get the identifier of the refactoring.
     * 
     * @return The identifier of the refactoring.
     */
    @Override
    public String getId();

}
