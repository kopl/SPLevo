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
package org.splevo.jamopp.diffing.jamoppdiff;

import org.emftext.language.java.members.Method;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A changed method declaration.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.jamopp.diffing.jamoppdiff.MethodChange#getChangedMethod <em>Changed Method</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage#getMethodChange()
 * @model
 * @generated
 */
public interface MethodChange extends JaMoPPDiff {
    /**
     * Returns the value of the '<em><b>Changed Method</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Changed Method</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Changed Method</em>' reference.
     * @see #setChangedMethod(Method)
     * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage#getMethodChange_ChangedMethod()
     * @model required="true"
     * @generated
     */
    Method getChangedMethod();

    /**
     * Sets the value of the '{@link org.splevo.jamopp.diffing.jamoppdiff.MethodChange#getChangedMethod <em>Changed Method</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Changed Method</em>' reference.
     * @see #getChangedMethod()
     * @generated
     */
    void setChangedMethod(Method value);

} // MethodChange
