/**
 * 
 */
package org.splevo.diffing;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.diff.metamodel.ComparisonResourceSetSnapshot;
import org.eclipse.emf.compare.util.EMFCompareMap;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for the diffing service class.
 * 
 * @author Benjamin Klatt
 * 
 */
public class EMFCompareDiffingServiceTest {
	
	/** Source path to the native calculator implementation */
	private static final File NATIVE_MODEL_FILE = new File(
			"testmodels/implementation/native/_java.xmi");
	
	/** Source path to the jscience based calculator implementation */
	private static final File JSCIENCE_MODEL_FILE = new File(
			"testmodels/implementation/jscience/_java.xmi");

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
		DiffingService diffingService = new EMFCompareDiffingService();
		
		List<File> leadingModelFiles = new ArrayList<File>();
		leadingModelFiles.add(NATIVE_MODEL_FILE);
		List<File> integrationModelFiles = new ArrayList<File>();
		integrationModelFiles.add(JSCIENCE_MODEL_FILE);
		
		
		ComparisonResourceSetSnapshot snapshot = diffingService.getDiff(leadingModelFiles,integrationModelFiles);
		File targetFile = new File("testresult/testDiff.emfdiff");
		saveDiff(targetFile, snapshot);
	}

	/**
	 * Saves an EMF Compare result to a file.
	 * 
	 * @param targetFile
	 *            target serialization file
	 * @param snapshot
	 *            snapshot of the complete comparison result.	
	 * @throws IOException
	 */
	private void saveDiff(File targetFile, ComparisonResourceSetSnapshot snapshot) throws IOException {
		Resource newModelRes = (new ResourceSetImpl()).createResource(URI
				.createFileURI(targetFile.getPath()));
		newModelRes.getContents().add(snapshot);
		final Map<String, String> options = new EMFCompareMap<String, String>();
		options.put(XMLResource.OPTION_ENCODING,
				System.getProperty("file.encoding"));
		newModelRes.save(options);
	}
}
