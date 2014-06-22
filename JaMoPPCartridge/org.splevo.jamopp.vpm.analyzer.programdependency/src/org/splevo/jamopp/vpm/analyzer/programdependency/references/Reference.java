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

import org.apache.log4j.Logger;
import org.emftext.language.java.commons.Commentable;
import org.splevo.jamopp.util.JaMoPPElementUtil;

/**
 * A reference between two JaMoPP elements (e.g. a MethodCall referencing a Method).
 *
 * A self reference is possible, if the source element is already a valid element to be referenced
 * by others (e.g. Method -> Method).
 */
public class Reference {

    private static Logger logger = Logger.getLogger(Reference.class);

    private Commentable source = null;
    private Commentable target = null;
    private ReferenceType type = null;

    /**
     * Creating a self reference element.
     *
     * @param self
     *            The single element.
     */
    public Reference(Commentable self) {
        this(self, self, ReferenceType.SELF);
    }

    /**
     * Creating a default reference.
     *
     * @deprecated A constructor with an explicit reference should be used instead.
     *
     * @param source
     *            The source element.
     * @param target
     *            The target element.
     */
    public Reference(Commentable source, Commentable target) {
        this(source, target, ReferenceType.DEFAULT);
    }

    /**
     * Setting the source and target of the reference object.
     *
     * @param source
     *            The element that is referencing.
     * @param target
     *            The element that is referenced.
     * @param type
     *            The specific type of the reference.
     */
    public Reference(Commentable source, Commentable target, ReferenceType type) {
        this.source = source;
        this.target = target;
        this.type = type;
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

    /**
     * Get the type of the reference.
     *
     * @return The type.
     */
    public ReferenceType getType() {
        return type;
    }

    /**
     * Get the dependency type of the reference. The type is derived from the source and target
     * elements as well as the reference type between them.
     *
     * DesignDecision Reverse ID build order for SuperType dependencies <strong>Special Case
     * SuperType reference</strong>: For super type dependencies, the ID is build in the reverse
     * order. For example: InterfaceSuperTypeClass if the class C implements the interface I. This
     * is necessary to have reasonable source->target roles if several sub types
     * implementing/extending the same super type.
     *
     * {@inheritDoc}
     */
    public DependencyType getDependencyType() {

        String typeID = null;
        if (type == ReferenceType.SuperType) {
            typeID = String.format("%s%s%s", JaMoPPElementUtil.getTypeLabel(target), type.name(),
                    JaMoPPElementUtil.getTypeLabel(source));
        } else {
            typeID = String.format("%s%s%s", JaMoPPElementUtil.getTypeLabel(source), type.name(),
                    JaMoPPElementUtil.getTypeLabel(target));
        }

        try {
            return DependencyType.valueOf(typeID);
        } catch (Exception e) {
            logger.error("Reference with unknown DependencyType: " + typeID);
            return DependencyType.IGNORE;
        }
    }

    /**
     * Set the type of the reference.
     *
     * @param type
     *            The new type to set.
     */
    public void setType(ReferenceType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("Reference[%s]", getDependencyType());
    }

}
