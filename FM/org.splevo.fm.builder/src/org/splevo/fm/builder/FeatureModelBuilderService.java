/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.fm.builder;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Service to access available feature model builder and to trigger selected ones for model
 * generation.
 */
public class FeatureModelBuilderService {

    /** Message to provide if a requested builder can not be loaded. */
    private static final String MSG_BUILDER_NOT_AVAILABLE = "No feature model builder available for the provided id: %1";

    /** The id of the extension point to look up registered builders. */
    private static final String BUILDER_EXTENSION_POINT_ID = "org.splevo.fm.builder";

    /** The extension attribute specifying the builder class to instantiate. */
    private static final String EXTENSION_POINT_ATTR_BUILDER_CLASS = "builder.class";

    /** The list of available builders. */
    private Map<String, FeatureModelBuilder<?>> builder = Maps.newLinkedHashMap();

    private static Logger logger = Logger.getLogger(FeatureModelBuilderService.class);

    // TODO refactor into a plugin activator.
    /**
     * Constructor trying to load the available builders from the eclipse registry if available.
     */
    public FeatureModelBuilderService() {
        loadRegisteredFeatureModels();
    }

    /**
     * Trigger the feature model generation for the builders identified by the list of IDs provided.
     *
     * @param builderIds
     *            The IDs of the builder to trigger.
     * @param vpm
     *            The variation point model to build the feature models for.
     * @param rootNodeName
     *            The name of the models root node.
     * @param targetPath
     *            The path of the base directory to save the models to. If null is provided the save
     *            will not be triggered.
     * @return A list with {@link FeatureModelWrapper}s containing the feature models.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<FeatureModelWrapper<Object>> buildAndSaveModels(List<String> builderIds, 
            VariationPointModel vpm, String rootNodeName, String targetPath) {
        List<FeatureModelWrapper<Object>> featureModels = Lists.newArrayList();
        for (String id : builderIds) {
            FeatureModelBuilder currentBuilder = getAvailableBuilders().get(id);
            if (currentBuilder == null) {
                logger.warn(String.format(MSG_BUILDER_NOT_AVAILABLE, id));
                continue;
            }

            FeatureModelWrapper modelWrapper = currentBuilder.build(vpm, rootNodeName);
            featureModels.add(modelWrapper);
            if (targetPath != null) {
                currentBuilder.save(modelWrapper.getModel(), targetPath);
            }
        }
        return featureModels;
    }

    /**
     * Try to access the eclipse extension registry and load the extension available.
     */
    private void loadRegisteredFeatureModels() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        if (registry != null) {

            IExtensionPoint extensionPoint = registry.getExtensionPoint(BUILDER_EXTENSION_POINT_ID);
            if (extensionPoint != null) {
                IExtension[] extensions = extensionPoint.getExtensions();
                for (IExtension extension : extensions) {
                    IConfigurationElement[] configurations = extension.getConfigurationElements();
                    for (IConfigurationElement element : configurations) {
                        try {
                            Object o = element.createExecutableExtension(EXTENSION_POINT_ATTR_BUILDER_CLASS);
                            if ((o != null) && (o instanceof FeatureModelBuilder<?>)) {
                                FeatureModelBuilder<?> extractor = (FeatureModelBuilder<?>) o;
                                builder.put(extractor.getId(), extractor);
                            }
                        } catch (CoreException e) {
                            logger.error("Failed to load feature model builder extension", e);
                        }
                    }
                }
            }
        }
    }

    /**
     * Get all registered feature model builder.
     *
     * Tries to access the eclipse extension registry, looks up registered extensions and returns
     * them.
     *
     * @return A map with feature model builder IDs linked to the builder instances.
     */
    public Map<String, FeatureModelBuilder<?>> getAvailableBuilders() {
        return builder;
    }
}
