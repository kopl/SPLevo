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

import org.emftext.language.java.members.Constructor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constructor Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A changed constructor declaration.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.jamopp.diffing.jamoppdiff.ConstructorChange#getChangedConstructor <em>Changed Constructor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage#getConstructorChange()
 * @model
 * @generated
 */
public interface ConstructorChange extends JaMoPPDiff {
    /**
     * Returns the value of the '<em><b>Changed Constructor</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Changed Constructor</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Changed Constructor</em>' reference.
     * @see #setChangedConstructor(Constructor)
     * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage#getConstructorChange_ChangedConstructor()
     * @model required="true" derived="true"
     * @generated
     */
    Constructor getChangedConstructor();

    /**
     * Sets the value of the '{@link org.splevo.jamopp.diffing.jamoppdiff.ConstructorChange#getChangedConstructor <em>Changed Constructor</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Changed Constructor</em>' reference.
     * @see #getChangedConstructor()
     * @generated
     */
    void setChangedConstructor(Constructor value);

} // ConstructorChange
