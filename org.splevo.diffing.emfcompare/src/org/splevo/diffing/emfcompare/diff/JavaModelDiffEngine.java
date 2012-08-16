package org.splevo.diffing.emfcompare.diff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.compare.diff.engine.GenericDiffEngine;
import org.eclipse.emf.compare.diff.engine.check.AttributesCheck;
import org.eclipse.emf.compare.diff.engine.check.ReferencesCheck;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffFactory;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.match.metamodel.UnmatchElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.Statement;
import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffFactory;
import org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange;

/**
 * A diff engine specific to the modisco java application model.
 * 
 * @author Benjamin Klatt
 *
 */
public class JavaModelDiffEngine extends GenericDiffEngine {

	/** The packages to be ignored. */
	private List<String> ignorePackages = new ArrayList<String>();

	/** This will keep track of the diff groups created for this comparison. */
	private Map<EObject, DiffGroup> diffGroups = new HashMap<EObject, DiffGroup>();

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
	 * Get the java model specific reference checker.
	 * The JavaModelReferenceCheck class is customized to the specific
	 * semantics of references contained in the modisco java model.
	 */
	@Override
	protected ReferencesCheck getReferencesChecker() {
		return new JavaModelReferenceCheck(getMatchManager(),ignorePackages);
	}
	
	/**
	 * Customized unmatched elements processing including:
	 * - ignore elements from filtered packages
	 */
	@Override
	protected void processUnmatchedElements(DiffGroup diffRoot, List<UnmatchElement> unmatched) {
		
		// filter due to package restrictions
		unmatched = filterIgnoredPackages(unmatched);

		// initialize the processors and filters
		UnmatchedElementProcessor ueProcessor = new UnmatchedElementProcessor();
		UnmatchedElementFilter ueFilter = new UnmatchedElementFilter();
		
		// analyze unmatched elements to create specific diff types.
		List<UnmatchElement> filteredUnmatched = new ArrayList<UnmatchElement>();
		for (final UnmatchElement unmatchElement : unmatched) {
			final EObject element = unmatchElement.getElement();
			final EObject leftParent = getMatchManager().getMatchedEObject(element.eContainer());

			DiffElement diffElement = ueProcessor.process(unmatchElement);
			
			// check if a specific diff element has been created.
			// otherwise add it to the list that still needs to be processed
			if(diffElement != null){
				addInContainerPackage(diffRoot, diffElement, leftParent);
			} else {
				Boolean filter = ueFilter.filter(unmatchElement);
				if(filter == Boolean.FALSE){
					filteredUnmatched.add(unmatchElement);
				}
			}
		}
	
		// Process the elements that were not handled by type specific diff's yet.
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

	/**
	 * Builds a {@link DiffGroup} for the <code>targetParent</code> with its full hierarchy.
	 * 
	 * @param targetParent
	 *            Parent of the operation we're building a {@link DiffGroup} for.
	 * @param root
	 *            {@link DiffGroup Root} of the {@link DiffModel}.
	 * @return {@link DiffGroup} containing the full hierarchy needed for the <code>targetParent</code>.
	 */
	private DiffGroup buildHierarchyGroup(EObject targetParent, DiffGroup root) {
		// if targetElement has a parent, we call buildgroup on it, else we add
		// the current group to the root
		DiffGroup curGroup = findExistingGroup(root, targetParent);
		if (curGroup == null) {
			
			// Check if the current parent is a statement and create a more type specific change
			// group. Also try to get a matching element from the original model by using the match manager.
			if(targetParent instanceof Statement){
				StatementChange stmtGroup = Java2KDMDiffFactory.eINSTANCE.createStatementChange();
				EObject statementLeft = getMatchManager().getMatchedEObject(targetParent);
				if(statementLeft instanceof Statement){
					stmtGroup.setStatementLeft((Statement) statementLeft);
				}
				curGroup = stmtGroup;
			} else {
				curGroup = DiffFactory.eINSTANCE.createDiffGroup();
			}
			curGroup.setRightParent(targetParent);
			diffGroups.put(targetParent, curGroup);
		}
		if (targetParent.eContainer() == null) {
			root.getSubDiffElements().add(curGroup);
			return curGroup;
		}
		// if targetElement is the root of a fragment resource, do not walk the hierarchy up,
		// instead report changes to fragments in their own resource's context
		if (targetParent.eResource() == targetParent.eContainer().eResource()) {
			buildHierarchyGroup(targetParent.eContainer(), root).getSubDiffElements().add(curGroup);
		} else {
			root.getSubDiffElements().add(curGroup);
		}
		return curGroup;
	}
	
	// UNMODIFIED METHODS BUT REQUIRED TO INHERIT TO OVERWRITE SUBSEQUENT METHODS

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.diff.engine.IDiffEngine#reset()
	 */
	public void reset() {
		diffGroups.clear();
		super.reset();
	}

	/**
	 * Looks for an already created {@link DiffGroup diff group} in order to add the operation, if none
	 * exists, create one where the operation belongs to.
	 * 
	 * @param root
	 *            {@link DiffGroup root} of the {@link DiffModel}.
	 * @param operation
	 *            Operation to add to the {@link DiffModel}.
	 * @param targetParent
	 *            Parent {@link EObject} for the operation.
	 */
	protected void addInContainerPackage(DiffGroup root, DiffElement operation, EObject targetParent) {
		if (targetParent == null) {
			root.getSubDiffElements().add(operation);
			return;
		}
		DiffGroup targetGroup = findExistingGroup(root, targetParent);
		if (targetGroup == null) {
			// Searches for a DiffGroup with the matched parent
			targetGroup = findExistingGroup(root, getMatchManager().getMatchedEObject(targetParent));
			if (targetGroup == null) {
				// we have to create the group
				targetGroup = buildHierarchyGroup(targetParent, root);
			}
		}
		targetGroup.getSubDiffElements().add(operation);
	}

	/**
	 * Searches for an existing {@link DiffGroup} under <code>root</code> to add the operation which parent is
	 * <code>targetParent</code>.
	 * 
	 * @param root
	 *            {@link DiffGroup Root} of the {@link DiffModel}.
	 * @param targetParent
	 *            Parent of the operation we're seeking a {@link DiffGroup} for.
	 * @return {@link DiffGroup} for the <code>targetParent</code>.
	 */
	private DiffGroup findExistingGroup(DiffGroup root, EObject targetParent) {
		return diffGroups.get(targetParent);
	}
	
}
