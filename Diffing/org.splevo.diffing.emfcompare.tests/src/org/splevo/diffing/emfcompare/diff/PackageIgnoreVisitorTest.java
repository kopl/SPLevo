/**
 * 
 */
package org.splevo.diffing.emfcompare.diff;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Tests case for the package ignore visitor.
 * 
 * @author Benjamin Klatt
 *
 */
public class PackageIgnoreVisitorTest {

    /**
     * Test method for {@link org.splevo.diffing.emfcompare.diff.PackageIgnoreVisitor#checkIgnorePackage(java.lang.String)}.
     */
    @Test
    public void testCheckIgnorePackage() {
        List<String> ignorePackages = new ArrayList<String>();
        ignorePackages.add("java.*");
        PackageIgnoreVisitor visitor = new PackageIgnoreVisitor(ignorePackages);
        
        assertTrue("Failed to detect ignore pattern.", visitor.checkIgnorePackage("java"));
        assertTrue("Failed to detect ignore pattern.", visitor.checkIgnorePackage("java.lang"));
        assertTrue("Failed to detect ignore pattern.", visitor.checkIgnorePackage("javax"));
        assertFalse("False negative.", visitor.checkIgnorePackage("org"));
        
    }

}
