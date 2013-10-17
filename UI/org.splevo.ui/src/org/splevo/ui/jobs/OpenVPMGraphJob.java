package org.splevo.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.splevo.ui.views.vpmgraph.VPMGraphView;
import org.splevo.vpm.analyzer.graph.VPMGraph;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * Job to open the VPMGraph currently present in the blackboard in a viewer.
 */
public class OpenVPMGraphJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /**
     * Build up the editor input with the analysis results stored in the blackboard and start the
     * refinement browser. {@inheritDoc}
     */
    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

        VPMGraph graph = getBlackboard().getVpmGraph();
        if (graph == null) {
            logger.error("No VPMGraph loaded in blackboard.");
            Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
            MessageDialog.openError(shell, "VPMGraph not available",
                    "No Variation Point Graph detected in the SPLevo blackboard");
            throw new JobFailedException("Editor could not be opened", new NullPointerException(
                    "No VPM Graph in blackboard"));
        }

        // open the viewer containing the graph
        IViewPart viewPart;
        try {
            viewPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                    .showView(VPMGraphView.ID, "vpmgraph" + graph.getId(), IWorkbenchPage.VIEW_ACTIVATE);
        } catch (PartInitException e) {
            throw new JobFailedException("Unable to open VPM Graph view.", e);
        }

        final VPMGraphView view = (VPMGraphView) viewPart;
        view.showGraph(graph);

        PlatformUI.getWorkbench().addWorkbenchListener(new IWorkbenchListener() {

            @Override
            public boolean preShutdown(IWorkbench workbench, boolean forced) {
                IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                activePage.hideView(view);
                return true;
            }

            @Override
            public void postShutdown(IWorkbench workbench) {
            }
        });
    }

    @Override
    public String getName() {
        return "Open VPM Refinement Browser Job";
    }

    @Override
    public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
    }

}
