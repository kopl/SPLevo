package org.splevo.diffing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.junit.Test;
import org.splevo.diffing.emfcompare.java2kdmdiff.FieldDelete;
import org.splevo.diffing.emfcompare.java2kdmdiff.FieldInsert;
import org.splevo.modisco.util.KDMUtil;

/**
 * Unit test to prove the differencing of field declarations.
 */
public class FieldDeclarationTest {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(FieldDeclarationTest.class);

    /** Source path to the original implementation. */
    private static final File TEST_FILE_1 = new File("testmodels/implementation/fielddeclaration.test/a/a_java2kdm.xmi");

    /** Source path to the modified implementation. */
    private static final File TEST_FILE_2 = new File("testmodels/implementation/fielddeclaration.test/b/b_java2kdm.xmi");

    /**
     * Test method to detect changes in the field declarations.
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
        assertEquals("Wrong number of differences detected", 2, differences.size());

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
                
            } else {
                fail("No other diff elements than FieldInsert should have been detected.");
            }
            logger.debug(diffElement.getKind() + ": " + diffElement.getClass().getName());
        }
        logger.debug("Found Differences: " + differences.size());
    }
}
