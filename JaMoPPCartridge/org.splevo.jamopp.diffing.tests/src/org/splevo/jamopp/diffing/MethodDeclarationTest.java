package org.splevo.jamopp.diffing;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.members.Method;
import org.junit.Test;
import org.splevo.jamopp.diffing.jamoppdiff.MethodChange;

import com.google.common.collect.Sets;

/**
 * Unit test to prove the differencing of method declarations.
 */
public class MethodDeclarationTest {

    private String basePath = "testmodels/implementation/methoddeclaration/";

    /**
     * Test method to detect changes in the method declarations.
     *
     * @throws Exception
     *             Identifies a failed diffing.
     */
    @Test
    public void testDoDiff() throws Exception {

		TestUtil.setUp();
		File testFileA = new File(basePath + "a/ClassA.java");
		File testFileB = new File(basePath + "b/ClassA.java");
		ResourceSet rsA = TestUtil.loadResourceSet(Sets.newHashSet(testFileA));
		ResourceSet rsB = TestUtil.loadResourceSet(Sets.newHashSet(testFileB));

		JaMoPPDiffer differ = new JaMoPPDiffer();
		Comparison comparison = differ.doDiff(rsA, rsB, TestUtil.DIFF_OPTIONS);

		EList<Diff> differences = comparison.getDifferences();

        assertThat("Wrong number of differences detected", differences.size(), is(1));
        assertThat("Wrong change type", differences.get(0), instanceOf(MethodChange.class));
        MethodChange methodChange = ((MethodChange) differences.get(0));
        assertThat("Wrong DifferenceKind", methodChange.getKind(), is(DifferenceKind.ADD));
        Method method = methodChange.getChangedMethod();
        assertThat("Wrong method", method.getName(), is("newMethod"));
    }
}
