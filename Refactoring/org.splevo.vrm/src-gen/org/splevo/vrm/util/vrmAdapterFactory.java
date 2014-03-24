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
package org.splevo.vrm.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.splevo.vrm.*;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter
 * <code>createXXX</code> method for each class of the model. <!-- end-user-doc -->
 * 
 * @see org.splevo.vrm.vrmPackage
 * @generated
 */
public class vrmAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static vrmPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public vrmAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = vrmPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc
     * --> This implementation returns <code>true</code> if the object is either the model's package
     * or is an instance object of the model. <!-- end-user-doc -->
     * 
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected vrmSwitch<Adapter> modelSwitch = new vrmSwitch<Adapter>() {
        @Override
        public Adapter caseVariabilityRealizationTechnique(VariabilityRealizationTechnique object) {
            return createVariabilityRealizationTechniqueAdapter();
        }

        @Override
        public Adapter caseVariabilityRealizationConfiguration(VariabilityRealizationConfiguration object) {
            return createVariabilityRealizationConfigurationAdapter();
        }

        @Override
        public Adapter caseVariabilityRealizationModel(VariabilityRealizationModel object) {
            return createVariabilityRealizationModelAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param target
     *            the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.splevo.vrm.VariabilityRealizationTechnique
     * <em>Variability Realization Technique</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's useful to ignore a case
     * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.splevo.vrm.VariabilityRealizationTechnique
     * @generated
     */
    public Adapter createVariabilityRealizationTechniqueAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.splevo.vrm.VariabilityRealizationConfiguration
     * <em>Variability Realization Configuration</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's useful to ignore a case
     * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.splevo.vrm.VariabilityRealizationConfiguration
     * @generated
     */
    public Adapter createVariabilityRealizationConfigurationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.splevo.vrm.VariabilityRealizationModel <em>Variability Realization Model</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.splevo.vrm.VariabilityRealizationModel
     * @generated
     */
    public Adapter createVariabilityRealizationModelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This default
     * implementation returns null. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // vrmAdapterFactory