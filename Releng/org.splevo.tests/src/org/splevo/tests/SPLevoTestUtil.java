package org.splevo.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.splevo.diffing.Java2KDMDiffingService;
import org.splevo.modisco.util.KDMUtil;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.graph.VPMGraph;
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
    public static final File NATIVE_JAVA2KDMMODEL_FILE = new File(
            "../org.splevo.tests/testmodels/implementation/gcd/native/_java2kdm.xmi");

    /** Source path to the jscience based calculator implementation. */
    public static final File JSCIENCE_JAVA2KDMMODEL_FILE = new File(
            "../org.splevo.tests/testmodels/implementation/gcd/jscience/_java2kdm.xmi");

    /** Source path to the class without an interface. */
    public static final File INTERFACE_IMPLEMENT_TEST_FILE_1 = new File(
            "../org.splevo.tests/testmodels/implementation/interface.import.test/basic/basic_java2kdm.xmi");

    /** Source path to the class implementing the java.io.Serializable interface. */
    public static final File INTERFACE_IMPLEMENT_TEST_FILE_2 = new File(
            "../org.splevo.tests/testmodels/implementation/interface.import.test/serializable/serializable_java2kdm.xmi");

    /**
     * Load the variation point model graph for the GCD example.
     * 
     * @return The loaded graph.
     * @throws IOException
     *             identifies that the graph could not be loaded.
     * @throws InterruptedException
     *             identifies that the refinement process was interrupted.
     */
    public static VPMGraph loadGCDVPMGraph() throws IOException, InterruptedException{
        VariationPointModel vpm = loadGCDVPMModel();
        
        DefaultVPMAnalyzerService service = new DefaultVPMAnalyzerService();
        VPMGraph graph = service.initVPMGraph(vpm);
        return graph;
    }
    
    /**
     * Load the vpm model for the common GCD test example.
     * 
     * @return The loaded model.
     * @throws IOException
     *             identifies that the vpm could not be loaded.
     * @throws InterruptedException
     *             identifies that the refinement process was interrupted.
     */
    public static VariationPointModel loadGCDVPMModel() throws IOException, InterruptedException {

        DiffModel diffModel = loadGCDDiffModel();

        Java2KDMVPMBuilder java2KDMVPMBuilder = new Java2KDMVPMBuilder();
        VariationPointModel initialVpm = java2KDMVPMBuilder.buildVPM(diffModel);

        return initialVpm;
    }

    /**
     * Load the diffing model.
     * 
     * @param leadingModel
     * @param integrationModel
     * @param ignorePackages
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static DiffModel loadGCDDiffModel() throws IOException, InterruptedException {

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

        DiffModel diffModel = diffingService.doDiff(leadingModel, integrationModel);

        return diffModel;
    }

    /**
     * Load the diffing model.
     * 
     * @param leadingModel
     * @param integrationModel
     * @param ignorePackages
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static DiffModel loadInterfaceImplementsDiffModel() throws IOException, InterruptedException {

        JavaApplication leadingModel = KDMUtil.loadKDMModel(INTERFACE_IMPLEMENT_TEST_FILE_1);
        JavaApplication integrationModel = KDMUtil.loadKDMModel(INTERFACE_IMPLEMENT_TEST_FILE_2);
        List<String> ignorePackages = new ArrayList<String>();
        ignorePackages.add("java.*");

        Java2KDMDiffingService diffingService = new Java2KDMDiffingService();
        diffingService.getIgnorePackages().addAll(ignorePackages);

        DiffModel diffModel = diffingService.doDiff(leadingModel, integrationModel);

        return diffModel;
    }

}
