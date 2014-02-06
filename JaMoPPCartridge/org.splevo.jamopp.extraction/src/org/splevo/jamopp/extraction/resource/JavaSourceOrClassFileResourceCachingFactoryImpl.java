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
package org.splevo.jamopp.extraction.resource;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.resource.JavaSourceOrClassFileResourceFactoryImpl;
import org.splevo.jamopp.extraction.cache.ReferenceCache;

/**
 * Factory for creating cache enabled JaMoPP java and class file resource.
 */
public class JavaSourceOrClassFileResourceCachingFactoryImpl extends JavaSourceOrClassFileResourceFactoryImpl {

    /** The reference cache to use. */
    private ReferenceCache referenceCache = null;

    /**
     * Constructor to set the base directory for internal reference cache.
     *
     * @param cacheDirectories
     *            The absolute paths of directories containing cache files.
     */
    public JavaSourceOrClassFileResourceCachingFactoryImpl(List<String> cacheDirectories) {
        super();
        referenceCache = new ReferenceCache(cacheDirectories);
    }

    /**
     * Create a cache enabled resource.
     *
     * {@inheritDoc}
     */
    @Override
    public Resource createResource(URI uri) {
        return new JavaSourceOrClassFileCachingResource(referenceCache, uri);
    }

    /**
     * Access the internal cache.
     *
     * @return The internally used cache.
     */
    public ReferenceCache getReferenceCache() {
        return referenceCache;
    }
}
