/**
 */
package org.splevo.vpm.refinement;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Model</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A model containing all refinements identified for the variation point model.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.vpm.refinement.RefinementModel#getRefinements <em>Refinements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.vpm.refinement.RefinementPackage#getRefinementModel()
 * @model
 * @generated
 */
public interface RefinementModel extends EObject {
    /**
     * Returns the value of the '<em><b>Refinements</b></em>' containment reference list.
     * The list contents are of type {@link org.splevo.vpm.refinement.Refinement}.
     * It is bidirectional and its opposite is '{@link org.splevo.vpm.refinement.Refinement#getRefinementModel <em>Refinement Model</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Refinements</em>' containment reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Refinements</em>' containment reference list.
     * @see org.splevo.vpm.refinement.RefinementPackage#getRefinementModel_Refinements()
     * @see org.splevo.vpm.refinement.Refinement#getRefinementModel
     * @model opposite="refinementModel" containment="true"
     * @generated
     */
    EList<Refinement> getRefinements();

} // RefinementModel
