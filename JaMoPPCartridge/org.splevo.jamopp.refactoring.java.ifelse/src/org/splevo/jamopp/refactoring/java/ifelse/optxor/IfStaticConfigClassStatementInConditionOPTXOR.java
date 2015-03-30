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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.Statement;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.refactoring.util.SPLConfigurationUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.refactoring.VariabilityRefactoringService;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;

/**
 * Refactors variable else-statements.
 */
public class IfStaticConfigClassStatementInConditionOPTXOR implements VariabilityRefactoring {

    private static final String REFACTORING_NAME = "IF with Static Configuration Class (OPTXOR): Statement in Condition";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.optxor.IfStaticConfigClassConditionOPTXOR";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public List<Resource> refactor(VariationPoint variationPoint, Map<String, Object> refactoringOptions) {
        Condition vpLocation = (Condition) ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        Statement elseStatement = vpLocation.getElseStatement();

        CompilationUnit compilationUnit = vpLocation.getContainingCompilationUnit();
        SPLConfigurationUtil.addConfigurationClassImportIfMissing(compilationUnit);

        String groupName = variationPoint.getGroup().getName();
        Condition previousCondition = vpLocation;

        for (Variant variant : variationPoint.getVariants()) {
            Condition variabilityCondition = RefactoringUtil.createVariabilityCondition(variant.getId(), groupName);

            Commentable element = ((JaMoPPSoftwareElement) variant.getImplementingElements().get(0)).getJamoppElement();
            Statement stmt = EcoreUtil.copy((Statement) element);
//            if (variant.getLeading()) {
//                stmt = (Statement) element;
//            } else {
//                stmt = EcoreUtil.copy((Statement) element);
//            }

            if (stmt instanceof Block) {
                variabilityCondition.setStatement(stmt);
            } else {
                Block ifBlock = (Block) variabilityCondition.getStatement();
                ifBlock.getStatements().add(stmt);
            }

            previousCondition.setElseStatement(variabilityCondition);
            previousCondition = variabilityCondition;
        }
        
        previousCondition.setElseStatement(elseStatement);

        ArrayList<Resource> resourceList = Lists.newArrayList(vpLocation.eResource());
        ResourceSet resourceSet = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement().eResource()
                .getResourceSet();
        String sourcePath = (String) refactoringOptions.get(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY);
        Resource configResource = SPLConfigurationUtil.addConfigurationIfMissing(sourcePath, resourceSet,
                variationPoint);
        if (configResource != null) {
            resourceList.add(configResource);
        }

        return resourceList;
    }

    @Override
    public boolean canBeAppliedTo(VariationPoint variationPoint) {
        Commentable vpLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        boolean correctLocation = vpLocation instanceof Condition;
        boolean allImplementingElementsAreStatements = RefactoringUtil.allImplementingElementsOfType(variationPoint,
                Statement.class);

        return correctLocation && allImplementingElementsAreStatements;
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

}
