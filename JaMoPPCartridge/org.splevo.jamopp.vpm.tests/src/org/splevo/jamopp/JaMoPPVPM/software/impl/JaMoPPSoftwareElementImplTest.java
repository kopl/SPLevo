package org.splevo.jamopp.JaMoPPVPM.software.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.emftext.language.java.JavaPackage;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.resource.JavaSourceOrClassFileResourceFactoryImpl;
import org.emftext.language.java.resource.java.IJavaOptions;
import org.emftext.language.java.resource.java.mopp.JavaResource;
import org.junit.Before;
import org.junit.Test;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.jamopp.vpm.software.softwareFactory;
import org.splevo.vpm.software.SourceLocation;

/**
 * Test the source location calculation for JaMoPP software elements.
 * @author Benjamin Klatt
 *
 */
public class JaMoPPSoftwareElementImplTest {

    /** Source path to the original implementation. */
    private static final File TEST_FILE = new File("testcode/ExampleClass.java");

    /** The compilation unit to test the elements of. */
    private CompilationUnit compilationUnit = null;

    /**
     * Initialize the resources to test.
     * @throws IOException
     */
    @Before
	public void setUp() throws IOException {

    	BasicConfigurator.resetConfiguration();
		BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));

		ResourceSet rs = setUpResourceSet();
		JavaResource resource = (JavaResource) parseResource(TEST_FILE, rs);
		compilationUnit = (CompilationUnit) resource.getContents().get(0);
	}

	@Test
	public void testSingleLineFieldLocation() throws Exception {
		ConcreteClassifier exampleClass = compilationUnit.getConcreteClassifier("ExampleClass");

		Field field = exampleClass.getFields().get(0);

		JaMoPPSoftwareElement softwareElement = softwareFactory.eINSTANCE.createJaMoPPSoftwareElement();
		softwareElement.setJamoppElement(field);
		SourceLocation location = softwareElement.getSourceLocation();

		assertThat("Wrong start line", location.getStartLine(), is(4));
		assertThat("Wrong start position", location.getStartPosition(), is(1));
		assertThat("Wrong end line", location.getEndLine(), is(4));
		assertThat("Wrong end position", location.getEndPosition(), is(28));
	}

	@Test
	public void testMultiLineMethodLocation() throws Exception {
		ConcreteClassifier exampleClass = compilationUnit.getConcreteClassifier("ExampleClass");

		Method method = exampleClass.getMethods().get(0);

		JaMoPPSoftwareElement softwareElement = softwareFactory.eINSTANCE.createJaMoPPSoftwareElement();
		softwareElement.setJamoppElement(method);
		SourceLocation location = softwareElement.getSourceLocation();

		assertThat("Wrong start line", location.getStartLine(), is(6));
		assertThat("Wrong start position", location.getStartPosition(), is(1));
		assertThat("Wrong end line", location.getEndLine(), is(8));
		assertThat("Wrong end position", location.getEndPosition(), is(2));
	}

    /**
     * Load a specific resource.
     *
     * @param file
     *            The file object to load as resource.
     * @param rs
     *            The resource set to add it to.
     * @throws IOException
     *             An exception during resource access.
     */
    private static Resource parseResource(File file, ResourceSet rs) throws IOException {
        String filePath = file.getCanonicalPath();
        URI uri = URI.createFileURI(filePath);
        return rs.getResource(uri, true);
    }



    /**
     * Setup the JaMoPP resource set and prepare the parsing options for the java resource type.
     *
     * @return The initialized resource set.
     */
    private static ResourceSet setUpResourceSet() {
        ResourceSet rs = new ResourceSetImpl();
        rs.getLoadOptions().put(IJavaOptions.DISABLE_LAYOUT_INFORMATION_RECORDING, Boolean.FALSE);
        rs.getLoadOptions().put(IJavaOptions.DISABLE_LOCATION_MAP, Boolean.FALSE);
        EPackage.Registry.INSTANCE.put("http://www.emftext.org/java", JavaPackage.eINSTANCE);
        Map<String, Object> extensionToFactoryMap = rs.getResourceFactoryRegistry().getExtensionToFactoryMap();
		extensionToFactoryMap.put("java", new JavaSourceOrClassFileResourceFactoryImpl());
        extensionToFactoryMap.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
        return rs;
    }

}
