/*******************************************************************************
 * Copyright (c) 2015
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.jamopp.refactoring.java;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.commons.Commentable;
import org.splevo.jamopp.refactoring.JaMoPPTodoTagCustomizer;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.refactoring.SemiAutomatedVariabilityRefactoring;
import org.splevo.refactoring.VariabilityRefactoringFailedException;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Base class for semi-automated variability refactorings in JaMoPP.
 */
public abstract class JaMoPPSemiAutomatedVariabilityRefactoring extends SemiAutomatedVariabilityRefactoring {

    @Override
    protected void addCommentToElement(EObject element, String variationPointId) {
        if (!(element instanceof Commentable)) {
            return;
        }
        String commentText = String.format("%s %s", JaMoPPTodoTagCustomizer.getTodoTaskTag(), variationPointId);
        RefactoringUtil.addCommentBefore((Commentable) element, commentText);
    }

    /* (non-Javadoc)
     * @see org.splevo.refactoring.SemiAutomatedVariabilityRefactoring#startManualRefactoring(org.splevo.vpm.variability.VariationPoint, java.util.Map)
     */
    @Override
    public List<Resource> startLanguageSpecificManualRefactoring(VariationPoint variationPoint,
            Map<String, Object> refactoringConfigurations) throws VariabilityRefactoringFailedException {
        List<Resource> changedResources = startManualRefactoringInternal(variationPoint, refactoringConfigurations);
        RefactoringUtil.deleteCommentFrom(variationPoint);
        return changedResources;
    }
    
    protected abstract List<Resource> startManualRefactoringInternal(VariationPoint variationPoint,
            Map<String, Object> refactoringConfigurations) throws VariabilityRefactoringFailedException;

}
