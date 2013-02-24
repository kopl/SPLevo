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
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.junit.Test;
import org.splevo.diffing.emfcompare.java2kdmdiff.ClassDelete;
import org.splevo.diffing.emfcompare.java2kdmdiff.ClassInsert;
import org.splevo.diffing.emfcompare.java2kdmdiff.PackageDelete;
import org.splevo.diffing.emfcompare.java2kdmdiff.PackageInsert;
import org.splevo.modisco.util.KDMUtil;

/**
 * Unit test to prove the differencing of class declarations.
 */
public class ClassDeclarationTest extends AbstractDiffingTest {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(ClassDeclarationTest.class);

    /** Source path to the original implementation. */
    private static final File TEST_FILE_1 = new File("testmodels/implementation/classdeclaration/a/a_java2kdm.xmi");

    /** Source path to the modified implementation. */
    private static final File TEST_FILE_2 = new File("testmodels/implementation/classdeclaration/b/b_java2kdm.xmi");

    /**
     * Test method to detect changes in the class and package declarations.
     * 
     * @throws IOException
     *             Identifies that either the source models could not be loaded or the diff model
     *             could not be serialized.
     * @throws InterruptedException
     *             identifies the diffing has been externally interrupted.
     */
    @Test
    public void testDoDiff() throws IOException, InterruptedException {
        JavaApplication leadingModel = KDMUtil.loadKDMModel(TEST_FILE_1);
        JavaApplication integrationModel = KDMUtil.loadKDMModel(TEST_FILE_2);

        Java2KDMDiffingService diffingService = new Java2KDMDiffingService();
        diffingService.getIgnorePackages().add("java.lang");

        DiffModel diff = diffingService.doDiff(integrationModel, leadingModel);

        EList<DiffElement> differences = diff.getDifferences();
        logger.debug("Found Differences: " + differences.size());
        assertEquals("Wrong number of differences detected", 2, differences.size());

        for (DiffElement diffElement : differences) {
            
            if (diffElement instanceof ClassInsert) {
                ClassInsert classInsert = ((ClassInsert) diffElement);
                ClassDeclaration classDecl = classInsert.getClassLeft();
                assertEquals("Wrong class detected as top level insert diff", "AddedClassDeclaration", classDecl.getName());
            
            
            } else if (diffElement instanceof PackageInsert) {
                PackageInsert packageInsert = (PackageInsert) diffElement;
                Package packageDeclaration = packageInsert.getPackageLeft();
                assertEquals("Wrong package detected as inserted", "newpackage", packageDeclaration.getName());
                assertEquals("wrong number of diffs contained in package", 2, packageInsert.getSubDiffElements().size());
                
                // analyse the sub diff elements of the package insert
                for (DiffElement subDiff : packageInsert.getSubDiffElements()) {
                    if (subDiff instanceof PackageInsert) {
                        PackageInsert subPackageInsert = (PackageInsert) subDiff;
                        Package subPackageDeclaration = subPackageInsert.getPackageLeft();
                        assertEquals("Wrong sub-package detected as inserted", "sub", subPackageDeclaration.getName());
                        assertEquals("Wrong number of diffs contained in sub-package", 1, subPackageInsert.getSubDiffElements().size());
            
                        assertTrue("Sub diff element ist not class insert", (subPackageInsert.getSubDiffElements().get(0) instanceof ClassInsert));
                        
                        
                    } else if (subDiff instanceof ClassDeclaration) {
                        ClassDeclaration subClassDeclaration = (ClassDeclaration) subDiff;
                        assertEquals("Wrong class detected as inserted", "NewSubPackageClass", subClassDeclaration.getName());
                    }
                }
                
            } else {
                fail("No other diff elements than class and package should have been detected.");
            }
        }
    }

    /**
     * Reverse test method to detect changes in the class and 
     * package declarations for opposite change types
     * (e.g. class delete instead of class insert). 
     * 
     * @throws IOException
     *             Identifies that either the source models could not be loaded or the diff model
     *             could not be serialized.
     * @throws InterruptedException
     *             identifies the diffing has been externally interrupted.
     */
    @Test
    public void testDoDiffReverse() throws IOException, InterruptedException {
        JavaApplication leadingModel = KDMUtil.loadKDMModel(TEST_FILE_2);
        JavaApplication integrationModel = KDMUtil.loadKDMModel(TEST_FILE_1);

        Java2KDMDiffingService diffingService = new Java2KDMDiffingService();
        diffingService.getIgnorePackages().add("java.lang");

        DiffModel diff = diffingService.doDiff(integrationModel, leadingModel);

        EList<DiffElement> differences = diff.getDifferences();
        logger.debug("Found Differences: " + differences.size());
        assertEquals("Wrong number of differences detected", 2, differences.size());

        for (DiffElement diffElement : differences) {
            if (diffElement instanceof ClassDelete) {
                ClassDelete classDelete = ((ClassDelete) diffElement);
                ClassDeclaration classDecl = classDelete.getClassRight();
                assertEquals("Wrong class detected as top level insert diff", "AddedClassDeclaration", classDecl.getName());
            } else if (diffElement instanceof PackageDelete) {
                PackageDelete packageDelete = (PackageDelete) diffElement;
                Package packageDeclaration = packageDelete.getPackageRight();
                assertEquals("Wrong package detected as deleted", "newpackage", packageDeclaration.getName());
                assertEquals("wrong number of diffs contained in package", 1, packageDelete.getSubDiffElements().size());
                
            } else {
                fail("No other diff elements than class and package delete should have been detected.");
            }
        }
    }
}
