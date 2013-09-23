package org.splevo.ui.jobs;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.util.ModelUtils;
import org.splevo.diffing.DefaultDiffingService;
import org.splevo.diffing.DiffingException;
import org.splevo.diffing.DiffingService;
import org.splevo.diffing.JavaDiffer;
import org.splevo.project.SPLevoProject;

import de.uka.ipd.sdq.workflow.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;

/**
 * Job to execute the diffing on the source models provided through the
 * blackboard.
 */
public class DiffingJob extends
		AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

	private final SPLevoProject splevoProject;
	private DateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd hh:mm:ss:S");

	/**
	 * Constructor for the diffing job.
	 * 
	 * @param splevoProject
	 *            The SPLevo project to process the diffing for.
	 */
	public DiffingJob(final SPLevoProject splevoProject) {
		this.splevoProject = splevoProject;
	}

	@Override
	public void execute(final IProgressMonitor monitor)
			throws JobFailedException, UserCanceledException {

		final String basePath = getWorkspacePath();

		logger.info("Init diffing service");

		Map<String, Object> options = buildDiffingOptions();
		URI leadingModelDir = new File(basePath
				+ splevoProject.getSourceModelPathLeading()).toURI();
		URI integrationModelDir = new File(basePath
				+ splevoProject.getSourceModelPathIntegration()).toURI();

		logger.info("Diffing started at: " + (dateFormat.format(new Date())));
		DiffModel diffModel = null;
		try {
			final DiffingService diffingService = new DefaultDiffingService();
			diffModel = diffingService.diffSoftwareModels(leadingModelDir,
					integrationModelDir, options);
		} catch (final DiffingException e) {
			throw new JobFailedException("Failed to process diffing.", e);
		}
		logger.info("Diffing finished at: " + (dateFormat.format(new Date())));
		logger.info("Number of differences: "
				+ diffModel.getDifferences().size());

		// check if the process was canceled
		if (monitor.isCanceled()) {
			monitor.done();
			return;
		}

		logger.info("Save Diff Model");
		try {
			// TODO Do not save a KDM specific diff model.
			final String targetPath = this.splevoProject.getWorkspace()
					+ "models/diffmodel/diffModel.java2kdmdiff";

			ModelUtils.save(diffModel, basePath + targetPath);
			this.splevoProject.setDiffingModelPath(targetPath);
		} catch (final IOException e) {
			throw new JobFailedException("Failed to save diff model.", e);
		}

		refreshWorkspace(monitor);
		monitor.done();
	}

	/**
	 * Get the path to the workspace.
	 * 
	 * @return The uri pointing to the workspace root.
	 */
	private String getWorkspacePath() {
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		final String basePath = workspace.getRoot().getRawLocation()
				.toOSString();
		return basePath;
	}

	/**
	 * Refresh the workspace so the UI will present all new generated files to
	 * the user.
	 * 
	 * @param monitor
	 *            The process monitor to report the progress to.
	 */
	private void refreshWorkspace(final IProgressMonitor monitor) {
		try {
			ResourcesPlugin.getWorkspace().getRoot()
					.refreshLocal(IResource.DEPTH_ONE, monitor);
		} catch (final CoreException e) {
			logger.error("Failed to refresh Workspace", e);
		}
	}

	/**
	 * Build the map of differ configurations from the settings in the splevo
	 * project.
	 * 
	 * @return The prepared diffings for the differs.
	 */
	private Map<String, Object> buildDiffingOptions() {
		List<String> ignorePackages = new ArrayList<String>();
		final String diffingRuleRaw = this.splevoProject
				.getDiffingFilterRules();
		final String[] parts = diffingRuleRaw.split(System
				.getProperty("line.separator"));
		for (final String rule : parts) {
			ignorePackages.add(rule);
		}
		Map<String, Object> diffingOptions = new HashMap<String, Object>();
		diffingOptions.put(JavaDiffer.OPTION_JAVA_IGNORE_PACKAGES, ignorePackages);
		return diffingOptions;
	}

	@Override
	public void rollback(final IProgressMonitor monitor)
			throws RollbackFailedException {
		// no rollback required
	}

	@Override
	public String getName() {
		return "Diff source models Job";
	}
}
