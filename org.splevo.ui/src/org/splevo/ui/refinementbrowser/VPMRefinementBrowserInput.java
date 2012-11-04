package org.splevo.ui.refinementbrowser;

import java.util.HashMap;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.VPMRefinementAnalyzer;

/**
 * Input object for the refinement browser.
 */
public class VPMRefinementBrowserInput implements IEditorInput {
	
	/** The refinements to be presented. */
	private HashMap<VPMRefinementAnalyzer, List<Refinement>> refinements = new HashMap<VPMRefinementAnalyzer, List<Refinement>>();
	
	/** The splevo editor originally trigger the process. */
	private SPLevoProjectEditor splevoEditor = null;

	/**
	 * Constructor requiring a reference to the refinements to present.
	 * @param refinements The refinements map.
	 */
	public VPMRefinementBrowserInput(HashMap<VPMRefinementAnalyzer, List<Refinement>> refinements, SPLevoProjectEditor splevoEditor) {
		this.refinements = refinements;
		this.splevoEditor = splevoEditor;
	}
	
	/**
	 * @return the refinements
	 */
	public HashMap<VPMRefinementAnalyzer, List<Refinement>> getRefinements() {
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
