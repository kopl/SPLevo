package org.splevo.ui.editors;

import java.util.HashMap;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.splevo.ui.workflow.VPMAnalysisConfiguration;
import org.splevo.vpm.refinement.Refinement;

/**
 * Input object for the refinement browser.
 */
public class VPMRefinementBrowserInput implements IEditorInput {
	
	/** The refinements to be presented. */
	private HashMap<VPMAnalysisConfiguration, List<Refinement>> refinements = new HashMap<VPMAnalysisConfiguration, List<Refinement>>();
	
	/** The splevo editor originally trigger the process. */
	private SPLevoProjectEditor splevoEditor = null;

	/**
	 * Constructor requiring a reference to the refinements to present.
	 * @param refinements The refinements map.
	 */
	public VPMRefinementBrowserInput(HashMap<VPMAnalysisConfiguration, List<Refinement>> refinements, SPLevoProjectEditor splevoEditor) {
		this.refinements = refinements;
		this.splevoEditor = splevoEditor;
	}
	
	/**
	 * @return the refinements
	 */
	public HashMap<VPMAnalysisConfiguration, List<Refinement>> getRefinements() {
		return refinements;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return "Recommended VPM Refinements";
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return "Presents the recommended VPM refinements";
	}

	/**
	 * @return the splevoEditor
	 */
	public SPLevoProjectEditor getSplevoEditor() {
		return splevoEditor;
	}

}
