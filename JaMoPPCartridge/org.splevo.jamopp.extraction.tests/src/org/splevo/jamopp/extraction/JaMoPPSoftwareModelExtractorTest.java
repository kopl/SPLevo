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
package org.splevo.jamopp.extraction;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.containers.CompilationUnit;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.extraction.SoftwareModelExtractionException;

import com.google.common.collect.Lists;

/**
 * Test case for basic functionality of the JaMoPP based extractor.
 */
public class JaMoPPSoftwareModelExtractorTest {

    private Logger logger = Logger.getLogger(JaMoPPSoftwareModelExtractorTest.class);

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void setUp() {
        // set up a basic logging configuration for the test environment
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * Test extraction functionality.
     *
     * Proof the number of resources resulting from the parsed test project.
     *
     * @throws SoftwareModelExtractionException
     *             for any exception during the extraction process.
     */
    @Test
    public void testExtractSoftwareModel() throws SoftwareModelExtractionException {

        String testProjectPath = "testcode/project/calculator-jscience";

        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        List<String> projectPaths = Lists.newArrayList(new File(testProjectPath).getAbsolutePath());
        ResourceSet extractionResult = extractor.extractSoftwareModel(projectPaths, new NullProgressMonitor(), null);

        assertThat(extractionResult, notNullValue());

        // check the compilation unit count
        int projectResourceCount = 0;
        List<Resource> projectResources = new ArrayList<Resource>();
        for (Resource resource : extractionResult.getResources()) {
            String[] segments = resource.getURI().segments();
            if ("calculator".equals(segments[segments.length - 2])) {
                projectResourceCount++;
                projectResources.add(resource);
            }
            for (EObject topLevelEObject : resource.getContents()) {
                if (!(topLevelEObject instanceof CompilationUnit)) {
                    logger.info("TopLevelEObject: " + topLevelEObject.getClass().getSimpleName());
                }
            }
        }

        int expectedTestClasses = 3;
        assertThat(projectResourceCount, equalTo(expectedTestClasses));
    }

    /**
     * Test a valid id to be returned.
     */
    @Test
    public void testGetId() {
        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        assertThat(extractor.getId(), notNullValue());
    }

    /**
     * Test a valid label to be returned.
     */
    @Test
    public void testGetLabel() {
        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        assertThat(extractor.getLabel(), notNullValue());
    }

}
