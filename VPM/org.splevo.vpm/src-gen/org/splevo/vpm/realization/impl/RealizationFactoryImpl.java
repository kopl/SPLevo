/**
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Benjamin Klatt
 */
package org.splevo.vpm.realization.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.splevo.vpm.realization.*;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.RealizationPackage;
import org.splevo.vpm.realization.VariabilityMechanism;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RealizationFactoryImpl extends EFactoryImpl implements
		RealizationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RealizationFactory init() {
		try {
			RealizationFactory theRealizationFactory = (RealizationFactory) EPackage.Registry.INSTANCE
					.getEFactory(RealizationPackage.eNS_URI);
			if (theRealizationFactory != null) {
				return theRealizationFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RealizationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RealizationFactoryImpl() {
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
		case RealizationPackage.VARIABILITY_MECHANISM:
			return createVariabilityMechanism();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName()
					+ "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariabilityMechanism createVariabilityMechanism() {
		VariabilityMechanismImpl variabilityMechanism = new VariabilityMechanismImpl();
		return variabilityMechanism;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RealizationPackage getRealizationPackage() {
		return (RealizationPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static RealizationPackage getPackage() {
		return RealizationPackage.eINSTANCE;
	}

} //RealizationFactoryImpl
