/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff.util;

import org.eclipse.emf.compare.diff.metamodel.AbstractDiffExtension;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.splevo.diffing.emfcompare.java2kdmdiff.*;

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
 * @see org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage
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
                Java2KDMDiffExtension java2KDMDiffExtension = (Java2KDMDiffExtension)theEObject;
                T result = caseJava2KDMDiffExtension(java2KDMDiffExtension);
                if (result == null) result = caseAbstractDiffExtension(java2KDMDiffExtension);
                if (result == null) result = caseDiffElement(java2KDMDiffExtension);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Java2KDMDiffPackage.STATEMENT_CHANGE: {
                StatementChange statementChange = (StatementChange)theEObject;
                T result = caseStatementChange(statementChange);
                if (result == null) result = caseJava2KDMDiffExtension(statementChange);
                if (result == null) result = caseAbstractDiffExtension(statementChange);
                if (result == null) result = caseDiffElement(statementChange);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Java2KDMDiffPackage.IMPORT_DECLARATION_CHANGE: {
                ImportDeclarationChange importDeclarationChange = (ImportDeclarationChange)theEObject;
                T result = caseImportDeclarationChange(importDeclarationChange);
                if (result == null) result = caseJava2KDMDiffExtension(importDeclarationChange);
                if (result == null) result = caseAbstractDiffExtension(importDeclarationChange);
                if (result == null) result = caseDiffElement(importDeclarationChange);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Java2KDMDiffPackage.IMPORT_INSERT: {
                ImportInsert importInsert = (ImportInsert)theEObject;
                T result = caseImportInsert(importInsert);
                if (result == null) result = caseImportDeclarationChange(importInsert);
                if (result == null) result = caseJava2KDMDiffExtension(importInsert);
                if (result == null) result = caseAbstractDiffExtension(importInsert);
                if (result == null) result = caseDiffElement(importInsert);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Java2KDMDiffPackage.IMPORT_DELETE: {
                ImportDelete importDelete = (ImportDelete)theEObject;
                T result = caseImportDelete(importDelete);
                if (result == null) result = caseImportDeclarationChange(importDelete);
                if (result == null) result = caseJava2KDMDiffExtension(importDelete);
                if (result == null) result = caseAbstractDiffExtension(importDelete);
                if (result == null) result = caseDiffElement(importDelete);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Java2KDMDiffPackage.CLASS_CHANGE: {
                ClassChange classChange = (ClassChange)theEObject;
                T result = caseClassChange(classChange);
                if (result == null) result = caseJava2KDMDiffExtension(classChange);
                if (result == null) result = caseAbstractDiffExtension(classChange);
                if (result == null) result = caseDiffElement(classChange);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Java2KDMDiffPackage.IMPLEMENTS_INTERFACE_INSERT: {
                ImplementsInterfaceInsert implementsInterfaceInsert = (ImplementsInterfaceInsert)theEObject;
                T result = caseImplementsInterfaceInsert(implementsInterfaceInsert);
                if (result == null) result = caseClassChange(implementsInterfaceInsert);
                if (result == null) result = caseJava2KDMDiffExtension(implementsInterfaceInsert);
                if (result == null) result = caseAbstractDiffExtension(implementsInterfaceInsert);
                if (result == null) result = caseDiffElement(implementsInterfaceInsert);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Java2KDMDiffPackage.IMPLEMENTS_INTERFACE_DELETE: {
                ImplementsInterfaceDelete implementsInterfaceDelete = (ImplementsInterfaceDelete)theEObject;
                T result = caseImplementsInterfaceDelete(implementsInterfaceDelete);
                if (result == null) result = caseClassChange(implementsInterfaceDelete);
                if (result == null) result = caseJava2KDMDiffExtension(implementsInterfaceDelete);
                if (result == null) result = caseAbstractDiffExtension(implementsInterfaceDelete);
                if (result == null) result = caseDiffElement(implementsInterfaceDelete);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Java2KDMDiffPackage.FIELD_CHANGE: {
                FieldChange fieldChange = (FieldChange)theEObject;
                T result = caseFieldChange(fieldChange);
                if (result == null) result = caseJava2KDMDiffExtension(fieldChange);
                if (result == null) result = caseAbstractDiffExtension(fieldChange);
                if (result == null) result = caseDiffElement(fieldChange);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Java2KDMDiffPackage.FIELD_INSERT: {
                FieldInsert fieldInsert = (FieldInsert)theEObject;
                T result = caseFieldInsert(fieldInsert);
                if (result == null) result = caseFieldChange(fieldInsert);
                if (result == null) result = caseJava2KDMDiffExtension(fieldInsert);
                if (result == null) result = caseAbstractDiffExtension(fieldInsert);
                if (result == null) result = caseDiffElement(fieldInsert);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Java2KDMDiffPackage.FIELD_DELETE: {
                FieldDelete fieldDelete = (FieldDelete)theEObject;
                T result = caseFieldDelete(fieldDelete);
                if (result == null) result = caseFieldChange(fieldDelete);
                if (result == null) result = caseJava2KDMDiffExtension(fieldDelete);
                if (result == null) result = caseAbstractDiffExtension(fieldDelete);
                if (result == null) result = caseDiffElement(fieldDelete);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Java2KDMDiffPackage.CLASS_INSERT: {
                ClassInsert classInsert = (ClassInsert)theEObject;
                T result = caseClassInsert(classInsert);
                if (result == null) result = caseClassChange(classInsert);
                if (result == null) result = caseJava2KDMDiffExtension(classInsert);
                if (result == null) result = caseAbstractDiffExtension(classInsert);
                if (result == null) result = caseDiffElement(classInsert);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Java2KDMDiffPackage.CLASS_DELETE: {
                ClassDelete classDelete = (ClassDelete)theEObject;
                T result = caseClassDelete(classDelete);
                if (result == null) result = caseClassChange(classDelete);
                if (result == null) result = caseJava2KDMDiffExtension(classDelete);
                if (result == null) result = caseAbstractDiffExtension(classDelete);
                if (result == null) result = caseDiffElement(classDelete);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
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
     * Returns the result of interpreting the object as an instance of '<em>Import Declaration Change</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Import Declaration Change</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseImportDeclarationChange(ImportDeclarationChange object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Import Insert</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Import Insert</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseImportInsert(ImportInsert object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Import Delete</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Import Delete</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseImportDelete(ImportDelete object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Implements Interface Insert</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Implements Interface Insert</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseImplementsInterfaceInsert(ImplementsInterfaceInsert object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Implements Interface Delete</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Implements Interface Delete</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseImplementsInterfaceDelete(ImplementsInterfaceDelete object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Field Insert</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Field Insert</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFieldInsert(FieldInsert object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Field Delete</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Field Delete</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFieldDelete(FieldDelete object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Class Insert</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Class Insert</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseClassInsert(ClassInsert object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Class Delete</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Class Delete</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseClassDelete(ClassDelete object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Diff Extension</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Diff Extension</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseAbstractDiffExtension(AbstractDiffExtension object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseDiffElement(DiffElement object) {
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
