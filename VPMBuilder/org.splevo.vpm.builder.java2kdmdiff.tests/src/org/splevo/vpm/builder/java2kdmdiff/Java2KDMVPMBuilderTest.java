package org.splevo.vpm.builder.java2kdmdiff;

import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.junit.Test;
import org.splevo.tests.SPLevoTestUtil;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Test case for the Java2KDM based VPM builder
 * 
 * This test case directly invokes the diffing service to produce the diffing model the builder
 * should use. It introduces a direct dependency that should be removed later on, but for now, it
 * improves the development cycles time for changes in the diff engine.
 * 
 */
public class Java2KDMVPMBuilderTest extends AbstractTest {

    /** The logger to use for this class. */
    private Logger logger = Logger.getLogger(Java2KDMVPMBuilderTest.class);

    /**
     * Test building a vpm from a diff model.
     * 
     * @throws Exception
     *             Error loading the test diff model.
     */
    @Test
    public void testBuildVPM() throws Exception {

        DiffModel diffModel = SPLevoTestUtil.loadGCDDiffModel();

        Java2KDMVPMBuilder java2KDMVPMBuilder = new Java2KDMVPMBuilder("LEADING", "INTEGRATION");
        VariationPointModel vpm = java2KDMVPMBuilder.buildVPM(diffModel);
        assertNotNull("No VPM initialized", vpm);

        logger.warn("Number of variation point groups: " + vpm.getVariationPointGroups().size());

        assertNotNull("Leading model must not be null", vpm.getLeadingModel());
        assertNotNull("Integration model must not be null", vpm.getIntegrationModel());

        // ModelUtils.save(vpm, "testresult/gcd-intial.vpm");
    }

}
