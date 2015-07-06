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

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.Commentable;
import org.splevo.jamopp.refactoring.JaMoPPTodoTagCustomizer;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.refactoring.SemiAutomatedVariabilityRefactoring;

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

}
