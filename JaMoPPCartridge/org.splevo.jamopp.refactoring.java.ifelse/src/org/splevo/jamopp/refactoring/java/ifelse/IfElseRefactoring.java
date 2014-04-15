/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic
 *******************************************************************************/
package org.splevo.jamopp.refactoring.java.ifelse;

import java.util.LinkedList;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.statements.StatementsFactory;
import org.splevo.jamopp.refactoring.util.SPLConfigurationUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;

/**
 * Refactoring a variation point into a single code base aligned with the leading variant using if
 * else condition of the given java code base.
 */
public class IfElseRefactoring implements VariabilityRefactoring {

    private static final String REFACTORING_ID = "org.splevo.refactoring.java.ifelse";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName("ifelse");
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public void refactor(VariationPoint variationPoint) {
        Commentable jamoppElement = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        if (jamoppElement instanceof CompilationUnit) {
            CompilationUnit compilationUnit = (CompilationUnit) jamoppElement;
            for (Variant variant : variationPoint.getVariants()) {
                for (SoftwareElement se : variant.getImplementingElements()) {
                    Commentable currentJamoppElement = ((JaMoPPSoftwareElement) se).getJamoppElement();
                    EList<Import> leadingImports = compilationUnit.getImports();
                    if (currentJamoppElement instanceof Import && !(leadingImports.contains(currentJamoppElement))) {
                        leadingImports.add((Import) currentJamoppElement);
                    }
                }
            }
            return;
        }

        StatementListContainer leadingMethod = (StatementListContainer) jamoppElement;
        leadingMethod.getContainingCompilationUnit().getImports().add(SPLConfigurationUtil.getRequiringImport());

        Condition previousCondition = null;
        for (Variant variant : variationPoint.getVariants()) {
            int indexVariantPositionInMethod = getIndexOfStatementInMethod(variant);

            if (variant.getLeading()) {
                for (SoftwareElement se : variant.getImplementingElements()) {
                    Statement variantStatement = (Statement) ((JaMoPPSoftwareElement) se).getJamoppElement();
                    leadingMethod.getStatements().remove(variantStatement);
                }
            }

            Condition currentCondition = StatementsFactory.eINSTANCE.createCondition();
            currentCondition.setCondition(SPLConfigurationUtil.generateVariantMatchingExpression(variationPoint
                    .getGroup().getGroupId(), variant.getVariantId()));
            Block currentBlock = StatementsFactory.eINSTANCE.createBlock();
            currentCondition.setStatement(currentBlock);

            if (previousCondition == null) {
                leadingMethod.getStatements().add(indexVariantPositionInMethod, currentCondition);
            } else {
                previousCondition.setElseStatement(currentCondition);
            }
            previousCondition = currentCondition;
            filllBlockWithStatementsFromVariant(variant, currentBlock);
        }
    }

    private int getIndexOfStatementInMethod(Variant variant) {
        JaMoPPSoftwareElement softwareElement = (JaMoPPSoftwareElement) variant.getImplementingElements().get(0);
        Commentable jamoppElement = softwareElement.getJamoppElement();
        EObject container = jamoppElement;
        while (container != null) {
            if (container.eContainer() instanceof StatementListContainer) {
                return ((StatementListContainer) container.eContainer()).getStatements().indexOf(container);
            }
            container = ((Commentable) container).eContainer();
        }

        return -1;
    }

    @Override
    public boolean canBeAppliedTo(VariationPoint variationPoint) {
        Commentable jamoppElement = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        if (variationPoint.getVariants().size() == 0) {
            return false;
        }

        if (jamoppElement instanceof StatementListContainer) {
            for (Variant variant : variationPoint.getVariants()) {
                for (SoftwareElement se : variant.getImplementingElements()) {
                    if (se instanceof JaMoPPSoftwareElement) {
                        continue;
                    }
                    return false;
                }
            }
        }

        if (jamoppElement instanceof CompilationUnit) {
            LinkedList<SoftwareElement> elementsToCheckForType = Lists.newLinkedList();

            for (Variant variant : variationPoint.getVariants()) {
                elementsToCheckForType.addAll(variant.getImplementingElements());
            }

            for (SoftwareElement softwareElement : elementsToCheckForType) {
                if (softwareElement instanceof JaMoPPSoftwareElement
                        || ((JaMoPPSoftwareElement) softwareElement).getJamoppElement() instanceof Import) {
                    continue;
                }
                return false;
            }
        }

        return true;
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

    private void filllBlockWithStatementsFromVariant(Variant variant, Block ifBlock) {
        for (SoftwareElement se : variant.getImplementingElements()) {
            Statement variantStatement = (Statement) ((JaMoPPSoftwareElement) se).getJamoppElement();
            ifBlock.getStatements().add(variantStatement);
        }
    }
}
