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

import org.emftext.language.java.commons.Commentable;

/**
 * A reference between two JaMoPP elements (e.g. a MethodCall referencing a Method).
 *
 * A self reference is possible, if the source element is already a valid element to be referenced
 * by others (e.g. Method -> Method).
 */
public class Reference {

    /** The source. */
    private Commentable source = null;

    /** The target. */
    private Commentable target = null;

    /**
     * Setting the source and target of the reference object.
     *
     * @param source
     *            The element that is referencing.
     * @param target
     *            The element that is referenced.
     */
    public Reference(Commentable source, Commentable target) {
        this.source = source;
        this.target = target;
    }

    /**
     * Update the source of a reference.
     *
     * @param source
     *            The new source.
     */
    public void setSource(Commentable source) {
        this.source = source;
    }

    /**
     * Gets the source.
     *
     * @return the source
     */
    public Commentable getSource() {
        return source;
    }

    /**
     * Gets the target.
     *
     * @return the target
     */
    public Commentable getTarget() {
        return target;
    }

}
