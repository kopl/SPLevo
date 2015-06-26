package org.splevo.jamopp.JaMoPPVPM.software.impl;

import static org.junit.Assert.*;

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
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.resource.JavaSourceOrClassFileResourceFactoryImpl;
import org.emftext.language.java.resource.java.IJavaOptions;
import org.emftext.language.java.resource.java.mopp.JavaResource;
import org.emftext.language.java.statements.Statement;
import org.junit.Before;
import org.junit.Test;
import org.splevo.jamopp.vpm.software.CommentableSoftwareElement;
import org.splevo.jamopp.vpm.software.softwareFactory;

/**
 * Test if CommentableSoftwareElements are set in the model.
 *
 * @author Max Scheerer
 *
 */
public class CommentableSoftwareElementImplTest {

    /** Source path to the original implementation. */
    private static final File TEST_FILE = new File("testcode/ExampleClass.java");

    /** The class of the test code. */
    private CompilationUnit compilationUnit = null;

    /**
     * Initialize the resources to test.
     *
     * @throws IOException
     *             An error during test initialization.
     */
    @Before
    public void setUp() throws IOException {

        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));

        ResourceSet rs = setUpResourceSet();
        JavaResource resource = (JavaResource) parseResource(TEST_FILE, rs);
        compilationUnit = (CompilationUnit) resource.getContents().get(0);
    }

    /**
     * Test if the CommentableSoftwareElement was set in the model. The comment which mark a specific variant in the code is actually a concatenation
     * of the variationpoint and a specific variant id. At this point the variationpoint model is not used in the test case and we assume that the comment
     * from the ExampleClass is the correct id name.
     *
     * @throws NullPointerException
     *             An error during location.
     */
    @Test
    public void testCommentableSoftwareElement() throws NullPointerException {

    	CommentableSoftwareElement comment = softwareFactory.eINSTANCE.createCommentableSoftwareElement();
    	comment.setCompilationUnit(compilationUnit);
    	comment.setId("//SPLEVO_REF VariationPointID_VariantID");
		
		Method method = compilationUnit.getClassifiers().get(0).getMethods().get(1);
 		Statement statement = ((ClassMethod) method).getStatements().get(0);

 		assertEquals(statement, comment.getWrappedElement());
    }

    /**
     * Load a specific resource.
     *
     * @param file
     *            The file object to load as resource.
     * @param rs
     *            The resource set to add it to.
     * @return The parsed resource.
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
        EPackage.Registry.INSTANCE.put("http://www.emftext.org/java", JavaPackage.eINSTANCE);
        Map<String, Object> extensionToFactoryMap = rs.getResourceFactoryRegistry().getExtensionToFactoryMap();
        extensionToFactoryMap.put("java", new JavaSourceOrClassFileResourceFactoryImpl());
        extensionToFactoryMap.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
        Map<Object, Object> options = rs.getLoadOptions();
        options.put(IJavaOptions.DISABLE_LAYOUT_INFORMATION_RECORDING, Boolean.FALSE);
        options.put(IJavaOptions.DISABLE_LOCATION_MAP, Boolean.FALSE);
        return rs;
    }

}
