package org.splevo.diffing.java.modisco;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.EnumDeclaration;
import org.eclipse.gmt.modisco.java.Package;
import org.junit.Test;
import org.splevo.diffing.DiffingException;
import org.splevo.diffing.java.modisco.java2kdmdiff.ClassDelete;
import org.splevo.diffing.java.modisco.java2kdmdiff.ClassInsert;
import org.splevo.diffing.java.modisco.java2kdmdiff.EnumDeclarationChange;
import org.splevo.diffing.java.modisco.java2kdmdiff.PackageDelete;
import org.splevo.diffing.java.modisco.java2kdmdiff.PackageInsert;

/**
 * Unit test to prove the differencing of class declarations.
 */
public class ClassDeclarationTest extends AbstractDiffingTest {

	/** The logger for this class. */
	private Logger logger = Logger.getLogger(ClassDeclarationTest.class);

	/** Source path to the original implementation. */
	private static final File TEST_DIR_1 = new File(
			"testmodels/implementation/classdeclaration/a");

	/** Source path to the modified implementation. */
	private static final File TEST_DIR_2 = new File(
			"testmodels/implementation/classdeclaration/b");

	/**
	 * Test method to detect changes in the class and package declarations.
	 * 
	 * @throws DiffingException
	 *             Identifies a failed diffing.
	 */
	@Test
	public void testDoDiff() throws DiffingException {

		DiffModel diff = differ.doDiff(TEST_DIR_1.toURI(), TEST_DIR_2.toURI(),
				diffOptions);

		EList<DiffElement> differences = diff.getDifferences();
		logger.debug("Found Differences: " + differences.size());
		assertEquals("Wrong number of differences detected", 3,
				differences.size());

		for (DiffElement diffElement : differences) {

			if (diffElement instanceof ClassInsert) {
				ClassInsert classInsert = ((ClassInsert) diffElement);
				ClassDeclaration classDecl = classInsert.getClassLeft();
				assertEquals("Wrong class detected as top level insert diff",
						"AddedClassDeclaration", classDecl.getName());

			} else if (diffElement instanceof EnumDeclarationChange) {
				EnumDeclarationChange enumChange = ((EnumDeclarationChange) diffElement);
				EnumDeclaration enumLeft = enumChange.getEnumLeft();
				assertEquals("Wrong enum detected as changed", "EnumChange",
						enumLeft.getName());
				assertEquals("Wrong number of constants in enum", 3, enumLeft
						.getEnumConstants().size());

			} else if (diffElement instanceof PackageInsert) {
				PackageInsert packageInsert = (PackageInsert) diffElement;
				Package packageDeclaration = packageInsert.getPackageLeft();
				assertEquals("Wrong package detected as inserted",
						"newpackage", packageDeclaration.getName());
				assertEquals("wrong number of diffs contained in package", 2,
						packageInsert.getSubDiffElements().size());

				// analyse the sub diff elements of the package insert
				for (DiffElement subDiff : packageInsert.getSubDiffElements()) {
					if (subDiff instanceof PackageInsert) {
						PackageInsert subPackageInsert = (PackageInsert) subDiff;
						Package subPackageDeclaration = subPackageInsert
								.getPackageLeft();
						EList<DiffElement> subPackDiffElements = subPackageInsert
								.getSubDiffElements();
						assertEquals("Wrong sub-package as inserted", "sub",
								subPackageDeclaration.getName());
						assertEquals("Wrong number of diffs in sub-package", 1,
								subPackDiffElements.size());

						assertTrue(
								"Sub diff element ist not class insert",
								(subPackDiffElements.get(0) instanceof ClassInsert));

					} else if (subDiff instanceof ClassDeclaration) {
						ClassDeclaration subClassDeclaration = (ClassDeclaration) subDiff;
						assertEquals("Wrong class detected as inserted",
								"NewSubPackageClass",
								subClassDeclaration.getName());
					}
				}

			} else {
				fail("No other diff elements than class and package should have been detected.");
			}
		}
	}

	/**
	 * Reverse test method to detect changes in the class and package
	 * declarations for opposite change types (e.g. class delete instead of
	 * class insert).
	 * 
	 * @throws DiffingException
	 *             Identifies a failed diffing.
	 */
	@Test
	public void testDoDiffReverse() throws DiffingException {

		DiffModel diff = differ.doDiff(TEST_DIR_2.toURI(), TEST_DIR_1.toURI(),
				diffOptions);

		EList<DiffElement> differences = diff.getDifferences();
		logger.debug("Found Differences: " + differences.size());
		assertEquals("Wrong number of differences detected", 3,
				differences.size());

		for (DiffElement diffElement : differences) {
			if (diffElement instanceof ClassDelete) {
				ClassDelete classDelete = ((ClassDelete) diffElement);
				ClassDeclaration classDecl = classDelete.getClassRight();
				assertEquals("Wrong class detected as top level insert diff",
						"AddedClassDeclaration", classDecl.getName());

			} else if (diffElement instanceof EnumDeclarationChange) {
				EnumDeclarationChange enumChange = ((EnumDeclarationChange) diffElement);
				EnumDeclaration enumLeft = enumChange.getEnumLeft();
				assertEquals("Wrong enum detected as changed", "EnumChange",
						enumLeft.getName());
				assertEquals("Wrong number of constants in enum", 2, enumLeft
						.getEnumConstants().size());

			} else if (diffElement instanceof PackageDelete) {
				PackageDelete packageDelete = (PackageDelete) diffElement;
				Package packageDeclaration = packageDelete.getPackageRight();
				assertEquals("Wrong package detected as deleted", "newpackage",
						packageDeclaration.getName());
				assertEquals("wrong number of diffs contained in package", 2,
						packageDelete.getSubDiffElements().size());

				// check the content of the sub package
				// analyse the sub diff elements of the package insert
				for (DiffElement subDiff : packageDelete.getSubDiffElements()) {
					if (subDiff instanceof PackageDelete) {
						PackageDelete subPackageDelete = (PackageDelete) subDiff;
						Package subPackageDeclaration = subPackageDelete
								.getPackageRight();
						assertEquals("Wrong sub-package detected as inserted",
								"sub", subPackageDeclaration.getName());
						assertEquals(
								"Wrong number of diffs contained in sub-package",
								1, subPackageDelete.getSubDiffElements().size());

						assertTrue(
								"Sub diff element ist not class delete",
								(subPackageDelete.getSubDiffElements().get(0) instanceof ClassDelete));

					} else if (subDiff instanceof ClassDeclaration) {
						ClassDeclaration subClassDeclaration = (ClassDeclaration) subDiff;
						assertEquals("Wrong class detected as inserted",
								"NewSubPackageClass",
								subClassDeclaration.getName());
					}
				}

			} else {
				logger.error("Detected Diff: " + diffElement.getClass());
				fail("No other diff elements than class and package delete should have been detected.");
			}
		}
	}
}
