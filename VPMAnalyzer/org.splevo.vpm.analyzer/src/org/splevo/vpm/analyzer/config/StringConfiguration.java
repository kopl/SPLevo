/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic - initial API and implementation and/or initial documentation
 *    Benjamin Klatt - enhancements and maintenance
 *******************************************************************************/
package org.splevo.vpm.analyzer.config;

/**
 * This configuration is build for textual inputs.
 */
public class StringConfiguration extends AbstractVPMAnalyzerConfiguration<String> {

	private boolean isSingleLine;

	/**
	 * This is the main constructor of this class.
	 *
     * @param id
     *            The unique identifier of the configuration option.
	 * @param label
	 *            The configurations' text label.
	 * @param explanation
	 *            A detailed description of the configuration.
	 * @param defaultValue
	 *            The default value for the configuration.
	 * @param isSingleLine Indicates whether the UI textbox's height should be adopted
	 * 			  to a single line or multiple lines. Use false for a bigger box.
	 */
	public StringConfiguration(String id, String label, String explanation,
			String defaultValue, boolean isSingleLine) {
		super(id, label, explanation, defaultValue);
		this.isSingleLine = isSingleLine;
	}

	/**
	 * @return the isSingleLine
	 */
	public boolean isSingleLine() {
		return isSingleLine;
	}

	/**
	 * @param isSingleLine the isSingleLine to set
	 */
	public void setSingleLine(boolean isSingleLine) {
		this.isSingleLine = isSingleLine;
	}
}
