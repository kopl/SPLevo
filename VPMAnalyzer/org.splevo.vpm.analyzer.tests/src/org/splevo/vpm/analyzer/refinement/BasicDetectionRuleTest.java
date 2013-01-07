package org.splevo.vpm.analyzer.refinement;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.splevo.tests.SPLevoTestUtil;
import org.splevo.vpm.analyzer.AbstractTest;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.codestructure.CodeStructureVPMAnalyzer;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementType;

/**
 * Test case for the basic detection rule.
 * 
 * @author Benjamin Klatt
 * 
 */
public class BasicDetectionRuleTest extends AbstractTest {

    /**
     * Test method for
     * {@link org.splevo.vpm.analyzer.refinement.BasicDetectionRule#detect(org.splevo.vpm.analyzer.graph.VPMGraph)}
     * .
     * 
     * @throws InterruptedException
     *             Identifies the test has been stopped.
     * @throws IOException
     *             Identifies the test failed to load the test model.
     */
    @Test
    public void testDetect() throws IOException, InterruptedException {

        // prepare the graph with relationship edges
        // for the gcd example.
        VPMGraph graph = SPLevoTestUtil.loadGCDVPMGraph();
        VPMAnalyzer analyzer = new CodeStructureVPMAnalyzer();
        VPMAnalyzerResult result = analyzer.analyze(graph);

        List<VPMAnalyzerResult> results = new ArrayList<VPMAnalyzerResult>();
        results.add(result);
        
        DefaultVPMAnalyzerService service = new DefaultVPMAnalyzerService();
        service.createGraphEdges(graph, results);
        
        // prepare a simple detection rule
        List<String> edgeLabels = new ArrayList<String>();
        edgeLabels.add("CodeStructure");
        DetectionRule detectionRule = new BasicDetectionRule(edgeLabels, RefinementType.MERGE);

        List<Refinement> refinements = detectionRule.detect(graph);
        assertEquals("Wrong number of refinements detected", 1, refinements.size());

    }
}
