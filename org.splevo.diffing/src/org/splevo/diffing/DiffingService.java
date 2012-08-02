package org.splevo.diffing;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.emf.compare.diff.metamodel.ComparisonResourceSetSnapshot;

public interface DiffingService {

	/**
	 * Analyze the differences between two AST models. This will build a diff
	 * model containing the differences.
	 * 
	 * @param leftASTModel
	 *            The left model to be diffed.
	 * @param rightASTModel
	 *            The right model to be diffed.
	 * @return
	 * @throws IOException
	 *             identifying that model files can not be loaded.
	 * @throws InterruptedException
	 *             identifying that the diffing process has been interrupted
	 */
	public abstract ComparisonResourceSetSnapshot getDiff(
			List<File> leftASTModels, List<File> rightASTModels)
			throws IOException, InterruptedException;

}