/**
 * 
 */
package org.splevo.modisco.java.diffing;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.junit.Test;
import org.splevo.diffing.DiffingException;

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
            "../org.splevo.tests/testmodels/implementation/gcd/native");

    /** Source path to the jscience based calculator implementation. */
    private static final File JSCIENCE_JAVA2KDMMODEL_DIR = new File(
            "../org.splevo.tests/testmodels/implementation/gcd/jscience");

    /**
	 * Test method for the greates common devisor (GCD) example.
     * 
	 * @throws DiffingException
	 *             Identifies a failed diffing.
     */
    @Test
    public final void testGetDiff() throws DiffingException {

		DiffModel diff = differ.doDiff(NATIVE_JAVA2KDMMODEL_DIR.toURI(), JSCIENCE_JAVA2KDMMODEL_DIR.toURI(),
				diffOptions);

        EList<DiffElement> differences = diff.getDifferences();

        for (DiffElement diffElement : differences) {
            logger.debug(diffElement.getKind() + ": " + diffElement.getClass().getName());
        }
        assertEquals("Wrong number of differences detected", 7, differences.size());

        logger.debug("Found Differences: " + differences.size());
    }
}
