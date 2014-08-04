/*******************************************************************************
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic - initial API and implementation and initial documentation
 *******************************************************************************/
package org.splevo.ui.jobs;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.splevo.project.SPLevoProject;
import org.splevo.refactoring.VariabilityRefactoringService;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.Maps;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * Assigns recommended refactorings to the vpm's vps, refactors them and finally saves the changed resources.
 */
public class RefactorVPMJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    private SPLevoProject splevoProject;

    /**
     * Initializes the job with a given splevo project.
     *
     * @param splevoProject
     *            The project to get the leading paths from.
     */
    public RefactorVPMJob(SPLevoProject splevoProject) {
        this.splevoProject = splevoProject;
    }

    @Override
    public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
        // nothing to do
    }

    @Override
    public void execute(IProgressMonitor arg0) throws JobFailedException, UserCanceledException {
        VariationPointModel vpm = getBlackboard().getVariationPointModel();

        String leadingSrcPath = new File(getLeadingSrcPath()).getAbsolutePath();
        Map<String, String> refactoringConfigurations = Maps.newHashMap();
        refactoringConfigurations.put(VariabilityRefactoringService.JAVA_SOURCE_DIRECTORY, leadingSrcPath);

        VariabilityRefactoringService variabilityRefactoringService = new VariabilityRefactoringService();
        ResourceSet rs = variabilityRefactoringService.refactor(vpm, refactoringConfigurations);
        
        for (Resource resource : rs.getResources()) {
            try {
                resource.save(null);
            } catch (IOException e) {
                logger.error("Could not save resource: " + resource.getURI().lastSegment(), e);
            }
        }
    }

    private String getLeadingSrcPath() {
        String leadingProjectPath = splevoProject.getLeadingProjects().get(0);

        String leadingSrcPath = leadingProjectPath;

        if (!leadingSrcPath.endsWith(File.separator)) {
            leadingSrcPath = leadingSrcPath + File.separator;
        }

        leadingSrcPath += "src";

        return leadingSrcPath;
    }

    @Override
    public String getName() {
        return "Refactor VPM Model Job";
    }

}
