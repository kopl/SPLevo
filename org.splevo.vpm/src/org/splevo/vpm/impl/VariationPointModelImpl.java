/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.vpm.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.splevo.vpm.VariationPointModel;

import org.splevo.vpm.realization.RealizationTechnique;

import org.splevo.vpm.variability.VariationPoint;

import org.splevo.vpm.vpmPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variation Point Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.vpm.impl.VariationPointModelImpl#getVariationPoints <em>Variation Points</em>}</li>
 *   <li>{@link org.splevo.vpm.impl.VariationPointModelImpl#getRealizationTechniques <em>Realization Techniques</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VariationPointModelImpl extends EObjectImpl implements VariationPointModel {
	/**
	 * The cached value of the '{@link #getVariationPoints() <em>Variation Points</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariationPoints()
	 * @generated
	 * @ordered
	 */
	protected EList<VariationPoint> variationPoints;

	/**
	 * The cached value of the '{@link #getRealizationTechniques() <em>Realization Techniques</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRealizationTechniques()
	 * @generated
	 * @ordered
	 */
	protected EList<RealizationTechnique> realizationTechniques;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariationPointModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return vpmPackage.Literals.VARIATION_POINT_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VariationPoint> getVariationPoints() {
		if (variationPoints == null) {
			variationPoints = new EObjectContainmentEList<VariationPoint>(VariationPoint.class, this, vpmPackage.VARIATION_POINT_MODEL__VARIATION_POINTS);
		}
		return variationPoints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RealizationTechnique> getRealizationTechniques() {
		if (realizationTechniques == null) {
			realizationTechniques = new EObjectContainmentEList<RealizationTechnique>(RealizationTechnique.class, this, vpmPackage.VARIATION_POINT_MODEL__REALIZATION_TECHNIQUES);
		}
		return realizationTechniques;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case vpmPackage.VARIATION_POINT_MODEL__VARIATION_POINTS:
				return ((InternalEList<?>)getVariationPoints()).basicRemove(otherEnd, msgs);
			case vpmPackage.VARIATION_POINT_MODEL__REALIZATION_TECHNIQUES:
				return ((InternalEList<?>)getRealizationTechniques()).basicRemove(otherEnd, msgs);
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
			case vpmPackage.VARIATION_POINT_MODEL__VARIATION_POINTS:
				return getVariationPoints();
			case vpmPackage.VARIATION_POINT_MODEL__REALIZATION_TECHNIQUES:
				return getRealizationTechniques();
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
			case vpmPackage.VARIATION_POINT_MODEL__VARIATION_POINTS:
				getVariationPoints().clear();
				getVariationPoints().addAll((Collection<? extends VariationPoint>)newValue);
				return;
			case vpmPackage.VARIATION_POINT_MODEL__REALIZATION_TECHNIQUES:
				getRealizationTechniques().clear();
				getRealizationTechniques().addAll((Collection<? extends RealizationTechnique>)newValue);
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
			case vpmPackage.VARIATION_POINT_MODEL__VARIATION_POINTS:
				getVariationPoints().clear();
				return;
			case vpmPackage.VARIATION_POINT_MODEL__REALIZATION_TECHNIQUES:
				getRealizationTechniques().clear();
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
			case vpmPackage.VARIATION_POINT_MODEL__VARIATION_POINTS:
				return variationPoints != null && !variationPoints.isEmpty();
			case vpmPackage.VARIATION_POINT_MODEL__REALIZATION_TECHNIQUES:
				return realizationTechniques != null && !realizationTechniques.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //VariationPointModelImpl
