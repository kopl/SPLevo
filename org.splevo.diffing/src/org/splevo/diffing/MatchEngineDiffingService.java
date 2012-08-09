package org.splevo.diffing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.match.MatchOptions;
import org.eclipse.emf.compare.match.engine.DefaultMatchScopeProvider;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.compare.match.metamodel.UnmatchElement;
import org.eclipse.emf.compare.util.EMFCompareMap;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.splevo.diffing.emfcompare.diff.JavaModelDiffEngine;
import org.splevo.diffing.emfcompare.match.JavaModelMatchEngine;
import org.splevo.diffing.kdm.JavaModelElementPrinter;

/**
 * Diffing Service that directly builds up the diffing model instead of using a
 * post processor based on the diffing identified by emf compare.
 * 
 * @author Benjamin Klatt
 * 
 */
public class MatchEngineDiffingService {

	/** Regular expressions defining packages to be ignored. */
	private List<String> ignorePackages = new ArrayList<String>();

	/**
	 * Perform the diffing process for two modisco JavaApplicationModels.
	 * 
	 * @param leadingModel The leading java model
	 * @param integrationModel The java model to be integrated and compared to the original one.
	 * @return The difference model.
	 * @throws InterruptedException Identifying that the match engine has been interrupted.
	 */
	public DiffModel doDiff(JavaApplication leadingModel,JavaApplication integrationModel)
			throws InterruptedException {

		Model leadingJavaModel = leadingModel.getJavaModel();
		Model integrationJavaModel = integrationModel.getJavaModel();

		// configure the match engine
		final Map<String, Object> matchOptions = new EMFCompareMap<String, Object>();
		DefaultMatchScopeProvider matchScopeProvider = new DefaultMatchScopeProvider(leadingJavaModel, integrationJavaModel);
		matchOptions.put(MatchOptions.OPTION_MATCH_SCOPE_PROVIDER,matchScopeProvider);

		JavaModelElementPrinter elementPrinter = new JavaModelElementPrinter();

		System.out.println("================ MATCHING PHASE  ===============");
		JavaModelMatchEngine matchEngine = new JavaModelMatchEngine();
		MatchModel matchModel = matchEngine.modelMatch(leadingJavaModel,integrationJavaModel, matchOptions);

		System.out.println("=== UNMATCHED ELEMENTS ===");
		EList<UnmatchElement> unmatchedElements = matchModel.getUnmatchedElements();
		for (UnmatchElement unmatchedElement : unmatchedElements) {
			System.out.print(unmatchedElement.getSide() + "\t");
			System.out.print(unmatchedElement.getElement().getClass().getSimpleName()+ "\t");
			System.out.print(elementPrinter.printElement(unmatchedElement.getElement()));
			System.out.println();
		}
		// System.out.println("");
		// System.out.println("");
		// System.out.println("==========================");
		// System.out.println("=== MATCHED ELEMENTS ===");
		// System.out.println("==========================");
		// EList<MatchElement> matchedElements =
		// matchModel.getMatchedElements();
		// for (MatchElement matchedElement : matchedElements) {
		// printMatchedElement(matchedElement,0);
		// }

		System.out.println("==================== DIFFING PHASE  ===================");
		JavaModelDiffEngine javaModelDiffEngine = new JavaModelDiffEngine();
		javaModelDiffEngine.getIgnorePackages().addAll(this.ignorePackages);
		DiffModel diffModel = javaModelDiffEngine.doDiff(matchModel,false);

		// System.out.println("=======================================================");
		// System.out.println("==================== POST DIFFING PHASE  ==============");
		// System.out.println("=======================================================");
		//
		// DiffModelPostProcessor postProcessor = new DiffModelPostProcessor();
		// DiffModel enhancedDiffModel = postProcessor.process(diffModel);

		// DiffNodeTransformer diffSwitch = new DiffNodeTransformer();

		return diffModel;

	}

	/**
	 * Get the regular expressions defining packages to be ignored.
	 * @return the ignorePackages
	 */
	public List<String> getIgnorePackages() {
		return ignorePackages;
	}

}
