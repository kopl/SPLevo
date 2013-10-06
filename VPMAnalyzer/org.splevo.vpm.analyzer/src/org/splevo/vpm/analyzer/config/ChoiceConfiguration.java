package org.splevo.vpm.analyzer.config;

import java.util.LinkedList;
import java.util.List;

/**
 * Use this configuration if there are various options for the configurations:
 * <ul>
 * <li>red | green | blue</li>
 * <li>a | b | c</li>
 * </ul>
 * 
 * Don't use this configurations for numerical values as the
 * {@link NumericConfiguration} class is built for that.
 * 
 * @author Daniel Kojic
 * 
 */
public class ChoiceConfiguration extends
		AbstractVPMAnalyzerConfiguration<String> {
	/**
	 * The available values this configurations offers.
	 */
	private List<String> availableValues;

	/**
	 * Gets all available values.
	 * 
	 * @return The values.
	 */
	public List<String> getAvailableValues() {
		return availableValues;
	}

	/**
	 * Sets the all available values the configuration.
	 * 
	 * @param availableValues
	 *            The values.
	 */
	public void setAvailableValues(String... availableValues) {
		for (String availableValue : availableValues) {
			this.availableValues.add(availableValue);
		}
	}

	/**
	 * The main constructor for this class.
	 * 
	 * @param label
	 *            The configurations' text label.
	 * @param explanation
	 *            A detailed description of the configuration.
	 * @param defaultValue
	 *            The default value for the configuration. Must be part of the
	 *            available configurations parameter.
	 * @param availableValues
	 *            The available values this configurations offers
	 */
	public ChoiceConfiguration(String label, String explanation,
			String defaultValue, String... availableValues) {
		super(label, explanation, defaultValue);
		this.availableValues = new LinkedList<String>();
		setAvailableValues(availableValues);
	}

	/* (non-Javadoc)
	 * @see org.splevo.vpm.analyzer.config.AbstractVPMAnalyzerConfiguration#setDefaultValue(java.lang.Object)
	 */
	@Override
	public void setDefaultValue(String defaultValue) {
		super.setDefaultValue(defaultValue);
	}

	/* (non-Javadoc)
	 * @see org.splevo.vpm.analyzer.config.AbstractVPMAnalyzerConfiguration#setCurrentValue(java.lang.Object)
	 */
	@Override
	public void setCurrentValue(String currentValue) {
		super.setCurrentValue(currentValue);
	}
}
