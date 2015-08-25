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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.splevo.diffing.DiffingModelUtil;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.commons.util.JobUtil;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;

/**
 * Job to load a diffing model in the blackboard.
 */
public class LoadDiffingModelJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The splevo project to store the required data to. */
    private SPLevoProject splevoProject;

    /**
     * Constructor to set a reference to the splevoproject.
     *
     * @param splevoProject
     *            The reference to the splevoproject.
     */
    public LoadDiffingModelJob(SPLevoProject splevoProject) {
        this.splevoProject = splevoProject;
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException {

        logger.info("Load diff models");
        File diffModelFile = new File(splevoProject.getDiffingModelPath());

        ResourceSet resourceSet = JobUtil.initResourceSet(splevoProject, false);

        Comparison diffModel;
        try {
            diffModel = DiffingModelUtil.loadModel(diffModelFile, resourceSet);
        } catch (IOException e) {
            throw new JobFailedException("Failed to load diff model.", e);
        }

        logger.info("Put diff model on the blackboard");
        getBlackboard().setDiffModel(diffModel);

        // finish run
        monitor.done();
    }

    @Override
    public String getName() {
        return "Load diffing model Job";
    }

    @Override
    public void cleanup(IProgressMonitor arg0) {
    }
}
