package org.splevo.utilities.metrics.calculator;

/**
 * Identifies a failed exception calculation.
 * @author Benjamin Klatt
 *
 */
public class MetricCalculationException extends Exception {

    /** The id to serialize objects of this type. */
    private static final long serialVersionUID = 2543168806393874344L;

    /**
     * Default Constructor.
     */
    public MetricCalculationException() {
    }

    /**
     * Constructor to set message and throwable.
     * 
     * @param message
     *            the message to register.
     * @param e
     *            the exception to encapsulate.
     */
    public MetricCalculationException(String message, Throwable e) {
        super(message, e);
    }
    
    /**
     * Instantiates a new metric calculation exception.
     * 
     * @param message
     *            the message to register.
     */
    public MetricCalculationException(String message) {
        super(message);
    }
    
    /**
     * Instantiates a new metric calculation exception.
     * 
     * @param e
     *            the exception to encapsulate.
     */
    public MetricCalculationException(Throwable e) {
        super(e);
    }

    
}
