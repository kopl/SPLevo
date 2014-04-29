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
package org.splevo.jamopp.diffing.match;

import org.eclipse.emf.ecore.EObject;
import org.splevo.diffing.match.HierarchicalMatchEngine.EqualityStrategy;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

/**
 * JaMoPP java model specific equality strategy based on the similarity checker strategy.
 */
public class JaMoPPEqualityStrategy implements EqualityStrategy {

    /** The similarity checker to use internally for equality checks. */
    private SimilarityChecker similarityChecker = null;

    /**
     * Constructor to set the required dependencies.
     *
     * @param similarityChecker
     *            The similarity checker to proof equality.
     */
    public JaMoPPEqualityStrategy(SimilarityChecker similarityChecker) {
        this.similarityChecker = similarityChecker;
    }

    @Override
    public boolean areEqual(EObject left, EObject right) {
        Boolean similar = similarityChecker.isSimilar(left, right);
        if (similar == Boolean.TRUE) {
            return true;
        }
        return false;
    }

}