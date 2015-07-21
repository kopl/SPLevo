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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>VPM Model Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.project.VPMModelReference#getPath <em>Path</em>}</li>
 *   <li>{@link org.splevo.project.VPMModelReference#isRefactoringStarted <em>Refactoring Started</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.project.ProjectPackage#getVPMModelReference()
 * @model
 * @generated
 */
public interface VPMModelReference extends EObject {
    /**
     * Returns the value of the '<em><b>Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Path</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Path</em>' attribute.
     * @see #setPath(String)
     * @see org.splevo.project.ProjectPackage#getVPMModelReference_Path()
     * @model required="true"
     * @generated
     */
    String getPath();

    /**
     * Sets the value of the '{@link org.splevo.project.VPMModelReference#getPath <em>Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Path</em>' attribute.
     * @see #getPath()
     * @generated
     */
    void setPath(String value);

    /**
     * Returns the value of the '<em><b>Refactoring Started</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Refactoring Started</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Refactoring Started</em>' attribute.
     * @see #setRefactoringStarted(boolean)
     * @see org.splevo.project.ProjectPackage#getVPMModelReference_RefactoringStarted()
     * @model default="false" required="true"
     * @generated
     */
    boolean isRefactoringStarted();

    /**
     * Sets the value of the '{@link org.splevo.project.VPMModelReference#isRefactoringStarted <em>Refactoring Started</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Refactoring Started</em>' attribute.
     * @see #isRefactoringStarted()
     * @generated
     */
    void setRefactoringStarted(boolean value);

} // VPMModelReference
