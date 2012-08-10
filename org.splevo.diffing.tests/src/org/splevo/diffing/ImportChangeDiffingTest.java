package org.splevo.diffing;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.util.ModelUtils;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.junit.Test;
import org.splevo.diffing.kdm.KDMUtil;

public class ImportChangeDiffingTest {

	/** Source path to the native calculator implementation */
	private static final File IMPORT_TEST_FILE_1 = new File("testmodels/implementation/importDiffing/1/1_java2kdm.xmi");
	
	/** Source path to the jscience based calculator implementation */
	private static final File IMPORT_TEST_FILE_2 = new File("testmodels/implementation/importDiffing/2/2_java2kdm.xmi");

	/**
	 * Test method for
	 * {@link org.splevo.diffing.EMFCompareDiffingService#getDiff(org.eclipse.modisco.java.composition.javaapplication.JavaApplication, org.eclipse.modisco.java.composition.javaapplication.JavaApplication)}
	 * .
	 * 
	 * @throws IOException
	 *             Identifies that either the source models could not be loaded
	 *             or the diff model could not be serialized.
	 * @throws InterruptedException 
	 * @throws Exception
	 */
	@Test
	public void testDoDiff() throws IOException, InterruptedException {
		JavaApplication leadingModel = KDMUtil.loadKDMModel(IMPORT_TEST_FILE_1);
		JavaApplication integrationModel = KDMUtil.loadKDMModel(IMPORT_TEST_FILE_2);
		
		MatchEngineDiffingService diffingService = new MatchEngineDiffingService();
		diffingService.getIgnorePackages().add("java.lang");
		diffingService.getIgnorePackages().add("java.math");
		
		DiffModel diff = diffingService.doDiff(leadingModel,integrationModel);
		
		EList<DiffElement> differences = diff.getDifferences();
		for (DiffElement diffElement : differences) {
			System.out.println(diffElement.getKind()+": "+diffElement.getClass().getName());
		}
		
		System.out.println("Found Differences: "+differences.size());
		
		ModelUtils.save(diff, "testresult/importDiffModel.java2kdmdiff");
	}

}
