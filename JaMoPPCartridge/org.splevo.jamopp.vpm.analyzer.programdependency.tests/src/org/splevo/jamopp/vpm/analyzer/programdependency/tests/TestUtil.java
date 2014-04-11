/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.jamopp.vpm.analyzer.programdependency.tests;

import java.io.File;
import java.util.LinkedHashMap;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.splevo.jamopp.diffing.JaMoPPDiffer;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;
import org.splevo.jamopp.vpm.builder.JaMoPPVPMBuilder;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Utilities for {@link JaMoPPProgramDependencyAnalyzerTest} cases.
 */
public final class TestUtil {

    /** Disabled constructor to force static utility usage. */
    private TestUtil() {
    }

    /**
     * Initialize the Log4j basic logging environment to enable logging in unit tests.
     */
    public static void initLogging() {
        // set up a basic logging configuration for the test environment
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * Initialize a variation point model graph to be analyzed based on the code provided at two
     * paths (paths relative to the unit test working directory.)
     *
     * @param relativePathA
     *            Version a of the source code.
     * @param relativePathB
     *            Version b of the source code.
     * @return The loaded VPM graph resulting from extractio, diffing and VPM initialization.
     * @throws Exception
     *             Graph preparation failed.
     */
    public static VPMGraph prepareVPMGraph(String relativePathA, String relativePathB) throws Exception {
        String pathA = new File(relativePathA).getCanonicalPath();
        String pathB = new File(relativePathB).getCanonicalPath();
        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        ResourceSet setA = extractor.extractSoftwareModel(Lists.newArrayList(pathA), new NullProgressMonitor());
        ResourceSet setB = extractor.extractSoftwareModel(Lists.newArrayList(pathB), new NullProgressMonitor());

        JaMoPPDiffer differ = new JaMoPPDiffer();
        LinkedHashMap<String, String> newLinkedHashMap = Maps.newLinkedHashMap();
        Comparison diff = differ.doDiff(setA, setB, newLinkedHashMap);

        JaMoPPVPMBuilder builder = new JaMoPPVPMBuilder();
        VariationPointModel vpm = builder.buildVPM(diff, "a", "b");

        DefaultVPMAnalyzerService service = new DefaultVPMAnalyzerService();
        VPMGraph graph = service.initVPMGraph(vpm);
        return graph;
    }
}
