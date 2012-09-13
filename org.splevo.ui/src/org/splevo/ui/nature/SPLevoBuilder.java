package org.splevo.ui.nature;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.compare.util.ModelUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.splevo.project.SPLevoProject;
import org.splevo.project.SPLevoProjectUtil;

public class SPLevoBuilder extends IncrementalProjectBuilder {

	class SampleDeltaVisitor implements IResourceDeltaVisitor {
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse
		 * .core.resources.IResourceDelta)
		 */
		public boolean visit(IResourceDelta delta) throws CoreException {
			IResource resource = delta.getResource();
			switch (delta.getKind()) {
			case IResourceDelta.ADDED:
				// handle added resource
				checkProjectSettings(resource);
				break;
			case IResourceDelta.REMOVED:
				// handle removed resource
				break;
			case IResourceDelta.CHANGED:
				// handle changed resource
				checkProjectSettings(resource);
				break;
			}
			// return true to continue visiting children.
			return true;
		}
	}

	class SampleResourceVisitor implements IResourceVisitor {
		public boolean visit(IResource resource) {
			checkProjectSettings(resource);
			// return true to continue visiting children.
			return true;
		}
	}

	public static final String BUILDER_ID = "org.splevo.ui.splevobuilder";

	private static final String MARKER_TYPE = "org.splevo.ui.splevoConfigurationProblem";

	/**
	 * Create a marker for a specific file.
	 * 
	 * @param file
	 *            The file to mark.
	 * @param message
	 *            The error message.
	 * @param lineNumber
	 *            The line to set the marker at.
	 * @param severity
	 *            The severitiy of the error.
	 */
	private void addMarker(IFile file, String message, int lineNumber,
			int severity) {
		try {
			IMarker marker = file.createMarker(MARKER_TYPE);
			marker.setAttribute(IMarker.MESSAGE, message);
			marker.setAttribute(IMarker.SEVERITY, severity);
			if (lineNumber == -1) {
				lineNumber = 1;
			}
			marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
		} catch (CoreException e) {
		}
	}

	/**
	 * Build the project
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	protected IProject[] build(int kind, Map args, IProgressMonitor monitor)
			throws CoreException {
		if (kind == FULL_BUILD) {
			fullBuild(monitor);
		} else {
			IResourceDelta delta = getDelta(getProject());
			if (delta == null) {
				fullBuild(monitor);
			} else {
				incrementalBuild(delta, monitor);
			}
		}
		return null;
	}

	void checkProjectSettings(IResource resource) {
		if (resource instanceof IFile
				&& resource.getName().endsWith(
						SPLevoProjectUtil.SPLEVO_FILE_EXTENSION)) {
			IFile file = (IFile) resource;
			deleteMarkers(file);
			ResourceSet rs = new ResourceSetImpl();
			String osFilePath = file.getFullPath().toOSString();
			SPLevoProject splEvoProject = null;
			try {
				EObject model = ModelUtils.load(new File(osFilePath), rs);
				if (model instanceof SPLevoProject) {
					splEvoProject = (SPLevoProject) model;
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (splEvoProject == null) {
					addMarker(file,
							"Project configuration file could not be loaded",
							-1, IMarker.SEVERITY_ERROR);
				}
			}

			// TODO: further validation of the splevo project model

		}
	}

	/**
	 * Reset the markers of the file.
	 * @param file The file to reset the markers for
	 */
	private void deleteMarkers(IFile file) {
		try {
			file.deleteMarkers(MARKER_TYPE, false, IResource.DEPTH_ZERO);
		} catch (CoreException ce) {
		}
	}
	
	/**
	 * Perform a full build of the project.
	 * @param monitor The monitor to report the progress to.
	 * @throws CoreException Any exception thrown during the build.
	 */
	protected void fullBuild(final IProgressMonitor monitor)
			throws CoreException {
		try {
			getProject().accept(new SampleResourceVisitor());
		} catch (CoreException e) {
		}
	}

	/**
	 * Perform an incremental build of the project.
	 * @param delta The modified resource
	 * @param monitor The monitor to report the progress to.
	 * @throws CoreException Any exception thrown during the build.
	 */
	protected void incrementalBuild(IResourceDelta delta,
			IProgressMonitor monitor) throws CoreException {
		// the visitor does the work.
		delta.accept(new SampleDeltaVisitor());
	}
}
