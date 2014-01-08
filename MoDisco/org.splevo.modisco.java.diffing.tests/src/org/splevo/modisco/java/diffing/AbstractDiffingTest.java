package org.splevo.modisco.java.diffing;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Before;

/**
 * Abstract base class for diffing tests.
 */
public abstract class AbstractDiffingTest {

    /** The default options to be used in the tests. */
    protected Map<String, String> diffOptions = null;

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @Before
    public void setUp() {
        // set up a basic logging configuration for the test environment
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));

        // prepare the default differ and options
        diffOptions = new LinkedHashMap<String, String>();
        StringBuilder sb = new StringBuilder();
        sb.append("java.*");
        sb.append(System.getProperty("line.separator"));
        sb.append("org.jscience.*");
        sb.append(System.getProperty("line.separator"));
        sb.append("javolution.*");
        diffOptions.put(Java2KDMDiffer.OPTION_JAVA_IGNORE_PACKAGES, sb.toString());
    }

    /**
     * Save a project model to a specified file.
     *
     * @param comparisonModel
     *            The model to save.
     * @param relativeFilePath
     *            The eclipse workspace relative file path to save to.
     * @throws IOException
     *             identifies that the file could not be written.
     */
    public static void save(Comparison comparisonModel, String relativeFilePath) throws IOException {

        String fileExtension = getFileExtension(relativeFilePath);

        // try to write to the project file
        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put(fileExtension, new XMIResourceFactoryImpl());
        ResourceSet resSet = new ResourceSetImpl();
        final Resource resource = resSet.createResource(URI.createFileURI(relativeFilePath));
        resource.getContents().add(comparisonModel);

        resource.save(Collections.EMPTY_MAP);
    }

    /**
     * Get the file extension of a file.
     *
     * @param path
     *            The file path to get the extension for.
     * @return The file extension or null if none found.
     */
    private static String getFileExtension(String path) {
        if (path == null) {
            return null;
        }
        String fileExtension = path.substring(path.lastIndexOf('.') + 1);
        return fileExtension;
    }
}
