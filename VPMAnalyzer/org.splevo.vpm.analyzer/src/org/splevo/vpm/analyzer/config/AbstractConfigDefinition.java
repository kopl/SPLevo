package org.splevo.vpm.analyzer.config;

/**
 * An abstract typed configuration definition.
 * 
 * @param <T>
 *            The data type of the configuration parameter.
 * 
 * @author Benjamin Klatt
 * 
 */
public abstract class AbstractConfigDefinition<T> implements ConfigDefinition {

    /** The specified default value of the configuration. */
    private T defaultValue = null;

    /** Flag if a null value is allowed for the configuration. */
    private boolean nullAllowed = true;

    @Override
    public T getDefaultValue() {
        return defaultValue;
    }

    /**
     * @param defaultValue
     *            the defaultValue to set
     */
    public void setDefaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public boolean isNullAllowed() {
        return nullAllowed;
    }

    /**
     * Specify if null values are allowed.
     * 
     * @param nullAllowed
     *            True if null values are allowed.
     */
    public void setNotNull(boolean nullAllowed) {
        this.nullAllowed = nullAllowed;
    }
    
    @Override
    public abstract T convertValue(String stringValue);
}
