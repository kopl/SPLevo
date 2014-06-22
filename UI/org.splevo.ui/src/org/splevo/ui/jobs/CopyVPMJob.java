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
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.project.SPLevoProject;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;

/**
 * Copies the VPM model from the blackboard and re-initializes with the given outputPath.
 */
public class CopyVPMJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    private String outputPath;
    private SPLevoProject spLevoProject;

    /**
     * Initializes the job with a given {@link SPLevoProject} and a path where the spl should be copied to.
     *
     * @param spLevoProject
     *            The {@link SPLevoProject}.
     * @param outputPath
     *            The {@link String} path.
     */
    public CopyVPMJob(SPLevoProject spLevoProject, String outputPath) {
        this.outputPath = outputPath;
        this.spLevoProject = spLevoProject;
    }

    @Override
    public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
        // nothing to do here
    }

    @Override
    public void execute(IProgressMonitor arg0) {
        copyLeadingProjects();

        VariationPointModel vpmModel = getBlackboard().getVariationPointModel();
        Set<Resource> resources = extractLeadingResourcesFromVPM(vpmModel);
        ResourceSetImpl rs = new ResourceSetImpl();

        for (Resource oldResource : resources) {
            String projectSpecificPath = buildNewResourcePath(oldResource);

            // create new resource and move contents
            URI newURI = URI.createFileURI(outputPath + "/" + projectSpecificPath);
            Resource newResource = rs.createResource(newURI);
            newResource.getContents().addAll(oldResource.getContents());
            oldResource.getContents().clear();

            // save resource
            try {
                newResource.save(Collections.EMPTY_MAP);
            } catch (IOException e) {
                logger.error("Couldn't save resource: " + newResource.getURI().toString());
            }
        }
    }

    private void copyLeadingProjects() {
        for (String project : spLevoProject.getLeadingProjects()) {

            String projectPath = ProjectPathUtil.buildProjectPath(project);
            String projectOutputPath = (outputPath + "/" + project);

            try {
                FileUtils.copyDirectory(new File(projectPath), new File(projectOutputPath));
            } catch (IOException e) {
                logger.error("Could not copy project " + projectPath + " to " + outputPath);
            }
        }
    }

    private String buildNewResourcePath(Resource resource) {
        String resPath = resource.getURI().path();
        String projectSpecificPath = null;
        for (String leadingProject : spLevoProject.getLeadingProjects()) {
            int index = resPath.indexOf(leadingProject);

            if (index == -1) {
                continue;
            }

            projectSpecificPath = resPath.substring(index, resPath.length());
        }
        return projectSpecificPath;
    }

    private Set<Resource> extractLeadingResourcesFromVPM(VariationPointModel vpmModel) {
        Set<Resource> resources = new HashSet<Resource>();

        // iterate over all software elements in the leading project
        for (VariationPointGroup group : vpmModel.getVariationPointGroups()) {
            for (VariationPoint vp : group.getVariationPoints()) {
                for (Variant variant : vp.getVariants()) {
                    if (!variant.getLeading()) {
                        continue;
                    }

                    for (SoftwareElement softwareElement : variant.getImplementingElements()) {
                        Resource jamoppResource = ((JaMoPPSoftwareElement) softwareElement).getJamoppElement()
                                .eResource();
                        resources.add(jamoppResource);
                    }
                }
            }
        }

        return resources;
    }

    @Override
    public String getName() {
        return "Copy VPM model job.";
    }
}
