package org.splevo.refactoring;

/**
 * Exception for failed variability refactorings.
 */
public class VariabilityRefactoringFailedException extends Exception {

    private static final long serialVersionUID = -8579012141818557131L;

    /**
     * Constructs the exception.
     * 
     * @param message
     *            A message describing the cause of the problem.
     */
    public VariabilityRefactoringFailedException(String message) {
        super(message);
    }

    /**
     * Constructs the exception.
     * 
     * @param message
     *            A message describing the cause of the problem.
     * @param e
     *            The exception that caused the problem.
     */
    public VariabilityRefactoringFailedException(String message, Exception e) {
        super(message, e);
    }

}
