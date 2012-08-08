package org.splevo.diffing;

import java.io.IOException;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.diff.engine.GenericDiffEngine;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.match.MatchOptions;
import org.eclipse.emf.compare.match.engine.DefaultMatchScopeProvider;
import org.eclipse.emf.compare.match.engine.IMatchEngine;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.compare.match.metamodel.UnmatchElement;
import org.eclipse.emf.compare.util.EMFCompareMap;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.splevo.diffing.emfcompare.match.KdmMatchEngine;
import org.splevo.diffing.kdm.JavaModelElementPrinter;

/**
 * Diffing Service that directly builds up the diffing model instead of using a post 
 * processor based on the diffing identified by emf compare.
 * 
 * @author Benjamin Klatt
 *
 */
public class MatchEngineDiffingService {

	// TODO: extend to more than one input model
	public DiffModel doDiff(JavaApplication leadingModel, JavaApplication integrationModel) throws IOException, InterruptedException {
		
		
		org.eclipse.gmt.modisco.java.Model leadingJavaModel = leadingModel.getJavaModel();
		org.eclipse.gmt.modisco.java.Model integrationJavaModel = integrationModel.getJavaModel();

		// configure the match engine
		final Map<String, Object> matchOptions = new EMFCompareMap<String, Object>();
		DefaultMatchScopeProvider matchScopeProvider = new DefaultMatchScopeProvider(leadingJavaModel, integrationJavaModel);
		matchOptions.put(MatchOptions.OPTION_MATCH_SCOPE_PROVIDER,matchScopeProvider);

		JavaModelElementPrinter elementPrinter = new JavaModelElementPrinter();
		
		System.out.println("=======================================================");
		System.out.println("==================== MATCHING PHASE  ==================");
		System.out.println("=======================================================");
		IMatchEngine matchEngine = new KdmMatchEngine();
		MatchModel matchModel = matchEngine.modelMatch(leadingJavaModel, integrationJavaModel, matchOptions);
		
		System.out.println("==========================");
		System.out.println("=== UNMATCHED ELEMENTS ===");
		System.out.println("==========================");
		EList<UnmatchElement> unmatchedElements = matchModel.getUnmatchedElements();
		for (UnmatchElement unmatchedElement : unmatchedElements) {
			 System.out.print(unmatchedElement.getSide()+"\t");
			 System.out.print(unmatchedElement.getElement().getClass().getSimpleName()+"\t");
			 System.out.print(elementPrinter.printElement(unmatchedElement.getElement()));
			 System.out.println();
		}
//		System.out.println("");
//		System.out.println("");
//		System.out.println("==========================");
//		System.out.println("=== MATCHED ELEMENTS ===");
//		System.out.println("==========================");
//		EList<MatchElement> matchedElements = matchModel.getMatchedElements();
//		for (MatchElement matchedElement : matchedElements) {
//			printMatchedElement(matchedElement,0);
//		}
		
		
		System.out.println("=======================================================");
		System.out.println("==================== DIFFING PHASE  ===================");
		System.out.println("=======================================================");
		
		DiffModel diffModel = new GenericDiffEngine().doDiff(matchModel, false);
		
		
//		System.out.println("=======================================================");
//		System.out.println("==================== POST DIFFING PHASE  ==============");
//		System.out.println("=======================================================");
//
//		DiffModelPostProcessor postProcessor = new DiffModelPostProcessor();
//		DiffModel enhancedDiffModel = postProcessor.process(diffModel);
		
		
		//DiffNodeTransformer diffSwitch = new DiffNodeTransformer();
		
		return diffModel;
		
		
	}

}
