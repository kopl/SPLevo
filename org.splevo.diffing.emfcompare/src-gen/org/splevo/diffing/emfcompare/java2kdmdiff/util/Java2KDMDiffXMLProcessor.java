/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class Java2KDMDiffXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Java2KDMDiffXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		Java2KDMDiffPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the Java2KDMDiffResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new Java2KDMDiffResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new Java2KDMDiffResourceFactoryImpl());
		}
		return registrations;
	}

} //Java2KDMDiffXMLProcessor
