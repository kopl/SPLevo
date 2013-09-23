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
import org.eclipse.gmt.modisco.java.ASTNode;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.variabilityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variation Point</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointImpl#getVariants <em>Variants</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointImpl#getEnclosingSoftwareEntity <em>Enclosing Software Entity</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointImpl#getGroup <em>Group</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VariationPointImpl extends EObjectImpl implements VariationPoint {
	/**
	 * The cached value of the '{@link #getVariants() <em>Variants</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariants()
	 * @generated
	 * @ordered
	 */
	protected EList<Variant> variants;

	/**
	 * The cached value of the '{@link #getEnclosingSoftwareEntity() <em>Enclosing Software Entity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnclosingSoftwareEntity()
	 * @generated
	 * @ordered
	 */
	protected ASTNode enclosingSoftwareEntity;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariationPointImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return variabilityPackage.Literals.VARIATION_POINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Variant> getVariants() {
		if (variants == null) {
			variants = new EObjectContainmentWithInverseEList<Variant>(
					Variant.class, this,
					variabilityPackage.VARIATION_POINT__VARIANTS,
					variabilityPackage.VARIANT__VARIATION_POINT);
		}
		return variants;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASTNode getEnclosingSoftwareEntity() {
		if (enclosingSoftwareEntity != null
				&& enclosingSoftwareEntity.eIsProxy()) {
			InternalEObject oldEnclosingSoftwareEntity = (InternalEObject) enclosingSoftwareEntity;
			enclosingSoftwareEntity = (ASTNode) eResolveProxy(oldEnclosingSoftwareEntity);
			if (enclosingSoftwareEntity != oldEnclosingSoftwareEntity) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(
							this,
							Notification.RESOLVE,
							variabilityPackage.VARIATION_POINT__ENCLOSING_SOFTWARE_ENTITY,
							oldEnclosingSoftwareEntity, enclosingSoftwareEntity));
			}
		}
		return enclosingSoftwareEntity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASTNode basicGetEnclosingSoftwareEntity() {
		return enclosingSoftwareEntity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnclosingSoftwareEntity(ASTNode newEnclosingSoftwareEntity) {
		ASTNode oldEnclosingSoftwareEntity = enclosingSoftwareEntity;
		enclosingSoftwareEntity = newEnclosingSoftwareEntity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
					this,
					Notification.SET,
					variabilityPackage.VARIATION_POINT__ENCLOSING_SOFTWARE_ENTITY,
					oldEnclosingSoftwareEntity, enclosingSoftwareEntity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariationPointGroup getGroup() {
		if (eContainerFeatureID() != variabilityPackage.VARIATION_POINT__GROUP)
			return null;
		return (VariationPointGroup) eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGroup(VariationPointGroup newGroup,
			NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newGroup,
				variabilityPackage.VARIATION_POINT__GROUP, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroup(VariationPointGroup newGroup) {
		if (newGroup != eInternalContainer()
				|| (eContainerFeatureID() != variabilityPackage.VARIATION_POINT__GROUP && newGroup != null)) {
			if (EcoreUtil.isAncestor(this, newGroup))
				throw new IllegalArgumentException(
						"Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGroup != null)
				msgs = ((InternalEObject) newGroup)
						.eInverseAdd(
								this,
								variabilityPackage.VARIATION_POINT_GROUP__VARIATION_POINTS,
								VariationPointGroup.class, msgs);
			msgs = basicSetGroup(newGroup, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					variabilityPackage.VARIATION_POINT__GROUP, newGroup,
					newGroup));
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
		case variabilityPackage.VARIATION_POINT__VARIANTS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getVariants())
					.basicAdd(otherEnd, msgs);
		case variabilityPackage.VARIATION_POINT__GROUP:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetGroup((VariationPointGroup) otherEnd, msgs);
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
		case variabilityPackage.VARIATION_POINT__VARIANTS:
			return ((InternalEList<?>) getVariants()).basicRemove(otherEnd,
					msgs);
		case variabilityPackage.VARIATION_POINT__GROUP:
			return basicSetGroup(null, msgs);
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
		case variabilityPackage.VARIATION_POINT__GROUP:
			return eInternalContainer().eInverseRemove(this,
					variabilityPackage.VARIATION_POINT_GROUP__VARIATION_POINTS,
					VariationPointGroup.class, msgs);
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
		case variabilityPackage.VARIATION_POINT__VARIANTS:
			return getVariants();
		case variabilityPackage.VARIATION_POINT__ENCLOSING_SOFTWARE_ENTITY:
			if (resolve)
				return getEnclosingSoftwareEntity();
			return basicGetEnclosingSoftwareEntity();
		case variabilityPackage.VARIATION_POINT__GROUP:
			return getGroup();
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
		case variabilityPackage.VARIATION_POINT__VARIANTS:
			getVariants().clear();
			getVariants().addAll((Collection<? extends Variant>) newValue);
			return;
		case variabilityPackage.VARIATION_POINT__ENCLOSING_SOFTWARE_ENTITY:
			setEnclosingSoftwareEntity((ASTNode) newValue);
			return;
		case variabilityPackage.VARIATION_POINT__GROUP:
			setGroup((VariationPointGroup) newValue);
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
		case variabilityPackage.VARIATION_POINT__VARIANTS:
			getVariants().clear();
			return;
		case variabilityPackage.VARIATION_POINT__ENCLOSING_SOFTWARE_ENTITY:
			setEnclosingSoftwareEntity((ASTNode) null);
			return;
		case variabilityPackage.VARIATION_POINT__GROUP:
			setGroup((VariationPointGroup) null);
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
		case variabilityPackage.VARIATION_POINT__VARIANTS:
			return variants != null && !variants.isEmpty();
		case variabilityPackage.VARIATION_POINT__ENCLOSING_SOFTWARE_ENTITY:
			return enclosingSoftwareEntity != null;
		case variabilityPackage.VARIATION_POINT__GROUP:
			return getGroup() != null;
		}
		return super.eIsSet(featureID);
	}

} //VariationPointImpl
