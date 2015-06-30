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

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.commons.util.WorkspaceUtil;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementModel;
import org.splevo.vpm.refinement.RefinementUtil;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import au.com.bytecode.opencsv.CSVWriter;

import com.google.common.io.Files;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;

/**
 * Job to read the refinement model from the blackboard and save it at a given path.
 *
 * The job accepts a format option to decide how to persist the data.
 */
public class SaveRefinementModelJob extends AbstractBlackboardInteractingJob<SPLevoBlackBoard> {

    /** The file name of the result file. */
    private String targetFile = null;

    /** The SPLevo project to work in. */
    private SPLevoProject splevoProject = null;

    /** The format to persist the model. */
    private SaveRefinementModelJob.FORMAT format = FORMAT.ECORE_XMI;

    /**
     * Constructor to set the target path to write the model to.
     *
     * @param splevoProject
     *            The project to get the workspace from.
     * @param targetFile
     *            The file name of the result file.
     */
    protected SaveRefinementModelJob(SPLevoProject splevoProject, String targetFile) {
        this.splevoProject = splevoProject;
        this.targetFile = targetFile;
    }

    /**
     * Constructor to set the target path to write the model to.
     *
     * @param splevoProject
     *            The project to get the workspace from.
     * @param targetFile
     *            The file name of the result file.
     * @param format
     *            To format to save the model in.
     */
    protected SaveRefinementModelJob(SPLevoProject splevoProject, String targetFile, FORMAT format) {
        this(splevoProject, targetFile);
        this.format = format;
    }
    
    /**
     * Job constructor for saving of the refinement model.
     * The model will be saved in a default path with a unique file name.
     *
     * @param splevoProject
     *            The project to get the workspace from.
     * @param format
     *            To format to save the model in.
     */
    public SaveRefinementModelJob(SPLevoProject splevoProject, FORMAT format) {
        this(splevoProject, null, format);
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
        
        final String absoluteSavePathWithoutExtension = constructAbsoluteFilenameWithoutExtension();
        try {
            Files.createParentDirs(new File(absoluteSavePathWithoutExtension));
        } catch (IOException e) {
            throw new JobFailedException("Creation of destination folder failed.", e);
        }
        
        switch (this.format) {
        case CSV:
            exportResultCSV(refModel, absoluteSavePathWithoutExtension);
            break;

        case ECORE_XMI:
            exportEcoreXMI(refModel, absoluteSavePathWithoutExtension, logger);
            break;

        default:
            break;
        }

        // finish run
        monitor.done();
    }
    
    private String constructAbsoluteFilenameWithoutExtension() {
        final String workspaceRelativeResultDirectory = splevoProject.getWorkspace() + "models/analyses-results/";
        String filenameBase = targetFile;
        if (filenameBase == null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
            Calendar cal = Calendar.getInstance();
            filenameBase = dateFormat.format(cal.getTime());
        }
        final String relativeBaseFilename = workspaceRelativeResultDirectory + filenameBase;
        return WorkspaceUtil.getAbsoluteFromWorkspaceRelativePath(relativeBaseFilename);
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
    private static void exportResultCSV(RefinementModel refModel, String targetPath) throws JobFailedException {
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
    private static void exportEcoreXMI(RefinementModel refModel, String targetPath, Logger logger) throws JobFailedException {
        logger.info("Save Refinement Model");
        try {
            String modelPath = targetPath + ".refinement";
            RefinementUtil.save(refModel, new File(modelPath), true);
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
