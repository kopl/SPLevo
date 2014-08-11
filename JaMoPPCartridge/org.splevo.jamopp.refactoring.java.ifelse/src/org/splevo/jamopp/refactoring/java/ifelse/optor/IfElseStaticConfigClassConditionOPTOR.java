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

import java.util.Map;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.expressions.ConditionalOrExpression;
import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementsFactory;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.refactoring.util.SPLConfigurationUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Refactors variable else-statements.
 */
public class IfElseStaticConfigClassConditionOPTOR implements VariabilityRefactoring {

    private static final String REFACTORING_NAME = "IF-Else with Static Configuration Class (OPTOR): Condition";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.optor.IfElseStaticConfigClassConditionOPTOR";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public ResourceSet refactor(VariationPoint variationPoint, Map<String, String> refactoringOptions) {
        Condition vpLocation = (Condition) ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        Statement elseStatement = vpLocation.getElseStatement();

        ClassifierImport splConfImport = SPLConfigurationUtil.getSPLConfigClassImport();
        if (!RefactoringUtil.containsImport(vpLocation.getContainingCompilationUnit(), splConfImport)) {
            vpLocation.getContainingCompilationUnit().getImports().add(splConfImport);
        }

        Condition mainCondition = null;
        if (variationPoint.getVariants().size() > 1) {
            mainCondition = getConditionForMultipleVariants(variationPoint);

        } else {
            mainCondition = createVariantCondition(variationPoint.getVariants().get(0));
        }

        vpLocation.setElseStatement(mainCondition);
        mainCondition.setElseStatement(elseStatement);

        return RefactoringUtil.wrapInNewResourceSet(vpLocation);
    }

    private Condition getConditionForMultipleVariants(VariationPoint variationPoint) {
        String groupId = variationPoint.getGroup().getId();

        Condition condition = null;
        ConditionalOrExpression orExpression = ExpressionsFactory.eINSTANCE.createConditionalOrExpression();
        for (Variant variant : variationPoint.getVariants()) {
            IdentifierReference cond = SPLConfigurationUtil.generateConfigMatchingExpression(variant.getId(), groupId);
            orExpression.getChildren().add(cond);
        }
        condition = StatementsFactory.eINSTANCE.createCondition();
        condition.setCondition(orExpression);
        Block block = StatementsFactory.eINSTANCE.createBlock();
        condition.setStatement(block);

        for (Variant variant : variationPoint.getVariants()) {
            Condition varCond = createVariantCondition(variant);
            block.getStatements().add(varCond);
        }

        return condition;
    }

    private Condition createVariantCondition(Variant variant) {
        String groupId = variant.getVariationPoint().getGroup().getId();
        Condition varCond = RefactoringUtil.createVariabilityCondition(variant.getId(), groupId);
        Statement statement = (Statement) ((JaMoPPSoftwareElement) variant.getImplementingElements().get(0))
                .getJamoppElement();

        statement = EcoreUtil.copy(statement);

        if (statement instanceof Block) {
            varCond.setStatement(statement);
        } else {
            ((Block) varCond.getStatement()).getStatements().add(statement);
        }

        return varCond;
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
