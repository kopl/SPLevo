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
package org.splevo.jamopp.vpm.mergedecider;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.statements.Statement;
import org.splevo.jamopp.util.JaMoPPElementUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.analyzer.mergedecider.MergeDecider;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * JaMoPP specific component to decide about the merge-ability for two variation points.
 */
public class JaMoPPMergeDecider implements MergeDecider {

    private static Logger logger = Logger.getLogger(JaMoPPMergeDecider.class);

    @Override
    public boolean canBeMerged(VariationPoint vp1, VariationPoint vp2) {

        SoftwareElement location1 = vp1.getLocation();
        SoftwareElement location2 = vp2.getLocation();

        Boolean bothJaMoPPElements = checkThatBothJaMoPPElements(location1, location2);
        if (!bothJaMoPPElements) {
            return false;
        }

        Commentable jamoppLocation1 = ((JaMoPPSoftwareElement) location1).getJamoppElement();
        Commentable jamoppLocation2 = ((JaMoPPSoftwareElement) location2).getJamoppElement();
        if (jamoppLocation1 != jamoppLocation2) {
            return false;
        }

        if (checkDistinctVariants(vp1, vp2)) {
            return true;
        }

        if (checkMergeableImports(vp1, vp2)) {
            return true;
        }

        if (checkMergeableStatements(vp1, vp2)) {
            return true;
        }

        return false;
    }

    /**
     * Check if the variation points are about changed statements which are located in their parent
     * container in a way that they can be combined.
     *
     * This check assumes that variation points location is already checked to be the same.
     *
     * @param vp1
     *            The first variation point to check.
     * @param vp2
     *            The second variation point to check.
     * @return
     */
    private boolean checkMergeableStatements(VariationPoint vp1, VariationPoint vp2) {

        Commentable loc1 = getJaMoPPLocation(vp1);
        Commentable loc2 = getJaMoPPLocation(vp2);

        if (!(loc1 instanceof Method) || !(loc2 instanceof Method)) {
            return false;
        }

        ArrayListMultimap<String, Statement> variantStatements = ArrayListMultimap.create();

        ArrayListMultimap<String, Statement> vp1VariantStatements = collectVariantStatements(vp1);
        variantStatements.putAll(vp1VariantStatements);
        ArrayListMultimap<String, Statement> vp2VariantStatements = collectVariantStatements(vp2);
        variantStatements.putAll(vp2VariantStatements);

        for (String variantId : variantStatements.keySet()) {
            List<Statement> statements = variantStatements.get(variantId);

            TreeMap<Integer, Statement> positionIndex = indexStatementPosition(statements);

            Iterator<Integer> keyIterator = positionIndex.keySet().iterator();
            int lastPosition = -1;
            while (keyIterator.hasNext()) {
                int currentPosition = keyIterator.next();

                // DesignDecision: Check statements position only and no program dependencies
                if (!statementIsFirstOrDirectFollower(lastPosition, currentPosition)) {
                    return false;
                }

                lastPosition = currentPosition;
            }

        }

        return true;
    }

    private TreeMap<Integer, Statement> indexStatementPosition(List<Statement> statements) {
        TreeMap<Integer, Statement> positionIndex = Maps.newTreeMap();
        for (Statement statement : statements) {
            int pos = JaMoPPElementUtil.getPositionInContainer(statement);
            positionIndex.put(pos, statement);
        }
        return positionIndex;
    }

    private boolean statementIsFirstOrDirectFollower(int lastPosition, int position) {
        return lastPosition == -1 || lastPosition == position - 1;
    }

    /**
     * Collect all implementing statement elements per variant of a VariationPoint.
     *
     * @param vp
     *            The variation point to investigate.
     * @return A map associating all variants to their implementing statements.
     */
    private ArrayListMultimap<String, Statement> collectVariantStatements(VariationPoint vp) {
        ArrayListMultimap<String, Statement> variantStatements = ArrayListMultimap.create();
        for (Variant variant : vp.getVariants()) {
            for (SoftwareElement se : variant.getImplementingElements()) {
                JaMoPPSoftwareElement jamoppSoftwareElement = (JaMoPPSoftwareElement) se;
                Commentable jamoppElement = jamoppSoftwareElement.getJamoppElement();
                if (!(jamoppElement instanceof Statement)) {
                    logger.warn("No statement: " + jamoppElement.getClass().getSimpleName());
                }

                Statement statement = (Statement) jamoppElement;
                variantStatements.get(variant.getVariantId()).add(statement);
            }
        }
        return variantStatements;
    }

    /**
     * True if the variation points have distinct variants only.
     *
     * This check assumes that the VPs location has already be proven to be the same.
     *
     * @param vp1
     *            The first variation point to check
     * @param vp2
     *            The second variation point to check
     * @return True if both VPs have contributions for different variants only.
     */
    // TODO: Check if it's valid to assume distinct variants for two vps is sufficient to merge them
    private boolean checkDistinctVariants(VariationPoint vp1, VariationPoint vp2) {

        Set<String> vp1Ids = getVariantIds(vp1);
        Set<String> vp2Ids = getVariantIds(vp2);

        return Collections.disjoint(vp1Ids, vp2Ids);
    }

    /**
     * Get the set of ids for the variants of variation point.
     *
     * @param vp
     *            The variation point.
     * @return The list of it's ids.
     */
    private Set<String> getVariantIds(VariationPoint vp) {
        Set<String> vpIds = Sets.newLinkedHashSet();
        for (Variant variant : vp.getVariants()) {
            vpIds.add(variant.getVariantId());
        }
        return vpIds;
    }

    private boolean checkMergeableImports(VariationPoint vp1, VariationPoint vp2) {

        Commentable loc1 = getJaMoPPLocation(vp1);
        Commentable loc2 = getJaMoPPLocation(vp2);

        if (!(loc1 instanceof CompilationUnit) || !(loc2 instanceof CompilationUnit)) {
            return false;
        }

        return true;
    }

    /**
     * Get the JaMoPP Element representing the location of the variation point. If this element can
     * not be determined return null.
     *
     * @param vp
     *            The variation point to get the location element for.
     * @return The JaMoPP Element or null if not available.
     */
    private Commentable getJaMoPPLocation(VariationPoint vp) {
        JaMoPPSoftwareElement element = (JaMoPPSoftwareElement) vp.getLocation();
        return element.getJamoppElement();
    }

    /**
     * Check if two elements are JaMoPP ones.<br>
     * If they are: Return True<br>
     * If only one or none: Return False<br>
     *
     * @param elem1
     *            First element to check.
     * @param elem2
     *            Second element to check.
     * @return True/False according to the rules above.
     */
    private boolean checkThatBothJaMoPPElements(SoftwareElement elem1, SoftwareElement elem2) {
        boolean isJaMoPP1 = isJaMoPPSoftwareElement(elem1);
        boolean isJaMoPP2 = isJaMoPPSoftwareElement(elem2);
        return (isJaMoPP1 && isJaMoPP2);
    }

    private boolean isJaMoPPSoftwareElement(SoftwareElement softwareElement) {
        return (softwareElement instanceof JaMoPPSoftwareElement);
    }

}