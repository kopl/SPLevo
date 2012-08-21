package org.splevo.vpm.builder.java2kdmdiff;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.util.ModelUtils;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.junit.Test;
import org.splevo.diffing.Java2KDMDiffingService;
import org.splevo.diffing.kdm.KDMUtil;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Test case for the Java2KDM based VPM builder 
 * 
 * This test case directly invokes the diffing service to produce the diffing model the builder should use. 
 * It introduces a direct dependency that should be removed later on,  
 * but for now, it improves the development cycles time for changes in the diff engine. 
 *
 */
public class Java2KDMVPMBuilderTest extends AbstractDiffingTest {
	
    private Logger logger = Logger.getLogger(Java2KDMVPMBuilderTest.class);

	/** Source path to the native calculator implementation */
	private static final File NATIVE_JAVA2KDMMODEL_FILE = new File("../org.splevo.diffing.tests/testmodels/implementation/gcd/native/_java2kdm.xmi");
	
	/** Source path to the jscience based calculator implementation */
	private static final File JSCIENCE_JAVA2KDMMODEL_FILE = new File("../org.splevo.diffing.tests/testmodels/implementation/gcd/jscience/_java2kdm.xmi");

	@Test
	public void testBuildVPM() throws IOException, InterruptedException {

		JavaApplication leadingModel = KDMUtil.loadKDMModel(NATIVE_JAVA2KDMMODEL_FILE);
		JavaApplication integrationModel = KDMUtil.loadKDMModel(JSCIENCE_JAVA2KDMMODEL_FILE);
		List<String> ignorePackages = new ArrayList<String>();
		ignorePackages.add("java.lang");
		ignorePackages.add("java.math");
		ignorePackages.add("java.io");
		ignorePackages.add("org.jscience.*");
		ignorePackages.add("org.jscience.*");
		ignorePackages.add("javolution.*");
		DiffModel diffModel = loadDiffModel(leadingModel, integrationModel, ignorePackages);
		
		Java2KDMVPMBuilder java2KDMVPMBuilder = new Java2KDMVPMBuilder();
		VariationPointModel vpm = java2KDMVPMBuilder.buildVPM(diffModel);
		
		logger.debug(vpm);
		
		ModelUtils.save(vpm, "testresult/gcd-intial.vpm");
	}


	/**
	 * Load the diffing model. 
	 * 
	 * @param leadingModel
	 * @param integrationModel
	 * @param ignorePackages
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private DiffModel loadDiffModel(JavaApplication leadingModel, JavaApplication integrationModel, List<String> ignorePackages) throws IOException, InterruptedException {

			
			Java2KDMDiffingService diffingService = new Java2KDMDiffingService();
			diffingService.getIgnorePackages().addAll(ignorePackages);
			
			DiffModel diffModel = diffingService.doDiff(leadingModel,integrationModel);
			
			return diffModel;
	}

}
