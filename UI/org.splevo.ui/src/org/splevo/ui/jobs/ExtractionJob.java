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
package org.splevo.ui.jobs;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.splevo.extraction.DefaultExtractionService;
import org.splevo.extraction.ExtractionService;
import org.splevo.extraction.SoftwareModelExtractionException;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.commons.util.JobUtil;
import org.splevo.ui.commons.util.WorkspaceUtil;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * Job to extract a software model from eclipse java projects.
 *
 * The extraction result is a {@link ResourceSet} which is merged into the variant specific
 * {@link ResourceSet} in the blackboard.
 */
public class ExtractionJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    private static final int PROGRESS_UPDATE_PROJECT_INFO_DONE = 5;
	private static final int PROGRESS_RESOURCESET_MERGE_DONE = 10;
	private static final int PROGRESS_EXTRACTION_DONE = 100;
	private static final int PROGRESS_PREPARATION_DONE = 10;

	/** The splevo project to store the required data to. */
    private SPLevoProject splevoProject;

    /** Flag whether the leading or the integration project should be extracted. */
    private boolean processLeading;

    /** The internal id of the extractor to be executed. */
    private String extractorId;

    /**
     * Constructor to create an extraction job with the required references.
     *
     * @param extractorId
     *            The identifier of the extractor to be executed.
     * @param splevoProject
     *            The splevo project to get and store required information
     * @param processLeading
     *            True/false wether this job is responsible for the leading implementation.
     */
    public ExtractionJob(String extractorId, SPLevoProject splevoProject, boolean processLeading) {
        this.splevoProject = splevoProject;
        this.processLeading = processLeading;
        this.extractorId = extractorId;
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {


        List<String> projectNames = null;
        String variantName = null;
        if (processLeading) {
            projectNames = splevoProject.getLeadingProjects();
            variantName = splevoProject.getVariantNameLeading();
        } else {
            projectNames = splevoProject.getIntegrationProjects();
            variantName = splevoProject.getVariantNameIntegration();
        }
        logger.info(String.format("Extraction of %s started at %s", variantName, JobUtil.getTimestamp()));

        // prepare the target path
        String sourceModelPath = WorkspaceUtil.getSourceModelPathWithinEclipse(splevoProject, variantName);

        List<String> projectURIsAbsolute = ProjectPathUtil.buildProjectPaths(projectNames);

        // check if the process was canceled
        if (monitor.isCanceled()) {
            throw new UserCanceledException();
        }

        // extract model
        ExtractionService extractionService = new DefaultExtractionService();

        monitor.worked(PROGRESS_PREPARATION_DONE);

        try {
            monitor.subTask("Extract Model for project: " + variantName);
            ResourceSet resourceSet = extractionService.extractSoftwareModel(extractorId, projectURIsAbsolute, monitor,
                    sourceModelPath);

            monitor.worked(PROGRESS_EXTRACTION_DONE);

            if (processLeading) {
                getBlackboard().getResourceSetLeading().getResources().addAll(resourceSet.getResources());
            } else {
                getBlackboard().getResourceSetIntegration().getResources().addAll(resourceSet.getResources());
            }

            monitor.worked(PROGRESS_RESOURCESET_MERGE_DONE);

        } catch (SoftwareModelExtractionException e) {
            throw new JobFailedException("Failed to extract model.", e);
        }

        monitor.subTask("Update SPLevo project information");

        if (processLeading) {
            splevoProject.setSourceModelPathLeading(sourceModelPath);
        } else {
            splevoProject.setSourceModelPathIntegration(sourceModelPath);
        }

        monitor.worked(PROGRESS_UPDATE_PROJECT_INFO_DONE);
        if (monitor.isCanceled()) {
            throw new UserCanceledException();
        }

        logger.info(String.format("Extraction of %s finished at %s", variantName, JobUtil.getTimestamp()));
    }

    /**
     * Get the name of the extraction job. This depends on whether the leading or the integration
     * job should be extracted.
     *
     * @return The name of the job.
     */
    @Override
    public String getName() {
        if (processLeading) {
            return "Model Extraction Job " + splevoProject.getVariantNameLeading();
        } else {
            return "Model Extraction Job " + splevoProject.getVariantNameIntegration();
        }
    }

    @Override
    public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
    }

}
