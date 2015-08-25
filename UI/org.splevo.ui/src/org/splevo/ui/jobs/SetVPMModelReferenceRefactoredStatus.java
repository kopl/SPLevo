package org.splevo.ui.jobs;

import java.util.NoSuchElementException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.project.VPMModelReference;
import org.splevo.ui.editors.SPLevoProjectEditor;

import com.google.common.collect.Iterables;

import de.uka.ipd.sdq.workflow.jobs.AbstractJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * Job to set the refactoring status of a VPMModelReference in the SPLevoProject. This job also
 * saves the project.
 */
public class SetVPMModelReferenceRefactoredStatus extends AbstractJob {

    private final SPLevoProjectEditor splevoProjectEditor;
    private final boolean refactored;

    /**
     * Constructs the job. The VPMModelReference to be changed will be the latest.
     * 
     * @param splevoProjectEditor
     *            The project editor holding the project.
     * @param refactored
     *            The new refactoring status.
     */
    public SetVPMModelReferenceRefactoredStatus(SPLevoProjectEditor splevoProjectEditor, boolean refactored) {
        this.splevoProjectEditor = splevoProjectEditor;
        this.refactored = refactored;
    }

    @Override
    public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
        // nothing to do
    }

    @Override
    public void execute(IProgressMonitor progress) throws JobFailedException, UserCanceledException {
        progress.beginTask(getName(), 2);
        try {
            VPMModelReference reference = Iterables.getLast(splevoProjectEditor.getSplevoProject()
                    .getVpmModelReferences());
            reference.setRefactoringStarted(refactored);
            progress.worked(1);
            splevoProjectEditor.doSave(null);
            progress.worked(1);
        } catch (NoSuchElementException e) {
            throw new JobFailedException("Could not determine the latest VPMModelReference.", e);
        }
        progress.done();
    }

    @Override
    public String getName() {
        return "Set refactoring status of VPM";
    }

}
