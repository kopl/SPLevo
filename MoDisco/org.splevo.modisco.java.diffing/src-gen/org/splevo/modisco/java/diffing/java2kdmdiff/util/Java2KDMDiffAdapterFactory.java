/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.compare.diff.metamodel.AbstractDiffExtension;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.ecore.EObject;
import org.splevo.modisco.java.diffing.java2kdmdiff.ClassChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.ClassDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.ClassInsert;
import org.splevo.modisco.java.diffing.java2kdmdiff.EnumChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.EnumDeclarationChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.FieldChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.FieldDeclarationChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.FieldDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.FieldInsert;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImplementsInterfaceDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImplementsInterfaceInsert;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImportDeclarationChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImportDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImportInsert;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffExtension;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;
import org.splevo.modisco.java.diffing.java2kdmdiff.MethodChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.MethodDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.MethodInsert;
import org.splevo.modisco.java.diffing.java2kdmdiff.PackageChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.PackageDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.PackageInsert;
import org.splevo.modisco.java.diffing.java2kdmdiff.StatementChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.StatementDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.StatementInsert;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage
 * @generated
 */
public class Java2KDMDiffAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Java2KDMDiffPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Java2KDMDiffAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = Java2KDMDiffPackage.eINSTANCE;
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
	protected Java2KDMDiffSwitch<Adapter> modelSwitch = new Java2KDMDiffSwitch<Adapter>() {
		@Override
		public Adapter caseJava2KDMDiffExtension(Java2KDMDiffExtension object) {
			return createJava2KDMDiffExtensionAdapter();
		}

		@Override
		public Adapter caseStatementChange(StatementChange object) {
			return createStatementChangeAdapter();
		}

		@Override
		public Adapter caseImportDeclarationChange(
				ImportDeclarationChange object) {
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
		public Adapter caseClassChange(ClassChange object) {
			return createClassChangeAdapter();
		}

		@Override
		public Adapter caseImplementsInterfaceInsert(
				ImplementsInterfaceInsert object) {
			return createImplementsInterfaceInsertAdapter();
		}

		@Override
		public Adapter caseImplementsInterfaceDelete(
				ImplementsInterfaceDelete object) {
			return createImplementsInterfaceDeleteAdapter();
		}

		@Override
		public Adapter caseFieldChange(FieldChange object) {
			return createFieldChangeAdapter();
		}

		@Override
		public Adapter caseFieldInsert(FieldInsert object) {
			return createFieldInsertAdapter();
		}

		@Override
		public Adapter caseFieldDelete(FieldDelete object) {
			return createFieldDeleteAdapter();
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
		public Adapter casePackageChange(PackageChange object) {
			return createPackageChangeAdapter();
		}

		@Override
		public Adapter casePackageInsert(PackageInsert object) {
			return createPackageInsertAdapter();
		}

		@Override
		public Adapter casePackageDelete(PackageDelete object) {
			return createPackageDeleteAdapter();
		}

		@Override
		public Adapter caseMethodChange(MethodChange object) {
			return createMethodChangeAdapter();
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
		public Adapter caseStatementInsert(StatementInsert object) {
			return createStatementInsertAdapter();
		}

		@Override
		public Adapter caseStatementDelete(StatementDelete object) {
			return createStatementDeleteAdapter();
		}

		@Override
		public Adapter caseFieldDeclarationChange(FieldDeclarationChange object) {
			return createFieldDeclarationChangeAdapter();
		}

		@Override
		public Adapter caseEnumChange(EnumChange object) {
			return createEnumChangeAdapter();
		}

		@Override
		public Adapter caseEnumDeclarationChange(EnumDeclarationChange object) {
			return createEnumDeclarationChangeAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffExtension <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffExtension
	 * @generated
	 */
	public Adapter createJava2KDMDiffExtensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.StatementChange <em>Statement Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.StatementChange
	 * @generated
	 */
	public Adapter createStatementChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.ImportDeclarationChange <em>Import Declaration Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.ImportDeclarationChange
	 * @generated
	 */
	public Adapter createImportDeclarationChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.ImportInsert <em>Import Insert</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.ImportInsert
	 * @generated
	 */
	public Adapter createImportInsertAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.ImportDelete <em>Import Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.ImportDelete
	 * @generated
	 */
	public Adapter createImportDeleteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.ClassChange <em>Class Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.ClassChange
	 * @generated
	 */
	public Adapter createClassChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.ImplementsInterfaceInsert <em>Implements Interface Insert</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.ImplementsInterfaceInsert
	 * @generated
	 */
	public Adapter createImplementsInterfaceInsertAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.ImplementsInterfaceDelete <em>Implements Interface Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.ImplementsInterfaceDelete
	 * @generated
	 */
	public Adapter createImplementsInterfaceDeleteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.FieldChange <em>Field Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.FieldChange
	 * @generated
	 */
	public Adapter createFieldChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.FieldInsert <em>Field Insert</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.FieldInsert
	 * @generated
	 */
	public Adapter createFieldInsertAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.FieldDelete <em>Field Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.FieldDelete
	 * @generated
	 */
	public Adapter createFieldDeleteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.ClassInsert <em>Class Insert</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.ClassInsert
	 * @generated
	 */
	public Adapter createClassInsertAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.ClassDelete <em>Class Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.ClassDelete
	 * @generated
	 */
	public Adapter createClassDeleteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.PackageChange <em>Package Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.PackageChange
	 * @generated
	 */
	public Adapter createPackageChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.PackageInsert <em>Package Insert</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.PackageInsert
	 * @generated
	 */
	public Adapter createPackageInsertAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.PackageDelete <em>Package Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.PackageDelete
	 * @generated
	 */
	public Adapter createPackageDeleteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.MethodChange <em>Method Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.MethodChange
	 * @generated
	 */
	public Adapter createMethodChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.MethodInsert <em>Method Insert</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.MethodInsert
	 * @generated
	 */
	public Adapter createMethodInsertAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.MethodDelete <em>Method Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.MethodDelete
	 * @generated
	 */
	public Adapter createMethodDeleteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.StatementInsert <em>Statement Insert</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.StatementInsert
	 * @generated
	 */
	public Adapter createStatementInsertAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.StatementDelete <em>Statement Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.StatementDelete
	 * @generated
	 */
	public Adapter createStatementDeleteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.FieldDeclarationChange <em>Field Declaration Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.FieldDeclarationChange
	 * @generated
	 */
	public Adapter createFieldDeclarationChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.EnumChange <em>Enum Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.EnumChange
	 * @generated
	 */
	public Adapter createEnumChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.EnumDeclarationChange <em>Enum Declaration Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.splevo.modisco.java.diffing.java2kdmdiff.EnumDeclarationChange
	 * @generated
	 */
	public Adapter createEnumDeclarationChangeAdapter() {
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

} //Java2KDMDiffAdapterFactory
