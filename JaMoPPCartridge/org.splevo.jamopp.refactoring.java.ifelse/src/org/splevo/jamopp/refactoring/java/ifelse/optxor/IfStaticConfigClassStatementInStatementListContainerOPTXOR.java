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
 *    Stephan Seifermann - restore VPM validity after refactoring
 *******************************************************************************/
package org.splevo.jamopp.refactoring.java.ifelse.optxor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Return;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.Void;
import org.emftext.language.java.variables.LocalVariable;
import org.splevo.jamopp.refactoring.java.JaMoPPFullyAutomatedVariabilityRefactoring;
import org.splevo.jamopp.refactoring.java.ifelse.util.FullyAutomatedIfElseRefactoringUtil;
import org.splevo.jamopp.refactoring.java.ifelse.util.IfElseRefactoringUtil;
import org.splevo.jamopp.refactoring.java.ifelse.util.SPLConfigurationUtil;
import org.splevo.jamopp.refactoring.java.ifelse.util.VariabilityPositionUtil;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.vpm.software.JaMoPPJavaSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoringService;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;

/**
 * Introduces If-Else Statements that check the configuration in the
 * If-condition to decide which variant's statements to execute. Common
 * variables with equal types will be merged. In case of common variables with
 * different types, the whole method gets extracted into variant-specific
 * methods. Throws an exception if no variant was selected in the configuration.
 */
