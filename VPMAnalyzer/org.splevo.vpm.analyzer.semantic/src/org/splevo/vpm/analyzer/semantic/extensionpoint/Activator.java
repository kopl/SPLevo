/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic - initial API and implementation and/or initial documentation
 *    Benjamin Klatt - improvements, documentation, and maintenance
 *******************************************************************************/
package org.splevo.vpm.analyzer.semantic.extensionpoint;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * This is the Activator class for the semantic analyzer project.
 *
 * At startup, it loads all extensions registered to the extension point of the semantic analyzer
 * and registers them in the static registry.
 */
public class Activator implements BundleActivator {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(Activator.class);

    /** The id of the analyzer extension point. */
    private static final String SEMANTIC_CONTENT_PROVIDER_EXTENSION_POINT_ID = "org.splevo.vpm.analyzer.semantic.contentprovider";

    /**
     * The id of the implementing class attribute of the content provider extension point.
     */
    private static final String EXTENSION_POINT_ATTR_ANALYZER_CLASS = "contentprovider.class";

    /**
     * Start the bundle life cycle by identifying available extensions during bundle start up.
     * {@inheritDoc}
     */
    public void start(BundleContext bundleContext) throws Exception {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        if (registry == null) {
            logger.warn("No extension point registry available.");
            return;
        }
        IExtensionPoint extensionPoint = registry.getExtensionPoint(SEMANTIC_CONTENT_PROVIDER_EXTENSION_POINT_ID);

        if (extensionPoint == null) {
            logger.warn("No extension point found for the ID " + SEMANTIC_CONTENT_PROVIDER_EXTENSION_POINT_ID);
            return;
        }
        IExtension[] extensions = extensionPoint.getExtensions();
        for (IExtension extension : extensions) {
            IConfigurationElement[] configurations = extension.getConfigurationElements();
            for (IConfigurationElement element : configurations) {
                try {
                    Object o = element.createExecutableExtension(EXTENSION_POINT_ATTR_ANALYZER_CLASS);
                    if ((o != null) && (o instanceof SemanticContentProvider)) {
                        SemanticContentProvider contentProvider = (SemanticContentProvider) o;
                        SemanticContentProviderRegistry.getInstance().registerElement(contentProvider);
                    }
                } catch (CoreException e) {
                    logger.error("Failed to load semantic content provider extension", e);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void stop(BundleContext bundleContext) throws Exception {
    }

}