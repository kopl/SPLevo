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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.JavaClasspath;
import org.splevo.extraction.SoftwareModelExtractionException;
import org.splevo.jamopp.diffing.JaMoPPDiffer;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;
import org.splevo.jamopp.vpm.analyzer.programdependency.ConfigurationBuilder;
import org.splevo.jamopp.vpm.analyzer.programdependency.JaMoPPProgramDependencyVPMAnalyzer;
import org.splevo.jamopp.vpm.analyzer.programdependency.references.DependencyType;
import org.splevo.jamopp.vpm.analyzer.programdependency.references.ReferenceSelectorRegistry;
import org.splevo.jamopp.vpm.builder.JaMoPPVPMBuilder;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.VPMAnalyzerException;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMEdgeDescriptor;
import org.splevo.vpm.analyzer.config.ChoiceConfiguration;
import org.splevo.vpm.analyzer.config.VPMAnalyzerConfigurationSet;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.base.Predicates;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;

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
     * Initialize a variation point model graph to be analyzed.<br>
     * The two code copies are assumed to be in sub directories named "a" and "b" of the provided
     * base path.
     *
     * The base path itself must be relative to the unit test working directory.
     *
     * @param testBasePath
     *            The relative base path ending with a "/".
     * @return The loaded VPM graph resulting from extraction, difference analysis and VPM
     *         initialization.
     * @throws Exception
     *             Graph preparation failed.
     */
    public static VPMGraph prepareVPMGraph(String testBasePath) throws Exception {
        return prepareVPMGraph(testBasePath + "a", testBasePath + "b");
    }

    /**
     * Initialize a variation point model graph to be analyzed based on the code provided at two
     * paths (paths relative to the unit test working directory.)
     *
     * @param relativePathA
     *            Version a of the source code.
     * @param relativePathB
     *            Version b of the source code.
     * @return The loaded VPM graph resulting from extraction, difference analysis and VPM
     *         initialization.
     * @throws Exception
     *             Graph preparation failed.
     */
    public static VPMGraph prepareVPMGraph(String relativePathA, String relativePathB) throws Exception {
        String pathA = new File(relativePathA).getCanonicalPath();
        String pathB = new File(relativePathB).getCanonicalPath();
        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractorWithJarHandling();
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

    /**
     * Analyze a graph with a program dependency analyzer in ROBILLARD EXTENDED SHARED ACCESS MODE.
     *
     * @param graph
     *            The graph to analyze.
     * @return The analysis result.
     * @throws VPMAnalyzerException
     *             An error during analysis.
     */
    public static VPMAnalyzerResult analyzeExtendedSharedAccess(VPMGraph graph) throws VPMAnalyzerException {
        JaMoPPProgramDependencyVPMAnalyzer analyzer = configureRobillardAnalyzer(true, true);
        VPMAnalyzerResult result = analyzer.analyze(graph);
        return result;
    }

    /**
     * Assert the number of detected dependencies in a result object.
     *
     * @param result
     *            The result object to check.
     * @param count
     *            The expected count of detected dependencies.
     */
    public static void assertDependencyCount(VPMAnalyzerResult result, int count) {
        assertThat("Wrong number of relationships", result.getEdgeDescriptors().size(), is(count));
    }

    /**
     * Convenience method to check detected dependencies if only one type is expected.
     *
     * @param result
     *            The result to check
     * @param type
     *            The expected type
     * @param count
     *            The expected amount of the type
     */
    public static void assertDependency(VPMAnalyzerResult result, DependencyType type, Integer count) {
        Map<DependencyType, Integer> dependencies = Maps.newHashMap();
        dependencies.put(type, count);
        assertDependencies(result, dependencies);
    }

    /**
     * Convenience method to check dependencies.
     *
     * @param result
     *            The result to check.
     * @param dependencies
     *            The expected dependencies and their count.
     */
    public static void assertDependencies(VPMAnalyzerResult result, Map<DependencyType, Integer> dependencies) {
        assertEdgeInfos(result, JaMoPPProgramDependencyVPMAnalyzer.EDGE_INFO_DEPENDENCY_TYPE, dependencies);
    }

    /**
     * Check that an expected number of edge descriptors with a specific result are contained in the
     * result object.
     *
     * All edges contained in the result will be checked for an relation ship info identified by the
     * submitted infoKey.
     *
     * First, it is checked that no info value has been detected which was not expected.<br>
     * Then, it is checked that all expected info values are detected in the expected amount.
     *
     * @param result
     *            The result to prove.
     * @param infoKey
     *            The key of the info to check.
     * @param infoValues
     *            The info values to check with the expected occurrences.
     */
    public static void assertEdgeInfos(VPMAnalyzerResult result, String infoKey, Map<?, Integer> infoValues) {
        Multiset<Object> detectedInfoValues = HashMultiset.create();
        for (VPMEdgeDescriptor edge : result.getEdgeDescriptors()) {
            detectedInfoValues.add(edge.getRelationShipInfos().get(infoKey));
        }

        for (Object detectedInfo : detectedInfoValues.elementSet()) {
            if (!infoValues.containsKey(detectedInfo)) {
                fail("Unexpected value: " + detectedInfo);
            }
        }

        for (Object expectedValue : infoValues.keySet()) {
            int is = detectedInfoValues.count(expectedValue);
            int expected = infoValues.get(expectedValue);
            assertThat("Wrong count for " + expectedValue, is, is(expected));
        }
    }

    /**
     * Assert the number of nodes contained in a graph.
     *
     * @param graph
     *            The graph to check.
     * @param nodeCount
     *            The expected number of nodes.
     */
    public static void assertNodeCount(VPMGraph graph, int nodeCount) {
        assertThat("Wrong number of graph nodes", graph.getNodeSet().size(), is(nodeCount));
    }

    /**
     * Get a configured program dependency analyzer in ROBILLAR_EXTENDED mode.
     *
     * @param extendedMode
     *            Flag if the extended robillard dependencies must be used.
     * @param sharedAccess
     *            Flag if shared accesses should be considered.
     *
     * @return The ready to use analyzer.
     */
    public static JaMoPPProgramDependencyVPMAnalyzer configureRobillardAnalyzer(boolean extendedMode,
            boolean sharedAccess) {
        JaMoPPProgramDependencyVPMAnalyzer analyzer = new JaMoPPProgramDependencyVPMAnalyzer();

        VPMAnalyzerConfigurationSet configurations = analyzer.getConfigurations();
        String groupId = ConfigurationBuilder.CONFIG_GROUP_DEPENDENCIES;
        String configId = ConfigurationBuilder.CONFIG_ID_REFERENCE_SELECTOR;
        ChoiceConfiguration configuration = (ChoiceConfiguration) configurations.getConfiguration(groupId, configId);
        if (extendedMode && sharedAccess) {
            configuration.setCurrentValue(ReferenceSelectorRegistry.ROBILLARD_EXTENDED_SHARED_ACCESS_SELECTOR);
        } else if (extendedMode) {
            configuration.setCurrentValue(ReferenceSelectorRegistry.ROBILLARD_EXTENDED_SELECTOR);
        } else {
            configuration.setCurrentValue(ReferenceSelectorRegistry.ROBILLARD_SELECTOR);
        }

        return analyzer;
    }
    
    /**
     * Modified software model extractor that can handle jar files in the project directory.
     */
    private static class JaMoPPSoftwareModelExtractorWithJarHandling extends JaMoPPSoftwareModelExtractor {
        
        private static final Logger LOGGER = Logger.getLogger(JaMoPPSoftwareModelExtractorWithJarHandling.class);
        
        @Override
        protected List<Resource> loadProjectJavaFiles(ResourceSet targetResourceSet, Iterable<String> projectPaths)
                throws SoftwareModelExtractionException {
            JavaClasspath classpath = (JavaClasspath) Iterables.find(targetResourceSet.eAdapters(),
                    Predicates.instanceOf(JavaClasspath.class));
            for (String jarFilePath : getAllJarFiles(projectPaths)) {
                classpath.registerClassifierJar(URI.createFileURI(jarFilePath));
            }
            return super.loadProjectJavaFiles(targetResourceSet, projectPaths);
        }

        private List<String> getAllJarFiles(Iterable<String> projectPaths) {
            List<String> jarPaths = Lists.newArrayList();
            for (String projectPath : projectPaths) {
                Collection<File> jarFiles = FileUtils.listFiles(new File(projectPath), new String[] { "jar" }, true);
                for (File jarPath : jarFiles) {
                    try {
                        jarPaths.add(jarPath.getCanonicalPath());
                    } catch (IOException e) {
                        LOGGER.warn("Unable to access jar file: " + jarPath);
                    }
                }
            }
            return jarPaths;
        }

    }
}
