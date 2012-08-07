package org.splevo.diffing;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.diff.engine.GenericDiffEngine;
import org.eclipse.emf.compare.diff.metamodel.ComparisonResourceSetSnapshot;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffFactory;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.metamodel.DiffResourceSet;
import org.eclipse.emf.compare.diff.metamodel.impl.ModelElementChangeLeftTargetImpl;
import org.eclipse.emf.compare.match.MatchOptions;
import org.eclipse.emf.compare.match.engine.DefaultMatchScopeProvider;
import org.eclipse.emf.compare.match.engine.IMatchEngine;
import org.eclipse.emf.compare.match.metamodel.Match2Elements;
import org.eclipse.emf.compare.match.metamodel.MatchElement;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.compare.match.metamodel.MatchResourceSet;
import org.eclipse.emf.compare.match.metamodel.UnmatchElement;
import org.eclipse.emf.compare.match.service.MatchService;
import org.eclipse.emf.compare.util.EMFCompareMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmt.modisco.java.emf.impl.ClassInstanceCreationImpl;
import org.eclipse.gmt.modisco.java.emf.impl.JavadocImpl;
import org.eclipse.gmt.modisco.java.emf.impl.MethodInvocationImpl;
import org.eclipse.gmt.modisco.java.emf.impl.PackageImpl;
import org.eclipse.gmt.modisco.java.emf.impl.SingleVariableAccessImpl;
import org.eclipse.gmt.modisco.java.emf.impl.TextElementImpl;
import org.eclipse.gmt.modisco.java.emf.impl.TypeAccessImpl;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.splevo.diffing.emfcompare.merge.KdmMatchEngine;

/**
 * Diffing Service that directly builds up the diffing model instead of using a post 
 * processor based on the diffing identified by emf compare.
 * 
 * @author Benjamin Klatt
 *
 */
public class MatchEngineDiffingService implements DiffingService {

	// TODO: extend to more than one input model
	public DiffModel doDiff(JavaApplication leadingModel, JavaApplication integrationModel) throws IOException, InterruptedException {
		
		
		org.eclipse.gmt.modisco.java.Model leadingJavaModel = leadingModel.getJavaModel();
		org.eclipse.gmt.modisco.java.Model integrationJavaModel = integrationModel.getJavaModel();

		// configure the match engine
		final Map<String, Object> matchOptions = new EMFCompareMap<String, Object>();
		DefaultMatchScopeProvider matchScopeProvider = new DefaultMatchScopeProvider(leadingJavaModel, integrationJavaModel);
		matchOptions.put(MatchOptions.OPTION_MATCH_SCOPE_PROVIDER,matchScopeProvider);
		
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
			 System.out.print(buildOutput(unmatchedElement.getElement()));
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
		
		DiffModel diff = new GenericDiffEngine().doDiff(matchModel, false);
		return diff;

//		EList<DiffElement> diffElements = diff.getDifferences();
//		for (DiffElement diffElement : diffElements) {
//			switch (diffElement.getKind()) {
//			case ADDITION:
//				printADDITION(diffElement);
//				break;
//
//			default:
//				System.out.println(diffElement.getKind().getName() + "\t" + diffElement.getClass().getSimpleName());
//				break;
//			}
//		}
		
		
	}

	private void printADDITION(DiffElement diffElement) {
		System.out.println("ADDITION\t" + diffElement.getSubDiffElements().size());
		if(diffElement instanceof ModelElementChangeLeftTargetImpl){
			
		}
	}

	@Override
	public ComparisonResourceSetSnapshot getDiff(List<File> leftASTModels,
			List<File> rightASTModels) throws IOException, InterruptedException {

		ResourceSet leftResourceSet = loadModelResourceSet(leftASTModels);
		ResourceSet rightResourceSet = loadModelResourceSet(rightASTModels);

		final MatchResourceSet matchResourceSet = buildMatchRessourceSet(leftResourceSet, rightResourceSet);

		DiffResourceSet diffSet = DiffFactory.eINSTANCE.createDiffResourceSet();
		
		for (final MatchModel match : matchResourceSet.getMatchModels()) {
			
			System.out.println("==========================");
			System.out.println("=== UNMATCHED ELEMENTS ===");
			System.out.println("==========================");
			EList<UnmatchElement> unmatchedElements = match.getUnmatchedElements();
			for (UnmatchElement unmatchedElement : unmatchedElements) {
				 System.out.print(unmatchedElement.getSide()+"\t");
				 System.out.print(unmatchedElement.getElement().getClass().getSimpleName()+"\t");
				 System.out.print(buildOutput(unmatchedElement.getElement()));
				 System.out.println();
			}
			System.out.println("");
			System.out.println("");
			System.out.println("==========================");
			System.out.println("=== MATCHED ELEMENTS ===");
			System.out.println("==========================");
			EList<MatchElement> matchedElements = match.getMatchedElements();
			for (MatchElement matchedElement : matchedElements) {
				printMatchedElement(matchedElement,0);
			}
		}
		
        // build and return the snapshot
		ComparisonResourceSetSnapshot snapshot = buildResourceSetComparisionSnapshot(matchResourceSet, diffSet);
		
		return snapshot;
	}

