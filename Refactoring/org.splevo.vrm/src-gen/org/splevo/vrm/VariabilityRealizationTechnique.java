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

import org.eclipse.emf.ecore.EObject;

import org.splevo.vpm.variability.VariationPoint;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Variability Realization Technique</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> A specific technique for realizing the variability of a variation point.
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.splevo.vrm.VariabilityRealizationTechnique#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.splevo.vrm.vrmPackage#getVariabilityRealizationTechnique()
 * @model
 * @generated
 */
public interface VariabilityRealizationTechnique extends EObject {
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model required="true"
     * @generated
     */
    boolean canBeAppliedTo(VariationPoint variationPoint);

} // VariabilityRealizationTechnique
