package org.splevo.modisco.util;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.junit.Test;

/**
 * The Class KDMUtilTest.
 */
public class KDMUtilTest {

    /**
     * Test load kdm model from directory.
     * 
     * @throws IOException
     *             Identifies that the model could not be loaded
     */
    @Test
    public final void testLoadKDMModelFromDirectory() throws IOException {
        File java2kdmModelFile = new File("testmodels/sourcemodels/Native/_java2kdm.xmi");

        JavaApplication applicationModel = KDMUtil.loadKDMModel(java2kdmModelFile);
        assertNotNull("No overarching model has been extracted", applicationModel);
        assertNotNull("No java model has been extracted", applicationModel.getJavaModel());
        assertNotNull("No inventory model has been extracted", applicationModel.getDeploymentModel());
    }

}
