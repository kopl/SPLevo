package org.splevo.jamopp.refactoring;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.resource.java.IJavaOptions;
import org.emftext.language.java.resource.java.IJavaTextResource;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;
import org.splevo.refactoring.ResourceProcessor;

/**
 * Resource processor for JaMoPP resources for usage in refactorings. This processor reloads a given
 * resource and enables layout and location extraction if this is not enabled by default.
 */
public class JaMoPPResourceProcessor implements ResourceProcessor {

    private static final Logger LOGGER = Logger.getLogger(JaMoPPResourceProcessor.class);

    @Override
    public void processBeforeRefactoring(Resource resource) {
        if (!JaMoPPSoftwareModelExtractor.EXTRACTOR_EXTRACT_LAYOUT_BY_DEFAULT && resource instanceof IJavaTextResource) {

            // construct new load options
            Map<Object, Object> options = resource.getResourceSet().getLoadOptions();
            options.put(IJavaOptions.DISABLE_LAYOUT_INFORMATION_RECORDING, Boolean.FALSE);
            options.put(IJavaOptions.DISABLE_LOCATION_MAP, Boolean.FALSE);

            // reload the resource with the new load options
            try {
                resource.unload();
                resource.load(options);
            } catch (IOException e) {
                LOGGER.error("Could not preprocess JaMoPP resource.", e);
            }

        }
    }

}
