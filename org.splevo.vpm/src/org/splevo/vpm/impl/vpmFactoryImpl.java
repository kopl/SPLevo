/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.vpm.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.splevo.vpm.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class vpmFactoryImpl extends EFactoryImpl implements vpmFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static vpmFactory init() {
		try {
			vpmFactory thevpmFactory = (vpmFactory)EPackage.Registry.INSTANCE.getEFactory("http://splevo.org/vpm/1.0"); 
			if (thevpmFactory != null) {
				return thevpmFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new vpmFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public vpmFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case vpmPackage.VARIATION_POINT_MODEL: return createVariationPointModel();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariationPointModel createVariationPointModel() {
		VariationPointModelImpl variationPointModel = new VariationPointModelImpl();
		return variationPointModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public vpmPackage getvpmPackage() {
		return (vpmPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static vpmPackage getPackage() {
		return vpmPackage.eINSTANCE;
	}

} //vpmFactoryImpl
