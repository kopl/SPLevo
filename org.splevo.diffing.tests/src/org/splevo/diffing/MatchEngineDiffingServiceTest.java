/**
 * 
 */
package org.splevo.diffing;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeLeftTarget;
import org.eclipse.emf.compare.diff.metamodel.UpdateAttribute;
import org.eclipse.emf.compare.diff.metamodel.util.DiffSwitch;
import org.eclipse.emf.compare.util.ModelUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.eclipse.modisco.java.composition.javaapplication.JavaapplicationPackage;
import org.junit.Test;

/**
 * Unit test for the diffing service class.
 * 
 * @author Benjamin Klatt
 * 
 */
public class MatchEngineDiffingServiceTest {
	
	/** Source path to the native calculator implementation */
	private static final File NATIVE_JAVA2KDMMODEL_FILE = new File("testmodels/implementation/native/_java2kdm.xmi");
	
	/** Source path to the jscience based calculator implementation */
	private static final File JSCIENCE_JAVA2KDMMODEL_FILE = new File("testmodels/implementation/jscience/_java2kdm.xmi");

	/**
	 * Test method for
	 * {@link org.splevo.diffing.EMFCompareDiffingService#getDiff(org.eclipse.modisco.java.composition.javaapplication.JavaApplication, org.eclipse.modisco.java.composition.javaapplication.JavaApplication)}
	 * .
	 * 
	 * @throws IOException
	 *             Identifies that either the source models could not be loaded
	 *             or the diff model could not be serialized.
	 * @throws InterruptedException 
	 * @throws Exception
	 */
	@Test
	public final void testGetDiff() throws IOException, InterruptedException {
		
		JavaApplication leadingModel = loadKDMModel(NATIVE_JAVA2KDMMODEL_FILE);
		JavaApplication integrationModel = loadKDMModel(JSCIENCE_JAVA2KDMMODEL_FILE);
		
		MatchEngineDiffingService diffingService = new MatchEngineDiffingService();
		DiffModel diff = diffingService.doDiff(leadingModel,integrationModel);
		
		EList<DiffElement> differences = diff.getDifferences();
		for (DiffElement diffElement : differences) {
			System.out.println(diffElement.getKind()+": "+diffElement.getClass().getName());
		}
		
		System.out.println("Found Differences: "+differences.size());
		
		
		// TODO: process/interprete the differences persisited in the file below
		// This file can be smoothly opened with the sample reflective editor.
		// perhaps using a switch provided by emf compare?
		ModelUtils.save(diff, "diffModel.emfdiff");
	}

	  /**
	   * Load KDM model from the standard kdm file set.
	   * 
	   * Based on a supplied directory, the method looks up the default kmd files within this 
	   * and loads them as a JavaApplication model.
	   * 
	   * Note that the method is limited to java specific application models because their is no
	   * other discoverer available at the moment.
	   * 
	   * @param directoryPath
	   *            the directory path to the persisted model. This should have a tailing slash.
	   * @return the java application model
	   * @throws IOException
	   *             Identifies that the file could not be loaded
	   */
	  public static JavaApplication loadKDMModel(File java2kdmModelFile) throws IOException{

		// check that the file is accessible
		if (!java2kdmModelFile.canRead()) {
			throw new IllegalArgumentException("cannot read model file "+ java2kdmModelFile);
		}
	    
	    // load the required meta class packages
	    JavaapplicationPackage.eINSTANCE.eClass();
	    JavaPackage.eINSTANCE.eClass();
	    
	    // prepare the resource
	    ResourceSet resourceSet = new ResourceSetImpl();
	    
	    // a cross reference adapter must be registered to resolve the references between the models
	    resourceSet.eAdapters().add(new ECrossReferenceAdapter());

		EObject model = ModelUtils.load(java2kdmModelFile, resourceSet);
	    if(!(model instanceof JavaApplication)){
	      return null;
	    }
		JavaApplication javaApplicationModel = (JavaApplication)model;
	    
	    return javaApplicationModel;
	  }
}
