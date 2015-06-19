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
package org.splevo.commons.eclipse;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.BundleException;

/**
 * Utility class for using extensions provided via Eclipse extension points.
 */
public class ExtensionHelper {

    private static final Logger LOGGER = Logger.getLogger(ExtensionHelper.class);

    /**
     * Returns all registered executable extensions for a given extension point. It is assumed that
     * the property of the executable extension is named like typename.class in lower case.
     * 
     * @param extensionPointId
     *            The ID of the extension point that provides the extensions.
     * @param extensionClazz
     *            The class of the provided extensions.
     * @param <T>
     *            The type of the provided extensions.
     * @return The list of the correctly typed extension objects.
     * @throws BundleException
     *             Indicates an error in the extension point handling.
     */
    @SuppressWarnings("unchecked")
    public static <T> Iterable<T> getAllRegisteredExtensions(final String extensionPointId, Class<T> extensionClazz)
            throws BundleException {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        if (registry == null) {
            throw new BundleException("No extension point registry available.");
        }
        IExtensionPoint extensionPoint = registry.getExtensionPoint(extensionPointId);

        if (extensionPoint == null) {
            throw new BundleException("No extension point found for the ID " + extensionPointId);
        }

        List<T> extensionObjects = new ArrayList<T>();
        IExtension[] extensions = extensionPoint.getExtensions();
        for (IExtension extension : extensions) {
            IConfigurationElement[] configurations = extension.getConfigurationElements();
            for (IConfigurationElement element : configurations) {
                try {
                    Object o = element.createExecutableExtension(extensionClazz.getSimpleName().toLowerCase()
                            + ".class");
                    if ((o != null) && extensionClazz.isAssignableFrom(o.getClass())) {
                        extensionObjects.add((T) o);
                    }
                } catch (CoreException e) {
                    LOGGER.error("Failed to load extensions", e);
                }
            }
        }
        return extensionObjects;
    }

}
