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
package org.splevo.vpm.variability;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Binding Time</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Defines the required action to decide for a variant (combination)
 * <!-- end-model-doc -->
 * @see org.splevo.vpm.variability.variabilityPackage#getBindingTime()
 * @model
 * @generated
 */
public enum BindingTime implements Enumerator {
    /**
     * The '<em><b>Compile Time</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #COMPILE_TIME_VALUE
     * @generated
     * @ordered
     */
    COMPILE_TIME(0, "CompileTime", "CompileTime"),
    /**
     * The '<em><b>Load Time</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LOAD_TIME_VALUE
     * @generated
     * @ordered
     */
    LOAD_TIME(3, "LoadTime", "LoadTime"),
    /**
     * The '<em><b>Run Time</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RUN_TIME_VALUE
     * @generated
     * @ordered
     */
    RUN_TIME(4, "RunTime", "RunTime");

    /**
     * The '<em><b>Compile Time</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Source code must modified
     * <!-- end-model-doc -->
     * @see #COMPILE_TIME
     * @model name="CompileTime"
     * @generated
     * @ordered
     */
    public static final int COMPILE_TIME_VALUE = 0;

    /**
     * The '<em><b>Load Time</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Configuration must be changed before application start
     * <!-- end-model-doc -->
     * @see #LOAD_TIME
     * @model name="LoadTime"
     * @generated
     * @ordered
     */
    public static final int LOAD_TIME_VALUE = 3;

    /**
     * The '<em><b>Run Time</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Context / parametrization must be changed before usage
     * <!-- end-model-doc -->
     * @see #RUN_TIME
     * @model name="RunTime"
     * @generated
     * @ordered
     */
    public static final int RUN_TIME_VALUE = 4;

    /**
     * An array of all the '<em><b>Binding Time</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final BindingTime[] VALUES_ARRAY = new BindingTime[] { COMPILE_TIME, LOAD_TIME, RUN_TIME, };

    /**
     * A public read-only list of all the '<em><b>Binding Time</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<BindingTime> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Binding Time</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static BindingTime get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            BindingTime result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Binding Time</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static BindingTime getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            BindingTime result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Binding Time</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static BindingTime get(int value) {
        switch (value) {
        case COMPILE_TIME_VALUE:
            return COMPILE_TIME;
        case LOAD_TIME_VALUE:
            return LOAD_TIME;
        case RUN_TIME_VALUE:
            return RUN_TIME;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private BindingTime(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLiteral() {
        return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string representation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }

} //BindingTime
