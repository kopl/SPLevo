package org.splevo.jamopp.refactoring.java.ifelse;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * The code base class must contain all fields from the variants. Therefore, this refactoring merges
 * the fields from all variants into the base.
 */
public class IfElseStaticConfigClassField implements VariabilityRefactoring {

    private static final String REFACTORING_NAME = "IF-Else with Static Configuration Class: Field";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.optor.IfElseStaticConfigClassField";
    private static final String FINAL_COMMENT = "FIXME: removed final from field";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public void refactor(VariationPoint variationPoint, Map<String, String> refactoringOptions) {
        RefactoringUtil.deleteVariableMembersFromLeading(variationPoint);

        Map<String, Field> fieldToFieldName = new HashMap<String, Field>();
        Map<String, Integer> positionToFieldName = new HashMap<String, Integer>();
        Map<String, List<Expression>> initialValuesToFieldName = new HashMap<String, List<Expression>>();
        Map<Expression, String> variantIDToInitialValue = new HashMap<Expression, String>();

        Class vpLocation = (Class) ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();

        fillMaps(variationPoint, fieldToFieldName, initialValuesToFieldName, variantIDToInitialValue, positionToFieldName);

        Block nonStaticBlock = StatementsFactory.eINSTANCE.createBlock();
        Block staticBlock = StatementsFactory.eINSTANCE.createBlock();
        staticBlock.getModifiers().add(ModifiersFactory.eINSTANCE.createStatic());

        for (String fieldName : fieldToFieldName.keySet()) {
            Field field = fieldToFieldName.get(fieldName);
            List<Expression> initialValues = initialValuesToFieldName.get(fieldName);
            int fieldPos = positionToFieldName.get(field.getName());

            vpLocation.getMembers().add(fieldPos, field);

            boolean hadFinalModifier = RefactoringUtil.removeFinalIfApplicable(field);

            if (hadFinalModifier) {
                RefactoringUtil.addCommentBefore(vpLocation, FINAL_COMMENT);
            }

            if (initialValues.size() > 1 && hasDifferentValues(initialValues)) {
                field.setInitialValue(null);

                for (Expression value : initialValues) {
                    String variantId = variantIDToInitialValue.get(value);
                    String groupId = variationPoint.getGroup().getId();
                    Condition condition = RefactoringUtil.createVariabilityCondition(variantId, groupId);
                    Block ifBlock = (Block) condition.getStatement();

                    AssignmentExpression assignmentExpr = ExpressionsFactory.eINSTANCE.createAssignmentExpression();
                    assignmentExpr.setValue(value);
                    assignmentExpr.setAssignmentOperator(OperatorsFactory.eINSTANCE.createAssignment());
                    IdentifierReference fieldRef = ReferencesFactory.eINSTANCE.createIdentifierReference();
                    fieldRef.setTarget(field);
                    assignmentExpr.setChild(fieldRef);
                    ExpressionStatement expressionStatement = StatementsFactory.eINSTANCE.createExpressionStatement();
                    expressionStatement.setExpression(assignmentExpr);

                    ifBlock.getStatements().add(expressionStatement);

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
    }

    private boolean isStatic(Field field) {
        for (Modifier modifier : field.getModifiers()) {
            if (modifier instanceof Static) {
                return true;
            }
        }
        return false;
    }

    private boolean hasDifferentValues(List<Expression> initialValues) {
        Expression firstValue = null;
        for (int i = 0; i < initialValues.size(); i++) {
            if (firstValue == null) {
                firstValue = initialValues.get(i);
                continue;
            }
            if (EcoreUtil.equals(firstValue, initialValues.get(i))) {
                continue;
            }
            return true;
        }
        return false;
    }

    private void fillMaps(VariationPoint vp, Map<String, Field> fieldsToName,
            Map<String, List<Expression>> expressionsToName, Map<Expression, String> variantIDToExpression,
            Map<String, Integer> positionToField) {
        for (Variant variant : vp.getVariants()) {
            for (SoftwareElement se : variant.getImplementingElements()) {
                Field field = (Field) ((JaMoPPSoftwareElement) se).getJamoppElement();
                Field fieldCpy = EcoreUtil.copy(field);
                MemberContainer fieldContainer = (MemberContainer) field.eContainer();
                int fieldPos;
                if (fieldContainer == null) {
                    fieldPos = 0;
                } else {
                    fieldPos = fieldContainer.getMembers().indexOf(field);
                }

                positionToField.put(fieldCpy.getName(), fieldPos);
                fieldsToName.put(fieldCpy.getName(), fieldCpy);

                if (!expressionsToName.containsKey(fieldCpy.getName())) {
                    expressionsToName.put(fieldCpy.getName(), new LinkedList<Expression>());
                }

                expressionsToName.get(fieldCpy.getName()).add(fieldCpy.getInitialValue());
                variantIDToExpression.put(fieldCpy.getInitialValue(), variant.getId());
            }
        }
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
