/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.modisco.java.vpm.analyzer.programstructure;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.compare.Comparison;
import org.splevo.modisco.java.diffing.Java2KDMDiffer;
import org.splevo.modisco.java.vpm.builder.Java2KDMVPMBuilder;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Utility class to provide the required data for the programstructure
 * analyzer.
 */
public class TestUtil {

    /** Source path to the native calculator implementation. */
    public static final File NATIVE_JAVA2KDMMODEL_DIR = new File(
            "testmodels/gcd/native");

    /** Source path to the jscience based calculator implementation. */
    public static final File JSCIENCE_JAVA2KDMMODEL_DIR = new File(
            "testmodels/gcd/jscience");

    /**
     * Load the variation point model graph for the GCD example.
     *
     * @return The loaded graph.
     * @throws Exception
     *             Identifies a failed diffing.
     */
    public static VPMGraph loadGCDVPMGraph() throws Exception {
        VariationPointModel vpm = loadGCDVPMModel();

        DefaultVPMAnalyzerService service = new DefaultVPMAnalyzerService();
        VPMGraph graph = service.initVPMGraph(vpm);
        return graph;
    }

    /**
     * Load the vpm model for the common GCD test example.
     *
     * @return The loaded model.
     * @throws Exception
     *             Identifies a failed diffing.
     */
    public static VariationPointModel loadGCDVPMModel() throws Exception {

        Comparison diffModel = loadGCDDiffModel();

        Java2KDMVPMBuilder java2KDMVPMBuilder = new Java2KDMVPMBuilder();
        VariationPointModel initialVpm = java2KDMVPMBuilder.buildVPM(diffModel, "LEADING", "INTEGRATION");

        return initialVpm;
    }

    /**
     * Load the diffing model.
     *
     * @return The comparison model for the GCD.
     * @throws Exception
     *             Identifies a failed diffing.
     */
    public static Comparison loadGCDDiffModel() throws Exception {

        String ignorePackages = "java.*\norg.jscience.*\njavolution.*";

        Map<String, String> diffOptions = new LinkedHashMap<String, String>();
        diffOptions.put(Java2KDMDiffer.OPTION_JAVA_IGNORE_PACKAGES, ignorePackages);

        Java2KDMDiffer differ = new Java2KDMDiffer();

        Comparison diffModel = differ.doDiff(NATIVE_JAVA2KDMMODEL_DIR.toURI(), JSCIENCE_JAVA2KDMMODEL_DIR.toURI(),
                diffOptions);

        return diffModel;
    }

}
