package org.splevo.jamopp.refactoring.java.ifelse.util;

import org.emftext.language.java.statements.Condition;

public interface IfElseRefactoringUtil {
	/**
     * Method-signature for the IfElseRefactorings 
     * 
     * @param variantId
     *            The variant id as {@link String}.
     * @param groupName
     *            The group name as {@link String}.
     * @return The generated {@link Condition}.
     */
    public Condition createVariabilityCondition(String variantId, String groupName);
}
