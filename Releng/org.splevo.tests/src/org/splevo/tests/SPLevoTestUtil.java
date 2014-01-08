package org.splevo.tests;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.compare.Comparison;
import org.splevo.jamopp.diffing.JaMoPPDiffer;
import org.splevo.jamopp.vpm.builder.JaMoPPVPMBuilder;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Utility class for the common SPLevo test infrastructure to prevent redundant code and stream line
 * test development.
 */
public class SPLevoTestUtil {

    /** Source path to the native calculator implementation. */
    public static final File NATIVE_DIR = new File(
            "../org.splevo.tests/testmodels/implementation/gcd/native");

    /** Source path to the jscience based calculator implementation. */
    public static final File JSCIENCE_DIR = new File(
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

        JaMoPPVPMBuilder jamoppVPMBuilder = new JaMoPPVPMBuilder();
        VariationPointModel initialVpm = jamoppVPMBuilder.buildVPM(diffModel, "LEADING", "INTEGRATION");

        return initialVpm;
    }

    /**
     * Load the diffing model.
     *
     * @return The loaded diff model.
     * @throws Exception
     *             Identifies a failed diffing.
     */
    public static Comparison loadGCDDiffModel() throws Exception {

        StringBuilder ignorePackages = new StringBuilder();
        ignorePackages.append("java.*");
        ignorePackages.append(System.getProperty("line.separator"));
        ignorePackages.append("org.jscience.*");
        ignorePackages.append(System.getProperty("line.separator"));
        ignorePackages.append("javolution.*");

        Map<String, String> diffOptions = new LinkedHashMap<String, String>();
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_IGNORE_PACKAGES, ignorePackages.toString());

        JaMoPPDiffer differ = new JaMoPPDiffer();

        Comparison diffModel = differ.doDiff(NATIVE_DIR.toURI(), JSCIENCE_DIR.toURI(),
                diffOptions);

        return diffModel;
    }

    /**
     * Load the diffing model.
     *
     * @param leadingModel
     * @param integrationModel
     * @return The resulting comparison model.
     * @throws Exception
     *             Identifies a failed diffing.
     */
    public static Comparison loadInterfaceImplementsDiffModel() throws Exception {

        Map<String, String> diffOptions = new LinkedHashMap<String, String>();
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_IGNORE_PACKAGES, "java.*");

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison diffModel = differ.doDiff(INTERFACE_IMPLEMENT_TEST_DIR_1.toURI(),
                INTERFACE_IMPLEMENT_TEST_DIR_2.toURI(), diffOptions);

        return diffModel;
    }

}
