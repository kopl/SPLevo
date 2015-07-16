package org.splevo.jamopp.JaMoPPVPM.software.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.emftext.language.java.JavaPackage;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.resource.JavaSourceOrClassFileResourceFactoryImpl;
import org.emftext.language.java.resource.java.IJavaOptions;
import org.emftext.language.java.resource.java.mopp.JavaResource;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Statement;
import org.junit.Before;
import org.junit.Test;
import org.splevo.jamopp.vpm.software.CommentableSoftwareElement;
import org.splevo.jamopp.vpm.software.softwareFactory;

/**
 * Test the reference resolving of CommentableSoftwareElements.
 *
 * @author Max Scheerer
 *
 */
public class CommentableSoftwareElementImplTest {

    /** Source path to the original implementation. */
    private static final File TEST_FILE = new File("testcode/CommentedExampleClass.java");

    /** The class of the test code. */
    private CompilationUnit compilationUnit;
    
    private CommentableSoftwareElement subject;

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
        
        subject = softwareFactory.eINSTANCE.createCommentableSoftwareElement();
    }

    /**
     * Tests the automatic conversion during setting a type. Types that are derived from statements
     * and are not member elements are converted to their base type (statement). A block is a member
     * and a statement.
     */
    @Test
    public void testSetTypeForBlock() {
        subject.setType(Block.class);
        
        assertEquals(Block.class, subject.getType());
    }
    
    /**
     * Tests the automatic conversion during setting a type. Types that are derived from statements
     * and are not member elements are converted to their base type (statement). A local variable
     * statement is a statement.
     */
    @Test
    public void testSetTypeForLocalVariableStatement() {
        subject.setType(LocalVariableStatement.class);
        
        assertEquals(Statement.class, subject.getType());
    }
    
    /**
     * Tests the automatic conversion during setting a type. Types that are derived from statements
     * and are not member elements are converted to their base type (statement). A class is a
     * statement and a member.
     */
    @Test
    public void testSetTypeForClass() {
        subject.setType(org.emftext.language.java.classifiers.Class.class);
        
        assertEquals(org.emftext.language.java.classifiers.Class.class, subject.getType());
    }
    
    /**
     * Tests the automatic conversion during setting a type. Types that are derived from statements
     * and are not member elements are converted to their base type (statement). A method is a
     * statement and a member.
     */
    @Test
    public void testSetTypeForMethod() {
        subject.setType(Method.class);
        
        assertEquals(Method.class, subject.getType());
    }
    
    /**
     * Tests the resolution of the referenced element by its ID can type.
     */
    @Test
    public void testResolveStatementReference() {
    	subject.setCompilationUnit(compilationUnit);
    	subject.setId("ID_Statement");
    	subject.setType(Statement.class);
		
    	Statement expected = ((ClassMethod) compilationUnit.getClassifiers().get(0).getMethods().get(1)).getStatements().get(0);
    	EObject actual = subject.getWrappedElement();
    	
 		assertEquals(expected, actual);
    }

    /**
     * Tests the resolution of the referenced element by its ID can type.
     */
    @Test
    public void testResolveMethodReference() {
        subject.setCompilationUnit(compilationUnit);
        subject.setId("ID_Method");
        subject.setType(Method.class);
        
        EObject expected = compilationUnit.getClassifiers().get(0).getMethods().get(1);
        EObject actual = subject.getWrappedElement();
        
        assertEquals(expected, actual);
    }
    
    /**
     * Tests the resolution of the referenced element by its ID can type.
     */
    @Test
    public void testResolveFieldReference() {
        subject.setCompilationUnit(compilationUnit);
        subject.setId("ID_Field");
        subject.setType(Field.class);
        
        EObject expected = compilationUnit.getClassifiers().get(0).getFields().get(0);
        EObject actual = subject.getWrappedElement();
        
        assertEquals(expected, actual);
    }
    
    /**
     * Tests the resolution of the referenced element by its ID can type.
     */
    @Test
    public void testResolveInnerClass() {
        subject.setCompilationUnit(compilationUnit);
        subject.setId("ID_INNER_CLASS");
        subject.setType(org.emftext.language.java.classifiers.Class.class);

        EObject expected = ((ClassMethod) compilationUnit.getClassifiers().get(0).getMethods().get(0)).getStatements()
                .get(0);
        EObject actual = subject.getWrappedElement();

        assertEquals(expected, actual);
    }
    
    /**
     * Tests the resolution of the referenced element by its ID can type.
     */
    @Test
    public void testResolveInnerField() {
        subject.setCompilationUnit(compilationUnit);
        subject.setId("ID_INNER_FIELD");
        subject.setType(Field.class);

        EObject expected = ((ConcreteClassifier) ((ClassMethod) compilationUnit.getClassifiers().get(0).getMethods()
                .get(0)).getStatements().get(0)).getFields().get(0);
        EObject actual = subject.getWrappedElement();

        assertEquals(expected, actual);
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