	/**
	 * Print out a MatchedElement returned from a match engine to the console. 
	 * @param matchedElement
	 * @param indentIndex
	 */
	private void printMatchedElement(MatchElement matchedElement, int indentIndex) {

		StringBuilder indent = new StringBuilder();
		for (int i = 0; i < indentIndex; i++) {
			indent.append(" ");
		}
		
		if(matchedElement instanceof Match2Elements){
			Match2Elements element = (Match2Elements) matchedElement;
			System.out.println(indent+"Left\t"+buildOutput(element.getLeftElement()));
			System.out.println(indent+"Right\t"+buildOutput(element.getRightElement()));
		} else {
			System.out.println(indent+matchedElement.getSubMatchElements().toString());
		}
		System.out.println(indent+"~~~~~~~~~~~~~~~");
		
		for (MatchElement subMatchedElement : matchedElement.getSubMatchElements()) {
			printMatchedElement(subMatchedElement,indentIndex++);
		}
		
	}

	/**
	 * Build the AST class specific output
	 * @param element
	 * @return The prepared output string
	 */
	private String buildOutput(EObject element) {
		if(element instanceof ClassInstanceCreationImpl){
			return buildOutput((ClassInstanceCreationImpl) element);
		} else if(element instanceof PackageImpl){
			return buildOutput((PackageImpl) element);
		} else if(element instanceof TextElementImpl){
			return buildOutput((TextElementImpl) element);
		} else if(element instanceof MethodInvocationImpl){
			return buildOutput((MethodInvocationImpl) element);
		} else if(element instanceof TypeAccessImpl){
			return buildOutput((TypeAccessImpl) element);
		} else if(element instanceof JavadocImpl){
			return buildOutput((JavadocImpl) element);
		} else if(element instanceof SingleVariableAccessImpl){
			return buildOutput((SingleVariableAccessImpl) element);
		} else {
			return element.toString();
		}
	}
	
	private String buildOutput(SingleVariableAccessImpl element) {
		return "SingleVariableAccessImpl "+element.getVariable().getName();
	}
	
	private String buildOutput(JavadocImpl element) {
		return "JavadocImpl";
	}
	
	private String buildOutput(ClassInstanceCreationImpl element) {
		return element.getType().getType().getName();
	}
	
	private String buildOutput(TypeAccessImpl element) {
		return element.getType().getName();
	}
	
	private String buildOutput(PackageImpl element) {
		return element.getName();
	}
	
	private String buildOutput(TextElementImpl element) {
		return element.getComments().toString();
	}
	
	private String buildOutput(MethodInvocationImpl element) {
		return element.getMethod().getName();
	}

	/**
	 * Build the match ressource set.
	 * 
	 * @param leftResourceSet
	 * @param rightResourceSet
	 * @return
	 * @throws InterruptedException
	 */
	private MatchResourceSet buildMatchRessourceSet(
			ResourceSet leftResourceSet, ResourceSet rightResourceSet)
			throws InterruptedException {
		
		// configure the match engine
		final Map<String, Object> matchOptions = new EMFCompareMap<String, Object>();
		DefaultMatchScopeProvider matchScopeProvider = new DefaultMatchScopeProvider(leftResourceSet, rightResourceSet);
		matchOptions.put(MatchOptions.OPTION_MATCH_SCOPE_PROVIDER,matchScopeProvider);
		
		MatchResourceSet matchResourceSet = MatchService.doResourceSetMatch(leftResourceSet, rightResourceSet,matchOptions);
		return matchResourceSet;
	}

	/**
	 * Create the comparsion resource snapshot combining the match and diff
	 * references as done by the default emf compare.
	 * 
	 * @param matchModel
	 *            matched model generated by EMF Compare
	 * @param diffModel
	 *            diff model generated by EMF Compare
	 * @return The complete comparsion snapshot.
	 */
	protected ComparisonResourceSetSnapshot buildResourceSetComparisionSnapshot(
			MatchResourceSet matchResourceSet, DiffResourceSet diffSet) {
		final ComparisonResourceSetSnapshot snapshot = DiffFactory.eINSTANCE
				.createComparisonResourceSetSnapshot();
		snapshot.setDate(Calendar.getInstance().getTime());
		snapshot.setMatchResourceSet(matchResourceSet);
		snapshot.setDiffResourceSet(diffSet);
		return snapshot;
	}


	/**
	 * Loads an Ecore model from the supplied file
	 * 
	 * @param modelFiles
	 *            List of models to load
	 * @return model resource set instance
	 * @throws IOException
	 *             possible load exception
	 */
	protected ResourceSet loadModelResourceSet(List<File> modelFiles) throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new
		org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl());

		for (File modelFile : modelFiles) {
			if (!modelFile.canRead()) {
				throw new IllegalArgumentException("cannot read model file "
						+ modelFile.getAbsolutePath());
			}
			URI fileUri = URI.createFileURI(modelFile.getPath());
			Resource resource = resourceSet.createResource(fileUri);
			resource.load(Collections.emptyMap());
		}
	
		return resourceSet;
	}

}
