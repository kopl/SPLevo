/**
 */
package org.splevo.modisco.java.diffing.java2kdmdiff.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.splevo.modisco.java.diffing.java2kdmdiff.ClassChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.EnumChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.FieldChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImportChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffFactory;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;
import org.splevo.modisco.java.diffing.java2kdmdiff.MethodChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.PackageChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.StatementChange;

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
            Java2KDMDiffFactory theJava2KDMDiffFactory = (Java2KDMDiffFactory) EPackage.Registry.INSTANCE
                    .getEFactory("http://www.eclipse.org/emf/compare/diff/java2kdmdiff/1.0");
            if (theJava2KDMDiffFactory != null) {
                return theJava2KDMDiffFactory;
            }
        } catch (Exception exception) {
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
        case Java2KDMDiffPackage.STATEMENT_CHANGE:
            return createStatementChange();
        case Java2KDMDiffPackage.IMPORT_CHANGE:
            return createImportChange();
        case Java2KDMDiffPackage.CLASS_CHANGE:
            return createClassChange();
        case Java2KDMDiffPackage.FIELD_CHANGE:
            return createFieldChange();
        case Java2KDMDiffPackage.PACKAGE_CHANGE:
            return createPackageChange();
        case Java2KDMDiffPackage.METHOD_CHANGE:
            return createMethodChange();
        case Java2KDMDiffPackage.ENUM_CHANGE:
            return createEnumChange();
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
    public ImportChange createImportChange() {
        ImportChangeImpl importChange = new ImportChangeImpl();
        return importChange;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ClassChange createClassChange() {
        ClassChangeImpl classChange = new ClassChangeImpl();
        return classChange;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FieldChange createFieldChange() {
        FieldChangeImpl fieldChange = new FieldChangeImpl();
        return fieldChange;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PackageChange createPackageChange() {
        PackageChangeImpl packageChange = new PackageChangeImpl();
        return packageChange;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MethodChange createMethodChange() {
        MethodChangeImpl methodChange = new MethodChangeImpl();
        return methodChange;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EnumChange createEnumChange() {
        EnumChangeImpl enumChange = new EnumChangeImpl();
        return enumChange;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Java2KDMDiffPackage getJava2KDMDiffPackage() {
        return (Java2KDMDiffPackage) getEPackage();
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
