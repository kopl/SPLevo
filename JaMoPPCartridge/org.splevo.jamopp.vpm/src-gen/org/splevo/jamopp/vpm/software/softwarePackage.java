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
package org.splevo.jamopp.vpm.software;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.splevo.vpm.software.SoftwarePackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.splevo.jamopp.vpm.software.softwareFactory
 * @model kind="package"
 * @generated
 */
public interface softwarePackage extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "software";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://splevo.org/jamopp/vpm/1.0/software";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "software";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	softwarePackage eINSTANCE = org.splevo.jamopp.vpm.software.impl.softwarePackageImpl
			.init();

	/**
	 * The meta object id for the '
	 * {@link org.splevo.jamopp.vpm.software.impl.JaMoPPSoftwareElementImpl
	 * <em>Ja Mo PP Software Element</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.splevo.jamopp.vpm.software.impl.JaMoPPSoftwareElementImpl
	 * @see org.splevo.jamopp.vpm.software.impl.softwarePackageImpl#getJaMoPPSoftwareElement()
	 * @generated
	 */
	int JA_MO_PP_SOFTWARE_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Jamopp Element</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JA_MO_PP_SOFTWARE_ELEMENT__JAMOPP_ELEMENT = SoftwarePackage.JAVA_SOFTWARE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '
	 * <em>Ja Mo PP Software Element</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JA_MO_PP_SOFTWARE_ELEMENT_FEATURE_COUNT = SoftwarePackage.JAVA_SOFTWARE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Label</em>' operation. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JA_MO_PP_SOFTWARE_ELEMENT___GET_LABEL = SoftwarePackage.JAVA_SOFTWARE_ELEMENT___GET_LABEL;

	/**
	 * The operation id for the '<em>Get Name</em>' operation. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JA_MO_PP_SOFTWARE_ELEMENT___GET_NAME = SoftwarePackage.JAVA_SOFTWARE_ELEMENT___GET_NAME;

	/**
	 * The operation id for the '<em>Get Source Location</em>' operation. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JA_MO_PP_SOFTWARE_ELEMENT___GET_SOURCE_LOCATION = SoftwarePackage.JAVA_SOFTWARE_ELEMENT___GET_SOURCE_LOCATION;

	/**
	 * The operation id for the '<em>Get Wrapped Element</em>' operation. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JA_MO_PP_SOFTWARE_ELEMENT___GET_WRAPPED_ELEMENT = SoftwarePackage.JAVA_SOFTWARE_ELEMENT___GET_WRAPPED_ELEMENT;

	/**
	 * The number of operations of the '<em>Ja Mo PP Software Element</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JA_MO_PP_SOFTWARE_ELEMENT_OPERATION_COUNT = SoftwarePackage.JAVA_SOFTWARE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '
	 * {@link org.splevo.jamopp.vpm.software.impl.CommentableSoftwareElementImpl
	 * <em>Commentable Software Element</em>}' class. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see org.splevo.jamopp.vpm.software.impl.CommentableSoftwareElementImpl
	 * @see org.splevo.jamopp.vpm.software.impl.softwarePackageImpl#getCommentableSoftwareElement()
	 * @generated
	 */
	int COMMENTABLE_SOFTWARE_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMMENTABLE_SOFTWARE_ELEMENT__ID = SoftwarePackage.JAVA_SOFTWARE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Compilation Unit</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMMENTABLE_SOFTWARE_ELEMENT__COMPILATION_UNIT = SoftwarePackage.JAVA_SOFTWARE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '
	 * <em>Commentable Software Element</em>' class. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMMENTABLE_SOFTWARE_ELEMENT_FEATURE_COUNT = SoftwarePackage.JAVA_SOFTWARE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Label</em>' operation. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMMENTABLE_SOFTWARE_ELEMENT___GET_LABEL = SoftwarePackage.JAVA_SOFTWARE_ELEMENT___GET_LABEL;

	/**
	 * The operation id for the '<em>Get Name</em>' operation. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMMENTABLE_SOFTWARE_ELEMENT___GET_NAME = SoftwarePackage.JAVA_SOFTWARE_ELEMENT___GET_NAME;

	/**
	 * The operation id for the '<em>Get Source Location</em>' operation. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMMENTABLE_SOFTWARE_ELEMENT___GET_SOURCE_LOCATION = SoftwarePackage.JAVA_SOFTWARE_ELEMENT___GET_SOURCE_LOCATION;

	/**
	 * The operation id for the '<em>Get Wrapped Element</em>' operation. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMMENTABLE_SOFTWARE_ELEMENT___GET_WRAPPED_ELEMENT = SoftwarePackage.JAVA_SOFTWARE_ELEMENT___GET_WRAPPED_ELEMENT;

	/**
	 * The number of operations of the '<em>Commentable Software Element</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMMENTABLE_SOFTWARE_ELEMENT_OPERATION_COUNT = SoftwarePackage.JAVA_SOFTWARE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * Returns the meta object for class '
	 * {@link org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement
	 * <em>Ja Mo PP Software Element</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Ja Mo PP Software Element</em>'.
	 * @see org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement
	 * @generated
	 */
	EClass getJaMoPPSoftwareElement();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement#getJamoppElement
	 * <em>Jamopp Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Jamopp Element</em>'.
	 * @see org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement#getJamoppElement()
	 * @see #getJaMoPPSoftwareElement()
	 * @generated
	 */
	EReference getJaMoPPSoftwareElement_JamoppElement();

	/**
	 * Returns the meta object for class '
	 * {@link org.splevo.jamopp.vpm.software.CommentableSoftwareElement
	 * <em>Commentable Software Element</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Commentable Software Element</em>
	 *         '.
	 * @see org.splevo.jamopp.vpm.software.CommentableSoftwareElement
	 * @generated
	 */
	EClass getCommentableSoftwareElement();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.splevo.jamopp.vpm.software.CommentableSoftwareElement#getId
	 * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.splevo.jamopp.vpm.software.CommentableSoftwareElement#getId()
	 * @see #getCommentableSoftwareElement()
	 * @generated
	 */
	EAttribute getCommentableSoftwareElement_Id();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.splevo.jamopp.vpm.software.CommentableSoftwareElement#getCompilationUnit
	 * <em>Compilation Unit</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the reference '<em>Compilation Unit</em>'.
	 * @see org.splevo.jamopp.vpm.software.CommentableSoftwareElement#getCompilationUnit()
	 * @see #getCommentableSoftwareElement()
	 * @generated
	 */
	EReference getCommentableSoftwareElement_CompilationUnit();

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	softwareFactory getsoftwareFactory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that
	 * represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each operation of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '
		 * {@link org.splevo.jamopp.vpm.software.impl.JaMoPPSoftwareElementImpl
		 * <em>Ja Mo PP Software Element</em>}' class. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see org.splevo.jamopp.vpm.software.impl.JaMoPPSoftwareElementImpl
		 * @see org.splevo.jamopp.vpm.software.impl.softwarePackageImpl#getJaMoPPSoftwareElement()
		 * @generated
		 */
		EClass JA_MO_PP_SOFTWARE_ELEMENT = eINSTANCE.getJaMoPPSoftwareElement();

		/**
		 * The meta object literal for the '<em><b>Jamopp Element</b></em>'
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference JA_MO_PP_SOFTWARE_ELEMENT__JAMOPP_ELEMENT = eINSTANCE
				.getJaMoPPSoftwareElement_JamoppElement();

		/**
		 * The meta object literal for the '
		 * {@link org.splevo.jamopp.vpm.software.impl.CommentableSoftwareElementImpl
		 * <em>Commentable Software Element</em>}' class. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @see org.splevo.jamopp.vpm.software.impl.CommentableSoftwareElementImpl
		 * @see org.splevo.jamopp.vpm.software.impl.softwarePackageImpl#getCommentableSoftwareElement()
		 * @generated
		 */
		EClass COMMENTABLE_SOFTWARE_ELEMENT = eINSTANCE
				.getCommentableSoftwareElement();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute COMMENTABLE_SOFTWARE_ELEMENT__ID = eINSTANCE
				.getCommentableSoftwareElement_Id();

		/**
		 * The meta object literal for the '<em><b>Compilation Unit</b></em>'
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference COMMENTABLE_SOFTWARE_ELEMENT__COMPILATION_UNIT = eINSTANCE
				.getCommentableSoftwareElement_CompilationUnit();

	}

} // softwarePackage
