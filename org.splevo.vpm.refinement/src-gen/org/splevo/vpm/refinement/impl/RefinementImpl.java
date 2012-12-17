/**
 */
package org.splevo.vpm.refinement.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.splevo.vpm.refinement.AnalyzerConfiguration;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementPackage;
import org.splevo.vpm.refinement.RefinementType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Refinement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.vpm.refinement.impl.RefinementImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.splevo.vpm.refinement.impl.RefinementImpl#getVariationPoints <em>Variation Points</em>}</li>
 *   <li>{@link org.splevo.vpm.refinement.impl.RefinementImpl#getAnalyzer <em>Analyzer</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RefinementImpl extends EObjectImpl implements Refinement {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final RefinementType TYPE_EDEFAULT = RefinementType.MERGE;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected RefinementType type = TYPE_EDEFAULT;

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
	 * The cached value of the '{@link #getAnalyzer() <em>Analyzer</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnalyzer()
	 * @generated
	 * @ordered
	 */
	protected AnalyzerConfiguration analyzer;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RefinementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RefinementPackage.Literals.REFINEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefinementType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(RefinementType newType) {
		RefinementType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefinementPackage.REFINEMENT__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VariationPoint> getVariationPoints() {
		if (variationPoints == null) {
			variationPoints = new EObjectResolvingEList<VariationPoint>(VariationPoint.class, this, RefinementPackage.REFINEMENT__VARIATION_POINTS);
		}
		return variationPoints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnalyzerConfiguration getAnalyzer() {
		if (analyzer != null && analyzer.eIsProxy()) {
			InternalEObject oldAnalyzer = (InternalEObject)analyzer;
			analyzer = (AnalyzerConfiguration)eResolveProxy(oldAnalyzer);
			if (analyzer != oldAnalyzer) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RefinementPackage.REFINEMENT__ANALYZER, oldAnalyzer, analyzer));
			}
		}
		return analyzer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnalyzerConfiguration basicGetAnalyzer() {
		return analyzer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAnalyzer(AnalyzerConfiguration newAnalyzer) {
		AnalyzerConfiguration oldAnalyzer = analyzer;
		analyzer = newAnalyzer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefinementPackage.REFINEMENT__ANALYZER, oldAnalyzer, analyzer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RefinementPackage.REFINEMENT__TYPE:
				return getType();
			case RefinementPackage.REFINEMENT__VARIATION_POINTS:
				return getVariationPoints();
			case RefinementPackage.REFINEMENT__ANALYZER:
				if (resolve) return getAnalyzer();
				return basicGetAnalyzer();
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
			case RefinementPackage.REFINEMENT__TYPE:
				setType((RefinementType)newValue);
				return;
			case RefinementPackage.REFINEMENT__VARIATION_POINTS:
				getVariationPoints().clear();
				getVariationPoints().addAll((Collection<? extends VariationPoint>)newValue);
				return;
			case RefinementPackage.REFINEMENT__ANALYZER:
				setAnalyzer((AnalyzerConfiguration)newValue);
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
			case RefinementPackage.REFINEMENT__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case RefinementPackage.REFINEMENT__VARIATION_POINTS:
				getVariationPoints().clear();
				return;
			case RefinementPackage.REFINEMENT__ANALYZER:
				setAnalyzer((AnalyzerConfiguration)null);
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
			case RefinementPackage.REFINEMENT__TYPE:
				return type != TYPE_EDEFAULT;
			case RefinementPackage.REFINEMENT__VARIATION_POINTS:
				return variationPoints != null && !variationPoints.isEmpty();
			case RefinementPackage.REFINEMENT__ANALYZER:
				return analyzer != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //RefinementImpl
