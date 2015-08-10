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
import org.splevo.project.SPLevoProject;
import org.splevo.vpm.VPMUtil;
import org.splevo.vpm.variability.VariationPointModel;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;

/**
 * Job to save the variation point model currently present in the blackboard.
 */
public class SaveVPMJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The splevo project to store the required data to. */
    private SPLevoProject splevoProject;

    /** The path to write the model to. */
    private String targetPath;

    private boolean refactoringStarted;

    /**
     * Constructor to set the reference to the splevo project and the target path to write the model
     * to.
     * 
     * @param splevoProject
     *            The project to update.
     * @param targetPath
     *            The eclipse workspace relative path to write to.
     * @param refactoringStarted
     *            Indicates if a refactoring has already been started for the VPM.
     */
    public SaveVPMJob(SPLevoProject splevoProject, String targetPath, boolean refactoringStarted) {
        this.splevoProject = splevoProject;
        this.targetPath = targetPath;
        this.refactoringStarted = refactoringStarted;
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException {

        VariationPointModel vpm = getBlackboard().getVariationPointModel();

        logger.info("Save VPM Model");
        try {
            VPMUtil.save(vpm, new File(targetPath));
            splevoProject.addVPMModelReference(targetPath, refactoringStarted);
        } catch (IOException e) {
            throw new JobFailedException("Failed to save vpm model.", e);
        }

        // finish run
        monitor.done();
    }

    @Override
    public String getName() {
        return "Save VPM model Job";
    }

    @Override
    public void cleanup(IProgressMonitor arg0) {
    }
}
