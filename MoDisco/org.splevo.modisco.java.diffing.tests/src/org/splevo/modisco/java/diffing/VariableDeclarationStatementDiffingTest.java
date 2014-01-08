package org.splevo.modisco.java.diffing;

import static org.hamcrest.CoreMatchers.instanceOf;
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
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;
import org.junit.Test;
import org.splevo.diffing.DiffingException;
import org.splevo.diffing.DiffingNotSupportedException;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImportChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.StatementChange;

/**
 * Diffing test case for changed variable declarations.
 *
 * @author Benjamin Klatt
 *
 */
public class VariableDeclarationStatementDiffingTest extends AbstractDiffingTest {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(VariableDeclarationStatementDiffingTest.class);

    /** Source path to the native calculator implementation. */
    private static final File TEST_DIR_1 = new File("testmodels/implementation/variabledeclaration/a");

    /** Source path to the jscience based calculator implementation. */
    private static final File TEST_DIR_2 = new File("testmodels/implementation/variabledeclaration/b");

    /**
     * Test method for identifying changed variable declarations. .
     *
     * This test compares two implementations with differing variable declaration statements. The
     * differing implementations are: <code>
     * 	BigInteger integerValue1 = new BigInteger("1");
     * </code> and <code>
     *  BigDecimal integerValue1 = new BigDecimal("1");
     * </code>
     *
     * This also includes differing imports which are not in the focus of this test. Both variable
     * declarations are within a methods methodA() of a class A.
     *
     * The changes should and up with a changed a single StatementChange.
     *
     * @throws DiffingException
     *             Identifies a failed diffing.
     */
    @Test
    public void testVariableDeclarationDiff() throws DiffingException, DiffingNotSupportedException {

        Java2KDMDiffer differ = new Java2KDMDiffer();
        Comparison comparison = differ.doDiff(TEST_DIR_1.toURI(), TEST_DIR_2.toURI(), diffOptions);

        EList<Diff> differences = comparison.getDifferences();
        for (Diff diff : differences) {
            logger.info(diff.getKind() + ": " + diff);
        }
        assertEquals("Wrong number of differences detected", 3, differences.size());

        for (Diff diff : differences) {
            if (diff instanceof ImportChange) {
                ImportChange importChange = (ImportChange) diff;
                String importedClass = importChange.getChangedImport().getImportedElement().getName();

                if (importChange.getKind() == DifferenceKind.ADD) {
                    assertThat("Wrong added import type", importedClass, is("BigDecimal"));
                } else if (importChange.getKind() == DifferenceKind.DELETE) {
                    assertThat("Wrong deleted import type", importedClass, is("BigInteger"));
                }

            } else if (diff instanceof StatementChange) {
                Statement changedStatement = ((StatementChange) diff).getChangedStatement();
                assertThat("Wrong statement type", changedStatement, is(instanceOf(VariableDeclarationStatement.class)));

            } else {
                fail("Unexpected diff type detected: " + diff);
            }
            logger.debug(diff.getKind() + ": " + diff.getClass().getName());
        }

        logger.debug("Found Differences: " + differences.size());
    }

}
