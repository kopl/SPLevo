/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.jamopp.vpm.analyzer.programdependency;

import java.util.ArrayList;
import java.util.List;

import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.statements.LocalVariableStatement;

/**
 * A switch to decide for a JaMoPP software element which sub elements are referable.
 */
public class RefereableChildNodeTraverser {

    /**
     * Get the referable child nodes of a JaMoPP element.
     *
     * @param commentable
     *            The JaMoPP element to find the child elements for.
     * @return The list of referable children.
     */
    public List<Commentable> getRefereableChildNodes(Commentable commentable) {
        List<Commentable> referableNodes = new ArrayList<Commentable>();

        if (commentable instanceof Import) {
            return referableNodes;
        }

        if (commentable instanceof LocalVariableStatement) {
            LocalVariableStatement lvs = (LocalVariableStatement) commentable;
            referableNodes.add(lvs.getVariable());
            return referableNodes;
        }

        return referableNodes;
    }

}
