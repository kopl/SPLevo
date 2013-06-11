package org.splevo.vpm.analyzer.config;

/**
 * A definition of a configuration parameter.
 * 
 * @author Benjamin Klatt
 * 
 */
public interface ConfigDefinition {

    /**
     * @return the defaultValue
     */
    Object getDefaultValue();

    /**
     * Check if null values are allowed.
     * 
     * @return true if null values are allowed.
     */
    boolean isNullAllowed();

    /**
     * Check a specific value if it is valid.
     * 
     * Specific configuration types must implement a more specific validation behavior.
     * 
     * @param value
     *            The value to check.
     * @return true/false depending on the value's validity
     */
    boolean isValidValue(Object value);

    /**
     * Convert a string representation of the value to a specific type.
     * 
     * @param stringValue The string representation of the value.
     * @return The type-safe representation of the value.
     */
    Object convertValue(String stringValue);

}