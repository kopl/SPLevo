/*******************************************************************************
 * Copyright (c) 2013
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.vpm.analyzer.programstructure;

import java.util.ArrayList;
import java.util.List;

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
 * The Class Activator.
 */
public class Activator implements BundleActivator {

    /** The id of the vpm extension extension point. */
    private static final String PROGRAM_STRUCTURE_PROVIDER_EXTENSION_POINT_ID = "org.splevo.vpm.analyzer.programstructure.programstructureprovider";

    /** The id of the implementing class attribute of the vpm extension extension point. */
    private static final String EXTENSION_POINT_ATTR_EXTENSION_CLASS = "extension.class";

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(Activator.class);

    /** The context. */
    private static BundleContext context;

    /** The program structure providers loaded and available to access. */
    private static List<ProgramStructureProvider> programStructureProviders = new ArrayList<ProgramStructureProvider>();

    /**
     * Get the available program structure providers.
     *
     * @return The loaded program structure provider extensions.
     */
    public static List<ProgramStructureProvider> getProgramStructureProviders() {
        return programStructureProviders;
    }

    /**
     * Gets the context.
     *
     * @return the context
     */
    static BundleContext getContext() {
        return context;
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        Activator.context = bundleContext;
        loadProgramStructureProviderExtensions();
    }

    /**
     * Load all variation point model extensions and trigger their initialization.
     */
    private void loadProgramStructureProviderExtensions() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        if (registry == null) {
            logger.warn("No extension point registry available.");
        }
        IExtensionPoint extensionPoint = registry.getExtensionPoint(PROGRAM_STRUCTURE_PROVIDER_EXTENSION_POINT_ID);

        if (extensionPoint == null) {
            logger.warn("No extension point found for the ID " + PROGRAM_STRUCTURE_PROVIDER_EXTENSION_POINT_ID);
        }
        IExtension[] extensions = extensionPoint.getExtensions();
        for (IExtension extension : extensions) {
            IConfigurationElement[] configurations = extension.getConfigurationElements();
            for (IConfigurationElement element : configurations) {
                try {
                    Object o = element.createExecutableExtension(EXTENSION_POINT_ATTR_EXTENSION_CLASS);
                    if ((o != null) && (o instanceof ProgramStructureProvider)) {
                        ProgramStructureProvider programStructureProvider = (ProgramStructureProvider) o;
                        programStructureProviders.add(programStructureProvider);
                    }
                } catch (CoreException e) {
                    logger.error("Failed to load and initialize VPM extension", e);
                }
            }
        }

        if (programStructureProviders.size() == 0) {
            logger.warn("No Program Structure Providers for the Program Structure Analyzer available");
        }
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        Activator.context = null;
    }

}
