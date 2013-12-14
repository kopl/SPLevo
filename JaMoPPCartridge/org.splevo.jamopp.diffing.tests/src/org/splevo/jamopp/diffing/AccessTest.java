package org.splevo.jamopp.diffing;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.junit.Test;
import org.splevo.diffing.DiffingException;
import org.splevo.diffing.DiffingNotSupportedException;

import com.google.common.collect.Sets;

/**
 * Unit test to prove the differencing of type accesses declarations.
 */
public class AccessTest {

    /** The logger for this class. */
    @SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(AccessTest.class);

    /** Source path to the original implementation. */
    private static final File TEST_FILE_1 = new File("testmodels/implementation/access/a/ExternalTypeAccess.java");

    /** Source path to the modified implementation. */
    private static final File TEST_FILE_2 = new File("testmodels/implementation/access/b/ExternalTypeAccess.java");

    /**
     * Test method to detect changes in the method declarations.
     *
	 * @throws DiffingException
	 *             Identifies a failed diffing.
     * @throws DiffingNotSupportedException
     * @throws IOException
     */
    @Test
    public void testDoDiff() throws Exception {

    	TestUtil.setUp();
    	ResourceSet rsLeading = TestUtil.loadResourceSet(Sets.newHashSet(TEST_FILE_1));
    	ResourceSet rsIntegration = TestUtil.loadResourceSet(Sets.newHashSet(TEST_FILE_2));

        JaMoPPDiffer differ = new JaMoPPDiffer();
    	Comparison comparision = differ.doDiff(rsLeading, rsIntegration, TestUtil.diffOptions);

        EList<Diff> differences = comparision.getDifferences();
        assertEquals("Wrong number of differences detected", 0, differences.size());
    }
}
