package org.splevo.vpm.variability;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.splevo.vpm.software.SoftwareElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variation Point Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.vpm.variability.VariationPointModel#getVariationPointGroups <em>Variation Point Groups</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.VariationPointModel#getSoftwareElements <em>Software Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.vpm.variability.variabilityPackage#getVariationPointModel()
 * @model
 * @generated
 */
public interface VariationPointModel extends EObject {
    /**
     * Returns the value of the '<em><b>Variation Point Groups</b></em>' containment reference list.
     * The list contents are of type {@link org.splevo.vpm.variability.VariationPointGroup}.
     * It is bidirectional and its opposite is '{@link org.splevo.vpm.variability.VariationPointGroup#getModel <em>Model</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variation Point Groups</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Variation Point Groups</em>' containment reference list.
     * @see org.splevo.vpm.variability.variabilityPackage#getVariationPointModel_VariationPointGroups()
     * @see org.splevo.vpm.variability.VariationPointGroup#getModel
     * @model opposite="model" containment="true"
     * @generated
     */
    EList<VariationPointGroup> getVariationPointGroups();

    /**
     * Returns the value of the '<em><b>Software Elements</b></em>' containment reference list.
     * The list contents are of type {@link org.splevo.vpm.software.SoftwareElement}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Software Elements</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Software Elements</em>' containment reference list.
     * @see org.splevo.vpm.variability.variabilityPackage#getVariationPointModel_SoftwareElements()
     * @model containment="true"
     * @generated
     */
    EList<SoftwareElement> getSoftwareElements();

} // VariationPointModel
