/*******************************************************************************
 * Copyright (c) 2013
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.jamopp.vpm.analyzer.programdependency.references;

import java.util.ArrayList;
import java.util.List;

import org.emftext.language.java.commons.Commentable;
import org.splevo.jamopp.util.JaMoPPElementUtil;
import org.splevo.vpm.analyzer.VPMAnalyzerUtil;

/**
 * A provider for the program structure analyzer to return information specific for JaMoPPSoftware
 * elements
 *
 */
public class DefaultReferenceSelector implements ReferenceSelector {

    private DefaultReferenceSelectorSwitch referencedElementsSwitch = new DefaultReferenceSelectorSwitch();

    /**
     * Get the referenced elements for a given JaMoPP element according to the SPLevo default
     * strategy.
     *
     * {@inheritDoc}
     */
    @Override
    public List<Commentable> getReferencedElements(Commentable commentable) {

        if (VPMAnalyzerUtil.isNullOrProxy(commentable)) {
            return new ArrayList<Commentable>();
        }

        List<Commentable> referencedElements = referencedElementsSwitch.doSwitch(commentable);

        for (Commentable element : referencedElements) {
            if (VPMAnalyzerUtil.isNullOrProxy(element)) {
                referencedElements.remove(element);
            }
        }

        return referencedElements;
    }

    /**
     * Check if the reference between the two referring and the referenced element should be ignored
     * or not.
     *
     * If one of the source elements specifies the target, the reference must be included.
     *
     * @param source1
     *            The first referring element.
     * @param source2
     *            The second referring element.
     * @param target
     *            The reference target.
     * @return True if the reference should be ignored, false if it must be considered.
     */
    public boolean ignoreReference(Commentable source1, Commentable source2, Commentable target) {

        if (source1 == source2) {
            return true;
        }

        if (JaMoPPElementUtil.isParentOf(source1, target)) {
            return false;
        }
        if (JaMoPPElementUtil.isParentOf(source2, target)) {
            return false;
        }

        // TODO Implement ignore check
//        boolean filterSharedVariable = true;
//        if (filterSharedVariable) {
//
//        }

        return false;
    }

}
