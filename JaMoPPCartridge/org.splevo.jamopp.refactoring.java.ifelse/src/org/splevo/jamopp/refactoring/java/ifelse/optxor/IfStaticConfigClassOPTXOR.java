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
package org.splevo.jamopp.refactoring.java.ifelse.optxor;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.Resource;
import org.splevo.jamopp.refactoring.java.JaMoPPFullyAutomatedVariabilityRefactoring;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassBlock;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassClassInMemberContainer;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassCompilationUnit;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassConstructor;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassEnumerationInCompilationUnit;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassEnumerationInMemberContainer;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassField;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassImport;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassInterfaceInMemberContainer;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassMethod;
import org.splevo.jamopp.refactoring.java.ifelse.RequiresIfRefactoringUtil;
import org.splevo.jamopp.refactoring.java.ifelse.util.FullyAutomatedIfElseRefactoringUtil;
import org.splevo.jamopp.refactoring.java.ifelse.util.IfElseRefactoringUtil;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * This refactoring implements the if-else mechanism for the use with a static configuration class.
 */
public class IfStaticConfigClassOPTXOR extends JaMoPPFullyAutomatedVariabilityRefactoring {

    private static Logger logger = Logger.getLogger(IfStaticConfigClassOPTXOR.class);

    private static final String REFACTORING_NAME = "IF with Static Configuration Class (OPTXOR)";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.optxor.IfStaticConfigClassOPTXOR";

    protected List<VariabilityRefactoring> availableRefactorings;

    /**
     * Constructs the refactoring.
     */
    public IfStaticConfigClassOPTXOR() {
        this(new FullyAutomatedIfElseRefactoringUtil());
    }

    /**
     * Constructs the refactoring.
     * 
     * @param util
     *            The refactoring util to be used in the refactoring.s
     */
    public IfStaticConfigClassOPTXOR(IfElseRefactoringUtil util) {
        availableRefactorings = new LinkedList<VariabilityRefactoring>();

        // add refactorings here
        availableRefactorings.add(new IfStaticConfigClassCompilationUnit());
        availableRefactorings.add(new IfStaticConfigClassImport());
        availableRefactorings.add(new IfStaticConfigClassClassInMemberContainer());
        availableRefactorings.add(new IfStaticConfigClassInterfaceInMemberContainer());
        availableRefactorings.add(new IfStaticConfigClassEnumerationInMemberContainer());
        availableRefactorings.add(new IfStaticConfigClassEnumerationInCompilationUnit());
        availableRefactorings.add(new IfStaticConfigClassField(util));
        availableRefactorings.add(new IfStaticConfigClassBlock());
        availableRefactorings.add(new IfStaticConfigClassMethod());
        availableRefactorings.add(new IfStaticConfigClassConstructor());
        availableRefactorings.add(new IfStaticConfigClassStatementInStatementListContainerOPTXOR(util));
        availableRefactorings.add(new IfStaticConfigClassStatementInConditionOPTXOR(util));
    }

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    protected List<Resource> refactorFullyAutomated(VariationPoint variationPoint,
            Map<String, Object> refactoringOptions) {
        for (VariabilityRefactoring refactoring : availableRefactorings) {
            if (refactoring.canBeAppliedTo(variationPoint).getSeverity() == Diagnostic.OK) {
                List<Resource> changedResources = refactoring.refactor(variationPoint, refactoringOptions);
                RefactoringUtil.resolveVPsWithSameLocation(variationPoint);

                logger.info("Refactored with: " + refactoring.getVariabilityMechanism().getName());
                return changedResources;
            }
        }
        return null;
    }

    @Override
    public Diagnostic canBeAppliedTo(VariationPoint variationPoint) {
        boolean correctCharacteristics = hasCorrectCharacteristics(variationPoint);
        if (!correctCharacteristics) {
            return new BasicDiagnostic(Diagnostic.ERROR, null, 0,
                    "If with Static Configuration Class (OPTXOR): Wrong Characteristics", null);
        }

        BasicDiagnostic diagnostic = new BasicDiagnostic(Diagnostic.ERROR, null, 0,
                "No matching Refactoring can be found!", null);
        for (VariabilityRefactoring refactoring : availableRefactorings) {
            Diagnostic d = refactoring.canBeAppliedTo(variationPoint);
            if (d.getSeverity() == Diagnostic.OK) {
                return new BasicDiagnostic(Diagnostic.OK, null, 0, "OK", null);
            } else {
                diagnostic.add(d);
            }
        }
        return diagnostic;
    }

    /**
     * Determines if the variation point has the correct characteristics for this refactoring.
     * 
     * @param variationPoint
     *            The variation point to be checked.
     * @return True if this refactoring can be applied to the variation point, False otherwise.
     */
    protected boolean hasCorrectCharacteristics(VariationPoint variationPoint) {
        boolean correctBindingTime = variationPoint.getBindingTime() == BindingTime.COMPILE_TIME;
        boolean correctVariabilityType = variationPoint.getVariabilityType() == VariabilityType.OPTXOR;
        boolean correctExtensibility = variationPoint.getExtensibility() == Extensible.NO;
        return correctBindingTime && correctVariabilityType && correctExtensibility;
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

    /**
     * Checks if the given variation point can be refactored without the use of the RefactoringUtil.
     * This implies that all features offered by the {@link IfElseRefactoringUtil} are not used in
     * these refactorings.
     * 
     * @param variationPoint The variation point to be refactored.
     * @return True if the variation point can be refactored, False otherwise.
     */
    public boolean canBeRefactoredWithoutRefactoringUtil(final VariationPoint variationPoint) {
        return Iterables.any(availableRefactorings, new Predicate<VariabilityRefactoring>() {
            @Override
            public boolean apply(VariabilityRefactoring input) {
                if (input instanceof RequiresIfRefactoringUtil) {
                    return false;
                }
                return input.canBeAppliedTo(variationPoint).getSeverity() == Diagnostic.OK;
            }
        });

    }
}
