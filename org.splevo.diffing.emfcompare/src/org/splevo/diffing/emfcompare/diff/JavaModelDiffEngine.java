package org.splevo.diffing.emfcompare.diff;

import org.eclipse.emf.compare.diff.engine.GenericDiffEngine;
import org.eclipse.emf.compare.diff.engine.check.AttributesCheck;

/**
 * A diff engine specific to the modisco java application model.
 * 
 * @author Benjamin Klatt
 *
 */
public class JavaModelDiffEngine extends GenericDiffEngine {

	/**
	 * Get the java model specific attributes checker.
	 * The JavaModelAttributesCheck class is customized to the specific
	 * semantics of attributes contained in the modisco java model.
	 */
	@Override
	protected AttributesCheck getAttributesChecker() {
		return new JavaModelAttributesCheck(getMatchManager());
	}
	
	
}
