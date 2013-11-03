package org.splevo.diffing;

/**
 * Exception identifying that the provided input models are not supported by the differ.
 */
public class DiffingNotSupportedException extends Exception {

	/** Id identifying the serialization version of the class. */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public DiffingNotSupportedException() {
		super();
	}

	/**
	 * Constructor providing a message.
	 * 
	 * @param message
	 *            The informative message about the exception.
	 */
	public DiffingNotSupportedException(String message) {
		super(message);
	}

	/**
	 * Constructor wrapping another throwable.
	 * 
	 * @param throwable
	 *            A detailed exception to wrap.
	 */
	public DiffingNotSupportedException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * Constructor providing a message and wrapping another throwable.
	 * 
	 * @param message
	 *            The informative message about the exception.
	 * @param throwable
	 *            A detailed exception to wrap.
	 */
	public DiffingNotSupportedException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
