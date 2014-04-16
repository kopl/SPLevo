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
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.expressions.AssignmentExpression;
import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.operators.OperatorsFactory;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.variables.LocalVariable;
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
 * else condition of the given java code base. Supports both, OR and XOR variability.
 */
public class IfElseRefactoring implements VariabilityRefactoring {

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
        // verify variability characteristics
        if (variationPoint.getBindingTime() == BindingTime.COMPILE_TIME
                || variationPoint.getBindingTime() == BindingTime.LOAD_TIME) {
            return false;
        }
        if (variationPoint.getExtensibility() == Extensible.YES) {
            return false;
        }
        if (!variationPoint.getVariabilityMechanism().getRefactoringID().equals(REFACTORING_ID)) {
            return false;
        }

        // verify that the variation point's location is jamopp specific
        if (!(variationPoint.getLocation() instanceof JaMoPPSoftwareElement)) {
            return false;
        }

        Commentable jamoppElement = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();

        // verify that in case of a CompilationUnit all variants are imports
        if (jamoppElement instanceof CompilationUnit) {
            for (Variant variant : variationPoint.getVariants()) {
                for (SoftwareElement softwareElement : variant.getImplementingElements()) {
                    if (((JaMoPPSoftwareElement) softwareElement).getJamoppElement() instanceof Import) {
                        continue;
                    }
                    return false;
                }
            }
        }

        // check for common variables of different types
        if (jamoppElement instanceof StatementListContainer) {
            Map<String, Set<Class<?>>> localVariableMap = new HashMap<String, Set<Class<?>>>();
            for (Variant variant : variationPoint.getVariants()) {
                for (SoftwareElement softwareElement : variant.getImplementingElements()) {
                    Commentable variantElement = ((JaMoPPSoftwareElement) softwareElement).getJamoppElement();
                    if (!(variantElement instanceof LocalVariableStatement)) {
                        continue;
                    }
                    LocalVariable variable = ((LocalVariableStatement) variantElement).getVariable();
                    if (!localVariableMap.containsKey(variable.getName())) {
                        Set<Class<?>> hashSet = new HashSet<Class<?>>();
                        hashSet.add(variable.getTypeReference().getTarget().getClass());
                        localVariableMap.put(variable.getName(), hashSet);
                    } else {
                        boolean typeWasNew = localVariableMap.get(variable.getName()).add(
                                variable.getTypeReference().getTarget().getClass());
                        if (typeWasNew) {
                            return false;
                        }
                    }
                }
            }

        }

        return true;
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

    @Override
    public void refactor(VariationPoint variationPoint) {
        Commentable jamoppElement = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();

        // merge imports
        if (jamoppElement instanceof CompilationUnit) {
            CompilationUnit compilationUnit = (CompilationUnit) jamoppElement;
            for (Variant variant : variationPoint.getVariants()) {
                for (SoftwareElement se : variant.getImplementingElements()) {
                    Commentable currentJamoppElement = ((JaMoPPSoftwareElement) se).getJamoppElement();
                    EList<Import> leadingImports = compilationUnit.getImports();
                    if (currentJamoppElement instanceof Import && !(leadingImports.contains(currentJamoppElement))) {
                        leadingImports.add((Import) currentJamoppElement);
                    }
                }
            }
            return;
        }

        StatementListContainer leadingMainContainer = (StatementListContainer) jamoppElement;

        // add import to access spl configurations
        leadingMainContainer.getContainingCompilationUnit().getImports().add(SPLConfigurationUtil.getRequiringImport());

        Condition previousCondition = null;
        Set<String> localVariableSet = new HashSet<String>();

        // process all variants
        for (Variant variant : variationPoint.getVariants()) {
            // store position of integration statements in int method
            int indexVariantPositionInMethod = getIndexOfStatementInMethod(variant);

            // create condition
            Condition currentCondition = StatementsFactory.eINSTANCE.createCondition();
            currentCondition.setCondition(SPLConfigurationUtil.generateVariantMatchingExpression(variationPoint
                    .getGroup().getGroupId(), variant.getVariantId()));
            Block currentBlock = StatementsFactory.eINSTANCE.createBlock();
            currentCondition.setStatement(currentBlock);

            // delete leading variant statements from leading method
            if (variant.getLeading()) {
                for (SoftwareElement se : variant.getImplementingElements()) {
                    Statement variantStatement = (Statement) ((JaMoPPSoftwareElement) se).getJamoppElement();
                    leadingMainContainer.getStatements().remove(variantStatement);
                }
            }

            // Fill if block. If statement is local variable, split into initialization and
            // assertion. Otherwise just add to if block.
            for (SoftwareElement se : variant.getImplementingElements()) {
                Statement variantStatement = (Statement) ((JaMoPPSoftwareElement) se).getJamoppElement();

                if (variantStatement instanceof LocalVariableStatement) {
                    LocalVariableStatement localVariableStatement = (LocalVariableStatement) variantStatement;
                    LocalVariable variable = localVariableStatement.getVariable();

                    // store names of variables
                    if (localVariableSet.add(variable.getName())) {
                        leadingMainContainer.getStatements()
                                .add(indexVariantPositionInMethod++, localVariableStatement);
                    }

                    // create expression statement and delete expression from variable
                    // initialization
                    ExpressionStatement expressionStatement = StatementsFactory.eINSTANCE.createExpressionStatement();
                    AssignmentExpression assignmentExpression = ExpressionsFactory.eINSTANCE
                            .createAssignmentExpression();
                    assignmentExpression.setAssignmentOperator(OperatorsFactory.eINSTANCE.createAssignment());
                    assignmentExpression.setValue(variable.getInitialValue());
                    expressionStatement.setExpression(assignmentExpression);
                    variable.setInitialValue(null);

                    // add statement to the if block
                    currentBlock.getStatements().add(expressionStatement);
                } else {
                    currentBlock.getStatements().add(variantStatement);
                }
            }

            // If variabilitytype or: add condition to if block
            // If variabilitytype xor: add condition to else statement
            VariabilityType variabilityType = variationPoint.getVariabilityType();
            if (previousCondition == null || variabilityType == VariabilityType.OR
                    || variabilityType == VariabilityType.OPTOR) {
                leadingMainContainer.getStatements().add(indexVariantPositionInMethod, currentCondition);
            } else if (variabilityType == VariabilityType.XOR || variabilityType == VariabilityType.OPTXOR) {
                previousCondition.setElseStatement(currentCondition);
            }

            // update previous condition
            previousCondition = currentCondition;
        }
    }

    /**
     * Calculates the index of the first implementing element of the given variant within the
     * containing method.
     * 
     * @param variant
     *            The {@link Variant}.
     * @return The int index.
     */
    private int getIndexOfStatementInMethod(Variant variant) {
        JaMoPPSoftwareElement softwareElement = (JaMoPPSoftwareElement) variant.getImplementingElements().get(0);
        Commentable jamoppElement = softwareElement.getJamoppElement();
        EObject container = jamoppElement;
        while (container != null) {
            if (container.eContainer() instanceof StatementListContainer) {
                return ((StatementListContainer) container.eContainer()).getStatements().indexOf(container);
            }
            container = ((Commentable) container).eContainer();
        }

        return -1;
    }
}
