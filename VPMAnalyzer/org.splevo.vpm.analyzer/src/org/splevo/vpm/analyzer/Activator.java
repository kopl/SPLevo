/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.vpm.analyzer;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.splevo.vpm.analyzer.mergedecider.MergeDecider;
import org.splevo.vpm.analyzer.mergedecider.MergeDeciderRegistry;

/**
 * Activator to manage the plugin's OSGi Bundle life-cycle.
 *
 * This is primarily used to register all available extensions.
 */
public class Activator implements BundleActivator {

    /** The id of the analyzer extension point. */
    private static final String VPM_ANALYZER_EXTENSION_POINT_ID = "org.splevo.vpm.analyzer.analyzer";

    /** The id of the implementing class attribute of the analyzer extension point. */
    private static final String EXTENSION_POINT_ATTR_ANALYZER_CLASS = "analyzer.class";

    /** The id of the merge decider extension point. */
    private static final String MERGE_DECIDER_EXTENSION_POINT_ID = "org.splevo.vpm.analyzer.mergedecider";

    /** The id of the implementing class attribute of the merge decider extension point. */
    private static final String MERGE_DECIDER_EXTENSION_POINT_ATTR_CLASS = "MergeDecider.class";

    private static Logger logger = Logger.getLogger(Activator.class);

    @Override
    public void start(BundleContext context) throws Exception {
        loadAndRegisterVPMAnalyzerExtensions();
        loadAndRegisterMergeDeciderExtensions();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
    }

    /**
     * Load and register all installed {@link VPMAnalyzer} extensions.
     *
     * @throws BundleException
     *             If the infrastructure to load analyzers is not available.
     */
    private static void loadAndRegisterVPMAnalyzerExtensions() throws BundleException {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        if (registry == null) {
            throw new BundleException("No extension point registry available.");
        }
        IExtensionPoint extensionPoint = registry.getExtensionPoint(VPM_ANALYZER_EXTENSION_POINT_ID);

        if (extensionPoint == null) {
            throw new BundleException("No extension point found for the ID " + VPM_ANALYZER_EXTENSION_POINT_ID);
        }
        IExtension[] extensions = extensionPoint.getExtensions();
        for (IExtension extension : extensions) {
            IConfigurationElement[] configurations = extension.getConfigurationElements();
            for (IConfigurationElement element : configurations) {
                try {
                    Object o = element.createExecutableExtension(EXTENSION_POINT_ATTR_ANALYZER_CLASS);
                    if ((o != null) && (o instanceof VPMAnalyzer)) {
                        VPMAnalyzer analyzer = (VPMAnalyzer) o;
                        VPMAnalyzerRegistry.registerVPMAnalyzer(analyzer);
                    }
                } catch (CoreException e) {
                    logger.error("Failed to load VPM analyzer extension", e);
                }
            }
        }
    }

    /**
     * Load and register all installed {@link MergeDecider} extensions.
     *
     * @throws BundleException
     *             If the infrastructure to load analyzers is not available.
     */
    private static void loadAndRegisterMergeDeciderExtensions() throws BundleException {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        if (registry == null) {
            throw new BundleException("No extension point registry available.");
        }
        IExtensionPoint extensionPoint = registry.getExtensionPoint(MERGE_DECIDER_EXTENSION_POINT_ID);

        if (extensionPoint == null) {
            throw new BundleException("No extension point found for the ID " + MERGE_DECIDER_EXTENSION_POINT_ID);
        }
        IExtension[] extensions = extensionPoint.getExtensions();
        for (IExtension extension : extensions) {
            IConfigurationElement[] configurations = extension.getConfigurationElements();
            for (IConfigurationElement element : configurations) {
                try {
                    Object o = element.createExecutableExtension(MERGE_DECIDER_EXTENSION_POINT_ATTR_CLASS);
                    if ((o != null) && (o instanceof MergeDecider)) {
                        MergeDecider mergeDecider = (MergeDecider) o;
                        MergeDeciderRegistry.registerMergeDecider(mergeDecider);
                    }
                } catch (CoreException e) {
                    logger.error("Failed to load merge decider extension", e);
                }
            }
        }
    }

}
