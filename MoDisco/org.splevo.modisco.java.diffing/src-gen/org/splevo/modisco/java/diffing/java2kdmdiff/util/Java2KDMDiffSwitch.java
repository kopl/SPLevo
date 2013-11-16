/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff.util;

import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.splevo.modisco.java.diffing.java2kdmdiff.ClassChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.EnumChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.FieldChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImportChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffExtension;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;
import org.splevo.modisco.java.diffing.java2kdmdiff.MethodChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.PackageChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.StatementChange;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage
 * @generated
 */
public class Java2KDMDiffSwitch<T> extends Switch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static Java2KDMDiffPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Java2KDMDiffSwitch() {
        if (modelPackage == null) {
            modelPackage = Java2KDMDiffPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @parameter ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
        case Java2KDMDiffPackage.JAVA2_KDM_DIFF_EXTENSION: {
            Java2KDMDiffExtension java2KDMDiffExtension = (Java2KDMDiffExtension) theEObject;
            T result = caseJava2KDMDiffExtension(java2KDMDiffExtension);
            if (result == null)
                result = caseDiff(java2KDMDiffExtension);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case Java2KDMDiffPackage.STATEMENT_CHANGE: {
            StatementChange statementChange = (StatementChange) theEObject;
            T result = caseStatementChange(statementChange);
            if (result == null)
                result = caseJava2KDMDiffExtension(statementChange);
            if (result == null)
                result = caseDiff(statementChange);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case Java2KDMDiffPackage.IMPORT_CHANGE: {
            ImportChange importChange = (ImportChange) theEObject;
            T result = caseImportChange(importChange);
            if (result == null)
                result = caseJava2KDMDiffExtension(importChange);
            if (result == null)
                result = caseDiff(importChange);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case Java2KDMDiffPackage.CLASS_CHANGE: {
            ClassChange classChange = (ClassChange) theEObject;
            T result = caseClassChange(classChange);
            if (result == null)
                result = caseJava2KDMDiffExtension(classChange);
            if (result == null)
                result = caseDiff(classChange);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case Java2KDMDiffPackage.FIELD_CHANGE: {
            FieldChange fieldChange = (FieldChange) theEObject;
            T result = caseFieldChange(fieldChange);
            if (result == null)
                result = caseJava2KDMDiffExtension(fieldChange);
            if (result == null)
                result = caseDiff(fieldChange);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case Java2KDMDiffPackage.PACKAGE_CHANGE: {
            PackageChange packageChange = (PackageChange) theEObject;
            T result = casePackageChange(packageChange);
            if (result == null)
                result = caseJava2KDMDiffExtension(packageChange);
            if (result == null)
                result = caseDiff(packageChange);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case Java2KDMDiffPackage.METHOD_CHANGE: {
            MethodChange methodChange = (MethodChange) theEObject;
            T result = caseMethodChange(methodChange);
            if (result == null)
                result = caseJava2KDMDiffExtension(methodChange);
            if (result == null)
                result = caseDiff(methodChange);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case Java2KDMDiffPackage.ENUM_CHANGE: {
            EnumChange enumChange = (EnumChange) theEObject;
            T result = caseEnumChange(enumChange);
            if (result == null)
                result = caseJava2KDMDiffExtension(enumChange);
            if (result == null)
                result = caseDiff(enumChange);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        default:
            return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Extension</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Extension</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseJava2KDMDiffExtension(Java2KDMDiffExtension object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Statement Change</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Statement Change</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStatementChange(StatementChange object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Import Change</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Import Change</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseImportChange(ImportChange object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Class Change</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Class Change</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseClassChange(ClassChange object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Field Change</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Field Change</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFieldChange(FieldChange object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Package Change</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Package Change</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePackageChange(PackageChange object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Method Change</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Method Change</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMethodChange(MethodChange object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Enum Change</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Enum Change</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEnumChange(EnumChange object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Diff</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Diff</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDiff(Diff object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase(EObject object) {
        return null;
    }

} //Java2KDMDiffSwitch
