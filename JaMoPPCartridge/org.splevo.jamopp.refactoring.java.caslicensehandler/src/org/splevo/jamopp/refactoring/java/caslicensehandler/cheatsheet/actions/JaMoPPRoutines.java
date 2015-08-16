package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.emftext.language.java.JavaPackage;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersFactory;
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

public class JaMoPPRoutines {
	public static JavaResource getResourceOf(File file) {
		ResourceSet rs = setUpResourceSet();
		JavaResource javaResource = null;
	
		try {
			javaResource = (JavaResource) parseResource(file, rs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return javaResource;
	}
	
	private static Resource parseResource(File file, ResourceSet rs) throws IOException {
        String filePath = file.getAbsoluteFile().getAbsolutePath();
        URI uri = URI.createFileURI(filePath);
        return rs.getResource(uri, true);
    }
	
	private static ResourceSet setUpResourceSet() {
		ResourceSet rs = new ResourceSetImpl();
		EPackage.Registry.INSTANCE.put("http://www.emftext.org/java", JavaPackage.eINSTANCE);
		Map<String, Object> extensionToFactoryMap = rs.getResourceFactoryRegistry().getExtensionToFactoryMap();
		extensionToFactoryMap.put("java", new JavaSourceOrClassFileResourceFactoryImpl());
		extensionToFactoryMap.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		return rs;
	}
	
	public static void addConstantLicenseFieldTo(File file, String licenseName) {
		JavaResource resource = getResourceOf(file);		
		CompilationUnit compilationUnit = (CompilationUnit) resource.getContents().get(0);
		
		Field field = createField(licenseName);
		compilationUnit.getConcreteClassifier(file.getName()).getMembers().add(field);
		
		saveJaMoPPModel(resource);
	}
	
	//TODO see also SPLConfigurationUtil
	private static Field createField(String fieldName) {
		Field field = MembersFactory.eINSTANCE.createField();
		field.setName(fieldName.toUpperCase());
		
		StringReference value = ReferencesFactory.eINSTANCE.createStringReference();
        value.setValue(fieldName);
		field.setInitialValue(value);
		
		Class stringClass = ClassifiersFactory.eINSTANCE.createClass();
        stringClass.setName("String");
        ClassifierReference stringRef = TypesFactory.eINSTANCE.createClassifierReference();
        stringRef.setTarget(stringClass);
        field.setTypeReference(stringRef);
        
        field.addModifier(ModifiersFactory.eINSTANCE.createPublic());
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
