package org.splevo.extraction;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Default service to run the an extractor.
 */
public class DefaultExtractionService implements ExtractionService {

    private Logger logger = Logger.getLogger(DefaultExtractionService.class);

    private static final String MSG_EXTRACTOR_NOT_AVAILABLE = "No software extractor available for the provided id: %1";
    private static final String EXTRACTOR_EXTENSION_POINT_ID = "org.splevo.extraction.softwareextractor";
    private static final String EXTENSION_POINT_ATTR_EXTRACTOR_CLASS = "extractor.class";

    @Override
    public ResourceSet extractSoftwareModel(String extractorId, List<URI> projectPaths, IProgressMonitor monitor,
            URI targetURI) throws SoftwareModelExtractionException {

        SoftwareModelExtractor extractor = getSoftwareModelExtractors().get(extractorId);
        if (extractor == null) {
            throw new SoftwareModelExtractionException(String.format(MSG_EXTRACTOR_NOT_AVAILABLE, extractorId));
        }

        return extractor.extractSoftwareModel(projectPaths, monitor, targetURI);
    }

    /**
     * Load the software model extractor implementations registered for the according extension
     * point.
     *
     * {@inheritDoc}
     */
    @Override
    public Map<String, SoftwareModelExtractor> getSoftwareModelExtractors() {
        Map<String, SoftwareModelExtractor> softwareModelExtractors = new LinkedHashMap<String, SoftwareModelExtractor>();
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        if (registry == null) {
            logger.warn("No extension point registry available.");
            return null;
        }
        IExtensionPoint extensionPoint = registry.getExtensionPoint(EXTRACTOR_EXTENSION_POINT_ID);

        if (extensionPoint == null) {
            logger.warn("No extension point found for the ID " + EXTRACTOR_EXTENSION_POINT_ID);
            return null;
        }
        IExtension[] extensions = extensionPoint.getExtensions();
        for (IExtension extension : extensions) {
            IConfigurationElement[] configurations = extension.getConfigurationElements();
            for (IConfigurationElement element : configurations) {
                try {
                    Object o = element.createExecutableExtension(EXTENSION_POINT_ATTR_EXTRACTOR_CLASS);
                    if ((o != null) && (o instanceof SoftwareModelExtractor)) {
                        SoftwareModelExtractor extractor = (SoftwareModelExtractor) o;
                        softwareModelExtractors.put(extractor.getId(), extractor);
                    }
                } catch (CoreException e) {
                    logger.error("Failed to load software model extractor extension", e);
                }
            }
        }
        return softwareModelExtractors;
    }

}
