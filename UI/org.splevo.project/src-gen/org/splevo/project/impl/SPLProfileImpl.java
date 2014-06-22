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
package org.splevo.project.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.splevo.project.ProjectPackage;
import org.splevo.project.QualityGoal;
import org.splevo.project.SPLProfile;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SPL Profile</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.project.impl.SPLProfileImpl#getRecommendedRefactoringIds <em>Recommended Refactoring Ids</em>}</li>
 *   <li>{@link org.splevo.project.impl.SPLProfileImpl#getQualityGoals <em>Quality Goals</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SPLProfileImpl extends EObjectImpl implements SPLProfile {
    /**
     * The cached value of the '{@link #getRecommendedRefactoringIds() <em>Recommended Refactoring Ids</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRecommendedRefactoringIds()
     * @generated
     * @ordered
     */
    protected EList<String> recommendedRefactoringIds;

    /**
     * The cached value of the '{@link #getQualityGoals() <em>Quality Goals</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getQualityGoals()
     * @generated
     * @ordered
     */
    protected EList<QualityGoal> qualityGoals;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SPLProfileImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ProjectPackage.Literals.SPL_PROFILE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getRecommendedRefactoringIds() {
        if (recommendedRefactoringIds == null) {
            recommendedRefactoringIds = new EDataTypeUniqueEList<String>(String.class, this, ProjectPackage.SPL_PROFILE__RECOMMENDED_REFACTORING_IDS);
        }
        return recommendedRefactoringIds;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<QualityGoal> getQualityGoals() {
        if (qualityGoals == null) {
            qualityGoals = new EDataTypeUniqueEList<QualityGoal>(QualityGoal.class, this, ProjectPackage.SPL_PROFILE__QUALITY_GOALS);
        }
        return qualityGoals;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ProjectPackage.SPL_PROFILE__RECOMMENDED_REFACTORING_IDS:
                return getRecommendedRefactoringIds();
            case ProjectPackage.SPL_PROFILE__QUALITY_GOALS:
                return getQualityGoals();
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
            case ProjectPackage.SPL_PROFILE__RECOMMENDED_REFACTORING_IDS:
                getRecommendedRefactoringIds().clear();
                getRecommendedRefactoringIds().addAll((Collection<? extends String>)newValue);
                return;
            case ProjectPackage.SPL_PROFILE__QUALITY_GOALS:
                getQualityGoals().clear();
                getQualityGoals().addAll((Collection<? extends QualityGoal>)newValue);
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
            case ProjectPackage.SPL_PROFILE__RECOMMENDED_REFACTORING_IDS:
                getRecommendedRefactoringIds().clear();
                return;
            case ProjectPackage.SPL_PROFILE__QUALITY_GOALS:
                getQualityGoals().clear();
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
            case ProjectPackage.SPL_PROFILE__RECOMMENDED_REFACTORING_IDS:
                return recommendedRefactoringIds != null && !recommendedRefactoringIds.isEmpty();
            case ProjectPackage.SPL_PROFILE__QUALITY_GOALS:
                return qualityGoals != null && !qualityGoals.isEmpty();
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
        result.append(" (recommendedRefactoringIds: ");
        result.append(recommendedRefactoringIds);
        result.append(", qualityGoals: ");
        result.append(qualityGoals);
        result.append(')');
        return result.toString();
    }

} //SPLProfileImpl
