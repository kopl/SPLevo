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
 * A representation of the literals of the enumeration '<em><b>Variability Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Characteristic how many variants can or must be selected for a variation point as defined in
 * "A taxonomy of variability techniques" by Svahnberg, Gurp and Bosch available at 
 * <!-- end-model-doc -->
 * @see org.splevo.vpm.variability.variabilityPackage#getVariabilityType()
 * @model
 * @generated
 */
public enum VariabilityType implements Enumerator {
    /**
     * The '<em><b>XOR</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #XOR_VALUE
     * @generated
     * @ordered
     */
    XOR(0, "XOR", "XOR"),

    /**
     * The '<em><b>OR</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #OR_VALUE
     * @generated
     * @ordered
     */
    OR(1, "OR", "OR"),

    /**
     * The '<em><b>OPTXOR</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #OPTXOR_VALUE
     * @generated
     * @ordered
     */
    OPTXOR(2, "OPTXOR", "OPTXOR"),

    /**
     * The '<em><b>OPTOR</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #OPTOR_VALUE
     * @generated
     * @ordered
     */
    OPTOR(3, "OPTOR", "OPTOR");

    /**
     * The '<em><b>XOR</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Exactly one of the available variants must be selected.
     * <!-- end-model-doc -->
     * @see #XOR
     * @model
     * @generated
     * @ordered
     */
    public static final int XOR_VALUE = 0;

    /**
     * The '<em><b>OR</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * One or more of the available variants must be selected.
     * <!-- end-model-doc -->
     * @see #OR
     * @model
     * @generated
     * @ordered
     */
    public static final int OR_VALUE = 1;

    /**
     * The '<em><b>OPTXOR</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * None or one of the available variants must be selected.
     * <!-- end-model-doc -->
     * @see #OPTXOR
     * @model
     * @generated
     * @ordered
     */
    public static final int OPTXOR_VALUE = 2;

    /**
     * The '<em><b>OPTOR</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * None, one, or more of the available variants must be selected.
     * <!-- end-model-doc -->
     * @see #OPTOR
     * @model
     * @generated
     * @ordered
     */
    public static final int OPTOR_VALUE = 3;

    /**
     * An array of all the '<em><b>Variability Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final VariabilityType[] VALUES_ARRAY = new VariabilityType[] { XOR, OR, OPTXOR, OPTOR, };

    /**
     * A public read-only list of all the '<em><b>Variability Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<VariabilityType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Variability Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static VariabilityType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            VariabilityType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Variability Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static VariabilityType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            VariabilityType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Variability Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static VariabilityType get(int value) {
        switch (value) {
        case XOR_VALUE:
            return XOR;
        case OR_VALUE:
            return OR;
        case OPTXOR_VALUE:
            return OPTXOR;
        case OPTOR_VALUE:
            return OPTOR;
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
    private VariabilityType(int value, String name, String literal) {
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

} //VariabilityType
