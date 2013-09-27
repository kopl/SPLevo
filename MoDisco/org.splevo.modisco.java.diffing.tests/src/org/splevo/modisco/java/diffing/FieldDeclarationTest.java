package org.splevo.modisco.java.diffing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.gmt.modisco.java.ArrayCreation;
import org.eclipse.gmt.modisco.java.ArrayInitializer;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.junit.Test;
import org.splevo.diffing.DiffingException;
import org.splevo.modisco.java.diffing.java2kdmdiff.FieldDeclarationChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.FieldDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.FieldInsert;

/**
 * Unit test to prove the differencing of field declarations.
 */
public class FieldDeclarationTest extends AbstractDiffingTest {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(FieldDeclarationTest.class);

    /** Source path to the original implementation. */
    private static final File TEST_DIR_1 = new File("testmodels/implementation/fielddeclaration.test/a");

    /** Source path to the modified implementation. */
    private static final File TEST_DIR_2 = new File("testmodels/implementation/fielddeclaration.test/b");

    /**
     * Test method to detect changes in the field declarations.
     * 
	 * @throws DiffingException
	 *             Identifies a failed diffing.
     */
    @Test
    public void testDoDiff() throws DiffingException {
    	
    	DiffModel diff = differ.doDiff(TEST_DIR_1.toURI(), TEST_DIR_2.toURI(),
				diffOptions);

        EList<DiffElement> differences = diff.getDifferences();
        assertEquals("Wrong number of differences detected", 3, differences.size());

        for (DiffElement diffElement : differences) {
            if (diffElement instanceof FieldInsert) {
                FieldInsert fieldInsert = ((FieldInsert) diffElement);
                FieldDeclaration field = fieldInsert.getFieldLeft();
                assertEquals("Wrong number of declared fragments", 1, field.getFragments().size());
                assertEquals("Wrong variable detected", "newField", field.getFragments().get(0).getName());

            } else if (diffElement instanceof FieldDelete) {
                FieldDelete fieldInsert = ((FieldDelete) diffElement);
                FieldDeclaration field = fieldInsert.getFieldRight();
                assertEquals("Wrong number of declared fragments", 1, field.getFragments().size());
                assertEquals("Wrong variable detected", "removeField", field.getFragments().get(0).getName());

            } else if (diffElement instanceof FieldDeclarationChange) {
                FieldDeclarationChange fieldChange = (FieldDeclarationChange) diffElement;
                FieldDeclaration fieldLeft = fieldChange.getFieldLeft();
                assertEquals("Wrong number of declared fragments", 1, fieldLeft.getFragments().size());
                assertEquals("Wrong variable detected", "newValueArray", fieldLeft.getFragments().get(0).getName());
                ArrayCreation arrayCreationleft = (ArrayCreation) fieldLeft.getFragments().get(0).getInitializer();
                ArrayInitializer initializerLeft = arrayCreationleft.getInitializer();
                assertEquals("Wrong number of initialized array values", 3, initializerLeft.getExpressions().size());

            } else {
                fail("No other diff elements than FieldInsert should have been detected. (" + diffElement + ")");
            }
            logger.debug(diffElement.getKind() + ": " + diffElement.getClass().getName());
        }
        logger.debug("Found Differences: " + differences.size());
    }
}
