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
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassBlock;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassClass;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassCompilationUnit;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassConstructor;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassEnumeration;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassField;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassImport;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassInterface;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassMethod;
import org.splevo.jamopp.refactoring.util.SPLConfigurationUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.refactoring.VariabilityRefactoringService;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * This refactoring implements the if-else mechanism for the use with a static configuration class.
 */
public class IfStaticConfigClassOPTXOR implements VariabilityRefactoring {

    private static Logger logger = Logger.getLogger(IfStaticConfigClassOPTXOR.class);

    private static final String REFACTORING_NAME = "IF-Else with Static Configuration Class (OPTXOR)";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.optxor.IfStaticConfigClassOPTXOR";

    private static List<VariabilityRefactoring> availableRefactorings;

    static {
        availableRefactorings = new LinkedList<VariabilityRefactoring>();

        // add refactorings here
        availableRefactorings.add(new IfStaticConfigClassCompilationUnit());
        availableRefactorings.add(new IfStaticConfigClassImport());
        availableRefactorings.add(new IfStaticConfigClassClass());
        availableRefactorings.add(new IfStaticConfigClassInterface());
        availableRefactorings.add(new IfStaticConfigClassEnumeration());
        availableRefactorings.add(new IfStaticConfigClassField());
        availableRefactorings.add(new IfStaticConfigClassBlock());
        availableRefactorings.add(new IfStaticConfigClassMethod());
        availableRefactorings.add(new IfStaticConfigClassConstructor());
        availableRefactorings.add(new IfStaticConfigClassStatementOPTXOR());
        availableRefactorings.add(new IfStaticConfigClassConditionOPTXOR());
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
        ConcreteClassifier configurationClass = SPLConfigurationUtil.findConfigurationClass(resourceSet);
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
        boolean hasConfigurationWithName = SPLConfigurationUtil.configurationClassHasConfigurationWithName(configurationClass, groupId);
        if (!hasConfigurationWithName) {
            Variant leadingOrFirstVariant = findLeadingOrFirstVariant(variationPoint);
            SPLConfigurationUtil.addConfigurationToConfigurationClass(configurationClass, groupId, leadingOrFirstVariant.getId());
        }
    }

    private Variant findLeadingOrFirstVariant(VariationPoint variationPoint) {
        VariationPointGroup group = variationPoint.getGroup();
        VariationPointModel vpm = group.getModel();

        for (VariationPointGroup vpg : vpm.getVariationPointGroups()) {
            for (VariationPoint vp : vpg.getVariationPoints()) {
                for (Variant v : vp.getVariants()) {
                    if (v.getLeading()) {
                        return v;
                    }
                }
            }
        }

        return variationPoint.getVariants().get(0);
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
