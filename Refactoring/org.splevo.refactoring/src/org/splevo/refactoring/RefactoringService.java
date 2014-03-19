package org.splevo.refactoring;

import org.splevo.vrm.VariabilityRealizationModel;

/**
 * A service to refactor the product copies described by a variation point model according to the
 * defined variation points and consolidating their implementations by introducing variability
 * mechanisms.
 */
public interface RefactoringService {

    /**
     * Perform refactoring according to the the configured {@link VariabilityRealizationModel}.
     *
     * @param variabilityRealizationModel
     *            The {@link VariabilityRealizationModel} linking the variation points with the
     *            intended variability realization technique.
     *            @param targetDirectory The absolute path to the directory to write to.
     */
    public void refactor(VariabilityRealizationModel variabilityRealizationModel, String targetDirectory);

    /**
     * Gets this refactoring services Id.
     *
     * @return The {@link String} Id.
     */
    public String getId();

}
