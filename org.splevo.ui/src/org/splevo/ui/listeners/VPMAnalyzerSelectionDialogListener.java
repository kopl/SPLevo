package org.splevo.ui.listeners;


import java.util.ArrayList;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.splevo.ui.wizards.VPMAnalyzerSelectionPage;
import org.splevo.vpm.refinement.VPMRefinementAnalyzer;
import org.splevo.vpm.refinement.VPMRefinementService;

/**
 * Listener to open a dialog for selecting a vpm analysis to be performed.
 */
public class VPMAnalyzerSelectionDialogListener extends MouseAdapter {
	
	/** The wizard page to update. */
	private VPMAnalyzerSelectionPage page = null;
	
	/** 
	 * Constructor requiring a reference to the listed to be filled.
	 * 
	 * @param listAvailableAnalyses
	 */
	public VPMAnalyzerSelectionDialogListener(VPMAnalyzerSelectionPage page) {
		this.page = page;
	}

	/**
	 * Mouse handler to open the dialog.
	 * @param The mouse event to process.
	 */
	@Override
	public void mouseUp(MouseEvent e) {
		
		Shell shell = e.widget.getDisplay().getShells()[0];
		VPMRefinementService vpmRefinementService = new VPMRefinementService();
		ArrayList<VPMRefinementAnalyzer> availableAnalyzer = vpmRefinementService.getAvailableAnalyzers();
		
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(
				shell, new VPMAnalyzerLabelProvider());
		dialog.setTitle("VPM Analyzer Selection");
		dialog.setMessage("Select an analyzer by name (* = any string, ? = any char):");
		dialog.setElements(availableAnalyzer.toArray());
		dialog.setMultipleSelection(false);
		int result = dialog.open();
		if(result == Window.OK){
			Object analyzerObject = dialog.getFirstResult();
			if(analyzerObject != null){
				VPMRefinementAnalyzer analyzer = (VPMRefinementAnalyzer) analyzerObject;
				page.addAnalyzer(analyzer);
			}
		}
	}

}
