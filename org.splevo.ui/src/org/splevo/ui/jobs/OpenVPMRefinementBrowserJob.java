package org.splevo.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.refinementbrowser.VPMRefinementBrowser;
import org.splevo.ui.refinementbrowser.VPMRefinementBrowserInput;

import de.uka.ipd.sdq.workflow.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;

/**
 * Job to open the VPM refinement browser with the result of the analysis.
 */
public class OpenVPMRefinementBrowserJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The internal reference to the splevo project editor to work with. */
    private SPLevoProjectEditor splevoProjectEditor = null;

    /**
     * Constructor requiring to set the splevo project editor reference.
     * 
     * @param splevoProjectEditor
     *            The editor to trigger the process.
     */
    public OpenVPMRefinementBrowserJob(SPLevoProjectEditor splevoProjectEditor) {
        this.splevoProjectEditor = splevoProjectEditor;
    }

    /**
     * Build up the editor input with the analysis results stored in the blackboard and start the
     * refinement browser. {@inheritDoc}
     */
    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

        try {
            VPMRefinementBrowserInput input = new VPMRefinementBrowserInput(getBlackboard().getRefinementModel()
                    .getRefinements(), this.splevoProjectEditor);
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                    .openEditor(input, VPMRefinementBrowser.ID);
        } catch (PartInitException e) {
            throw new JobFailedException("Editor could not be opened", e);
        }
    }

    @Override
    public void rollback(IProgressMonitor monitor) throws RollbackFailedException {
        // nothing to roll back
    }

    @Override
    public String getName() {
        return "Open VPM Refinement Browser Job";
    }

}
