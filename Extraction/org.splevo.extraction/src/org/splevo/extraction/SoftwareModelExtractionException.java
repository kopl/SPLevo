package org.splevo.extraction;

/**
 * Exception identifying an error during the extraction process.
 */
public class SoftwareModelExtractionException extends Exception {

    /** Id identifying the serialization version of the class. */
    private static final long serialVersionUID = 1L;

    /**
     * {inheritDoc}.
     */
    public SoftwareModelExtractionException() {
        super();
    }

    /**
     * {inheritDoc}.
     * @param message The informative message about the exception.
     */
    public SoftwareModelExtractionException(String message) {
        super(message);
    }

    /**
     * {inheritDoc}.
     * @param throwable A detailed exception to wrap.
     */
    public SoftwareModelExtractionException(Throwable throwable) {
        super(throwable);
    }

    /**
     * {inheritDoc}.
     * @param message The informative message about the exception.
     * @param throwable A detailed exception to wrap.
     */
    public SoftwareModelExtractionException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
