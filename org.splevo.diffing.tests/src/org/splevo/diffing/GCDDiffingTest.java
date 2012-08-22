/**
 * 
 */
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

/**
 * Unit test for the diffing service class.
 * 
 * @author Benjamin Klatt
 * 
 */
public class GCDDiffingTest extends AbstractDiffingTest {
	
	/** Source path to the native calculator implementation */
	private static final File NATIVE_JAVA2KDMMODEL_FILE = new File("testmodels/implementation/gcd/native/_java2kdm.xmi");
	
	/** Source path to the jscience based calculator implementation */
	private static final File JSCIENCE_JAVA2KDMMODEL_FILE = new File("testmodels/implementation/gcd/jscience/_java2kdm.xmi");

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
	public final void testGetDiff() throws IOException, InterruptedException {
		
		JavaApplication leadingModel = KDMUtil.loadKDMModel(NATIVE_JAVA2KDMMODEL_FILE);
		JavaApplication integrationModel = KDMUtil.loadKDMModel(JSCIENCE_JAVA2KDMMODEL_FILE);
		
		Java2KDMDiffingService diffingService = new Java2KDMDiffingService();
		diffingService.getIgnorePackages().add("java.lang");
		diffingService.getIgnorePackages().add("java.math");
		diffingService.getIgnorePackages().add("java.io");
		diffingService.getIgnorePackages().add("org.jscience.*");
		diffingService.getIgnorePackages().add("org.jscience.*");
		diffingService.getIgnorePackages().add("javolution.*");
		
		DiffModel diff = diffingService.doDiff(integrationModel,leadingModel);
		
		EList<DiffElement> differences = diff.getDifferences();
		
		for (DiffElement diffElement : differences) {
			System.out.println(diffElement.getKind()+": "+diffElement.getClass().getName());
		}
		
		System.out.println("Found Differences: "+differences.size());
		
		ModelUtils.save(diff, "testresult/gcdDiffModel.java2kdmdiff");
	}
}
