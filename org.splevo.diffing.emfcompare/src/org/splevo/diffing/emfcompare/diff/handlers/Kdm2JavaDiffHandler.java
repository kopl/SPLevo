package org.splevo.diffing.emfcompare.diff.handlers;

import org.eclipse.emf.compare.diff.metamodel.DiffElement;

/**
 * A handler that can adapt subtrees of an generic EMF Compare diff model to specific Java edit operations.
 *
 */
public interface Kdm2JavaDiffHandler {

	/**
	 * Checks whether this handler can process the supplied diff subtree.
	 * @param diffElement subtree to handle
	 * @return true, iff the handler can process the <code>diffElement</code> 
	 */
	public boolean isValidSubtreeHandler(DiffElement diffElement);
	
	/**
	 * Process the diff subtree identified by <code>diffElement</code> and return the adapted version
	 *  corresponding to the subtree.
	 *  
	 * It is required that {@code isValidSubtreeHandler(diffElement)} returns {@code true}.
	 *  
	 * @param diffElement subtree to handle
	 * @return processed version of diff subtree
	 */
	public DiffElement handleSubtree(DiffElement diffElement);
}
