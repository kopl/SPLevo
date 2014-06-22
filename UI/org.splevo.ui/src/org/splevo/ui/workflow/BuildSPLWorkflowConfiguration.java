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
package org.splevo.ui.workflow;

/**
 * This is the configuration for the job that builds the SPL.
 */
public class BuildSPLWorkflowConfiguration extends BasicSPLevoWorkflowConfiguration {
    private String outputPath;

    /**
     * Initializes the configuration with the given outputpath.
     * 
     * @param outputPath The path the SPL will be generated in.
     */
    public BuildSPLWorkflowConfiguration(String outputPath) {
        this.outputPath = outputPath;
    }

    /**
     * @return the outputPath
     */
    public String getOutputPath() {
        return outputPath;
    }

    /**
     * @param outputPath
     *            the outputPath to set
     */
    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }
}
