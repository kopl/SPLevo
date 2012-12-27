package org.splevo.extraction;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.modisco.java.composition.discoverer.DiscoverKDMSourceAndJavaModelFromJavaProject;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;

/**
 * Service to extract source models from a given input. 
 * 
 */
public class ExtractionService {

	/**
	 * Extract the source model of a java project.
	 * @param javaProject The project to extract.
	 * @param monitor The monitor to report the progress to.
	 * @param targetURI The target uri to store the model to.
	 * @return The reference to the data model.
	 * @throws Exception Identifies the extraction was not successful.
	 */
	public JavaApplication extractProject(IJavaProject javaProject, IProgressMonitor monitor, URI targetURI) throws Exception{
		DiscoverKDMSourceAndJavaModelFromJavaProject discoverer = new DiscoverKDMSourceAndJavaModelFromJavaProject();
		discoverer.setSerializeTarget(true);
		discoverer.setTargetURI(targetURI);
		discoverer.discoverElement(javaProject, monitor);
		Resource javaApplicationModel = discoverer.getTargetModel();
		return (JavaApplication) javaApplicationModel.getContents().get(0);
	}
}
