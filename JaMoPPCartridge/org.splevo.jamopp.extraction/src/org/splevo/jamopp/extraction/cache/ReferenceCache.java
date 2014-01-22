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

/**
 * A file based cache to reduce the number of proxy resolutions for multiple code extractions.
 */
public class ReferenceCache {

    private static Logger logger = Logger.getLogger(ReferenceCache.class);

    /**
     * The file the cache will be serialized into.
     */
    private final File cacheFile;

    private Map<String, List<String>> resourceToTargetURIListMap = new LinkedHashMap<String, List<String>>();

    /**
     * Constructor requiring to set the file to persist the cache to and to load from if the file is
     * available.
     *
     * @param cacheFilePath
     *            Location of the file to be used for serializing the cache. Will be created if
     *            non-existent.
     */
    public ReferenceCache(String cacheFilePath) {
        this.cacheFile = new File(cacheFilePath);
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
        List<String> cachedList = resourceToTargetURIListMap.get(resource.getURI().toString());
        if (cachedList != null) {
            resolveProxiesFromCache(resource, cachedList);
            return cachedList;
        }

        List<String> targetURIs = new ArrayList<String>();

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
                        resolve(resource, targetURIs, crElement);
                    }
                } else if (refValue instanceof EObject) {
                    EObject crElement = (EObject) refValue;
                    resolve(resource, targetURIs, crElement);
                } else {
                    throw new RuntimeException("Unknown object: " + refValue);
                }
            }
        }

        resourceToTargetURIListMap.put(resource.getURI().toString(), targetURIs);
        return targetURIs;
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
     * Persist the cache in the file system.
     */
    public void save() {
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
     * Load the chache if it was persisted before.
     */
    @SuppressWarnings("unchecked")
    public void load() {
        if (!cacheFile.exists() && !cacheFile.canRead()) {
            return;
        }

        ObjectInputStream oos = null;
        try {
            oos = new ObjectInputStream(new FileInputStream(cacheFile));
            resourceToTargetURIListMap = (Map<String, List<String>>) oos.readObject();
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
    public void resolveProxiesFromCache(Resource resource, List<String> targetURIList) {

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
                        EObject targetObject = getTarget(resource, targetURI);
                        list.set(i, targetObject);
                        index++;
                    }
                } else if (refValue instanceof EObject) {
                    String targetURI = targetURIList.get(index);
                    EObject targetObject = getTarget(resource, targetURI);
                    nextElement.eSet(eReference, targetObject);
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
}
