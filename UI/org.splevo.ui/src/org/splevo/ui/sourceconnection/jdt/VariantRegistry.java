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
package org.splevo.ui.sourceconnection.jdt;

import java.util.Map;

import org.splevo.vpm.variability.Variant;

import com.google.common.collect.Maps;

/**
 * A registry to allow for transporting variant elements through eclipse IMarkers.
 */
public final class VariantRegistry {

    private static Map<String, Variant> variantIndex = Maps.newLinkedHashMap();

    /** Disable constructor to force static use of the registry. */
    private VariantRegistry() {
    }

    /**
     * Register a variant and get the key to access it later on.
     *
     * @param variant
     *            The variant to register.
     * @return The key it was registered with.
     */
    public static String register(Variant variant) {
        String key = "" + variant.hashCode();
        variantIndex.put(key, variant);
        return key;
    }

    /**
     * Get a variant from the index for a given key.
     *
     * @param key
     *            The key to look up the variant with.
     * @return The Variant or null if none has been registered for it before.
     */
    public static Variant get(String key) {
        return variantIndex.get(key);
    }
}
