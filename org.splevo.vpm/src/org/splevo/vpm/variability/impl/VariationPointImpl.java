/**
 * <copyright>
 * </copyright>
 *
 * $Id$
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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.featuremodel.Feature;

import org.eclipse.gmt.modisco.omg.kdm.core.KDMEntity;

import org.splevo.vpm.realization.RealizationTechnique;

import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
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
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointImpl#getSoftwareEntities <em>Software Entities</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointImpl#getFeature <em>Feature</em>}</li>
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
	 * The cached value of the '{@link #getSoftwareEntities() <em>Software Entities</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSoftwareEntities()
	 * @generated
	 * @ordered
	 */
	protected EList<KDMEntity> softwareEntities;

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
	public EList<KDMEntity> getSoftwareEntities() {
		if (softwareEntities == null) {
			softwareEntities = new EObjectResolvingEList<KDMEntity>(KDMEntity.class, this, variabilityPackage.VARIATION_POINT__SOFTWARE_ENTITIES);
		}
		return softwareEntities;
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
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case variabilityPackage.VARIATION_POINT__VARIANTS:
				return ((InternalEList<?>)getVariants()).basicRemove(otherEnd, msgs);
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
			case variabilityPackage.VARIATION_POINT__VARIANTS:
				return getVariants();
			case variabilityPackage.VARIATION_POINT__REALIZATION_TECHNIQUE:
				if (resolve) return getRealizationTechnique();
				return basicGetRealizationTechnique();
			case variabilityPackage.VARIATION_POINT__SOFTWARE_ENTITIES:
				return getSoftwareEntities();
			case variabilityPackage.VARIATION_POINT__FEATURE:
				if (resolve) return getFeature();
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
			case variabilityPackage.VARIATION_POINT__VARIANTS:
				getVariants().clear();
				getVariants().addAll((Collection<? extends Variant>)newValue);
				return;
			case variabilityPackage.VARIATION_POINT__REALIZATION_TECHNIQUE:
				setRealizationTechnique((RealizationTechnique)newValue);
				return;
			case variabilityPackage.VARIATION_POINT__SOFTWARE_ENTITIES:
				getSoftwareEntities().clear();
				getSoftwareEntities().addAll((Collection<? extends KDMEntity>)newValue);
				return;
			case variabilityPackage.VARIATION_POINT__FEATURE:
				setFeature((Feature)newValue);
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
			case variabilityPackage.VARIATION_POINT__SOFTWARE_ENTITIES:
				getSoftwareEntities().clear();
				return;
			case variabilityPackage.VARIATION_POINT__FEATURE:
				setFeature((Feature)null);
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
			case variabilityPackage.VARIATION_POINT__SOFTWARE_ENTITIES:
				return softwareEntities != null && !softwareEntities.isEmpty();
			case variabilityPackage.VARIATION_POINT__FEATURE:
				return feature != null;
		}
		return super.eIsSet(featureID);
	}

} //VariationPointImpl
