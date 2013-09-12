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
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variation Point Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
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
     * The cached value of the '{@link #getLeadingModel() <em>Leading Model</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLeadingModel()
     * @generated
     * @ordered
     */
    protected JavaApplication leadingModel;

    /**
     * The cached value of the '{@link #getIntegrationModel() <em>Integration Model</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIntegrationModel()
     * @generated
     * @ordered
     */
    protected JavaApplication integrationModel;

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
    public JavaApplication getLeadingModel() {
        if (leadingModel != null && leadingModel.eIsProxy()) {
            InternalEObject oldLeadingModel = (InternalEObject) leadingModel;
            leadingModel = (JavaApplication) eResolveProxy(oldLeadingModel);
            if (leadingModel != oldLeadingModel) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            variabilityPackage.VARIATION_POINT_MODEL__LEADING_MODEL, oldLeadingModel, leadingModel));
            }
        }
        return leadingModel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public JavaApplication basicGetLeadingModel() {
        return leadingModel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLeadingModel(JavaApplication newLeadingModel) {
        JavaApplication oldLeadingModel = leadingModel;
        leadingModel = newLeadingModel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    variabilityPackage.VARIATION_POINT_MODEL__LEADING_MODEL, oldLeadingModel, leadingModel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public JavaApplication getIntegrationModel() {
        if (integrationModel != null && integrationModel.eIsProxy()) {
            InternalEObject oldIntegrationModel = (InternalEObject) integrationModel;
            integrationModel = (JavaApplication) eResolveProxy(oldIntegrationModel);
            if (integrationModel != oldIntegrationModel) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            variabilityPackage.VARIATION_POINT_MODEL__INTEGRATION_MODEL, oldIntegrationModel,
                            integrationModel));
            }
        }
        return integrationModel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public JavaApplication basicGetIntegrationModel() {
        return integrationModel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIntegrationModel(JavaApplication newIntegrationModel) {
        JavaApplication oldIntegrationModel = integrationModel;
        integrationModel = newIntegrationModel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    variabilityPackage.VARIATION_POINT_MODEL__INTEGRATION_MODEL, oldIntegrationModel, integrationModel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<VariationPointGroup> getVariationPointGroups() {
        if (variationPointGroups == null) {
            variationPointGroups = new EObjectContainmentWithInverseEList<VariationPointGroup>(
                    VariationPointGroup.class, this, variabilityPackage.VARIATION_POINT_MODEL__VARIATION_POINT_GROUPS,
                    variabilityPackage.VARIATION_POINT_GROUP__MODEL);
        }
        return variationPointGroups;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case variabilityPackage.VARIATION_POINT_MODEL__VARIATION_POINT_GROUPS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getVariationPointGroups()).basicAdd(otherEnd,
                    msgs);
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
        case variabilityPackage.VARIATION_POINT_MODEL__VARIATION_POINT_GROUPS:
            return ((InternalEList<?>) getVariationPointGroups()).basicRemove(otherEnd, msgs);
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
        case variabilityPackage.VARIATION_POINT_MODEL__LEADING_MODEL:
            if (resolve)
                return getLeadingModel();
            return basicGetLeadingModel();
        case variabilityPackage.VARIATION_POINT_MODEL__INTEGRATION_MODEL:
            if (resolve)
                return getIntegrationModel();
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
        case variabilityPackage.VARIATION_POINT_MODEL__LEADING_MODEL:
            setLeadingModel((JavaApplication) newValue);
            return;
        case variabilityPackage.VARIATION_POINT_MODEL__INTEGRATION_MODEL:
            setIntegrationModel((JavaApplication) newValue);
            return;
        case variabilityPackage.VARIATION_POINT_MODEL__VARIATION_POINT_GROUPS:
            getVariationPointGroups().clear();
            getVariationPointGroups().addAll((Collection<? extends VariationPointGroup>) newValue);
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
        case variabilityPackage.VARIATION_POINT_MODEL__LEADING_MODEL:
            setLeadingModel((JavaApplication) null);
            return;
        case variabilityPackage.VARIATION_POINT_MODEL__INTEGRATION_MODEL:
            setIntegrationModel((JavaApplication) null);
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
