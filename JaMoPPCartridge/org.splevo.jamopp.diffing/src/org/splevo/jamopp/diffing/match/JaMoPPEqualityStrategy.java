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

import java.util.List;

import org.eclipse.emf.compare.Match;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.commons.NamedElement;
import org.emftext.language.java.containers.CompilationUnit;
import org.splevo.diffing.match.HierarchicalMatchEngine.EqualityStrategy;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

import com.google.common.base.Strings;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

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

    /**
     * Filter duplicate matched compilation units and classes due to derived copy matches.
     *
     * Otherwise the EMF Compare engine would register original elements which are intended to be
     * identified as deleted, have matches due to the orginal class that must be present and they
     * matched to.
     *
     * {@inheritDoc}
     */
    @Override
    public List<Match> filterDuplicateMatches(List<Match> matches) {

        List<Match> filteredMatches = Lists.newLinkedList(matches);

        // index used to identify duplicate original elements (e.g. for DerivedCopy detection)
        Multimap<NamedElement, Match> rightMatchedIndex = LinkedHashMultimap.create();
        for (Match match : matches) {
            EObject right = match.getRight();
            if (right instanceof CompilationUnit || right instanceof Class) {
                rightMatchedIndex.get((NamedElement) right).add(match);
            }
        }

        // For duplicate matches keep only those with the same name
        for (NamedElement right : rightMatchedIndex.keySet()) {
            if (rightMatchedIndex.get(right).size() > 1) {
                for (Match match : rightMatchedIndex.get(right)) {
                    NamedElement left = (NamedElement) match.getLeft();
                    String leftName = Strings.nullToEmpty(left.getName());
                    if (!leftName.equals(right.getName())) {
                        filteredMatches.remove(match);
                    }
                }
            }
        }

        return matches;
    }

}