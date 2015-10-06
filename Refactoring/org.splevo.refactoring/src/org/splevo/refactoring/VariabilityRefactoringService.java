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
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.splevo.refactoring.RecommenderResult.Status;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * A service to refactor the product copies described by a variation point model according to the
 * defined variation points and consolidating their implementations by introducing variability
 * mechanisms.
 */
public class VariabilityRefactoringService {

    public static final String JAVA_SOURCE_DIRECTORY = "JaMoPP.Refactoring.Options.SourceDirectory";
    public static final String SPLEVO_PROJECT = "SPLEVO_PROJECT";

    private static Logger logger = Logger.getLogger(VariabilityRefactoringService.class);

    /**
     * Perform refactoring according to the the configured {@link VariationPointModel}. This method
     * executes the {@link VariabilityRefactoring#refactor(VariationPoint, Map)} method.
     * 
     * @param variationPointModel
     *            The {@link VariationPointModel} containing the variation points with the intended
     *            variability mechanism.
     * @param refactoringConfigurations
     *            Refactoring configurations.
     */
    public void refactor(VariationPointModel variationPointModel, Map<String, Object> refactoringConfigurations) {
        preprocessResources(variationPointModel);
        EcoreUtil.resolveAll(variationPointModel);
        preprocessVPM(variationPointModel);
        Set<Resource> toBeSaved = new HashSet<Resource>();
        for (VariationPointGroup vpGroup : variationPointModel.getVariationPointGroups()) {

            for (VariationPoint variationPoint : vpGroup.getVariationPoints()) {
                String refactoringID = variationPoint.getVariabilityMechanism().getRefactoringID();
                VariabilityRefactoring refactoring = VariabilityRefactoringRegistry.getInstance().getElementById(
                        refactoringID);

                if (refactoring.canBeAppliedTo(variationPoint).getSeverity() != Diagnostic.OK) {
                    logger.debug("Recommended refactoring cannot be applied to this variation point.");
                    continue;
                }

                List<Resource> changedResources = refactoring.refactor(variationPoint, refactoringConfigurations);

                toBeSaved.addAll(changedResources);
            }
        }

        postprocess(variationPointModel, toBeSaved);
    }

    /**
     * Perform refactoring according to the the configured {@link VariationPointModel}. This method
     * executes the {@link VariabilityRefactoring#refactor(VariationPoint, Map)} method.
     * 
     * <p>
     * <b>This method shall only be called from semi-automated refactorings that want to execute a
     * fully-automated refactoring as part of their workflow.</b>
     * </p>
     * 
     * @param refactoring
     *            The refactoring to be executed (has to be of type
     *            {@link FullyAutomatedVariabilityRefactoring})
     * @param vpm
     *            The variation point model to which the variation point belongs.
     * @param variationPoint
     *            The variation point to be refactored.
     * @param refactoringConfigurations
     *            The refactoring configuration to be used.
     * @throws VariabilityRefactoringFailedException
     *             Thrown if the refactoring is not applicable or not of type
     *             {@link FullyAutomatedVariabilityRefactoring}.
     */
    public void refactorFullyAutomated(VariabilityRefactoring refactoring, VariationPointModel vpm,
            VariationPoint variationPoint, Map<String, Object> refactoringConfigurations)
            throws VariabilityRefactoringFailedException {
        if (refactoring.canBeAppliedTo(variationPoint).getSeverity() != Diagnostic.OK) {
            throw new VariabilityRefactoringFailedException(
                    "Recommended refactoring cannot be applied to this variation point.");
        }

        if (!(refactoring instanceof FullyAutomatedVariabilityRefactoring)) {
            throw new VariabilityRefactoringFailedException("The given refactoring is no fully automated refactoring.");
        }

        List<Resource> changedResources = refactoring.refactor(variationPoint, refactoringConfigurations);

        postprocess(vpm, Sets.newHashSet(changedResources));
    }

