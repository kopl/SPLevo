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
package org.splevo.diffing;

import java.util.ArrayList;
import java.util.LinkedList;
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

    private static final String DIFFER_EXTENSION_POINT_ID = "org.splevo.diffing.differ";
    private static final String EXTENSION_POINT_ATTR_DIFFER_CLASS = "differ.class";

    /** The logger for this class. */
    private static Logger logger = Logger.getLogger(Activator.class);

    private static List<Differ> differs = new ArrayList<Differ>();

    /** The context. */
    private static BundleContext context;

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
        Activator.differs = loadDiffers();
    }

    /**
     * Load the software model extractor implementations registered for the according extension
     * point.
     * 
     * {@inheritDoc}
     */
    public List<Differ> loadDiffers() {
        List<Differ> differList = new LinkedList<Differ>();

        IExtensionRegistry registry = Platform.getExtensionRegistry();
        if (registry == null) {
            logger.warn("No extension point registry available.");
            return null;
        }
        IExtensionPoint extensionPoint = registry.getExtensionPoint(DIFFER_EXTENSION_POINT_ID);

        if (extensionPoint == null) {
            logger.warn("No extension point found for the ID " + DIFFER_EXTENSION_POINT_ID);
            return null;
        }
        IExtension[] extensions = extensionPoint.getExtensions();
        for (IExtension extension : extensions) {
            IConfigurationElement[] configurations = extension.getConfigurationElements();
            for (IConfigurationElement element : configurations) {
                try {
                    Object o = element.createExecutableExtension(EXTENSION_POINT_ATTR_DIFFER_CLASS);
                    if ((o != null) && (o instanceof Differ)) {
                        Differ differ = (Differ) o;
                        differ.init();
                        differList.add(differ);
                    }
                } catch (CoreException e) {
                    logger.error("Failed to load differ extension", e);
                }
            }
        }

        if (differIdsNotUnique(differList)) {
            logger.warn("Two or more differs with the same id loaded.");
        }

        return differList;
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

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        Activator.context = null;
    }

    /**
     * Get the available differs.
     * @return The list of loaded differs.
     */
    public static List<Differ> getDiffers() {
        return differs;
    }

}
