/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage
 * @generated
 */
public interface Java2KDMDiffFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	Java2KDMDiffFactory eINSTANCE = org.splevo.diffing.emfcompare.java2kdmdiff.impl.Java2KDMDiffFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Statement Change</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Statement Change</em>'.
     * @generated
     */
	StatementChange createStatementChange();

	/**
     * Returns a new object of class '<em>Import Insert</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Import Insert</em>'.
     * @generated
     */
	ImportInsert createImportInsert();

	/**
     * Returns a new object of class '<em>Import Delete</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Import Delete</em>'.
     * @generated
     */
	ImportDelete createImportDelete();

	/**
     * Returns a new object of class '<em>Implements Interface Insert</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Implements Interface Insert</em>'.
     * @generated
     */
    ImplementsInterfaceInsert createImplementsInterfaceInsert();

    /**
     * Returns a new object of class '<em>Implements Interface Delete</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Implements Interface Delete</em>'.
     * @generated
     */
    ImplementsInterfaceDelete createImplementsInterfaceDelete();

    /**
     * Returns a new object of class '<em>Field Insert</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Field Insert</em>'.
     * @generated
     */
    FieldInsert createFieldInsert();

    /**
     * Returns a new object of class '<em>Field Delete</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Field Delete</em>'.
     * @generated
     */
    FieldDelete createFieldDelete();

    /**
     * Returns a new object of class '<em>Class Insert</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Class Insert</em>'.
     * @generated
     */
    ClassInsert createClassInsert();

    /**
     * Returns a new object of class '<em>Class Delete</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Class Delete</em>'.
     * @generated
     */
    ClassDelete createClassDelete();

    /**
     * Returns a new object of class '<em>Package Insert</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Package Insert</em>'.
     * @generated
     */
    PackageInsert createPackageInsert();

    /**
     * Returns a new object of class '<em>Package Delete</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Package Delete</em>'.
     * @generated
     */
    PackageDelete createPackageDelete();

    /**
     * Returns a new object of class '<em>Method Insert</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Method Insert</em>'.
     * @generated
     */
    MethodInsert createMethodInsert();

    /**
     * Returns a new object of class '<em>Method Delete</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Method Delete</em>'.
     * @generated
     */
    MethodDelete createMethodDelete();

    /**
     * Returns a new object of class '<em>Statement Insert</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Statement Insert</em>'.
     * @generated
     */
    StatementInsert createStatementInsert();

    /**
     * Returns a new object of class '<em>Statement Delete</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Statement Delete</em>'.
     * @generated
     */
    StatementDelete createStatementDelete();

    /**
     * Returns a new object of class '<em>Field Declaration Change</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Field Declaration Change</em>'.
     * @generated
     */
    FieldDeclarationChange createFieldDeclarationChange();

    /**
     * Returns a new object of class '<em>Enum Declaration Change</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Enum Declaration Change</em>'.
     * @generated
     */
    EnumDeclarationChange createEnumDeclarationChange();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	Java2KDMDiffPackage getJava2KDMDiffPackage();

} //Java2KDMDiffFactory
