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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A changed package declaration.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.jamopp.diffing.jamoppdiff.PackageChange#getChangedPackage <em>Changed Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage#getPackageChange()
 * @model
 * @generated
 */
public interface PackageChange extends JaMoPPDiff {
    /**
     * Returns the value of the '<em><b>Changed Package</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Changed Package</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Changed Package</em>' reference.
     * @see #setChangedPackage(org.emftext.language.java.containers.Package)
     * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage#getPackageChange_ChangedPackage()
     * @model required="true" derived="true"
     * @generated
     */
    org.emftext.language.java.containers.Package getChangedPackage();

    /**
     * Sets the value of the '{@link org.splevo.jamopp.diffing.jamoppdiff.PackageChange#getChangedPackage <em>Changed Package</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Changed Package</em>' reference.
     * @see #getChangedPackage()
     * @generated
     */
    void setChangedPackage(org.emftext.language.java.containers.Package value);

} // PackageChange
