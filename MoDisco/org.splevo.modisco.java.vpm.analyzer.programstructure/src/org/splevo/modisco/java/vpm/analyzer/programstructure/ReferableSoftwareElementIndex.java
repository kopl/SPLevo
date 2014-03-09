/*******************************************************************************
 * Copyright (c) 2013
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.modisco.java.vpm.analyzer.programstructure;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * An index to store the referable software elements of the variation points and link them with the
 * containing variation points.
 */
public class ReferableSoftwareElementIndex {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(ReferableSoftwareElementIndex.class);

    /**
     * An index mapping all AST nodes enclosed by a variation point to the enclosing variation
     * point.
     */
    private Map<SoftwareElement, VariationPoint> softwareElement2VPIndex = null;

    /**
     * Index the referable software elements of the variation points.
     *
     * @param variationPoints
     *            The variation points to index the elements for.
     */
    public void index(Set<VariationPoint> variationPoints) {

        softwareElement2VPIndex = new LinkedHashMap<SoftwareElement, VariationPoint>();

        logger.info("Start build enclosed software element index");
        for (VariationPoint vp : variationPoints) {
            for (SoftwareElement se : getVariantsSoftwareElements(vp)) {
                indexReferableSoftwareElements(softwareElement2VPIndex, se, vp);
            }
        }
    }

    /**
     * Software elements can have child elements (e.g. a method containing statements). This method
     * indexes all sub elements.
     *
     * @param index
     *            The index map to add the node to.
     * @param softwareElement
     *            The AST node to index.
     * @param vp
     *            The variation point to map the node to.
     */
    private void indexReferableSoftwareElements(Map<SoftwareElement, VariationPoint> index,
            SoftwareElement softwareElement, VariationPoint vp) {
        index.put(softwareElement, vp);

        MoDiscoJavaProgramStructureProvider provider = new MoDiscoJavaProgramStructureProvider();
        List<SoftwareElement> subElements = provider.getReferableSubElements(softwareElement);
        for (SoftwareElement subElement : subElements) {
            indexReferableSoftwareElements(index, subElement, vp);
        }
    }

    /**
     * Collect all AST nodes implementing a variant of a Variation Point.
     *
     * @param vp
     *            The variation point to get the ASTNodes for.
     * @return A list of all referenced ASTNodes.
     */
    public List<SoftwareElement> getVariantsSoftwareElements(VariationPoint vp) {
        List<SoftwareElement> softwareElements = new ArrayList<SoftwareElement>();

        for (Variant v : vp.getVariants()) {

            for (SoftwareElement softwareElement : v.getImplementingElements()) {
                softwareElements.add(softwareElement);
            }
        }

        return softwareElements;
    }

    /**
     * Lookup the enclosing variation point for an AST node. This might be null if the provided AST
     * nodes is not part of a variation point.
     *
     * @param softwareElement
     *            The SoftwareElement to look up the variation point for.
     * @return The enclosing variation point or null if none exist.
     */
    public VariationPoint getEnclosingVariationPoint(SoftwareElement softwareElement) {
        return softwareElement2VPIndex.get(softwareElement);
    }

}
