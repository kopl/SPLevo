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

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.Statement;
import org.splevo.jamopp.refactoring.java.JaMoPPFullyAutomatedVariabilityRefactoring;
import org.splevo.jamopp.refactoring.java.ifelse.RequiresIfRefactoringUtil;
import org.splevo.jamopp.refactoring.java.ifelse.util.IfElseRefactoringUtil;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.vpm.software.JaMoPPJavaSoftwareElement;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

/**
 * Refactors variable else-statements.
 */
public class IfStaticConfigClassStatementInConditionOPTXOR extends JaMoPPFullyAutomatedVariabilityRefactoring implements
        RequiresIfRefactoringUtil {

    private static final String REFACTORING_NAME = "IF with Static Configuration Class (OPTXOR): Statement in Condition";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.optxor.IfStaticConfigClassConditionOPTXOR";

    private final IfElseRefactoringUtil ifElseRefactoringUtil;

    /**
     * Constructs the refactoring.
     * 
     * @param ifElseRefactoringUtil
     *            The refactoring util to be used during the refactoring.
     */
    public IfStaticConfigClassStatementInConditionOPTXOR(IfElseRefactoringUtil ifElseRefactoringUtil) {
        this.ifElseRefactoringUtil = ifElseRefactoringUtil;
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
        Condition vpLocation = (Condition) ((JaMoPPJavaSoftwareElement) variationPoint.getLocation())
                .getJamoppElement();
        Statement elseStatement = vpLocation.getElseStatement();

        ifElseRefactoringUtil.createConfigurationClassImport(vpLocation);

        Condition previousCondition = vpLocation;

        for (Variant variant : variationPoint.getVariants()) {
            Condition variabilityCondition = this.ifElseRefactoringUtil.createVariabilityCondition(variationPoint,
                    variant.getId());

            Commentable element = ((JaMoPPJavaSoftwareElement) variant.getImplementingElements().get(0))
                    .getJamoppElement();
            Statement stmt = clone((Statement) element);
            // if (variant.getLeading()) {
            // stmt = (Statement) element;
            // } else {
            // stmt = EcoreUtil.copy((Statement) element);
            // }

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

        Optional<Resource> configClassResource = ifElseRefactoringUtil.createConfigurationClass(variationPoint,
                refactoringOptions);
        if (configClassResource.isPresent()) {
            resourceList.add(configClassResource.get());
        }

        return resourceList;
    }

    @Override
    public Diagnostic canBeAppliedTo(VariationPoint variationPoint) {
        Commentable vpLocation = ((JaMoPPJavaSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        boolean correctLocation = vpLocation instanceof Condition;
        boolean allImplementingElementsAreStatements = RefactoringUtil.allImplementingElementsOfType(variationPoint,
                Statement.class);

        String error = "If with Static Configuration Class Statement in Condition (OPTXOR): ";
        if (!correctLocation) {
            return new BasicDiagnostic(Diagnostic.ERROR, null, 0, error + "Wrong Location", null);
        }
        if (!allImplementingElementsAreStatements) {
            return new BasicDiagnostic(Diagnostic.ERROR, null, 0, error + "Not All Ellements are Statements", null);
        }
        return new BasicDiagnostic(Diagnostic.OK, null, 0, "OK", null);
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

}
