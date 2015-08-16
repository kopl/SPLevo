package org.splevo.jamopp.refactoring.java.ifelse.util;

import org.emftext.language.java.statements.Condition;

public class SemiAutomatedIfElseRefactoring implements IfElseRefactoringUtil {

	/**
     * Generates a condition for license-if-block. Matches the SPL configuration attribute with
     * the given name (from the group ID) with the given variant id within the condition.
     * 
     * @param variantId
     *            The variant id as {@link String}.
     * @param groupName
     *            The group name as {@link String}.
     * @return The generated {@link Condition}.
     */
	@Override
	public Condition createVariabilityCondition(String variantId,
			String groupName) {
		// TODO Auto-generated method stub
		return null;
	}

}
