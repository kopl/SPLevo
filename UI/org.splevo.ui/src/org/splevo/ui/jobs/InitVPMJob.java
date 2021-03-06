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

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.compare.Comparison;
import org.splevo.project.SPLevoProject;
import org.splevo.vpm.builder.DefaultVPMBuilderService;
import org.splevo.vpm.builder.VPMBuildException;
import org.splevo.vpm.variability.VariationPointModel;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;

/**
 * Job to initialize a variation point model from a diffing model.
 */
public class InitVPMJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The splevo project to store the required data to. */
    private SPLevoProject splevoProject;

    /**
     * Constructor to set the reference to the SPLevo project.
     *
     * @param splevoProject
     *            The SPLevo project to exchange data with.
     */
    public InitVPMJob(SPLevoProject splevoProject) {
        this.splevoProject = splevoProject;
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException {

        logger.info("Get comparison model");
        Comparison comparison = getBlackboard().getDiffModel();

        if (comparison == null) {
            throw new JobFailedException("No Diff model available on blackboard");
        }

        logger.info("Build initial VPM model");
        Map<String, Object> builderOptions = new LinkedHashMap<String, Object>();
        DefaultVPMBuilderService builderService = new DefaultVPMBuilderService();
        VariationPointModel vpm;
        try {
            vpm = builderService.buildVPM(comparison, splevoProject.getVariantNameLeading(),
                    splevoProject.getVariantNameIntegration(), builderOptions);
        } catch (VPMBuildException e) {
            throw new JobFailedException("Failed to initialize the VPM model", e);
        }

        // check if the process was canceled
        if (monitor.isCanceled()) {
            monitor.done();
            logger.info("Workflow cancled.");
            return;
        }

        logger.info("Store VPM model in blackboard");
        getBlackboard().setVariationPointModel(vpm);

        // finish run
        monitor.done();
    }

    @Override
    public String getName() {
        return "Init VPM model Job";
    }

    @Override
    public void cleanup(IProgressMonitor arg0) {
    }
}
