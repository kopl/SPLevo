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

import org.emftext.language.java.containers.CompilationUnit;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Compilation Unit Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A changed comilatio unit representing a physical software artefact.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.jamopp.diffing.jamoppdiff.CompilationUnitChange#getChangedCompilationUnit <em>Changed Compilation Unit</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage#getCompilationUnitChange()
 * @model
 * @generated
 */
public interface CompilationUnitChange extends JaMoPPDiff {
    /**
     * Returns the value of the '<em><b>Changed Compilation Unit</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Changed Compilation Unit</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Changed Compilation Unit</em>' reference.
     * @see #setChangedCompilationUnit(CompilationUnit)
     * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage#getCompilationUnitChange_ChangedCompilationUnit()
     * @model required="true" derived="true"
     * @generated
     */
    CompilationUnit getChangedCompilationUnit();

    /**
     * Sets the value of the '{@link org.splevo.jamopp.diffing.jamoppdiff.CompilationUnitChange#getChangedCompilationUnit <em>Changed Compilation Unit</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Changed Compilation Unit</em>' reference.
     * @see #getChangedCompilationUnit()
     * @generated
     */
    void setChangedCompilationUnit(CompilationUnit value);

} // CompilationUnitChange
