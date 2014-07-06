package org.splevo.jamopp.refactoring.java.ifelse.optor;

import java.util.HashSet;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.Statement;
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
 * Refactors variability in if-else chains.
 */
public class IfElseStaticConfigClassConditionOPTOR implements VariabilityRefactoring {

    private static final String REFACTORING_NAME = "IF-Else with Static Configuration Class (OPTOR): Condition";
    private static final String REFACTORING_ID = 
            "org.splevo.jamopp.refactoring.java.ifelse.xor.IfElseStaticConfigClassConditionOPTOR";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public void refactor(VariationPoint vp) {
        Condition vpLocation = (Condition) ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();

        ClassifierImport splConfImport = SPLConfigurationUtil.getSPLConfigClassImport();
        if (!RefactoringUtil.containsImport(vpLocation.getContainingCompilationUnit(), splConfImport)) {
            vpLocation.getContainingCompilationUnit().getImports().add(splConfImport);
        }

        Statement elseStatement = vpLocation.getElseStatement();
        boolean hasLeadingVariant = false;

        Condition previousVarCond = null;
        for (Variant variant : vp.getVariants()) {
            if (variant.getLeading()) {
                hasLeadingVariant = true;
            }
            Condition varCond = RefactoringUtil.generateConditionVariantIDWithEmptyIfBlock(variant.getId());

            if (vp.getVariants().indexOf(variant) == 0) {
                vpLocation.setElseStatement(varCond);
            }

            HashSet<Statement> implElements = new HashSet<Statement>();
            for (SoftwareElement se : variant.getImplementingElements()) {
                Statement element = EcoreUtil.copy((Statement) ((JaMoPPSoftwareElement) se).getJamoppElement());
                implElements.add(element);
                if (variant.getImplementingElements().indexOf(se) == (variant.getImplementingElements().size() - 1)
                        && element instanceof Condition
                        && EcoreUtil.equals(((Condition) element).getElseStatement(), elseStatement)) {
                    ((Condition) element).setElseStatement(null);
                }
            }

            Block varCondBlock = (Block) varCond.getStatement();
            varCondBlock.getStatements().addAll(implElements);
            if (previousVarCond != null) {
                previousVarCond.setElseStatement(varCond);
            }
            previousVarCond = varCond;
        }
        if (!hasLeadingVariant) {
            previousVarCond.setElseStatement(elseStatement);
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
        boolean correctLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement() instanceof Condition;
        boolean allImplementingElementsAreStatements = RefactoringUtil.allImplementingElementsOfType(variationPoint,
                Statement.class);
        boolean correctInput = hasEnoughVariants && correctLocation && allImplementingElementsAreStatements;

        return correctInput;
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

}
