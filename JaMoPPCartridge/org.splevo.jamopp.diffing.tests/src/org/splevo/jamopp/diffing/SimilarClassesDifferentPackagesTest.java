package org.splevo.jamopp.diffing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.diffing.jamoppdiff.CompilationUnitChange;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;

import com.google.common.collect.Lists;

/**
 * Unit test to prove the mapping options of the diffing.
 */
public class SimilarClassesDifferentPackagesTest {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(SimilarClassesDifferentPackagesTest.class);

    private static ResourceSet setA;
    private static ResourceSet setB;

    /**
     * Initialize the test by loading the resources once to be used by the severall diff tests.
     *
     * @throws Exception
     *             A failed initialization
     */
    @BeforeClass
    public static void initTest() throws Exception {

        TestUtil.setUp();

        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        String basePath = "testmodels/implementation/similarclasses-differentpackages/";
        List<URI> urisA = Lists.asList(URI.createFileURI(basePath + "a"), new URI[] {});
        List<URI> urisB = Lists.asList(URI.createFileURI(basePath + "b"), new URI[] {});
        NullProgressMonitor monitor = new NullProgressMonitor();
        setA = extractor.extractSoftwareModel(urisA, monitor, null);
        setB = extractor.extractSoftwareModel(urisB, monitor, null);
    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testDoDiff() throws Exception {

        TestUtil.setUp();

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Map<String, String> diffOptions = TestUtil.DIFF_OPTIONS;
        Comparison comparison = differ.doDiff(setA, setB, diffOptions);

        assertThat("Wrong number of matched resources", comparison.getMatchedResources().size(), is(2));

        EList<Diff> differences = comparison.getDifferences();
        for (Diff diffElement : differences) {
            logger.debug(diffElement.getClass().getSimpleName());
            if (diffElement instanceof CompilationUnitChange) {
                CompilationUnitChange change = (CompilationUnitChange) diffElement;
                logger.debug(change.getChangedCompilationUnit().getName());
            }
        }
        assertThat("No difference should exist",  comparison.getMatches().size(), is(0));

    }
}
