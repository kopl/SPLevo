package org.splevo.diffing.emfcompare.diff;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.compare.diff.engine.GenericDiffEngine;
import org.eclipse.emf.compare.diff.engine.check.AttributesCheck;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;
import org.eclipse.emf.compare.match.metamodel.Side;
import org.eclipse.emf.compare.match.metamodel.UnmatchElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.splevo.diffing.emfcompare.kdm2javadiff.ImportDelete;
import org.splevo.diffing.emfcompare.kdm2javadiff.ImportInsert;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffFactory;

/**
 * A diff engine specific to the modisco java application model.
 * 
 * @author Benjamin Klatt
 *
 */
public class JavaModelDiffEngine extends GenericDiffEngine {

	/** The packages to be ignored. */
	private List<String> ignorePackages = new ArrayList<String>();

	/**
	 * Get the java model specific attributes checker.
	 * The JavaModelAttributesCheck class is customized to the specific
	 * semantics of attributes contained in the modisco java model.
	 */
	@Override
	protected AttributesCheck getAttributesChecker() {
		return new JavaModelAttributesCheck(getMatchManager());
	}
	
	/**
	 * Customized unmatched elements processing including:
	 * - ignore elements from filtered packages
	 */
	@Override
	protected void processUnmatchedElements(DiffGroup diffRoot,
			List<UnmatchElement> unmatched) {
		
		// filter due to package restrictions
		unmatched = filterIgnoredPackages(unmatched);
		
		// analyze unmatched elements to create specific diff types.
		List<UnmatchElement> filteredUnmatched = new ArrayList<UnmatchElement>();
		for (final UnmatchElement unmatchElement : unmatched) {
			final EObject element = unmatchElement.getElement();
			final EObject leftParent = getMatchManager().getMatchedEObject(element.eContainer());

			DiffElement diffElement = null;
			
			if(element instanceof ImportDeclaration){
				if (unmatchElement.getSide() == Side.RIGHT) {
					// add ImportInsert
					final ImportInsert importInsert = KDM2JavaDiffFactory.eINSTANCE
							.createImportInsert();
					importInsert.setImportRight((ImportDeclaration) element);
					importInsert.setRemote(unmatchElement.isRemote());
					diffElement = importInsert;
				} else {
					// add ImportDelete
					final ImportDelete importDelete = KDM2JavaDiffFactory.eINSTANCE.createImportDelete();
					importDelete.setImportLeft((ImportDeclaration) element);
					importDelete.setRemote(unmatchElement.isRemote());
					diffElement = importDelete;
				}
			}
			
			// check if a specific diff element has been created.
			// otherwise add it to the list that still needs to be processed
			if(diffElement != null){
				addInContainerPackage(diffRoot, diffElement, leftParent);
			} else {
				filteredUnmatched.add(unmatchElement);
			}
		}
			
		super.processUnmatchedElements(diffRoot, filteredUnmatched);
	}
	
	/**
	 * Build a new list of unmatched elements containing only those not 
	 * filtered due to the given package restrictions.
	 * 
	 * @param unmatched The original list of unmatched elements.
	 * @return The list of unmatched elements that should not be filtered.
	 */
	private List<UnmatchElement> filterIgnoredPackages(
			List<UnmatchElement> unmatched) {
		
		List<UnmatchElement> unmatchedFiltered = new ArrayList<UnmatchElement>(unmatched.size());
		
		// filter type declarations located in a package to be ignored
		PackageIgnoreVisitor packageIgnoreVisitor = new PackageIgnoreVisitor(ignorePackages);
		for (UnmatchElement unmatchElement : unmatched) {
			Boolean ignore = packageIgnoreVisitor.doSwitch(unmatchElement.getElement());
			if(ignore == Boolean.FALSE){
				unmatchedFiltered.add(unmatchElement);
			}
		}
		return unmatchedFiltered;
	}

	/**
	 * Get the list of packages to ignored any differences in.
	 * @return The list of regular expressions describing the packages to ignore.
	 */
	public List<String> getIgnorePackages() {
		return ignorePackages;
	}
	
}
