package org.splevo.modisco.java.diffing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.junit.Test;
import org.splevo.diffing.DiffingException;
import org.splevo.diffing.DiffingNotSupportedException;
import org.splevo.modisco.java.diffing.java2kdmdiff.MethodChange;

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

        Java2KDMDiffer differ = new Java2KDMDiffer();
        Comparison diff = differ.doDiff(TEST_DIR_1.toURI(), TEST_DIR_2.toURI(), diffOptions);

        EList<Diff> differences = diff.getDifferences();
        assertEquals("Wrong number of differences detected", 1, differences.size());

        for (Diff diffElement : differences) {
            if (diffElement instanceof MethodChange) {
                MethodChange methodChange = ((MethodChange) diffElement);

                assertThat("Wrong DifferenceKind", methodChange.getKind(), is(DifferenceKind.ADD));

                AbstractMethodDeclaration methodDecl = methodChange.getChangedMethod();
                assertThat("Wrong method", methodDecl.getName(), is("newMethod"));

            } else {
                fail("No other diff elements than MethodChanges should have been detected.");
            }
            logger.debug(diffElement.getKind() + ": " + diffElement.getClass().getName());
        }
        logger.debug("Found Differences: " + differences.size());
    }
}
