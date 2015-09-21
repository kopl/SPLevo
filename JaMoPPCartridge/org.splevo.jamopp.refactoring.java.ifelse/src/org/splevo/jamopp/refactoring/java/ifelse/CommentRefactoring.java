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

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.commons.Commentable;
import org.splevo.jamopp.refactoring.java.JaMoPPFullyAutomatedVariabilityRefactoring;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.util.JaMoPPElementUtil;
import org.splevo.jamopp.vpm.software.JaMoPPJavaSoftwareElement;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;

/**
 * This refactoring annotates a variation point's location with a fixme comment with the following
 * content: "Variability could not be handled". This refactoring can be used to annotate variation
 * points that cannot be processed.
 */
public class CommentRefactoring extends JaMoPPFullyAutomatedVariabilityRefactoring {

    private static final String REFACTORING_NAME = "Comment Refactoring";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.CommentRefactoring";
    private static final String COMMENT_TEXT = "FIXME: Variability could not be handled\n";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    protected List<Resource> refactorFullyAutomated(VariationPoint variationPoint, Map<String, Object> refactoringOptions) {
        Commentable vpLocation = ((JaMoPPJavaSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        StringBuilder sb = new StringBuilder();
        sb.append(COMMENT_TEXT + "\n");
        for (Variant variant : variationPoint.getVariants()) {
            sb.append("Variant: " + variant.getId() + "\n");
            for (SoftwareElement se : variant.getImplementingElements()) {
                String label = JaMoPPElementUtil.getLabel(((JaMoPPJavaSoftwareElement) se).getJamoppElement());
                sb.append(label + "\n");
            }
        }
        RefactoringUtil.addCommentBefore(vpLocation, COMMENT_TEXT);

        return Lists.newArrayList(vpLocation.eResource());
    }

    @Override
    public Diagnostic canBeAppliedTo(VariationPoint variationPoint) {
        return new BasicDiagnostic(Diagnostic.OK, null, 0, "Allright", null);
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }
}
