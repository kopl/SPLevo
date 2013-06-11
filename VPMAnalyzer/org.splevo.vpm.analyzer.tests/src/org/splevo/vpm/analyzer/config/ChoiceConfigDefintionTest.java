/**
 * 
 */
package org.splevo.vpm.analyzer.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * The Class ChoiceConfigDefintionTest.
 * 
 * @author Benjamin Klatt
 */
public class ChoiceConfigDefintionTest {

    /** The Constant VALUE_2. */
    private static final Integer VALUE_2 = 2;

    /** The Constant VALUE_1. */
    private static final Integer VALUE_1 = 1;

    /** The Constant for an invalid value. */
    private static final Integer VALUE_INVALID = 3;

    /** The Constant LABEL2. */
    private static final String LABEL2 = "LABEL2";

    /** The Constant LABEL1. */
    private static final String LABEL1 = "LABEL1";

    /**
     * Test method for a choice config definition.
     */
    @Test
    public void testChoiceConfigDefintion() {
        ChoiceConfigDefintion<Integer> configDef = new ChoiceConfigDefintion<Integer>();
        configDef.addAvailableValue(VALUE_1, LABEL1);
        configDef.addAvailableValue(VALUE_2, LABEL2);

        assertEquals("Failed to convert index to value.", VALUE_1, configDef.convertValue("0"));
        assertEquals("Failed to get a value for a specific index.", VALUE_2, configDef.getValueForIndex(1));
        assertEquals("Failed to get the index of a value", Integer.valueOf(1), configDef.getIndexOfValue(VALUE_2));
        assertTrue("Null allowed check failed.", configDef.isNullAllowed());
        assertTrue("Check of a valid value failed", configDef.isValidValue(VALUE_1));
        assertFalse("Check of an invalid value failed", configDef.isValidValue(VALUE_INVALID));
    }
}
