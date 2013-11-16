/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.EObject;
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
     * Creates a new adapter for an object of class '{@link org.splevo.modisco.java.diffing.java2kdmdiff.ImportChange <em>Import Change</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.splevo.modisco.java.diffing.java2kdmdiff.ImportChange
     * @generated
     */
    public Adapter createImportChangeAdapter() {
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

} //Java2KDMDiffAdapterFactory
