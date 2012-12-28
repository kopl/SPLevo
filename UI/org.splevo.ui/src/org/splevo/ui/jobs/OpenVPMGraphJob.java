package org.splevo.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.graphstream.ui.swingViewer.Viewer;
import org.graphstream.ui.swingViewer.Viewer.CloseFramePolicy;
import org.splevo.vpm.analyzer.graph.CustomLabelAttributeProxy;
import org.splevo.vpm.analyzer.graph.RelationshipEdge;
import org.splevo.vpm.analyzer.graph.VPMGraph;

import de.uka.ipd.sdq.workflow.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;

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

        Viewer v = new Viewer(new CustomLabelAttributeProxy(graph, RelationshipEdge.RELATIONSHIP_LABEL));
        v.enableAutoLayout();
        v.addDefaultView(true);
        v.setCloseFramePolicy(CloseFramePolicy.HIDE_ONLY);
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
