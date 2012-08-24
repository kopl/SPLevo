/**
 */
package org.splevo.vpm.variability;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmt.modisco.java.Model;

import org.splevo.vpm.realization.RealizationTechnique;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variation Point Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.vpm.variability.VariationPointModel#getRealizationTechniques <em>Realization Techniques</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.VariationPointModel#getLeadingModel <em>Leading Model</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.VariationPointModel#getIntegrationModel <em>Integration Model</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.VariationPointModel#getVariationPointGroups <em>Variation Point Groups</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.vpm.variability.variabilityPackage#getVariationPointModel()
 * @model
 * @generated
 */
public interface VariationPointModel extends EObject {
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
	 * @see org.splevo.vpm.variability.variabilityPackage#getVariationPointModel_RealizationTechniques()
	 * @model containment="true"
	 * @generated
	 */
	EList<RealizationTechnique> getRealizationTechniques();

	/**
	 * Returns the value of the '<em><b>Leading Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Leading Model</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Leading Model</em>' reference.
	 * @see #setLeadingModel(Model)
	 * @see org.splevo.vpm.variability.variabilityPackage#getVariationPointModel_LeadingModel()
	 * @model required="true"
	 * @generated
	 */
	Model getLeadingModel();

	/**
	 * Sets the value of the '{@link org.splevo.vpm.variability.VariationPointModel#getLeadingModel <em>Leading Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Leading Model</em>' reference.
	 * @see #getLeadingModel()
	 * @generated
	 */
	void setLeadingModel(Model value);

	/**
	 * Returns the value of the '<em><b>Integration Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Integration Model</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Integration Model</em>' reference.
	 * @see #setIntegrationModel(Model)
	 * @see org.splevo.vpm.variability.variabilityPackage#getVariationPointModel_IntegrationModel()
	 * @model required="true"
	 * @generated
	 */
	Model getIntegrationModel();

	/**
	 * Sets the value of the '{@link org.splevo.vpm.variability.VariationPointModel#getIntegrationModel <em>Integration Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Integration Model</em>' reference.
	 * @see #getIntegrationModel()
	 * @generated
	 */
	void setIntegrationModel(Model value);

	/**
	 * Returns the value of the '<em><b>Variation Point Groups</b></em>' containment reference list.
	 * The list contents are of type {@link org.splevo.vpm.variability.VariationPointGroup}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variation Point Groups</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variation Point Groups</em>' containment reference list.
	 * @see org.splevo.vpm.variability.variabilityPackage#getVariationPointModel_VariationPointGroups()
	 * @model containment="true"
	 * @generated
	 */
	EList<VariationPointGroup> getVariationPointGroups();

} // VariationPointModel
