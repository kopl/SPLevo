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
     * Constructs the job. The new file name will include the given path segment.
     * 
     * @param splevoProject
     *            The SPLevoProject containing the project information such as the list of VPMs.
     * @param pathSegment
     *            The identifier to be used in the new VPM file name.
     * @param refactoringStarted
     *            Indicates if a refactoring has already been started on the VPM.
     */
    public SaveAndReloadVPMJob(SPLevoProject splevoProject, String pathSegment, boolean refactoringStarted) {
        this(splevoProject, pathSegment, refactoringStarted, true);
    }

    /**
     * Constructs the job. The new file name will include the given path segment.
     * 
     * @param splevoProject
     *            The SPLevoProject containing the project information such as the list of VPMs.
     * @param pathSegment
     *            The identifier to be used in the new VPM file name.
     * @param refactoringStarted
     *            Indicates if a refactoring has already been started on the VPM.
     * @param overwrite
     *            Indicates if an existing VPM shall be overwritten
     */
    public SaveAndReloadVPMJob(SPLevoProject splevoProject, String pathSegment, boolean refactoringStarted, boolean overwrite) {
        addJobs(splevoProject, getTargetPath(splevoProject, pathSegment), refactoringStarted, overwrite);
    }

    private void addJobs(SPLevoProject splevoProject, String targetPath, boolean refactoringStarted, boolean overwrite) {
        // load latest vpm model in blackboard
        LoadVPMJob loadVPMJob = new LoadVPMJob(splevoProject);
        add(loadVPMJob);
        
        // save the latest vpm model
        SaveVPMJob saveVPMJob = new SaveVPMJob(splevoProject, targetPath, refactoringStarted, overwrite);
        add(saveVPMJob);
        
        // load latest vpm model in blackboard
        loadVPMJob = new LoadVPMJob(splevoProject);
        add(loadVPMJob);

        // open the model
        add(new OpenVPMJob(splevoProject, null));
    }
    
    private static String getTargetPath(SPLevoProject splevoProject, String pathSegment) {
        return splevoProject.getWorkspace() + String.format("models/vpms/%s-vpm.vpm", pathSegment);
    }
    
}
