package org.splevo.vpm.refinement;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.util.ModelUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.eclipse.modisco.java.composition.javaapplication.JavaNodeSourceRegion;
import org.eclipse.modisco.java.composition.javaapplication.queries.GetASTNodeSourceRegion;
import org.junit.Test;
import org.splevo.diffing.Java2KDMDiffingService;
import org.splevo.modisco.util.KDMUtil;
import org.splevo.vpm.builder.java2kdmdiff.Java2KDMVPMBuilder;
import org.splevo.vpm.refinement.simplelocation.VPLocationAnalyzer;
import org.splevo.modisco.util.SourceConnector;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Test the vpm refinement service.
 */
public class VPMRefinementServiceTest extends AbstractTest {

	/** The logger for this class. */
    private Logger logger = Logger.getLogger(VPMRefinementServiceTest.class);

	/** Source path to the native calculator implementation */
	private static final File NATIVE_JAVA2KDMMODEL_FILE = new File("../org.splevo.diffing.tests/testmodels/implementation/gcd/native/_java2kdm.xmi");
	
	/** Source path to the jscience based calculator implementation */
	private static final File JSCIENCE_JAVA2KDMMODEL_FILE = new File("../org.splevo.diffing.tests/testmodels/implementation/gcd/jscience/_java2kdm.xmi");

	/**
	 * Test of a basic refinement analysis and application.
	 * This test makes use of the basic location based variation point analysis.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void testRefinements() throws IOException, InterruptedException {

		VariationPointModel initialVpm = loadVPMModel();
		logger.debug("Number of initial variation point groups: "+initialVpm.getVariationPointGroups().size());
		
		VPMRefinementService refinementService = new VPMRefinementService();
		VPLocationAnalyzer analyzer = new VPLocationAnalyzer();
		List<Refinement> refinements = refinementService.identifyRefinements(initialVpm,analyzer);
		assertEquals("wrong number of refinements identified",1,refinements.size());
		
		VariationPointModel refinedVPM = refinementService.applyRefinements(refinements, initialVpm);
		logger.debug("Number of variation point groups after refinement: "+refinedVPM.getVariationPointGroups().size());

		ModelUtils.save(refinedVPM, "testresult/gcd-refined.vpm");
		
		for (VariationPointGroup vpGroup : ((VariationPointModel) refinedVPM).getVariationPointGroups()){
			if(vpGroup.getGroupId().equals("gcd")){
				ASTNode astNode = vpGroup.getVariationPoints().get(0).getSoftwareEntity();
				
				SourceConnector sourceConnector = new SourceConnector(refinedVPM.getLeadingModel());
				JavaNodeSourceRegion sourceRegion = sourceConnector.findSourceRegion(astNode);
				assertNotNull(sourceRegion);
			}
		}
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
