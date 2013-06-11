package org.splevo.vpm.analyzer.config;

import java.util.HashMap;
import java.util.Map;

/**
 * A configuration definition providing a list of predefined values available for the configuration.
 * 
 * This configuration contains a map for pairs of value and label.
 * 
 * In addition it provides methods to get a string array of the labels and to work with the indexes
 * of the labels and values in the map. While a map might not be ordered, some use cases of the
 * configuration definition require this predefined order.
 * 
 * @param <T>
 *            The data type of the configuration parameter.
 * 
 * @author Benjamin Klatt
 * 
 */
public class ChoiceConfigDefintion<T> extends AbstractConfigDefinition<T> {

    /**
     * The map of values predefined for the configuration parameter. The key is the available
     * configuration value, the value represent
     */
    private Map<T, String> availableValues = new HashMap<T, String>();

    /**
     * Get the available values to choose from.
     * 
     * @return the availableValues
     */
    public Map<T, String> getAvailableValues() {
        return availableValues;
    }

    /**
     * Set the available values to choose from.
     * 
     * @param availableValues
     *            the availableValues to set
     */
    public void setAvailableValues(Map<T, String> availableValues) {
        this.availableValues = availableValues;
    }

    /**
     * add a single value to the list of available ones.
     * 
     * @param value
     *            The value to add. If it already exists, the old one will be replaced.
     * @param label
     *            The label to represent this value.
     */
    public void addAvailableValue(T value, String label) {
        this.availableValues.put(value, label);
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
        return this.availableValues.containsKey(value);
    }
    
    /**
     * The string representation of the value is assumed to represent
     * the index of the value in the list of values. 
     * This is the typical case when a drop down box is presented in the user interface.
     * 
     * @param stringValue The value representing the index of the value.
     * @return the value for the index.
     */
    @Override
    public T convertValue(String stringValue) {
        return getValueForIndex(Integer.valueOf(stringValue));
    }

    /**
     * Get the value for a specific index.
     * 
     * This method should only be invoked with indexes present in the list of available values.
     * Otherwise, an illegal argument exception will be thrown.
     * 
     * @param index
     *            The label to lookup for.
     * @return The value at the specified index.
     */
    @SuppressWarnings("unchecked")
    public T getValueForIndex(Integer index) {
        try {
            return (T) availableValues.keySet().toArray()[index];
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Unspecified label. Only labels of the available values are allowed! [Invalid label: " + index
                            + "]", e);
        }
    }

    /**
     * Get the index of a specific value.
     * 
     * @param value
     *            The value to get the index for.
     * @return The index of the value.
     */
    public Integer getIndexOfValue(Object value) {

        int index = 0;
        for (T currentValue : availableValues.keySet()) {
            if (currentValue.equals(value)) {
                return Integer.valueOf(index);
            }
            index++;
        }

        throw new IllegalArgumentException("Invalid value not available for this configuration! [Invalid value: "
                + value + "]");

    }
}
