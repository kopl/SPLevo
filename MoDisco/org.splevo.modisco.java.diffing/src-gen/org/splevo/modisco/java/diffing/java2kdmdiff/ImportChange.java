/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff;

import org.eclipse.gmt.modisco.java.ImportDeclaration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Import Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A difference identifying that an import has been changed.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.modisco.java.diffing.java2kdmdiff.ImportChange#getChangedImport <em>Changed Import</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage#getImportChange()
 * @model extendedMetaData="name='ImportDeclarationChange'"
 * @generated
 */
public interface ImportChange extends Java2KDMDiffExtension {

    /**
     * Returns the value of the '<em><b>Changed Import</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Changed Import</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Changed Import</em>' reference.
     * @see #setChangedImport(ImportDeclaration)
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage#getImportChange_ChangedImport()
     * @model required="true"
     * @generated
     */
    ImportDeclaration getChangedImport();

    /**
     * Sets the value of the '{@link org.splevo.modisco.java.diffing.java2kdmdiff.ImportChange#getChangedImport <em>Changed Import</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Changed Import</em>' reference.
     * @see #getChangedImport()
     * @generated
     */
    void setChangedImport(ImportDeclaration value);
} // ImportChange
