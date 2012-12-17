/**
 */
package org.splevo.vpm.refinement;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Analyzer Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A configuration of an executed analyzer that led to the refinements.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.vpm.refinement.AnalyzerConfiguration#getAnalyzerID <em>Analyzer ID</em>}</li>
 *   <li>{@link org.splevo.vpm.refinement.AnalyzerConfiguration#getSettings <em>Settings</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.vpm.refinement.RefinementPackage#getAnalyzerConfiguration()
 * @model
 * @generated
 */
public interface AnalyzerConfiguration extends EObject {
	/**
	 * Returns the value of the '<em><b>Analyzer ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The identifier for the analyzer used.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Analyzer ID</em>' attribute.
	 * @see #setAnalyzerID(String)
	 * @see org.splevo.vpm.refinement.RefinementPackage#getAnalyzerConfiguration_AnalyzerID()
	 * @model
	 * @generated
	 */
	String getAnalyzerID();

	/**
	 * Sets the value of the '{@link org.splevo.vpm.refinement.AnalyzerConfiguration#getAnalyzerID <em>Analyzer ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Analyzer ID</em>' attribute.
	 * @see #getAnalyzerID()
	 * @generated
	 */
	void setAnalyzerID(String value);

	/**
	 * Returns the value of the '<em><b>Settings</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The configured parameters for the analyzer.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Settings</em>' attribute.
	 * @see #setSettings(String)
	 * @see org.splevo.vpm.refinement.RefinementPackage#getAnalyzerConfiguration_Settings()
	 * @model
	 * @generated
	 */
	String getSettings();

	/**
	 * Sets the value of the '{@link org.splevo.vpm.refinement.AnalyzerConfiguration#getSettings <em>Settings</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Settings</em>' attribute.
	 * @see #getSettings()
	 * @generated
	 */
	void setSettings(String value);

} // AnalyzerConfiguration
