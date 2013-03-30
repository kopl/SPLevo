package org.splevo.vpm.refinement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.eclipse.modisco.java.composition.javaapplication.JavaNodeSourceRegion;
import org.junit.Test;
import org.splevo.modisco.util.SourceConnector;
import org.splevo.tests.SPLevoTestUtil;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Test the vpm refinement service.
 */
public class VPMRefinementServiceTest extends AbstractTest {

    /**
     * Test of a basic refinement analysis and application. This test makes use of the basic
     * location based variation point analysis.
     * 
     * @throws IOException
     *             identifies that the vpm could not be loaded.
     * @throws InterruptedException
     *             identifies that the refinement process was interrupted.
     */
    @Test
    public void testRefinements() throws IOException, InterruptedException {

        VariationPointModel initialVpm = SPLevoTestUtil.loadGCDVPMModel();
        
        assertEquals("wrong number of initial variation point groups", 6, initialVpm.getVariationPointGroups().size());

        List<Refinement> refinements = new ArrayList<Refinement>();
        Refinement refinement = RefinementFactory.eINSTANCE.createRefinement();
        refinement.setSource("Manual");
        refinement.setType(RefinementType.GROUPING);
        for (VariationPointGroup group : initialVpm.getVariationPointGroups()) {
            for (VariationPoint vp : group.getVariationPoints()) {
                if (vp.getEnclosingSoftwareEntity() instanceof Block) {
                    refinement.getVariationPoints().add(vp);
                }

            }
        }
        refinements.add(refinement);
        assertEquals("wrong number of refinements identified", 1, refinements.size());

        VPMRefinementService refinementService = new VPMRefinementService();
        VariationPointModel refinedVPM = refinementService.applyRefinements(refinements, initialVpm);
        assertEquals("wrong number of variation point groups after refinement", 3, refinedVPM.getVariationPointGroups()
                .size());

        // ModelUtils.save(refinedVPM, "testresult/gcd-refined.vpm");

        for (VariationPointGroup vpGroup : ((VariationPointModel) refinedVPM).getVariationPointGroups()) {
            if (vpGroup.getGroupId().equals("gcd")) {
                ASTNode astNode = vpGroup.getVariationPoints().get(0).getEnclosingSoftwareEntity();
                JavaApplication model = refinedVPM.getLeadingModel();
                SourceConnector sourceConnector = new SourceConnector(model);
                JavaNodeSourceRegion sourceRegion = sourceConnector.findSourceRegion(astNode);
                assertNotNull(sourceRegion);
            }
        }
    }

}
