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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each
 * non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see org.splevo.vrm.vrmPackage
 * @generated
 */
public interface vrmFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    vrmFactory eINSTANCE = org.splevo.vrm.impl.vrmFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Variability Mechanism</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return a new object of class '<em>Variability Mechanism</em>'.
     * @generated
     */
    VariabilityMechanism createVariabilityMechanism();

    /**
     * Returns a new object of class '<em>Variability Realization Configuration</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Variability Realization Configuration</em>'.
     * @generated
     */
    VariabilityRealizationConfiguration createVariabilityRealizationConfiguration();

    /**
     * Returns a new object of class '<em>Variability Realization Model</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Variability Realization Model</em>'.
     * @generated
     */
    VariabilityRealizationModel createVariabilityRealizationModel();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the package supported by this factory.
     * @generated
     */
    vrmPackage getvrmPackage();

} // vrmFactory
