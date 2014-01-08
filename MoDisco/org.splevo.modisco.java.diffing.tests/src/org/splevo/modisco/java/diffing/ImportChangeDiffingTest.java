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
import org.junit.Test;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImportChange;

/**
 * Test the diffing of changed imports.
 *
 * @author Benjamin Klatt
 *
 */
public class ImportChangeDiffingTest extends AbstractDiffingTest {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(ImportChangeDiffingTest.class);

    /** Path to the source model of the original import project. */
    private static final File TEST_DIR_1 = new File("testmodels/implementation/importDiffing/a");

    /** Path to the source model of the changed import project. */
    private static final File TEST_DIR_2 = new File("testmodels/implementation/importDiffing/b");

    /**
     * Test method to detect changes in import declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testDoDiff() throws Exception {

        Java2KDMDiffer differ = new Java2KDMDiffer();
        Comparison comparison = differ.doDiff(TEST_DIR_1.toURI(), TEST_DIR_2.toURI(), diffOptions);

        EList<Diff> differences = comparison.getDifferences();
        assertThat("Wrong number of differences detected", differences.size(), is(2));

        for (Diff diff : differences) {
            if (diff instanceof ImportChange) {

                ImportChange importChange = (ImportChange) diff;
                String importedClass = importChange.getChangedImport().getImportedElement().getName();

                if (importChange.getKind() == DifferenceKind.ADD) {
                    assertEquals("BigDecimal should have been recognized as new import", "BigDecimal", importedClass);
                } else if (importChange.getKind() == DifferenceKind.DELETE) {
                    assertEquals("BigInteger should have been recognized as deleted import", "BigInteger",
                            importedClass);
                } else {
                    fail("Wrong DifferenceKind detected: " + importChange.getKind());
                }

            } else {
                fail("No other diff elements than ImportChange should have been detected. Detected: " + diff);
            }
            logger.debug(diff.getKind() + ": " + diff.getClass().getName());
        }
        logger.debug("Found Differences: " + differences.size());

    }

}
