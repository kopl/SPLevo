package org.splevo.diffing.java.modisco;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.junit.Test;
import org.splevo.diffing.DiffingException;
import org.splevo.diffing.java.modisco.java2kdmdiff.FieldDelete;
import org.splevo.diffing.java.modisco.java2kdmdiff.FieldInsert;
import org.splevo.diffing.java.modisco.java2kdmdiff.ImplementsInterfaceDelete;
import org.splevo.diffing.java.modisco.java2kdmdiff.ImplementsInterfaceInsert;
import org.splevo.diffing.java.modisco.java2kdmdiff.ImportDelete;
import org.splevo.diffing.java.modisco.java2kdmdiff.ImportInsert;
import org.splevo.tests.SPLevoTestUtil;

/**
 * Test the diffing of a class with a new implements interface signature.
 * 
 * The newly implemented interface is java.io.Serializable.
 * 
 * @author Benjamin Klatt
 * 
 */
public class InterfaceImplementationChangeDiffingTest extends AbstractDiffingTest {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(InterfaceImplementationChangeDiffingTest.class);

    /**
     * Test method for interface implementation changes.
     * 
	 * @throws DiffingException
	 *             Identifies a failed diffing.
     */
    @Test
    public void testInserts() throws DiffingException {
    	
    	DiffModel diff = differ.doDiff(
    								SPLevoTestUtil.INTERFACE_IMPLEMENT_TEST_DIR_1.toURI(), 
    								SPLevoTestUtil.INTERFACE_IMPLEMENT_TEST_DIR_2.toURI(),
    								diffOptions);

    	EList<DiffElement> differences = diff.getDifferences();
        assertEquals("Wrong number of differences detected", 3, differences.size());

        for (DiffElement diffElement : differences) {
            if (diffElement instanceof ImportInsert) {
                String importedClass = ((ImportInsert) diffElement).getImportLeft().getImportedElement().getName();
                assertEquals("Serializable should have been recognized as new import", "Serializable", importedClass);
            } else if (diffElement instanceof ImplementsInterfaceInsert) {
                String implementedInterface = ((ImplementsInterfaceInsert) diffElement).getImplementedInterface()
                        .getName();
                assertEquals("Serializable should have been recognized as new implements class signature",
                        "Serializable", implementedInterface);
            } else if (diffElement instanceof FieldInsert) {
                FieldDeclaration fieldDecl = ((FieldInsert) diffElement).getFieldLeft();
                String fieldName = fieldDecl.getFragments().get(0).getName();
                assertEquals("serialVersionUID should have been recognized as new field", "serialVersionUID", fieldName);
            } else {
                fail("No other diff elements than ImportInsert, ImplementsInterfaceInsert and FieldInsert should have been detected. "
                        + diffElement);
            }
            logger.debug(diffElement.getKind() + ": " + diffElement.getClass().getName());
        }
        logger.debug("Found Differences: " + differences.size());
    }

    /**
     * Test method for detecting deleted interface implementations.
     * 
	 * @throws DiffingException
	 *             Identifies a failed diffing.
     */
    @Test
    public void testDeletes() throws DiffingException {
    	
    	DiffModel diff = differ.doDiff(
    								SPLevoTestUtil.INTERFACE_IMPLEMENT_TEST_DIR_2.toURI(), 
    								SPLevoTestUtil.INTERFACE_IMPLEMENT_TEST_DIR_1.toURI(),
    								diffOptions);
    	
        EList<DiffElement> differences = diff.getDifferences();
        assertEquals("Wrong number of differences detected", 3, differences.size());

        for (DiffElement diffElement : differences) {
            if (diffElement instanceof ImportDelete) {
                String importedClass = ((ImportDelete) diffElement).getImportRight().getImportedElement().getName();
                assertEquals("Serializable should have been recognized as removed import", "Serializable",
                        importedClass);
            } else if (diffElement instanceof ImplementsInterfaceDelete) {
                String implementedInterface = ((ImplementsInterfaceDelete) diffElement).getImplementedInterface()
                        .getName();
                assertEquals("Serializable should have been recognized as removed implements class signature",
                        "Serializable", implementedInterface);
            } else if (diffElement instanceof FieldDelete) {
                FieldDeclaration fieldDecl = ((FieldDelete) diffElement).getFieldRight();
                String fieldName = fieldDecl.getFragments().get(0).getName();
                assertEquals("serialVersionUID should have been recognized as removed field", "serialVersionUID",
                        fieldName);
            } else {
                fail("No other diff elements than ImportDelete, ImplementsInterfaceDelete and FieldDelete should have been detected. "
                        + diffElement);
            }
            logger.debug(diffElement.getKind() + ": " + diffElement.getClass().getName());
        }
        logger.debug("Found Differences: " + differences.size());

        // ModelUtils.save(diff, "testresult/importDiffModel.java2kdmdiff");
    }

}
