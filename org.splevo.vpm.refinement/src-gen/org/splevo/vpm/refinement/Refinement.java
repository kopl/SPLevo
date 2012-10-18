/**
 */
package org.splevo.vpm.refinement;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.splevo.vpm.variability.VariationPoint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Refinement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A refinement identifies a refinement within a variation point model.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.vpm.refinement.Refinement#getType <em>Type</em>}</li>
 *   <li>{@link org.splevo.vpm.refinement.Refinement#getVariationPoints <em>Variation Points</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.vpm.refinement.RefinementPackage#getRefinement()
 * @model
 * @generated
 */
public interface Refinement extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The default value is <code>"MANDATORY"</code>.
	 * The literals are from the enumeration {@link org.splevo.vpm.refinement.RefinementType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The refinement type defines how the refinement can or must be handled.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.splevo.vpm.refinement.RefinementType
	 * @see #setType(RefinementType)
	 * @see org.splevo.vpm.refinement.RefinementPackage#getRefinement_Type()
	 * @model default="MANDATORY" required="true"
	 * @generated
	 */
	RefinementType getType();

	/**
	 * Sets the value of the '{@link org.splevo.vpm.refinement.Refinement#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.splevo.vpm.refinement.RefinementType
	 * @see #getType()
	 * @generated
	 */
	void setType(RefinementType value);

	/**
	 * Returns the value of the '<em><b>Variation Points</b></em>' reference list.
	 * The list contents are of type {@link org.splevo.vpm.variability.VariationPoint}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The variation points to be merged.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Variation Points</em>' reference list.
	 * @see org.splevo.vpm.refinement.RefinementPackage#getRefinement_VariationPoints()
	 * @model
	 * @generated
	 */
	EList<VariationPoint> getVariationPoints();

} // Refinement
