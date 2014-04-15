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
 * Selector to collect all referenced or referencable elements of a Commentable.
 *
 * <b>Referenceable:</b><br>
 * The commentable itself, or sub-elements that can be referenced by other elements (e.g. declared
 * classes)
 *
 * <b>Referenced:</b><br>
 * Elements referenced by the commentable or by one of it's (sub-)elements and the reference might
 * be shared with other elements. (e.g. a call to method, which might be declared or called as well
 * by other elements)
 */
public interface ReferencedElementSelector {

    /**
     * Get the referencable and referenced (child-) elements of a JaMoPP element.
     *
     * @param commentable
     *            The JaMoPP element to study.
     * @return The list of reasonable elements.
     */
    public abstract List<Commentable> getReferencedElements(Commentable commentable);

}