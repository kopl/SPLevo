package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import java.io.IOException;
import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.core.IType;
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

import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


/**
 * Implements some routines on the JaMoPP-model.
 */
public class JaMoPPRoutines {
	private static Logger logger = Logger.getLogger(JaMoPPRoutines.class);
	
	/**
	 * Returns the concrete classifier (JaMoPP) for the given type (JavaCore).
	 * @param type
	 * 			represents the type.
	 * @return
	 * 			the matched classifier.
	 */
	public static Optional<ConcreteClassifier> getConcreteClassifierOf(IType type) {
		ResourceSet resourceSet = ((JaMoPPJavaSoftwareElement) CASLicenseHandlerConfiguration.getInstance().getVariationPoint()
									.getLocation()).getJamoppElement().eResource().getResourceSet();
		
		final String typeName = type.getElementName();
		URI resourceURI = URI.createPlatformResourceURI(type.getResource().getFullPath().toString(), true);
		Resource r = resourceSet.getResource(resourceURI, true);
		for (CompilationUnit cu : Iterables.filter(r.getContents(), CompilationUnit.class)) {
		    LinkedList<ConcreteClassifier> queue = Lists.newLinkedList(cu.getClassifiers());
		    while (!queue.isEmpty()) {
		        ConcreteClassifier classifier = queue.pop();
		        if (typeName.equals(classifier.getName())) {
		            return Optional.of(classifier);
		        }
		    }
		}
	
		return Optional.absent();
	}
	
	/**
	 * Adds a public static field to a give class.
	 * @param type
	 * 			represents the type.
	 * @param licenseName
	 * 			represents the license name.
	 */
	public static void addConstantLicenseFieldTo(IType type, String licenseName) {
		if (isAlreadyStored(licenseName.toUpperCase())) {
			return;
		}
		
		Optional<ConcreteClassifier> classifier = JaMoPPRoutines.getConcreteClassifierOf(type);
		if (classifier.isPresent()) {
		    Field field = createField(licenseName);
		    classifier.get().getMembers().add(field);
		    saveJaMoPPModel(classifier.get().eResource());
		} else {
		    logger.error("Could not add member field: " + licenseName);		    
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
        	logger.error("Could not save resource: " + eResource.getURI().lastSegment(), e);
        }
		
    }
}
