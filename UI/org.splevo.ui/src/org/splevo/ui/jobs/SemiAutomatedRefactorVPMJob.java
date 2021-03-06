package org.splevo.ui.jobs;

import java.io.File;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.project.SPLevoProject;
import org.splevo.refactoring.VariabilityRefactoringFailedException;
import org.splevo.refactoring.VariabilityRefactoringService;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * Job for executing a semi-automated refactoring.
 */
public class SemiAutomatedRefactorVPMJob extends RefactorVPMJob {

    private final String variationPointId;
    private final SPLevoProject splevoProject;

    /**
     * Constructs the refactoring job.
     * 
     * @param splevoProject
     *            The SPLevoProject to which the variation point belongs.
     * @param variationPointId
     *            The ID of the variation point to be refactored.
     */
    public SemiAutomatedRefactorVPMJob(SPLevoProject splevoProject, String variationPointId) {
        super(splevoProject);
        this.variationPointId = variationPointId;
        this.splevoProject = splevoProject;
    }

    @Override
    public void execute(IProgressMonitor arg0) throws JobFailedException, UserCanceledException {
        VariationPointModel vpm = getBlackboard().getVariationPointModel();

        Optional<VariationPoint> vp = findVPToBeRefactored(vpm);

        if (!vp.isPresent()) {
            throw new JobFailedException("The VP that shall be refactored could not be found in the VPM.");
        }

        String leadingSrcPath = new File(getLeadingSrcPath()).getAbsolutePath();
        Map<String, Object> refactoringConfigurations = Maps.newHashMap();
        refactoringConfigurations.put(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY, leadingSrcPath);
        refactoringConfigurations.put(VariabilityRefactoringService.SPLEVO_PROJECT, this.splevoProject);

        VariabilityRefactoringService variabilityRefactoringService = new VariabilityRefactoringService();
        try {
            variabilityRefactoringService.refactorSemiAutomated(vpm, vp.get(), refactoringConfigurations);
        } catch (VariabilityRefactoringFailedException e) {
            throw new JobFailedException("The refactoring failed during execution.", e);
        }
    }

    private Optional<VariationPoint> findVPToBeRefactored(VariationPointModel vpm) {

        Iterable<VariationPoint> vps = Iterables.concat(Iterables.transform(vpm.getVariationPointGroups(),
                new Function<VariationPointGroup, Iterable<VariationPoint>>() {
                    @Override
                    public Iterable<VariationPoint> apply(VariationPointGroup input) {
                        return input.getVariationPoints();
                    }
                }));

        return Iterables.tryFind(vps, new Predicate<VariationPoint>() {
            @Override
            public boolean apply(VariationPoint input) {
                return input.getId().equals(variationPointId);
            }
        });
    }

    @Override
    public String getName() {
        return "Semi-automated VPM refactoring job";
    }

}
