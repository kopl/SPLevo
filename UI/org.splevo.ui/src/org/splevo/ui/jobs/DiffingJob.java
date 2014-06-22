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

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.splevo.diffing.DefaultDiffingService;
import org.splevo.diffing.DiffingException;
import org.splevo.diffing.DiffingModelUtil;
import org.splevo.diffing.DiffingService;
import org.splevo.project.SPLevoProject;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;

/**
 * Job to execute the diffing on the source models provided through the blackboard.
 */
public class DiffingJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    private static final int PROGRESS_DIFF_MODEL_SAVE_DONE = 20;
	private static final int PROGRESS_DIFFING_DONE = 100;

	private final SPLevoProject splevoProject;

    /**
     * Constructor for the diffing job.
     *
     * @param splevoProject
     *            The SPLevo project to process the diffing for.
     */
    public DiffingJob(final SPLevoProject splevoProject) {
        this.splevoProject = splevoProject;
    }

    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException {

        logger.info("Difference analysis started");

        Map<String, String> options = buildDiffingOptions();
        ResourceSet leadingModelDir = getBlackboard().getResourceSetLeading();
        ResourceSet integrationModelDir = getBlackboard().getResourceSetIntegration();
        List<String> differIds = splevoProject.getDifferIds();

        logger.info(String.format("Diffing started at %s", JobUtil.getTimestamp()));
        Comparison comparison = null;
        try {
            final DiffingService diffingService = new DefaultDiffingService();
            comparison = diffingService.diffSoftwareModels(differIds, leadingModelDir, integrationModelDir, options);
            monitor.worked(PROGRESS_DIFFING_DONE);
        } catch (final DiffingException e) {
            throw new JobFailedException("Failed to process diffing.", e);
        }
        logger.info(String.format("Diffing finished at %s", JobUtil.getTimestamp()));
        logger.info("Number of differences: " + comparison.getDifferences().size());

        // check if the process was canceled
        if (monitor.isCanceled()) {
            monitor.done();
            return;
        }

        if (comparison.eContents().size() == 0) {
            logger.info("No Differences detected.");
            return;
        }

        logger.info("Save Diff Model");
        try {
            // TODO Do not save a technology specific diff model.
            String targetPath = null;
            if (splevoProject.getDifferIds().contains("org.splevo.jamopp.differ")) {
                targetPath = "models/diffmodel/diffModel.jamoppdiff";
            } else {
                targetPath = "models/diffmodel/diffModel.java2kdmdiff";
            }
            targetPath = splevoProject.getWorkspace() + targetPath;
            DiffingModelUtil.save(comparison, new File(targetPath));
            this.splevoProject.setDiffingModelPath(targetPath);
        } catch (final IOException e) {
            throw new JobFailedException("Failed to save diff model.", e);
        }

        monitor.worked(PROGRESS_DIFF_MODEL_SAVE_DONE);
        refreshWorkspace(monitor);

        logger.info("Difference analysis finished");
    }

    /**
     * Refresh the workspace so the UI will present all new generated files to the user.
     *
     * @param monitor
     *            The process monitor to report the progress to.
     */
    private void refreshWorkspace(final IProgressMonitor monitor) {
        try {
            ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_ONE, monitor);
        } catch (final CoreException e) {
            logger.error("Failed to refresh Workspace", e);
        }
    }

    /**
     * Build the map of differ configurations from the settings in the splevo project.
     *
     * @return The prepared options for the differs.
     */
    private Map<String, String> buildDiffingOptions() {
        Map<String, String> diffingOptions = this.splevoProject.getDifferOptions().map();
        return diffingOptions;
    }

    @Override
    public String getName() {
        return "Diff source models Job";
    }

    @Override
    public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
    }
}
