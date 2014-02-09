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
 * This configuration is build for decisions with two states:
 * <ul>
 * 	<li>true/false</li>
 * 	<li>yes/no</li>
 * 	<li>do/don't</li>
 * </ul>
 * 
 * @author Daniel Kojic
 *
 */
public class BooleanConfiguration extends AbstractVPMAnalyzerConfiguration<Boolean> {

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
	public BooleanConfiguration(String label, String explanation,
			Boolean defaultValue) {
		super(label, explanation, defaultValue);
	}
}
