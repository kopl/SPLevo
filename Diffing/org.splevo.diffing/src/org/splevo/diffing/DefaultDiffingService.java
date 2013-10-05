package org.splevo.diffing;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;

/**
 * Default service to run the an extractor.
 */
public class DefaultDiffingService implements DiffingService {

	private Logger logger = Logger.getLogger(DefaultDiffingService.class);

	/** Regular expressions defining packages to be ignored. */
	private static final String MSG_DIFFER_NOT_AVAILABLE = "No differs available.";
	private static final String DIFFER_EXTENSION_POINT_ID = "org.splevo.diffing.differ";
	private static final String EXTENSION_POINT_ATTR_DIFFER_CLASS = "differ.class";

	@Override
	public DiffModel diffSoftwareModels(URI leadingModelDirectory,
			URI integrationModelDirectory, Map<String, Object> diffingOptions)
			throws DiffingException {

		List<Differ> differs = getDiffers();
		if (differs.size() == 0) {
			throw new DiffingException(String.format(MSG_DIFFER_NOT_AVAILABLE));
		}

		// TODO Implement using all differs.
		return differs.get(0).doDiff(leadingModelDirectory,
				integrationModelDirectory, diffingOptions);
	}

	/**
	 * Load the software model extractor implementations registered for the
	 * according extension point.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public List<Differ> getDiffers() {
		List<Differ> differs = new LinkedList<Differ>();

		IExtensionRegistry registry = Platform.getExtensionRegistry();
		if (registry == null) {
			logger.warn("No extension point registry available.");
			return null;
		}
		IExtensionPoint extensionPoint = registry
				.getExtensionPoint(DIFFER_EXTENSION_POINT_ID);

		if (extensionPoint == null) {
			logger.warn("No extension point found for the ID "
					+ DIFFER_EXTENSION_POINT_ID);
			return null;
		}
		IExtension[] extensions = extensionPoint.getExtensions();
		for (IExtension extension : extensions) {
			IConfigurationElement[] configurations = extension
					.getConfigurationElements();
			for (IConfigurationElement element : configurations) {
				try {
					Object o = element
							.createExecutableExtension(EXTENSION_POINT_ATTR_DIFFER_CLASS);
					if ((o != null) && (o instanceof Differ)) {
						Differ differ = (Differ) o;
						differs.add(differ);
					}
				} catch (CoreException e) {
					logger.error(
							"Failed to load differ extension",
							e);
				}
			}
		}

		if (differIdsNotUnique(differs)) {
			logger.warn("Two or more differs with the same id loaded.");
		}

		return differs;
	}

	/**
	 * Check if there are two or more differs with the same id.
	 * 
	 * @param differs
	 *            The differs to check.
	 * @return True if the same id is used more than once. False otherwise.
	 */
	private boolean differIdsNotUnique(List<Differ> differs) {
		List<String> ids = new LinkedList<String>();
		for (Differ differ : differs) {
			if (ids.contains(differ.getId())) {
				return true;
			}
			ids.add(differ.getId());
		}
		return false;

	}
}
