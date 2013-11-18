package org.splevo.modisco.java.diffing.diff;

import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.EnumDeclaration;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;
import org.splevo.modisco.java.diffing.java2kdmdiff.ClassChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.EnumChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.FieldChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImportChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffFactory;
import org.splevo.modisco.java.diffing.java2kdmdiff.MethodChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.PackageChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.StatementChange;

/**
 * Factory to create custom changes according to the changed value object.
 */
@SuppressWarnings("restriction")
public class CustomChangeFactory extends JavaSwitch<Diff> {

    @Override
    public Diff caseImportDeclaration(ImportDeclaration object) {
        ImportChange importChange = Java2KDMDiffFactory.eINSTANCE.createImportChange();
        importChange.setChangedImport(object);
        return importChange;
    }

    @Override
    public Diff caseClassDeclaration(ClassDeclaration classDeclaration) {
        final ClassChange classChange = Java2KDMDiffFactory.eINSTANCE.createClassChange();
        classChange.setChangedClass(classDeclaration);
        return classChange;
    }

    @Override
    public Diff caseFieldDeclaration(FieldDeclaration fieldDeclaration) {
        FieldChange fieldInsert = Java2KDMDiffFactory.eINSTANCE.createFieldChange();
        fieldInsert.setChangedField(fieldDeclaration);
        return fieldInsert;
    }

    @Override
    public Diff caseAbstractMethodDeclaration(AbstractMethodDeclaration methodDeclaration) {
        MethodChange methodChange = Java2KDMDiffFactory.eINSTANCE.createMethodChange();
        methodChange.setChangedMethod(methodDeclaration);
        return methodChange;
    }

    @Override
    public Diff caseStatement(Statement statement) {
        StatementChange statementChange = Java2KDMDiffFactory.eINSTANCE.createStatementChange();
        statementChange.setChangedStatement(statement);
        return statementChange;
    }

    @Override
    public Diff casePackage(Package object) {
        PackageChange packageChange = Java2KDMDiffFactory.eINSTANCE.createPackageChange();
        packageChange.setChangedPackage(object);
        return packageChange;
    }

    @Override
    public Diff caseEnumDeclaration(EnumDeclaration object) {
        EnumChange enumChange = Java2KDMDiffFactory.eINSTANCE.createEnumChange();
        enumChange.setChangedEnum(object);
        return enumChange;
    }

    @Override
    public Diff defaultCase(EObject object) {
        return null;
    }
}