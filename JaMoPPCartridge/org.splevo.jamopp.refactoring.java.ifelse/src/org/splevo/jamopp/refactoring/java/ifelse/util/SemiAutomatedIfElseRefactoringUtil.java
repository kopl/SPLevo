package org.splevo.jamopp.refactoring.java.ifelse.util;

import java.util.Map;

import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.StatementsFactory;

public class SemiAutomatedIfElseRefactoringUtil implements IfElseRefactoringUtil {

	private String validatorName = "";
	private Map<String, String> variantToLicenseMap = null;
	
	public SemiAutomatedIfElseRefactoringUtil(String validatorName, Map<String, String> variantToLicenseMap) {
		this.validatorName = validatorName;
		this.variantToLicenseMap = variantToLicenseMap;
	}
	
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
	public Condition createVariabilityCondition(String variantID, String groupName) {
		Condition condition = StatementsFactory.eINSTANCE.createCondition();
		
        condition.setCondition(SPLConfigurationUtil.generateLicenseValidationExpression(variantToLicenseMap.get(variantID), this.validatorName));
		
        Block ifBlock = StatementsFactory.eINSTANCE.createBlock();
        condition.setStatement(ifBlock);

        return condition;
	}

}
