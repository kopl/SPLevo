/**
 */
package org.splevo.vpm.refinement;

import org.eclipse.emf.common.util.EList;

import org.splevo.vpm.variability.VariationPoint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Grouping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.vpm.refinement.Grouping#getVariationPoints <em>Variation Points</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.vpm.refinement.RefinementPackage#getGrouping()
 * @model
 * @generated
 */
public interface Grouping extends Refinement {
	/**
	 * Returns the value of the '<em><b>Variation Points</b></em>' reference list.
	 * The list contents are of type {@link org.splevo.vpm.variability.VariationPoint}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The variation points to be grouped.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Variation Points</em>' reference list.
	 * @see org.splevo.vpm.refinement.RefinementPackage#getGrouping_VariationPoints()
	 * @model
	 * @generated
	 */
	EList<VariationPoint> getVariationPoints();

} // Grouping
