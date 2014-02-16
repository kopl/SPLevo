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
package org.splevo.jamopp.extraction.cache;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * The data container of the reference cache.
 */
public class ReferenceCacheData implements Serializable {

    /** The data version identifier. */
    private static final long serialVersionUID = 1L;

    // TODO check if fields can be made final
    /**
     * The list of reference URIs for reach resource. The resource itself is also identified by its
     * URI.
     */
    private Map<String, List<String>> resourceToTargetURIListMap = Maps.newLinkedHashMap();

    /** The absolute paths to jar files to register in the JavaClasspath. */
    private Set<String> jarFilePaths = Sets.newLinkedHashSet();

    /**
     * Access to the resource reference map.
     * 
     * @return The map at least empty but never empty.
     */
    public Map<String, List<String>> getResourceToTargetURIListMap() {
        return resourceToTargetURIListMap;
    }

    /**
     * Access the list of jar files to be registered.
     * 
     * @return The path list, never null.
     */
    public Set<String> getJarFilePaths() {
        return jarFilePaths;
    }

    /**
     * Merge the data of the provided cache to this one. If the provided cache data contains data
     * with a key stored in this cache already, the new one will override the existing one.
     * 
     * @param mergeInCacheData
     *            The data to merge into this cache.
     */
    public void merge(ReferenceCacheData mergeInCacheData) {
        resourceToTargetURIListMap.putAll(mergeInCacheData.getResourceToTargetURIListMap());
        jarFilePaths.addAll(mergeInCacheData.getJarFilePaths());
    }
}