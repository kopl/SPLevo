package org.splevo.jamopp.refactoring;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.resource.java.IJavaOptions;
import org.emftext.language.java.resource.java.IJavaTextResource;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;
import org.splevo.refactoring.ResourceProcessor;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Resource processor for JaMoPP resources for usage in refactorings. This processor reloads a given
 * resource and enables layout and location extraction if this is not enabled by default.
 */
public class JaMoPPResourceProcessor implements ResourceProcessor {

    private static final Logger LOGGER = Logger.getLogger(JaMoPPResourceProcessor.class);

    @Override
    public void processBeforeRefactoring(Resource resource) {
        if (!JaMoPPSoftwareModelExtractor.EXTRACTOR_EXTRACT_LAYOUT_BY_DEFAULT && resource instanceof IJavaTextResource) {
            reloadResourceWithLayoutInformation(resource);
        }
    }

    @Override
    public void processVPMBeforeRefactoring(VariationPointModel variationPointModel) {
        // TODO replace JaMoPPSoftwareElement with newly created element that references via
        // comments (id to be used in the comment can be calculated by concatenating the id of the
        // variation point and the variant). Use the comment adding method in RefactoringUtil
        // (extract it and add it to this project).
    }

    @Override
    public void processAfterRefactoring(Resource resource) {
//        if (resource instanceof IJavaTextResource) {
//            reloadResourceWithLayoutInformation(resource);
//        }
    }
    
    private void reloadResourceWithLayoutInformation(Resource resource) {
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
