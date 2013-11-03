package org.splevo.modisco.java.diffing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;
import org.junit.Test;
import org.splevo.diffing.DiffingException;
import org.splevo.diffing.DiffingNotSupportedException;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImportDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImportInsert;
import org.splevo.modisco.java.diffing.java2kdmdiff.StatementChange;

/**
 * Diffing test case for changed variable declarations.
 * 
 * @author Benjamin Klatt
 * 
 */
public class VariableDeclarationStatementDiffingTest extends
		AbstractDiffingTest {

	/** The logger for this class. */
	private Logger logger = Logger
			.getLogger(VariableDeclarationStatementDiffingTest.class);

	/** Source path to the native calculator implementation. */
	private static final File TEST_DIR_1 = new File(
			"testmodels/implementation/variabledeclaration/1");

	/** Source path to the jscience based calculator implementation. */
	private static final File TEST_DIR_2 = new File(
			"testmodels/implementation/variabledeclaration/2");

	/**
	 * Test method for identifying changed variable declarations. .
	 * 
	 * This test compares two implementations with differing variable
	 * declaration statements. The differing implementations are: <code>
	 * 	BigInteger integerValue1 = new BigInteger("1");
	 * </code> and <code>
	 *  BigDecimal integerValue1 = new BigDecimal("1");
	 * </code>
	 * 
	 * This also includes differing imports which are not in the focus of this
	 * test. Both variable declarations are within a methods methodA() of a
	 * class A.
	 * 
	 * The changes should and up with a changed a single StatementChange.
	 * 
	 * @throws DiffingException
	 *             Identifies a failed diffing.
	 */
	@Test
	public void testVariableDeclarationDiff() throws DiffingException, DiffingNotSupportedException {

		DiffModel diff = differ.doDiff(TEST_DIR_1.toURI(), TEST_DIR_2.toURI(),
				diffOptions);

		EList<DiffElement> differences = diff.getDifferences();
		assertEquals("Wrong number of differences detected", 3,
				differences.size());

		for (DiffElement diffElement : differences) {
			if (diffElement instanceof ImportInsert) {
				String importedClass = ((ImportInsert) diffElement)
						.getImportLeft().getImportedElement().getName();
				assertEquals(
						"BigDecimal should have been recognized as new import",
						"BigDecimal", importedClass);
			} else if (diffElement instanceof ImportDelete) {
				String importedClass = ((ImportDelete) diffElement)
						.getImportRight().getImportedElement().getName();
				assertEquals(
						"BigInteger should have been recognized as new import",
						"BigInteger", importedClass);
			} else if (diffElement instanceof StatementChange) {
				Statement changedStatement = ((StatementChange) diffElement)
						.getStatementRight();
				assertTrue(
						"Wrong type of statement",
						(changedStatement instanceof VariableDeclarationStatement));
			} else {
				fail("Unexpected diff type detected: " + diffElement);
			}
			logger.debug(diffElement.getKind() + ": "
					+ diffElement.getClass().getName());
		}

		logger.debug("Found Differences: " + differences.size());
	}

}
