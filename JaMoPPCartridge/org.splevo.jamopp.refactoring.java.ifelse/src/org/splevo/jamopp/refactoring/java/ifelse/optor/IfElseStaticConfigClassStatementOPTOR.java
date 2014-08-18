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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Return;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.refactoring.util.SPLConfigurationUtil;
import org.splevo.jamopp.refactoring.util.VariabilityPositionUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;

/**
 * Introduces If-Else Statements that check the configuration in the If-condition to decide which
 * variant's statements to execute. Common variables with equal types will be merged. In case of
 * common variables with different types, the whole method gets extracted into variant-specific
 * methods. Throws an exception if no variant was selected in the configuration.
 */
public class IfElseStaticConfigClassStatementOPTOR implements VariabilityRefactoring {

    private static final String REFACTORING_NAME = "IF-Else with Static Configuration Class (OPTOR): Statement";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.optor.IfElseStaticConfigClassStatementOPTOR";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public List<Resource> refactor(VariationPoint variationPoint, Map<String, String> refactoringOptions) {
        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPSoftwareElement) variationPoint
                .getLocation()).getJamoppElement();

        ClassifierImport splConfImport = SPLConfigurationUtil.getSPLConfigClassImport();
        CompilationUnit containingCompilationUnit = vpLocation.getContainingCompilationUnit();
        if (containingCompilationUnit != null
                && !RefactoringUtil.containsImport(containingCompilationUnit, splConfImport)) {
            containingCompilationUnit.getImports().add(splConfImport);
        }

        Map<String, LocalVariableStatement> localVariableStatements = new HashMap<String, LocalVariableStatement>();
        EList<Variant> variants = variationPoint.getVariants();

        int variabilityPositionStart = VariabilityPositionUtil.getVariabilityPosition(variationPoint);
        int variabilityPositionEnd = variabilityPositionStart;

        EList<Statement> vpLocationStatements = vpLocation.getStatements();

        // warmup references
        for (Variant variant : variants) {
            for(SoftwareElement element : variant.getImplementingElements()) {
                EcoreUtil.resolveAll(element);
            }
        }
        for(Statement statement : vpLocationStatements) {
            EcoreUtil.resolveAll(statement);
        }

        for (Variant variant : variants) {
            Condition currentCondition = RefactoringUtil.generateVariantCondition(variant, localVariableStatements);

            if (variant.getLeading()) {
                RefactoringUtil.deleteVariableStatements(variationPoint);
            }

            vpLocationStatements.add(variabilityPositionEnd++, currentCondition);
        }

        for (String key : localVariableStatements.keySet()) {
            vpLocationStatements.add(variabilityPositionStart++, localVariableStatements.get(key));
        }

        if (vpLocation instanceof ClassMethod) {
            boolean isVoid = ((ClassMethod) vpLocation).getTypeReference().getTarget() instanceof org.emftext.language.java.types.Void;
            boolean allVariantsHaveReturn = allVariantsHaveAReturn(variationPoint);

            if (!isVoid && allVariantsHaveReturn) {
                RefactoringUtil.addReturnStatement((ClassMethod) vpLocation);
            }

        }

        return Lists.newArrayList(vpLocation.eResource());
    }

    private boolean allVariantsHaveAReturn(VariationPoint variationPoint) {
        for (Variant variant : variationPoint.getVariants()) {
            SoftwareElement se = variant.getImplementingElements().get(variant.getImplementingElements().size() - 1);
            Commentable jamoppElement = ((JaMoPPSoftwareElement) se).getJamoppElement();
            if (!(jamoppElement instanceof Return)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean canBeAppliedTo(VariationPoint variationPoint) {
        Commentable vpLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();

        boolean correctLocation = vpLocation instanceof StatementListContainer;
        boolean allImplementingElementsAreStatements = RefactoringUtil.allImplementingElementsOfType(variationPoint,
                Statement.class);
        boolean correctInput = correctLocation && allImplementingElementsAreStatements;

        if (!correctInput) {
            return false;
        }

        boolean hasConflictingVariables = RefactoringUtil.hasConflictingLocalVariables(variationPoint);
        boolean hasConstructorCalls = RefactoringUtil.hasConstructorCalls(variationPoint);

        return !hasConflictingVariables && !hasConstructorCalls;
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

}
