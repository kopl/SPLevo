package org.splevo.jamopp.refactoring.java.ifelse.optor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.LocalVariableStatement;
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
 * <h1>Summary</h1> Introduces If-Else Statements that check the configuration in the If-condition
 * to decide which variant's statements to execute. Common variables with equal types will be
 * merged. In case of common variables with different types, the whole method gets extracted into
 * variant-specific methods. Throws an exception if no variant was selected in the configuration.
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
        RefactoringUtil.deleteVariableElements(vp);

        if (RefactoringUtil.containsVarsSameNameDiffType(vp)) {
            RefactoringUtil.extractVariantsIntoMethods(vp);
        }

        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPSoftwareElement) vp.getLocation())
                .getJamoppElement();

        vpLocation.getContainingCompilationUnit().getImports().add(SPLConfigurationUtil.getSPLConfigClassImport());

        int indexBeginVariant = RefactoringUtil.getVariabilityPosition(vp);
        int indexEndVariant = indexBeginVariant;

        Map<String, LocalVariableStatement> localVariableStatements = new HashMap<String, LocalVariableStatement>();

        EList<Variant> variants = vp.getVariants();
        for (Variant variant : variants) {
            Condition currentCondition = RefactoringUtil.generateVariantIDMatchingConditionWithEmptyIfBlock(variant
                    .getVariantId());
            RefactoringUtil.fillIfBlockWithVariantElements(variant, currentCondition, localVariableStatements);

            vpLocation.getStatements().add(indexEndVariant++, currentCondition);
        }
        vpLocation.getStatements().addAll(indexBeginVariant, localVariableStatements.values());
    }

    @Override
    public boolean canBeAppliedTo(VariationPoint variationPoint) {
        boolean correctBindingTime = variationPoint.getBindingTime() == BindingTime.COMPILE_TIME;
        boolean correctVariabilityType = variationPoint.getVariabilityType() == VariabilityType.OPTOR;
        boolean correctExtensibility = variationPoint.getExtensibility() == Extensible.NO;
        boolean correctCharacteristics = correctBindingTime && correctVariabilityType && correctExtensibility;

        boolean hasEnoughVariants = variationPoint.getVariants().size() > 0;
        boolean correctLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement() instanceof Method;
        boolean allImplementingElementsAreStatements = 
                RefactoringUtil.allImplementingElementsOfType(variationPoint, Statement.class);
        boolean correctInput = hasEnoughVariants && correctLocation && allImplementingElementsAreStatements;

        return correctCharacteristics && correctInput;
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

}
