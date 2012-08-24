package org.splevo.fm.builder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.util.ModelUtils;
import org.eclipse.featuremodel.FeatureModel;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.junit.Test;
import org.splevo.diffing.Java2KDMDiffingService;
import org.splevo.diffing.kdm.KDMUtil;
import org.splevo.vpm.builder.java2kdmdiff.Java2KDMVPMBuilder;
import org.splevo.vpm.variability.VariationPointModel;

public class VPM2FMBuilderTest extends AbstractTest {

	/** The logger for this test class. */
    private Logger logger = Logger.getLogger(VPM2FMBuilderTest.class);

	/** Source path to the native calculator implementation */
	private static final File NATIVE_JAVA2KDMMODEL_FILE = new File("../org.splevo.diffing.tests/testmodels/implementation/gcd/native/_java2kdm.xmi");
	
	/** Source path to the jscience based calculator implementation */
	private static final File JSCIENCE_JAVA2KDMMODEL_FILE = new File("../org.splevo.diffing.tests/testmodels/implementation/gcd/jscience/_java2kdm.xmi");

	/**
	 * Basic test to create a vpm and derive a feature model.
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void testBuildFeatureModel() throws IOException, InterruptedException {
		
		VariationPointModel vpm = null;
		vpm = generateVPM();
		
		logger.debug("VPM prepared");
		
		VPM2FMBuilder builder = new VPM2FMBuilder();
		FeatureModel fm = builder.buildFeatureModel(vpm);

		logger.debug("FM created");
		
		ModelUtils.save(fm, "testresult/gcdFeatureModel.featuremodel");
	}
	
	/**
	 * Generate the vpm model to work with.
	 * 
	 * @return The prepared vpm.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public VariationPointModel generateVPM() throws IOException, InterruptedException {

		JavaApplication leadingModel = KDMUtil.loadKDMModel(NATIVE_JAVA2KDMMODEL_FILE);
		JavaApplication integrationModel = KDMUtil.loadKDMModel(JSCIENCE_JAVA2KDMMODEL_FILE);
		List<String> ignorePackages = new ArrayList<String>();
		ignorePackages.add("java.lang");
		ignorePackages.add("java.math");
		ignorePackages.add("java.io");
		ignorePackages.add("org.jscience.*");
		ignorePackages.add("org.jscience.*");
		ignorePackages.add("javolution.*");
		Java2KDMDiffingService diffingService = new Java2KDMDiffingService();
		diffingService.getIgnorePackages().addAll(ignorePackages);
		DiffModel diffModel;
		diffModel = diffingService.doDiff(leadingModel,integrationModel);
			
		Java2KDMVPMBuilder java2KDMVPMBuilder = new Java2KDMVPMBuilder();
		VariationPointModel vpm = java2KDMVPMBuilder.buildVPM(diffModel);
		
		return vpm;
	}

}
