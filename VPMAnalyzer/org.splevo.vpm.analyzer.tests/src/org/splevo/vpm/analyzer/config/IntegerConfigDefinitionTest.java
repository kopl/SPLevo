/**
 * 
 */
package org.splevo.vpm.analyzer.config;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testcase for the integer config definition.
 * 
 * @author Benjamin Klatt
 *
 */
public class IntegerConfigDefinitionTest extends TestCase {

    /** The Constant DEFAULT_VALUE. */
    private static final Integer DEFAULT_VALUE = 12;
    
    /** The Constant DEFAULT_VALUE_STRING. */
    private static final String DEFAULT_VALUE_STRING = "12";

    /**
     * Test the boolean choice configuration.
     */
    @Test
    public void test() {
        IntegerConfigDefinition configDef = new IntegerConfigDefinition(DEFAULT_VALUE);
        
        assertEquals("Wrong default value", DEFAULT_VALUE,  configDef.getDefaultValue());
        assertEquals("failed to get value at index 1", DEFAULT_VALUE, configDef.convertValue(DEFAULT_VALUE_STRING));
        assertTrue("Valid value check failed", configDef.isValidValue(33));
        assertFalse("Invalid value check failed", configDef.isValidValue("Hello World"));
        assertTrue("Invalid value check failed", configDef.isValidValue(null));
        
        // check fresh default constructor
        configDef = new IntegerConfigDefinition();
        assertEquals("Wrong default value", null,  configDef.getDefaultValue());
    }

}
