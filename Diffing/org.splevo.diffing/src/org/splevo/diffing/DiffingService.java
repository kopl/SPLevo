/**
 * 
 */
package org.splevo.diffing;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.compare.Comparison;

/**
 * A service to difference two resource sets.
 */
public interface DiffingService {

	/**
	 * Differencing two software models. Software models might consist of
	 * multiple models encapsulated in two EMF resource sets.
	 * 
	 * @param leadingModelDirectory
	 *            The base directory containing the leading software model.
	 * @param integrationModelDirectory
	 *            The base directory containing the leading software model.
	 * @param diffingOptions
	 *            A set of configurations for the diffing.
	 * @return The differences and matches of the two models.
	 * @throws DiffingException
	 *             An error during the differencing process.
	 */
	public Comparison diffSoftwareModels(URI leadingModelDirectory,
			URI integrationModelDirectory, Map<String, Object> diffingOptions)
			throws DiffingException;
	
	/**
	 * Get the Map of available differs.
	 * 
	 * @return The list of differs.
	 */
	public List<Differ> getDiffers();

}
