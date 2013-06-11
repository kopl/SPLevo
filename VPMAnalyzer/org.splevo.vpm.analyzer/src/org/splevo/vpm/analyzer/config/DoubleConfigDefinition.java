package org.splevo.vpm.analyzer.config;

/**
 * A double configuration parameter.
 * 
 * @author Benjamin Klatt
 * 
 */
public class DoubleConfigDefinition extends AbstractConfigDefinition<Double> {

    /** Default constructor. */
    public DoubleConfigDefinition() {
    }

    /**
     * Constructor to set the default value.
     * 
     * @param defaultValue
     *            The default value of the configuration.
     */
    public DoubleConfigDefinition(Double defaultValue) {
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
        return (value instanceof Double);
    }

    @Override
    public Double convertValue(String stringValue) {
        return Double.valueOf(stringValue);
    }
}
