package org.splevo.tests;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.JavaClasspath;
import org.splevo.jamopp.diffing.JaMoPPDiffer;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;
import org.splevo.jamopp.vpm.builder.JaMoPPVPMBuilder;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.Lists;

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

        JaMoPPDiffer differ = new JaMoPPDiffer(new JaMoPPSoftwareModelExtractorWithJarHandling());

        Comparison diffModel = differ.doDiff(NATIVE_DIR.toURI(), JSCIENCE_DIR.toURI(), diffOptions);

        return diffModel;
    }
    
    /**
     * Modified software model extractor that can handle jar files in the project directory.
     */
    private static class JaMoPPSoftwareModelExtractorWithJarHandling extends JaMoPPSoftwareModelExtractor {
        
        private static final Logger LOGGER = Logger.getLogger(JaMoPPSoftwareModelExtractorWithJarHandling.class);

        @Override
        protected JavaClasspath determineClasspath(ResourceSet rs, Iterable<String> sourceModelPaths) {
            JavaClasspath classpath = super.determineClasspath(rs, sourceModelPaths);
            for (String jarFilePath : getAllJarFiles(sourceModelPaths)) {
                classpath.registerClassifierJar(URI.createFileURI(jarFilePath));
            }
            return classpath;
        }

        private List<String> getAllJarFiles(Iterable<String> projectPaths) {
            List<String> jarPaths = Lists.newArrayList();
            for (String projectPath : projectPaths) {
                Collection<File> jarFiles = FileUtils.listFiles(new File(projectPath), new String[] { "jar" }, true);
                for (File jarPath : jarFiles) {
                    try {
                        jarPaths.add(jarPath.getCanonicalPath());
                    } catch (IOException e) {
                        LOGGER.warn("Unable to access jar file: " + jarPath);
                    }
                }
            }
            return jarPaths;
        }

    }

}
