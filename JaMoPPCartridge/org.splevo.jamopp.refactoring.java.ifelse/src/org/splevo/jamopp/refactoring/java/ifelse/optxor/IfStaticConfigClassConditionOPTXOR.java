/*******************************************************************************
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic - initial API and implementation and initial documentation
 *******************************************************************************/
package org.splevo.jamopp.refactoring.java.ifelse.optxor;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.expressions.ConditionalOrExpression;
import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementsFactory;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.refactoring.util.SPLConfigurationUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;

/**
 * Refactors variable else-statements.
 */
public class IfStaticConfigClassConditionOPTXOR implements VariabilityRefactoring {

    private static final String REFACTORING_NAME = "IF with Static Configuration Class (OPTXOR): Condition";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.optxor.IfStaticConfigClassConditionOPTXOR";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public List<Resource> refactor(VariationPoint variationPoint, Map<String, String> refactoringOptions) {
        RefactoringUtil.resolveVPsWithSameLocation(variationPoint);
        
        Condition vpLocation = (Condition) ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        Statement elseStatement = vpLocation.getElseStatement();

        CompilationUnit compilationUnit = vpLocation.getContainingCompilationUnit();
        SPLConfigurationUtil.addConfigurationClassImportIfMissing(compilationUnit);

        Condition variabilityCondition = null;
        
        if (variationPoint.getVariants().size() > 1) {
            variabilityCondition = createConditionForMultipleVariants(variationPoint);
        } else {
            variabilityCondition = createConditionForSingleVariant(variationPoint.getVariants().get(0));
        }

        vpLocation.setElseStatement(variabilityCondition);
        variabilityCondition.setElseStatement(elseStatement);

        return Lists.newArrayList(vpLocation.eResource());
    }

    private Condition createConditionForMultipleVariants(VariationPoint variationPoint) {
        String groupId = variationPoint.getGroup().getId();

        Condition condition = StatementsFactory.eINSTANCE.createCondition();
        Block block = StatementsFactory.eINSTANCE.createBlock();
        condition.setStatement(block);
        
        ConditionalOrExpression orExpression = createConfigMatchingExpressionForAllVariants(variationPoint, groupId);
        condition.setCondition(orExpression);
        
        for (Variant variant : variationPoint.getVariants()) {
            Condition variabilityCondition = createConditionForSingleVariant(variant);
            block.getStatements().add(variabilityCondition);
        }

        return condition;
    }

    private ConditionalOrExpression createConfigMatchingExpressionForAllVariants(VariationPoint variationPoint,
            String groupId) {
        ConditionalOrExpression orExpression = ExpressionsFactory.eINSTANCE.createConditionalOrExpression();
        
        for (Variant variant : variationPoint.getVariants()) {
            IdentifierReference cond = SPLConfigurationUtil.generateConfigMatchingExpression(variant.getId(), groupId);
            orExpression.getChildren().add(cond);
        }
        return orExpression;
    }

    private Condition createConditionForSingleVariant(Variant variant) {
        String groupId = variant.getVariationPoint().getGroup().getId();
        String variantId = variant.getId();
        
        Condition variabilityCondition = RefactoringUtil.createVariabilityCondition(variantId, groupId);
        Statement stmtCpy = EcoreUtil.copy((Statement) ((JaMoPPSoftwareElement) variant.getImplementingElements().get(0))
                .getJamoppElement());

        if (stmtCpy instanceof Block) {
            variabilityCondition.setStatement(stmtCpy);
        } else {
            ((Block) variabilityCondition.getStatement()).getStatements().add(stmtCpy);
        }

        return variabilityCondition;
    }

    @Override
    public boolean canBeAppliedTo(VariationPoint variationPoint) {
        Commentable vpLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        boolean correctLocation = vpLocation instanceof Condition;
        boolean allImplementingElementsAreStatements = RefactoringUtil.allImplementingElementsOfType(variationPoint,
                Statement.class);

        return correctLocation && allImplementingElementsAreStatements;
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

}
