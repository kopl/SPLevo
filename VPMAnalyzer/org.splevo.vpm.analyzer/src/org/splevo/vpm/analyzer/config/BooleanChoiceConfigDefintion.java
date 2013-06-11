package org.splevo.vpm.analyzer.config;

/**
 * A choice configuration definition for boolean values. True / False are the predefined options.
 * 
 * @author Benjamin Klatt
 * 
 */
public class BooleanChoiceConfigDefintion extends ChoiceConfigDefintion<Boolean> {

    /**
     * Default constructor to initialize the boolean configuration.
     */
    public BooleanChoiceConfigDefintion() {
        addAvailableValue(Boolean.FALSE, "false");
        addAvailableValue(Boolean.TRUE, "true");
        setDefaultValue(Boolean.TRUE);
    }

    /**
     * Constructor allowing to set a default value.
     * 
     * @param defaultValue
     *            The default value for the choice.
     */
    public BooleanChoiceConfigDefintion(Boolean defaultValue) {
        this();
        setDefaultValue(defaultValue);
    }

}
