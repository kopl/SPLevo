package org.splevo.refactoring;

import org.splevo.vpm.variability.VariationPointModel;

/**
 * A service to refactor the input models according to a {@link VariationPointModel}.
 */
public interface RefactoringService {
    
    /**
     * Consolidates the leading and integration input project's models according
     * to a {@link VariationPointModel}. The output is the SPL model.
     * 
     * @param vpm The {@link VariationPointModel}.
     * @param outputPath The SPL will be saved here.
     */
    public void buildSoftwareProductLine(VariationPointModel vpm, String outputPath);
}
