/**
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Benjamin Klatt
 */
package org.splevo.vrm.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vrm.VariabilityMechanism;
import org.splevo.vrm.vrmPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Variability Mechanism</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.splevo.vrm.impl.VariabilityMechanismImpl#getRefactoringId <em>Refactoring Id</em>}
 * </li>
 * <li>{@link org.splevo.vrm.impl.VariabilityMechanismImpl#getName <em>Name</em>}</li>
 * <li>{@link org.splevo.vrm.impl.VariabilityMechanismImpl#getDescription <em>Description</em>}</li>
 * <li>{@link org.splevo.vrm.impl.VariabilityMechanismImpl#getSupportedBindingTimes <em>Supported
 * Binding Times</em>}</li>
 * <li>{@link org.splevo.vrm.impl.VariabilityMechanismImpl#getSupportedExtensibilities <em>Supported
 * Extensibilities</em>}</li>
 * <li>{@link org.splevo.vrm.impl.VariabilityMechanismImpl#getSupportedVariabilityTypes <em>
 * Supported Variability Types</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class VariabilityMechanismImpl extends MinimalEObjectImpl.Container implements VariabilityMechanism {
    /**
     * The default value of the '{@link #getRefactoringId() <em>Refactoring Id</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getRefactoringId()
     * @generated
     * @ordered
     */
    protected static final String REFACTORING_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRefactoringId() <em>Refactoring Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getRefactoringId()
     * @generated
     * @ordered
     */
    protected String refactoringId = REFACTORING_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getDescription() <em>Description</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected String description = DESCRIPTION_EDEFAULT;

    /**
     * The cached value of the '{@link #getSupportedBindingTimes() <em>Supported Binding Times</em>}
     * ' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSupportedBindingTimes()
     * @generated
     * @ordered
     */
    protected EList<BindingTime> supportedBindingTimes;

    /**
     * The cached value of the '{@link #getSupportedExtensibilities()
     * <em>Supported Extensibilities</em>}' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getSupportedExtensibilities()
     * @generated
     * @ordered
     */
    protected EList<Extensible> supportedExtensibilities;

    /**
     * The cached value of the '{@link #getSupportedVariabilityTypes()
     * <em>Supported Variability Types</em>}' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getSupportedVariabilityTypes()
     * @generated
     * @ordered
     */
    protected EList<VariabilityType> supportedVariabilityTypes;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected VariabilityMechanismImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return vrmPackage.Literals.VARIABILITY_MECHANISM;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getRefactoringId() {
        return refactoringId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setRefactoringId(String newRefactoringId) {
        String oldRefactoringId = refactoringId;
        refactoringId = newRefactoringId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, vrmPackage.VARIABILITY_MECHANISM__REFACTORING_ID,
                    oldRefactoringId, refactoringId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, vrmPackage.VARIABILITY_MECHANISM__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, vrmPackage.VARIABILITY_MECHANISM__DESCRIPTION,
                    oldDescription, description));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<BindingTime> getSupportedBindingTimes() {
        if (supportedBindingTimes == null) {
            supportedBindingTimes = new EDataTypeUniqueEList<BindingTime>(BindingTime.class, this,
                    vrmPackage.VARIABILITY_MECHANISM__SUPPORTED_BINDING_TIMES);
        }
        return supportedBindingTimes;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Extensible> getSupportedExtensibilities() {
        if (supportedExtensibilities == null) {
            supportedExtensibilities = new EDataTypeUniqueEList<Extensible>(Extensible.class, this,
                    vrmPackage.VARIABILITY_MECHANISM__SUPPORTED_EXTENSIBILITIES);
        }
        return supportedExtensibilities;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<VariabilityType> getSupportedVariabilityTypes() {
        if (supportedVariabilityTypes == null) {
            supportedVariabilityTypes = new EDataTypeUniqueEList<VariabilityType>(VariabilityType.class, this,
                    vrmPackage.VARIABILITY_MECHANISM__SUPPORTED_VARIABILITY_TYPES);
        }
        return supportedVariabilityTypes;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case vrmPackage.VARIABILITY_MECHANISM__REFACTORING_ID:
            return getRefactoringId();
        case vrmPackage.VARIABILITY_MECHANISM__NAME:
            return getName();
        case vrmPackage.VARIABILITY_MECHANISM__DESCRIPTION:
            return getDescription();
        case vrmPackage.VARIABILITY_MECHANISM__SUPPORTED_BINDING_TIMES:
            return getSupportedBindingTimes();
        case vrmPackage.VARIABILITY_MECHANISM__SUPPORTED_EXTENSIBILITIES:
            return getSupportedExtensibilities();
        case vrmPackage.VARIABILITY_MECHANISM__SUPPORTED_VARIABILITY_TYPES:
            return getSupportedVariabilityTypes();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case vrmPackage.VARIABILITY_MECHANISM__REFACTORING_ID:
            setRefactoringId((String) newValue);
            return;
        case vrmPackage.VARIABILITY_MECHANISM__NAME:
            setName((String) newValue);
            return;
        case vrmPackage.VARIABILITY_MECHANISM__DESCRIPTION:
            setDescription((String) newValue);
            return;
        case vrmPackage.VARIABILITY_MECHANISM__SUPPORTED_BINDING_TIMES:
            getSupportedBindingTimes().clear();
            getSupportedBindingTimes().addAll((Collection<? extends BindingTime>) newValue);
            return;
        case vrmPackage.VARIABILITY_MECHANISM__SUPPORTED_EXTENSIBILITIES:
            getSupportedExtensibilities().clear();
            getSupportedExtensibilities().addAll((Collection<? extends Extensible>) newValue);
            return;
        case vrmPackage.VARIABILITY_MECHANISM__SUPPORTED_VARIABILITY_TYPES:
            getSupportedVariabilityTypes().clear();
            getSupportedVariabilityTypes().addAll((Collection<? extends VariabilityType>) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case vrmPackage.VARIABILITY_MECHANISM__REFACTORING_ID:
            setRefactoringId(REFACTORING_ID_EDEFAULT);
            return;
        case vrmPackage.VARIABILITY_MECHANISM__NAME:
            setName(NAME_EDEFAULT);
            return;
        case vrmPackage.VARIABILITY_MECHANISM__DESCRIPTION:
            setDescription(DESCRIPTION_EDEFAULT);
            return;
        case vrmPackage.VARIABILITY_MECHANISM__SUPPORTED_BINDING_TIMES:
            getSupportedBindingTimes().clear();
            return;
        case vrmPackage.VARIABILITY_MECHANISM__SUPPORTED_EXTENSIBILITIES:
            getSupportedExtensibilities().clear();
            return;
        case vrmPackage.VARIABILITY_MECHANISM__SUPPORTED_VARIABILITY_TYPES:
            getSupportedVariabilityTypes().clear();
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case vrmPackage.VARIABILITY_MECHANISM__REFACTORING_ID:
            return REFACTORING_ID_EDEFAULT == null ? refactoringId != null : !REFACTORING_ID_EDEFAULT
                    .equals(refactoringId);
        case vrmPackage.VARIABILITY_MECHANISM__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
        case vrmPackage.VARIABILITY_MECHANISM__DESCRIPTION:
            return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
        case vrmPackage.VARIABILITY_MECHANISM__SUPPORTED_BINDING_TIMES:
            return supportedBindingTimes != null && !supportedBindingTimes.isEmpty();
        case vrmPackage.VARIABILITY_MECHANISM__SUPPORTED_EXTENSIBILITIES:
            return supportedExtensibilities != null && !supportedExtensibilities.isEmpty();
        case vrmPackage.VARIABILITY_MECHANISM__SUPPORTED_VARIABILITY_TYPES:
            return supportedVariabilityTypes != null && !supportedVariabilityTypes.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (refactoringId: ");
        result.append(refactoringId);
        result.append(", name: ");
        result.append(name);
        result.append(", description: ");
        result.append(description);
        result.append(", supportedBindingTimes: ");
        result.append(supportedBindingTimes);
        result.append(", supportedExtensibilities: ");
        result.append(supportedExtensibilities);
        result.append(", supportedVariabilityTypes: ");
        result.append(supportedVariabilityTypes);
        result.append(')');
        return result.toString();
    }

} // VariabilityMechanismImpl
