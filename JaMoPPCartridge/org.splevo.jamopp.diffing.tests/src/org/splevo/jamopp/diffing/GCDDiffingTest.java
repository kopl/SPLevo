/**
 *
 */
package org.splevo.jamopp.diffing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;

import com.google.common.collect.Lists;

/**
 * Unit test for the diffing service class.
 *
 * @author Benjamin Klatt
 *
 */
public class GCDDiffingTest {

	private static ResourceSet setA;
	private static ResourceSet setB;

	@BeforeClass
	public static void initTest() throws Exception {

		TestUtil.setUp();

		JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
		String basePath = "testmodels/implementation/calculator/";
		List<URI> urisA = Lists.asList(URI.createFileURI(basePath + "native"),
				new URI[] {});
		List<URI> urisB = Lists.asList(URI.createFileURI(basePath + "jscience"),
				new URI[] {});
		NullProgressMonitor monitor = new NullProgressMonitor();
		setA = extractor.extractSoftwareModel(urisA, monitor, null);
		setB = extractor.extractSoftwareModel(urisB, monitor, null);
	}

	/**
	 * Test the diffing of the calculator example.
	 *
	 * @throws Exception
	 *             Identifies a failed diffing.
	 */
	@Test
	public void testDoDiff() throws Exception {

		TestUtil.setUp();

		JaMoPPDiffer differ = new JaMoPPDiffer();

		Map<String, Object> diffOptions = TestUtil.diffOptions;
		Comparison comparison = differ.doDiff(setA, setB, diffOptions);

		EList<Diff> differences = comparison.getDifferences();

		assertThat("Wrong number of differences", differences.size(), is(12));
    }
}
