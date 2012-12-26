package org.splevo.vpm.refinement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.util.ModelUtils;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.modisco.java.composition.javaapplication.JavaNodeSourceRegion;
import org.junit.Test;
import org.splevo.modisco.util.SourceConnector;
import org.splevo.tests.SPLevoTestUtil;
import org.splevo.vpm.refinement.simplelocation.VPLocationAnalyzer;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Test the vpm refinement service.
 */
public class VPMRefinementServiceTest extends AbstractTest {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(VPMRefinementServiceTest.class);

    /**
     * Test of a basic refinement analysis and application. This test makes use of the basic
     * location based variation point analysis.
     * 
     * @throws IOException identifies that the vpm could not be loaded.
     * @throws InterruptedException identifies that the refinement process was interrupted.
     */
    @Test
    public void testRefinements() throws IOException, InterruptedException {

        VariationPointModel initialVpm = SPLevoTestUtil.loadGCDVPMModel();
        logger.debug("Number of initial variation point groups: " + initialVpm.getVariationPointGroups().size());

        VPMRefinementService refinementService = new VPMRefinementService();
        VPLocationAnalyzer analyzer = new VPLocationAnalyzer();
        List<Refinement> refinements = refinementService.identifyRefinements(initialVpm, analyzer);
        assertEquals("wrong number of refinements identified", 1, refinements.size());

        VariationPointModel refinedVPM = refinementService.applyRefinements(refinements, initialVpm);
        logger.debug("Number of variation point groups after refinement: "
                + refinedVPM.getVariationPointGroups().size());

        ModelUtils.save(refinedVPM, "testresult/gcd-refined.vpm");

        for (VariationPointGroup vpGroup : ((VariationPointModel) refinedVPM).getVariationPointGroups()) {
            if (vpGroup.getGroupId().equals("gcd")) {
                ASTNode astNode = vpGroup.getVariationPoints().get(0).getEnclosingSoftwareEntity();

                SourceConnector sourceConnector = new SourceConnector(refinedVPM.getLeadingModel());
                JavaNodeSourceRegion sourceRegion = sourceConnector.findSourceRegion(astNode);
                assertNotNull(sourceRegion);
            }
        }
    }

}
