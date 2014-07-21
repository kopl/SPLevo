package org.splevo.jamopp.refactoring.java.ifelse.optor;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.variability.VariationPoint;

/**
 * This refactoring implements the if-else mechanism for the use with a static configuration class.
 */
public class IfElseStaticConfigClassOPTOR implements VariabilityRefactoring {

    private static Logger logger = Logger.getLogger(IfElseStaticConfigClassOPTOR.class);
    
    private static final String REFACTORING_NAME = "IF-Else with Static Configuration Class (OPTOR)";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.xor.IfElseStaticConfigClassOPTOR";

    private static List<VariabilityRefactoring> availableRefactorings;

    static {
        availableRefactorings = new LinkedList<VariabilityRefactoring>();

        // add all optor if-else refactorings to the list of available refactorings in the order
        // they shoud be executed
        availableRefactorings.add(new IfElseStaticConfigClassClassOPTOR());
        availableRefactorings.add(new IfElseStaticConfigClassConditionOPTOR());
        availableRefactorings.add(new IfElseStaticConfigClassConstructorOPTOR());
        availableRefactorings.add(new IfElseStaticConfigClassEnumerationOPTOR());
        availableRefactorings.add(new IfElseStaticConfigClassFieldOPTOR());
        availableRefactorings.add(new IfElseStaticConfigClassImportOPTOR());
        availableRefactorings.add(new IfElseStaticConfigClassInterfaceOPTOR());
        availableRefactorings.add(new IfElseStaticConfigClassMethodOPTOR());
        availableRefactorings.add(new IfElseStaticConfigClassStatementOPTOR());
    }

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public void refactor(VariationPoint vp) {
        // execute the refactoring that can be applied
        for (VariabilityRefactoring refactoring : availableRefactorings) {
            if (refactoring.canBeAppliedTo(vp)) {
                refactoring.refactor(vp);
                logger.info("Used refactoring: " + refactoring.getVariabilityMechanism().getName());
            }
        }
    }

    @Override
    public boolean canBeAppliedTo(VariationPoint variationPoint) {
        // check whether a refactoring can be applied
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
