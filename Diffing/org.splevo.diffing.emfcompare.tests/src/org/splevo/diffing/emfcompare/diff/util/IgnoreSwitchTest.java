/**
 * 
 */
package org.splevo.diffing.emfcompare.diff.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.splevo.diffing.emfcompare.util.IgnoreSwitch;

/**
 * Tests case for the package ignore visitor.
 * 
 * @author Benjamin Klatt
 *
 */
public class IgnoreSwitchTest {

    /**
     * Test method for {@link org.splevo.diffing.emfcompare.util.PackageIgnoreChecker#checkIgnorePackage(java.lang.String)}.
     */
    @Test
    public void testCheckIgnorePackage() {
        List<String> ignorePackages = new ArrayList<String>();
        ignorePackages.add("java.*");
        IgnoreSwitch ignoreSwitch = new IgnoreSwitch(ignorePackages);
        
        assertTrue("Failed to detect ignore pattern.", ignoreSwitch.checkIgnorePackage("java"));
        assertTrue("Failed to detect ignore pattern.", ignoreSwitch.checkIgnorePackage("java.lang"));
        assertTrue("Failed to detect ignore pattern.", ignoreSwitch.checkIgnorePackage("javax"));
        assertFalse("False negative.", ignoreSwitch.checkIgnorePackage("org"));
        
    }

}
