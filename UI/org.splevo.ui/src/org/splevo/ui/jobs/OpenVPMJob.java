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

import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.util.VPMUIUtil;
import org.splevo.vpm.variability.VariationPointModel;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;

/**
 * Job to open the variation point model in the VP Explorer.
 */
public class OpenVPMJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    protected final SPLevoProject splevoProject;
    protected String vpmPath = null;

    /**
     * Constructor to set vpm path and project context to load the vpm from file.
     *
     * @param splevoProject
     *            The project context to use.
     * @param vpmPath
     *            The absolute path to the model to load.
     */
    public OpenVPMJob(SPLevoProject splevoProject, String vpmPath) {
        this.splevoProject = splevoProject;
        this.vpmPath = vpmPath;
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException {

        if (splevoProject != null && vpmPath != null) {
            logger.info("Trigger Job to Open VPM from file");
            VPMUIUtil.openVPExplorer(splevoProject, vpmPath);
        } else {
            logger.info("Trigger Job to Open VPm from Blackboard");
            VariationPointModel vpm = getBlackboard().getVariationPointModel();
            VPMUIUtil.openVPExplorer(splevoProject, vpm);
        }

        // finish run
        monitor.done();
    }

    @Override
    public String getName() {
        return "Open VPM in VP Explorer";
    }

    @Override
    public void cleanup(IProgressMonitor arg0) {
    }
}
