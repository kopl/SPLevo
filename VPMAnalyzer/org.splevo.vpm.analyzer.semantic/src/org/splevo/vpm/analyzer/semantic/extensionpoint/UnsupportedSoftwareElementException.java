package org.splevo.vpm.analyzer.semantic.extensionpoint;

import org.splevo.vpm.software.SoftwareElement;

/**
 * The exception class in case of incompatible SoftwareElements.
 * 
 * @author Daniel Kojic
 * 
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
