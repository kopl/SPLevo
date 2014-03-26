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
 *******************************************************************************/
package org.splevo.jamopp.refactoring.refactory.ifelse.vp;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.refactoring.rolemapping.RoleMapping;
import org.emftext.refactoring.interpreter.IRefactorer;
import org.emftext.refactoring.interpreter.RefactorerFactory;
import org.splevo.jamopp.refactoring.refactory.ifelse.IfElseVariabilityRealizationTechnique;
import org.splevo.jamopp.refactoring.refactory.ifelse.RefactoryUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vrm.VariabilityRealizationTechnique;

/**
 * Refactoring a variation point into a single code base aligned with the leading variant using if
 * else condition of the given java code base.
 */
public class IfElseVPRefactoring implements VariabilityRefactoring {

    private static Logger logger = Logger.getLogger(IfElseVPRefactoring.class);

    @Override
    public void refactor(VariationPoint variationPoint) {
        Resource javaResource = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement().eResource();
        RoleMapping roleMapping = RefactoryUtil.getRoleMapping("IfElseBasic");
        IRefactorer refactorer = RefactorerFactory.eINSTANCE.getRefactorer(javaResource, roleMapping);

        List<EObject> inputList = new LinkedList<EObject>();
        inputList.add(variationPoint);
        for(Variant variant : variationPoint.getVariants()) {
            if(variant.getLeading()) {
                inputList.add(variant);
            }
        }
        refactorer.setInput(inputList);

        refactorer.refactor();
        if (refactorer.didErrorsOccur()) {
            logger.error("Errow while refactoring: " + refactorer.getStatus());
        }
    }


    @Override
    public VariabilityRealizationTechnique getSupportedVariabilityRealizationTechnique() {
        return new IfElseVariabilityRealizationTechnique();
    }

    @Override
    public String getLabel() {
        return "IfElse Basic Refactoring";
    }

    @Override
    public String getId() {
        return "org.splevo.refactoring.ifelsebasic";
    }

}
