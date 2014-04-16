/*******************************************************************************
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic
 *******************************************************************************/
package org.splevo.jamopp.refactoring.java.ifelse.tests.util;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.splevo.jamopp.diffing.JaMoPPDiffer;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;
import org.splevo.jamopp.refactoring.java.ifelse.IfElseRefactoring;
import org.splevo.jamopp.vpm.builder.JaMoPPVPMBuilder;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementFactory;
import org.splevo.vpm.refinement.RefinementType;
import org.splevo.vpm.refinement.VPMRefinementService;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Utility class for jamopp refactorings.
 */
public class RefactoringTestUtil {

    // Paths to the testcode
    private static final String PATH_TESTCODE_FOLDER = "testcode/";

    /**
     * Initialize the variation point model to refactor. Extract, Diff and init VPM.
     * 
     * @param folderName
     *            The name of the folder (within the testcode folder) that contains the code to load
     *            (must contain subdirectories leading and integration).
     * @return The initialized VPM based on the source code differences.
     * @throws Exception
     *             Failed to initialize the model.
     */
    public static VariationPointModel initializeVariationPointModel(String folderName) throws Exception {
        String leadingPath = PATH_TESTCODE_FOLDER + folderName + "/leading/";
        String integrationPath = PATH_TESTCODE_FOLDER + folderName + "/integration/";

        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        List<String> urisA = Lists.newArrayList(new File(leadingPath).getAbsolutePath());
        List<String> urisB = Lists.newArrayList(new File(integrationPath).getAbsolutePath());
        NullProgressMonitor monitor = new NullProgressMonitor();

        ResourceSet setA = extractor.extractSoftwareModel(urisA, monitor, null);
        ResourceSet setB = extractor.extractSoftwareModel(urisB, monitor, null);

        String ignorePackages = buildIgnorePackages();

        Map<String, String> diffOptions = Maps.newLinkedHashMap();
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_IGNORE_PACKAGES, ignorePackages);

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(setA, setB, diffOptions);

        JaMoPPVPMBuilder builder = new JaMoPPVPMBuilder();
        VariationPointModel vpm = builder.buildVPM(comparison, "leading", "integration");
        return vpm;
    }

    /**
     * Build the configuration string for the packages to ignore.
     * 
     * @return The regular expressions for the packages to ignore.
     */
    public static String buildIgnorePackages() {
        StringBuilder sb = new StringBuilder();
        sb.append("java.*");
        sb.append(System.getProperty("line.separator"));
        String ignorePackages = sb.toString();
        return ignorePackages;
    }

    /**
     * Executes a refinement of a given {@link RefinementType} on specified VariationPoints.
     * 
     * @param vpm
     *            The base {@link VariationPointModel}.
     * @param refinementType
     *            The {@link RefinementType}.
     * @param variationPoints
     *            The {@link VariationPoint}s to be refined.
     */
    public static void performRefinement(VariationPointModel vpm, RefinementType refinementType,
            VariationPoint... variationPoints) {
        Refinement refinement = RefinementFactory.eINSTANCE.createRefinement();
        refinement.setType(refinementType);

        for (VariationPoint variationPoint : variationPoints) {
            refinement.getVariationPoints().add(variationPoint);
        }

        VPMRefinementService refinementService = new VPMRefinementService();
        refinementService.applyRefinements(Lists.newArrayList(refinement), vpm);
    }

    /**
     * Sets up a variation point: use ifelse to introduce XOR variability.
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     */
    public static void setUpVariationPointForIfElseXOR(VariationPoint variationPoint) {
        variationPoint.setBindingTime(BindingTime.RUN_TIME);
        variationPoint.setExtensibility(Extensible.NO);
        variationPoint.setVariabilityMechanism(new IfElseRefactoring().getVariabilityMechanism());
        variationPoint.setVariabilityType(VariabilityType.XOR);
    }

    /**
     * Sets up a variation point: use ifelse to introduce OR variability.
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     */
    public static void setUpVariationPointForIfElseOR(VariationPoint variationPoint) {
        variationPoint.setBindingTime(BindingTime.RUN_TIME);
        variationPoint.setExtensibility(Extensible.NO);
        variationPoint.setVariabilityMechanism(new IfElseRefactoring().getVariabilityMechanism());
        variationPoint.setVariabilityType(VariabilityType.OR);
    }
}
