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
package org.splevo.jamopp.vpm.analyzer.programdependency;

import java.util.List;

import org.emftext.language.java.commons.Commentable;

/**
 * Selector to collect all referenced elements of a JaMoPP element (contained by the element or not)
 * and to decide about a reference if it should be ignored or is a valid shared reference to derive
 * a relationship.
 *
 *
 */
public interface ReferenceSelector {

    /**
     * Get the referenced (child-) elements of a JaMoPP element.
     *
     * @param commentable
     *            The JaMoPP element to study.
     * @return The list of reasonable elements.
     */
    public List<Commentable> getReferencedElements(Commentable commentable);

    /**
     * Check if the reference between the two referring and the referenced element should be ignored
     * or not.
     *
     * @param source1
     *            The first referring element.
     * @param source2
     *            The second referring element.
     * @param target
     *            The reference target.
     * @return True if the reference should be ignored, false if it must be considered.
     */
    public boolean ignoreReference(Commentable source1, Commentable source2, Commentable target);

}