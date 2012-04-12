package org.splevo.diffing.kdm;

import java.io.IOException;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.eclipse.modisco.java.composition.javaapplication.JavaapplicationPackage;

/**
 * The Class KDMUtils. Utility class to handle omg kdm models in general and
 * java specific extended models in specific.
 */
public class KDMUtil {
  
  /** The Constant FILENAME_KDM2JAVA. */
  public static final String FILENAME_KDM2JAVA   = "_java2kdm.xmi";
  
  /** The Constant FILENAME_KDM. */
  public static final String FILENAME_KDM     = "_kdm.xmi";
  
  /** The Constant FILENAME_JAVA. */
  public static final String FILENAME_JAVA     = "_java.xmi";

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
  public static JavaApplication loadKDMModelFromDirectory(String kdmBasePath) throws IOException{

    
    Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
    Map<String, Object> m = reg.getExtensionToFactoryMap();
    m.put("xmi", new XMIResourceFactoryImpl());

    // prepare the resource
    // including registering required packages and URI converters 
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getPackageRegistry().put(JavaapplicationPackage.eNS_URI,
                         JavaapplicationPackage.eINSTANCE);
    resourceSet.getPackageRegistry().put(JavaPackage.eNS_URI,
                        JavaPackage.eINSTANCE);
    
    // a cross reference adapter must be registered to resolve
    // the references between the models
    resourceSet.eAdapters().add(new ECrossReferenceAdapter());
    
    // The URI converter is required to map between the relative 
    // model references in the model and their absolute location
    resourceSet.getURIConverter().getURIMap().put(  URI.createURI(FILENAME_JAVA),
                          URI.createURI(kdmBasePath+FILENAME_JAVA));
    resourceSet.getURIConverter().getURIMap().put(  URI.createURI(FILENAME_KDM),
                          URI.createURI(kdmBasePath+FILENAME_KDM));

    // Get the resource
    String filePath = kdmBasePath+FILENAME_KDM2JAVA;
    Resource resourceJavaModel = resourceSet.getResource(URI.createURI(filePath), true);
    resourceJavaModel.load(null);
    
    
    EObject model = resourceJavaModel.getContents().get(0);
    
    // EObject model = ModelUtils.load(modelFile1, resourceSet);
    if(!(model instanceof JavaApplication)){
      return null;
    }
    JavaApplication kdmModel = (JavaApplication)model;
    
    return kdmModel;
  }
}