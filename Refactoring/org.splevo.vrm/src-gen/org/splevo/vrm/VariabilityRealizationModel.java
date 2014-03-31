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
import org.splevo.vpm.variability.VariationPointModel;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Variability Realization Model</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.splevo.vrm.VariabilityRealizationModel#getRealizationConfigurations <em>
 * Realization Configurations</em>}</li>
 * <li>{@link org.splevo.vrm.VariabilityRealizationModel#getVpm <em>Vpm</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.splevo.vrm.vrmPackage#getVariabilityRealizationModel()
 * @model
 * @generated
 */
public interface VariabilityRealizationModel extends EObject {
    /**
     * Returns the value of the '<em><b>Realization Configurations</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.splevo.vrm.VariabilityRealizationConfiguration}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Realization Configurations</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Realization Configurations</em>' containment reference list.
     * @see org.splevo.vrm.vrmPackage#getVariabilityRealizationModel_RealizationConfigurations()
     * @model containment="true"
     * @generated
     */
    EList<VariabilityRealizationConfiguration> getRealizationConfigurations();

    /**
     * Returns the value of the '<em><b>Vpm</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Vpm</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Vpm</em>' reference.
     * @see #setVpm(VariationPointModel)
     * @see org.splevo.vrm.vrmPackage#getVariabilityRealizationModel_Vpm()
     * @model required="true"
     * @generated
     */
    VariationPointModel getVpm();

    /**
     * Sets the value of the '{@link org.splevo.vrm.VariabilityRealizationModel#getVpm <em>Vpm</em>}
     * ' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Vpm</em>' reference.
     * @see #getVpm()
     * @generated
     */
    void setVpm(VariationPointModel value);

} // VariabilityRealizationModel
