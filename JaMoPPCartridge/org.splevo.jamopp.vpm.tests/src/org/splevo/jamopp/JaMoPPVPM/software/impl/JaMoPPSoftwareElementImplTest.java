package org.splevo.jamopp.JaMoPPVPM.software.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Method;
import org.junit.Before;
import org.junit.Test;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.jamopp.vpm.software.softwareFactory;
import org.splevo.vpm.software.SourceLocation;

/**
 * Test the source location calculation for JaMoPP software elements.
 *
 * @author Benjamin Klatt
 *
 */
public class JaMoPPSoftwareElementImplTest extends JaMoPPJavaSoftwareElementTest {

    /** Source path to the original implementation. */
    private static final File TEST_FILE = new File("testcode/ExampleClass.java");

    /** The class of the test code. */
    private ConcreteClassifier exampleClass = null;

    /**
     * Initialize the resources to test.
     *
     * @throws Exception
     *             An error during test initialization.
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        exampleClass = compilationUnit.getConcreteClassifier("ExampleClass");
    }

    /**
     * Test the location detection of a method defined in a single line.
     *
     * @throws Exception
     *             An error during location.
     */
    @Test
    public void testSingleLineFieldLocation() throws Exception {

        Field field = exampleClass.getFields().get(0);

        JaMoPPSoftwareElement softwareElement = softwareFactory.eINSTANCE.createJaMoPPSoftwareElement();
        softwareElement.setJamoppElement(field);
        SourceLocation location = softwareElement.getSourceLocation();

        assertThat("Wrong start line", location.getStartLine(), is(4));
        assertThat("Wrong start position", location.getStartPosition(), is(31));
        assertThat("Wrong end position", location.getEndPosition(), is(57));
    }

    /**
     * Test that a method specified on more than one line is located as expected.
     *
     * @throws Exception
     *             An error during parsing or locating.
     */
    @Test
    public void testMultiLineMethodLocation() throws Exception {

        Method method = exampleClass.getMethods().get(0);

        JaMoPPSoftwareElement softwareElement = softwareFactory.eINSTANCE.createJaMoPPSoftwareElement();
        softwareElement.setJamoppElement(method);
        SourceLocation location = softwareElement.getSourceLocation();

        assertThat("Wrong start line", location.getStartLine(), is(6));
        assertThat("Wrong start position", location.getStartPosition(), is(61));
        assertThat("Wrong end position", location.getEndPosition(), is(121));
    }

    @Override
    protected File getTestFile() {
        return TEST_FILE;
    }

    @Override
    protected boolean parseLayoutInformation() {
        return false;
    }

}
