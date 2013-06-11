package org.splevo.vpm.analyzer.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test case to test the boolean choice configuration definition.
 * @author Benjamin Klatt
 *
 */
public class BooleanChoiceConfigDefintionTest {

    /**
     * Test the boolean choice configuration.
     */
    @Test
    public void test() {
        BooleanChoiceConfigDefintion configDef = new BooleanChoiceConfigDefintion(true);
        
        assertTrue("wrong default value", configDef.getDefaultValue());
        assertEquals("Wrong number of available values", 2,  configDef.getAvailableValues().size());
        assertEquals("failed to get value at index 1", Boolean.TRUE, configDef.getValueForIndex(1));
    }

}
