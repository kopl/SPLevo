/*******************************************************************************
 * Copyright (c) 2015
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.ui.jobs;

import org.splevo.project.SPLevoProject;

import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;

/**
 * Job that saves the VPM into a new file and reloads it in the UI.
 */
public class SaveAndReloadVPMJob extends SequentialBlackboardInteractingJob<SPLevoBlackBoard> {
    
    /**
     * Constructs the job. The new file name will include the incremented version counter.
     * @param splevoProject The SPLevoProject containing the project information such as the list of VPMs.
     */
    public SaveAndReloadVPMJob(SPLevoProject splevoProject) {
        addJobs(splevoProject, getTargetPath(splevoProject));
    }
    
    /**
     * Constructs the job. The new file name will include the given path segment.
     * @param splevoProject The SPLevoProject containing the project information such as the list of VPMs.
     * @param pathSegment The identifier to be used in the new VPM file name.
     */
    public SaveAndReloadVPMJob(SPLevoProject splevoProject, String pathSegment) {
        addJobs(splevoProject, getTargetPath(splevoProject, pathSegment));
    }

    private void addJobs(SPLevoProject splevoProject, String targetPath) {
        // save the latest vpm model
        SaveVPMJob saveVPMJob = new SaveVPMJob(splevoProject, targetPath);
        add(saveVPMJob);

        // open the model
        add(new OpenVPMJob(splevoProject, null));
    }
    
    private static String getTargetPath(SPLevoProject splevoProject) {
        return splevoProject.getWorkspace() + String.format("models/vpms/%d-vpm.vpm", splevoProject.getVpmModelPaths().size());
    }
    
    private static String getTargetPath(SPLevoProject splevoProject, String pathSegment) {
        return splevoProject.getWorkspace() + String.format("models/vpms/%s-vpm.vpm", pathSegment);
    }
    
}
