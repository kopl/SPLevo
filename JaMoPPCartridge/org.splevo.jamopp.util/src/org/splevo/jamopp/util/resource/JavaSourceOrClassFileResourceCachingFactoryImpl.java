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

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.resource.JavaSourceOrClassFileResourceFactoryImpl;

/**
 * Factory for creating cache enabled JaMoPP java and class file resource.
 */
public class JavaSourceOrClassFileResourceCachingFactoryImpl extends JavaSourceOrClassFileResourceFactoryImpl {

    /** The directories for reference cache files. */
    private List<String> cacheDirectories = null;

    /**
     * Constructor to set the base directory for cache files.
     *
     * @param cacheDirectories
     *            The absolute paths of directories containing cache files.
     */
    public JavaSourceOrClassFileResourceCachingFactoryImpl(List<String> cacheDirectories) {
        super();
        this.cacheDirectories = cacheDirectories;
    }

    /**
     * Create a cache enabled resource.
     *
     * {@inheritDoc}
     */
    @Override
    public Resource createResource(URI uri) {
        return new JavaSourceOrClassFileCachingResource(cacheDirectories, uri);
    }
}
