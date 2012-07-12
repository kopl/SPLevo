package org.splevo.diffing.kdm;

import static org.junit.Assert.*;

import java.io.IOException;

import org.eclipse.emf.compare.util.ModelUtils;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.junit.Test;

/**
 * The Class KDMUtilTest.
 */
public class KDMUtilTest {

	/**
	 * Test load kdm model from directory.
	 * @throws IOException Identifies that the model could not be loaded
	 */
	@Test
	public final void testLoadKDMModelFromDirectory() throws IOException {
		String baseDirectory = "testmodels/implementation/native/";
		JavaApplication applicationModel = KDMUtil.loadKDMModelFromDirectory(baseDirectory);
		assertNotNull("No overarching model has been extracted",applicationModel);
		assertNotNull("No java model has been extracted",applicationModel.getJavaModel());
		assertNotNull("No inventory model has been extracted",applicationModel.getDeploymentModel());
		System.out.println(ModelUtils.serialize(applicationModel.getDeploymentModel()));
	}

}
