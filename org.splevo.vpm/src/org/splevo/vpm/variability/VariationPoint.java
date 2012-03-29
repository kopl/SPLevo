/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.vpm.variability;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.featuremodel.Feature;

import org.eclipse.gmt.modisco.omg.kdm.core.KDMEntity;

import org.splevo.vpm.realization.RealizationTechnique;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variation Point</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.vpm.variability.VariationPoint#getVariants <em>Variants</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.VariationPoint#getRealizationTechnique <em>Realization Technique</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.VariationPoint#getSoftwareEntities <em>Software Entities</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.VariationPoint#getFeature <em>Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.vpm.variability.variabilityPackage#getVariationPoint()
 * @model
 * @generated
 */
public interface VariationPoint extends EObject {
	/**
	 * Returns the value of the '<em><b>Variants</b></em>' containment reference list.
	 * The list contents are of type {@link org.splevo.vpm.variability.Variant}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variants</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variants</em>' containment reference list.
	 * @see org.splevo.vpm.variability.variabilityPackage#getVariationPoint_Variants()
	 * @model containment="true"
	 * @generated
	 */
	EList<Variant> getVariants();

	/**
	 * Returns the value of the '<em><b>Realization Technique</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realization Technique</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Realization Technique</em>' reference.
	 * @see #setRealizationTechnique(RealizationTechnique)
	 * @see org.splevo.vpm.variability.variabilityPackage#getVariationPoint_RealizationTechnique()
	 * @model
	 * @generated
	 */
	RealizationTechnique getRealizationTechnique();

	/**
	 * Sets the value of the '{@link org.splevo.vpm.variability.VariationPoint#getRealizationTechnique <em>Realization Technique</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Realization Technique</em>' reference.
	 * @see #getRealizationTechnique()
	 * @generated
	 */
	void setRealizationTechnique(RealizationTechnique value);

	/**
	 * Returns the value of the '<em><b>Software Entities</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.omg.kdm.core.KDMEntity}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Software Entities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Software Entities</em>' reference list.
	 * @see org.splevo.vpm.variability.variabilityPackage#getVariationPoint_SoftwareEntities()
	 * @model
	 * @generated
	 */
	EList<KDMEntity> getSoftwareEntities();

	/**
	 * Returns the value of the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Feature</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature</em>' reference.
	 * @see #setFeature(Feature)
	 * @see org.splevo.vpm.variability.variabilityPackage#getVariationPoint_Feature()
	 * @model
	 * @generated
	 */
	Feature getFeature();

	/**
	 * Sets the value of the '{@link org.splevo.vpm.variability.VariationPoint#getFeature <em>Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature</em>' reference.
	 * @see #getFeature()
	 * @generated
	 */
	void setFeature(Feature value);

} // VariationPoint
