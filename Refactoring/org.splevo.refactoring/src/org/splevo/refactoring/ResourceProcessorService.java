/*******************************************************************************
 * Copyright (c) 2015
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.refactoring;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;
import org.osgi.framework.BundleException;
import org.splevo.commons.eclipse.ExtensionHelper;
import org.splevo.commons.registry.RegistryBase;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Service providing resource processings to be used before or after refactorings. This might be
 * necessary because of incomplete models due to performance tweaks, for instance.
 */
public class ResourceProcessorService {

    private static final Logger LOGGER = Logger.getLogger(ResourceProcessorService.class);
    private static final String RESOURCE_PROCESSOR_EXTENSION_ID = "org.splevo.refactoring.resourceprocessor";
    private static final RegistryBase<ResourceProcessor> RESOURCE_PROCESSOR_REGISTRY;

    static {
        RESOURCE_PROCESSOR_REGISTRY = new RegistryBase<ResourceProcessor>() {
        };

        Iterable<ResourceProcessor> extensions = Collections.emptyList();
        try {
            extensions = ExtensionHelper.getAllRegisteredExtensions(RESOURCE_PROCESSOR_EXTENSION_ID,
                    ResourceProcessor.class);
        } catch (BundleException e) {
            LOGGER.error("Could not access the extension for resource processors.", e);
        }

        for (ResourceProcessor processor : extensions) {
            RESOURCE_PROCESSOR_REGISTRY.registerElement(processor);
        }
    }

    /**
     * Preprocesses all given resources before the refactoring by applying all available processors
     * to it.
     * 
     * @param resources
     *            The resources to be processed.
     */
    public void processResourcesBeforeRefactorings(Iterable<Resource> resources) {
        final List<ResourceProcessor> processors = RESOURCE_PROCESSOR_REGISTRY.getElements();
        for (Resource resource : resources) {
            for (ResourceProcessor processor : processors) {
                processor.processBeforeRefactoring(resource);
            }
        }
    }

    /**
     * Preprocesses the given VPM before the refactoring by applying all available processors to it.
     * 
     * @param variationPointModel
     *            The VPM to be processed.
     */
    public void processVPMBeforeRefactorings(VariationPointModel variationPointModel) {
        final List<ResourceProcessor> processors = RESOURCE_PROCESSOR_REGISTRY.getElements();
        for (ResourceProcessor processor : processors) {
            processor.processVPMBeforeRefactoring(variationPointModel);
        }
    }

}
