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
package org.splevo.jamopp.diffing.jamoppdiff.util;

import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.splevo.jamopp.diffing.jamoppdiff.ClassChange;
import org.splevo.jamopp.diffing.jamoppdiff.CompilationUnitChange;
import org.splevo.jamopp.diffing.jamoppdiff.EnumChange;
import org.splevo.jamopp.diffing.jamoppdiff.FieldChange;
import org.splevo.jamopp.diffing.jamoppdiff.ImportChange;
import org.splevo.jamopp.diffing.jamoppdiff.InterfaceChange;
import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiff;
import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage;
import org.splevo.jamopp.diffing.jamoppdiff.MethodChange;
import org.splevo.jamopp.diffing.jamoppdiff.PackageChange;
import org.splevo.jamopp.diffing.jamoppdiff.StatementChange;

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
 * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage
 * @generated
 */
public class JaMoPPDiffSwitch<T> extends Switch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static JaMoPPDiffPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public JaMoPPDiffSwitch() {
        if (modelPackage == null) {
            modelPackage = JaMoPPDiffPackage.eINSTANCE;
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
        case JaMoPPDiffPackage.JA_MO_PP_DIFF: {
            JaMoPPDiff jaMoPPDiff = (JaMoPPDiff) theEObject;
            T result = caseJaMoPPDiff(jaMoPPDiff);
            if (result == null)
                result = caseDiff(jaMoPPDiff);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case JaMoPPDiffPackage.STATEMENT_CHANGE: {
            StatementChange statementChange = (StatementChange) theEObject;
            T result = caseStatementChange(statementChange);
            if (result == null)
                result = caseJaMoPPDiff(statementChange);
            if (result == null)
                result = caseDiff(statementChange);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case JaMoPPDiffPackage.IMPORT_CHANGE: {
            ImportChange importChange = (ImportChange) theEObject;
            T result = caseImportChange(importChange);
            if (result == null)
                result = caseJaMoPPDiff(importChange);
            if (result == null)
                result = caseDiff(importChange);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case JaMoPPDiffPackage.CLASS_CHANGE: {
            ClassChange classChange = (ClassChange) theEObject;
            T result = caseClassChange(classChange);
            if (result == null)
                result = caseJaMoPPDiff(classChange);
            if (result == null)
                result = caseDiff(classChange);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case JaMoPPDiffPackage.FIELD_CHANGE: {
            FieldChange fieldChange = (FieldChange) theEObject;
            T result = caseFieldChange(fieldChange);
            if (result == null)
                result = caseJaMoPPDiff(fieldChange);
            if (result == null)
                result = caseDiff(fieldChange);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case JaMoPPDiffPackage.PACKAGE_CHANGE: {
            PackageChange packageChange = (PackageChange) theEObject;
            T result = casePackageChange(packageChange);
            if (result == null)
                result = caseJaMoPPDiff(packageChange);
            if (result == null)
                result = caseDiff(packageChange);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case JaMoPPDiffPackage.METHOD_CHANGE: {
            MethodChange methodChange = (MethodChange) theEObject;
            T result = caseMethodChange(methodChange);
            if (result == null)
                result = caseJaMoPPDiff(methodChange);
            if (result == null)
                result = caseDiff(methodChange);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case JaMoPPDiffPackage.ENUM_CHANGE: {
            EnumChange enumChange = (EnumChange) theEObject;
            T result = caseEnumChange(enumChange);
            if (result == null)
                result = caseJaMoPPDiff(enumChange);
            if (result == null)
                result = caseDiff(enumChange);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case JaMoPPDiffPackage.COMPILATION_UNIT_CHANGE: {
            CompilationUnitChange compilationUnitChange = (CompilationUnitChange) theEObject;
            T result = caseCompilationUnitChange(compilationUnitChange);
            if (result == null)
                result = caseJaMoPPDiff(compilationUnitChange);
            if (result == null)
                result = caseDiff(compilationUnitChange);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case JaMoPPDiffPackage.INTERFACE_CHANGE: {
            InterfaceChange interfaceChange = (InterfaceChange) theEObject;
            T result = caseInterfaceChange(interfaceChange);
            if (result == null)
                result = caseJaMoPPDiff(interfaceChange);
            if (result == null)
                result = caseDiff(interfaceChange);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        default:
            return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Ja Mo PP Diff</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Ja Mo PP Diff</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseJaMoPPDiff(JaMoPPDiff object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Compilation Unit Change</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Compilation Unit Change</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCompilationUnitChange(CompilationUnitChange object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Interface Change</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Interface Change</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInterfaceChange(InterfaceChange object) {
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

} //JaMoPPDiffSwitch
