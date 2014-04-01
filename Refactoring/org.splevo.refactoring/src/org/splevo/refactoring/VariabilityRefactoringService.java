package org.splevo.refactoring;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * A service to refactor the product copies described by a variation point model according to the
 * defined variation points and consolidating their implementations by introducing variability
 * mechanisms.
 */
public interface VariabilityRefactoringService {

    /**
     * Perform refactoring according to the the configured {@link VariationPointModel}.
     *
     * @param variationPointModel
     *            The {@link VariationPointModel} containing the variation points with the
     *            intended variability mechanism.
     * @return The ResourceSet referencing the refactored software.
     */
    public ResourceSet refactor(VariationPointModel variationPointModel);
}
