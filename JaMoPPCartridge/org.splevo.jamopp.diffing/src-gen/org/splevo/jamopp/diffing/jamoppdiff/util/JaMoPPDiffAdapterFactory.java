/**
 */
package org.splevo.jamopp.diffing.jamoppdiff.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.compare.Diff;

import org.eclipse.emf.ecore.EObject;

import org.splevo.jamopp.diffing.jamoppdiff.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage
 * @generated
 */
public class JaMoPPDiffAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static JaMoPPDiffPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JaMoPPDiffAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = JaMoPPDiffPackage.eINSTANCE;
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
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JaMoPPDiffSwitch<Adapter> modelSwitch = new JaMoPPDiffSwitch<Adapter>() {
		@Override
		public Adapter caseJaMoPPDiff(JaMoPPDiff object) {
			return createJaMoPPDiffAdapter();
		}

		@Override
		public Adapter caseStatementChange(StatementChange object) {
			return createStatementChangeAdapter();
		}

		@Override
		public Adapter caseImportChange(ImportChange object) {
			return createImportChangeAdapter();
		}

		@Override
		public Adapter caseClassChange(ClassChange object) {
			return createClassChangeAdapter();
		}

		@Override
		public Adapter caseFieldChange(FieldChange object) {
			return createFieldChangeAdapter();
		}

		@Override
		public Adapter casePackageChange(PackageChange object) {
			return createPackageChangeAdapter();
		}

		@Override
		public Adapter caseMethodChange(MethodChange object) {
			return createMethodChangeAdapter();
		}

		@Override
		public Adapter caseEnumChange(EnumChange object) {
			return createEnumChangeAdapter();
		}

		@Override
		public Adapter caseCompilationUnitChange(CompilationUnitChange object) {
			return createCompilationUnitChangeAdapter();
		}

		@Override
		public Adapter caseInterfaceChange(InterfaceChange object) {
			return createInterfaceChangeAdapter();
		}

		@Override
		public Adapter caseDiff(Diff object) {
			return createDiffAdapter();
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
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiff <em>Ja Mo PP Diff</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiff
	 * @generated
	 */
	public Adapter createJaMoPPDiffAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.jamopp.diffing.jamoppdiff.StatementChange <em>Statement Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.jamopp.diffing.jamoppdiff.StatementChange
	 * @generated
	 */
	public Adapter createStatementChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.jamopp.diffing.jamoppdiff.ImportChange <em>Import Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.jamopp.diffing.jamoppdiff.ImportChange
	 * @generated
	 */
	public Adapter createImportChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.jamopp.diffing.jamoppdiff.ClassChange <em>Class Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.jamopp.diffing.jamoppdiff.ClassChange
	 * @generated
	 */
	public Adapter createClassChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.jamopp.diffing.jamoppdiff.FieldChange <em>Field Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.jamopp.diffing.jamoppdiff.FieldChange
	 * @generated
	 */
	public Adapter createFieldChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.jamopp.diffing.jamoppdiff.PackageChange <em>Package Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.jamopp.diffing.jamoppdiff.PackageChange
	 * @generated
	 */
	public Adapter createPackageChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.jamopp.diffing.jamoppdiff.MethodChange <em>Method Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.jamopp.diffing.jamoppdiff.MethodChange
	 * @generated
	 */
	public Adapter createMethodChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.jamopp.diffing.jamoppdiff.EnumChange <em>Enum Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.jamopp.diffing.jamoppdiff.EnumChange
	 * @generated
	 */
	public Adapter createEnumChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.jamopp.diffing.jamoppdiff.CompilationUnitChange <em>Compilation Unit Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.jamopp.diffing.jamoppdiff.CompilationUnitChange
	 * @generated
	 */
	public Adapter createCompilationUnitChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.jamopp.diffing.jamoppdiff.InterfaceChange <em>Interface Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.jamopp.diffing.jamoppdiff.InterfaceChange
	 * @generated
	 */
	public Adapter createInterfaceChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.compare.Diff <em>Diff</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.compare.Diff
	 * @generated
	 */
	public Adapter createDiffAdapter() {
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

} //JaMoPPDiffAdapterFactory
