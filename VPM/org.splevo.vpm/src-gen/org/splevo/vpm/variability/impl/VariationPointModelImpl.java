/**
 */
package org.splevo.vpm.variability.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variation Point Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointModelImpl#getVariationPointGroups <em>Variation Point Groups</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointModelImpl#getSoftwareElements <em>Software Elements</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VariationPointModelImpl extends MinimalEObjectImpl.Container implements VariationPointModel {
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
     * The cached value of the '{@link #getSoftwareElements() <em>Software Elements</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSoftwareElements()
     * @generated
     * @ordered
     */
    protected EList<SoftwareElement> softwareElements;

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
    public EList<SoftwareElement> getSoftwareElements() {
        if (softwareElements == null) {
            softwareElements = new EObjectContainmentEList<SoftwareElement>(SoftwareElement.class, this,
                    variabilityPackage.VARIATION_POINT_MODEL__SOFTWARE_ELEMENTS);
        }
        return softwareElements;
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
        case variabilityPackage.VARIATION_POINT_MODEL__SOFTWARE_ELEMENTS:
            return ((InternalEList<?>) getSoftwareElements()).basicRemove(otherEnd, msgs);
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
        case variabilityPackage.VARIATION_POINT_MODEL__VARIATION_POINT_GROUPS:
            return getVariationPointGroups();
        case variabilityPackage.VARIATION_POINT_MODEL__SOFTWARE_ELEMENTS:
            return getSoftwareElements();
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
        case variabilityPackage.VARIATION_POINT_MODEL__VARIATION_POINT_GROUPS:
            getVariationPointGroups().clear();
            getVariationPointGroups().addAll((Collection<? extends VariationPointGroup>) newValue);
            return;
        case variabilityPackage.VARIATION_POINT_MODEL__SOFTWARE_ELEMENTS:
            getSoftwareElements().clear();
            getSoftwareElements().addAll((Collection<? extends SoftwareElement>) newValue);
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
        case variabilityPackage.VARIATION_POINT_MODEL__VARIATION_POINT_GROUPS:
            getVariationPointGroups().clear();
            return;
        case variabilityPackage.VARIATION_POINT_MODEL__SOFTWARE_ELEMENTS:
            getSoftwareElements().clear();
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
        case variabilityPackage.VARIATION_POINT_MODEL__VARIATION_POINT_GROUPS:
            return variationPointGroups != null && !variationPointGroups.isEmpty();
        case variabilityPackage.VARIATION_POINT_MODEL__SOFTWARE_ELEMENTS:
            return softwareElements != null && !softwareElements.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //VariationPointModelImpl
