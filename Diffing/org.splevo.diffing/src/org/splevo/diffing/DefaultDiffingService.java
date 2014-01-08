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
package org.splevo.diffing;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.CompareFactory;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Default service to run the an extractor.
 */
public class DefaultDiffingService implements DiffingService {

    private Logger logger = Logger.getLogger(DefaultDiffingService.class);

    /** Regular expressions defining packages to be ignored. */
    private static final String MSG_DIFFER_NOT_AVAILABLE = "No differs available.";

    @Override
    public Comparison diffSoftwareModels(List<String> differIds, ResourceSet leadingModel,
            ResourceSet integrationModel, Map<String, String> diffingOptions) throws DiffingException {

        List<Differ> differs = getDiffers();
        if (differs.size() == 0) {
            throw new DiffingException(String.format(MSG_DIFFER_NOT_AVAILABLE));
        }

        Comparison diffModel = CompareFactory.eINSTANCE.createComparison();
        diffModel.setThreeWay(false);

        for (Differ differ : differs) {
            if (!differIds.contains(differ.getId())) {
                continue;
            }

            Comparison partComparisonModel;
            try {
                partComparisonModel = differ.doDiff(leadingModel, integrationModel, diffingOptions);
                diffModel.getMatches().addAll(partComparisonModel.getMatches());
                diffModel.getMatchedResources().addAll(partComparisonModel.getMatchedResources());
            } catch (DiffingNotSupportedException e) {
                logger.info("The differ does not support the provided input");
            }
        }

        return diffModel;
    }

    /**
     * Load the software model extractor implementations registered for the according extension
     * point.
     *
     * {@inheritDoc}
     */
    @Override
    public List<Differ> getDiffers() {
        List<Differ> differ = Activator.getDiffers();
        Collections.sort(differ, new Comparator<Differ>() {
            @Override
            public int compare(Differ d1, Differ d2) {
                return d1.getOrderId() - d2.getOrderId();
            }
        });
        return differ;
    }
}
