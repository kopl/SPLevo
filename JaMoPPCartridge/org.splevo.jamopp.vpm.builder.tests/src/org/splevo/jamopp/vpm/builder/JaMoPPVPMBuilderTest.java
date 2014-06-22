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
package org.splevo.jamopp.vpm.builder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.diffing.JaMoPPDiffer;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Tests for the JaMoPP specific VPM generation.
 *
 */
public class JaMoPPVPMBuilderTest {

    /** The logger to use for this class. */
    @SuppressWarnings("unused")
    private Logger logger = Logger.getLogger(JaMoPPVPMBuilderTest.class);

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void setUp() {
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * Test to build a vpm model from a diffing result.
     *
     * @throws Exception
     *             An unexpected failure during the test execution.
     */
    @Test
    public void test() throws Exception {

        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        String basePath = "testcode/";
        List<String> urisA = Lists.newArrayList(new File(basePath + "a").getAbsolutePath());
        List<String> urisB = Lists.newArrayList(new File(basePath + "b").getAbsolutePath());
        NullProgressMonitor monitor = new NullProgressMonitor();
        ResourceSet setA = extractor.extractSoftwareModel(urisA, monitor, null);
        ResourceSet setB = extractor.extractSoftwareModel(urisB, monitor, null);

        String ignorePackages = buildIgnorePackages();

        Map<String, String> diffOptions = Maps.newLinkedHashMap();
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_IGNORE_PACKAGES, ignorePackages);

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(setA, setB, diffOptions);
        assertThat("Wrong number of differences", comparison.getDifferences().size(), is(5));

        JaMoPPVPMBuilder builder = new JaMoPPVPMBuilder();
        VariationPointModel vpm = builder.buildVPM(comparison, "leading", "integration");
        assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), is(5));
    }

    /**
     * Build the configuration string for the packages to ignore.
     *
     * @return The regular expressions for the packages to ignore.
     */
    private String buildIgnorePackages() {
        StringBuilder sb = new StringBuilder();
        sb.append("java.*");
        sb.append(System.getProperty("line.separator"));
        sb.append("org.jscience.*");
        sb.append(System.getProperty("line.separator"));
        sb.append("javolution.*");
        String ignorePackages = sb.toString();
        return ignorePackages;
    }

}
