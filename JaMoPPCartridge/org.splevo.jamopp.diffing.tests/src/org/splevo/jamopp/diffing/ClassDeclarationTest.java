package org.splevo.jamopp.diffing;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.containers.CompilationUnit;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.diffing.jamoppdiff.ClassChange;
import org.splevo.jamopp.diffing.jamoppdiff.CompilationUnitChange;
import org.splevo.jamopp.diffing.jamoppdiff.EnumChange;
import org.splevo.jamopp.diffing.jamoppdiff.PackageChange;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;

import com.google.common.collect.Lists;

/**
 * Unit test to prove the differencing of class declarations.
 *
 * This test also considers package-info files which are ignored by default.
 */
public class ClassDeclarationTest {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(ClassDeclarationTest.class);

    private static ResourceSet setA;
    private static ResourceSet setB;

    /**
     * Initialize the test by loading the resources once to be used by the severall diff tests.
     *
     * @throws Exception A failed initialization
     */
    @BeforeClass
    public static void initTest() throws Exception {

        TestUtil.setUp();

        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        String basePath = "testmodels/implementation/classdeclaration/";
        List<URI> urisA = Lists.asList(URI.createFileURI(basePath + "a"), new URI[] {});
        List<URI> urisB = Lists.asList(URI.createFileURI(basePath + "b"), new URI[] {});
        NullProgressMonitor monitor = new NullProgressMonitor();
        setA = extractor.extractSoftwareModel(urisA, monitor, null);
        setB = extractor.extractSoftwareModel(urisB, monitor, null);
    }

    /**
     * Test method to detect changes in the class and package declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testDoDiff() throws Exception {

        TestUtil.setUp();

        JaMoPPDiffer differ = new JaMoPPDiffer();

        Map<String, String> diffOptions = TestUtil.DIFF_OPTIONS;
        diffOptions.put(JaMoPPDiffer.OPTION_JAMOPP_IGNORE_FILES, "");
        Comparison comparison = differ.doDiff(setA, setB, diffOptions);

        EList<Diff> differences = comparison.getDifferences();
        for (Diff diffElement : differences) {
            logger.debug(diffElement.getClass().getSimpleName());
            //logger.debug(TestUtil.printDiff(diffElement));
        }

        assertThat("Wrong number of differences", differences.size(), is(6));

        for (Diff diffElement : differences) {

            if (diffElement instanceof ClassChange) {
                ClassChange classChange = ((ClassChange) diffElement);
                org.emftext.language.java.classifiers.Class classDecl = classChange.getChangedClass();
                assertEquals("Wrong class detected as top level insert diff", "AddedClassDeclaration",
                        classDecl.getName());

            } else if (diffElement instanceof EnumChange) {
                EnumChange enumChange = ((EnumChange) diffElement);
                Enumeration enumLeft = enumChange.getChangedEnum();
                assertEquals("Wrong enum detected as changed", "EnumChange", enumLeft.getName());
                assertEquals("Wrong number of constants in enum", 3, enumLeft.getConstants().size());

            } else if (diffElement instanceof CompilationUnitChange) {
                CompilationUnitChange unitChange = (CompilationUnitChange) diffElement;
                CompilationUnit unit = unitChange.getChangedCompilationUnit();
                assertThat("wrong difference type", unitChange.getKind(), equalTo(DifferenceKind.ADD));
                assertThat(
                        "Unexpected package added",
                        unit.getName(),
                        anyOf(equalTo("org.splevo.tests.fielddeclaration.AddedClassDeclaration.java"),
                                equalTo("org.splevo.tests.fielddeclaration.newpackage.NewPackageClass.java"),
                                equalTo("org.splevo.tests.fielddeclaration.newpackage.sub.NewSubPackageClass.java")));

            } else if (diffElement instanceof PackageChange) {
                PackageChange packageChange = (PackageChange) diffElement;
                org.emftext.language.java.containers.Package packageDeclaration = packageChange.getChangedPackage();
                assertThat("wrong difference type", packageChange.getKind(), equalTo(DifferenceKind.ADD));
                assertThat("Unexpected package added", packageDeclaration.getName(),
                        anyOf(equalTo("newpackage"), equalTo("sub")));

            } else {
                fail("No other diff elements than class and package should have been detected: " + diffElement);
            }
        }
    }

    /**
     * Reverse test method to detect changes in the class and package declarations for opposite
     * change types (e.g. class delete instead of class insert).
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testDoDiffReverse() throws Exception {

        JaMoPPDiffer differ = new JaMoPPDiffer();

        Map<String, String> diffOptions = TestUtil.DIFF_OPTIONS;
        diffOptions.put(JaMoPPDiffer.OPTION_JAMOPP_IGNORE_FILES, "");
        Comparison comparison = differ.doDiff(setB, setA, diffOptions);

        EList<Diff> differences = comparison.getDifferences();
        for (Diff diffElement : differences) {
            logger.debug(diffElement.getClass().getSimpleName());
            //logger.debug(TestUtil.printDiff(diffElement));
        }

        for (Diff diffElement : comparison.getDifferences()) {
            if (diffElement instanceof ClassChange) {
                ClassChange classChange = ((ClassChange) diffElement);
                org.emftext.language.java.classifiers.Class classDecl = classChange.getChangedClass();
                assertEquals("Wrong class detected as top level insert diff", "AddedClassDeclaration",
                        classDecl.getName());
                assertTrue("ClassChange is not of type add", (classChange.getKind() == DifferenceKind.DELETE));

            } else if (diffElement instanceof EnumChange) {
                EnumChange enumChange = ((EnumChange) diffElement);
                Enumeration enumLeft = enumChange.getChangedEnum();
                assertEquals("Wrong enum detected as changed", "EnumChange", enumLeft.getName());
                assertEquals("Wrong number of constants in enum", 2, enumLeft.getConstants().size());

            } else if (diffElement instanceof CompilationUnitChange) {
                CompilationUnitChange unitChange = (CompilationUnitChange) diffElement;
                CompilationUnit unit = unitChange.getChangedCompilationUnit();
                assertThat("wrong difference type", unitChange.getKind(), equalTo(DifferenceKind.DELETE));
                assertThat(
                        "Unexpected package added",
                        unit.getName(),
                        anyOf(equalTo("org.splevo.tests.fielddeclaration.AddedClassDeclaration.java"),
                                equalTo("org.splevo.tests.fielddeclaration.newpackage.NewPackageClass.java"),
                                equalTo("org.splevo.tests.fielddeclaration.newpackage.sub.NewSubPackageClass.java")));

            } else if (diffElement instanceof PackageChange) {

                PackageChange packageChange = (PackageChange) diffElement;
                org.emftext.language.java.containers.Package packageDeclaration = packageChange.getChangedPackage();
                assertThat("wrong difference type", packageChange.getKind(), equalTo(DifferenceKind.DELETE));
                assertThat("Unexpected package deleted", packageDeclaration.getName(),
                        anyOf(equalTo("newpackage"), equalTo("sub")));
            } else {
                logger.error("Detected Diff: " + diffElement.getClass());
                fail("No other diff elements than class and package delete should have been detected.");
            }
        }
        assertThat("Wrong number of differences", differences.size(), is(6));
    }
}
