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
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.splevo.project.SPLevoProject;
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
    private int targetVPMIndex = -1;

    /** Option to let EMF Text resources load layout information. */
    private boolean loadLayoutInformation = false;

    /**
     * Constructor to set a reference to the splevoproject.
     *
     * @param splevoProject
     *            The reference to the splevoproject.
     */
    public LoadVPMJob(SPLevoProject splevoProject) {
        this.splevoProject = splevoProject;
        this.targetVPMIndex = -1;
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
     * @param loadLayoutInformation
     *            Option to let EMF Text resources load layout information.
     */
    public LoadVPMJob(SPLevoProject splevoProject, int targetVPMIndex, boolean loadLayoutInformation) {
        this.splevoProject = splevoProject;
        this.targetVPMIndex = targetVPMIndex;
        this.loadLayoutInformation = loadLayoutInformation;
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException {

        logger.info("Load vpm model");
        variabilityPackage.eINSTANCE.eClass();
        SoftwarePackage.eINSTANCE.eClass();

        int index = targetVPMIndex;
        if (targetVPMIndex == -1) {
            index = splevoProject.getVpmModelPaths().size() - 1;
        }

        ResourceSet resourceSet = JobUtil.initResourceSet(splevoProject);
        if (!loadLayoutInformation) {
            Map<Object, Object> loadOptions = resourceSet.getLoadOptions();
            loadOptions.put("DISABLE_LAYOUT_INFORMATION_RECORDING", Boolean.TRUE);
        }

        VariationPointModel vpm;
        try {
            File vpmFile = new File(splevoProject.getVpmModelPaths().get(index));
            vpm = VPMUtil.loadVariationPointModel(vpmFile, resourceSet);
        } catch (Exception e) {
            throw new JobFailedException("Failed to load vpm model.", e);
        }

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
