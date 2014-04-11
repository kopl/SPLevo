package org.splevo.jamopp.vpm.analyzer.programdependency.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.vpm.analyzer.programdependency.JaMoPPProgramDependencyVPMAnalyzer;
import org.splevo.tests.SPLevoTestUtil;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMEdgeDescriptor;
import org.splevo.vpm.analyzer.graph.VPMGraph;

/**
 * Test for the JaMoPP specific program structure analyzer.
 */
public class JaMoPPProgramDependencyAnalyzerTest {

    private static Logger logger = Logger.getLogger(JaMoPPProgramDependencyAnalyzerTest.class);

    /** An instance of the analyzer for general tests. */
    private final JaMoPPProgramDependencyVPMAnalyzer analyzer = new JaMoPPProgramDependencyVPMAnalyzer();

    /**
     * Test method for
     * {@link org.splevo.vpm.analyzer.programstructure.ProgramStructureVPMAnalyzer#analyze(org.splevo.vpm.analyzer.graph.VPMGraph)}
     * .
     *
     * It actively registers the MoDiscoProgramStructureProvider to enable the test run as a regular
     * JUnit test without starting the OSGi Bundles.
     *
     * @throws Exception
     *             Identifies the test input graph could not be read.
     */
    @Test
    public void testAnalyze() throws Exception {

        VPMGraph graph = SPLevoTestUtil.loadGCDVPMGraph();

        int originalNodeCount = graph.getNodeCount();
        int originalEdgeCount = graph.getEdgeCount();

        VPMAnalyzerResult result = analyzer.analyze(graph);

        assertNotNull("The analyzer result must not be null", result);
        assertEquals("The graph's node count should not have been changed.", originalNodeCount, graph.getNodeCount());
        assertEquals("The graph's edge count should not have been changed.", originalEdgeCount, graph.getEdgeCount());

        // This output should only be used for local test analysis.
        // It should not be committed to not mess up the build server
        for (VPMEdgeDescriptor edgeDescriptor : result.getEdgeDescriptors()) {
            logger.debug("Edge Sub Label: " + edgeDescriptor.getRelationshipSubLabel());
        }

        assertThat("Wrong edge descriptor count", result.getEdgeDescriptors().size(), is(11));

    }

    /**
     * Test that a valid configuration set is returned by the analyzer.
     */
    @Test
    public void testGetConfigurations() {
        assertNotNull("Null for configurations is not allowed", analyzer.getConfigurations());
    }

    /**
     * Test that the analyzer returns a valid name.
     */
    @Test
    public void testGetName() {
        assertNotNull("Null name is not allowed", analyzer.getName());
        assertTrue("Empty name not allowed", analyzer.getName().length() > 0);
    }

    /**
     * Test that the analyzer returns a valid relationship label.
     */
    @Test
    public void testGetRelationshipLabel() {
        assertNotNull("Null label is not allowed", analyzer.getRelationshipLabel());
        assertTrue("Empty label not allowed", analyzer.getRelationshipLabel().length() > 0);
    }

    /**
     * Initialize the logging infrastructure.
     */
    @BeforeClass
    public static void setUp() {
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

}
