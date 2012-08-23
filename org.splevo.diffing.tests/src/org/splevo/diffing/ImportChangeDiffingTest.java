package org.splevo.diffing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.util.ModelUtils;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.junit.Test;
import org.splevo.diffing.emfcompare.java2kdmdiff.ImportDelete;
import org.splevo.diffing.emfcompare.java2kdmdiff.ImportInsert;
import org.splevo.diffing.kdm.KDMUtil;

public class ImportChangeDiffingTest extends AbstractDiffingTest {
	
	/** The logger for this class. */
    private Logger logger = Logger.getLogger(ImportChangeDiffingTest.class);

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
		
		Java2KDMDiffingService diffingService = new Java2KDMDiffingService();
		diffingService.getIgnorePackages().add("java.lang");
		diffingService.getIgnorePackages().add("java.math");
		
		DiffModel diff = diffingService.doDiff(integrationModel,leadingModel);
		
		EList<DiffElement> differences = diff.getDifferences();
		assertEquals("Wrong number of differences detected",2,differences.size());
		
		for (DiffElement diffElement : differences) {
			if(diffElement instanceof ImportInsert){
				String importedClass = ((ImportInsert) diffElement).getImportLeft().getImportedElement().getName();
				assertEquals("BigDecimal should have been recognized as new import","BigDecimal",importedClass);
			} else if (diffElement instanceof ImportDelete){
				String importedClass = ((ImportDelete) diffElement).getImportRight().getImportedElement().getName();
				assertEquals("BigInteger should have been recognized as new import","BigInteger",importedClass);
			} else {
				fail("No other diff elements than ImportInsert and ImportDelete should have been detected.");
			}
			logger.debug(diffElement.getKind()+": "+diffElement.getClass().getName());
		}
		logger.debug("Found Differences: "+differences.size());
		
		ModelUtils.save(diff, "testresult/importDiffModel.java2kdmdiff");
	}

}
