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
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.vpm.analyzer.semantic.extensionpoint;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * A registry for available content providers.
 */
public class SemanticContentProviderRegistry {

    private static List<SemanticContentProvider> contentProviders = Lists.newArrayList();

    /**
     * Register a new content provider.
     *
     * Note: If a provider instance has already been registered, nothing is done. However, providers
     * to not have an identifier or similar. So only instance checking can be done.
     *
     * @param provider
     *            The provider itself.
     */
    public static void registerConentProvider(SemanticContentProvider provider) {
        if (!contentProviders.contains(provider)) {
            contentProviders.add(provider);
        }
    }

    /**
     * Get the list of registered content providers.
     *
     * @return The current list.
     */
    public static List<SemanticContentProvider> getContentProviders() {
        return contentProviders;
    }

}
