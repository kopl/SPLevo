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
 *******************************************************************************/
package org.splevo.jamopp.refactoring.java.ifelse;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.NestedExpression;
import org.emftext.language.java.expressions.UnaryExpression;
import org.emftext.language.java.operators.OperatorsFactory;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.variables.LocalVariable;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.refactoring.util.SPLConfigurationUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Refactoring a variation point into a single code base aligned with the leading variant using if
 * else condition of the given java code base. Supports OR and OPTOR variability.
 */
public class IfElseRefactoringAllOR implements VariabilityRefactoring {

    private static final String REFACTORING_ID = "org.splevo.refactoring.java.ifelse";
    private static final String REFACTORING_NAME = "If-Else Refactoring (Run-time Version)";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public boolean canBeAppliedTo(VariationPoint variationPoint) {
        if (variationPoint.getBindingTime() != BindingTime.RUN_TIME) {
            return false;
        }

        if (variationPoint.getExtensibility() != Extensible.NO) {
            return false;
        }

        if (!variationPoint.getVariabilityMechanism().getRefactoringID().equals(REFACTORING_ID)) {
            return false;
        }

        if (variationPoint.getVariabilityType() != VariabilityType.OR
                && variationPoint.getVariabilityType() != VariabilityType.OPTOR) {
            return false;
        }

        if (!(variationPoint.getLocation() instanceof JaMoPPSoftwareElement)) {
            return false;
        }

        Commentable jamoppElement = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        if (jamoppElement instanceof CompilationUnit) {
            return RefactoringUtil.allImplementingElementsAreImports(variationPoint);
        }

        return jamoppElement instanceof StatementListContainer;
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

    @Override
    public void refactor(VariationPoint variationPoint) {
        Commentable jamoppElement = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();

        if (jamoppElement instanceof CompilationUnit) {
            RefactoringUtil.mergeImports(variationPoint);
            return;
        }

        RefactoringUtil.deleteVariableElements(variationPoint);

        if (RefactoringUtil.containsVarsSameNameDiffType(variationPoint)) {
            RefactoringUtil.extractVariableStatementsIntoMethods(variationPoint);
        }

        StatementListContainer vpLocation = (StatementListContainer) jamoppElement;

        vpLocation.getContainingCompilationUnit().getImports().add(SPLConfigurationUtil.getSPLConfigClassImport());

        int indexBeginVariant = RefactoringUtil.getVariabilityPosition(variationPoint);
        int indexEndVariant = indexBeginVariant;

        Map<String, LocalVariableStatement> localVariableStatements = new HashMap<String, LocalVariableStatement>();

        EList<Variant> variants = variationPoint.getVariants();
        for (Variant variant : variants) {
            // create condition
            Condition currentCondition = StatementsFactory.eINSTANCE.createCondition();
            currentCondition.setCondition(SPLConfigurationUtil.generateSingleVariantMatchingExpression(variationPoint
                    .getGroup().getGroupId(), variant.getVariantId()));
            Block currentBlock = StatementsFactory.eINSTANCE.createBlock();
            currentCondition.setStatement(currentBlock);

            // Fill if block
            for (SoftwareElement se : variant.getImplementingElements()) {
                Statement variantStatement = (Statement) ((JaMoPPSoftwareElement) se).getJamoppElement();
                if (variantStatement instanceof LocalVariableStatement) {
                    LocalVariableStatement localVariableStatement = (LocalVariableStatement) variantStatement;
                    LocalVariable variable = localVariableStatement.getVariable();
                    if (!localVariableStatements.containsKey(variable.getName())) {
                        localVariableStatements.put(variable.getName(), localVariableStatement);
                    }
                    ExpressionStatement extractedAssignment = RefactoringUtil.extractAssignment(variable);
                    variable.setInitialValue(null);
                    variantStatement = extractedAssignment;
                }
                currentBlock.getStatements().add(variantStatement);
            }

            // add condition to base
            vpLocation.getStatements().add(indexEndVariant++, currentCondition);
            if (variationPoint.getVariabilityType() == VariabilityType.OR
                    && variants.indexOf(variant) == (variants.size() - 1)) {
                vpLocation.getStatements().add(indexEndVariant++, getORVerificationCondition(variationPoint));
            }
        }

        // add local variables in front of the condition(s)
        for (String name : localVariableStatements.keySet()) {
            LocalVariableStatement localVariableStatement = localVariableStatements.get(name);
            vpLocation.getStatements().add(indexBeginVariant++, localVariableStatement);
        }
    }

    /**
     * Creates a condition that verifies whether at least one variant of a variation point is
     * defined in the configuration.
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     * @return The {@link Condition}.
     */
    private Condition getORVerificationCondition(VariationPoint variationPoint) {
        Condition condition = StatementsFactory.eINSTANCE.createCondition();
        condition.setStatement(RefactoringUtil.getBlockThrowingARuntimeException());

        Set<String> variantNames = new HashSet<String>();
        for (Variant variant : variationPoint.getVariants()) {
            variantNames.add(variant.getVariantId());
        }
        Expression variantsMatchingExpression = SPLConfigurationUtil.generateVariantMatchingExpression(variationPoint
                .getGroup().getGroupId(), variantNames);

        UnaryExpression unaryExpression = ExpressionsFactory.eINSTANCE.createUnaryExpression();
        unaryExpression.getOperators().add(OperatorsFactory.eINSTANCE.createNegate());
        NestedExpression nestedExpression = ExpressionsFactory.eINSTANCE.createNestedExpression();
        nestedExpression.setExpression(variantsMatchingExpression);
        unaryExpression.setChild(nestedExpression);

        condition.setCondition(unaryExpression);

        return condition;
    }
}
