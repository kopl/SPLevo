package org.splevo.modisco.java.diffing.util;

import static org.junit.Assert.assertEquals;

import org.eclipse.gmt.modisco.java.TextElement;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.junit.Test;

/**
 * Test Case for the java model element printer.
 * 
 * @author Benjamin Klatt
 * 
 */
public class JavaModelElementPrinterTest {

    /**
     * Test method for
     * {@link org.splevo.modisco.java.diffing.util.JavaModelElementPrinter#printElement(org.eclipse.emf.ecore.EObject)}
     * .
     */
    @Test
    public void testPrintTextElement() {

        JavaModelElementPrinter printer = new JavaModelElementPrinter();

        TextElement textElement = JavaFactory.eINSTANCE.createTextElement();
        
        textElement.setText("ShortString");
        assertEquals("Failed to return short string", "ShortString", printer.printElement(textElement));

        textElement.setText("20CharacterString123");
        assertEquals("Failed to return short string", "20CharacterString123", printer.printElement(textElement));

        textElement.setText("21CharacterString1234");
        assertEquals("Failed to return short string", "21CharacterString123", printer.printElement(textElement));

    }
}
