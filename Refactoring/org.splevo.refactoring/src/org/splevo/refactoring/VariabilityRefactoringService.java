package org.splevo.refactoring;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.splevo.vrm.VariabilityRealizationModel;

/**
 * A service to refactor the product copies described by a variation point model according to the
 * defined variation points and consolidating their implementations by introducing variability
 * mechanisms.
 */
public interface VariabilityRefactoringService {

    /**
     * Perform refactoring according to the the configured {@link VariabilityRealizationModel}.
     *
     * @param variabilityRealizationModel
     *            The {@link VariabilityRealizationModel} linking the variation points with the
     *            intended variability realization technique.
     * @return The ResourceSet referencing the refactored software.
     */
    public ResourceSet refactor(VariabilityRealizationModel variabilityRealizationModel);
}
