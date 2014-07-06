package org.splevo.jamopp.refactoring.java.ifelse.optor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Return;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.refactoring.util.SPLConfigurationUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Introduces If-Else Statements that check the configuration in the If-condition to decide which
 * variant's statements to execute. Common variables with equal types will be merged. In case of
 * common variables with different types, the whole method gets extracted into variant-specific
 * methods. Throws an exception if no variant was selected in the configuration.
 */
public class IfElseStaticConfigClassStatementOPTOR implements VariabilityRefactoring {

    private static final String REFACTORING_NAME = "IF-Else with Static Configuration Class (OPTOR): Statement";
    private static final String REFACTORING_ID = 
            "org.splevo.jamopp.refactoring.java.ifelse.xor.IfElseStaticConfigClassStatementOPTOR";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public void refactor(VariationPoint vp) {
        boolean hasConflictingVariables = RefactoringUtil.hasConflictingLocalVariables(vp);
        if (hasConflictingVariables) {
            RefactoringUtil.extractVariantsIntoMethods(vp);
        }

        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPSoftwareElement) vp.getLocation())
                .getJamoppElement();

        ClassifierImport splConfImport = SPLConfigurationUtil.getSPLConfigClassImport();
        if (!RefactoringUtil.containsImport(vpLocation.getContainingCompilationUnit(), splConfImport)) {
            vpLocation.getContainingCompilationUnit().getImports().add(splConfImport);
        }

        Map<String, LocalVariableStatement> localVariableStatements = new HashMap<String, LocalVariableStatement>();
        int posBeforeFirstCond = Integer.MAX_VALUE;
        EList<Variant> variants = vp.getVariants();

        for (Variant variant : variants) {
            int indexBeginVariant = RefactoringUtil.getVariabilityPosition(vpLocation, variant);

            if (variant.getLeading()) {
                RefactoringUtil.deleteVariableStatements(vp);
            }

            if (indexBeginVariant < posBeforeFirstCond) {
                posBeforeFirstCond = indexBeginVariant;
            }

            Condition currentCondition = RefactoringUtil.generateConditionVariantIDWithEmptyIfBlock(variant
                    .getId());

            RefactoringUtil.fillIfBlockWithVariantElements(variant, currentCondition, localVariableStatements);

            if (indexBeginVariant != -1) {
                vpLocation.getStatements().add(indexBeginVariant, currentCondition);
            } else {
                vpLocation.getStatements().add(currentCondition);
            }
        }

        if (posBeforeFirstCond != -1) {
            vpLocation.getStatements().addAll(posBeforeFirstCond, localVariableStatements.values());
        } else {
            vpLocation.getStatements().addAll(localVariableStatements.values());
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
        boolean correctLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement() instanceof StatementListContainer;
        boolean allImplementingElementsAreStatements = RefactoringUtil.allImplementingElementsOfType(variationPoint,
                Statement.class);
        boolean correctInput = hasEnoughVariants && correctLocation && allImplementingElementsAreStatements;

        if (!correctInput) {
            return false;
        }

        return !RefactoringUtil.hasImplementingElementsOfType(variationPoint, Return.class);
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

}
