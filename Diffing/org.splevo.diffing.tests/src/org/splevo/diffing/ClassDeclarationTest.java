package org.splevo.diffing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.junit.Test;
import org.splevo.diffing.emfcompare.java2kdmdiff.ClassInsert;
import org.splevo.modisco.util.KDMUtil;

/**
 * Unit test to prove the differencing of class declarations.
 */
public class ClassDeclarationTest {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(ClassDeclarationTest.class);

    /** Source path to the original implementation. */
    private static final File TEST_FILE_1 = new File("testmodels/implementation/classdeclaration/a/a_java2kdm.xmi");

    /** Source path to the modified implementation. */
    private static final File TEST_FILE_2 = new File("testmodels/implementation/classdeclaration/b/b_java2kdm.xmi");

    /**
     * Test method to detect changes in the class declarations.
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
        assertEquals("Wrong number of differences detected", 1, differences.size());

        for (DiffElement diffElement : differences) {
            if (diffElement instanceof ClassInsert) {
                ClassInsert classInsert = ((ClassInsert) diffElement);
                ClassDeclaration classDecl = classInsert.getClassLeft();
                assertEquals("Wrong detected class", "AddedClassDeclaration", classDecl.getName());
                
            } else {
                fail("No other diff elements than FieldInsert should have been detected.");
            }
            logger.debug(diffElement.getKind() + ": " + diffElement.getClass().getName());
        }
    }
}
