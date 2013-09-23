/**
 * 
 */
package org.splevo.vpm.analyzer.programstructure;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.splevo.tests.AbstractTest;
import org.splevo.tests.SPLevoTestUtil;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMEdgeDescriptor;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.analyzer.programstructure.ProgramStructureVPMAnalyzer;

/**
 * Test case for the ProgramDependencyVPMAnalyzer.
 * 
 * @author Benjamin Klatt
 */
public class ProgramStructureVPMAnalyzerTest extends AbstractTest {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(ProgramStructureVPMAnalyzerTest.class);
    
    /** An instance of the analyzer for general tests. */
    private final ProgramStructureVPMAnalyzer analyzer = new ProgramStructureVPMAnalyzer();

    /**
     * Test method for
     * {@link org.splevo.vpm.analyzer.programstructure.ProgramStructureVPMAnalyzer#analyze(org.splevo.vpm.analyzer.graph.VPMGraph)}
     * .
     * 
     * @throws IOException
     *             Identifies the test input graph could not be read.
     * @throws InterruptedException
     *             identifies the test has been interrupted.
     */
    @Test
    public void testAnalyze() throws Exception {
        
        VPMGraph graph = SPLevoTestUtil.loadGCDVPMGraph();
        
        int originalNodeCount = graph.getNodeCount();
        int originalEdgeCount = graph.getEdgeCount();
        
        VPMAnalyzerResult result = analyzer.analyze(graph);
        
        assertNotNull("The analyzer result must not be null",result);
        assertEquals("The graph's node count should not have been changed.", originalNodeCount, graph.getNodeCount());
        assertEquals("The graph's edge count should not have been changed.", originalEdgeCount, graph.getEdgeCount());

        for (VPMEdgeDescriptor edgeDescriptor : result.getEdgeDescriptors()) {
            logger.debug("ProgramStructure edge: "+edgeDescriptor.getRelationshipSubLabel());
        }
        
        assertEquals("Wrong edge descriptor count", 10, result.getEdgeDescriptors().size());
        
    }

    /**
     * Test method for {@link org.splevo.vpm.analyzer.programstructure.ProgramStructureVPMAnalyzer#getAvailableConfigurations()}.
     */
    @Test
    public void testGetAvailableConfigurations() {
        assertNotNull("Null available configuration map is not allowed", analyzer.getAvailableConfigurations());
    }

    /**
     * Test method for {@link org.splevo.vpm.analyzer.programstructure.ProgramStructureVPMAnalyzer#getConfigurationLabels()}.
     */
    @Test
    public void testGetConfigurationLabels() {
        assertNotNull("Null configuration label map is not allowed", analyzer.getConfigurationLabels());
    }

    /**
     * Test method for {@link org.splevo.vpm.analyzer.programstructure.ProgramStructureVPMAnalyzer#getConfigurations()}.
     */
    @Test
    public void testGetConfigurations() {
        assertNotNull("Null configuration map is not allowed", analyzer.getConfigurations());
    }

    /**
     * Test method for {@link org.splevo.vpm.analyzer.programstructure.ProgramStructureVPMAnalyzer#getName()}.
     */
    @Test
    public void testGetName() {
        assertNotNull("Null name is not allowed", analyzer.getName());
        assertTrue("Empty name not allowed", analyzer.getName().length() > 0);
    }

    /**
     * Test method for {@link org.splevo.vpm.analyzer.programstructure.ProgramStructureVPMAnalyzer#getRelationshipLabel()}.
     */
    @Test
    public void testGetRelationshipLabel() {
        assertNotNull("Null label is not allowed", analyzer.getRelationshipLabel());
        assertTrue("Empty label not allowed", analyzer.getRelationshipLabel().length() > 0);
    }

}
