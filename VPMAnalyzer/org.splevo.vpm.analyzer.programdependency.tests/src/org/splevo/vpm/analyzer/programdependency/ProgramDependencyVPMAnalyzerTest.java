/**
 * 
 */
package org.splevo.vpm.analyzer.programdependency;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.splevo.tests.SPLevoTestUtil;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.graph.VPMGraph;

/**
 * Test case for the ProgramDependencyVPMAnalyzer.
 * 
 * @author Benjamin Klatt
 */
public class ProgramDependencyVPMAnalyzerTest {
    
    /** An instance of the analyzer for general tests. */
    private final ProgramDependencyVPMAnalyzer analyzer = new ProgramDependencyVPMAnalyzer();

    /**
     * Test method for
     * {@link org.splevo.vpm.analyzer.programdependency.ProgramDependencyVPMAnalyzer#analyze(org.splevo.vpm.analyzer.graph.VPMGraph)}
     * .
     * 
     * @throws IOException
     *             Identifies the test input graph could not be read.
     * @throws InterruptedException
     *             identifies the test has been interrupted.
     */
    @Test
    public void testAnalyze() throws IOException, InterruptedException {
        
        VPMGraph graph = SPLevoTestUtil.loadGCDVPMGraph();
        
        int originalNodeCount = graph.getNodeCount();
        int originalEdgeCount = graph.getEdgeCount();
        
        VPMAnalyzerResult result = analyzer.analyze(graph);
        
        assertNotNull("The analyzer result must not be null",result);
        assertEquals("The graph's node count should not have been changed.", originalNodeCount, graph.getNodeCount());
        assertEquals("The graph's edge count should not have been changed.", originalEdgeCount, graph.getEdgeCount());

        assertEquals("Wrong edge descriptor count", 2, result.getEdgeDescriptors().size());
        
    }

    /**
     * Test method for {@link org.splevo.vpm.analyzer.programdependency.ProgramDependencyVPMAnalyzer#getAvailableConfigurations()}.
     */
    @Test
    public void testGetAvailableConfigurations() {
        assertNotNull("Null available configuration map is not allowed", analyzer.getAvailableConfigurations());
    }

    /**
     * Test method for {@link org.splevo.vpm.analyzer.programdependency.ProgramDependencyVPMAnalyzer#getConfigurationLabels()}.
     */
    @Test
    public void testGetConfigurationLabels() {
        assertNotNull("Null configuration label map is not allowed", analyzer.getConfigurationLabels());
    }

    /**
     * Test method for {@link org.splevo.vpm.analyzer.programdependency.ProgramDependencyVPMAnalyzer#getConfigurations()}.
     */
    @Test
    public void testGetConfigurations() {
        assertNotNull("Null configuration map is not allowed", analyzer.getConfigurations());
    }

    /**
     * Test method for {@link org.splevo.vpm.analyzer.programdependency.ProgramDependencyVPMAnalyzer#getName()}.
     */
    @Test
    public void testGetName() {
        assertNotNull("Null name is not allowed", analyzer.getName());
        assertTrue("Empty name not allowed", analyzer.getName().length() > 0);
    }

    /**
     * Test method for {@link org.splevo.vpm.analyzer.programdependency.ProgramDependencyVPMAnalyzer#getRelationshipLabel()}.
     */
    @Test
    public void testGetRelationshipLabel() {
        assertNotNull("Null label is not allowed", analyzer.getRelationshipLabel());
        assertTrue("Empty label not allowed", analyzer.getRelationshipLabel().length() > 0);
    }

}
