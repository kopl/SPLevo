package org.splevo.diffing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.util.ModelUtils;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.junit.Test;
import org.splevo.diffing.emfcompare.java2kdmdiff.ImportDelete;
import org.splevo.diffing.emfcompare.java2kdmdiff.ImportInsert;
import org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange;
import org.splevo.modisco.util.KDMUtil;

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
    private static final File VARDECL_TEST_FILE_1 = new File(
            "testmodels/implementation/variabledeclaration/1/1_java2kdm.xmi");

    /** Source path to the jscience based calculator implementation. */
    private static final File VARDECL_TEST_FILE_2 = new File(
            "testmodels/implementation/variabledeclaration/2/2_java2kdm.xmi");

    /**
     * Test method for
     * {@link org.splevo.diffing.EMFCompareDiffingService#getDiff(org.eclipse.modisco.java.composition.javaapplication.JavaApplication, org.eclipse.modisco.java.composition.javaapplication.JavaApplication)}
     * .
     * 
     * This test compares two implementations with differing variable declaration statements. The
     * differing implentations are: <code>
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
     * @throws IOException
     *             Identifies that either the source models could not be loaded or the diff model
     *             could not be serialized.
     * @throws InterruptedException The diffing process has been externally interrupted.
     */
    @Test
    public void testVariableDeclarationDiff() throws IOException, InterruptedException {
        JavaApplication leadingModel = KDMUtil.loadKDMModel(VARDECL_TEST_FILE_1);
        JavaApplication integrationModel = KDMUtil.loadKDMModel(VARDECL_TEST_FILE_2);

        Java2KDMDiffingService diffingService = new Java2KDMDiffingService();
        diffingService.getIgnorePackages().add("java.lang");
        diffingService.getIgnorePackages().add("java.math");

        DiffModel diff = diffingService.doDiff(integrationModel, leadingModel);

        EList<DiffElement> differences = diff.getDifferences();
        assertEquals("Wrong number of differences detected", 3, differences.size());

        for (DiffElement diffElement : differences) {
            if (diffElement instanceof ImportInsert) {
                String importedClass = ((ImportInsert) diffElement).getImportLeft().getImportedElement().getName();
                assertEquals("BigDecimal should have been recognized as new import", "BigDecimal", importedClass);
            } else if (diffElement instanceof ImportDelete) {
                String importedClass = ((ImportDelete) diffElement).getImportRight().getImportedElement().getName();
                assertEquals("BigInteger should have been recognized as new import", "BigInteger", importedClass);
            } else if (diffElement instanceof StatementChange) {
                Statement changedStatement = ((StatementChange) diffElement).getStatementRight();
                assertTrue("Wrong type of statement", (changedStatement instanceof VariableDeclarationStatement));
            } else {
                fail("No other diff elements than StatementChange, ImportInsert and ImportDelete should have been detected. Detected instead: "
                        + diffElement);
            }
            logger.debug(diffElement.getKind() + ": " + diffElement.getClass().getName());
        }

        logger.debug("Found Differences: " + differences.size());

        ModelUtils.save(diff, "testresult/variableDeclDiffModel.java2kdmdiff");
    }

}
