package org.splevo.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.splevo.diffing.Java2KDMDiffingService;
import org.splevo.modisco.util.KDMUtil;
import org.splevo.vpm.builder.java2kdmdiff.Java2KDMVPMBuilder;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Utility class for the common SPLevo test infrstructure to prevent redundant code and stream line
 * test development.
 * 
 * @author Benjamin Klatt
 * 
 */
public class SPLevoTestUtil {

    /** Source path to the native calculator implementation. */
    private static final File NATIVE_JAVA2KDMMODEL_FILE = new File("testmodels/implementation/gcd/native/_java2kdm.xmi");

    /** Source path to the jscience based calculator implementation. */
    private static final File JSCIENCE_JAVA2KDMMODEL_FILE = new File(
            "testmodels/implementation/gcd/jscience/_java2kdm.xmi");

    /**
     * Load the vpm model for the common GCD test example.
     * 
     * @return The loaded model.
     * @throws IOException
     *             identifies that the vpm could not be loaded.
     * @throws InterruptedException
     *             identifies that the refinement process was interrupted.
     */
    public VariationPointModel loadGCDVPMModel() throws IOException, InterruptedException {

        JavaApplication leadingModel = KDMUtil.loadKDMModel(NATIVE_JAVA2KDMMODEL_FILE);
        JavaApplication integrationModel = KDMUtil.loadKDMModel(JSCIENCE_JAVA2KDMMODEL_FILE);
        List<String> ignorePackages = new ArrayList<String>();
        ignorePackages.add("java.lang");
        ignorePackages.add("java.math");
        ignorePackages.add("java.io");
        ignorePackages.add("org.jscience.*");
        ignorePackages.add("org.jscience.*");
        ignorePackages.add("javolution.*");

        Java2KDMDiffingService diffingService = new Java2KDMDiffingService();
        diffingService.getIgnorePackages().addAll(ignorePackages);

        DiffModel diffModel = diffingService.doDiff(integrationModel, leadingModel);

        Java2KDMVPMBuilder java2KDMVPMBuilder = new Java2KDMVPMBuilder();
        VariationPointModel initialVpm = java2KDMVPMBuilder.buildVPM(diffModel);

        return initialVpm;
    }

}
