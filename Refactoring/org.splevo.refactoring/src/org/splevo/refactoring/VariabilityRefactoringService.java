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
 *    Daniel Kojic
 *******************************************************************************/
package org.splevo.refactoring;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.splevo.refactoring.RecommenderResult.Status;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * A service to refactor the product copies described by a variation point model according to the
 * defined variation points and consolidating their implementations by introducing variability
 * mechanisms.
 */
public class VariabilityRefactoringService {

    public static final String JAVA_SOURCE_DIRECTORY = "JaMoPP.Refactoring.Options.SourceDirectory";

    private static Logger logger = Logger.getLogger(VariabilityRefactoringService.class);

    /**
     * Perform refactoring according to the the configured {@link VariationPointModel}.
     *
     * @param variationPointModel
     *            The {@link VariationPointModel} containing the variation points with the intended
     *            variability mechanism.
     * @param refactoringConfigurations
     *            Refactoring configurations.
     * @return The ResourceSet referencing the refactored software.
     */
    public Set<Resource> refactor(VariationPointModel variationPointModel, Map<String, Object> refactoringConfigurations) {
        EcoreUtil.resolveAll(variationPointModel);
        Set<Resource> toBeSaved = new HashSet<Resource>();
        for (VariationPointGroup vpGroup : variationPointModel.getVariationPointGroups()) {

            for (VariationPoint variationPoint : vpGroup.getVariationPoints()) {
                String refactoringID = variationPoint.getVariabilityMechanism().getRefactoringID();
                VariabilityRefactoring refactoring = VariabilityRefactoringRegistry.getRefactoringById(refactoringID);

                if (!refactoring.canBeAppliedTo(variationPoint)) {
                    logger.debug("Recommended refactoring cannot be applied to this variation point.");
                    continue;
                }

                List<Resource> changedResources = refactoring.refactor(variationPoint, refactoringConfigurations);

                toBeSaved.addAll(changedResources);
            }
        }

        return toBeSaved;
    }

    /**
     * Auto assign the highest prioritized and matching variability mechanism to each not yet
     * assigned variation point.
     *
     * @param variationPointModel
     *            The variation point model to assign the vps in.
     * @param refactorings
     *            The prioritized list of refactorings to be considered by the recommender.
     * @return The result of the recommendation run.
     */
    public RecommenderResult recommendMechanisms(VariationPointModel variationPointModel,
            List<VariabilityRefactoring> refactorings) {
        RecommenderResult result = new RecommenderResult();

        for (VariationPointGroup group : variationPointModel.getVariationPointGroups()) {
            for (VariationPoint vp : group.getVariationPoints()) {
                if (vp.getVariabilityMechanism() == null) {

                    VariabilityRefactoring bestRefactoring = getBestMatchingRefactoring(vp, refactorings);
                    if (bestRefactoring == null) {
                        result.getUnassignedVariationPoints().add(vp);
                        continue;
                    }
                    VariabilityMechanism mechanism = bestRefactoring.getVariabilityMechanism();
                    vp.setVariabilityMechanism(mechanism);
                }
            }
        }

        try {
            if (variationPointModel.eResource() != null) {
                variationPointModel.eResource().save(null);
            } else {
                logger.info("Variation Point Model without a resource");
            }
        } catch (IOException e) {
            result.setStatus(Status.FAILED);
            logger.error("Failed to save Variation Point Model", e);
        }

        return result;
    }

    /**
     * Get the best matching refactoring from a priorized list of refactorings.
     *
     * @param vp
     *            The vp to decide a refactoring for.
     * @param refactorings
     *            The priorized list of refactorings. Higher priorized first.
     * @return The best matching refactoring. Null if none could be found.
     */
    private VariabilityRefactoring getBestMatchingRefactoring(VariationPoint vp,
            List<VariabilityRefactoring> refactorings) {
        VariabilityRefactoring bestRefactoring = refactorings.get(0);
        return bestRefactoring;
    }

}
