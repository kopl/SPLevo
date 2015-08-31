package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.emftext.language.java.JavaPackage;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.references.StringReference;
import org.emftext.language.java.resource.JavaSourceOrClassFileResourceFactoryImpl;
import org.emftext.language.java.resource.java.mopp.JavaResource;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.TypesFactory;
import org.splevo.jamopp.vpm.software.CommentableSoftwareElement;
import org.splevo.jamopp.vpm.software.JaMoPPJavaSoftwareElement;
import org.splevo.refactoring.ResourceProcessorService;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Implements some routines on the JaMoPP-model.
 */
public class JaMoPPRoutines {
	/**
	 * Returns the resource object to a given file.
	 * @param file
	 * 			represents the file.
	 * @return
	 * 			returns the resource to the corresponding file.
	 */
	public static Resource getResourceOf(File file) {
		ResourceSet resourceSet = ((JaMoPPJavaSoftwareElement) CASLicenseHandlerConfiguration.getVariationPoint().getLocation()).getJamoppElement().eResource()
                .getResourceSet();		
	
		return resourceSet.getResource(URI.createFileURI(file.getAbsolutePath()), true);
	}
	
	private static Resource parseResource(File file, ResourceSet rs) throws IOException {
        String filePath = file.getAbsoluteFile().getAbsolutePath();
        URI uri = URI.createFileURI(filePath);
        return rs.getResource(uri, false);
    }
	
	private static ResourceSet setUpResourceSet() {
		ResourceSet rs = new ResourceSetImpl();
		EPackage.Registry.INSTANCE.put("http://www.emftext.org/java", JavaPackage.eINSTANCE);
		Map<String, Object> extensionToFactoryMap = rs.getResourceFactoryRegistry().getExtensionToFactoryMap();
		extensionToFactoryMap.put("java", new JavaSourceOrClassFileResourceFactoryImpl());
		extensionToFactoryMap.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		return rs;
	}
	
	/**
	 * Adds a public static field to a give class.
	 * @param file
	 * 			represents a file.
	 * @param licenseName
	 * 			represents the license name.
	 */
	public static void addConstantLicenseFieldTo(File file, String licenseName) {
		Resource resource = JaMoPPRoutines.getResourceOf(file);
		CompilationUnit compilationUnit = (CompilationUnit) resource.getContents().get(0);
		
		Field field = createField(licenseName);
		
		ConcreteClassifier constantLicenseClass = compilationUnit.getContainedClassifier(FilenameUtils.removeExtension(file.getName()));
													//.getConcreteClassifier(FilenameUtils.removeExtension(file.getName()));
		if (constantLicenseClass != null)  {
			constantLicenseClass.getMembers().add(field);
			
			saveJaMoPPModel(resource);
		}
	}
	
	//TODO see also SPLConfigurationUtil
	private static Field createField(String fieldName) {	
		StringReference value = ReferencesFactory.eINSTANCE.createStringReference();
        value.setValue(fieldName);
        
        Field field = MembersFactory.eINSTANCE.createField();
		field.setName(fieldName.toUpperCase());
		field.setInitialValue(value);
		
		Class stringClass = ClassifiersFactory.eINSTANCE.createClass();
        stringClass.setName("String");
        ClassifierReference stringRef = TypesFactory.eINSTANCE.createClassifierReference();
        stringRef.setTarget(stringClass);
        
        field.setTypeReference(stringRef);
        field.makePublic();
        field.addModifier(ModifiersFactory.eINSTANCE.createStatic());
        field.addModifier(ModifiersFactory.eINSTANCE.createFinal());
        
        return field;
	}
	
	private static void saveJaMoPPModel(Resource eResource) {
        try {
            eResource.save(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
    }
}
