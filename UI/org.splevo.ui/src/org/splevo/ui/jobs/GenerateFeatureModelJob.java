/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *    Daniel Kojic
 *******************************************************************************/
package org.splevo.ui.jobs;

import java.io.File;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.splevo.fm.builder.FeatureModelBuilder;
import org.splevo.fm.builder.FeatureModelBuilderRegistry;
import org.splevo.fm.builder.FeatureModelWrapper;
import org.splevo.project.SPLevoProject;
import org.splevo.vpm.variability.VariationPointModel;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;

/**
 * Job to generate a feature model from a variation point model and opens the diagram if possible.
 */
public class GenerateFeatureModelJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** Message to provide if a requested builder can not be loaded. */
    private static final String MSG_BUILDER_NOT_AVAILABLE = "No feature model builder available for the provided id: %1";
    
    /** The splevo project to store the required data to. */
    private SPLevoProject splevoProject;

    /**
     * Constructor to set the reference to the SPLevo project.
     * 
     * @param splevoProject
     *            The SPLevo project to exchange data with.
     */
    public GenerateFeatureModelJob(SPLevoProject splevoProject) {
        this.splevoProject = splevoProject;
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException {

        logger.info("Generate Feature Model");
        VariationPointModel vpm = getBlackboard().getVariationPointModel();
        if (vpm == null) {
            throw new JobFailedException("No variation point model available in the blackboard.");
        }

        // build model
        String targetPath = getModelFilePath(splevoProject);
        
        String fmBuilderId = splevoProject.getFmBuilderId();
        FeatureModelBuilder<Object> currentBuilder = FeatureModelBuilderRegistry.getInstance().getElementById(fmBuilderId);
        if (currentBuilder == null) {
            throw new JobFailedException(String.format(MSG_BUILDER_NOT_AVAILABLE, fmBuilderId));
        }

        final FeatureModelWrapper<Object> modelWrapper = currentBuilder.build(vpm, splevoProject.getName());
        currentBuilder.save(modelWrapper.getModel(), targetPath);

        // open diagram editor in ui thread
        if (modelWrapper.isCanBeDisplayed()) {
            Display.getDefault().asyncExec(new Runnable() {
                @Override
                public void run() {
                    openFeatureDiagram(modelWrapper);
                }
            });
        }

        // check if the process was canceled
        if (monitor.isCanceled()) {
            monitor.done();
            return;
        }

        // finish run
        monitor.done();
    }
    
    /**
     * Opens the diagram file to a corresponding model.
     * 
     * @param modelWrapper The {@link FeatureModelWrapper} containing the model.
     */
    private void openFeatureDiagram(FeatureModelWrapper<Object> modelWrapper) {
        IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getActivePage();
        try {
            EObject model = (EObject) modelWrapper.getModel();
            URI diagramURI = model.eResource().getURI().trimFileExtension().appendFileExtension("featurediagram");
            activePage.openEditor(new URIEditorInput(diagramURI), "org.eclipse.featuremodel.diagrameditor.diagrameditor");
        } catch (PartInitException e) {
            logger.error("Cannot open Feature Diagram View.", e);
        } catch (ClassCastException e) {
            logger.error("Invalid model type.", e);
        }
    }

    /**
     * Return the Feature Model file path.
     * 
     * @param splevoProject
     *            The {@link SPLevoProject} to get the workspace from.
     * @return the absolute path to the file.
     */
    private String getModelFilePath(SPLevoProject splevoProject) {
        String path = splevoProject.getWorkspace() + "models" + File.separator + "fm" + File.separator;
        return path;
    }

    @Override
    public String getName() {
        return "Generate Feature Model Job";
    }

    @Override
    public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
    }
}
