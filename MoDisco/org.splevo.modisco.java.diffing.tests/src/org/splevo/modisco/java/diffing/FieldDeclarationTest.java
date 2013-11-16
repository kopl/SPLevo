package org.splevo.modisco.java.diffing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.gmt.modisco.java.ArrayCreation;
import org.eclipse.gmt.modisco.java.ArrayInitializer;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.junit.Test;
import org.splevo.diffing.DiffingException;
import org.splevo.diffing.DiffingNotSupportedException;
import org.splevo.modisco.java.diffing.java2kdmdiff.FieldChange;

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
    public void testDoDiff() throws DiffingException, DiffingNotSupportedException {

        Java2KDMDiffer differ = new Java2KDMDiffer();
    	Comparison diff = differ.doDiff(TEST_DIR_1.toURI(), TEST_DIR_2.toURI(),
				diffOptions);

        EList<Diff> differences = diff.getDifferences();
        assertEquals("Wrong number of differences detected", 3, differences.size());

        for (Diff diffElement : differences) {
            if (diffElement instanceof FieldChange) {
                FieldChange fieldChange = ((FieldChange) diffElement);
                FieldDeclaration field = fieldChange.getChangedField();
                assertEquals("Wrong number of declared fragments", 1, field.getFragments().size());
                
                String fieldName = field.getFragments().get(0).getName();
                if(fieldChange.getKind() == DifferenceKind.ADD){
                    assertEquals("Wrong variable detected", "newField", fieldName);
                } else if (fieldChange.getKind() == DifferenceKind.DELETE){
                    assertEquals("Wrong variable detected", "removeField", fieldName);
                } else {
                    assertEquals("Wrong variable detected", "newValueArray", fieldName);
                    ArrayCreation arrayCreationleft = (ArrayCreation) field.getFragments().get(0).getInitializer();
                    ArrayInitializer initializerLeft = arrayCreationleft.getInitializer();
                    assertEquals("Wrong number of initialized array values", 3, initializerLeft.getExpressions().size());
                }

            } else {
                fail("No other diff elements than FieldChange should have been detected. (" + diffElement + ")");
            }
            logger.debug(diffElement.getKind() + ": " + diffElement.getClass().getName());
        }
        logger.debug("Found Differences: " + differences.size());
    }
}
