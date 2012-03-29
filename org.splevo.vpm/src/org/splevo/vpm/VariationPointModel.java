/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.vpm;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.splevo.vpm.realization.RealizationTechnique;

import org.splevo.vpm.variability.VariationPoint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variation Point Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.vpm.VariationPointModel#getVariationPoints <em>Variation Points</em>}</li>
 *   <li>{@link org.splevo.vpm.VariationPointModel#getRealizationTechniques <em>Realization Techniques</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.vpm.vpmPackage#getVariationPointModel()
 * @model
 * @generated
 */
public interface VariationPointModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Variation Points</b></em>' containment reference list.
	 * The list contents are of type {@link org.splevo.vpm.variability.VariationPoint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variation Points</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variation Points</em>' containment reference list.
	 * @see org.splevo.vpm.vpmPackage#getVariationPointModel_VariationPoints()
	 * @model containment="true"
	 * @generated
	 */
	EList<VariationPoint> getVariationPoints();

	/**
	 * Returns the value of the '<em><b>Realization Techniques</b></em>' containment reference list.
	 * The list contents are of type {@link org.splevo.vpm.realization.RealizationTechnique}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realization Techniques</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Realization Techniques</em>' containment reference list.
	 * @see org.splevo.vpm.vpmPackage#getVariationPointModel_RealizationTechniques()
	 * @model containment="true"
	 * @generated
	 */
	EList<RealizationTechnique> getRealizationTechniques();

} // VariationPointModel
