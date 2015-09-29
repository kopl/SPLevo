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
import org.eclipse.emf.ecore.resource.Resource;
import org.splevo.jamopp.refactoring.java.JaMoPPFullyAutomatedVariabilityRefactoring;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassBlock;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassClassInMemberContainer;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassCompilationUnit;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassConstructor;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassEnumerationInMemberContainer;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassField;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassImport;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassInterfaceInMemberContainer;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassMethod;
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

/**
 * This refactoring implements the if-else mechanism for the use with a static configuration class.
 */
public class IfStaticConfigClassOPTXOR extends JaMoPPFullyAutomatedVariabilityRefactoring {

    private static Logger logger = Logger.getLogger(IfStaticConfigClassOPTXOR.class);

    private static final String REFACTORING_NAME = "IF with Static Configuration Class (OPTXOR)";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.optxor.IfStaticConfigClassOPTXOR";

    protected List<VariabilityRefactoring> availableRefactorings;

    public IfStaticConfigClassOPTXOR() {
    	this (new FullyAutomatedIfElseRefactoringUtil());
    }
    
    public IfStaticConfigClassOPTXOR(IfElseRefactoringUtil util) {
        availableRefactorings = new LinkedList<VariabilityRefactoring>();

        // add refactorings here
        availableRefactorings.add(new IfStaticConfigClassCompilationUnit());
        availableRefactorings.add(new IfStaticConfigClassImport());
        availableRefactorings.add(new IfStaticConfigClassClassInMemberContainer());
        availableRefactorings.add(new IfStaticConfigClassInterfaceInMemberContainer());
        availableRefactorings.add(new IfStaticConfigClassEnumerationInMemberContainer());
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
    protected List<Resource> refactorFullyAutomated(VariationPoint variationPoint, Map<String, Object> refactoringOptions) {
        for (VariabilityRefactoring refactoring : availableRefactorings) {
            if (refactoring.canBeAppliedTo(variationPoint)) {
                List<Resource> changedResources = refactoring.refactor(variationPoint, refactoringOptions);
                RefactoringUtil.resolveVPsWithSameLocation(variationPoint);

                logger.info("Refactored with: " + refactoring.getVariabilityMechanism().getName());
                return changedResources;
            }
        }
        return null;
    }

    @Override
    public boolean canBeAppliedTo(VariationPoint variationPoint) {
        boolean correctBindingTime = (variationPoint.getBindingTime() == BindingTime.COMPILE_TIME);
        boolean correctVariabilityType = variationPoint.getVariabilityType() == VariabilityType.OPTXOR;
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
