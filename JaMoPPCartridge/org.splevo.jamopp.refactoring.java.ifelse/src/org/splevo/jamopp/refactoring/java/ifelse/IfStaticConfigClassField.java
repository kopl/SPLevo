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
package org.splevo.jamopp.refactoring.java.ifelse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.expressions.AssignmentExpression;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.modifiers.Modifier;
import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Static;
import org.emftext.language.java.operators.OperatorsFactory;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.StatementsFactory;
import org.splevo.jamopp.refactoring.java.ifelse.util.IfElseRefactoringUtil;
import org.splevo.jamopp.refactoring.java.ifelse.util.SPLConfigurationUtil;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.FullyAutomatedVariabilityRefactoring;
import org.splevo.refactoring.VariabilityRefactoringService;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;

/**
 * The code base class must contain all fields from the variants. Therefore, this refactoring merges
 * the fields from all variants into the base.
 */
public class IfStaticConfigClassField extends FullyAutomatedVariabilityRefactoring {

    private static final String REFACTORING_NAME = "IF with Static Configuration Class: Field";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassField";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public List<Resource> refactor(VariationPoint variationPoint, Map<String, Object> refactoringOptions) {
        Map<String, Field> fieldToFieldName = new HashMap<String, Field>();
        Map<String, Integer> positionToFieldName = new HashMap<String, Integer>();
        Map<String, List<Expression>> initialValuesToFieldName = new HashMap<String, List<Expression>>();
        Map<Expression, String> variantIDToInitialValue = new HashMap<Expression, String>();

        Class vpLocation = (Class) ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();

        fillMaps(variationPoint, fieldToFieldName, initialValuesToFieldName, variantIDToInitialValue,
                positionToFieldName);

        RefactoringUtil.deleteVariableMembersFromLeading(variationPoint);

        Block nonStaticBlock = StatementsFactory.eINSTANCE.createBlock();
        Block staticBlock = StatementsFactory.eINSTANCE.createBlock();
        staticBlock.getModifiers().add(ModifiersFactory.eINSTANCE.createStatic());

        for (String fieldName : fieldToFieldName.keySet()) {
            Field field = fieldToFieldName.get(fieldName);
            List<Expression> initialValues = initialValuesToFieldName.get(fieldName);
            int fieldPos = positionToFieldName.get(field.getName());

            vpLocation.getMembers().add(fieldPos, field);

            if (initialValues.size() > 1) {
                RefactoringUtil.removeFinalIfApplicable(field);

                field.setInitialValue(null);

                for (Expression value : initialValues) {
                    String variantId = variantIDToInitialValue.get(value);
                    String groupName = variationPoint.getGroup().getName();

                    ExpressionStatement fieldAssignment = createFieldAssignment(field, value);

                    Condition condition = IfElseRefactoringUtil.createVariabilityCondition(variantId, groupName);
                    Block ifBlock = (Block) condition.getStatement();
                    ifBlock.getStatements().add(fieldAssignment);

                    if (isStatic(field)) {
                        staticBlock.getStatements().add(condition);
                    } else {
                        nonStaticBlock.getStatements().add(condition);
                    }
                }
            }
        }
        if (staticBlock.getStatements().size() > 0) {
            vpLocation.getMembers().add(0, staticBlock);
        }
        if (nonStaticBlock.getStatements().size() > 0) {
            vpLocation.getMembers().add(0, nonStaticBlock);
        }

        ArrayList<Resource> resourceList = Lists.newArrayList(vpLocation.eResource());
        ResourceSet resourceSet = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement().eResource()
                .getResourceSet();
        String sourcePath = (String) refactoringOptions.get(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY);
        Resource configResource = SPLConfigurationUtil.addConfigurationIfMissing(sourcePath, resourceSet,
                variationPoint);
        if (configResource != null) {
            resourceList.add(configResource);
        }

        return resourceList;
    }

    private ExpressionStatement createFieldAssignment(Field field, Expression value) {
        AssignmentExpression assignmentExpr = ExpressionsFactory.eINSTANCE.createAssignmentExpression();
        assignmentExpr.setValue(value);
        assignmentExpr.setAssignmentOperator(OperatorsFactory.eINSTANCE.createAssignment());
        IdentifierReference fieldRef = ReferencesFactory.eINSTANCE.createIdentifierReference();
        fieldRef.setTarget(field);
        assignmentExpr.setChild(fieldRef);
        ExpressionStatement expressionStatement = StatementsFactory.eINSTANCE.createExpressionStatement();
        expressionStatement.setExpression(assignmentExpr);
        return expressionStatement;
    }

    private boolean isStatic(Field field) {
        for (Modifier modifier : field.getModifiers()) {
            if (modifier instanceof Static) {
                return true;
            }
        }
        return false;
    }

    private void fillMaps(VariationPoint vp, Map<String, Field> fieldToFieldName,
            Map<String, List<Expression>> initialValuesToFieldName, Map<Expression, String> variantIDToInitialValue,
            Map<String, Integer> positionToFieldName) {
        for (Variant variant : vp.getVariants()) {
            for (SoftwareElement se : variant.getImplementingElements()) {
                Field field = (Field) ((JaMoPPSoftwareElement) se).getJamoppElement();
                Field fieldCpy = EcoreUtil.copy(field);

                fieldToFieldName.put(fieldCpy.getName(), fieldCpy);

                int fieldPos = getFieldPosInContainer(field);
                positionToFieldName.put(fieldCpy.getName(), fieldPos);

                if (!initialValuesToFieldName.containsKey(fieldCpy.getName())) {
                    initialValuesToFieldName.put(fieldCpy.getName(), new LinkedList<Expression>());
                }

                Expression initialValue = fieldCpy.getInitialValue();
                if (!containsExpression(initialValuesToFieldName.get(fieldCpy.getName()), initialValue)) {
                    initialValuesToFieldName.get(fieldCpy.getName()).add(initialValue);
                }

                variantIDToInitialValue.put(initialValue, variant.getId());
            }
        }
    }

    private boolean containsExpression(List<Expression> container, Expression expression) {
        for (Expression element : container) {
            if (RefactoringUtil.areEqual(element, expression)) {
                return true;
            }
        }
        return false;
    }

    private int getFieldPosInContainer(Field field) {
        MemberContainer fieldContainer = (MemberContainer) field.eContainer();
        int fieldPos;
        if (fieldContainer == null) {
            fieldPos = 0;
        } else {
            fieldPos = fieldContainer.getMembers().indexOf(field);
        }
        return fieldPos;
    }

    @Override
    public boolean canBeAppliedTo(VariationPoint variationPoint) {
        Commentable jamoppElement = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();

        boolean correctLocation = jamoppElement instanceof MemberContainer;
        boolean allImplementingElementsAreFields = RefactoringUtil.allImplementingElementsOfType(variationPoint,
                Field.class);
        boolean correctInput = correctLocation && allImplementingElementsAreFields;

        if (!correctInput) {
            return false;
        }

        return !RefactoringUtil.hasConflictingFields(variationPoint);
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

}
