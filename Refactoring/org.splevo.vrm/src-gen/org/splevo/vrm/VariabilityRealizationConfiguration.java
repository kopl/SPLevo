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
 * <em><b>Variability Realization Configuration</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.splevo.vrm.VariabilityRealizationConfiguration#getVariationPoint <em>Variation
 * Point</em>}</li>
 * <li>{@link org.splevo.vrm.VariabilityRealizationConfiguration#getMechanism <em>Mechanism</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.splevo.vrm.vrmPackage#getVariabilityRealizationConfiguration()
 * @model
 * @generated
 */
public interface VariabilityRealizationConfiguration extends EObject {
    /**
     * Returns the value of the '<em><b>Variation Point</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variation Point</em>' reference isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The configured variation point. <!--
     * end-model-doc -->
     * 
     * @return the value of the '<em>Variation Point</em>' reference.
     * @see #setVariationPoint(VariationPoint)
     * @see org.splevo.vrm.vrmPackage#getVariabilityRealizationConfiguration_VariationPoint()
     * @model required="true"
     * @generated
     */
    VariationPoint getVariationPoint();

    /**
     * Sets the value of the '
     * {@link org.splevo.vrm.VariabilityRealizationConfiguration#getVariationPoint
     * <em>Variation Point</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Variation Point</em>' reference.
     * @see #getVariationPoint()
     * @generated
     */
    void setVariationPoint(VariationPoint value);

    /**
     * Returns the value of the '<em><b>Mechanism</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The mechanism configured to realize the variability
     * of a variation point. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Mechanism</em>' reference.
     * @see #setMechanism(VariabilityMechanism)
     * @see org.splevo.vrm.vrmPackage#getVariabilityRealizationConfiguration_Mechanism()
     * @model required="true"
     * @generated
     */
    VariabilityMechanism getMechanism();

    /**
     * Sets the value of the '
     * {@link org.splevo.vrm.VariabilityRealizationConfiguration#getMechanism <em>Mechanism</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Mechanism</em>' reference.
     * @see #getMechanism()
     * @generated
     */
    void setMechanism(VariabilityMechanism value);

} // VariabilityRealizationConfiguration
