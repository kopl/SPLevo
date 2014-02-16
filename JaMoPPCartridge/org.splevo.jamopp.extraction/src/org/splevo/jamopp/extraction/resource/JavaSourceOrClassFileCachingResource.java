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

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.emftext.language.java.resource.JavaSourceOrClassFileResource;
import org.splevo.jamopp.extraction.cache.ReferenceCache;

/***
 * JaMoPP java resource using an internal cache for reference resolving.
 */
public class JavaSourceOrClassFileCachingResource extends JavaSourceOrClassFileResource {

    /** The reference cache to resolve proxies. */
    private ReferenceCache referenceCache = null;

    /** Flag if the resource's references should be resolved as soon as doLoad is finished. */
    private boolean resolveImmediately = true;

    /**
     * Constructor to set the reference cache the resource should use for resolving.
     * 
     * @param uri
     *            The URI identifying this resource.
     * @param referenceCache
     *            The reference cache to use. If null is provided no cache is used.
     */
    public JavaSourceOrClassFileCachingResource(URI uri, ReferenceCache referenceCache) {
        this(uri, referenceCache, true);
    }

    /**
     * Constructor to set the reference cache the resource should use for resolving.
     * 
     * @param uri
     *            The URI identifying this resource.
     * @param referenceCache
     *            The reference cache to use. If null is provided no cache is used.
     * @param resolveImmediately
     *            Flag if the resource's references should be resolved as soon as doLoad is
     *            finished.
     */
    public JavaSourceOrClassFileCachingResource(URI uri, ReferenceCache referenceCache, boolean resolveImmediately) {
        super(uri);
        this.referenceCache = referenceCache;
        this.resolveImmediately = resolveImmediately;
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

        if (resolveImmediately && referenceCache != null) {
            referenceCache.resolve(this);
        }
    }
}
