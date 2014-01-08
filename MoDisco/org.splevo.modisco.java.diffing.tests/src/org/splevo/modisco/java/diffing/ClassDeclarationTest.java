package org.splevo.modisco.java.diffing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.EnumDeclaration;
import org.eclipse.gmt.modisco.java.Package;
import org.junit.Test;
import org.splevo.diffing.DiffingException;
import org.splevo.diffing.DiffingNotSupportedException;
import org.splevo.modisco.java.diffing.java2kdmdiff.ClassChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.EnumChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.PackageChange;

/**
 * Unit test to prove the differencing of class declarations.
 */
public class ClassDeclarationTest extends AbstractDiffingTest {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(ClassDeclarationTest.class);

    /** Source path to the original implementation. */
    private static final File TEST_DIR_1 = new File("testmodels/implementation/classdeclaration/a");

    /** Source path to the modified implementation. */
    private static final File TEST_DIR_2 = new File("testmodels/implementation/classdeclaration/b");

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testDoDiff() throws Exception {

        Java2KDMDiffer differ = new Java2KDMDiffer();
        Comparison comparison = differ.doDiff(TEST_DIR_1.toURI(), TEST_DIR_2.toURI(), diffOptions);

        EList<Diff> differences = comparison.getDifferences();

        for (Diff diff : differences) {
            logger.debug(diff.getKind() + ": " + diff.getClass().getSimpleName() + " : " + diff.getMatch());
        }
        logger.debug("Found Differences: " + differences.size());
        assertEquals("Wrong number of differences detected", 3, differences.size());

        for (Diff diffElement : differences) {

            if (diffElement instanceof ClassChange) {
                ClassChange classChange = ((ClassChange) diffElement);
                ClassDeclaration classDecl = classChange.getChangedClass();
                assertEquals("Wrong class detected as top level insert diff", "AddedClassDeclaration",
                        classDecl.getName());

            } else if (diffElement instanceof EnumChange) {
                EnumChange enumChange = ((EnumChange) diffElement);
                EnumDeclaration enumLeft = enumChange.getChangedEnum();
                assertEquals("Wrong enum detected as changed", "EnumChange", enumLeft.getName());
                assertEquals("Wrong number of constants in enum", 3, enumLeft.getEnumConstants().size());

            } else if (diffElement instanceof PackageChange) {
                PackageChange packageChange = (PackageChange) diffElement;
                Package packageDeclaration = packageChange.getChangedPackage();
                assertEquals("Wrong package detected as inserted", "newpackage", packageDeclaration.getName());

            } else {
                fail("No other diff elements than class and package should have been detected.");
            }
        }
    }

    /**
     * Reverse test method to detect changes in the class and package declarations for opposite
     * change types (e.g. class delete instead of class insert).
     *
     * @throws DiffingException
     *             Identifies a failed diffing.
     */
    @Test
    public void testDoDiffReverse() throws DiffingException, DiffingNotSupportedException {

        Java2KDMDiffer differ = new Java2KDMDiffer();
        Comparison diff = differ.doDiff(TEST_DIR_2.toURI(), TEST_DIR_1.toURI(), diffOptions);

        EList<Diff> differences = diff.getDifferences();
        logger.debug("Found Differences: " + differences.size());
        assertEquals("Wrong number of differences detected", 3, differences.size());

        for (Diff diffElement : differences) {
            if (diffElement instanceof ClassChange) {
                ClassChange classChange = ((ClassChange) diffElement);
                ClassDeclaration classDecl = classChange.getChangedClass();
                assertEquals("Wrong class detected as top level insert diff", "AddedClassDeclaration",
                        classDecl.getName());
                assertTrue("ClassChange is not of type add", (classChange.getKind() == DifferenceKind.DELETE));

            } else if (diffElement instanceof EnumChange) {
                EnumChange enumChange = ((EnumChange) diffElement);
                EnumDeclaration enumLeft = enumChange.getChangedEnum();
                assertEquals("Wrong enum detected as changed", "EnumChange", enumLeft.getName());
                assertEquals("Wrong number of constants in enum", 2, enumLeft.getEnumConstants().size());

            } else if (diffElement instanceof PackageChange) {
                PackageChange packageChange = (PackageChange) diffElement;
                Package packageDeclaration = packageChange.getChangedPackage();
                assertEquals("Wrong package detected as deleted", "newpackage", packageDeclaration.getName());
                assertEquals("wrong difference type", DifferenceKind.DELETE, packageChange.getKind());

            } else {
                logger.error("Detected Diff: " + diffElement.getClass());
                fail("No other diff elements than class and package delete should have been detected.");
            }
        }
    }
}
