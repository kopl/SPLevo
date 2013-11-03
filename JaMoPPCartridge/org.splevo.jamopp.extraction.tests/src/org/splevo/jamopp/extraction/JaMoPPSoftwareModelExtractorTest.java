package org.splevo.jamopp.extraction;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.containers.CompilationUnit;
import org.junit.Test;
import org.splevo.extraction.SoftwareModelExtractionException;
import org.splevo.tests.AbstractTest;

/**
 * Test case for basic functionality of the JaMoPP based extractor.
 */
public class JaMoPPSoftwareModelExtractorTest extends AbstractTest {

    private static final String TEST_TARGET_PATH = "test/models/sourcemodel/calculator-jscience";

    private Logger logger = Logger.getLogger(JaMoPPSoftwareModelExtractorTest.class);

    /** The relative path to the project to extract java sources from. */
    private static final String TEST_PROJECT_PATH = "test/project/calculator-jscience";

    /**
     * Test extraction functionality.
     * 
     * @throws SoftwareModelExtractionException
     *             for any exception during the extraction process.
     */
    @Test
    public void testExtractSoftwareModel() throws SoftwareModelExtractionException {

        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        List<URI> projectPaths = new ArrayList<URI>();
        projectPaths.add(URI.createFileURI(TEST_PROJECT_PATH));
        URI targetURI = URI.createFileURI(TEST_TARGET_PATH);
        ResourceSet extractionResult = extractor.extractSoftwareModel(projectPaths, new NullProgressMonitor(),
                targetURI);

        assertThat(extractionResult, notNullValue());

        // check the compilation unit count
        int projectResourceCount = 0;
        List<Resource> projectResources = new ArrayList<Resource>();
        for (Resource resource : extractionResult.getResources()) {
            if (!resource.getURI().toString().startsWith("pathmap:")) {
                projectResourceCount++;
                projectResources.add(resource);
            }
            for (EObject topLevelEObject : resource.getContents()) {
                if (!(topLevelEObject instanceof CompilationUnit)) {
                    logger.info("TopLevelEObject: " + topLevelEObject.getClass().getSimpleName());
                }
            }
        }

        int expectedTestClasses = 3;
        assertThat(projectResourceCount, equalTo(expectedTestClasses));
    }

    /**
     * Test a valid id to be returned.
     */
    @Test
    public void testGetId() {
        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        assertThat(extractor.getId(), notNullValue());
    }

    /**
     * Test a valid label to be returned.
     */
    @Test
    public void testGetLabel() {
        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        assertThat(extractor.getLabel(), notNullValue());
    }

}
