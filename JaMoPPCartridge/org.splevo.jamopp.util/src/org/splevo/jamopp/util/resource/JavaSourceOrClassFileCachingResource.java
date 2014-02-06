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
package org.splevo.jamopp.util.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
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
import org.emftext.language.java.resource.JavaSourceOrClassFileResource;

/***
 * JaMoPP java resource using an internal cache for reference resolving.
 */
public class JavaSourceOrClassFileCachingResource extends JavaSourceOrClassFileResource {

    private static Logger logger = Logger.getLogger(JavaSourceOrClassFileCachingResource.class);

    /** The base directories for reference cache files. */
    private List<String> cacheDirectories = new ArrayList<String>();

    /**
     * Constructor to set the potential cache directories. If none is provided no cache will be
     * used. If multiple exist, the resource will check all of them for a matching cache file. If a
     * new cache file must be created, it will be placed in the first of the directories.
     *
     * @param cacheDirectories
     *            Absolute paths of directories containing the resources cache files.
     * @param uri
     *            The uri identifying this resource.
     */
    public JavaSourceOrClassFileCachingResource(List<String> cacheDirectories, URI uri) {
        super(uri);
        if (cacheDirectories != null) {
            this.cacheDirectories.addAll(cacheDirectories);
        }
    }

    /**
     * Adapted loading implementation resolving the references from cache if available after the
     * resource itself has been loaded.
     *
     * If no cacheDirectory was provided (null value), no caching will be used.
     *
     * {@inheritDoc}
     */
    @Override
    protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
        super.doLoad(inputStream, options);

        File cacheFile = getCacheFile();
        if (cacheFile != null && cacheFile.exists() && cacheFile.canRead()) {
            List<String> cachedTargetURIs = loadCache(cacheFile);
            resolveProxiesFromCache(cachedTargetURIs);
            logger.info("references loaded from cache.");
            return;
        }

        List<String> targetURIs = resolveProxies();

        if (cacheFile != null) {
            saveCache(cacheFile, targetURIs);
        }

    }

    /**
     * Resolve the proxies without any caching involved and return the results.
     *
     * @return The list of resolved target URIs
     */
    @SuppressWarnings("unchecked")
    private List<String> resolveProxies() {
        List<String> targetURIs = new ArrayList<String>();

        for (Iterator<EObject> elementIt = getAllContents(); elementIt.hasNext();) {
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
                        resolve(targetURIs, crElement);
                    }
                } else if (refValue instanceof EObject) {
                    EObject crElement = (EObject) refValue;
                    resolve(targetURIs, crElement);
                } else {
                    throw new RuntimeException("Unknown object: " + refValue);
                }
            }
        }
        return targetURIs;
    }

    /**
     * Resolve a model element and store it's new target uri in the list of uris.
     *
     * @param targetURIs
     *            The list of target URIs to fill.
     * @param crElement
     *            The element to resolve (might be a proxy)
     */
    private void resolve(List<String> targetURIs, EObject crElement) {
        crElement = EcoreUtil.resolve(crElement, this);
        if (crElement.eIsProxy()) {
            targetURIs.add(null);
        } else {
            Resource targetResource = crElement.eResource();
            targetURIs.add(targetResource.getURI().toString() + "#" + targetResource.getURIFragment(crElement));
        }
    }

    /**
     * Try to resolve the proxies in the resource with a list of target URIs.
     *
     * @param targetURIList
     *            The list of target URIs to use for the resolution.
     */
    @SuppressWarnings("unchecked")
    public void resolveProxiesFromCache(List<String> targetURIList) {

        int index = 0;
        for (Iterator<EObject> elementIt = EcoreUtil.getAllContents(this, true); elementIt.hasNext();) {
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
                            EObject targetObject = getTarget(targetURI);
                            list.set(i, targetObject);
                        } else {
                            logger.warn("Element is not resolved (null) in cache: " + targetURI);
                        }
                        index++;
                    }
                } else if (refValue instanceof EObject) {
                    String targetURI = targetURIList.get(index);
                    if (targetURI != null) {
                        EObject targetObject = getTarget(targetURI);
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
     * @param targetURI
     *            The URI to resolve the target object with.
     * @return The resolved EObject.
     */
    private EObject getTarget(String targetURI) {
        URI createURI = URI.createURI(targetURI);
        ResourceSet resourceSet = getResourceSet();
        EObject target = resourceSet.getEObject(createURI, true);
        return target;
    }

    /**
     * Load the cached references.
     *
     * @param cacheFile
     *            The file to read the cache from.
     * @return the list of target URIs loaded from cache. Null if cache could not be loaded.
     */
    @SuppressWarnings("unchecked")
    private List<String> loadCache(File cacheFile) {

        ObjectInputStream oos = null;
        List<String> references = null;
        try {
            oos = new ObjectInputStream(new FileInputStream(cacheFile));
            references = (List<String>) oos.readObject();
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

        return references;
    }

    /**
     * Persist the cache in the file system.
     *
     * @param cacheFile
     *            The cache file to write to.
     * @param targetURIs
     *            The list of (un-)resolved reference target URIs.
     */
    public void saveCache(File cacheFile, List<String> targetURIs) {
        ObjectOutputStream oos = null;
        try {
            FileUtils.forceMkdir(cacheFile.getParentFile());
            oos = new ObjectOutputStream(new FileOutputStream(cacheFile));
            oos.writeObject(targetURIs);
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
     * Get the cache file to use.
     *
     * If none exist in one of the provided directories, a file handler of the non existing file is
     * created for the first one. If no directories have been provided at all, null will be
     * returned.
     *
     * @return The file handle or null if no file directory is configured.
     */
    private File getCacheFile() {

        String fileName = this.getURI().hashCode() + ".cache";
        File cacheFile = null;
        for (String directory : cacheDirectories) {
            File candidate = new File(directory + File.separator + fileName);
            if (candidate != null && candidate.exists() && candidate.canRead()) {
                cacheFile = candidate;
            }
        }

        if (cacheFile == null && cacheDirectories != null && cacheDirectories.size() > 0) {
            cacheFile = new File(cacheDirectories.get(0) + File.separator + fileName);
        }

        return cacheFile;
    }

}
