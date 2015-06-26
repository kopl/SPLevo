/**
 */
package org.splevo.vpm.software;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.splevo.vpm.software.SoftwareFactory
 * @model kind="package"
 * @generated
 */
public interface SoftwarePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "software";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://splevo.org/vpm/1.0/software";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "software";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SoftwarePackage eINSTANCE = org.splevo.vpm.software.impl.SoftwarePackageImpl
			.init();

	/**
	 * The meta object id for the '{@link org.splevo.vpm.software.SoftwareElement <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.vpm.software.SoftwareElement
	 * @see org.splevo.vpm.software.impl.SoftwarePackageImpl#getSoftwareElement()
	 * @generated
	 */
	int SOFTWARE_ELEMENT = 0;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The operation id for the '<em>Get Label</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_ELEMENT___GET_LABEL = 0;

	/**
	 * The operation id for the '<em>Get Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_ELEMENT___GET_NAME = 1;

	/**
	 * The operation id for the '<em>Get Source Location</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_ELEMENT___GET_SOURCE_LOCATION = 2;

	/**
	 * The operation id for the '<em>Get Wrapped Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_ELEMENT___GET_WRAPPED_ELEMENT = 3;

	/**
	 * The number of operations of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_ELEMENT_OPERATION_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.splevo.vpm.software.JavaSoftwareElement <em>Java Software Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.vpm.software.JavaSoftwareElement
	 * @see org.splevo.vpm.software.impl.SoftwarePackageImpl#getJavaSoftwareElement()
	 * @generated
	 */
	int JAVA_SOFTWARE_ELEMENT = 1;

	/**
	 * The number of structural features of the '<em>Java Software Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_SOFTWARE_ELEMENT_FEATURE_COUNT = SOFTWARE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Label</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_SOFTWARE_ELEMENT___GET_LABEL = SOFTWARE_ELEMENT___GET_LABEL;

	/**
	 * The operation id for the '<em>Get Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_SOFTWARE_ELEMENT___GET_NAME = SOFTWARE_ELEMENT___GET_NAME;

	/**
	 * The operation id for the '<em>Get Source Location</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_SOFTWARE_ELEMENT___GET_SOURCE_LOCATION = SOFTWARE_ELEMENT___GET_SOURCE_LOCATION;

	/**
	 * The operation id for the '<em>Get Wrapped Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_SOFTWARE_ELEMENT___GET_WRAPPED_ELEMENT = SOFTWARE_ELEMENT___GET_WRAPPED_ELEMENT;

	/**
	 * The number of operations of the '<em>Java Software Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_SOFTWARE_ELEMENT_OPERATION_COUNT = SOFTWARE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.splevo.vpm.software.impl.SourceLocationImpl <em>Source Location</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.vpm.software.impl.SourceLocationImpl
	 * @see org.splevo.vpm.software.impl.SoftwarePackageImpl#getSourceLocation()
	 * @generated
	 */
	int SOURCE_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>File Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_LOCATION__FILE_PATH = 0;

	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_LOCATION__START_LINE = 1;

	/**
	 * The feature id for the '<em><b>Start Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_LOCATION__START_POSITION = 2;

	/**
	 * The feature id for the '<em><b>End Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_LOCATION__END_POSITION = 3;

	/**
	 * The number of structural features of the '<em>Source Location</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_LOCATION_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Source Location</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_LOCATION_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link org.splevo.vpm.software.SoftwareElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see org.splevo.vpm.software.SoftwareElement
	 * @generated
	 */
	EClass getSoftwareElement();

	/**
	 * Returns the meta object for the '{@link org.splevo.vpm.software.SoftwareElement#getLabel() <em>Get Label</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Label</em>' operation.
	 * @see org.splevo.vpm.software.SoftwareElement#getLabel()
	 * @generated
	 */
	EOperation getSoftwareElement__GetLabel();

	/**
	 * Returns the meta object for the '{@link org.splevo.vpm.software.SoftwareElement#getName() <em>Get Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Name</em>' operation.
	 * @see org.splevo.vpm.software.SoftwareElement#getName()
	 * @generated
	 */
	EOperation getSoftwareElement__GetName();

	/**
	 * Returns the meta object for the '{@link org.splevo.vpm.software.SoftwareElement#getSourceLocation() <em>Get Source Location</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Source Location</em>' operation.
	 * @see org.splevo.vpm.software.SoftwareElement#getSourceLocation()
	 * @generated
	 */
	EOperation getSoftwareElement__GetSourceLocation();

	/**
	 * Returns the meta object for the '{@link org.splevo.vpm.software.SoftwareElement#getWrappedElement() <em>Get Wrapped Element</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Wrapped Element</em>' operation.
	 * @see org.splevo.vpm.software.SoftwareElement#getWrappedElement()
	 * @generated
	 */
	EOperation getSoftwareElement__GetWrappedElement();

	/**
	 * Returns the meta object for class '{@link org.splevo.vpm.software.JavaSoftwareElement <em>Java Software Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Java Software Element</em>'.
	 * @see org.splevo.vpm.software.JavaSoftwareElement
	 * @generated
	 */
	EClass getJavaSoftwareElement();

	/**
	 * Returns the meta object for class '{@link org.splevo.vpm.software.SourceLocation <em>Source Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Source Location</em>'.
	 * @see org.splevo.vpm.software.SourceLocation
	 * @generated
	 */
	EClass getSourceLocation();

	/**
	 * Returns the meta object for the attribute '{@link org.splevo.vpm.software.SourceLocation#getFilePath <em>File Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Path</em>'.
	 * @see org.splevo.vpm.software.SourceLocation#getFilePath()
	 * @see #getSourceLocation()
	 * @generated
	 */
	EAttribute getSourceLocation_FilePath();

	/**
	 * Returns the meta object for the attribute '{@link org.splevo.vpm.software.SourceLocation#getStartLine <em>Start Line</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Line</em>'.
	 * @see org.splevo.vpm.software.SourceLocation#getStartLine()
	 * @see #getSourceLocation()
	 * @generated
	 */
	EAttribute getSourceLocation_StartLine();

	/**
	 * Returns the meta object for the attribute '{@link org.splevo.vpm.software.SourceLocation#getStartPosition <em>Start Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Position</em>'.
	 * @see org.splevo.vpm.software.SourceLocation#getStartPosition()
	 * @see #getSourceLocation()
	 * @generated
	 */
	EAttribute getSourceLocation_StartPosition();

	/**
	 * Returns the meta object for the attribute '{@link org.splevo.vpm.software.SourceLocation#getEndPosition <em>End Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End Position</em>'.
	 * @see org.splevo.vpm.software.SourceLocation#getEndPosition()
	 * @see #getSourceLocation()
	 * @generated
	 */
	EAttribute getSourceLocation_EndPosition();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SoftwareFactory getSoftwareFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.splevo.vpm.software.SoftwareElement <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.vpm.software.SoftwareElement
		 * @see org.splevo.vpm.software.impl.SoftwarePackageImpl#getSoftwareElement()
		 * @generated
		 */
		EClass SOFTWARE_ELEMENT = eINSTANCE.getSoftwareElement();

		/**
		 * The meta object literal for the '<em><b>Get Label</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation SOFTWARE_ELEMENT___GET_LABEL = eINSTANCE
				.getSoftwareElement__GetLabel();

		/**
		 * The meta object literal for the '<em><b>Get Name</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation SOFTWARE_ELEMENT___GET_NAME = eINSTANCE
				.getSoftwareElement__GetName();

		/**
		 * The meta object literal for the '<em><b>Get Source Location</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation SOFTWARE_ELEMENT___GET_SOURCE_LOCATION = eINSTANCE
				.getSoftwareElement__GetSourceLocation();

		/**
		 * The meta object literal for the '<em><b>Get Wrapped Element</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation SOFTWARE_ELEMENT___GET_WRAPPED_ELEMENT = eINSTANCE
				.getSoftwareElement__GetWrappedElement();

		/**
		 * The meta object literal for the '{@link org.splevo.vpm.software.JavaSoftwareElement <em>Java Software Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.vpm.software.JavaSoftwareElement
		 * @see org.splevo.vpm.software.impl.SoftwarePackageImpl#getJavaSoftwareElement()
		 * @generated
		 */
		EClass JAVA_SOFTWARE_ELEMENT = eINSTANCE.getJavaSoftwareElement();

		/**
		 * The meta object literal for the '{@link org.splevo.vpm.software.impl.SourceLocationImpl <em>Source Location</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.splevo.vpm.software.impl.SourceLocationImpl
		 * @see org.splevo.vpm.software.impl.SoftwarePackageImpl#getSourceLocation()
		 * @generated
		 */
		EClass SOURCE_LOCATION = eINSTANCE.getSourceLocation();

		/**
		 * The meta object literal for the '<em><b>File Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_LOCATION__FILE_PATH = eINSTANCE
				.getSourceLocation_FilePath();

		/**
		 * The meta object literal for the '<em><b>Start Line</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_LOCATION__START_LINE = eINSTANCE
				.getSourceLocation_StartLine();

		/**
		 * The meta object literal for the '<em><b>Start Position</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_LOCATION__START_POSITION = eINSTANCE
				.getSourceLocation_StartPosition();

		/**
		 * The meta object literal for the '<em><b>End Position</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_LOCATION__END_POSITION = eINSTANCE
				.getSourceLocation_EndPosition();

	}

} //SoftwarePackage
