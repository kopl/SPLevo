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
package org.splevo.vrm;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extendibility;
import org.splevo.vpm.variability.VariabilityType;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Variability Realization Technique</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> A specific technique for realizing the variability of a variation point.
 * Techniques should be on the real implementation level and not on a generic conceptual one. The
 * must be realizable. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.splevo.vrm.VariabilityRealizationTechnique#getRefactoringId <em>Refactoring Id
 * </em>}</li>
 * <li>{@link org.splevo.vrm.VariabilityRealizationTechnique#getName <em>Name</em>}</li>
 * <li>{@link org.splevo.vrm.VariabilityRealizationTechnique#getDescription <em>Description</em>}</li>
 * <li>{@link org.splevo.vrm.VariabilityRealizationTechnique#getSupportedBindingTimes <em>Supported
 * Binding Times</em>}</li>
 * <li>{@link org.splevo.vrm.VariabilityRealizationTechnique#getSupportedExtendibility <em>Supported
 * Extendibility</em>}</li>
 * <li>{@link org.splevo.vrm.VariabilityRealizationTechnique#getSupportedVariabilityTypes <em>
 * Supported Variability Types</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.splevo.vrm.vrmPackage#getVariabilityRealizationTechnique()
 * @model
 * @generated
 */
public interface VariabilityRealizationTechnique extends EObject {
    /**
     * Returns the value of the '<em><b>Refactoring Id</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> The internal id of the refactoring
     * implementation providing this technique and able to refactor the copies. <!-- end-model-doc
     * -->
     * 
     * @return the value of the '<em>Refactoring Id</em>' attribute.
     * @see #setRefactoringId(String)
     * @see org.splevo.vrm.vrmPackage#getVariabilityRealizationTechnique_RefactoringId()
     * @model required="true"
     * @generated
     */
    String getRefactoringId();

    /**
     * Sets the value of the '
     * {@link org.splevo.vrm.VariabilityRealizationTechnique#getRefactoringId
     * <em>Refactoring Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Refactoring Id</em>' attribute.
     * @see #getRefactoringId()
     * @generated
     */
    void setRefactoringId(String value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The name of the technique. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.splevo.vrm.vrmPackage#getVariabilityRealizationTechnique_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.splevo.vrm.VariabilityRealizationTechnique#getName
     * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> A description to understand the technique and
     * it's implications. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see org.splevo.vrm.vrmPackage#getVariabilityRealizationTechnique_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '{@link org.splevo.vrm.VariabilityRealizationTechnique#getDescription
     * <em>Description</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Supported Binding Times</b></em>' attribute list. The list
     * contents are of type {@link org.splevo.vpm.variability.BindingTime}. The literals are from
     * the enumeration {@link org.splevo.vpm.variability.BindingTime}. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The binding times supported by this technique <!--
     * end-model-doc -->
     * 
     * @return the value of the '<em>Supported Binding Times</em>' attribute list.
     * @see org.splevo.vpm.variability.BindingTime
     * @see org.splevo.vrm.vrmPackage#getVariabilityRealizationTechnique_SupportedBindingTimes()
     * @model required="true"
     * @generated
     */
    EList<BindingTime> getSupportedBindingTimes();

    /**
     * Returns the value of the '<em><b>Supported Extendibility</b></em>' attribute list. The list
     * contents are of type {@link org.splevo.vpm.variability.Extendibility}. The literals are from
     * the enumeration {@link org.splevo.vpm.variability.Extendibility}. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> The extendibility options supported by this
     * technique. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Supported Extendibility</em>' attribute list.
     * @see org.splevo.vpm.variability.Extendibility
     * @see org.splevo.vrm.vrmPackage#getVariabilityRealizationTechnique_SupportedExtendibility()
     * @model required="true"
     * @generated
     */
    EList<Extendibility> getSupportedExtendibility();

    /**
     * Returns the value of the '<em><b>Supported Variability Types</b></em>' attribute list. The
     * list contents are of type {@link org.splevo.vpm.variability.VariabilityType}. The literals
     * are from the enumeration {@link org.splevo.vpm.variability.VariabilityType}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The variability type
     * options supported by this realization technique. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Supported Variability Types</em>' attribute list.
     * @see org.splevo.vpm.variability.VariabilityType
     * @see org.splevo.vrm.vrmPackage#getVariabilityRealizationTechnique_SupportedVariabilityTypes()
     * @model required="true"
     * @generated
     */
    EList<VariabilityType> getSupportedVariabilityTypes();

} // VariabilityRealizationTechnique
