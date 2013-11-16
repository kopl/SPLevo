/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage
 * @generated
 */
public interface Java2KDMDiffFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    Java2KDMDiffFactory eINSTANCE = org.splevo.modisco.java.diffing.java2kdmdiff.impl.Java2KDMDiffFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Statement Change</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Statement Change</em>'.
     * @generated
     */
    StatementChange createStatementChange();

    /**
     * Returns a new object of class '<em>Import Change</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Import Change</em>'.
     * @generated
     */
    ImportChange createImportChange();

    /**
     * Returns a new object of class '<em>Class Change</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Class Change</em>'.
     * @generated
     */
    ClassChange createClassChange();

    /**
     * Returns a new object of class '<em>Field Change</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Field Change</em>'.
     * @generated
     */
    FieldChange createFieldChange();

    /**
     * Returns a new object of class '<em>Package Change</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Package Change</em>'.
     * @generated
     */
    PackageChange createPackageChange();

    /**
     * Returns a new object of class '<em>Method Change</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Method Change</em>'.
     * @generated
     */
    MethodChange createMethodChange();

    /**
     * Returns a new object of class '<em>Enum Change</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Enum Change</em>'.
     * @generated
     */
    EnumChange createEnumChange();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    Java2KDMDiffPackage getJava2KDMDiffPackage();

} //Java2KDMDiffFactory
