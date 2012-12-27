/**
 * 
 */
package org.splevo.vpm.analyzer.codestructure;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.splevo.tests.SPLevoTestUtil;
import org.splevo.vpm.analyzer.graph.VPMGraph;

/**
 * Test case for the code structure vpm analyzer.
 * 
 * @author Benjamin Klatt
 *
 */
public class CodeStructureVPMAnalyzerTest extends AbstractTest {

    /**
     * Test method for {@link org.splevo.vpm.analyzer.codestructure.CodeStructureVPMAnalyzer#analyze(org.splevo.vpm.analyzer.graph.VPMGraph)}.
     * @throws InterruptedException identifies the test was interrupted.
     * @throws IOException Identifies the initial vpm graph could not be loaded.
     */
    @Test
    public void testAnalyze() throws IOException, InterruptedException {
        VPMGraph graph = SPLevoTestUtil.loadGCDVPMGraph();
        
        int originalNodeCount = graph.getNodeCount();
        
        CodeStructureVPMAnalyzer analyzer = new CodeStructureVPMAnalyzer();
        analyzer.analyze(graph);
        
        assertNotNull("The should not be null",graph);
        assertEquals("The node count should not have been changed.", originalNodeCount, graph.getNodeCount());

        
        // TODO: Check import add/delete parent node structure
        assertEquals("Wrong edge count", 6, graph.getEdgeCount());

//        graph.display();
//        while (true) {
//            Thread.sleep(2000);
//        }
        
    }

    /**
     * Test method for {@link org.splevo.vpm.analyzer.codestructure.CodeStructureVPMAnalyzer#getName()}.
     */
    @Test
    public void testGetName() {
        CodeStructureVPMAnalyzer anlayzer = new CodeStructureVPMAnalyzer();
        assertNotNull("The analyzer name must not be not.",anlayzer.getName());
    }

    /**
     * Test method for {@link org.splevo.vpm.analyzer.codestructure.CodeStructureVPMAnalyzer#getRelationshipLabel()}.
     */
    @Test
    public void testGetRelationshipLabel() {
        CodeStructureVPMAnalyzer anlayzer = new CodeStructureVPMAnalyzer();
        assertNotNull("The analyzer relationship label must not be not.",anlayzer.getRelationshipLabel());
    }

}
