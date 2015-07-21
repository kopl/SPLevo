package org.splevo.ui.vpexplorer.util;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.splevo.project.SPLevoProject;
import org.splevo.project.VPMModelReference;
import org.splevo.ui.commons.util.WorkspaceUtil;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Job for switching back a VPM version. This changes the current VPM version to the given one and
 * removed all newer versions. It removes the obsolete VPMs from the project and deletes the
 * corresponding files. Afterwards the VPM related view parts are reinitialized with the new current
 * VPM versions.
 */
public class SwitchBackVPMJob extends Job {

    private static final Logger LOGGER = Logger.getLogger(SwitchBackVPMJob.class);
    private final SPLevoProject splevoProject;
    private final VPMModelReference vpmReference;

    /**
     * Constructs the switch back job.
     * @param splevoProject The project containing the VPM to revert to.
     * @param vpmReference The reference to the VPM as noted in the project model.
     */
    public SwitchBackVPMJob(SPLevoProject splevoProject, VPMModelReference vpmReference) {
        super("Switch Back VPM Version");
        this.splevoProject = splevoProject;
        this.vpmReference = vpmReference;
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
        monitor.beginTask("Switch Back VPM Version", IProgressMonitor.UNKNOWN);

        monitor.subTask("Remove obsolete VPMs from project");
        Iterable<VPMModelReference> filteredModels = Iterables.filter(splevoProject.getVpmModelReferences(), new Predicate<VPMModelReference>() {
            private boolean afterSelected = false;

            @Override
            public boolean apply(VPMModelReference arg0) {
                if (afterSelected) {
                    return true;
                }
                afterSelected = arg0.equals(vpmReference);
                return false;
            }
        });
        List<VPMModelReference> obsoleteModels = Lists.newArrayList(filteredModels);

        for (VPMModelReference reference : obsoleteModels) {
            new File(WorkspaceUtil.getAbsoluteFromWorkspaceRelativePath(reference.getPath())).delete();
        }
        splevoProject.getVpmModelReferences().removeAll(obsoleteModels);

        monitor.subTask("Save project");
        try {
            splevoProject.eResource().save(Collections.EMPTY_MAP);
        } catch (IOException e) {
            LOGGER.error("Unable to save project after removing VPMs.", e);
            return new Status(Status.ERROR, "Error",
                    "We could not save the project after removing obsolete variation point models.");
        }

        monitor.subTask("Shedule loading of the new VPM");
        VPMUIUtil.openVPExplorer(splevoProject, vpmReference);

        monitor.done();
        return Status.OK_STATUS;
    }

}
