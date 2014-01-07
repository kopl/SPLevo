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
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.junit.Test;
import org.splevo.diffing.DiffingException;
import org.splevo.diffing.DiffingNotSupportedException;
import org.splevo.modisco.java.diffing.java2kdmdiff.ClassChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.FieldChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImportChange;

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

    /** Source path to the original implementation. */
    private static final File TEST_DIR_1 = new File("testmodels/implementation/interface.import.test/basic");

    /** Source path to the modified implementation. */
    private static final File TEST_DIR_2 = new File("testmodels/implementation/interface.import.test/serializable");

    /**
     * Test method for interface implementation changes.
     *
     * @throws DiffingException
     *             Identifies a failed diffing.
     * @throws DiffingNotSupportedException
     *             Identifies that the provided models can not be handled by this differ.
     */
    @Test
    public void testInserts() throws DiffingException, DiffingNotSupportedException {

        Java2KDMDiffer differ = new Java2KDMDiffer();
        Comparison comparison = differ.doDiff(TEST_DIR_1.toURI(), TEST_DIR_2.toURI(), diffOptions);

        EList<Diff> differences = comparison.getDifferences();

        assertEquals("Wrong number of differences detected", 3, differences.size());

        for (Diff diff : differences) {
            if (diff instanceof ImportChange) {
                ImportChange importChange = (ImportChange) diff;
                String importedType = importChange.getChangedImport().getImportedElement().getName();
                assertEquals("Wrong imported type", "Serializable", importedType);
                assertThat("Wrong DifferenceKind", importChange.getKind(), is(DifferenceKind.ADD));

            } else if (diff instanceof ClassChange) {
                ClassChange classChange = (ClassChange) diff;
                assertThat("Wrong DifferenceKind", classChange.getKind(), is(DifferenceKind.CHANGE));

                ClassDeclaration classDecl = classChange.getChangedClass();
                String interfaceName = classDecl.getSuperInterfaces().get(0).getType().getName();
                assertThat("Wrong interface detected as implemented", interfaceName, is("Serializable"));

            } else if (diff instanceof FieldChange) {
                FieldChange fieldChange = (FieldChange) diff;
                assertThat("Wrong DifferenceKind", fieldChange.getKind(), is(DifferenceKind.ADD));
                FieldDeclaration fieldDecl = fieldChange.getChangedField();

                String fieldName = fieldDecl.getFragments().get(0).getName();
                assertThat("Expected field not added", fieldName, is("serialVersionUID"));

            } else {
                fail("Unexpected diff type detected " + diff);
            }
            logger.debug(diff.getKind() + ": " + diff.getClass().getName());
        }
        logger.debug("Found Differences: " + differences.size());
    }

    /**
     * Test method for detecting deleted interface implementations.
     *
     * @throws DiffingException
     *             Identifies a failed diffing.
     * @throws DiffingNotSupportedException
     *             Identifies that the provided models can not be handled by this differ.
     */
    @Test
    public void testDeletes() throws DiffingException, DiffingNotSupportedException {

        Java2KDMDiffer differ = new Java2KDMDiffer();
        Comparison comparison = differ.doDiff(TEST_DIR_2.toURI(), TEST_DIR_1.toURI(), diffOptions);

        EList<Diff> differences = comparison.getDifferences();
        assertEquals("Wrong number of differences detected", 3, differences.size());

        for (Diff diff : differences) {
            if (diff instanceof ImportChange) {
                ImportChange importChange = (ImportChange) diff;
                String importedType = importChange.getChangedImport().getImportedElement().getName();
                assertEquals("Wrong imported type", "Serializable", importedType);
                assertThat("Wrong DifferenceKind", importChange.getKind(), is(DifferenceKind.DELETE));

            } else if (diff instanceof ClassChange) {
                ClassChange classChange = (ClassChange) diff;
                assertThat("Wrong DifferenceKind", classChange.getKind(), is(DifferenceKind.CHANGE));

                ClassDeclaration changedClass = classChange.getChangedClass();
                ClassDeclaration origClass = (ClassDeclaration) comparison.getMatch(changedClass).getRight();
                String interfaceName = origClass.getSuperInterfaces().get(0).getType().getName();
                assertThat("Wrong interface detected as implemented", interfaceName, is("Serializable"));

            } else if (diff instanceof FieldChange) {
                FieldChange fieldChange = (FieldChange) diff;
                assertThat("Wrong DifferenceKind", fieldChange.getKind(), is(DifferenceKind.DELETE));
                FieldDeclaration fieldDecl = fieldChange.getChangedField();

                String fieldName = fieldDecl.getFragments().get(0).getName();
                assertThat("Expected field not added", fieldName, is("serialVersionUID"));

            } else {
                fail("Unexpected diff type detected " + diff);
            }
            logger.debug(diff.getKind() + ": " + diff.getClass().getName());
        }
        logger.debug("Found Differences: " + differences.size());
    }

}
