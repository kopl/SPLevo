package org.splevo.ui.listeners;

import org.eclipse.jface.viewers.LabelProvider;
import org.splevo.vpm.refinement.VPMRefinementAnalyzer;

/**
 * Label provider for vpm refinement analyzers returning the name of the analyzer.
 */
public class VPMAnalyzerLabelProvider extends LabelProvider {

	/**
	 * Get the text for the label.
	 * @param element The object which is expected to be an VPMRefinementAnalyzer
	 * @return The name of the vpm refinement analyzer as the label to present..
	 */
	@Override
	public String getText(Object element) {
		return ((VPMRefinementAnalyzer) element).getName();
	}
}