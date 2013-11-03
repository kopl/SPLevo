package org.splevo.modisco.java.diffing;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.junit.Test;
import org.splevo.diffing.DiffingException;
import org.splevo.diffing.DiffingNotSupportedException;

/**
 * Unit test to prove the differencing of type accesses declarations.
 */
public class AccessTest extends AbstractDiffingTest {

    /** The logger for this class. */
    @SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(AccessTest.class);

    /** Source path to the original implementation. */
    private static final File TEST_DIR_1 = new File("testmodels/implementation/access/a");

    /** Source path to the modified implementation. */
    private static final File TEST_DIR_2 = new File("testmodels/implementation/access/b");

    /**
     * Test method to detect changes in the method declarations.
     * 
	 * @throws DiffingException
	 *             Identifies a failed diffing.
     * @throws DiffingNotSupportedException 
     */
    @Test
    public void testDoDiff() throws DiffingException, DiffingNotSupportedException {
    	
    	DiffModel diff = differ.doDiff(TEST_DIR_1.toURI(), TEST_DIR_2.toURI(),
				diffOptions);

        EList<DiffElement> differences = diff.getDifferences();
        assertEquals("Wrong number of differences detected", 0, differences.size());
    }
}
