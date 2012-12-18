/**
 */
package org.splevo.vpm.refinement;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A model containing all refinements identified for the variation point model.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.vpm.refinement.RefinementModel#getAnalyzerConfigurations <em>Analyzer Configurations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.vpm.refinement.RefinementPackage#getRefinementModel()
 * @model
 * @generated
 */
public interface RefinementModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Analyzer Configurations</b></em>' containment reference list.
	 * The list contents are of type {@link org.splevo.vpm.refinement.AnalyzerConfiguration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Analyzer Configurations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Analyzer Configurations</em>' containment reference list.
	 * @see org.splevo.vpm.refinement.RefinementPackage#getRefinementModel_AnalyzerConfigurations()
	 * @model containment="true"
	 * @generated
	 */
	EList<AnalyzerConfiguration> getAnalyzerConfigurations();

} // RefinementModel
