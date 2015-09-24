package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.references.StringReference;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.TypesFactory;
import org.splevo.jamopp.vpm.software.JaMoPPJavaSoftwareElement;

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
		ResourceSet resourceSet = ((JaMoPPJavaSoftwareElement) CASLicenseHandlerConfiguration.getInstance().getVariationPoint()
									.getLocation()).getJamoppElement().eResource().getResourceSet();		
	
		return resourceSet.getResource(URI.createFileURI(file.getAbsolutePath()), true);
	}
	
	/**
	 * Adds a public static field to a give class.
	 * @param file
	 * 			represents a file.
	 * @param licenseName
	 * 			represents the license name.
	 */
	public static void addConstantLicenseFieldTo(File file, String licenseName) {
		if (isAlreadyStored(licenseName.toUpperCase())) {
			return;
		}
		
		Resource resource = JaMoPPRoutines.getResourceOf(file);
		CompilationUnit compilationUnit = (CompilationUnit) resource.getContents().get(0);
		
		Field field = createField(licenseName);
		
		ConcreteClassifier constantLicenseClass = compilationUnit.getContainedClassifier(FilenameUtils.removeExtension(file.getName()));

		if (constantLicenseClass != null)  {
			constantLicenseClass.getMembers().add(field);
			
			saveJaMoPPModel(resource);
		}
	}
	
	private static boolean isAlreadyStored(String licenseName) {
		for (String storedLicense : CASLicenseHandlerConfiguration.getInstance().getAllLicenses()) {
			if (storedLicense.equals(licenseName)) {
				return true;
			}
		}
		return false;
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
