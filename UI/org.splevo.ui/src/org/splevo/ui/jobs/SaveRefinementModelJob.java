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
 *******************************************************************************/
package org.splevo.ui.jobs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.project.SPLevoProject;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementModel;
import org.splevo.vpm.refinement.RefinementUtil;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import au.com.bytecode.opencsv.CSVWriter;
import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;

/**
 * Job to read the refinement model from the blackboard and save it at a given path.
 *
 * The job accepts a format option to decide how to persist the data.
 */
public class SaveRefinementModelJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The relative path to write the model to. */
    private String targetPath = null;

    /** The SPLevo project to work in. */
    private SPLevoProject splevoProject = null;

    /** The format to persist the model. */
    private SaveRefinementModelJob.FORMAT format = FORMAT.ECORE_XMI;

    /**
     * Constructor to set the target path to write the model to.
     *
     * @param splevoProject
     *            The project to get the workspace from.
     * @param targetPath
     *            The eclipse workspace relative path to write to.
     */
    public SaveRefinementModelJob(SPLevoProject splevoProject, String targetPath) {
        this.splevoProject = splevoProject;
        this.targetPath = targetPath;
    }

    /**
     * Constructor to set the target path to write the model to.
     *
     * @param splevoProject
     *            The project to get the workspace from.
     * @param targetPath
     *            The eclipse workspace relative path to write to.
     * @param format
     *            To format to save the model in.
     */
    public SaveRefinementModelJob(SPLevoProject splevoProject, String targetPath, FORMAT format) {
        this(splevoProject, targetPath);
        this.format = format;
    }

    /**
     * Runs the long running operation.
     *
     * @param monitor
     *            the progress monitor
     * @throws JobFailedException
     *             identifies an unexpected termination of the job.
     */
    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException {

        RefinementModel refModel = getBlackboard().getRefinementModel();

        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        String basePath = workspace.getRoot().getRawLocation().toOSString();
        String resultDirectory = splevoProject.getWorkspace() + "models/analyses-results/";

        if (targetPath == null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
            Calendar cal = Calendar.getInstance();
            targetPath = dateFormat.format(cal.getTime());
        }
        targetPath = resultDirectory + targetPath;

        switch (this.format) {
        case CSV:
            new File(basePath + resultDirectory).mkdirs();
            exportResultCSV(refModel, basePath + targetPath);
            break;

        case ECORE_XMI:
            exportEcoreXMI(refModel, targetPath);
            break;

        default:
            break;
        }

        // finish run
        monitor.done();
    }

    /**
     * Export csv files for the identified refinements.
     *
     * @param refModel
     *            The model to export.
     * @param targetPath
     *            The path to write to.
     * @throws JobFailedException
     *             Failed to write the csv export.
     */
    private void exportResultCSV(RefinementModel refModel, String targetPath) throws JobFailedException {
        String resultFilePath = targetPath + "-results.csv";

        // write the configuration file
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(resultFilePath));
            String[] header = new String[] { "Type", "VP ASTNode", "Leading AST Node", "Integration AST Node" };
            writer.writeNext(header);
            for (Refinement refinement : refModel.getRefinements()) {
                String type = refinement.getType().toString();

                for (VariationPoint vp : refinement.getVariationPoints()) {

                    StringBuilder leadingVariant = new StringBuilder("");
                    StringBuilder integrationVariant = new StringBuilder("");

                    for (Variant v : vp.getVariants()) {
                        if (v.getLeading()) {
                            leadingVariant.append(v.getImplementingElements().toString());
                        } else {
                            integrationVariant.append(v.getImplementingElements().toString());
                        }
                    }

                    String vpLocation = vp.getLocation().toString();
                    String[] result = new String[] { type, vpLocation, "" + leadingVariant, "" + integrationVariant };
                    writer.writeNext(result);
                }
            }

        } catch (Exception e) {
            throw new JobFailedException("Failed to write csv export", e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new JobFailedException("Failed to save csv file", e);
                }
            }
        }

    }

    /**
     * Export the model as xmi ecore file.
     *
     * @param refModel
     *            The model to export.
     * @param targetPath
     *            The target path to write to with an appended ".refinement".
     * @throws JobFailedException
     *             Identifies the job could not write the file.
     */
    private void exportEcoreXMI(RefinementModel refModel, String targetPath) throws JobFailedException {
        logger.info("Save Refinement Model");
        try {
            String modelPath = targetPath + ".refinement";
            RefinementUtil.save(refModel, new File(modelPath));
        } catch (IOException e) {
            throw new JobFailedException("Failed to save refinement model as xmi.", e);
        }
    }

    /**
     * Get the name of the job.
     *
     * @return The name of the job.
     */
    @Override
    public String getName() {
        return "Save Refinement Model Job";
    }

    /**
     * Enumeration specifying the formats supported by this job.
     *
     * @author Benjamin Klatt
     *
     */
    public enum FORMAT {

        /**
         * The job will store the model as an ecore model in the common XMI format (file extension
         * .refinement).
         */
        ECORE_XMI,

        /**
         * The job will store the data included in the model as two separate csv files, one
         * containing the configuration, the other containing the refinement data.
         */
        CSV,

        /**
         * The job will write nothing to the file system.
         */
        NONE
    }

    @Override
    public void cleanup(IProgressMonitor arg0) {
    }
}
