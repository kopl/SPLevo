/**
 */
package org.splevo.vpm.variability.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.featuremodel.Feature;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variation Point Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointGroupImpl#getVariationPoints <em>Variation Points</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointGroupImpl#getGroupId <em>Group Id</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointGroupImpl#getModel <em>Model</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointGroupImpl#getFeature <em>Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VariationPointGroupImpl extends EObjectImpl implements
		VariationPointGroup {
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
	 * The default value of the '{@link #getGroupId() <em>Group Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupId()
	 * @generated
	 * @ordered
	 */
	protected static final String GROUP_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGroupId() <em>Group Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupId()
	 * @generated
	 * @ordered
	 */
	protected String groupId = GROUP_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFeature() <em>Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeature()
	 * @generated
	 * @ordered
	 */
	protected Feature feature;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariationPointGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return variabilityPackage.Literals.VARIATION_POINT_GROUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VariationPoint> getVariationPoints() {
		if (variationPoints == null) {
			variationPoints = new EObjectContainmentWithInverseEList<VariationPoint>(
					VariationPoint.class, this,
					variabilityPackage.VARIATION_POINT_GROUP__VARIATION_POINTS,
					variabilityPackage.VARIATION_POINT__GROUP);
		}
		return variationPoints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroupId(String newGroupId) {
		String oldGroupId = groupId;
		groupId = newGroupId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					variabilityPackage.VARIATION_POINT_GROUP__GROUP_ID,
					oldGroupId, groupId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariationPointModel getModel() {
		if (eContainerFeatureID() != variabilityPackage.VARIATION_POINT_GROUP__MODEL)
			return null;
		return (VariationPointModel) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModel(VariationPointModel newModel,
			NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newModel,
				variabilityPackage.VARIATION_POINT_GROUP__MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModel(VariationPointModel newModel) {
		if (newModel != eInternalContainer()
				|| (eContainerFeatureID() != variabilityPackage.VARIATION_POINT_GROUP__MODEL && newModel != null)) {
			if (EcoreUtil.isAncestor(this, newModel))
				throw new IllegalArgumentException(
						"Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newModel != null)
				msgs = ((InternalEObject) newModel)
						.eInverseAdd(
								this,
								variabilityPackage.VARIATION_POINT_MODEL__VARIATION_POINT_GROUPS,
								VariationPointModel.class, msgs);
			msgs = basicSetModel(newModel, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					variabilityPackage.VARIATION_POINT_GROUP__MODEL, newModel,
					newModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Feature getFeature() {
		if (feature != null && feature.eIsProxy()) {
			InternalEObject oldFeature = (InternalEObject) feature;
			feature = (Feature) eResolveProxy(oldFeature);
			if (feature != oldFeature) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							variabilityPackage.VARIATION_POINT_GROUP__FEATURE,
							oldFeature, feature));
			}
		}
		return feature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Feature basicGetFeature() {
		return feature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFeature(Feature newFeature) {
		Feature oldFeature = feature;
		feature = newFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					variabilityPackage.VARIATION_POINT_GROUP__FEATURE,
					oldFeature, feature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case variabilityPackage.VARIATION_POINT_GROUP__VARIATION_POINTS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getVariationPoints())
					.basicAdd(otherEnd, msgs);
		case variabilityPackage.VARIATION_POINT_GROUP__MODEL:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetModel((VariationPointModel) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case variabilityPackage.VARIATION_POINT_GROUP__VARIATION_POINTS:
			return ((InternalEList<?>) getVariationPoints()).basicRemove(
					otherEnd, msgs);
		case variabilityPackage.VARIATION_POINT_GROUP__MODEL:
			return basicSetModel(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(
			NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case variabilityPackage.VARIATION_POINT_GROUP__MODEL:
			return eInternalContainer()
					.eInverseRemove(
							this,
							variabilityPackage.VARIATION_POINT_MODEL__VARIATION_POINT_GROUPS,
							VariationPointModel.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case variabilityPackage.VARIATION_POINT_GROUP__VARIATION_POINTS:
			return getVariationPoints();
		case variabilityPackage.VARIATION_POINT_GROUP__GROUP_ID:
			return getGroupId();
		case variabilityPackage.VARIATION_POINT_GROUP__MODEL:
			return getModel();
		case variabilityPackage.VARIATION_POINT_GROUP__FEATURE:
			if (resolve)
				return getFeature();
			return basicGetFeature();
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
		case variabilityPackage.VARIATION_POINT_GROUP__VARIATION_POINTS:
			getVariationPoints().clear();
			getVariationPoints().addAll(
					(Collection<? extends VariationPoint>) newValue);
			return;
		case variabilityPackage.VARIATION_POINT_GROUP__GROUP_ID:
			setGroupId((String) newValue);
			return;
		case variabilityPackage.VARIATION_POINT_GROUP__MODEL:
			setModel((VariationPointModel) newValue);
			return;
		case variabilityPackage.VARIATION_POINT_GROUP__FEATURE:
			setFeature((Feature) newValue);
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
		case variabilityPackage.VARIATION_POINT_GROUP__VARIATION_POINTS:
			getVariationPoints().clear();
			return;
		case variabilityPackage.VARIATION_POINT_GROUP__GROUP_ID:
			setGroupId(GROUP_ID_EDEFAULT);
			return;
		case variabilityPackage.VARIATION_POINT_GROUP__MODEL:
			setModel((VariationPointModel) null);
			return;
		case variabilityPackage.VARIATION_POINT_GROUP__FEATURE:
			setFeature((Feature) null);
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
		case variabilityPackage.VARIATION_POINT_GROUP__VARIATION_POINTS:
			return variationPoints != null && !variationPoints.isEmpty();
		case variabilityPackage.VARIATION_POINT_GROUP__GROUP_ID:
			return GROUP_ID_EDEFAULT == null ? groupId != null
					: !GROUP_ID_EDEFAULT.equals(groupId);
		case variabilityPackage.VARIATION_POINT_GROUP__MODEL:
			return getModel() != null;
		case variabilityPackage.VARIATION_POINT_GROUP__FEATURE:
			return feature != null;
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
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (groupId: ");
		result.append(groupId);
		result.append(')');
		return result.toString();
	}

} //VariationPointGroupImpl
