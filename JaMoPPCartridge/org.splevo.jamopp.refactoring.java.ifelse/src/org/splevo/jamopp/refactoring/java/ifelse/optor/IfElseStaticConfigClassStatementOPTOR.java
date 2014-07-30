package org.splevo.jamopp.refactoring.java.ifelse.optor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Return;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.refactoring.util.SPLConfigurationUtil;
import org.splevo.jamopp.refactoring.util.VariabilityPositionUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
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
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.optor.IfElseStaticConfigClassStatementOPTOR";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public void refactor(VariationPoint variationPoint, Map<String, String> refactoringOptions) {
        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPSoftwareElement) variationPoint.getLocation())
                .getJamoppElement();

        if (RefactoringUtil.hasImplementingElementsOfType(variationPoint, Return.class)) {
            RefactoringUtil
                    .addCommentBefore(vpLocation, "FIXME: Introduced optional variability for return statement.");
        }

        ClassifierImport splConfImport = SPLConfigurationUtil.getSPLConfigClassImport();
        if (!RefactoringUtil.containsImport(vpLocation.getContainingCompilationUnit(), splConfImport)) {
            vpLocation.getContainingCompilationUnit().getImports().add(splConfImport);
        }
        Map<String, LocalVariableStatement> localVariableStatements = new HashMap<String, LocalVariableStatement>();
        EList<Variant> variants = variationPoint.getVariants();

        int variabilityPosition = VariabilityPositionUtil.getVariabilityPosition(variationPoint);
        int currentVariabilityPosition = variabilityPosition;

        for (Variant variant : variants) {
            if (variant.getLeading()) {
                RefactoringUtil.deleteVariableStatements(variationPoint);
            }

            Condition currentCondition = RefactoringUtil.generateVariantCondition(variant, localVariableStatements);
            vpLocation.getStatements().add(currentVariabilityPosition++, currentCondition);
        }

        for (String key : localVariableStatements.keySet()) {
            if (!RefactoringUtil.hasVariableWithSameName(vpLocation, key)) {
                vpLocation.getStatements().add(variabilityPosition++, localVariableStatements.get(key));
            }
        }
    }

    @Override
    public boolean canBeAppliedTo(VariationPoint variationPoint) {
        Commentable vpLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();

        boolean correctLocation = vpLocation instanceof StatementListContainer;
        boolean allImplementingElementsAreStatements = RefactoringUtil.allImplementingElementsOfType(variationPoint,
                Statement.class);
        boolean correctInput = correctLocation && allImplementingElementsAreStatements;

        if (!correctInput) {
            return false;
        }

        boolean hasConflictingVariables = RefactoringUtil.hasConflictingLocalVariables(variationPoint);
        boolean hasConstructorCalls = RefactoringUtil.hasConstructorCalls(variationPoint);

        return !hasConflictingVariables && !hasConstructorCalls;
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

}
