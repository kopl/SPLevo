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
import org.splevo.vpm.variability.Extendibility;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vrm.VariabilityRealizationTechnique;
import org.splevo.vrm.vrmPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Variability Realization Technique</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.splevo.vrm.impl.VariabilityRealizationTechniqueImpl#getRefactoringId <em>
 * Refactoring Id</em>}</li>
 * <li>{@link org.splevo.vrm.impl.VariabilityRealizationTechniqueImpl#getName <em>Name</em>}</li>
 * <li>{@link org.splevo.vrm.impl.VariabilityRealizationTechniqueImpl#getDescription <em>Description
 * </em>}</li>
 * <li>{@link org.splevo.vrm.impl.VariabilityRealizationTechniqueImpl#getSupportedBindingTimes <em>
 * Supported Binding Times</em>}</li>
 * <li>{@link org.splevo.vrm.impl.VariabilityRealizationTechniqueImpl#getSupportedExtendibility <em>
 * Supported Extendibility</em>}</li>
 * <li>{@link org.splevo.vrm.impl.VariabilityRealizationTechniqueImpl#getSupportedVariabilityTypes
 * <em>Supported Variability Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VariabilityRealizationTechniqueImpl extends MinimalEObjectImpl.Container implements
        VariabilityRealizationTechnique {
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
     * The cached value of the '{@link #getSupportedExtendibility()
     * <em>Supported Extendibility</em>}' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getSupportedExtendibility()
     * @generated
     * @ordered
     */
    protected EList<Extendibility> supportedExtendibility;

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
    protected VariabilityRealizationTechniqueImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return vrmPackage.Literals.VARIABILITY_REALIZATION_TECHNIQUE;
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
            eNotify(new ENotificationImpl(this, Notification.SET,
                    vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__REFACTORING_ID, oldRefactoringId, refactoringId));
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
            eNotify(new ENotificationImpl(this, Notification.SET, vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__NAME,
                    oldName, name));
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
            eNotify(new ENotificationImpl(this, Notification.SET,
                    vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__DESCRIPTION, oldDescription, description));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EList<BindingTime> getSupportedBindingTimes() {
        if (supportedBindingTimes == null) {
            supportedBindingTimes = new EDataTypeUniqueEList<BindingTime>(BindingTime.class, this,
                    vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__SUPPORTED_BINDING_TIMES);
        }
        return supportedBindingTimes;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EList<Extendibility> getSupportedExtendibility() {
        if (supportedExtendibility == null) {
            supportedExtendibility = new EDataTypeUniqueEList<Extendibility>(Extendibility.class, this,
                    vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__SUPPORTED_EXTENDIBILITY);
        }
        return supportedExtendibility;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EList<VariabilityType> getSupportedVariabilityTypes() {
        if (supportedVariabilityTypes == null) {
            supportedVariabilityTypes = new EDataTypeUniqueEList<VariabilityType>(VariabilityType.class, this,
                    vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__SUPPORTED_VARIABILITY_TYPES);
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
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__REFACTORING_ID:
            return getRefactoringId();
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__NAME:
            return getName();
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__DESCRIPTION:
            return getDescription();
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__SUPPORTED_BINDING_TIMES:
            return getSupportedBindingTimes();
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__SUPPORTED_EXTENDIBILITY:
            return getSupportedExtendibility();
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__SUPPORTED_VARIABILITY_TYPES:
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
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__REFACTORING_ID:
            setRefactoringId((String) newValue);
            return;
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__NAME:
            setName((String) newValue);
            return;
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__DESCRIPTION:
            setDescription((String) newValue);
            return;
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__SUPPORTED_BINDING_TIMES:
            getSupportedBindingTimes().clear();
            getSupportedBindingTimes().addAll((Collection<? extends BindingTime>) newValue);
            return;
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__SUPPORTED_EXTENDIBILITY:
            getSupportedExtendibility().clear();
            getSupportedExtendibility().addAll((Collection<? extends Extendibility>) newValue);
            return;
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__SUPPORTED_VARIABILITY_TYPES:
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
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__REFACTORING_ID:
            setRefactoringId(REFACTORING_ID_EDEFAULT);
            return;
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__NAME:
            setName(NAME_EDEFAULT);
            return;
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__DESCRIPTION:
            setDescription(DESCRIPTION_EDEFAULT);
            return;
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__SUPPORTED_BINDING_TIMES:
            getSupportedBindingTimes().clear();
            return;
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__SUPPORTED_EXTENDIBILITY:
            getSupportedExtendibility().clear();
            return;
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__SUPPORTED_VARIABILITY_TYPES:
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
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__REFACTORING_ID:
            return REFACTORING_ID_EDEFAULT == null ? refactoringId != null : !REFACTORING_ID_EDEFAULT
                    .equals(refactoringId);
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__DESCRIPTION:
            return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__SUPPORTED_BINDING_TIMES:
            return supportedBindingTimes != null && !supportedBindingTimes.isEmpty();
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__SUPPORTED_EXTENDIBILITY:
            return supportedExtendibility != null && !supportedExtendibility.isEmpty();
        case vrmPackage.VARIABILITY_REALIZATION_TECHNIQUE__SUPPORTED_VARIABILITY_TYPES:
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
        result.append(", supportedExtendibility: ");
        result.append(supportedExtendibility);
        result.append(", supportedVariabilityTypes: ");
        result.append(supportedVariabilityTypes);
        result.append(')');
        return result.toString();
    }

} // VariabilityRealizationTechniqueImpl
