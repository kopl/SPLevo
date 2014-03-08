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
    private static final String MSG_DIFFER_NOT_SELECTED = "No differ selected.";

    @Override
    public Comparison diffSoftwareModels(List<String> differIds, ResourceSet leadingModel,
            ResourceSet integrationModel, Map<String, String> diffingOptions) throws DiffingException {

        if (differIds.size() == 0) {
            throw new DiffingException(String.format(MSG_DIFFER_NOT_SELECTED));
        }

        Comparison diffModel = CompareFactory.eINSTANCE.createComparison();
        diffModel.setThreeWay(false);

        for (String differId : differIds) {
            Differ differ = DifferRegistry.getDifferById(differId);
            if (differ == null) {
                logger.warn("Selected Differ not registered: " + differId);
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
}
