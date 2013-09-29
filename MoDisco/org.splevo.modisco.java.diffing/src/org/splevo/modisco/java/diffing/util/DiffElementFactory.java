package org.splevo.modisco.java.diffing.util;

import org.eclipse.emf.compare.diff.engine.IMatchManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.Statement;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffFactory;
import org.splevo.modisco.java.diffing.java2kdmdiff.StatementDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.StatementInsert;

/**
 * A factory to simplify the creation of specific diff elements.
 */
public final class DiffElementFactory {

	/** Utility class does not need a constructor. */
	private DiffElementFactory() {
	}

	/**
	 * Factory method to create a statement insert diff element.
	 * 
	 * @param statement
	 *            The statement that has been inserted.
	 * @return The created statement insert element.
	 */
	public static StatementInsert createStatementInsert(Statement statement) {
		StatementInsert statementInsert = Java2KDMDiffFactory.eINSTANCE
				.createStatementInsert();
		statementInsert.setStatementLeft(statement);
		return statementInsert;
	}

	/**
	 * Factory method to create a statement delete diff element.
	 * 
	 * @param statement
	 *            The deleted statement.
	 * @param matchManager
	 *            The match manager to get access to the new model.
	 * @return The created statement delete element.
	 */
	public static StatementDelete createStatementDelete(Statement statement,
			IMatchManager matchManager) {
		StatementDelete statementDelete = Java2KDMDiffFactory.eINSTANCE
				.createStatementDelete();
		statementDelete.setStatementRight(statement);

		EObject leftContainer = matchManager.getMatchedEObject(statement
				.eContainer());
		if (leftContainer != null) {
			statementDelete.setLeftContainer((ASTNode) leftContainer);
		}
		return statementDelete;
	}

}
