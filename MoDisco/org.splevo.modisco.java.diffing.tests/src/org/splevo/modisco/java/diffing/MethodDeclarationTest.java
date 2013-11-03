package org.splevo.modisco.java.diffing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.junit.Test;
import org.splevo.diffing.DiffingException;
import org.splevo.diffing.DiffingNotSupportedException;
import org.splevo.modisco.java.diffing.java2kdmdiff.MethodInsert;

/**
 * Unit test to prove the differencing of method declarations.
 */
public class MethodDeclarationTest extends AbstractDiffingTest {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(MethodDeclarationTest.class);

    /** Source path to the original implementation. */
    private static final File TEST_DIR_1 = new File("testmodels/implementation/methoddeclaration/a");

    /** Source path to the modified implementation. */
    private static final File TEST_DIR_2 = new File("testmodels/implementation/methoddeclaration/b");

    /**
     * Test method to detect changes in the method declarations.
     * 
	 * @throws DiffingException
	 *             Identifies a failed diffing.
     */
    @Test
    public void testDoDiff() throws DiffingException, DiffingNotSupportedException {
    	
    	DiffModel diff = differ.doDiff(TEST_DIR_1.toURI(), TEST_DIR_2.toURI(),
				diffOptions);

        EList<DiffElement> differences = diff.getDifferences();
        assertEquals("Wrong number of differences detected", 1, differences.size());

        for (DiffElement diffElement : differences) {
            if (diffElement instanceof MethodInsert) {
                MethodInsert fieldInsert = ((MethodInsert) diffElement);
                AbstractMethodDeclaration methodDecl = fieldInsert.getMethodLeft();
                assertEquals("Wrong method detected as changed", "newMethod", methodDecl.getName());
                
            
                
            } else {
                fail("No other diff elements than MethodInsert should have been detected.");
            }
            logger.debug(diffElement.getKind() + ": " + diffElement.getClass().getName());
        }
        logger.debug("Found Differences: " + differences.size());
    }
}
