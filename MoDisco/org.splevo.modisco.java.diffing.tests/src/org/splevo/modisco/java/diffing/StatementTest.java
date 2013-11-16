package org.splevo.modisco.java.diffing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.gmt.modisco.java.CatchClause;
import org.eclipse.gmt.modisco.java.EnhancedForStatement;
import org.eclipse.gmt.modisco.java.ExpressionStatement;
import org.eclipse.gmt.modisco.java.ForStatement;
import org.eclipse.gmt.modisco.java.IfStatement;
import org.eclipse.gmt.modisco.java.ReturnStatement;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.ThrowStatement;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;
import org.eclipse.gmt.modisco.java.WhileStatement;
import org.junit.Test;
import org.splevo.diffing.DiffingException;
import org.splevo.diffing.DiffingNotSupportedException;
import org.splevo.modisco.java.diffing.java2kdmdiff.StatementChange;

/**
 * Unit test to prove the differencing of method declarations.
 */
public class StatementTest extends AbstractDiffingTest {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(StatementTest.class);

    /** Source path to the original implementation. */
    private static final File TEST_DIR_1 = new File("testmodels/implementation/statements/a");

    /** Source path to the modified implementation. */
    private static final File TEST_DIR_2 = new File("testmodels/implementation/statements/b");

    /**
     * Test method to detect changes in the method declarations.
     * 
	 * @throws DiffingException
	 *             Identifies a failed diffing.
     */
    @Test
    public void testDoDiff() throws DiffingException, DiffingNotSupportedException {

        Java2KDMDiffer differ = new Java2KDMDiffer();
    	Comparison comparison = differ.doDiff(TEST_DIR_1.toURI(), TEST_DIR_2.toURI(),
				diffOptions);

        EList<Diff> differences = comparison.getDifferences();
        assertEquals("Wrong number of differences detected", 25, differences.size());

        for (Diff diff : differences) {
            if (diff instanceof StatementChange) {
                StatementChange statementChange = (StatementChange) diff;

                Statement statement = statementChange.getChangedStatement();
                assertNotNull("The changed statement should not be null.", statement);

                if(statementChange.getKind() == DifferenceKind.ADD) {
                    // check the statements should be either if, return or expression statements
                    if (!(statement instanceof IfStatement || statement instanceof ReturnStatement
                            || statement instanceof ExpressionStatement
                            || statement instanceof VariableDeclarationStatement 
                            || statement instanceof CatchClause
                            || statement instanceof ThrowStatement
                            || statement instanceof ForStatement
                            || statement instanceof EnhancedForStatement
                            || statement instanceof WhileStatement)) {
                        fail("Unexpected statement type detected." + statement);
                    }
                } else if(statementChange.getKind() == DifferenceKind.DELETE) {

                    if (!(statement instanceof IfStatement || statement instanceof VariableDeclarationStatement
                            || statement instanceof ReturnStatement 
                            || statement instanceof CatchClause
                            || statement instanceof ThrowStatement
                            || statement instanceof ForStatement
                            || statement instanceof EnhancedForStatement
                            || statement instanceof WhileStatement)) {
                        fail("Unexpected statement type detected." + statement);
                    }
                } else if(statementChange.getKind() == DifferenceKind.CHANGE) {

                    if (!(statement instanceof ForStatement)) {
                        fail("Unexpected statement type detected." + statement);
                    }
                } else {
                    fail("Unexpected DifferenceKind: " + statementChange.getKind());
                }

            } else {
                fail("No other diff elements than StatementChange should have been detected.:" + diff);
            }
            logger.debug(diff.getKind() + ": " + diff.getClass().getName());
        }
        logger.debug("Found Differences: " + differences.size());
    }
}
