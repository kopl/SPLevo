/**
 */
package org.splevo.vpm.variability;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.featuremodel.Feature;

import org.eclipse.gmt.modisco.java.ASTNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.vpm.variability.Variant#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.Variant#getSoftwareEntities <em>Software Entities</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.Variant#getLeading <em>Leading</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.vpm.variability.variabilityPackage#getVariant()
 * @model
 * @generated
 */
public interface Variant extends EObject {
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
	 * @see org.splevo.vpm.variability.variabilityPackage#getVariant_Feature()
	 * @model
	 * @generated
	 */
	Feature getFeature();

	/**
	 * Sets the value of the '{@link org.splevo.vpm.variability.Variant#getFeature <em>Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature</em>' reference.
	 * @see #getFeature()
	 * @generated
	 */
	void setFeature(Feature value);

	/**
	 * Returns the value of the '<em><b>Software Entities</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.ASTNode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Software Entities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Software Entities</em>' reference list.
	 * @see org.splevo.vpm.variability.variabilityPackage#getVariant_SoftwareEntities()
	 * @model
	 * @generated
	 */
	EList<ASTNode> getSoftwareEntities();

	/**
	 * Returns the value of the '<em><b>Leading</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Flag identifying if the variant is one of the leading variantes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Leading</em>' attribute.
	 * @see #setLeading(Boolean)
	 * @see org.splevo.vpm.variability.variabilityPackage#getVariant_Leading()
	 * @model required="true"
	 * @generated
	 */
	Boolean getLeading();

	/**
	 * Sets the value of the '{@link org.splevo.vpm.variability.Variant#getLeading <em>Leading</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Leading</em>' attribute.
	 * @see #getLeading()
	 * @generated
	 */
	void setLeading(Boolean value);

} // Variant
