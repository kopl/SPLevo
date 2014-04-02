/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *    Daniel Kojic
 *******************************************************************************/
package org.splevo.jamopp.refactoring.refactory.ifelse;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.refactoring.rolemapping.RoleMapping;
import org.emftext.refactoring.interpreter.IRefactorer;
import org.emftext.refactoring.interpreter.RefactorerFactory;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.refactoring.refactory.util.RefactoryUtil;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Refactoring a variation point into a single code base aligned with the leading variant using if
 * else condition of the given java code base.
 */
public class IfElseRefactoring implements VariabilityRefactoring {

    private static final String REFACTORING_ID = "org.splevo.refactoring.refactory.ifelse";
	private static Logger logger = Logger.getLogger(IfElseRefactoring.class);

    @Override
    public void refactor(VariationPoint variationPoint) {
        Resource javaResource = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement().eResource();
        RoleMapping roleMapping = RefactoryUtil.getRoleMapping("IfElseBasic");
        IRefactorer refactorer = RefactorerFactory.eINSTANCE.getRefactorer(javaResource, roleMapping);
        ClassMethod leadingMethod = (ClassMethod) ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        List<EObject> integrationStatements = getVariantElements(variationPoint);

        List<EObject> inputList = new LinkedList<EObject>();
        inputList.add(leadingMethod);
        inputList.addAll(integrationStatements);
        refactorer.setInput(inputList);

        refactorer.refactor();
        if (refactorer.didErrorsOccur()) {
            logger.error("Errow while refactoring: " + refactorer.getStatus());
        }
    }

    private List<EObject> getVariantElements(VariationPoint variationPoint) {
        List<EObject> result = new LinkedList<EObject>();
        for (SoftwareElement softwareElement : variationPoint.getVariants().get(1).getImplementingElements()) {
            JaMoPPSoftwareElement jaMoPPSoftwareElement = (JaMoPPSoftwareElement) softwareElement;
            result.add(jaMoPPSoftwareElement.getJamoppElement());
        }

        return result;
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

	@Override
	public VariabilityMechanism getVariabilityMechanism() {
		VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
		variabilityMechanism.setName("ifelse");
		variabilityMechanism.setRefactoringID(REFACTORING_ID);
		return variabilityMechanism;
	}

	@Override
	public boolean canBeAppliedTo(VariationPoint variationPoint) {
		Commentable jamoppElement = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
		if (jamoppElement instanceof ClassMethod) {
			return variationPoint.getVariants().size() >= 2; 
		}
		
		return false;
	}

}
