package org.splevo.fm.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.featuremodel.FeatureModel;
import org.junit.Test;
import org.splevo.tests.SPLevoTestUtil;
import org.splevo.vpm.variability.VariationPointModel;

public class VPM2FMBuilderTest extends AbstractTest {

	/** The logger for this test class. */
    private Logger logger = Logger.getLogger(VPM2FMBuilderTest.class);

	/**
	 * Basic test to create a vpm and derive a feature model.
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void testBuildFeatureModel() throws IOException, InterruptedException {
		
		VariationPointModel vpm = SPLevoTestUtil.loadGCDVPMModel();
		
		VPM2FMBuilder builder = new VPM2FMBuilder();
		FeatureModel fm = builder.buildFeatureModel(vpm);

		assertNotNull("No Feature Model Created", fm);
        assertNotNull("No root feature created", fm.getRoot());
        assertEquals("Wrong number of top level features", 6, fm.getRoot().getChildren().size());
		
		//ModelUtils.save(fm, "testresult/gcdFeatureModel.featuremodel");
	}
}
