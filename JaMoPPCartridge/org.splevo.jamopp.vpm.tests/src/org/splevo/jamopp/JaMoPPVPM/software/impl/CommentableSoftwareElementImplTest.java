package org.splevo.jamopp.JaMoPPVPM.software.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Method;
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
public class CommentableSoftwareElementImplTest extends JaMoPPJavaSoftwareElementTest {

    /** Source path to the original implementation. */
    private static final File TEST_FILE = new File("testcode/CommentedExampleClass.java");
    
    private CommentableSoftwareElement subject;

    /**
     * Initialize the resources to test.
     *
     * @throws Exception
     *             An error during test initialization.
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
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

    @Override
    protected File getTestFile() {
        return TEST_FILE;
    }

    @Override
    protected boolean parseLayoutInformation() {
        return true;
    }

}
