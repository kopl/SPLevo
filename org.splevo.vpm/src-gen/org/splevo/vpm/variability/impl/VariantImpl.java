/**
 */
package org.splevo.vpm.variability.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.featuremodel.Feature;

import org.eclipse.gmt.modisco.java.ASTNode;

import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.variabilityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.vpm.variability.impl.VariantImpl#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariantImpl#getSoftwareEntities <em>Software Entities</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariantImpl#getLeading <em>Leading</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariantImpl#getVariantId <em>Variant Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VariantImpl extends EObjectImpl implements Variant {
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
	 * The cached value of the '{@link #getSoftwareEntities() <em>Software Entities</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSoftwareEntities()
	 * @generated
	 * @ordered
	 */
	protected EList<ASTNode> softwareEntities;

	/**
	 * The default value of the '{@link #getLeading() <em>Leading</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeading()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean LEADING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLeading() <em>Leading</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeading()
	 * @generated
	 * @ordered
	 */
	protected Boolean leading = LEADING_EDEFAULT;

	/**
	 * The default value of the '{@link #getVariantId() <em>Variant Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariantId()
	 * @generated
	 * @ordered
	 */
	protected static final String VARIANT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVariantId() <em>Variant Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariantId()
	 * @generated
	 * @ordered
	 */
	protected String variantId = VARIANT_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariantImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return variabilityPackage.Literals.VARIANT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Feature getFeature() {
		if (feature != null && feature.eIsProxy()) {
			InternalEObject oldFeature = (InternalEObject)feature;
			feature = (Feature)eResolveProxy(oldFeature);
			if (feature != oldFeature) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, variabilityPackage.VARIANT__FEATURE, oldFeature, feature));
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
			eNotify(new ENotificationImpl(this, Notification.SET, variabilityPackage.VARIANT__FEATURE, oldFeature, feature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ASTNode> getSoftwareEntities() {
		if (softwareEntities == null) {
			softwareEntities = new EObjectResolvingEList<ASTNode>(ASTNode.class, this, variabilityPackage.VARIANT__SOFTWARE_ENTITIES);
		}
		return softwareEntities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getLeading() {
		return leading;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeading(Boolean newLeading) {
		Boolean oldLeading = leading;
		leading = newLeading;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, variabilityPackage.VARIANT__LEADING, oldLeading, leading));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVariantId() {
		return variantId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariantId(String newVariantId) {
		String oldVariantId = variantId;
		variantId = newVariantId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, variabilityPackage.VARIANT__VARIANT_ID, oldVariantId, variantId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case variabilityPackage.VARIANT__FEATURE:
				if (resolve) return getFeature();
				return basicGetFeature();
			case variabilityPackage.VARIANT__SOFTWARE_ENTITIES:
				return getSoftwareEntities();
			case variabilityPackage.VARIANT__LEADING:
				return getLeading();
			case variabilityPackage.VARIANT__VARIANT_ID:
				return getVariantId();
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
			case variabilityPackage.VARIANT__FEATURE:
				setFeature((Feature)newValue);
				return;
			case variabilityPackage.VARIANT__SOFTWARE_ENTITIES:
				getSoftwareEntities().clear();
				getSoftwareEntities().addAll((Collection<? extends ASTNode>)newValue);
				return;
			case variabilityPackage.VARIANT__LEADING:
				setLeading((Boolean)newValue);
				return;
			case variabilityPackage.VARIANT__VARIANT_ID:
				setVariantId((String)newValue);
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
			case variabilityPackage.VARIANT__FEATURE:
				setFeature((Feature)null);
				return;
			case variabilityPackage.VARIANT__SOFTWARE_ENTITIES:
				getSoftwareEntities().clear();
				return;
			case variabilityPackage.VARIANT__LEADING:
				setLeading(LEADING_EDEFAULT);
				return;
			case variabilityPackage.VARIANT__VARIANT_ID:
				setVariantId(VARIANT_ID_EDEFAULT);
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
			case variabilityPackage.VARIANT__FEATURE:
				return feature != null;
			case variabilityPackage.VARIANT__SOFTWARE_ENTITIES:
				return softwareEntities != null && !softwareEntities.isEmpty();
			case variabilityPackage.VARIANT__LEADING:
				return LEADING_EDEFAULT == null ? leading != null : !LEADING_EDEFAULT.equals(leading);
			case variabilityPackage.VARIANT__VARIANT_ID:
				return VARIANT_ID_EDEFAULT == null ? variantId != null : !VARIANT_ID_EDEFAULT.equals(variantId);
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
		result.append(" (leading: ");
		result.append(leading);
		result.append(", variantId: ");
		result.append(variantId);
		result.append(')');
		return result.toString();
	}

} //VariantImpl