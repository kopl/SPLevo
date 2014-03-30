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
     * The '<em><b>Implementation</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #IMPLEMENTATION_VALUE
     * @generated
     * @ordered
     */
    IMPLEMENTATION(0, "Implementation", "Implementation"),

    /**
     * The '<em><b>Compilation</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #COMPILATION_VALUE
     * @generated
     * @ordered
     */
    COMPILATION(1, "Compilation", "Compilation"),

    /**
     * The '<em><b>Linking</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LINKING_VALUE
     * @generated
     * @ordered
     */
    LINKING(2, "Linking", "Linking"),

    /**
     * The '<em><b>Loading</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LOADING_VALUE
     * @generated
     * @ordered
     */
    LOADING(3, "Loading", "Loading"),

    /**
     * The '<em><b>Runtime</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RUNTIME_VALUE
     * @generated
     * @ordered
     */
    RUNTIME(4, "Runtime", "Runtime");

    /**
     * The '<em><b>Implementation</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Source code must modified
     * <!-- end-model-doc -->
     * @see #IMPLEMENTATION
     * @model name="Implementation"
     * @generated
     * @ordered
     */
    public static final int IMPLEMENTATION_VALUE = 0;

    /**
     * The '<em><b>Compilation</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Compilation process must be influenced
     * <!-- end-model-doc -->
     * @see #COMPILATION
     * @model name="Compilation"
     * @generated
     * @ordered
     */
    public static final int COMPILATION_VALUE = 1;

    /**
     * The '<em><b>Linking</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Composition / assembly process must be influenced
     * <!-- end-model-doc -->
     * @see #LINKING
     * @model name="Linking"
     * @generated
     * @ordered
     */
    public static final int LINKING_VALUE = 2;

    /**
     * The '<em><b>Loading</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Configuration must be changed before application start
     * <!-- end-model-doc -->
     * @see #LOADING
     * @model name="Loading"
     * @generated
     * @ordered
     */
    public static final int LOADING_VALUE = 3;

    /**
     * The '<em><b>Runtime</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Context / parametrization must be changed before usage
     * <!-- end-model-doc -->
     * @see #RUNTIME
     * @model name="Runtime"
     * @generated
     * @ordered
     */
    public static final int RUNTIME_VALUE = 4;

    /**
     * An array of all the '<em><b>Binding Time</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final BindingTime[] VALUES_ARRAY = new BindingTime[] { IMPLEMENTATION, COMPILATION, LINKING,
            LOADING, RUNTIME, };

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
        case IMPLEMENTATION_VALUE:
            return IMPLEMENTATION;
        case COMPILATION_VALUE:
            return COMPILATION;
        case LINKING_VALUE:
            return LINKING;
        case LOADING_VALUE:
            return LOADING;
        case RUNTIME_VALUE:
            return RUNTIME;
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
