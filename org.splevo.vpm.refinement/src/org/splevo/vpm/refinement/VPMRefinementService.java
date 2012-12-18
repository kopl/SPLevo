package org.splevo.vpm.refinement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Class providing refinement services for a variation point model.
 */
public class VPMRefinementService {

	/** The Constant WorkflowExtensionPointId. */
	private static final String VPM_ANALYZER_EXTENSION_POINT_ID = "org.splevo.vpm.refinement.analyzer";

	/** The Constant ExtensionPointAttribute_AnalyzerClass. */
	private static final String EXTENSION_POINT_ATTR_ANALYZER_CLASS = "analyzer.class";

	/** The logger for this class. */
	private Logger logger = Logger.getLogger(VPMRefinementService.class);

	/**
	 * Identify the refinements recommended for the variation point model.
	 * 
	 * This method can be used to apply a single analyzer. 
	 * It does not return a complete refinement model, to be more useful 
	 * when multiple analyzers should be executed in parallel and the results
	 * should be merged into a variation point model afterwards.
	 * Such a merge would also require to merge include the analyzer configuration.
	 * 
	 * @param vpm
	 *            The model to be analyzed.
	 * @param analyzer The analyzer to be executed.
	 * @return The list of recommended refinements.
	 */
	public List<Refinement> identifyRefinements(VariationPointModel vpm,
			VPMRefinementAnalyzer analyzer) {
		List<Refinement> refinements = new ArrayList<Refinement>();

		List<Refinement> locationBasedRefinements = analyzer.analyze(vpm);
		refinements.addAll(locationBasedRefinements);

		return refinements;
	}

	/**
	 * Apply a list of refinements to a variation point model.
	 * 
	 * TODO Implement refinement application for merge type
	 * 
	 * @param refinements
	 *            The refinements to be applied.
	 * @param vpm
	 *            The variation model to apply the refinements to.
	 */
	public VariationPointModel applyRefinements(List<Refinement> refinements,
			VariationPointModel vpm) {

		for (Refinement refinement : refinements) {

			if (refinement.getType().equals(RefinementType.GROUPING)) {

				// The group of the first variation point is kept as surviving
				// group
				// The variation points of all others are moved to this group
				// and the other groups are removed.
				VariationPointGroup survivingGroup = refinement
						.getVariationPoints().get(0).getGroup();
				for (VariationPoint vp : refinement.getVariationPoints()) {
					if (!vp.getGroup().equals(survivingGroup)) {
						VariationPointGroup oldGroup = vp.getGroup();
						vp.setGroup(survivingGroup);
						vpm.getVariationPointGroups().remove(oldGroup);
					}
				}
			}
		}

		return vpm;
	}

	/**
	 * Get the analyzers registered for the analyzer extension point.
	 * 
	 * @return The list of available analyzers.
	 */
	public ArrayList<VPMRefinementAnalyzer> getAvailableAnalyzers() {
		ArrayList<VPMRefinementAnalyzer> refinementAnalyses = new ArrayList<VPMRefinementAnalyzer>();
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		if (registry == null) {
			logger.warn("No extension point registry available.");
			return null;
		}
		IExtensionPoint extensionPoint = registry
				.getExtensionPoint(VPM_ANALYZER_EXTENSION_POINT_ID);
		if (extensionPoint == null) {
			logger.warn("No extension point found for the ID "
					+ VPM_ANALYZER_EXTENSION_POINT_ID);
			return null;
		}
		IExtension[] extensions = extensionPoint.getExtensions();
		for (IExtension extension : extensions) {
			IConfigurationElement[] configurations = extension
					.getConfigurationElements();
			for (IConfigurationElement element : configurations) {
				try {
					Object o = element
							.createExecutableExtension(EXTENSION_POINT_ATTR_ANALYZER_CLASS);
					if ((o != null) && (o instanceof VPMRefinementAnalyzer)) {
						VPMRefinementAnalyzer analyzer = (VPMRefinementAnalyzer) o;
						refinementAnalyses.add(analyzer);
					}
				} catch (CoreException e) {
					logger.error("Failed to load VPM analyzer extension", e);
				}
			}
		}
		return refinementAnalyses;
	}
}
