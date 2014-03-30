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
 * A representation of the literals of the enumeration '<em><b>Extendibility</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * The extendibility describes if this variation point is open for extendibility in excess of the product line code base.
 * <!-- end-model-doc -->
 * @see org.splevo.vpm.variability.variabilityPackage#getExtendibility()
 * @model
 * @generated
 */
public enum Extendibility implements Enumerator {
    /**
     * The '<em><b>Closed</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CLOSED_VALUE
     * @generated
     * @ordered
     */
    CLOSED(0, "Closed", "Closed"),

    /**
     * The '<em><b>Open</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #OPEN_VALUE
     * @generated
     * @ordered
     */
    OPEN(1, "Open", "Open");

    /**
     * The '<em><b>Closed</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * All possible variants must be part of the product line code.
     * <!-- end-model-doc -->
     * @see #CLOSED
     * @model name="Closed"
     * @generated
     * @ordered
     */
    public static final int CLOSED_VALUE = 0;

    /**
     * The '<em><b>Open</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * New variants can be added as extensions in excess of the product line code base.
     * <!-- end-model-doc -->
     * @see #OPEN
     * @model name="Open"
     * @generated
     * @ordered
     */
    public static final int OPEN_VALUE = 1;

    /**
     * An array of all the '<em><b>Extendibility</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final Extendibility[] VALUES_ARRAY = new Extendibility[] { CLOSED, OPEN, };

    /**
     * A public read-only list of all the '<em><b>Extendibility</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<Extendibility> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Extendibility</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Extendibility get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            Extendibility result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Extendibility</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Extendibility getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            Extendibility result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Extendibility</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Extendibility get(int value) {
        switch (value) {
        case CLOSED_VALUE:
            return CLOSED;
        case OPEN_VALUE:
            return OPEN;
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
    private Extendibility(int value, String name, String literal) {
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

} //Extendibility
