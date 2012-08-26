package org.splevo.vpm.refinement;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.junit.Test;
import org.splevo.diffing.Java2KDMDiffingService;
import org.splevo.diffing.kdm.KDMUtil;
import org.splevo.vpm.builder.java2kdmdiff.Java2KDMVPMBuilder;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Test the vpm refinement service.
 */
public class VPMRefinementServiceTest extends AbstractTest {

    private Logger logger = Logger.getLogger(VPMRefinementServiceTest.class);

	/** Source path to the native calculator implementation */
	private static final File NATIVE_JAVA2KDMMODEL_FILE = new File("../org.splevo.diffing.tests/testmodels/implementation/gcd/native/_java2kdm.xmi");
	
	/** Source path to the jscience based calculator implementation */
	private static final File JSCIENCE_JAVA2KDMMODEL_FILE = new File("../org.splevo.diffing.tests/testmodels/implementation/gcd/jscience/_java2kdm.xmi");

	@Test
	public void testRefinements() throws IOException, InterruptedException {

		VariationPointModel initialVpm = loadVPMModel();
		logger.debug("Number of initial variation point groups: "+initialVpm.getVariationPointGroups().size());
		
		VPMRefinementService refinementService = new VPMRefinementService();
		List<Refinement> refinements = refinementService.identifyRefinements(initialVpm);
		for (Refinement refinement : refinements) {
			if(refinement instanceof Grouping){
				logger.debug("GROUPING: "+refinement.getType());
				Grouping grouping = (Grouping) refinement;
				logger.debug("#vps: "+grouping.getVariationPoints().size());
				// TODO: it seems that the group identified in the refinement analyzer does not work
			} else {
				logger.debug("MERGE: "+refinement.getType());				
			}
		}
		assertEquals("wrong number of refinements identified",2,refinements.size());
		
		VariationPointModel refinedVPM = refinementService.applyRefinements(refinements, initialVpm);
		logger.debug("Number of variation point groups after refinement: "+refinedVPM.getVariationPointGroups().size());		
		
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
	private VariationPointModel loadVPMModel() throws IOException, InterruptedException {

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
		
		DiffModel diffModel = diffingService.doDiff(integrationModel, leadingModel);
		
		Java2KDMVPMBuilder java2KDMVPMBuilder = new Java2KDMVPMBuilder();
		VariationPointModel initialVpm = java2KDMVPMBuilder.buildVPM(diffModel);
		
		return initialVpm;
	}

}
