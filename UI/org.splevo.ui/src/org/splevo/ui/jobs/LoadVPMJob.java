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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.splevo.project.SPLevoProject;
import org.splevo.project.VPMModelReference;
import org.splevo.ui.commons.util.JobUtil;
import org.splevo.vpm.VPMUtil;
import org.splevo.vpm.software.SoftwarePackage;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityPackage;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;

/**
 * Job to load a variation point model into the blackboard.
 */
public class LoadVPMJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The splevo project to get the vpm info from. */
    private SPLevoProject splevoProject;

    /**
     * The index of the vpm to be loaded. If this is -1 the latest one will be loaded.
     */
    private final int targetVPMIndex;

    /**
     * Constructor to set a reference to the splevoproject.
     *
     * @param splevoProject
     *            The reference to the splevoproject.
     */
    public LoadVPMJob(SPLevoProject splevoProject) {
        this(splevoProject, -1);
    }

    /**
     * Constructor to set a reference to the splevo project and the index of the vpm to be loaded.
     *
     * By default, the job configures EMF Text resources to not load layout information. This allows
     * to speed up model loading. However, this can issue problems when layout information are
     * necessary such as printing the model. Make sure to activate this option in such cases.
     *
     * @param splevoProject
     *            The reference to the splevo project.
     * @param targetVPMIndex
     *            The index of the VPM to load.
     */
    public LoadVPMJob(SPLevoProject splevoProject, int targetVPMIndex) {
        this.splevoProject = splevoProject;
        this.targetVPMIndex = targetVPMIndex;
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException {

        logger.info("Load vpm model");
        variabilityPackage.eINSTANCE.eClass();
        SoftwarePackage.eINSTANCE.eClass();

        int index = targetVPMIndex;
        if (targetVPMIndex == -1) {
            index = splevoProject.getVpmModelReferences().size() - 1;
        }

        VPMModelReference vpmReference = splevoProject.getVpmModelReferences().get(index);
        
        ResourceSet resourceSet = JobUtil.initResourceSet(splevoProject, vpmReference.isRefactoringStarted());

        VariationPointModel vpm;
        try {
            File vpmFile = new File(vpmReference.getPath());
            vpm = VPMUtil.loadVariationPointModel(vpmFile, resourceSet);
        } catch (Exception e) {
            throw new JobFailedException("Failed to load vpm model.", e);
        }
        
        // resolve VPM
        EcoreUtil.resolveAll(vpm);

        logger.info("Put variation point model on the blackboard");
        getBlackboard().setVariationPointModel(vpm);

        // finish run
        monitor.done();
    }

    @Override
    public String getName() {
        return "Load VPM model Job";
    }

    @Override
    public void cleanup(IProgressMonitor arg0) {
    }
}
