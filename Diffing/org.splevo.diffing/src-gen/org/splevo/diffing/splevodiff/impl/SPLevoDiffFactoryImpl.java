/**
 * Copyright (c) 2014
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Benjamin Klatt - initial API and implementation and/or initial documentation
 */
package org.splevo.diffing.splevodiff.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.splevo.diffing.splevodiff.SPLevoDiff;
import org.splevo.diffing.splevodiff.SPLevoDiffFactory;
import org.splevo.diffing.splevodiff.SPLevoDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SPLevoDiffFactoryImpl extends EFactoryImpl implements SPLevoDiffFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static SPLevoDiffFactory init() {
        try {
            SPLevoDiffFactory theSPLevoDiffFactory = (SPLevoDiffFactory) EPackage.Registry.INSTANCE
                    .getEFactory(SPLevoDiffPackage.eNS_URI);
            if (theSPLevoDiffFactory != null) {
                return theSPLevoDiffFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new SPLevoDiffFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SPLevoDiffFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case SPLevoDiffPackage.SP_LEVO_DIFF:
            return createSPLevoDiff();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SPLevoDiff createSPLevoDiff() {
        SPLevoDiffImpl spLevoDiff = new SPLevoDiffImpl();
        return spLevoDiff;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SPLevoDiffPackage getSPLevoDiffPackage() {
        return (SPLevoDiffPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static SPLevoDiffPackage getPackage() {
        return SPLevoDiffPackage.eINSTANCE;
    }

} //SPLevoDiffFactoryImpl
