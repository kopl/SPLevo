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
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gmt.modisco.java.Model;

import org.splevo.vpm.realization.RealizationTechnique;

import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variation Point Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointModelImpl#getRealizationTechniques <em>Realization Techniques</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointModelImpl#getLeadingModel <em>Leading Model</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointModelImpl#getIntegrationModel <em>Integration Model</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointModelImpl#getVariationPointGroups <em>Variation Point Groups</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VariationPointModelImpl extends EObjectImpl implements VariationPointModel {
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
	 * The cached value of the '{@link #getLeadingModel() <em>Leading Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeadingModel()
	 * @generated
	 * @ordered
	 */
	protected Model leadingModel;

	/**
	 * The cached value of the '{@link #getIntegrationModel() <em>Integration Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntegrationModel()
	 * @generated
	 * @ordered
	 */
	protected Model integrationModel;

	/**
	 * The cached value of the '{@link #getVariationPointGroups() <em>Variation Point Groups</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariationPointGroups()
	 * @generated
	 * @ordered
	 */
	protected EList<VariationPointGroup> variationPointGroups;

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
		return variabilityPackage.Literals.VARIATION_POINT_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RealizationTechnique> getRealizationTechniques() {
		if (realizationTechniques == null) {
			realizationTechniques = new EObjectContainmentEList<RealizationTechnique>(RealizationTechnique.class, this, variabilityPackage.VARIATION_POINT_MODEL__REALIZATION_TECHNIQUES);
		}
		return realizationTechniques;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model getLeadingModel() {
		if (leadingModel != null && leadingModel.eIsProxy()) {
			InternalEObject oldLeadingModel = (InternalEObject)leadingModel;
			leadingModel = (Model)eResolveProxy(oldLeadingModel);
			if (leadingModel != oldLeadingModel) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, variabilityPackage.VARIATION_POINT_MODEL__LEADING_MODEL, oldLeadingModel, leadingModel));
			}
		}
		return leadingModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model basicGetLeadingModel() {
		return leadingModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeadingModel(Model newLeadingModel) {
		Model oldLeadingModel = leadingModel;
		leadingModel = newLeadingModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, variabilityPackage.VARIATION_POINT_MODEL__LEADING_MODEL, oldLeadingModel, leadingModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model getIntegrationModel() {
		if (integrationModel != null && integrationModel.eIsProxy()) {
			InternalEObject oldIntegrationModel = (InternalEObject)integrationModel;
			integrationModel = (Model)eResolveProxy(oldIntegrationModel);
			if (integrationModel != oldIntegrationModel) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, variabilityPackage.VARIATION_POINT_MODEL__INTEGRATION_MODEL, oldIntegrationModel, integrationModel));
			}
		}
		return integrationModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model basicGetIntegrationModel() {
		return integrationModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIntegrationModel(Model newIntegrationModel) {
		Model oldIntegrationModel = integrationModel;
		integrationModel = newIntegrationModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, variabilityPackage.VARIATION_POINT_MODEL__INTEGRATION_MODEL, oldIntegrationModel, integrationModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VariationPointGroup> getVariationPointGroups() {
		if (variationPointGroups == null) {
			variationPointGroups = new EObjectContainmentEList<VariationPointGroup>(VariationPointGroup.class, this, variabilityPackage.VARIATION_POINT_MODEL__VARIATION_POINT_GROUPS);
		}
		return variationPointGroups;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case variabilityPackage.VARIATION_POINT_MODEL__REALIZATION_TECHNIQUES:
				return ((InternalEList<?>)getRealizationTechniques()).basicRemove(otherEnd, msgs);
			case variabilityPackage.VARIATION_POINT_MODEL__VARIATION_POINT_GROUPS:
				return ((InternalEList<?>)getVariationPointGroups()).basicRemove(otherEnd, msgs);
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
			case variabilityPackage.VARIATION_POINT_MODEL__REALIZATION_TECHNIQUES:
				return getRealizationTechniques();
			case variabilityPackage.VARIATION_POINT_MODEL__LEADING_MODEL:
				if (resolve) return getLeadingModel();
				return basicGetLeadingModel();
			case variabilityPackage.VARIATION_POINT_MODEL__INTEGRATION_MODEL:
				if (resolve) return getIntegrationModel();
				return basicGetIntegrationModel();
			case variabilityPackage.VARIATION_POINT_MODEL__VARIATION_POINT_GROUPS:
				return getVariationPointGroups();
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
			case variabilityPackage.VARIATION_POINT_MODEL__REALIZATION_TECHNIQUES:
				getRealizationTechniques().clear();
				getRealizationTechniques().addAll((Collection<? extends RealizationTechnique>)newValue);
				return;
			case variabilityPackage.VARIATION_POINT_MODEL__LEADING_MODEL:
				setLeadingModel((Model)newValue);
				return;
			case variabilityPackage.VARIATION_POINT_MODEL__INTEGRATION_MODEL:
				setIntegrationModel((Model)newValue);
				return;
			case variabilityPackage.VARIATION_POINT_MODEL__VARIATION_POINT_GROUPS:
				getVariationPointGroups().clear();
				getVariationPointGroups().addAll((Collection<? extends VariationPointGroup>)newValue);
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
			case variabilityPackage.VARIATION_POINT_MODEL__REALIZATION_TECHNIQUES:
				getRealizationTechniques().clear();
				return;
			case variabilityPackage.VARIATION_POINT_MODEL__LEADING_MODEL:
				setLeadingModel((Model)null);
				return;
			case variabilityPackage.VARIATION_POINT_MODEL__INTEGRATION_MODEL:
				setIntegrationModel((Model)null);
				return;
			case variabilityPackage.VARIATION_POINT_MODEL__VARIATION_POINT_GROUPS:
				getVariationPointGroups().clear();
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
			case variabilityPackage.VARIATION_POINT_MODEL__REALIZATION_TECHNIQUES:
				return realizationTechniques != null && !realizationTechniques.isEmpty();
			case variabilityPackage.VARIATION_POINT_MODEL__LEADING_MODEL:
				return leadingModel != null;
			case variabilityPackage.VARIATION_POINT_MODEL__INTEGRATION_MODEL:
				return integrationModel != null;
			case variabilityPackage.VARIATION_POINT_MODEL__VARIATION_POINT_GROUPS:
				return variationPointGroups != null && !variationPointGroups.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //VariationPointModelImpl