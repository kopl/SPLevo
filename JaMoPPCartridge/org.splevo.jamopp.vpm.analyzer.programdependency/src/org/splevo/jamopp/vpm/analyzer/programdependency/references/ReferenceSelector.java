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
package org.splevo.jamopp.vpm.analyzer.programdependency.references;

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
    public List<Reference> getReferencedElements(Commentable commentable);

    /**
     * Get the identifier for the type of dependency derived from the given references.
     *
     * If the given references do not represent a valid dependency null is returned.
     *
     * @param reference1
     *            The first reference to the target element.
     * @param reference2
     *            The second reference to the target element.
     * @param target
     *            The referenced element.
     * @return The type of the identified dependency.
     */
    public DependencyType getDependencyType(Reference reference1, Reference reference2, Commentable target);

}