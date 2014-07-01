package org.splevo.jamopp.refactoring.java.ifelse.optor;

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
import org.emftext.language.java.literals.LiteralsFactory;
import org.emftext.language.java.literals.NullLiteral;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.modifiers.ModifiersFactory;
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
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * <h1>Summary</h1> The code base class must contain all fields from the variants. Therefore, this
 * refactoring merges the fields from all variants into the base.
 */
public class IfElseStaticConfigClassFieldOPTOR implements VariabilityRefactoring {

    private static final String REFACTORING_NAME = "IF-Else with Static Configuration Class (OPTOR): Field";
    private static final String REFACTORING_ID = 
            "org.splevo.jamopp.refactoring.java.ifelse.xor.IfElseStaticConfigClassFieldOPTOR";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public void refactor(VariationPoint vp) {
        RefactoringUtil.deleteVariableMembers(vp);

        Map<String, Field> fieldsToName = new HashMap<String, Field>();
        Map<Field, Integer> positionToField = new HashMap<Field, Integer>();
        Map<String, List<Expression>> expressionsToName = new HashMap<String, List<Expression>>();
        Map<Expression, String> variantIDToExpression = new HashMap<Expression, String>();

        Class vpLocation = (Class) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();

        fillMaps(vp, fieldsToName, expressionsToName, variantIDToExpression, positionToField);

        for (String fieldName : fieldsToName.keySet()) {
            Field field = fieldsToName.get(fieldName);
            List<Expression> initialValues = expressionsToName.get(fieldName);
            int fieldPos = positionToField.get(field);
            vpLocation.getMembers().add(fieldPos, field);

            RefactoringUtil.removeFinalAndAddCommentIfApplicable(field);

            if (initialValues.size() > 1) {
                NullLiteral nullLiteral = LiteralsFactory.eINSTANCE.createNullLiteral();
                field.setInitialValue(nullLiteral);

                Block staticBlock = StatementsFactory.eINSTANCE.createBlock();
                staticBlock.getModifiers().add(ModifiersFactory.eINSTANCE.createStatic());

                for (Expression value : initialValues) {
                    String variantId = variantIDToExpression.get(value);
                    Condition condition = RefactoringUtil.generateConditionVariantIDWithEmptyIfBlock(variantId);
                    AssignmentExpression assignmentExpr = ExpressionsFactory.eINSTANCE.createAssignmentExpression();
                    assignmentExpr.setValue(value);
                    assignmentExpr.setAssignmentOperator(OperatorsFactory.eINSTANCE.createAssignment());
                    IdentifierReference fieldRef = ReferencesFactory.eINSTANCE.createIdentifierReference();
                    fieldRef.setTarget(field);
                    assignmentExpr.setChild(fieldRef);
                    ExpressionStatement expressionStatement = StatementsFactory.eINSTANCE.createExpressionStatement();
                    expressionStatement.setExpression(assignmentExpr);
                    ((Block) condition.getStatement()).getStatements().add(expressionStatement);
                    staticBlock.getStatements().add(condition);
                }
                vpLocation.getMembers().add(++fieldPos, staticBlock);
            }
        }
    }

    private void fillMaps(VariationPoint vp, Map<String, Field> fieldsToName,
            Map<String, List<Expression>> expressionsToName, Map<Expression, String> variantIDToExpression,
            Map<Field, Integer> positionToField) {
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

                positionToField.put(fieldCpy, fieldPos);
                fieldsToName.put(fieldCpy.getName(), fieldCpy);

                if (!expressionsToName.containsKey(fieldCpy.getName())) {
                    expressionsToName.put(fieldCpy.getName(), new LinkedList<Expression>());
                }

                expressionsToName.get(fieldCpy.getName()).add(fieldCpy.getInitialValue());
                variantIDToExpression.put(fieldCpy.getInitialValue(), variant.getVariantId());
            }
        }
    }

    @Override
    public boolean canBeAppliedTo(VariationPoint variationPoint) {
        boolean correctBindingTime = variationPoint.getBindingTime() == BindingTime.COMPILE_TIME;
        boolean correctVariabilityType = variationPoint.getVariabilityType() == VariabilityType.OPTOR;
        boolean correctExtensibility = variationPoint.getExtensibility() == Extensible.NO;
        boolean correctCharacteristics = correctBindingTime && correctVariabilityType && correctExtensibility;

        if (!correctCharacteristics) {
            return false;
        }

        boolean hasEnoughVariants = variationPoint.getVariants().size() > 0;
        Commentable jamoppElement = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        boolean correctLocation = jamoppElement instanceof MemberContainer;
        boolean allImplementingElementsAreFields = RefactoringUtil.allImplementingElementsOfType(variationPoint,
                Field.class);
        boolean correctInput = hasEnoughVariants && correctLocation && allImplementingElementsAreFields;

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
