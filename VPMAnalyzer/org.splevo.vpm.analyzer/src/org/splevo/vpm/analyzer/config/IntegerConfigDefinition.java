package org.splevo.vpm.analyzer.config;

/**
 * A integer configuration parameter.
 * 
 * @author Benjamin Klatt
 *
 */
public class IntegerConfigDefinition extends AbstractConfigDefinition<Integer> {

    /**
     * Default constructor.
     */
    public IntegerConfigDefinition() {
    }
    
    /**
     * Constructor to set the default value.
     * @param defaultValue The default value of the configuration.
     */
    public IntegerConfigDefinition(Integer defaultValue) {
        setDefaultValue(defaultValue);
    }
    
    /**
     * Check a specific value if it is valid.
     * 
     * @param value
     *            The value to check.
     * @return true/false depending on the value's validity
     */
    @Override
    public boolean isValidValue(Object value) {
        if (value == null) {
            return isNullAllowed();
        }
        return (value instanceof Integer);
    }
    
    @Override
    public Integer convertValue(String stringValue) {
        return Integer.valueOf(stringValue);
    }
}
