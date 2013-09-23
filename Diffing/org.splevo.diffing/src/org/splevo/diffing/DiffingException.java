package org.splevo.diffing;

/**
 * Exception identifying a problem during the diffing process.
 */
public class DiffingException extends Exception {

	/** Id identifying the serialization version of the class. */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public DiffingException() {
		super();
	}

	/**
	 * Constructor providing a message.
	 * 
	 * @param message
	 *            The informative message about the exception.
	 */
	public DiffingException(String message) {
		super(message);
	}

	/**
	 * Constructor wrapping another throwable.
	 * 
	 * @param throwable
	 *            A detailed exception to wrap.
	 */
	public DiffingException(Throwable throwable) {
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
	public DiffingException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
