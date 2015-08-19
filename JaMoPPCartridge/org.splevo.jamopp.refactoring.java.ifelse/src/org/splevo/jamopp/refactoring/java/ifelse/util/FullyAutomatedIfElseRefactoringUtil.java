/*******************************************************************************
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.jamopp.refactoring.java.ifelse.util;

import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.StatementsFactory;

/**
 * Provides utility methods for the refactorings.
 */
public class FullyAutomatedIfElseRefactoringUtil implements IfElseRefactoringUtil {

    /**
     * Generates a condition with an empty if-block. Matches the SPL configuration attribute with
     * the given name (from the group ID) with the given variant id within the condition.
     * 
     * @param variantId
     *            The variant id as {@link String}.
     * @param groupName
     *            The group name as {@link String}.
     * @return The generated {@link Condition}.
     */
    public Condition createVariabilityCondition(String variantId, String groupName) {
        Condition condition = StatementsFactory.eINSTANCE.createCondition();
        condition.setCondition(SPLConfigurationUtil.generateConfigMatchingExpression(variantId, groupName));
        
        Block ifBlock = StatementsFactory.eINSTANCE.createBlock();
        condition.setStatement(ifBlock);

        return condition;
    }
	
}
