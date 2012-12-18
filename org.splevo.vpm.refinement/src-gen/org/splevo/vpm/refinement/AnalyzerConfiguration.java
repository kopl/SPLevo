/**
 */
package org.splevo.vpm.refinement;

import java.util.Map;
import org.eclipse.emf.common.util.EList;
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
 *   <li>{@link org.splevo.vpm.refinement.AnalyzerConfiguration#getRefinements <em>Refinements</em>}</li>
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
	 * @see #setSettings(Map)
	 * @see org.splevo.vpm.refinement.RefinementPackage#getAnalyzerConfiguration_Settings()
	 * @model transient="true"
	 * @generated
	 */
	Map<String, String> getSettings();

	/**
	 * Sets the value of the '{@link org.splevo.vpm.refinement.AnalyzerConfiguration#getSettings <em>Settings</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Settings</em>' attribute.
	 * @see #getSettings()
	 * @generated
	 */
	void setSettings(Map<String, String> value);

	/**
	 * Returns the value of the '<em><b>Refinements</b></em>' containment reference list.
	 * The list contents are of type {@link org.splevo.vpm.refinement.Refinement}.
	 * It is bidirectional and its opposite is '{@link org.splevo.vpm.refinement.Refinement#getAnalyzer <em>Analyzer</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Refinements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Refinements</em>' containment reference list.
	 * @see org.splevo.vpm.refinement.RefinementPackage#getAnalyzerConfiguration_Refinements()
	 * @see org.splevo.vpm.refinement.Refinement#getAnalyzer
	 * @model opposite="analyzer" containment="true"
	 * @generated
	 */
	EList<Refinement> getRefinements();

} // AnalyzerConfiguration
