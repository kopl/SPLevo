/*******************************************************************************
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.project.util;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.splevo.project.*;
import org.splevo.project.ProjectPackage;
import org.splevo.project.SPLProfile;
import org.splevo.project.SPLevoProject;
import org.splevo.project.VPMModelReference;
import org.splevo.project.utils.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.splevo.project.ProjectPackage
 * @generated
 */
public class ProjectAdapterFactory extends AdapterFactoryImpl {
	/**
     * The cached model package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static ProjectPackage modelPackage;

	/**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ProjectAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = ProjectPackage.eINSTANCE;
        }
    }

	/**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
	@Override
	public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

	/**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ProjectSwitch<Adapter> modelSwitch = new ProjectSwitch<Adapter>() {
            @Override
            public Adapter caseSPLevoProject(SPLevoProject object) {
                return createSPLevoProjectAdapter();
            }
            @Override
            public Adapter caseDifferOption(Map.Entry<String, String> object) {
                return createDifferOptionAdapter();
            }
            @Override
            public Adapter caseSPLProfile(SPLProfile object) {
                return createSPLProfileAdapter();
            }
            @Override
            public Adapter caseVPMModelReference(VPMModelReference object) {
                return createVPMModelReferenceAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

	/**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
	@Override
	public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }

	/**
     * Creates a new adapter for an object of class '{@link org.splevo.project.SPLevoProject <em>SP Levo Project</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.splevo.project.SPLevoProject
     * @generated
     */
	public Adapter createSPLevoProjectAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Differ Option</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see java.util.Map.Entry
     * @generated
     */
    public Adapter createDifferOptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.splevo.project.SPLProfile <em>SPL Profile</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.splevo.project.SPLProfile
     * @generated
     */
    public Adapter createSPLProfileAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.splevo.project.VPMModelReference <em>VPM Model Reference</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.splevo.project.VPMModelReference
     * @generated
     */
    public Adapter createVPMModelReferenceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
	public Adapter createEObjectAdapter() {
        return null;
    }

} //ProjectAdapterFactory
