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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.featuremodel.Feature;

import org.eclipse.gmt.modisco.java.ASTNode;

import org.splevo.vpm.realization.RealizationTechnique;

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
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointImpl#getRealizationTechnique <em>Realization Technique</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointImpl#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointImpl#getSoftwareEntity <em>Software Entity</em>}</li>
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
	 * The cached value of the '{@link #getRealizationTechnique() <em>Realization Technique</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRealizationTechnique()
	 * @generated
	 * @ordered
	 */
	protected RealizationTechnique realizationTechnique;

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
	 * The cached value of the '{@link #getSoftwareEntity() <em>Software Entity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSoftwareEntity()
	 * @generated
	 * @ordered
	 */
	protected ASTNode softwareEntity;

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
			variants = new EObjectContainmentEList<Variant>(Variant.class, this, variabilityPackage.VARIATION_POINT__VARIANTS);
		}
		return variants;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RealizationTechnique getRealizationTechnique() {
		if (realizationTechnique != null && realizationTechnique.eIsProxy()) {
			InternalEObject oldRealizationTechnique = (InternalEObject)realizationTechnique;
			realizationTechnique = (RealizationTechnique)eResolveProxy(oldRealizationTechnique);
			if (realizationTechnique != oldRealizationTechnique) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, variabilityPackage.VARIATION_POINT__REALIZATION_TECHNIQUE, oldRealizationTechnique, realizationTechnique));
			}
		}
		return realizationTechnique;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RealizationTechnique basicGetRealizationTechnique() {
		return realizationTechnique;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRealizationTechnique(RealizationTechnique newRealizationTechnique) {
		RealizationTechnique oldRealizationTechnique = realizationTechnique;
		realizationTechnique = newRealizationTechnique;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, variabilityPackage.VARIATION_POINT__REALIZATION_TECHNIQUE, oldRealizationTechnique, realizationTechnique));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, variabilityPackage.VARIATION_POINT__FEATURE, oldFeature, feature));
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
			eNotify(new ENotificationImpl(this, Notification.SET, variabilityPackage.VARIATION_POINT__FEATURE, oldFeature, feature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASTNode getSoftwareEntity() {
		if (softwareEntity != null && softwareEntity.eIsProxy()) {
			InternalEObject oldSoftwareEntity = (InternalEObject)softwareEntity;
			softwareEntity = (ASTNode)eResolveProxy(oldSoftwareEntity);
			if (softwareEntity != oldSoftwareEntity) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, variabilityPackage.VARIATION_POINT__SOFTWARE_ENTITY, oldSoftwareEntity, softwareEntity));
			}
		}
		return softwareEntity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASTNode basicGetSoftwareEntity() {
		return softwareEntity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSoftwareEntity(ASTNode newSoftwareEntity) {
		ASTNode oldSoftwareEntity = softwareEntity;
		softwareEntity = newSoftwareEntity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, variabilityPackage.VARIATION_POINT__SOFTWARE_ENTITY, oldSoftwareEntity, softwareEntity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariationPointGroup getGroup() {
		if (eContainerFeatureID() != variabilityPackage.VARIATION_POINT__GROUP) return null;
		return (VariationPointGroup)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGroup(VariationPointGroup newGroup, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGroup, variabilityPackage.VARIATION_POINT__GROUP, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroup(VariationPointGroup newGroup) {
		if (newGroup != eInternalContainer() || (eContainerFeatureID() != variabilityPackage.VARIATION_POINT__GROUP && newGroup != null)) {
			if (EcoreUtil.isAncestor(this, newGroup))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGroup != null)
				msgs = ((InternalEObject)newGroup).eInverseAdd(this, variabilityPackage.VARIATION_POINT_GROUP__VARIATION_POINTS, VariationPointGroup.class, msgs);
			msgs = basicSetGroup(newGroup, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, variabilityPackage.VARIATION_POINT__GROUP, newGroup, newGroup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case variabilityPackage.VARIATION_POINT__GROUP:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGroup((VariationPointGroup)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case variabilityPackage.VARIATION_POINT__VARIANTS:
				return ((InternalEList<?>)getVariants()).basicRemove(otherEnd, msgs);
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
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case variabilityPackage.VARIATION_POINT__GROUP:
				return eInternalContainer().eInverseRemove(this, variabilityPackage.VARIATION_POINT_GROUP__VARIATION_POINTS, VariationPointGroup.class, msgs);
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
			case variabilityPackage.VARIATION_POINT__REALIZATION_TECHNIQUE:
				if (resolve) return getRealizationTechnique();
				return basicGetRealizationTechnique();
			case variabilityPackage.VARIATION_POINT__FEATURE:
				if (resolve) return getFeature();
				return basicGetFeature();
			case variabilityPackage.VARIATION_POINT__SOFTWARE_ENTITY:
				if (resolve) return getSoftwareEntity();
				return basicGetSoftwareEntity();
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
				getVariants().addAll((Collection<? extends Variant>)newValue);
				return;
			case variabilityPackage.VARIATION_POINT__REALIZATION_TECHNIQUE:
				setRealizationTechnique((RealizationTechnique)newValue);
				return;
			case variabilityPackage.VARIATION_POINT__FEATURE:
				setFeature((Feature)newValue);
				return;
			case variabilityPackage.VARIATION_POINT__SOFTWARE_ENTITY:
				setSoftwareEntity((ASTNode)newValue);
				return;
			case variabilityPackage.VARIATION_POINT__GROUP:
				setGroup((VariationPointGroup)newValue);
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
			case variabilityPackage.VARIATION_POINT__REALIZATION_TECHNIQUE:
				setRealizationTechnique((RealizationTechnique)null);
				return;
			case variabilityPackage.VARIATION_POINT__FEATURE:
				setFeature((Feature)null);
				return;
			case variabilityPackage.VARIATION_POINT__SOFTWARE_ENTITY:
				setSoftwareEntity((ASTNode)null);
				return;
			case variabilityPackage.VARIATION_POINT__GROUP:
				setGroup((VariationPointGroup)null);
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
			case variabilityPackage.VARIATION_POINT__REALIZATION_TECHNIQUE:
				return realizationTechnique != null;
			case variabilityPackage.VARIATION_POINT__FEATURE:
				return feature != null;
			case variabilityPackage.VARIATION_POINT__SOFTWARE_ENTITY:
				return softwareEntity != null;
			case variabilityPackage.VARIATION_POINT__GROUP:
				return getGroup() != null;
		}
		return super.eIsSet(featureID);
	}

} //VariationPointImpl
