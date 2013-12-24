package org.splevo.jamopp.diffing;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Statement;
import org.junit.Test;
import org.splevo.jamopp.diffing.jamoppdiff.ImportChange;
import org.splevo.jamopp.diffing.jamoppdiff.StatementChange;

import com.google.common.collect.Sets;

/**
 * Diffing test case for changed variable declarations.
 *
 * @author Benjamin Klatt
 *
 */
public class VariableDeclarationStatementDiffingTest {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(VariableDeclarationStatementDiffingTest.class);

    /** Source path to the original variable declaration test model. */
    private static final File TEST_FILE_1 = new File("testmodels/implementation/variabledeclaration/a/A.java");

    /** Source path to the modified variable declaration test model. */
    private static final File TEST_FILE_2 = new File("testmodels/implementation/variabledeclaration/b/A.java");

    /**
     * Test method for identifying changed variable declarations. .
     *
     * This test compares two implementations with differing variable declaration statements.<br>
     * The differing implementations are: <code>
     * 	BigInteger integerValue1 = new BigInteger("1");
     * </code> and <code>
     *  BigDecimal integerValue1 = new BigDecimal("1");
     * </code> This is expected to result in a single Statement Change.
     *
     * This also includes differing imports which are not in the focus of this test.<br>
     * Both variable declarations are within a method methodA() of a class A.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testVariableDeclarationDiff() throws Exception {

        TestUtil.setUp();
        ResourceSet rsLeading = TestUtil.loadResourceSet(Sets.newHashSet(TEST_FILE_1));
        ResourceSet rsIntegration = TestUtil.loadResourceSet(Sets.newHashSet(TEST_FILE_2));

        JaMoPPDiffer differ = new JaMoPPDiffer();

        Comparison comparison = differ.doDiff(rsLeading, rsIntegration, TestUtil.DIFF_OPTIONS);
        EList<Diff> differences = comparison.getDifferences();
        for (Diff diff : differences) {
            logger.debug(diff.getKind() + ": " + TestUtil.printDiff(diff));
        }
        assertThat("Difference should contain three diffs", differences.size(), is(3));

        for (Diff diff : differences) {
            if (diff instanceof ImportChange) {
                ImportChange importChange = (ImportChange) diff;
                ClassifierImport classifierImport = (ClassifierImport) importChange.getChangedImport();
                String importedClass = classifierImport.getClassifier().getName();

                if (importChange.getKind() == DifferenceKind.ADD) {
                    assertThat("Wrong added import type", importedClass, is("BigDecimal"));
                } else if (importChange.getKind() == DifferenceKind.DELETE) {
                    assertThat("Wrong deleted import type", importedClass, is("BigInteger"));
                }

            } else if (diff instanceof StatementChange) {
                Statement changedStatement = ((StatementChange) diff).getChangedStatement();
                assertThat("Wrong statement type", changedStatement, is(instanceOf(LocalVariableStatement.class)));

            } else {
                fail("Unexpected diff type detected: " + diff);
            }
        }

        logger.debug("Found Differences: " + differences.size());
    }

}
