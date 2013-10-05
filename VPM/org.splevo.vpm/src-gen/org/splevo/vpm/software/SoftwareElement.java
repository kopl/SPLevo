/**
 */
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
     * @model kind="operation"
     * @generated
     */
    String getLabel();
} // SoftwareElement
