package org.splevo.ui.jobs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.splevo.extraction.DefaultExtractionService;
import org.splevo.extraction.ExtractionService;
import org.splevo.extraction.SoftwareModelExtractionException;
import org.splevo.project.SPLevoProject;

import de.uka.ipd.sdq.workflow.AbstractJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;

/**
 * Job to extract a software model from an eclipse java project.
 */
public class ExtractionJob extends AbstractJob {

    /** The splevo project to store the required data to. */
    private SPLevoProject splevoProject;

    /** Flag whether the leading or the integration project should be extracted. */
    private boolean processLeading;
    
    /** The internal id of the extractor to be executed. */
    private String extractorId;

    /**
     * Constructor to create an extraction job with the required references.
     * 
     * @param extractorId The identifier of the extractor to be executed.
     * @param splevoProject
     *            The splevo project to get and store required information
     * @param processLeading
     *            True/false wether this job is responsible for the leading implementation.
     */
    public ExtractionJob(String extractorId, SPLevoProject splevoProject, boolean processLeading) {
        this.splevoProject = splevoProject;
        this.processLeading = processLeading;
        this.extractorId = extractorId;
    }

    /**
     * Runs the long running operation.
     * 
     * @param monitor
     *            the progress monitor
     * @return the status of the job. This should be OK or CANCLED
     */
    public IStatus run(IProgressMonitor monitor) {
        monitor.beginTask("Software Model Extraction", 100);

        List<String> projectNames = null;
        String variantName = null;
        if (processLeading) {
            projectNames = splevoProject.getLeadingProjects();
            variantName = splevoProject.getVariantNameLeading();
        } else {
            projectNames = splevoProject.getIntegrationProjects();
            variantName = splevoProject.getVariantNameIntegration();
        }

        // prepare the target path
        URI targetURI = buildTargetURI(variantName);
        List<URI> projectURIs = buildProjectURIs(projectNames);

        logger.info("Extraction target: " + targetURI);
        logger.info("Main Project: " + projectNames.get(0));
        if (projectNames.size() > 1) {
            logger.info("Additional Projects: " + projectNames.subList(1, projectNames.size() - 1));
        }

        // check if the process was canceled
        if (monitor.isCanceled()) {
            return Status.CANCEL_STATUS;
        }

        // extract model
        ExtractionService extractionService = new DefaultExtractionService();
        try {
            monitor.subTask("Extract Model for project: " + variantName);
            extractionService.extractSoftwareModel(extractorId, projectURIs, monitor, targetURI);
        } catch (SoftwareModelExtractionException e) {
            e.printStackTrace();
            return Status.CANCEL_STATUS;
        }

        monitor.subTask("Update SPLevo project information");

        if (processLeading) {
            splevoProject.setSourceModelPathLeading(targetURI.path());
        } else {
            splevoProject.setSourceModelPathIntegration(targetURI.path());
        }

        // finish run
        monitor.done();

        if (monitor.isCanceled()) {
            return Status.CANCEL_STATUS;
        }

        return Status.OK_STATUS;
    }

    /**
     * Build the URIs for a list of projects identified by their names.
     * 
     * @param projectNames
     *            The project names.
     * @return The list of URIs identifying the projects.
     */
    private List<URI> buildProjectURIs(List<String> projectNames) {
        List<URI> projectURIs = new ArrayList<URI>();
        for (String projectName : projectNames) {
            String basePath = getBasePath(splevoProject);
            URI projectURI = URI.createURI(basePath + projectName);
            projectURIs.add(projectURI);
        }
        return projectURIs;
    }

    /**
     * Build the target uri for the model extraction.
     * 
     * @param variantName
     *            The name of the variant to extract.
     * @return The prepared URI.
     */
    private URI buildTargetURI(String variantName) {
        String basePath = getBasePath(splevoProject);
        String targetPath = basePath + variantName;
        URI targetURI = URI.createURI(targetPath);
        return targetURI;
    }

    /**
     * Build the base path for the target models.
     * 
     * @param splevoProject
     *            The SPLevo project to interact with.
     * @return The base path to store the extracted models at.
     */
    private String getBasePath(SPLevoProject splevoProject) {
        return splevoProject.getWorkspace() + "models/sourcemodels/";
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        run(monitor);
    }

    @Override
    public void rollback(IProgressMonitor monitor) throws RollbackFailedException {
        // no rollback possible
    }

    /**
     * Get the name of the extraction job. This depends on whether the leading or the integration
     * job should be extracted.
     * 
     * @return The name of the job.
     */
    @Override
    public String getName() {
        if (processLeading) {
            return "Model Extraction Job " + splevoProject.getVariantNameLeading();
        } else {
            return "Model Extraction Job " + splevoProject.getVariantNameIntegration();
        }
    }
}
