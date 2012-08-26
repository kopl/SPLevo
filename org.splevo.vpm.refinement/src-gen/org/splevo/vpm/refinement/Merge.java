/**
 */
package org.splevo.vpm.refinement;

import org.eclipse.emf.common.util.EList;

import org.splevo.vpm.variability.VariationPoint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Merge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.vpm.refinement.Merge#getVariationPoints <em>Variation Points</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.vpm.refinement.RefinementPackage#getMerge()
 * @model
 * @generated
 */
public interface Merge extends Refinement {
	/**
	 * Returns the value of the '<em><b>Variation Points</b></em>' reference list.
	 * The list contents are of type {@link org.splevo.vpm.variability.VariationPoint}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The variation points to be merged.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Variation Points</em>' reference list.
	 * @see org.splevo.vpm.refinement.RefinementPackage#getMerge_VariationPoints()
	 * @model
	 * @generated
	 */
	EList<VariationPoint> getVariationPoints();

} // Merge
