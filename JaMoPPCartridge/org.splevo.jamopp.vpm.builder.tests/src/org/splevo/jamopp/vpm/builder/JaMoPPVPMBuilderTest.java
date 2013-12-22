/**
 *
 */
package org.splevo.jamopp.vpm.builder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.diffing.JavaDiffer;
import org.splevo.jamopp.diffing.JaMoPPDiffer;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.Lists;

/**
 * Tests for the JaMoPP specific VPM generation.
 *
 */
public class JaMoPPVPMBuilderTest {

    /** The logger to use for this class. */
    @SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(JaMoPPVPMBuilderTest.class);

	/**
	 * Prepare the test.
	 * Initializes a log4j logging environment.
	 */
	@BeforeClass
	public static void setUp() {
		// set up a basic logging configuration for the test environment
		BasicConfigurator.resetConfiguration();
		BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
	}

	@Test
	public void test() throws Exception{

		JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
		String basePath = "testcode/";
		List<URI> urisA = Lists.asList(URI.createFileURI(basePath + "a"), new URI[] {});
		List<URI> urisB = Lists.asList(URI.createFileURI(basePath + "b"), new URI[] {});
		NullProgressMonitor monitor = new NullProgressMonitor();
		ResourceSet setA = extractor.extractSoftwareModel(urisA, monitor, null);
		ResourceSet setB = extractor.extractSoftwareModel(urisB, monitor, null);

		Map<String, Object> diffOptions = new LinkedHashMap<String, Object>();
		diffOptions.put(JavaDiffer.OPTION_JAVA_IGNORE_PACKAGES, Arrays.asList("java.*", "org.jscience.*", "javolution.*"));

        JaMoPPDiffer differ = new JaMoPPDiffer();
    	Comparison comparison = differ.doDiff(setA, setB, diffOptions);
    	assertThat("Wrong number of differences", comparison.getDifferences().size(), is(5));

    	JaMoPPVPMBuilder builder = new JaMoPPVPMBuilder();
    	VariationPointModel vpm = builder.buildVPM(comparison, "leading", "integration");
    	assertThat("Wrong number of vpm groups", vpm.getVariationPointGroups().size(), is(5));
	}

}
