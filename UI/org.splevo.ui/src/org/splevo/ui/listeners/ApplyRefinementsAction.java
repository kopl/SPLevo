package org.splevo.ui.listeners;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.splevo.ui.refinementbrowser.VPMRefinementBrowser;
import org.splevo.ui.workflow.VPMRefinementWorkflowConfiguration;
import org.splevo.ui.workflow.VPMRefinementWorkflowDelegate;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * An action to trigger the application of a list of refinements to variation point model.
 */
public class ApplyRefinementsAction extends Action {
	
	/** The refinement viewer to access the selected refinements. */
	private VPMRefinementBrowser vpmRefinementBrowser = null;

	/**
	 * Constructor requiring a reference to the viewer to get the 
	 * refinements to be executed from as well as the text of the action.
	 * 
	 * @param vpmRefinementBrowser The reference to the viewer.
	 * @param text The text for the action.
	 */
	public ApplyRefinementsAction(VPMRefinementBrowser vpmRefinementBrowser,String text) {
		super(text);
		this.vpmRefinementBrowser = vpmRefinementBrowser;
	}
	
	/**
	 * Apply the refinement by 
	 * - checking that refinements have been selected
	 * - configuring and running the workflow
	 * - closing the browser 
	 */
	@Override
	public void runWithEvent(Event event) {
		
		List<Refinement> refinements = this.vpmRefinementBrowser.getSelectedRefinements();
		
		// handle an empty selection by asking the user how to proceed
		if(refinements.size() == 0){
			Shell shell = event.widget.getDisplay().getShells()[0];
			boolean cancel = MessageDialog.openQuestion(shell, "SPLevo Info", "No Refinements selected. Do you want to cancel the analysis?");
			if(cancel == false){
				return;
			}
		} else {
			// Initialize the requried data
			//TODO Clean up the access to the variation point model
			VariationPointGroup group = (VariationPointGroup) refinements.get(0).getVariationPoints().get(0).eContainer();
			VariationPointModel model = (VariationPointModel) group.eContainer();
			VPMRefinementWorkflowConfiguration config = buildWorflowConfiguration(refinements);
			config.setVariationPointModel(model);
			VPMRefinementWorkflowDelegate workflowDelegate = new VPMRefinementWorkflowDelegate(config);
			workflowDelegate.run(this);
		}
		
		// close the browser
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();	
		page.closeEditor(vpmRefinementBrowser, false);
	}

	/**
	 * Build the configuration for the workflow.
	 * @return The prepared configuration.
	 */
	private VPMRefinementWorkflowConfiguration buildWorflowConfiguration(List<Refinement> refinements) {
		VPMRefinementWorkflowConfiguration config = new VPMRefinementWorkflowConfiguration();
		config.setSplevoProjectEditor(vpmRefinementBrowser.getSPLevoProjectEditor());
		config.getRefinements().addAll(refinements);
		return config;
	}

	
	
	
}
