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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emftext.language.java.JavaClasspath;
import org.emftext.language.java.JavaPackage;
import org.emftext.language.java.resource.java.IJavaOptions;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;
import org.splevo.jamopp.extraction.resource.JavaSourceOrClassFileResourceCachingFactoryImpl;

import com.google.common.collect.Lists;

/**
 * Test case to ensure the valid resolution as well as saving and loading of the cache.
 */
public class ReferenceCacheTest {

    private TemporaryFolder folder = null;

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void setUpBefore() {
        // set up a basic logging configuration for the test environment
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * Prepare the test.
     * 
     * @throws IOException
     *             Failed to create required resources.
     */
    @Before
    public void setUp() throws IOException {
        folder = new TemporaryFolder();
        folder.create();
    }

    /** Clean up afterwards. */
    @After
    public void tearDown() {
        folder.delete();
    }

    /**
     * Test to create a cache while resolving a resource and reload the same code a second time with
     * references resolved from the cache.
     * 
     * @throws IOException
     *             Failed to create the cache directory.
     */
    @Test
    public void testResolve() throws IOException {
        File cacheDir = folder.newFolder("cacheDir");
        ArrayList<String> cacheFileDirs = Lists.newArrayList(cacheDir.getAbsolutePath());

        String resourcePath = "test/cache/basic/CalculatorSqrt.java";
        String jarPath = "test/cache/basic/lib/jscience.jar";
        String jarPathAbsolute = new File(jarPath).getAbsolutePath();
        List<String> jarPaths = Lists.newArrayList(jarPathAbsolute);

        ResourceSet rs = prepareResourceSetAsDoneByExtractor(cacheFileDirs, jarPaths);
        ReferenceCache referenceCache = loadCache(rs, resourcePath);
        referenceCache.save();

        // resolve the resource a second time and use the now pre-loaded cache
        ResourceSet resourceSet = initNewResourceSet(cacheFileDirs);
        ReferenceCache referenceCacheNew = getReferenceCache(resourceSet);
        Resource resource = resourceSet.getResource(URI.createFileURI(resourcePath), true);
        referenceCacheNew.resolve(resource);

        int uncachedProxies = referenceCacheNew.getNotResolvedFromCacheCounter();

        assertThat("No cache file created", cacheDir.listFiles().length, is(1));
        assertThat("Not all references could be resolved from cache", uncachedProxies, is(0));

    }

    /**
     * Prepare a resource set in the same way as the extractor for extraction (e.g. without layout
     * information).
     * 
     * Initialize ("warm up") the cache by parsing the resource the first time.
     * 
     * @param rs
     *            The resource set to initialize the cache for.
     * @param resourcePath
     *            The path to the java resource (file) to load.
     * @return The filled cache.
     */
    private ReferenceCache loadCache(ResourceSet rs, String resourcePath) {
        ReferenceCache referenceCache = getReferenceCache(rs);
        Resource resource = rs.getResource(URI.createFileURI(resourcePath), true);
        referenceCache.resolve(resource);
        return referenceCache;
    }

    /**
     * Configure the resource set in the same manner as the software model extractor.
     * 
     * @param cacheFileDirs
     *            The cache directories.
     * @param jarPaths
     *            The jar files to register.
     * @return The prepared resource set.
     */
    private ResourceSet prepareResourceSetAsDoneByExtractor(List<String> cacheFileDirs, List<String> jarPaths) {

        ResourceSet rs = new ResourceSetImpl();

        Map<Object, Object> options = rs.getLoadOptions();
        options.put(JavaClasspath.OPTION_USE_LOCAL_CLASSPATH, Boolean.TRUE);
        options.put(JavaClasspath.OPTION_REGISTER_STD_LIB, Boolean.TRUE);
        options.put(IJavaOptions.DISABLE_LAYOUT_INFORMATION_RECORDING, Boolean.TRUE);
        options.put(IJavaOptions.DISABLE_LOCATION_MAP, Boolean.TRUE);
        EPackage.Registry.INSTANCE.put("http://www.emftext.org/java", JavaPackage.eINSTANCE);
        JavaClasspath javaClasspath = JavaClasspath.get(rs);
        Map<String, Object> factoryMap = rs.getResourceFactoryRegistry().getExtensionToFactoryMap();
        factoryMap.put("java", new JavaSourceOrClassFileResourceCachingFactoryImpl(cacheFileDirs, javaClasspath,
                jarPaths, false));

        return rs;
    }

    /**
     * Get the reference cache of the factory registered in a {@link ResourceSet}. The method
     * assumes to have a cache enabled factory registered for .java files.
     * 
     * @param resourceSet
     *            The resource set to get the cache from.
     * @return The cache found.
     */
    private ReferenceCache getReferenceCache(ResourceSet resourceSet) {
        Map<String, Object> factoryMap = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
        JavaSourceOrClassFileResourceCachingFactoryImpl factory = (JavaSourceOrClassFileResourceCachingFactoryImpl) factoryMap
                .get("java");
        ReferenceCache referenceCache = factory.getReferenceCache();
        return referenceCache;
    }

    /**
     * Initialize a fresh resource set prepared by the java JaMoPP software model extractor to be
     * "cache enabled".
     * 
     * @param cacheFileDirs
     *            The cache directories.
     * @return The prepared resource set.
     */
    private ResourceSet initNewResourceSet(List<String> cacheFileDirs) {
        ResourceSet resourceSet = new ResourceSetImpl();
        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        extractor.prepareResourceSet(resourceSet, cacheFileDirs);
        return resourceSet;
    }

}
