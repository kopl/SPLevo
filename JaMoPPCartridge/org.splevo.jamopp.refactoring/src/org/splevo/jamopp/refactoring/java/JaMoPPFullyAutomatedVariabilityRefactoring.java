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

import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.Commentable;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.jamopp.vpm.software.softwareFactory;
import org.splevo.refactoring.FullyAutomatedVariabilityRefactoring;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

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
        
        JaMoPPSoftwareElement jse = softwareFactory.eINSTANCE.createJaMoPPSoftwareElement();
        jse.setJamoppElement(element);
        return jse;
    }

    @Override
    protected void executeReplacement(Entry<EObject, EObject> replacement, VariationPoint variationPoint) {
        // collect all possible replacement targets
        Iterable<SoftwareElement> softwareElements = Iterables.concat(Iterables.transform(variationPoint.getVariants(),
                new Function<Variant, Iterable<SoftwareElement>>() {
                    @Override
                    public Iterable<SoftwareElement> apply(Variant input) {
                        return input.getImplementingElements();
                    }
                }));

        // execute the replacement or fall back to the default implementation.
        if (!executeReplacement(replacement, softwareElements)) {
            super.executeReplacement(replacement, variationPoint);
        }
    }

    private boolean executeReplacement(Entry<EObject, EObject> replacement, Iterable<SoftwareElement> softwareElements) {
        // ensure that we can handle the replacement
        if (!(replacement.getKey() instanceof Commentable && replacement.getValue() instanceof Commentable)) {
            return false;
        }

        // execute the replacement if the software element is a JaMoPPSoftwareElement
        for (SoftwareElement se : softwareElements) {
            if (se.getWrappedElement() == replacement.getKey()) {
                if (!(se instanceof JaMoPPSoftwareElement)) {
                    return false;
                }
                LOGGER.debug(String.format("Replaced %s with %s.", replacement.getKey(), replacement.getValue()));
                JaMoPPSoftwareElement jse = (JaMoPPSoftwareElement) se;
                jse.setJamoppElement((Commentable) replacement.getValue());
            }
        }

        return true;
    }

}
