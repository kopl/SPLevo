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
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.featuremodel.FeatureModel;
import org.eclipse.featuremodel.diagrameditor.FMEDiagramEditor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.splevo.fm.builder.FeatureModelBuilderService;
import org.splevo.fm.builder.FeatureModelWrapper;
import org.splevo.project.SPLevoProject;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.Lists;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;

/**
 * Job to generate a feature model from a variation point model and opens the diagram if possible.
 */
public class GenerateFeatureModelJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

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
        FeatureModelBuilderService service = new FeatureModelBuilderService();
        // TODO make configurable and do not trigger all builders
        List<String> ids = Lists.newArrayList();
        for (String builderId : service.getAvailableBuilders().keySet()) {
            ids.add(builderId);
        }

        // build model
        String targetPath = getModelFilePath(splevoProject);
        List<FeatureModelWrapper<FeatureModel>> buildAndSaveModels = service.buildAndSaveModels(ids, vpm,
                splevoProject.getName(), targetPath);

        // open diagram editor in ui thread
        for (final FeatureModelWrapper<FeatureModel> modelWrapper : buildAndSaveModels) {
            if (modelWrapper.isCanBeDisplayed()) {
                Display.getDefault().asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        openFeatureDiagram(modelWrapper);
                    }
                });
            }
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
    private void openFeatureDiagram(FeatureModelWrapper<FeatureModel> modelWrapper) {
        IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getActivePage();
        try {
            URI diagramURI = modelWrapper.getModel().eResource().getURI().trimFileExtension().appendFileExtension("featurediagram");
            activePage.openEditor(new URIEditorInput(diagramURI), FMEDiagramEditor.DIAGRAM_EDITOR_ID);
        } catch (PartInitException e) {
            logger.error("Cannot open Feature Diagram View.", e);
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