public class IfStaticConfigClassStatementInStatementListContainerOPTXOR extends
		JaMoPPFullyAutomatedVariabilityRefactoring {

	private static final String REFACTORING_NAME = "IF with Static Configuration Class (OPTXOR): Statement in StatementListContainer";
	private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.optxor.IfStaticConfigClassStatementOPTXOR";

	private IfElseRefactoringUtil ifElseRefactoringUtil = null;
	
	public IfStaticConfigClassStatementInStatementListContainerOPTXOR(IfElseRefactoringUtil ifElseRefactoringUtil) {
		this.ifElseRefactoringUtil = ifElseRefactoringUtil;
	}
	
	@Override
	public VariabilityMechanism getVariabilityMechanism() {
		VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE
				.createVariabilityMechanism();
		variabilityMechanism.setName(REFACTORING_NAME);
		variabilityMechanism.setRefactoringID(REFACTORING_ID);
		return variabilityMechanism;
	}

	@Override
	protected List<Resource> refactorFullyAutomated(
			VariationPoint variationPoint,
			Map<String, Object> refactoringOptions) {
		StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPJavaSoftwareElement) variationPoint
				.getLocation()).getJamoppElement();

		if (this.ifElseRefactoringUtil instanceof FullyAutomatedIfElseRefactoringUtil) {
			CompilationUnit compilationUnit = vpLocation
					.getContainingCompilationUnit();
			SPLConfigurationUtil
					.addConfigurationClassImportIfMissing(compilationUnit);
		}
	
		int variabilityPositionStart = VariabilityPositionUtil
				.getVariabilityPosition(variationPoint);
		int variabilityPositionEnd = variabilityPositionStart;

		Map<String, LocalVariableStatement> localVariableStatements = new HashMap<String, LocalVariableStatement>();

		for (Variant variant : variationPoint.getVariants()) {
			Condition variantCondition = generateVariantCondition(variant,
					localVariableStatements);
			vpLocation.getStatements().add(variabilityPositionEnd++,
					variantCondition);
		}

		RefactoringUtil.deleteVariableStatements(variationPoint);

		vpLocation.getStatements().addAll(variabilityPositionStart,
				localVariableStatements.values());

		if (vpLocation instanceof ClassMethod) {
			addMandatoryReturnIfNecessary(variationPoint);
		}

		ArrayList<Resource> resourceList = Lists.newArrayList(vpLocation
				.eResource());
		
		if (this.ifElseRefactoringUtil instanceof FullyAutomatedIfElseRefactoringUtil) {
        	ResourceSet resourceSet = ((JaMoPPJavaSoftwareElement) variationPoint.getLocation()).getJamoppElement().eResource()
                    .getResourceSet();
            String sourcePath = (String) refactoringOptions.get(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY);
            Resource configResource = SPLConfigurationUtil.addConfigurationIfMissing(sourcePath, resourceSet, variationPoint);
            
            if (configResource != null) {
                resourceList.add(configResource);
            }
        }
		
	      return resourceList;
	}

    @Override
    public Diagnostic canBeAppliedTo(VariationPoint variationPoint) {
        Commentable vpLocation = ((JaMoPPJavaSoftwareElement) variationPoint.getLocation()).getJamoppElement();

        boolean correctLocation = vpLocation instanceof StatementListContainer;
        boolean allImplementingElementsAreStatements = RefactoringUtil.allImplementingElementsOfType(variationPoint,
                Statement.class);
        boolean correctInput = correctLocation && allImplementingElementsAreStatements;

        String error = "If with Static Configuration Class Statement in Statmentlist (OPTXOR): ";
        
        if (!correctInput) {
            return new BasicDiagnostic(Diagnostic.ERROR, null, 0, error + "Wrong Input", null);
        }

        boolean hasConflictingVariables = RefactoringUtil.hasConflictingLocalVariables(variationPoint);
        boolean hasConstructorCalls = RefactoringUtil.hasConstructorCalls(variationPoint);
        
        if (hasConflictingVariables) {
            return new BasicDiagnostic(Diagnostic.ERROR, null, 0, error + "Has Conflicting Variables", null);   
        }
        if (hasConstructorCalls) {
            return new BasicDiagnostic(Diagnostic.ERROR, null, 0, error + "Has Constructor Calls", null);
        }
        
        return new BasicDiagnostic(Diagnostic.OK, null, 0, "OK", null);
    }

	private Condition generateVariantCondition(Variant variant,
			Map<String, LocalVariableStatement> localVariableStatements) {
		VariationPoint variationPoint = variant.getVariationPoint();

		String variantId = variant.getId();
		String groupName = variationPoint.getGroup().getName();

		Condition currentCondition = this.ifElseRefactoringUtil
				.createVariabilityCondition(variantId, groupName);

		for (SoftwareElement se : variant.getImplementingElements()) {
			Statement originalStatement = (Statement) ((JaMoPPJavaSoftwareElement) se)
					.getJamoppElement();
			Statement statement = clone(originalStatement);

			int offset = variant.getImplementingElements().size()
					- variant.getImplementingElements().indexOf(se);

			if (statement instanceof LocalVariableStatement
					&& RefactoringUtil.isReferencedByPostdecessor(
							(LocalVariableStatement) originalStatement, offset)) {
				LocalVariableStatement localVariableStatement = (LocalVariableStatement) statement;
				LocalVariable variable = localVariableStatement.getVariable();

				RefactoringUtil.removeFinalIfApplicable(variable);

				Statement oldStatement = statement;
				statement = RefactoringUtil.extractAssignment(variable);
				registerReplacement(oldStatement, statement);
				Type variableType = variable.getTypeReference().getTarget();
				variable.setInitialValue(RefactoringUtil
						.getDefaultValueForType(variableType));

				if (!localVariableStatements.containsKey(variable.getName())
						|| variant.getLeading()) {
					localVariableStatements.put(variable.getName(),
							localVariableStatement);
				}
			}

			RefactoringUtil
					.initializeAndRemoveFinalForReferencedVariables(statement);

			if (statement != null) {
				((Block) currentCondition.getStatement()).getStatements().add(
						statement);
			}
		}

		return currentCondition;
	}

	private void addMandatoryReturnIfNecessary(VariationPoint variationPoint) {
		StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPJavaSoftwareElement) variationPoint
				.getLocation()).getJamoppElement();

		Type methodReturnType = ((ClassMethod) vpLocation).getTypeReference()
				.getTarget();

		boolean isVoid = methodReturnType instanceof Void;
		boolean allVariantsHaveReturn = allVariantsHaveAReturn(variationPoint);

		if (!isVoid && allVariantsHaveReturn) {
			RefactoringUtil.addReturnStatement((ClassMethod) vpLocation);
		}
	}

	private boolean allVariantsHaveAReturn(VariationPoint variationPoint) {
		for (Variant variant : variationPoint.getVariants()) {
			SoftwareElement se = variant.getImplementingElements().get(
					variant.getImplementingElements().size() - 1);
			Commentable jamoppElement = ((JaMoPPJavaSoftwareElement) se)
					.getJamoppElement();
			if (!(jamoppElement instanceof Return)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String getId() {
		return REFACTORING_ID;
	}

}
