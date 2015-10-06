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
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.jamopp.refactoring.java.ifelse.util;

import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.StatementsFactory;
import org.splevo.jamopp.vpm.software.JaMoPPJavaSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoringService;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.base.Optional;

/**
 * Provides utility methods for the refactorings.
 */
public class FullyAutomatedIfElseRefactoringUtil implements IfElseRefactoringUtil {

    @Override
    public Condition createVariabilityCondition(VariationPoint vp, String variantId) {
        String groupName = vp.getGroup().getName();
        
        Condition condition = StatementsFactory.eINSTANCE.createCondition();
        condition.setCondition(SPLConfigurationUtil.generateConfigMatchingExpression(variantId, groupName));
        
        Block ifBlock = StatementsFactory.eINSTANCE.createBlock();
        condition.setStatement(ifBlock);

        return condition;
    }

    @Override
    public Optional<Resource> createConfigurationClass(VariationPoint variationPoint, Map<String, Object> refactoringOptions) {
        ResourceSet resourceSet = ((JaMoPPJavaSoftwareElement) variationPoint.getLocation()).getJamoppElement()
                .eResource().getResourceSet();
        String sourcePath = (String) refactoringOptions.get(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY);
        Resource configResource = SPLConfigurationUtil.addConfigurationIfMissing(sourcePath, resourceSet,
                variationPoint);

        return Optional.fromNullable(configResource);
    }

    @Override
    public void createConfigurationClassImport(Commentable vpLocation) {
        CompilationUnit compilationUnit = vpLocation.getContainingCompilationUnit();
        SPLConfigurationUtil.addConfigurationClassImportIfMissing(compilationUnit);
    }
	
}
