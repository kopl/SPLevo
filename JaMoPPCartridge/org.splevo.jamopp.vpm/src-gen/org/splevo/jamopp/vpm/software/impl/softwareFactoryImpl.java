/**
 */
package org.splevo.jamopp.vpm.software.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.splevo.jamopp.vpm.software.*;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class softwareFactoryImpl extends EFactoryImpl implements
		softwareFactory {
	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static softwareFactory init() {
		try {
			softwareFactory thesoftwareFactory = (softwareFactory) EPackage.Registry.INSTANCE
					.getEFactory(softwarePackage.eNS_URI);
			if (thesoftwareFactory != null) {
				return thesoftwareFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new softwareFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public softwareFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case softwarePackage.JA_MO_PP_SOFTWARE_ELEMENT:
			return createJaMoPPSoftwareElement();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName()
					+ "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public JaMoPPSoftwareElement createJaMoPPSoftwareElement() {
		JaMoPPSoftwareElementImpl jaMoPPSoftwareElement = new JaMoPPSoftwareElementImpl();
		return jaMoPPSoftwareElement;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public softwarePackage getsoftwarePackage() {
		return (softwarePackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static softwarePackage getPackage() {
		return softwarePackage.eINSTANCE;
	}

} // softwareFactoryImpl
