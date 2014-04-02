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
package org.splevo.refactoring.java.ifelse;

import java.util.LinkedList;
import java.util.List;

import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.references.StringReference;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementsFactory;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

public class IfElse implements VariabilityRefactoring {

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
		// Initialization
		ClassMethod leadingMethod = (ClassMethod) ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
		Variant leadingVariant = getLeadingVariant(variationPoint);
		List<Variant> integrationVariants = getIntegrationVariants(variationPoint);
		
		// TODO: 1
//		leadingMethod.getClassClass().addImport("org.splevo.spl.Config");
		
		// 2+3
		Integer indexLeadingStatementsInMethod = null;
		for(SoftwareElement se : leadingVariant.getImplementingElements()){
			Statement variantStatement = (Statement) ((JaMoPPSoftwareElement) se).getJamoppElement();
			int index = leadingMethod.getStatements().indexOf(variantStatement);
			if(index != -1){
				if(indexLeadingStatementsInMethod == null) {
					indexLeadingStatementsInMethod = index;
				}
				leadingMethod.getStatements().remove(variantStatement);
			}
		}
		
		// 4
		Condition leadingCondition = StatementsFactory.eINSTANCE.createCondition();
		Block ifBlock = StatementsFactory.eINSTANCE.createBlock();
		leadingCondition.setStatement(ifBlock);
		leadingMethod.getStatements().add(indexLeadingStatementsInMethod, leadingCondition);

		// 5
		StringReference leadingStringreference = setUpCondition(variationPoint, leadingVariant);
		leadingCondition.setCondition(leadingStringreference);
		
		// 6
		filllBlockWithStatementsFromVariant(leadingVariant, ifBlock);
		
		// 7
		Condition previousCondition = leadingCondition;
		for (Variant variant : integrationVariants) {
			// 7a + 7b
			Condition currentLoopCondition = StatementsFactory.eINSTANCE.createCondition();
			previousCondition.setElseStatement(currentLoopCondition);
			Block currentLoopIfBlock = StatementsFactory.eINSTANCE.createBlock();
			currentLoopCondition.setStatement(currentLoopIfBlock);
			
			// 7c
			StringReference currentStringreference = setUpCondition(variationPoint, variant);
			currentLoopCondition.setCondition(currentStringreference);
			
			// 7d
			filllBlockWithStatementsFromVariant(variant, currentLoopIfBlock);
		}
	}
	
	@Override
	public boolean canBeAppliedTo(VariationPoint variationPoint) {
		Commentable jamoppElement = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
		if (jamoppElement instanceof ClassMethod) {
			return variationPoint.getVariants().size() >= 2; 
		}
		
		return false;
	}
	
	@Override
	public String getId() {
		return REFACTORING_ID;
	}

	private StringReference setUpCondition(VariationPoint variationPoint,
			Variant leadingVariant) {
		StringReference stringReference = ReferencesFactory.eINSTANCE.createStringReference();
		stringReference.setValue(leadingVariant.getVariantId());
		Method stringEqualsMethod = MembersFactory.eINSTANCE.createClassMethod();
		stringEqualsMethod.setName("equals");
		
		Class configurationAccessClass = ClassifiersFactory.eINSTANCE.createClass();
		configurationAccessClass.setName("Config");
		Method getConfigMethod = MembersFactory.eINSTANCE.createClassMethod();
		getConfigMethod.setName("getConfig");
		configurationAccessClass.getMembers().add(getConfigMethod);
		IdentifierReference identifierReference = ReferencesFactory.eINSTANCE.createIdentifierReference();
		identifierReference.setTarget(configurationAccessClass);
		MethodCall getConfigMethodCall = ReferencesFactory.eINSTANCE.createMethodCall();
		StringReference variationPointIDStringReference = ReferencesFactory.eINSTANCE.createStringReference();
		variationPointIDStringReference.setValue(variationPoint.getGroup().getGroupId());
		getConfigMethodCall.getArguments().add(variationPointIDStringReference);
		getConfigMethodCall.setTarget(getConfigMethod);
		identifierReference.setNext(getConfigMethodCall);
		
		MethodCall methodCall = ReferencesFactory.eINSTANCE.createMethodCall();
		methodCall.setTarget(stringEqualsMethod);
		methodCall.getArguments().add(identifierReference);
		stringReference.setNext(methodCall);
		return stringReference;
	}
	
	private void filllBlockWithStatementsFromVariant(Variant variant,
			Block ifBlock) {
		for(SoftwareElement se : variant.getImplementingElements()){
			Statement variantStatement = (Statement) ((JaMoPPSoftwareElement) se).getJamoppElement();
			ifBlock.getStatements().add(variantStatement);
		}
	}

	private List<Variant> getIntegrationVariants(VariationPoint variationPoint) {
		List<Variant> result = new LinkedList<Variant>();
		
		for (Variant variant : variationPoint.getVariants()) {
			if(!variant.getLeading()) {
				result.add(variant);
			}
		}
		
		return result;
	}

	private Variant getLeadingVariant(VariationPoint variationPoint) {
		for (Variant variant : variationPoint.getVariants()) {
			if(variant.getLeading()) {
				return variant;
			}
		}
		
		return null;
	}

}
