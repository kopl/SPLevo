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

import org.emftext.language.java.types.TypeReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extends Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Change describing a changed extends references.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.jamopp.diffing.jamoppdiff.ExtendsChange#getChangedReference <em>Changed Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage#getExtendsChange()
 * @model
 * @generated
 */
public interface ExtendsChange extends JaMoPPDiff {
    /**
     * Returns the value of the '<em><b>Changed Reference</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The changed reference.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Changed Reference</em>' reference.
     * @see #setChangedReference(TypeReference)
     * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage#getExtendsChange_ChangedReference()
     * @model derived="true"
     * @generated
     */
    TypeReference getChangedReference();

    /**
     * Sets the value of the '{@link org.splevo.jamopp.diffing.jamoppdiff.ExtendsChange#getChangedReference <em>Changed Reference</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Changed Reference</em>' reference.
     * @see #getChangedReference()
     * @generated
     */
    void setChangedReference(TypeReference value);

} // ExtendsChange
