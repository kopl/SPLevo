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

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.Commentable;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.refactoring.FullyAutomatedVariabilityRefactoring;
import org.splevo.vpm.software.SoftwareElement;

/**
 * Fully-automated variability refactoring for JaMoPP refactorings.
 */
public abstract class JaMoPPFullyAutomatedVariabilityRefactoring extends FullyAutomatedVariabilityRefactoring {

    private static final Logger LOGGER = Logger.getLogger(JaMoPPFullyAutomatedVariabilityRefactoring.class);

    @Override
    protected SoftwareElement createSoftwareElement(EObject eobject) {
        if (!(eobject instanceof Commentable)) {
            LOGGER.error("The given EObject is no Commentable, but a " + eobject.getClass().getSimpleName() + ".");
            return null;
        }
        Commentable element = (Commentable) eobject;

        final String elementID = RefactoringUtil.addCommentableSoftwareElementReference(element);
        return RefactoringUtil.createCommentableSoftwareElement(element, elementID);
    }

}
