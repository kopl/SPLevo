/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.vpm.realization.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.splevo.vpm.realization.*;

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
 * @see org.splevo.vpm.realization.realizationPackage
 * @generated
 */
public class realizationSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static realizationPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public realizationSwitch() {
		if (modelPackage == null) {
			modelPackage = realizationPackage.eINSTANCE;
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
			case realizationPackage.REALIZATION_TECHNIQUE: {
				RealizationTechnique realizationTechnique = (RealizationTechnique)theEObject;
				T result = caseRealizationTechnique(realizationTechnique);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case realizationPackage.DESIGN_TIME_REALIZATION_TECHNIQUE: {
				DesignTimeRealizationTechnique designTimeRealizationTechnique = (DesignTimeRealizationTechnique)theEObject;
				T result = caseDesignTimeRealizationTechnique(designTimeRealizationTechnique);
				if (result == null) result = caseRealizationTechnique(designTimeRealizationTechnique);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case realizationPackage.COMPILATION_TIME_REALIZATION_TECHNIQUE: {
				CompilationTimeRealizationTechnique compilationTimeRealizationTechnique = (CompilationTimeRealizationTechnique)theEObject;
				T result = caseCompilationTimeRealizationTechnique(compilationTimeRealizationTechnique);
				if (result == null) result = caseRealizationTechnique(compilationTimeRealizationTechnique);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case realizationPackage.LINKING_TIME_REALIZATION_TECHNIQUE: {
				LinkingTimeRealizationTechnique linkingTimeRealizationTechnique = (LinkingTimeRealizationTechnique)theEObject;
				T result = caseLinkingTimeRealizationTechnique(linkingTimeRealizationTechnique);
				if (result == null) result = caseRealizationTechnique(linkingTimeRealizationTechnique);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case realizationPackage.RUNTIME_REALIZATION_TECHNIQUE: {
				RuntimeRealizationTechnique runtimeRealizationTechnique = (RuntimeRealizationTechnique)theEObject;
				T result = caseRuntimeRealizationTechnique(runtimeRealizationTechnique);
				if (result == null) result = caseRealizationTechnique(runtimeRealizationTechnique);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Realization Technique</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Realization Technique</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRealizationTechnique(RealizationTechnique object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Design Time Realization Technique</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Design Time Realization Technique</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDesignTimeRealizationTechnique(DesignTimeRealizationTechnique object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Compilation Time Realization Technique</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Compilation Time Realization Technique</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompilationTimeRealizationTechnique(CompilationTimeRealizationTechnique object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Linking Time Realization Technique</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Linking Time Realization Technique</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLinkingTimeRealizationTechnique(LinkingTimeRealizationTechnique object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Runtime Realization Technique</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Runtime Realization Technique</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRuntimeRealizationTechnique(RuntimeRealizationTechnique object) {
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

} //realizationSwitch
