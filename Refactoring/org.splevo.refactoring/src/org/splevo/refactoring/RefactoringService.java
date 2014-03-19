package org.splevo.refactoring;

import org.splevo.vpm.variability.VariationPointModel;

/**
 * This service provides a refactoring that can be executed on a {@link VariationPointModel}. 
 */
public interface RefactoringService {
	
	/**
	 * Executes the refactoring on the given {@link VariationPointModel}.
	 * 
	 * @param variationPointModel The {@link VariationPointModel}.
	 */
	public void refactor(VariationPointModel variationPointModel);

	/**
	 * Gets this refactoring services Id.
	 * 
	 * @return The {@link String} Id.
	 */
	public String getId();

}
