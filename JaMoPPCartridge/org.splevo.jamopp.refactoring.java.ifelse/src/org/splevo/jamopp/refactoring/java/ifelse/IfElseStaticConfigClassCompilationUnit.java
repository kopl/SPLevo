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
package org.splevo.jamopp.refactoring.java.ifelse;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.refactoring.VariabilityRefactoringService;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * The SPL must integrate the compilation units from all variants. This refactoring merges the
 * compilation units from the integration projects into the leading project.
 */
public class IfElseStaticConfigClassCompilationUnit implements VariabilityRefactoring {

    private static final String REFACTORING_NAME = "IF-Else with Static Configuration Class: CompilationUnit";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.optor.IfElseStaticConfigClassCompilationUnit";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public List<Resource> refactor(VariationPoint variationPoint, Map<String, String> refactoringConfigurations) {
        if (refactoringConfigurations == null
                || !refactoringConfigurations.containsKey(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY)) {
            throw new IllegalArgumentException("JAVA_SOURCE_DIRECTORY configuration not found.");
        }

        String sourcePath = refactoringConfigurations.get(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY);

        ResourceSet resourceSet = new ResourceSetImpl();
        for (Variant variant : variationPoint.getVariants()) {
            if (variant.getLeading()) {
                continue;
            }
            for (SoftwareElement se : variant.getImplementingElements()) {
                CompilationUnit compilationUnit = (CompilationUnit) ((JaMoPPSoftwareElement) se).getJamoppElement();
                wrapCompUnitInNewResourceSet(resourceSet, compilationUnit, sourcePath);
            }
        }

        return resourceSet.getResources();
    }

    private void wrapCompUnitInNewResourceSet(ResourceSet rs, CompilationUnit compilationUnit, String sourcePath) {
        StringBuilder sb = buildPathForLeadingProject(compilationUnit, sourcePath);
        URI leadingURI = URI.createFileURI(sb.toString());

        Resource resource = rs.createResource(leadingURI);
        resource.getContents().add(compilationUnit);
    }

    private StringBuilder buildPathForLeadingProject(CompilationUnit compilationUnit, String sourcePath) {
        StringBuilder sb = new StringBuilder();
        sb.append(sourcePath);
        if (!sourcePath.endsWith(File.separator)) {
            sb.append(File.separator);
        }

        for (String segment : compilationUnit.getNamespaces()) {
            sb.append(segment + File.separator);
        }

        String compNameWithoutNamespace = compilationUnit.getName().substring(
                compilationUnit.getNamespacesAsString().length());
        sb.append(compNameWithoutNamespace);
        return sb;
    }

    @Override
    public boolean canBeAppliedTo(VariationPoint variationPoint) {
        Commentable jamoppElement = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();

        boolean correctLocation = jamoppElement instanceof CompilationUnit;
        boolean allImplementingElementsAreClasses = RefactoringUtil.allImplementingElementsOfType(variationPoint,
                CompilationUnit.class);

        return correctLocation && allImplementingElementsAreClasses;
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }
}