    /**
     * Perform refactoring according to the the configured {@link VariationPointModel}. This method
     * executes the
     * {@link SemiAutomatedVariabilityRefactoring#startManualRefactoring(VariationPoint, Map)}
     * method after determining the refactoring that shall be applied from the variation point.
     * 
     * @param vpm
     *            The variation point model to which the variation point belongs.
     * @param variationPoint
     *            The variation point to be refactored.
     * @param refactoringConfigurations
     *            The refactoring configuration to be used.
     * @throws VariabilityRefactoringFailedException
     *             Thrown if the refactoring is not applicable, the type is not
     *             {@link SemiAutomatedVariabilityRefactoring}, or the execution of the refactoring
     *             failed.
     */
    public void refactorSemiAutomated(VariationPointModel vpm, VariationPoint variationPoint,
            Map<String, Object> refactoringConfigurations) throws VariabilityRefactoringFailedException {

        String refactoringID = variationPoint.getVariabilityMechanism().getRefactoringID();
        VariabilityRefactoring refactoring = VariabilityRefactoringRegistry.getInstance().getElementById(refactoringID);

        if (refactoring.canBeAppliedTo(variationPoint).getSeverity() != Diagnostic.OK) {
            throw new VariabilityRefactoringFailedException(
                    "Recommended refactoring cannot be applied to this variation point.");
        }

        if (!(refactoring instanceof SemiAutomatedVariabilityRefactoring)) {
            throw new VariabilityRefactoringFailedException("The given refactoring is no fully automated refactoring.");
        }

        List<Resource> changedResources = ((SemiAutomatedVariabilityRefactoring) refactoring).startManualRefactoring(
                variationPoint, refactoringConfigurations);

        postprocess(vpm, Sets.newHashSet(changedResources));
    }

    private void postprocess(VariationPointModel variationPointModel, Set<Resource> toBeSaved) {
        EcoreUtil.resolveAll(variationPointModel);
        postprocessVPM(variationPointModel);
        saveVPM(variationPointModel);
        saveAndPostprocessResources(toBeSaved);
    }

    private void postprocessVPM(VariationPointModel variationPointModel) {
        new ResourceProcessorService().processVPMAfterRefactorings(variationPointModel);
    }

    private void saveAndPostprocessResources(Set<Resource> toBeSaved) {
        for (Resource resource : toBeSaved) {
            saveResource(resource);
        }

        postprocessResources(toBeSaved);

        for (Resource resource : toBeSaved) {
            saveResource(resource);
        }
    }

    private void postprocessResources(Set<Resource> toBeSaved) {
        new ResourceProcessorService().postprocessResources(toBeSaved);
    }

    private void preprocessVPM(VariationPointModel variationPointModel) {
        new ResourceProcessorService().processVPMBeforeRefactorings(variationPointModel);
        saveVPM(variationPointModel);
    }

    private void preprocessResources(VariationPointModel variationPointModel) {
        Iterable<Resource> resourcesOfAllSoftwareElements = Iterables.transform(
                variationPointModel.getSoftwareElements(), new Function<SoftwareElement, Resource>() {
                    @Override
                    public Resource apply(SoftwareElement arg0) {
                        return arg0.getWrappedElement().eResource();
                    }
                });
        Iterable<Resource> nonNullResources = Iterables.filter(resourcesOfAllSoftwareElements, Predicates.notNull());
        Set<Resource> resources = Sets.newHashSet(nonNullResources);
        new ResourceProcessorService().processResourcesBeforeRefactorings(resources);
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

        if (!saveVPM(variationPointModel)) {
            result.setStatus(Status.FAILED);
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

        for (VariabilityRefactoring refactoring : refactorings) {
            if (refactoring.canBeAppliedTo(vp).getSeverity() == Diagnostic.OK) {
                return refactoring;
            }
        }
        return null;
    }

    private boolean saveVPM(VariationPointModel variationPointModel) {
        if (variationPointModel.eResource() != null) {
            return saveResource(variationPointModel.eResource());
        }
        logger.info("Variation Point Model without a resource");
        return true;
    }

    private boolean saveResource(Resource resource) {
        try {
            resource.save(null);
        } catch (IOException e) {
            logger.error("Could not save resource: " + resource.getURI().lastSegment(), e);
            return false;
        }
        return true;
    }

}
