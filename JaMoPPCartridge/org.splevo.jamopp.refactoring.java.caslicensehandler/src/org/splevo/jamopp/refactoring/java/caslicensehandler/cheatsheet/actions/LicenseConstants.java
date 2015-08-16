package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.emftext.language.java.JavaPackage;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.resource.JavaSourceOrClassFileResourceFactoryImpl;
import org.emftext.language.java.resource.java.mopp.JavaResource;

public class LicenseConstants {

	private static File licenseConstants = null;
	
	public static File getLicenseConstant() {
		return licenseConstants;
	}
	
	public static void setLicenseConstant(File newLicenseConstantClass) {
		licenseConstants = newLicenseConstantClass;
	}
	
	public static String[] getAllLicenses() {
		
		if(null == licenseConstants) {
			return null;
		}
		
        JavaResource resource = JaMoPPRoutines.getResourceOf(licenseConstants);
        		
		return getAllLicensesBy((CompilationUnit) resource.getContents().get(0));
	}

	private static String[] getAllLicensesBy(CompilationUnit compilationUnit) {
		ArrayList<String> licenses = new ArrayList<String>();
		
		for (ConcreteClassifier concreteClassifier : compilationUnit.getClassifiers()) {
			for (Field field : concreteClassifier.getFields()) {
					licenses.add(field.getName());
			}
		}
		
		return licenses.toArray(new String[licenses.size()]);
	}
}
