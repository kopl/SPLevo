/**
 * 
 */
package org.splevo.modisco.java.diffing.similarity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.InterfaceDeclaration;
import org.junit.Test;
import org.splevo.modisco.util.SimilarityChecker;

/**
 * @author benjamin
 *
 */
public class SimilarityCheckerTest {

    /**
     * Test method for {@link org.splevo.modisco.util.SimilarityChecker#isSimilar(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)}.
     */
    @Test
    public void testIsSimilar() {
        
        SimilarityChecker checker = new SimilarityChecker();
        
        ClassDeclaration classDecl = org.eclipse.gmt.modisco.java.emf.JavaFactory.eINSTANCE.createClassDeclaration();
        classDecl.setName("NonNullClass");
        
        InterfaceDeclaration interfaceDecl = org.eclipse.gmt.modisco.java.emf.JavaFactory.eINSTANCE.createInterfaceDeclaration();
        interfaceDecl.setName("NonNullInterface");

        assertFalse("Null vs. non-null not detected", checker.isSimilar(null, classDecl) );
        assertFalse("Non-null vs. null not detected", checker.isSimilar(classDecl, null) );
        assertTrue("Null vs. null not detected", checker.isSimilar(null, null) );

        assertFalse("Different meta classes not detected", checker.isSimilar(interfaceDecl, classDecl) );

    }

}
