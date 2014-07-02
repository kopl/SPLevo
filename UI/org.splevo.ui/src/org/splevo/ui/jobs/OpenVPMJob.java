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
import org.splevo.ui.util.VPMUIUtil;
import org.splevo.vpm.variability.VariationPointModel;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;

/**
 * Job to open the variation point model in the VP Explorer.
 */
public class OpenVPMJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException {

        VariationPointModel vpm = getBlackboard().getVariationPointModel();

        logger.info("Trigger Job to Open VP Explorer");
        VPMUIUtil.openVPExplorer(vpm);

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
