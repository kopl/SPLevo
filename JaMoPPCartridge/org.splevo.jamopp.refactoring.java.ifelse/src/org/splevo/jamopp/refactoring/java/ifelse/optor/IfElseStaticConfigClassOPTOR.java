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
package org.splevo.jamopp.refactoring.java.ifelse.optor;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseStaticConfigClassBlock;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseStaticConfigClassClass;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseStaticConfigClassCompilationUnit;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseStaticConfigClassConstructor;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseStaticConfigClassEnumeration;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseStaticConfigClassField;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseStaticConfigClassImport;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseStaticConfigClassInterface;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseStaticConfigClassMethod;
import org.splevo.jamopp.refactoring.util.SPLConfigurationUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.refactoring.VariabilityRefactoringService;
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
    public List<Resource> refactor(VariationPoint variationPoint, Map<String, String> refactoringOptions) {
        ResourceSet resourceSet = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement().eResource()
                .getResourceSet();
        Resource configResource = addConfigurationFileIfMissing(refactoringOptions, resourceSet);
        ConcreteClassifier configurationClass = SPLConfigurationUtil.getConfigurationClass(resourceSet);
        addConfigurationToConfigurationClass(variationPoint, configurationClass);

        for (VariabilityRefactoring refactoring : availableRefactorings) {
            if (refactoring.canBeAppliedTo(variationPoint)) {
                List<Resource> changedResources = refactoring.refactor(variationPoint, refactoringOptions);

                if (configResource != null) {
                    changedResources.add(configResource);
                }

                logger.info("Refactored with: " + refactoring.getVariabilityMechanism().getName());
                return changedResources;
            }
        }
        return null;
    }

    private void addConfigurationToConfigurationClass(VariationPoint variationPoint,
            ConcreteClassifier configurationClass) {
        String groupId = variationPoint.getGroup().getId();
        boolean hasConfigurationWithName = SPLConfigurationUtil.hasConfigurationWithName(configurationClass, groupId);
        if (!hasConfigurationWithName) {
            SPLConfigurationUtil.addConfiguration(configurationClass, groupId, "");
        }
    }

    private Resource addConfigurationFileIfMissing(Map<String, String> refactoringOptions, ResourceSet resourceSet) {
        Resource configResource = null;
        if (!SPLConfigurationUtil.hasConfigurationClass(resourceSet)) {
            configResource = SPLConfigurationUtil.generateConfigurationClassIn(resourceSet,
                    refactoringOptions.get(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY));
        }
        return configResource;
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
