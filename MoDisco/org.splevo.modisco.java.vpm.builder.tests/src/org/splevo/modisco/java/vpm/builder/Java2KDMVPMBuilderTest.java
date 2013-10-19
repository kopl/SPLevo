package org.splevo.modisco.java.vpm.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.tests.SPLevoTestUtil;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
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

    private static DiffModel diffModel;

    /**
     * Load the diff model to process.
     * 
     * @throws Exception
     *             Failed to load generate the diff model.
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        diffModel = SPLevoTestUtil.loadGCDDiffModel();
    }

    /**
     * Test building a vpm from a diff model.
     */
    @Test
    public void testBuildVPM() {

        Java2KDMVPMBuilder java2KDMVPMBuilder = new Java2KDMVPMBuilder();
        VariationPointModel vpm = java2KDMVPMBuilder.buildVPM(diffModel, "LEADING", "INTEGRATION");
        assertNotNull("No VPM initialized", vpm);

        logger.warn("Number of variation point groups: " + vpm.getVariationPointGroups().size());
    }

    /**
     * Variation Points located at the same software element should reference the same software
     * element and not different instances pointing to different concrete AST nodes.
     */
    @Test
    public void testVPLocationReferences() {

        Java2KDMVPMBuilder java2KDMVPMBuilder = new Java2KDMVPMBuilder();
        VariationPointModel vpm = java2KDMVPMBuilder.buildVPM(diffModel, "LEADING", "INTEGRATION");
        assertNotNull("No VPM initialized", vpm);
        
        Set<SoftwareElement> vpLocations = new LinkedHashSet<SoftwareElement>();
        
        for (VariationPointGroup vpGroup : vpm.getVariationPointGroups()) {
            for (VariationPoint vp : vpGroup.getVariationPoints()) {
                vpLocations.add(vp.getEnclosingSoftwareEntity());
            }
        }

        // expects the locations:
        // CompilationUnit for the imports
        // gcd method body for the statement changes of the gcd calculation
        assertEquals("Wrong number of variation point locations", 2, vpLocations.size());
    }

}
