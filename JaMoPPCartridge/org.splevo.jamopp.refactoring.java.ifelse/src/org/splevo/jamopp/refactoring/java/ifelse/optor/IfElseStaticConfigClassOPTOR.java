package org.splevo.jamopp.refactoring.java.ifelse.optor;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseStaticConfigClassBlock;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseStaticConfigClassClass;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseStaticConfigClassCompilationUnit;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseStaticConfigClassConstructor;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseStaticConfigClassEnumeration;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseStaticConfigClassField;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseStaticConfigClassImport;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseStaticConfigClassInterface;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseStaticConfigClassMethod;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * This refactoring implements the if-else mechanism for the use with a static configuration class.
 */
public class IfElseStaticConfigClassOPTOR implements VariabilityRefactoring {

    public static final String JAVA_SOURCE_DIRECTORY = "JaMoPP.Refactoring.Options.SourceDirectory";

    private static Logger logger = Logger.getLogger(IfElseStaticConfigClassOPTOR.class);

    private static final String REFACTORING_NAME = "IF-Else with Static Configuration Class (OPTOR)";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.optor.IfElseStaticConfigClassOPTOR";

    private static List<VariabilityRefactoring> availableRefactorings;

    static {
        availableRefactorings = new LinkedList<VariabilityRefactoring>();

        // add optor if refactorings here
        availableRefactorings.add(new IfElseStaticConfigClassCompilationUnit());
        availableRefactorings.add(new IfElseStaticConfigClassImport());
        availableRefactorings.add(new IfElseStaticConfigClassClass());
        availableRefactorings.add(new IfElseStaticConfigClassInterface());
        availableRefactorings.add(new IfElseStaticConfigClassEnumeration());
        availableRefactorings.add(new IfElseStaticConfigClassField());
        availableRefactorings.add(new IfElseStaticConfigClassBlock());
        availableRefactorings.add(new IfElseStaticConfigClassMethod());
        availableRefactorings.add(new IfElseStaticConfigClassConstructor());
        availableRefactorings.add(new IfElseStaticConfigClassStatementOPTOR());
        availableRefactorings.add(new IfElseStaticConfigClassConditionOPTOR());
    }

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public void refactor(VariationPoint variationPoint, Map<String, String> refactoringOptions) {
        for (VariabilityRefactoring refactoring : availableRefactorings) {
            if (refactoring.canBeAppliedTo(variationPoint)) {
                refactoring.refactor(variationPoint, refactoringOptions);
                logger.info("Refactored with: " + refactoring.getVariabilityMechanism().getName());
                break;
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

        for (VariabilityRefactoring refactoring : availableRefactorings) {
            if (refactoring.canBeAppliedTo(variationPoint)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

}
