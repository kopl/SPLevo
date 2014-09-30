/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christian Busch
 *******************************************************************************/
package org.splevo.jamopp.algorithm.clones.baxtor;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.Commentable;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

/**
 * The clone detector detects clones between Commentables.
 *
 */
public class CloneDetector {
    
    private final CloneDetectionType detectionType;
    private final SimilarityChecker similarityChecker;
    
    public CloneDetector(CloneDetectionType detectionType) {
        this.detectionType = detectionType;
        this.similarityChecker = new SimilarityChecker();
    }
    
    /**
     * Checks whether two commentables are clones of each other.
     *
     * @param commentable1
     *            The first Commentable to check.
     * @param commentable2
     *            The second Commentable to check.
     * @return true if the commentables are clones of each other.
     */
    public boolean isClone(Commentable commentable1, Commentable commentable2) {
        if (commentable1 == null || commentable2 == null) {
            return false;
        }

        if (commentable1 == commentable2) {
            return true;
        }

        if (commentable1.eClass() != commentable2.eClass()) {
            return false;
        }
        
        if (detectionType == CloneDetectionType.EXACT) {
            if (similarityChecker.isSimilar(commentable1, commentable2, false) == false) {
                return false;
            }
        }

        return wholeTreeCloned(commentable1, commentable2);
    }

    /**
     * Checks whether elements of two lists of commentables are clones of each other.
     *
     * @param list1
     *            The first Commentable to check.
     * @param list2
     *            The second Commentable to check.
     * @return true if the commentables in the lists are clones of each other.
     */
    public boolean isClone(List<Commentable> list1, List<Commentable> list2) {
        if (list1 == null || list2 == null) {
            return false;
        }
        
        if (list1.size() == 0 || list2.size() == 0) {
            return false;
        }
        
        if (list1.size() != list2.size()) {
            return false;
        }

        Iterator<Commentable> iterator1 = list1.iterator();
        Iterator<Commentable> iterator2 = list2.iterator();

        Commentable commentable1;
        Commentable commentable2;

        while (iterator1.hasNext()) {
            commentable1 = iterator1.next();
            commentable2 = iterator2.next();

            if (!isClone(commentable1, commentable2)) {
                return false;
            }
        }

        return true;
    }

    private boolean wholeTreeCloned(Commentable commentable1, Commentable commentable2) {

        TreeIterator<EObject> commentable1Contents = commentable1.eAllContents();
        TreeIterator<EObject> commentable2Contents = commentable2.eAllContents();

        // If both trees have same structure with elements of the same type at the same places we
        // view them as the structurally equal. This way we are not dependent on the identifiers or values of
        // attributes.
        while (commentable1Contents.hasNext() && commentable2Contents.hasNext()) {
            EObject content1 = commentable1Contents.next();
            EObject content2 = commentable2Contents.next();

            if (content1.eClass() != content2.eClass()) {
                return false;
            }
            
            // For exact clones check for similarity of the elements.
            if (detectionType == CloneDetectionType.EXACT) {
                if (similarityChecker.isSimilar(commentable1, commentable2, false) == false) {
                    return false;
                }
            }
            
        }

        // If one tree has more elements than the other they are not equal
        if (commentable1Contents.hasNext() || commentable2Contents.hasNext()) {
            return false;
        }

        return true;
    }
}
