/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.splevo.diffing.emfcompare.java2kdmdiff.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Java2KDMDiffFactoryImpl extends EFactoryImpl implements Java2KDMDiffFactory {
	/**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static Java2KDMDiffFactory init() {
        try {
            Java2KDMDiffFactory theJava2KDMDiffFactory = (Java2KDMDiffFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/emf/compare/diff/java2kdmdiff/1.0"); 
            if (theJava2KDMDiffFactory != null) {
                return theJava2KDMDiffFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new Java2KDMDiffFactoryImpl();
    }

	/**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Java2KDMDiffFactoryImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case Java2KDMDiffPackage.STATEMENT_CHANGE: return createStatementChange();
            case Java2KDMDiffPackage.IMPORT_INSERT: return createImportInsert();
            case Java2KDMDiffPackage.IMPORT_DELETE: return createImportDelete();
            case Java2KDMDiffPackage.IMPLEMENTS_INTERFACE_INSERT: return createImplementsInterfaceInsert();
            case Java2KDMDiffPackage.IMPLEMENTS_INTERFACE_DELETE: return createImplementsInterfaceDelete();
            case Java2KDMDiffPackage.FIELD_INSERT: return createFieldInsert();
            case Java2KDMDiffPackage.FIELD_DELETE: return createFieldDelete();
            case Java2KDMDiffPackage.CLASS_INSERT: return createClassInsert();
            case Java2KDMDiffPackage.CLASS_DELETE: return createClassDelete();
            case Java2KDMDiffPackage.PACKAGE_INSERT: return createPackageInsert();
            case Java2KDMDiffPackage.PACKAGE_DELETE: return createPackageDelete();
            case Java2KDMDiffPackage.METHOD_INSERT: return createMethodInsert();
            case Java2KDMDiffPackage.METHOD_DELETE: return createMethodDelete();
            case Java2KDMDiffPackage.STATEMENT_INSERT: return createStatementInsert();
            case Java2KDMDiffPackage.STATEMENT_DELETE: return createStatementDelete();
            case Java2KDMDiffPackage.FIELD_DECLARATION_CHANGE: return createFieldDeclarationChange();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public StatementChange createStatementChange() {
        StatementChangeImpl statementChange = new StatementChangeImpl();
        return statementChange;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ImportInsert createImportInsert() {
        ImportInsertImpl importInsert = new ImportInsertImpl();
        return importInsert;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ImportDelete createImportDelete() {
        ImportDeleteImpl importDelete = new ImportDeleteImpl();
        return importDelete;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ImplementsInterfaceInsert createImplementsInterfaceInsert() {
        ImplementsInterfaceInsertImpl implementsInterfaceInsert = new ImplementsInterfaceInsertImpl();
        return implementsInterfaceInsert;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ImplementsInterfaceDelete createImplementsInterfaceDelete() {
        ImplementsInterfaceDeleteImpl implementsInterfaceDelete = new ImplementsInterfaceDeleteImpl();
        return implementsInterfaceDelete;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FieldInsert createFieldInsert() {
        FieldInsertImpl fieldInsert = new FieldInsertImpl();
        return fieldInsert;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FieldDelete createFieldDelete() {
        FieldDeleteImpl fieldDelete = new FieldDeleteImpl();
        return fieldDelete;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ClassInsert createClassInsert() {
        ClassInsertImpl classInsert = new ClassInsertImpl();
        return classInsert;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ClassDelete createClassDelete() {
        ClassDeleteImpl classDelete = new ClassDeleteImpl();
        return classDelete;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PackageInsert createPackageInsert() {
        PackageInsertImpl packageInsert = new PackageInsertImpl();
        return packageInsert;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PackageDelete createPackageDelete() {
        PackageDeleteImpl packageDelete = new PackageDeleteImpl();
        return packageDelete;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MethodInsert createMethodInsert() {
        MethodInsertImpl methodInsert = new MethodInsertImpl();
        return methodInsert;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MethodDelete createMethodDelete() {
        MethodDeleteImpl methodDelete = new MethodDeleteImpl();
        return methodDelete;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StatementInsert createStatementInsert() {
        StatementInsertImpl statementInsert = new StatementInsertImpl();
        return statementInsert;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StatementDelete createStatementDelete() {
        StatementDeleteImpl statementDelete = new StatementDeleteImpl();
        return statementDelete;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FieldDeclarationChange createFieldDeclarationChange() {
        FieldDeclarationChangeImpl fieldDeclarationChange = new FieldDeclarationChangeImpl();
        return fieldDeclarationChange;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Java2KDMDiffPackage getJava2KDMDiffPackage() {
        return (Java2KDMDiffPackage)getEPackage();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
	@Deprecated
	public static Java2KDMDiffPackage getPackage() {
        return Java2KDMDiffPackage.eINSTANCE;
    }

} //Java2KDMDiffFactoryImpl
