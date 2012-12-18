/**
 */
package org.splevo.vpm.refinement.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.splevo.vpm.refinement.AnalyzerConfiguration;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementModel;
import org.splevo.vpm.refinement.RefinementPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.vpm.refinement.impl.RefinementModelImpl#getAnalyzerConfigurations <em>Analyzer Configurations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RefinementModelImpl extends EObjectImpl implements RefinementModel {
	/**
	 * The cached value of the '{@link #getAnalyzerConfigurations() <em>Analyzer Configurations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnalyzerConfigurations()
	 * @generated
	 * @ordered
	 */
	protected EList<AnalyzerConfiguration> analyzerConfigurations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RefinementModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RefinementPackage.Literals.REFINEMENT_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AnalyzerConfiguration> getAnalyzerConfigurations() {
		if (analyzerConfigurations == null) {
			analyzerConfigurations = new EObjectContainmentEList<AnalyzerConfiguration>(AnalyzerConfiguration.class, this, RefinementPackage.REFINEMENT_MODEL__ANALYZER_CONFIGURATIONS);
		}
		return analyzerConfigurations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RefinementPackage.REFINEMENT_MODEL__ANALYZER_CONFIGURATIONS:
				return ((InternalEList<?>)getAnalyzerConfigurations()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RefinementPackage.REFINEMENT_MODEL__ANALYZER_CONFIGURATIONS:
				return getAnalyzerConfigurations();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RefinementPackage.REFINEMENT_MODEL__ANALYZER_CONFIGURATIONS:
				getAnalyzerConfigurations().clear();
				getAnalyzerConfigurations().addAll((Collection<? extends AnalyzerConfiguration>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case RefinementPackage.REFINEMENT_MODEL__ANALYZER_CONFIGURATIONS:
				getAnalyzerConfigurations().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case RefinementPackage.REFINEMENT_MODEL__ANALYZER_CONFIGURATIONS:
				return analyzerConfigurations != null && !analyzerConfigurations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //RefinementModelImpl
