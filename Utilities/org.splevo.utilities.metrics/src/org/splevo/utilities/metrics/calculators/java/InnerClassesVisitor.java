package org.splevo.utilities.metrics.calculators.java;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TypeDeclaration;

/**
 * Class for determining the number of inner Classes.
 * Has to be used with the Eclipse AST.
 * Implements the ASTVisitor Interface.
 * @author Bodo Vossen
 *
 */
public class InnerClassesVisitor extends ASTVisitor {

	/**
	 * Counter for the number of innerClasses.
	 */
	private int counterInnerClasses = 0;

	@Override
	public boolean visit(TypeDeclaration node) {
		//only get inner/anonymous classes
		if (!node.isPackageMemberTypeDeclaration()) {
			counterInnerClasses++;
		}
		return true;
	}

	/**
	 * Method that return the number of inner Classes.
	 * @return the number of inner Classes
	 */
	public int getCounterInnerClasses() {
		return counterInnerClasses;
	}
}