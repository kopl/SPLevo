/**
 */
package org.splevo.vpm.refinement.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.splevo.vpm.refinement.Grouping;
import org.splevo.vpm.refinement.RefinementPackage;

import org.splevo.vpm.variability.VariationPoint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Grouping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.vpm.refinement.impl.GroupingImpl#getVariationPoints <em>Variation Points</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GroupingImpl extends RefinementImpl implements Grouping {
	/**
	 * The cached value of the '{@link #getVariationPoints() <em>Variation Points</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariationPoints()
	 * @generated
	 * @ordered
	 */
	protected EList<VariationPoint> variationPoints;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GroupingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RefinementPackage.Literals.GROUPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VariationPoint> getVariationPoints() {
		if (variationPoints == null) {
			variationPoints = new EObjectResolvingEList<VariationPoint>(VariationPoint.class, this, RefinementPackage.GROUPING__VARIATION_POINTS);
		}
		return variationPoints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RefinementPackage.GROUPING__VARIATION_POINTS:
				return getVariationPoints();
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
			case RefinementPackage.GROUPING__VARIATION_POINTS:
				getVariationPoints().clear();
				getVariationPoints().addAll((Collection<? extends VariationPoint>)newValue);
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
			case RefinementPackage.GROUPING__VARIATION_POINTS:
				getVariationPoints().clear();
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
			case RefinementPackage.GROUPING__VARIATION_POINTS:
				return variationPoints != null && !variationPoints.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //GroupingImpl
