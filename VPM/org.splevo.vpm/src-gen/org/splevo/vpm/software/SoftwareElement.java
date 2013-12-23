package org.splevo.vpm.software;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An element of a software implementation.
 * <!-- end-model-doc -->
 *
 *
 * @see org.splevo.vpm.software.SoftwarePackage#getSoftwareElement()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface SoftwareElement extends EObject {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A representative string for the software element, for example to present it in the UI.
	 * This can be similar to the name plus an identifier of the type of the element.
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getLabel();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the name of a NamedElement, such as a public class declaration, a method, or others.
	 * In case of not named elements, it will return null.
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getName();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the source location of the software elements implementation.
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	SourceLocation getSourceLocation();
} // SoftwareElement
