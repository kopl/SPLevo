/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff.util;

import org.eclipse.emf.compare.diff.metamodel.AbstractDiffExtension;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.splevo.diffing.emfcompare.kdm2javadiff.*;

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
 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage
 * @generated
 */
public class KDM2JavaDiffSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static KDM2JavaDiffPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KDM2JavaDiffSwitch() {
		if (modelPackage == null) {
			modelPackage = KDM2JavaDiffPackage.eINSTANCE;
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
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION: {
				KDM2JavaDiffExtension kdm2JavaDiffExtension = (KDM2JavaDiffExtension)theEObject;
				T result = caseKDM2JavaDiffExtension(kdm2JavaDiffExtension);
				if (result == null) result = caseAbstractDiffExtension(kdm2JavaDiffExtension);
				if (result == null) result = caseDiffElement(kdm2JavaDiffExtension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case KDM2JavaDiffPackage.STATEMENT_CHANGE: {
				StatementChange statementChange = (StatementChange)theEObject;
				T result = caseStatementChange(statementChange);
				if (result == null) result = caseKDM2JavaDiffExtension(statementChange);
				if (result == null) result = caseAbstractDiffExtension(statementChange);
				if (result == null) result = caseDiffElement(statementChange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case KDM2JavaDiffPackage.STATEMENT_ORDER_CHANGE: {
				StatementOrderChange statementOrderChange = (StatementOrderChange)theEObject;
				T result = caseStatementOrderChange(statementOrderChange);
				if (result == null) result = caseStatementChange(statementOrderChange);
				if (result == null) result = caseKDM2JavaDiffExtension(statementOrderChange);
				if (result == null) result = caseAbstractDiffExtension(statementOrderChange);
				if (result == null) result = caseDiffElement(statementOrderChange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case KDM2JavaDiffPackage.STATEMENT_INSERT: {
				StatementInsert statementInsert = (StatementInsert)theEObject;
				T result = caseStatementInsert(statementInsert);
				if (result == null) result = caseStatementChange(statementInsert);
				if (result == null) result = caseKDM2JavaDiffExtension(statementInsert);
				if (result == null) result = caseAbstractDiffExtension(statementInsert);
				if (result == null) result = caseDiffElement(statementInsert);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case KDM2JavaDiffPackage.STATEMENT_DELETE: {
				StatementDelete statementDelete = (StatementDelete)theEObject;
				T result = caseStatementDelete(statementDelete);
				if (result == null) result = caseStatementChange(statementDelete);
				if (result == null) result = caseKDM2JavaDiffExtension(statementDelete);
				if (result == null) result = caseAbstractDiffExtension(statementDelete);
				if (result == null) result = caseDiffElement(statementDelete);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case KDM2JavaDiffPackage.STATEMENT_MOVE: {
				StatementMove statementMove = (StatementMove)theEObject;
				T result = caseStatementMove(statementMove);
				if (result == null) result = caseStatementChange(statementMove);
				if (result == null) result = caseKDM2JavaDiffExtension(statementMove);
				if (result == null) result = caseAbstractDiffExtension(statementMove);
				if (result == null) result = caseDiffElement(statementMove);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case KDM2JavaDiffPackage.CLASS_DECLARATION_CHANGE: {
				ClassDeclarationChange classDeclarationChange = (ClassDeclarationChange)theEObject;
				T result = caseClassDeclarationChange(classDeclarationChange);
				if (result == null) result = caseKDM2JavaDiffExtension(classDeclarationChange);
				if (result == null) result = caseAbstractDiffExtension(classDeclarationChange);
				if (result == null) result = caseDiffElement(classDeclarationChange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case KDM2JavaDiffPackage.CLASS_INSERT: {
				ClassInsert classInsert = (ClassInsert)theEObject;
				T result = caseClassInsert(classInsert);
				if (result == null) result = caseClassDeclarationChange(classInsert);
				if (result == null) result = caseKDM2JavaDiffExtension(classInsert);
				if (result == null) result = caseAbstractDiffExtension(classInsert);
				if (result == null) result = caseDiffElement(classInsert);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case KDM2JavaDiffPackage.CLASS_DELETE: {
				ClassDelete classDelete = (ClassDelete)theEObject;
				T result = caseClassDelete(classDelete);
				if (result == null) result = caseClassDeclarationChange(classDelete);
				if (result == null) result = caseKDM2JavaDiffExtension(classDelete);
				if (result == null) result = caseAbstractDiffExtension(classDelete);
				if (result == null) result = caseDiffElement(classDelete);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE: {
				ClassModifierChange classModifierChange = (ClassModifierChange)theEObject;
				T result = caseClassModifierChange(classModifierChange);
				if (result == null) result = caseClassDeclarationChange(classModifierChange);
				if (result == null) result = caseKDM2JavaDiffExtension(classModifierChange);
				if (result == null) result = caseAbstractDiffExtension(classModifierChange);
				if (result == null) result = caseDiffElement(classModifierChange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case KDM2JavaDiffPackage.IMPORT_DECLARATION_CHANGE: {
				ImportDeclarationChange importDeclarationChange = (ImportDeclarationChange)theEObject;
				T result = caseImportDeclarationChange(importDeclarationChange);
				if (result == null) result = caseKDM2JavaDiffExtension(importDeclarationChange);
				if (result == null) result = caseAbstractDiffExtension(importDeclarationChange);
				if (result == null) result = caseDiffElement(importDeclarationChange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case KDM2JavaDiffPackage.IMPORT_INSERT: {
				ImportInsert importInsert = (ImportInsert)theEObject;
				T result = caseImportInsert(importInsert);
				if (result == null) result = caseImportDeclarationChange(importInsert);
				if (result == null) result = caseKDM2JavaDiffExtension(importInsert);
				if (result == null) result = caseAbstractDiffExtension(importInsert);
				if (result == null) result = caseDiffElement(importInsert);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case KDM2JavaDiffPackage.IMPORT_DELETE: {
				ImportDelete importDelete = (ImportDelete)theEObject;
				T result = caseImportDelete(importDelete);
				if (result == null) result = caseImportDeclarationChange(importDelete);
				if (result == null) result = caseKDM2JavaDiffExtension(importDelete);
				if (result == null) result = caseAbstractDiffExtension(importDelete);
				if (result == null) result = caseDiffElement(importDelete);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case KDM2JavaDiffPackage.METHOD_DECLARATION_CHANGE: {
				MethodDeclarationChange methodDeclarationChange = (MethodDeclarationChange)theEObject;
				T result = caseMethodDeclarationChange(methodDeclarationChange);
				if (result == null) result = caseKDM2JavaDiffExtension(methodDeclarationChange);
				if (result == null) result = caseAbstractDiffExtension(methodDeclarationChange);
				if (result == null) result = caseDiffElement(methodDeclarationChange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case KDM2JavaDiffPackage.CLASS_CHANGE: {
				ClassChange classChange = (ClassChange)theEObject;
				T result = caseClassChange(classChange);
				if (result == null) result = caseDiffGroup(classChange);
				if (result == null) result = caseDiffElement(classChange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case KDM2JavaDiffPackage.METHOD_CHANGE: {
				MethodChange methodChange = (MethodChange)theEObject;
				T result = caseMethodChange(methodChange);
				if (result == null) result = caseDiffGroup(methodChange);
				if (result == null) result = caseDiffElement(methodChange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case KDM2JavaDiffPackage.METHOD_MODIFIER_CHANGE: {
				MethodModifierChange methodModifierChange = (MethodModifierChange)theEObject;
				T result = caseMethodModifierChange(methodModifierChange);
				if (result == null) result = caseMethodDeclarationChange(methodModifierChange);
				if (result == null) result = caseKDM2JavaDiffExtension(methodModifierChange);
				if (result == null) result = caseAbstractDiffExtension(methodModifierChange);
				if (result == null) result = caseDiffElement(methodModifierChange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case KDM2JavaDiffPackage.RETURN_TYPE_CHANGE: {
				ReturnTypeChange returnTypeChange = (ReturnTypeChange)theEObject;
				T result = caseReturnTypeChange(returnTypeChange);
				if (result == null) result = caseMethodDeclarationChange(returnTypeChange);
				if (result == null) result = caseKDM2JavaDiffExtension(returnTypeChange);
				if (result == null) result = caseAbstractDiffExtension(returnTypeChange);
				if (result == null) result = caseDiffElement(returnTypeChange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case KDM2JavaDiffPackage.METHOD_PARAMETER_CHANGE: {
				MethodParameterChange methodParameterChange = (MethodParameterChange)theEObject;
				T result = caseMethodParameterChange(methodParameterChange);
				if (result == null) result = caseMethodDeclarationChange(methodParameterChange);
				if (result == null) result = caseKDM2JavaDiffExtension(methodParameterChange);
				if (result == null) result = caseAbstractDiffExtension(methodParameterChange);
				if (result == null) result = caseDiffElement(methodParameterChange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case KDM2JavaDiffPackage.METHOD_INSERT: {
				MethodInsert methodInsert = (MethodInsert)theEObject;
				T result = caseMethodInsert(methodInsert);
				if (result == null) result = caseMethodDeclarationChange(methodInsert);
				if (result == null) result = caseKDM2JavaDiffExtension(methodInsert);
				if (result == null) result = caseAbstractDiffExtension(methodInsert);
				if (result == null) result = caseDiffElement(methodInsert);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case KDM2JavaDiffPackage.METHOD_DELETE: {
				MethodDelete methodDelete = (MethodDelete)theEObject;
				T result = caseMethodDelete(methodDelete);
				if (result == null) result = caseMethodDeclarationChange(methodDelete);
				if (result == null) result = caseKDM2JavaDiffExtension(methodDelete);
				if (result == null) result = caseAbstractDiffExtension(methodDelete);
				if (result == null) result = caseDiffElement(methodDelete);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE: {
				CompilationUnitChange compilationUnitChange = (CompilationUnitChange)theEObject;
				T result = caseCompilationUnitChange(compilationUnitChange);
				if (result == null) result = caseDiffGroup(compilationUnitChange);
				if (result == null) result = caseDiffElement(compilationUnitChange);
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
	public T caseKDM2JavaDiffExtension(KDM2JavaDiffExtension object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Statement Order Change</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Statement Order Change</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStatementOrderChange(StatementOrderChange object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Statement Insert</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Statement Insert</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStatementInsert(StatementInsert object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Statement Delete</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Statement Delete</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStatementDelete(StatementDelete object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Statement Move</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Statement Move</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStatementMove(StatementMove object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class Declaration Change</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class Declaration Change</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassDeclarationChange(ClassDeclarationChange object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Class Modifier Change</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class Modifier Change</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassModifierChange(ClassModifierChange object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Method Declaration Change</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Method Declaration Change</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMethodDeclarationChange(MethodDeclarationChange object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Method Modifier Change</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Method Modifier Change</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMethodModifierChange(MethodModifierChange object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Return Type Change</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Return Type Change</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReturnTypeChange(ReturnTypeChange object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Method Parameter Change</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Method Parameter Change</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMethodParameterChange(MethodParameterChange object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Method Insert</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Method Insert</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMethodInsert(MethodInsert object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Method Delete</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Method Delete</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMethodDelete(MethodDelete object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDiffGroup(DiffGroup object) {
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

} //KDM2JavaDiffSwitch
