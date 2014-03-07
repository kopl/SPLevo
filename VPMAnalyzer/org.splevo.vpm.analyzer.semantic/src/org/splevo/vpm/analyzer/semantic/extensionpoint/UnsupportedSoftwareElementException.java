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
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.vpm.analyzer.semantic.extensionpoint;

import org.splevo.vpm.software.SoftwareElement;

/**
 * The exception class in case of incompatible SoftwareElements.
 */
public class UnsupportedSoftwareElementException extends Exception {

	private static final long serialVersionUID = 778651687944577521L;
	private SoftwareElement element;

	/**
	 * The default constructor for this exception. Uses a
	 * {@link SoftwareElement} to generate a detailed error message.
	 *
	 * @param element The {@link SoftwareElement}.
	 */
	public UnsupportedSoftwareElementException(SoftwareElement element) {
		this.element = element;
	}

	@Override
	public String getMessage() {
		return "Got unsupported SoftwareElement of type "
				+ element.getClass().getSimpleName() + ".";
	}
}
