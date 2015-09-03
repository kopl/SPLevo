package org.splevo.refactoring;

public class VariabilityRefactoringFailedException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -8579012141818557131L;

    public VariabilityRefactoringFailedException(String message) {
        super(message);
    }
    
    public VariabilityRefactoringFailedException(String message, Exception e) {
        super(message, e);
    }
    
}
