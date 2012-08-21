/**
 */
package org.splevo.vpm.realization.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.splevo.vpm.realization.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.splevo.vpm.realization.realizationPackage
 * @generated
 */
public class realizationAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static realizationPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public realizationAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = realizationPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected realizationSwitch<Adapter> modelSwitch =
		new realizationSwitch<Adapter>() {
			@Override
			public Adapter caseRealizationTechnique(RealizationTechnique object) {
				return createRealizationTechniqueAdapter();
			}
			@Override
			public Adapter caseDesignTimeRealizationTechnique(DesignTimeRealizationTechnique object) {
				return createDesignTimeRealizationTechniqueAdapter();
			}
			@Override
			public Adapter caseCompilationTimeRealizationTechnique(CompilationTimeRealizationTechnique object) {
				return createCompilationTimeRealizationTechniqueAdapter();
			}
			@Override
			public Adapter caseLinkingTimeRealizationTechnique(LinkingTimeRealizationTechnique object) {
				return createLinkingTimeRealizationTechniqueAdapter();
			}
			@Override
			public Adapter caseRuntimeRealizationTechnique(RuntimeRealizationTechnique object) {
				return createRuntimeRealizationTechniqueAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.vpm.realization.RealizationTechnique <em>Realization Technique</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.vpm.realization.RealizationTechnique
	 * @generated
	 */
	public Adapter createRealizationTechniqueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.vpm.realization.DesignTimeRealizationTechnique <em>Design Time Realization Technique</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.vpm.realization.DesignTimeRealizationTechnique
	 * @generated
	 */
	public Adapter createDesignTimeRealizationTechniqueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.vpm.realization.CompilationTimeRealizationTechnique <em>Compilation Time Realization Technique</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.vpm.realization.CompilationTimeRealizationTechnique
	 * @generated
	 */
	public Adapter createCompilationTimeRealizationTechniqueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.vpm.realization.LinkingTimeRealizationTechnique <em>Linking Time Realization Technique</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.vpm.realization.LinkingTimeRealizationTechnique
	 * @generated
	 */
	public Adapter createLinkingTimeRealizationTechniqueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.vpm.realization.RuntimeRealizationTechnique <em>Runtime Realization Technique</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.vpm.realization.RuntimeRealizationTechnique
	 * @generated
	 */
	public Adapter createRuntimeRealizationTechniqueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //realizationAdapterFactory
