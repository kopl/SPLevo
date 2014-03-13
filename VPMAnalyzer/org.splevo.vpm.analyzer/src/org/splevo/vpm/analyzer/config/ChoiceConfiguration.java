/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
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
     * @param id
     *            The unique identifier of the configuration option.
	 * @param label
	 *            The configurations' text label.
	 * @param explanation
	 *            A detailed description of the configuration.
	 * @param defaultValue
	 *            The default value for the configuration. Must be part of the
	 *            available configurations parameter.
	 * @param availableValues
	 *            The available values this configurations offers defined as attribute expansion
	 */
	public ChoiceConfiguration(String id, String label, String explanation,
			String defaultValue, String... availableValues) {
		super(id, label, explanation, defaultValue);
		this.availableValues = new LinkedList<String>();
		setAvailableValues(availableValues);
	}

    /**
     * The main constructor for this class.
     *
     * @param id
     *            The unique identifier of the configuration option.
     * @param label
     *            The configurations' text label.
     * @param explanation
     *            A detailed description of the configuration.
     * @param defaultValue
     *            The default value for the configuration. Must be part of the
     *            available configurations parameter.
     * @param availableValues
     *            The available values this configurations offers defined as list
     */
    public ChoiceConfiguration(String id, String label, String explanation,
            String defaultValue, List<String> availableValues) {
        super(id, label, explanation, defaultValue);
        this.availableValues = availableValues;
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
