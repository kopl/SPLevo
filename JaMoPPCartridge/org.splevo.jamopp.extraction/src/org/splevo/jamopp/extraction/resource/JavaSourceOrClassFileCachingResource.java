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

    /**
     * Constructor to set the reference cache the resource should use for resolving.
     *
     * @param referenceCache
     *            The reference cache to use. If null is provided no cache is used.
     * @param uri
     *            The URI identifying this resource.
     */
    public JavaSourceOrClassFileCachingResource(ReferenceCache referenceCache, URI uri) {
        super(uri);
        if (referenceCache != null) {
            this.referenceCache = referenceCache;
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

        if (referenceCache != null) {
            referenceCache.resolve(this);
        }
    }
}
