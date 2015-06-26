/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.jamopp.vpm.software.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.splevo.jamopp.vpm.software.*;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.jamopp.vpm.software.softwareFactory;
import org.splevo.jamopp.vpm.software.softwarePackage;

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
		case softwarePackage.COMMENTABLE_SOFTWARE_ELEMENT:
			return createCommentableSoftwareElement();
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
	public CommentableSoftwareElement createCommentableSoftwareElement() {
		CommentableSoftwareElementImpl commentableSoftwareElement = new CommentableSoftwareElementImpl();
		return commentableSoftwareElement;
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
