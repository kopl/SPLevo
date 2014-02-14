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
import java.util.List;

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
import org.emftext.language.java.JavaClasspath;

import com.google.common.collect.Lists;

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

    /** The cache data object to work with. */
    private ReferenceCacheData cacheData = new ReferenceCacheData();

    /**
     * Memory of JavaClasspaths which's URI maps have already been enhanced with cached URI
     * mappings.<br>
     * This is used to prevent filling a class path with cached URI mappings multiple times. The
     * list exists to remember the already filled class path instances.
     */
    private List<JavaClasspath> alreadyEnhancedClasspath = Lists.newArrayList();

    /**
     * Memory of JavaClasspaths which's URI maps have already been extracted.<br>
     * This is used to prevent extracting and caching a class path's URI mappings multiple times.
     */
    private List<JavaClasspath> alreadyAnalyzedClasspath = Lists.newArrayList();

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
                ReferenceCacheData loadedCacheData = load(cacheFile);
                if (loadedCacheData != null) {
                    cacheData.merge(loadedCacheData);
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
    public List<String> resolve(Resource resource) {

        processClasspath(resource);

        String resourceUri = resource.getURI().toString();
        List<String> cachedList = cacheData.getResourceToTargetURIListMap().get(resourceUri);
        if (cachedList != null) {
            resolveProxiesFromCache(resource, cachedList);
        } else {
            cachedList = resolveProxies(resource);
            cacheData.getResourceToTargetURIListMap().put(resourceUri, cachedList);
            notResolvedFromCacheCounter++;
        }
        return cachedList;
    }

    /**
     * Process the {@link JavaClasspath} to either extract or fill up the contained URI mappings
     * depending of the cache status and if the {@link JavaClasspath} has been processed before.
     * 
     * This method assumes a local class path used by the ResourceSet.
     * 
     * @param resource
     *            The resource to process the class path of.
     */
    private void processClasspath(Resource resource) {
        JavaClasspath cp = JavaClasspath.get(resource.getResourceSet());
        if (!alreadyEnhancedClasspath.contains(cp)) {

            for (String keyURI : cacheData.getJavaClasspathUriMap().keySet()) {
                String valueURI = cacheData.getJavaClasspathUriMap().get(keyURI);
                cp.getURIMap().put(URI.createURI(keyURI.toString()), URI.createURI(valueURI.toString()));
            }
            alreadyEnhancedClasspath.add(cp);
        }
        if (!alreadyAnalyzedClasspath.contains(cp)) {

            for (URI keyURI : cp.getURIMap().keySet()) {
                URI valueURI = cp.getURIMap().get(keyURI);
                cacheData.getJavaClasspathUriMap().put(keyURI.toString(), valueURI.toString());
            }
            alreadyAnalyzedClasspath.add(cp);
        }
    }

    /**
     * Resolve the proxies in the resource without any previously cached data.
     * 
     * @param resource
     *            The resource to resolve the proxies in.
     * @return The list of resolved URIs for this resource.
     */
    @SuppressWarnings("unchecked")
    private List<String> resolveProxies(Resource resource) {
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

        if (cacheFileDirectories == null || cacheFileDirectories.size() < 1 || cacheFileDirectories.get(0) == null) {
            logger.warn("No cache file directory(ies) configured");
            return;
        }

        File cacheFile = new File(cacheFileDirectories.get(0) + File.separator + CACHE_FILE_NAME);
        ReferenceCacheData cacheDataExisting = load(cacheFile);
        if (cacheDataExisting == null) {
            cacheDataExisting = new ReferenceCacheData();
            cacheData.merge(cacheDataExisting);
        }
        
        save(cacheFile, cacheData);
    }

    /**
     * Persist the cache in the file system.
     * 
     * @param cacheFile
     *            The file to save to.
     * @param cacheData
     *            The cache data to save.
     */
    public synchronized void save(File cacheFile, ReferenceCacheData cacheData) {
        ObjectOutputStream oos = null;
        try {
            FileUtils.forceMkdir(cacheFile.getParentFile());
            oos = new ObjectOutputStream(new FileOutputStream(cacheFile));
            oos.writeObject(cacheData);
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
    private ReferenceCacheData load(File cacheFile) {

        if (!cacheFile.exists() && !cacheFile.canRead()) {
            return null;
        }

        logger.debug("Load reference cache file: " + cacheFile.getAbsolutePath());

        ReferenceCacheData cacheDataLoad = null;
        ObjectInputStream oos = null;
        try {
            oos = new ObjectInputStream(new FileInputStream(cacheFile));
            cacheDataLoad = (ReferenceCacheData) oos.readObject();
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

        return cacheDataLoad;
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
