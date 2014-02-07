/**
 *
 */
package org.splevo.jamopp.diffing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.junit.Test;

/**
 * Unit test for the diffing service class.
 *
 * @author Benjamin Klatt
 *
 */
public class GCDDiffingTest {

    private static final String BASE_PATH = "testmodels/implementation/calculator/";

    /**
     * Test the diffing of the calculator example.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testDoDiff() throws Exception {

        TestUtil.setUp();
        ResourceSet setA = TestUtil.extractModel(BASE_PATH + "native");
        ResourceSet setB = TestUtil.extractModel(BASE_PATH + "jscience");

        JaMoPPDiffer differ = new JaMoPPDiffer();

        Map<String, String> diffOptions = TestUtil.DIFF_OPTIONS;
        Comparison comparison = differ.doDiff(setA, setB, diffOptions);

        EList<Diff> differences = comparison.getDifferences();

        assertThat("Wrong number of differences", differences.size(), is(12));
    }
}
