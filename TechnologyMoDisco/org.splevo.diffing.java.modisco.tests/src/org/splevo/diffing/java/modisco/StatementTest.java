package org.splevo.diffing.java.modisco;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.gmt.modisco.java.CatchClause;
import org.eclipse.gmt.modisco.java.ExpressionStatement;
import org.eclipse.gmt.modisco.java.IfStatement;
import org.eclipse.gmt.modisco.java.ReturnStatement;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.ThrowStatement;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;
import org.junit.Test;
import org.splevo.diffing.DiffingException;
import org.splevo.diffing.java.modisco.java2kdmdiff.StatementDelete;
import org.splevo.diffing.java.modisco.java2kdmdiff.StatementInsert;

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
    public void testDoDiff() throws DiffingException {
    	
    	DiffModel diff = differ.doDiff(TEST_DIR_1.toURI(), TEST_DIR_2.toURI(),
				diffOptions);

        EList<DiffElement> differences = diff.getDifferences();
        assertEquals("Wrong number of differences detected", 20, differences.size());

        for (DiffElement diffElement : differences) {
            if (diffElement instanceof StatementInsert) {
                StatementInsert statementInsert = ((StatementInsert) diffElement);
                Statement statement = statementInsert.getStatementLeft();
                assertNotNull("The inserted statement should not be null.", statement);

                // check the statements should be either if, return or expression statements
                if (!(statement instanceof IfStatement || statement instanceof ReturnStatement
                        || statement instanceof ExpressionStatement
                        || statement instanceof VariableDeclarationStatement 
                        || statement instanceof CatchClause
                        || statement instanceof ThrowStatement)) {
                    fail("Unexpected statement type detected." + statement);
                }

            } else if (diffElement instanceof StatementDelete) {
                StatementDelete statementDelete = ((StatementDelete) diffElement);
                Statement statement = statementDelete.getStatementRight();
                assertNotNull("The deleted statement should not be null.", statement);

                if (!(statement instanceof IfStatement || statement instanceof VariableDeclarationStatement
                        || statement instanceof ReturnStatement 
                        || statement instanceof CatchClause
                        || statement instanceof ThrowStatement)) {
                    fail("Unexpected statement type detected." + statement);
                }

                assertNotNull("Left container not set.", statementDelete.getLeftContainer());
            } else {
                fail("No other diff elements than StatementInsert and Delete should have been detected.:" + diffElement);
            }
            logger.debug(diffElement.getKind() + ": " + diffElement.getClass().getName());
        }
        logger.debug("Found Differences: " + differences.size());
    }
}
