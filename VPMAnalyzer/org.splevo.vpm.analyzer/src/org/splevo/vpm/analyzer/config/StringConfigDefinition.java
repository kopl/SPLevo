package org.splevo.vpm.analyzer.config;

/**
 * A string configuration parameter.
 * 
 * @author Benjamin Klatt
 *
 */
public class StringConfigDefinition extends AbstractConfigDefinition<String> {

    /** Default constructor. */
    public StringConfigDefinition() {
    }
    
    /** 
     * Constructor to set the default value.
     * @param defaultValue The default value to set. 
     */
    public StringConfigDefinition(String defaultValue) {
        setDefaultValue(defaultValue);
    }
    
    /**
     * Check a specific value if it is valid.
     * 
     * Always true for this standard configuration definition. Sub config types might implement a
     * more specific validation behavior.
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
        return (value instanceof String);
    }
    
    @Override
    public String convertValue(String stringValue) {
        return stringValue;
    }
}
