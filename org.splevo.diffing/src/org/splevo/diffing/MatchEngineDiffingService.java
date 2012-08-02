package org.splevo.diffing;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.diff.metamodel.ComparisonResourceSetSnapshot;
import org.eclipse.emf.compare.diff.metamodel.DiffFactory;
import org.eclipse.emf.compare.diff.metamodel.DiffResourceSet;
import org.eclipse.emf.compare.match.MatchOptions;
import org.eclipse.emf.compare.match.engine.DefaultMatchScopeProvider;
import org.eclipse.emf.compare.match.metamodel.Match2Elements;
import org.eclipse.emf.compare.match.metamodel.MatchElement;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.compare.match.metamodel.MatchResourceSet;
import org.eclipse.emf.compare.match.metamodel.UnmatchElement;
import org.eclipse.emf.compare.match.service.MatchService;
import org.eclipse.emf.compare.util.EMFCompareMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.gmt.modisco.java.emf.impl.ClassInstanceCreationImpl;
import org.eclipse.gmt.modisco.java.emf.impl.JavadocImpl;
import org.eclipse.gmt.modisco.java.emf.impl.MethodInvocationImpl;
import org.eclipse.gmt.modisco.java.emf.impl.PackageImpl;
import org.eclipse.gmt.modisco.java.emf.impl.SingleVariableAccessImpl;
import org.eclipse.gmt.modisco.java.emf.impl.TextElementImpl;
import org.eclipse.gmt.modisco.java.emf.impl.TypeAccessImpl;

/**
 * Diffing Service that directly builds up the diffing model instead of using a post 
 * processor based on the diffing identified by emf compare.
 * 
 * @author Benjamin Klatt
 *
 */
public class MatchEngineDiffingService extends AbstractDiffingService {

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
		ComparisonResourceSetSnapshot snapshot = buildResourceSetComparisionSnapshot(
				matchResourceSet, diffSet);
		
		return snapshot;
	}

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
		final Map<String, Object> matchOptions = new EMFCompareMap<String, Object>();
		DefaultMatchScopeProvider matchScopeProvider = new DefaultMatchScopeProvider(leftResourceSet, rightResourceSet);
		matchOptions.put(MatchOptions.OPTION_MATCH_SCOPE_PROVIDER,matchScopeProvider);
		final MatchResourceSet matchResourceSet = MatchService.doResourceSetMatch(leftResourceSet, rightResourceSet,matchOptions);
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

}
