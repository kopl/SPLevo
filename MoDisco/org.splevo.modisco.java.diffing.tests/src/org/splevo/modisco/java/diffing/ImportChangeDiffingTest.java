package org.splevo.modisco.java.diffing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.junit.Test;
import org.splevo.diffing.DiffingException;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImportDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImportInsert;

/**
 * Test the diffing of changed imports.
 * 
 * @author Benjamin Klatt
 * 
 */
public class ImportChangeDiffingTest extends AbstractDiffingTest {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(ImportChangeDiffingTest.class);

    /** Source path to the native calculator implementation. */
    private static final File TEST_DIR_1 = new File("testmodels/implementation/importDiffing/1");

    /** Source path to the jscience based calculator implementation. */
    private static final File TEST_DIR_2 = new File("testmodels/implementation/importDiffing/2");

    /**
     * Test method to detect changes in import declarations.
     * 
	 * @throws DiffingException
	 *             Identifies a failed diffing.
     */
    @Test
    public void testDoDiff() throws DiffingException {
    	
    	DiffModel diff = differ.doDiff(TEST_DIR_1.toURI(), TEST_DIR_2.toURI(),
				diffOptions);

        EList<DiffElement> differences = diff.getDifferences();
        assertEquals("Wrong number of differences detected", 2, differences.size());

        for (DiffElement diffElement : differences) {
            if (diffElement instanceof ImportInsert) {
                String importedClass = ((ImportInsert) diffElement).getImportLeft().getImportedElement().getName();
                assertEquals("BigDecimal should have been recognized as new import", "BigDecimal", importedClass);
            } else if (diffElement instanceof ImportDelete) {
                String importedClass = ((ImportDelete) diffElement).getImportRight().getImportedElement().getName();
                assertEquals("BigInteger should have been recognized as new import", "BigInteger", importedClass);
            } else {
                fail("No other diff elements than ImportInsert and ImportDelete should have been detected.");
            }
            logger.debug(diffElement.getKind() + ": " + diffElement.getClass().getName());
        }
        logger.debug("Found Differences: " + differences.size());

        //ModelUtils.save(diff, "testresult/importDiffModel.java2kdmdiff");
    }

}
