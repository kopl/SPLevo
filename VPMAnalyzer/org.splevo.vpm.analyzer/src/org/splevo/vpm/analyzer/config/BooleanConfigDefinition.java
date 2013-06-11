package org.splevo.vpm.analyzer.config;

/**
 * A boolean configuration parameter.
 * 
 * @author Benjamin Klatt
 *
 */
public class BooleanConfigDefinition extends AbstractConfigDefinition<Boolean> {

    /**
     * Default constructor.
     */
    public BooleanConfigDefinition() {
    }
    
    /**
     * Constructor to set the default value.
     * @param defaultValue The default value.
     */
    public BooleanConfigDefinition(Boolean defaultValue) {
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
        return (value instanceof Boolean);
    }
    
    @Override
    public Boolean convertValue(String stringValue) {
        return Boolean.valueOf(stringValue);
    }
}
