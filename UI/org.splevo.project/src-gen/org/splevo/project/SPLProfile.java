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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SPL Profile</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.project.SPLProfile#getRecommendedRefactorerIds <em>Recommended Refactorer Ids</em>}</li>
 *   <li>{@link org.splevo.project.SPLProfile#getQualityGoals <em>Quality Goals</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.project.ProjectPackage#getSPLProfile()
 * @model
 * @generated
 */
public interface SPLProfile extends EObject {
    /**
     * Returns the value of the '<em><b>Recommended Refactorer Ids</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Recommended Refactorer Ids</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Recommended Refactorer Ids</em>' attribute list.
     * @see org.splevo.project.ProjectPackage#getSPLProfile_RecommendedRefactorerIds()
     * @model
     * @generated
     */
    EList<String> getRecommendedRefactorerIds();

    /**
     * Returns the value of the '<em><b>Quality Goals</b></em>' attribute list.
     * The list contents are of type {@link org.splevo.project.QualityGoal}.
     * The literals are from the enumeration {@link org.splevo.project.QualityGoal}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Quality Goals</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Quality Goals</em>' attribute list.
     * @see org.splevo.project.QualityGoal
     * @see org.splevo.project.ProjectPackage#getSPLProfile_QualityGoals()
     * @model
     * @generated
     */
    EList<QualityGoal> getQualityGoals();

} // SPLProfile
