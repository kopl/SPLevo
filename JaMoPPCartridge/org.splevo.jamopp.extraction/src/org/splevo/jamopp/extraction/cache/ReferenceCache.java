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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * A file based cache to reuse the proxy resolutions already performed.
 *
 * The cache was designed to work with one or more cache files to use it with resource sets
 * containing the resources of one or more software models. For example, during extraction a
 * separate resource set is used per software, but for differencing several software models must be
 * accessed in one resource set.
 *
 * Cache files are always named according to {@link #CACHE_FILE_NAME}.
 *
 * During initialization, cache files existing in the provided directories are loaded.
 * Subdirectories are not considered.
 *
 * When proxies in new resources are resolved and {@link #save()} is triggered, they are stored in a
 * cache file of the first directory provided in the list. If a cache file already exists in the
 * first cache directory, the existing cache is loaded and enhanced with the new cached references.
 */
public class ReferenceCache {

    /** The name of the cache files to be used. */
    public static final String CACHE_FILE_NAME = "jamopp.cache";

    private static Logger logger = Logger.getLogger(ReferenceCache.class);

    /** Internal counter how many resources have been resolved from cache. */
    private int notResolvedFromCacheCounter = 0;

    /**
     * The file the cache will be serialized into.
     */
    private final List<String> cacheFileDirectories;

    private Map<String, List<String>> resourceToTargetURIListMap = new LinkedHashMap<String, List<String>>();

    /** List of map entries that are not persisted yet and must be saved before cache is finished. */
    private List<String> unsavedCacheEntries = Lists.newArrayList();

    /**
     * Constructor to set a list of directories containing cache files. Within these directories,
     * files with the name {@link #CACHE_FILE_NAME} are searched.
     *
     * If a new file must be created, this will be done in the first directory of the list.
     *
     * @param cacheFileDirectories
     *            A list of absolute paths to the directories containing cache files.
     */
    public ReferenceCache(List<String> cacheFileDirectories) {
        this.cacheFileDirectories = cacheFileDirectories;
        init();
    }

    /**
     * Initialize the cache by loading all cache files available in the configured directory.
     */
    private void init() {
        for (String cacheDirectory : this.cacheFileDirectories) {
            File cacheFile = new File(cacheDirectory + File.separator + CACHE_FILE_NAME);
            if (cacheFile.exists() && cacheFile.canRead()) {
                Map<String, List<String>> load = load(cacheFile);
                if (load != null) {
                    resourceToTargetURIListMap.putAll(load);
                }
            }
        }
    }

    /**
     * Resolves the resource. Will use the cache if the resource has already been resolved and
     * result is cached.
     *
     * @param resource
     *            Resource to be resolved.
     * @return A list of this resources target URIs.
     */
    @SuppressWarnings("unchecked")
    public List<String> resolve(Resource resource) {
        String resourceUri = resource.getURI().toString();
        List<String> cachedList = resourceToTargetURIListMap.get(resourceUri);
        if (cachedList != null) {
            resolveProxiesFromCache(resource, cachedList);
            return cachedList;
        }

        List<String> resolvedReferenceTargetURIs = new ArrayList<String>();

        for (Iterator<EObject> elementIt = resource.getAllContents(); elementIt.hasNext();) {
            InternalEObject nextElement = (InternalEObject) elementIt.next();
            if (nextElement.eIsProxy()) {
                throw new RuntimeException("Unexpected containment proxy.");
            }

            List<EReference> eReferences = nextElement.eClass().getEAllReferences();
            for (EReference eReference : eReferences) {
                if (eReference.isContainment()) {
                    continue;
                }
                if (eReference.isDerived()) {
                    continue;
                }

                Object refValue = nextElement.eGet(eReference, false);
                if (refValue == null) {
                    continue;
                }

                if (refValue instanceof BasicEList) {
                    BasicEList<EObject> list = (BasicEList<EObject>) refValue;
                    for (int i = 0; i < list.size(); i++) {
                        EObject crElement = list.basicGet(i);
                        resolve(resource, resolvedReferenceTargetURIs, crElement);
                    }
                } else if (refValue instanceof EObject) {
                    EObject crElement = (EObject) refValue;
                    resolve(resource, resolvedReferenceTargetURIs, crElement);
                } else {
                    throw new RuntimeException("Unknown object: " + refValue);
                }
            }
        }

        resourceToTargetURIListMap.put(resourceUri, resolvedReferenceTargetURIs);
        unsavedCacheEntries.add(resourceUri);
        notResolvedFromCacheCounter++;
        return resolvedReferenceTargetURIs;
    }

    /**
     * Resolve a model element and store it's new target uri in the list of uris.
     *
     * @param resource
     *            The resource to use for resolution.
     * @param targetURIs
     *            The list of target URIs to fill.
     * @param crElement
     *            The element to resolve (might be a proxy)
     */
    private void resolve(Resource resource, List<String> targetURIs, EObject crElement) {
        crElement = EcoreUtil.resolve(crElement, resource);
        if (crElement.eIsProxy()) {
            targetURIs.add(null);
        } else {
            Resource targetResource = crElement.eResource();
            targetURIs.add(targetResource.getURI().toString() + "#" + targetResource.getURIFragment(crElement));
        }
    }

    /**
     * Get the map of target URIs defined for a resource. The later is identified by a String URI as
     * well.
     *
     * @return The map of resource and target URIs
     */
    public Map<String, List<String>> getResourceToTargetURIListMap() {
        return resourceToTargetURIListMap;
    }

    /**
     * Trigger to save all non yet persisted cache entries.<br>
     * These are the entries created for resources that could not be loaded from any existing cache
     * file.
     *
     * If more than one cache file directory was created, the first entry in the list will be used.
     *
     * If the cache file already exists, it will not be overridden, but loaded and the new entries
     * will be added to it.
     *
     */
    public void save() {

        if (unsavedCacheEntries.size() == 0) {
            logger.debug("No new cache entries to save. ");
        }

        if (cacheFileDirectories == null || cacheFileDirectories.size() < 1) {
            logger.warn("No cache file directory(ies) configured");
            return;
        }

        File cacheFile = new File(cacheFileDirectories.get(0) + File.separator + CACHE_FILE_NAME);
        Map<String, List<String>> cacheMap = load(cacheFile);
        if (cacheMap == null) {
            cacheMap = Maps.newLinkedHashMap();
        }

        for (String key : unsavedCacheEntries) {
            List<String> uris = resourceToTargetURIListMap.get(key);
            cacheMap.put(key, uris);
        }

        save(cacheFile, cacheMap);
    }

    /**
     * Persist the cache in the file system.
     *
     * @param cacheFile
     *            The file to save to.
     * @param resourceToTargetURIListMap
     *            The cache map to save.
     */
    public synchronized void save(File cacheFile, Map<String, List<String>> resourceToTargetURIListMap) {
        ObjectOutputStream oos = null;
        try {
            FileUtils.forceMkdir(cacheFile.getParentFile());
            oos = new ObjectOutputStream(new FileOutputStream(cacheFile));
            oos.writeObject(resourceToTargetURIListMap);
        } catch (FileNotFoundException e) {
            logger.info("cache file does not exist yet" + cacheFile);
        } catch (IOException e) {
            logger.warn("cache file could not be accessed: " + cacheFile);
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    logger.warn("Failed to close cache file output stream", e);
                }
            }
        }
    }

    /**
     * Load the cache from a file.
     *
     * @param cacheFile
     *            The file to load.
     * @return The cache map loaded from this file.
     */
    @SuppressWarnings("unchecked")
    private Map<String, List<String>> load(File cacheFile) {

        if (!cacheFile.exists() && !cacheFile.canRead()) {
            return null;
        }

        Map<String, List<String>> cacheMap = null;

        ObjectInputStream oos = null;
        try {
            oos = new ObjectInputStream(new FileInputStream(cacheFile));
            cacheMap = (Map<String, List<String>>) oos.readObject();
        } catch (FileNotFoundException e) {
            logger.error("Cache file can not be found", e);
        } catch (IOException e) {
            logger.error("Cache file could not be accessed correctly", e);
        } catch (ClassNotFoundException e) {
            logger.error("An object persisted in the cache file could not be loaded", e);
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    logger.warn("Failed to close cache file output stream", e);
                }
            }
        }

        return cacheMap;
    }

    /**
     * Try to resolve the proxies in the resource with a list of target URIs.
     *
     * @param resource
     *            The resource to resolve the proxies in.
     * @param targetURIList
     *            The list of target URIs to use for the resolution.
     */
    @SuppressWarnings("unchecked")
    private void resolveProxiesFromCache(Resource resource, List<String> targetURIList) {

        int index = 0;
        for (Iterator<EObject> elementIt = EcoreUtil.getAllContents(resource, true); elementIt.hasNext();) {
            InternalEObject nextElement = (InternalEObject) elementIt.next();
            if (nextElement.eIsProxy()) {
                throw new RuntimeException("Unexpected containment proxy.");
            }

            EList<EReference> eReferences = nextElement.eClass().getEAllReferences();
            for (EReference eReference : eReferences) {
                if (eReference.isContainment()) {
                    continue;
                }

                Object refValue = nextElement.eGet(eReference, false);
                if (refValue == null) {
                    continue;
                }

                if (refValue instanceof BasicEList) {
                    BasicEList<EObject> list = (BasicEList<EObject>) refValue;
                    for (int i = 0; i < list.size(); i++) {
                        String targetURI = targetURIList.get(index);
                        if (targetURI != null) {
                            EObject targetObject = getTarget(resource, targetURI);
                            list.set(i, targetObject);
                        } else {
                            logger.warn("Element is not resolved (null) in cache: " + targetURI);
                        }
                        index++;
                    }
                } else if (refValue instanceof EObject) {
                    String targetURI = targetURIList.get(index);
                    if (targetURI != null) {
                        EObject targetObject = getTarget(resource, targetURI);
                        nextElement.eSet(eReference, targetObject);
                    } else {
                        logger.warn("Element is not resolved (null) in cache: " + targetURI);
                    }
                    index++;
                } else {
                    throw new RuntimeException("Unknown object: " + refValue);
                }
            }
        }
    }

    /**
     * Get the target object for a specified URI.
     *
     * @param resource
     *            The resource to use for EObject resolution.
     * @param targetURI
     *            The URI to resolve the target object with.
     * @return The resolved EObject.
     */
    private EObject getTarget(Resource resource, String targetURI) {
        URI createURI = URI.createURI(targetURI);
        ResourceSet resourceSet = resource.getResourceSet();
        EObject target = resourceSet.getEObject(createURI, true);
        return target;
    }

    /**
     * Get the number of resources which's references have not been resolved from cache.
     *
     * @return The counter value.
     */
    public int getNotResolvedFromCacheCounter() {
        return notResolvedFromCacheCounter;
    }

}
