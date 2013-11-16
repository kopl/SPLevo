/**
 * 
 */
package org.splevo.modisco.java.diffing;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.junit.Test;
import org.splevo.diffing.DiffingException;
import org.splevo.diffing.DiffingNotSupportedException;

/**
 * Unit test for the diffing service class.
 * 
 * @author Benjamin Klatt
 * 
 */
public class GCDDiffingTest extends AbstractDiffingTest {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(VariableDeclarationStatementDiffingTest.class);

    /** Source path to the native calculator implementation. */
    private static final File NATIVE_JAVA2KDMMODEL_DIR = new File(
            "testmodels/implementation/calculator/native");

    /** Source path to the jscience based calculator implementation. */
    private static final File JSCIENCE_JAVA2KDMMODEL_DIR = new File(
            "testmodels/implementation/calculator/jscience");

    /**
	 * Test method for the greates common devisor (GCD) example.
     * 
	 * @throws DiffingException
	 *             Identifies a failed diffing.
     */
    @Test
    public final void testGetDiff() throws DiffingException, DiffingNotSupportedException {

        Java2KDMDiffer differ = new Java2KDMDiffer();
		Comparison comparison = differ.doDiff(NATIVE_JAVA2KDMMODEL_DIR.toURI(), JSCIENCE_JAVA2KDMMODEL_DIR.toURI(),
				diffOptions);

        EList<Diff> differences = comparison.getDifferences();

        for (Diff diff : differences) {
            logger.debug(diff.getKind() + ": " + diff.getClass().getSimpleName() + " : "+ diff.getMatch());
        }
        assertEquals("Wrong number of differences detected", 12, differences.size());

        logger.debug("Found Differences: " + differences.size());
    }
}
