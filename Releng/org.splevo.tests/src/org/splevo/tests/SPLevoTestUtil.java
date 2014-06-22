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
    private static final File NATIVE_DIR = new File("testmodels/implementation/gcd/native");

    /** Source path to the jscience based calculator implementation. */
    private static final File JSCIENCE_DIR = new File("testmodels/implementation/gcd/jscience");

    /**
     * Load the variation point model graph for the GCD example.
     *
     * NOTE: Using this method requires to copy the testmodels directory in the appropriate test. It
     * is recommended to use more specific tests!!
     *
     * @deprecated Recommended to use more specific fine-grained tests.
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
     * NOTE: Using this method requires to copy the testmodels directory in the appropriate test. It
     * is recommended to use more specific tests!!
     *
     * @deprecated Recommended to use more specific fine-grained tests.
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
    private static Comparison loadGCDDiffModel() throws Exception {

        StringBuilder ignorePackages = new StringBuilder();
        ignorePackages.append("java.*");
        ignorePackages.append(System.getProperty("line.separator"));
        ignorePackages.append("org.jscience.*");
        ignorePackages.append(System.getProperty("line.separator"));
        ignorePackages.append("javolution.*");

        Map<String, String> diffOptions = new LinkedHashMap<String, String>();
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_IGNORE_PACKAGES, ignorePackages.toString());

        JaMoPPDiffer differ = new JaMoPPDiffer();

        Comparison diffModel = differ.doDiff(NATIVE_DIR.toURI(), JSCIENCE_DIR.toURI(), diffOptions);

        return diffModel;
    }

}
