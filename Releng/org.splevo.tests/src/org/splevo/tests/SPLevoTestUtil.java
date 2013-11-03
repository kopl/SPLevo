package org.splevo.tests;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.splevo.diffing.DiffingException;
import org.splevo.diffing.DiffingNotSupportedException;
import org.splevo.diffing.JavaDiffer;
import org.splevo.modisco.java.diffing.Java2KDMDiffer;
import org.splevo.modisco.java.vpm.builder.Java2KDMVPMBuilder;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.graph.VPMGraph;
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
    public static final File NATIVE_JAVA2KDMMODEL_DIR = new File(
            "../org.splevo.tests/testmodels/implementation/gcd/native");

    /** Source path to the jscience based calculator implementation. */
    public static final File JSCIENCE_JAVA2KDMMODEL_DIR = new File(
            "../org.splevo.tests/testmodels/implementation/gcd/jscience");

    /** Source path to the class without an interface. */
    public static final File INTERFACE_IMPLEMENT_TEST_DIR_1 = new File(
            "../org.splevo.tests/testmodels/implementation/interface.import.test/basic");

    /** Source path to the class implementing the java.io.Serializable interface. */
    public static final File INTERFACE_IMPLEMENT_TEST_DIR_2 = new File(
            "../org.splevo.tests/testmodels/implementation/interface.import.test/serializable");

    /**
     * Load the variation point model graph for the GCD example.
     * 
     * @return The loaded graph.
     * @throws DiffingException
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
     * @throws DiffingException
     *             Identifies a failed diffing.
     */
    public static VariationPointModel loadGCDVPMModel() throws Exception {

        DiffModel diffModel = loadGCDDiffModel();

        Java2KDMVPMBuilder java2KDMVPMBuilder = new Java2KDMVPMBuilder();
        VariationPointModel initialVpm = java2KDMVPMBuilder.buildVPM(diffModel, "LEADING", "INTEGRATION");

        return initialVpm;
    }

    /**
     * Load the diffing model.
     * 
     * @param leadingModel
     * @param integrationModel
     * @param ignorePackages
     * @return
     * @throws DiffingException
     *             Identifies a failed diffing.
     */
    public static DiffModel loadGCDDiffModel() throws Exception {

        List<String> ignorePackages = new ArrayList<String>();
        ignorePackages.add("java.lang");
        ignorePackages.add("java.math");
        ignorePackages.add("java.io");
        ignorePackages.add("org.jscience.*");
        ignorePackages.add("javolution.*");

        Map<String, Object> diffOptions = new LinkedHashMap<String, Object>();
        diffOptions.put(JavaDiffer.OPTION_JAVA_IGNORE_PACKAGES, ignorePackages);

        Java2KDMDiffer differ = new Java2KDMDiffer();

        DiffModel diffModel = differ.doDiff(NATIVE_JAVA2KDMMODEL_DIR.toURI(), JSCIENCE_JAVA2KDMMODEL_DIR.toURI(),
                diffOptions);

        return diffModel;
    }

    /**
     * Load the diffing model.
     * 
     * @param leadingModel
     * @param integrationModel
     * @param ignorePackages
     * @return
     * @throws DiffingException
     *             Identifies a failed diffing.
     */
    public static DiffModel loadInterfaceImplementsDiffModel() throws DiffingException, DiffingNotSupportedException {

        Map<String, Object> diffOptions = new LinkedHashMap<String, Object>();
        diffOptions.put(JavaDiffer.OPTION_JAVA_IGNORE_PACKAGES, Arrays.asList("java.*"));

        Java2KDMDiffer differ = new Java2KDMDiffer();
        DiffModel diffModel = differ.doDiff(INTERFACE_IMPLEMENT_TEST_DIR_1.toURI(),
                INTERFACE_IMPLEMENT_TEST_DIR_2.toURI(), diffOptions);

        return diffModel;
    }

}
