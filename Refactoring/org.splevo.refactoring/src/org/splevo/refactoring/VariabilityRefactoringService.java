package org.splevo.refactoring;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
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

    private static Logger logger = Logger.getLogger(VariabilityRefactoringService.class);

    /**
     * Perform refactoring according to the the configured {@link VariationPointModel}.
     * 
     * @param variationPointModel
     *            The {@link VariationPointModel} containing the variation points with the intended
     *            variability mechanism.
     * @return The ResourceSet referencing the refactored software.
     */
    public ResourceSet refactor(VariationPointModel variationPointModel) {
        ResourceSetImpl resourceSetImpl = new ResourceSetImpl();
        for (VariationPointGroup vpGroup : variationPointModel.getVariationPointGroups()) {
            for (VariationPoint variationPoint : vpGroup.getVariationPoints()) {
                // get refactoing, check if applicable and apply
                String refactoringID = variationPoint.getVariabilityMechanism().getRefactoringID();
                VariabilityRefactoring refactoring = VariabilityRefactoringRegistry.getRefactoringById(refactoringID);
                if (!refactoring.canBeAppliedTo(variationPoint)) {
                    logger.warn("Recommended refactoring cannot be applied to this variation point.");
                    continue;
                }
                refactoring.refactor(variationPoint);

                // add resource to the new set if not yet contained
                Resource vpLocationResource = variationPoint.getLocation().eResource();
                if (!resourceSetImpl.getResources().contains(vpLocationResource)) {
                    resourceSetImpl.getResources().add(vpLocationResource);
                }
            }
        }

        return resourceSetImpl;
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
