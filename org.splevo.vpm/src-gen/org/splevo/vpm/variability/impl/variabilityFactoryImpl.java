/**
 */
package org.splevo.vpm.variability.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.splevo.vpm.variability.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class variabilityFactoryImpl extends EFactoryImpl implements variabilityFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static variabilityFactory init() {
		try {
			variabilityFactory thevariabilityFactory = (variabilityFactory)EPackage.Registry.INSTANCE.getEFactory("http://splevo.org/vpm/1.0/variability"); 
			if (thevariabilityFactory != null) {
				return thevariabilityFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new variabilityFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public variabilityFactoryImpl() {
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
			case variabilityPackage.VARIATION_POINT: return createVariationPoint();
			case variabilityPackage.VARIANT: return createVariant();
			case variabilityPackage.VARIATION_POINT_MODEL: return createVariationPointModel();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariationPoint createVariationPoint() {
		VariationPointImpl variationPoint = new VariationPointImpl();
		return variationPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Variant createVariant() {
		VariantImpl variant = new VariantImpl();
		return variant;
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
	public variabilityPackage getvariabilityPackage() {
		return (variabilityPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static variabilityPackage getPackage() {
		return variabilityPackage.eINSTANCE;
	}

} //variabilityFactoryImpl
