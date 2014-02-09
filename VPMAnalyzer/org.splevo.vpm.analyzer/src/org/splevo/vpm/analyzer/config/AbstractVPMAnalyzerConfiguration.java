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

/**
 * This is the abstract configuration class. All VPM analyzer configurations
 * must implement this class. It provides the basic configuration features.
 * 
 * @author Daniel Kojic
 * 
 * @param <T>
 *            The generic type that the configuration holds.
 */
public abstract class AbstractVPMAnalyzerConfiguration<T> {

	/** This member holds a detailed description of the configuration. */
	private String explanation;

	/** This is the configuration's text label. */
	private String label;

	/** This is the default value for the configuration. */
	private T defaultValue;

	/** This is the currently selected value of this configuration. */
	private T currentValue;

	/**
	 * This is the main constructor of this class.
	 * 
	 * @param label
	 *            The configurations' text label.
	 * @param explanation
	 *            A detailed description of the configuration.
	 * @param defaultValue
	 *            The default value for the configuration.
	 */
	public AbstractVPMAnalyzerConfiguration(String label, String explanation,
			T defaultValue) {
		this.label = label;
		this.explanation = explanation;
		this.defaultValue = defaultValue;

		// Set the config's current value to the default value as initial state.
		setCurrentValue(defaultValue);
	}

	/**
	 * Getter for the explanation.
	 * 
	 * @return The textual explanation.
	 */
	public String getExplanation() {
		return explanation;
	}

	/**
	 * Setter for the explanation.
	 * 
	 * @param explanation
	 *            The string-explanation.
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	/**
	 * Getter for the label.
	 * 
	 * @return The text label.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Setter for the label.
	 * 
	 * @param label
	 *            The string-label.
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Getter for the default value.
	 * 
	 * @return The default value.
	 */
	public T getDefaultValue() {
		return defaultValue;
	}

	/**
	 * Setter for the default value.
	 * 
	 * @param defaultValue
	 *            The default value.
	 */
	public void setDefaultValue(T defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * Getter for currently selected value.
	 * 
	 * @return The current value.
	 */
	public T getCurrentValue() {
		return currentValue;
	}

	/**
	 * Setter for the currently selected value.
	 * 
	 * @param currentValue
	 *            The current value.
	 */
	public void setCurrentValue(T currentValue) {
		this.currentValue = currentValue;
	}
}
