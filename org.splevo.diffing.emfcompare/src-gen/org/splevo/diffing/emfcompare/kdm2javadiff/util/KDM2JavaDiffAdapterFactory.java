/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.compare.diff.metamodel.AbstractDiffExtension;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;

import org.eclipse.emf.ecore.EObject;

import org.splevo.diffing.emfcompare.kdm2javadiff.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage
 * @generated
 */
public class KDM2JavaDiffAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static KDM2JavaDiffPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KDM2JavaDiffAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = KDM2JavaDiffPackage.eINSTANCE;
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
	protected KDM2JavaDiffSwitch<Adapter> modelSwitch =
		new KDM2JavaDiffSwitch<Adapter>() {
			@Override
			public Adapter caseKDM2JavaDiffExtension(KDM2JavaDiffExtension object) {
				return createKDM2JavaDiffExtensionAdapter();
			}
			@Override
			public Adapter caseStatementChange(StatementChange object) {
				return createStatementChangeAdapter();
			}
			@Override
			public Adapter caseStatementOrderChange(StatementOrderChange object) {
				return createStatementOrderChangeAdapter();
			}
			@Override
			public Adapter caseStatementInsert(StatementInsert object) {
				return createStatementInsertAdapter();
			}
			@Override
			public Adapter caseStatementDelete(StatementDelete object) {
				return createStatementDeleteAdapter();
			}
			@Override
			public Adapter caseStatementMove(StatementMove object) {
				return createStatementMoveAdapter();
			}
			@Override
			public Adapter caseClassDeclarationChange(ClassDeclarationChange object) {
				return createClassDeclarationChangeAdapter();
			}
			@Override
			public Adapter caseClassInsert(ClassInsert object) {
				return createClassInsertAdapter();
			}
			@Override
			public Adapter caseClassDelete(ClassDelete object) {
				return createClassDeleteAdapter();
			}
			@Override
			public Adapter caseClassModifierChange(ClassModifierChange object) {
				return createClassModifierChangeAdapter();
			}
			@Override
			public Adapter caseImportDeclarationChange(ImportDeclarationChange object) {
				return createImportDeclarationChangeAdapter();
			}
			@Override
			public Adapter caseImportInsert(ImportInsert object) {
				return createImportInsertAdapter();
			}
			@Override
			public Adapter caseImportDelete(ImportDelete object) {
				return createImportDeleteAdapter();
			}
			@Override
			public Adapter caseMethodDeclarationChange(MethodDeclarationChange object) {
				return createMethodDeclarationChangeAdapter();
			}
			@Override
			public Adapter caseClassChange(ClassChange object) {
				return createClassChangeAdapter();
			}
			@Override
			public Adapter caseMethodChange(MethodChange object) {
				return createMethodChangeAdapter();
			}
			@Override
			public Adapter caseMethodModifierChange(MethodModifierChange object) {
				return createMethodModifierChangeAdapter();
			}
			@Override
			public Adapter caseReturnTypeChange(ReturnTypeChange object) {
				return createReturnTypeChangeAdapter();
			}
			@Override
			public Adapter caseMethodParameterChange(MethodParameterChange object) {
				return createMethodParameterChangeAdapter();
			}
			@Override
			public Adapter caseMethodInsert(MethodInsert object) {
				return createMethodInsertAdapter();
			}
			@Override
			public Adapter caseMethodDelete(MethodDelete object) {
				return createMethodDeleteAdapter();
			}
			@Override
			public Adapter caseCompilationUnitChange(CompilationUnitChange object) {
				return createCompilationUnitChangeAdapter();
			}
			@Override
			public Adapter casePackageChange(PackageChange object) {
				return createPackageChangeAdapter();
			}
			@Override
			public Adapter caseAbstractDiffExtension(AbstractDiffExtension object) {
				return createAbstractDiffExtensionAdapter();
			}
			@Override
			public Adapter caseDiffElement(DiffElement object) {
				return createDiffElementAdapter();
			}
			@Override
			public Adapter caseDiffGroup(DiffGroup object) {
				return createDiffGroupAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffExtension <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffExtension
	 * @generated
	 */
	public Adapter createKDM2JavaDiffExtensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange <em>Statement Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange
	 * @generated
	 */
	public Adapter createStatementChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementOrderChange <em>Statement Order Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.StatementOrderChange
	 * @generated
	 */
	public Adapter createStatementOrderChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementInsert <em>Statement Insert</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.StatementInsert
	 * @generated
	 */
	public Adapter createStatementInsertAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementDelete <em>Statement Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.StatementDelete
	 * @generated
	 */
	public Adapter createStatementDeleteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.StatementMove <em>Statement Move</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.StatementMove
	 * @generated
	 */
	public Adapter createStatementMoveAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange <em>Class Declaration Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange
	 * @generated
	 */
	public Adapter createClassDeclarationChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassInsert <em>Class Insert</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ClassInsert
	 * @generated
	 */
	public Adapter createClassInsertAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassDelete <em>Class Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ClassDelete
	 * @generated
	 */
	public Adapter createClassDeleteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassModifierChange <em>Class Modifier Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ClassModifierChange
	 * @generated
	 */
	public Adapter createClassModifierChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange <em>Import Declaration Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange
	 * @generated
	 */
	public Adapter createImportDeclarationChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ImportInsert <em>Import Insert</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ImportInsert
	 * @generated
	 */
	public Adapter createImportInsertAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ImportDelete <em>Import Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ImportDelete
	 * @generated
	 */
	public Adapter createImportDeleteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange <em>Method Declaration Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange
	 * @generated
	 */
	public Adapter createMethodDeclarationChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange <em>Class Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange
	 * @generated
	 */
	public Adapter createClassChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange <em>Method Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange
	 * @generated
	 */
	public Adapter createMethodChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodModifierChange <em>Method Modifier Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodModifierChange
	 * @generated
	 */
	public Adapter createMethodModifierChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.ReturnTypeChange <em>Return Type Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.ReturnTypeChange
	 * @generated
	 */
	public Adapter createReturnTypeChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodParameterChange <em>Method Parameter Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodParameterChange
	 * @generated
	 */
	public Adapter createMethodParameterChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodInsert <em>Method Insert</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodInsert
	 * @generated
	 */
	public Adapter createMethodInsertAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.MethodDelete <em>Method Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.MethodDelete
	 * @generated
	 */
	public Adapter createMethodDeleteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange <em>Compilation Unit Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange
	 * @generated
	 */
	public Adapter createCompilationUnitChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange <em>Package Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange
	 * @generated
	 */
	public Adapter createPackageChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.compare.diff.metamodel.AbstractDiffExtension <em>Abstract Diff Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.compare.diff.metamodel.AbstractDiffExtension
	 * @generated
	 */
	public Adapter createAbstractDiffExtensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.compare.diff.metamodel.DiffElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.compare.diff.metamodel.DiffElement
	 * @generated
	 */
	public Adapter createDiffElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.compare.diff.metamodel.DiffGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.compare.diff.metamodel.DiffGroup
	 * @generated
	 */
	public Adapter createDiffGroupAdapter() {
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

} //KDM2JavaDiffAdapterFactory
