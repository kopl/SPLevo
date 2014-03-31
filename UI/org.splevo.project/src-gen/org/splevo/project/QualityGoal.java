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
package org.splevo.project;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Quality Goal</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.splevo.project.ProjectPackage#getQualityGoal()
 * @model
 * @generated
 */
public enum QualityGoal implements Enumerator {
    /**
     * The '<em><b>REDUCE REDUNDANCY</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #REDUCE_REDUNDANCY_VALUE
     * @generated
     * @ordered
     */
    REDUCE_REDUNDANCY(0, "REDUCE_REDUNDANCY", "REDUCE_REDUNDANCY"),

    /**
     * The '<em><b>REDUCE COMPLEXITY</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #REDUCE_COMPLEXITY_VALUE
     * @generated
     * @ordered
     */
    REDUCE_COMPLEXITY(1, "REDUCE_COMPLEXITY", "REDUCE_COMPLEXITY");

    /**
     * The '<em><b>REDUCE REDUNDANCY</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>REDUCE REDUNDANCY</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #REDUCE_REDUNDANCY
     * @model
     * @generated
     * @ordered
     */
    public static final int REDUCE_REDUNDANCY_VALUE = 0;

    /**
     * The '<em><b>REDUCE COMPLEXITY</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>REDUCE COMPLEXITY</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #REDUCE_COMPLEXITY
     * @model
     * @generated
     * @ordered
     */
    public static final int REDUCE_COMPLEXITY_VALUE = 1;

    /**
     * An array of all the '<em><b>Quality Goal</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final QualityGoal[] VALUES_ARRAY =
        new QualityGoal[] {
            REDUCE_REDUNDANCY,
            REDUCE_COMPLEXITY,
        };

    /**
     * A public read-only list of all the '<em><b>Quality Goal</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<QualityGoal> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Quality Goal</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static QualityGoal get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            QualityGoal result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Quality Goal</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static QualityGoal getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            QualityGoal result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Quality Goal</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static QualityGoal get(int value) {
        switch (value) {
            case REDUCE_REDUNDANCY_VALUE: return REDUCE_REDUNDANCY;
            case REDUCE_COMPLEXITY_VALUE: return REDUCE_COMPLEXITY;
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
    private QualityGoal(int value, String name, String literal) {
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
    
} //QualityGoal
