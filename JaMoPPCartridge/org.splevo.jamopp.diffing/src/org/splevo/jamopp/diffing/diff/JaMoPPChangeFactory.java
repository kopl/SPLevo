/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.jamopp.diffing.diff;

import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.classifiers.util.ClassifiersSwitch;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.util.ContainersSwitch;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.util.ImportsSwitch;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.members.util.MembersSwitch;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.util.StatementsSwitch;
import org.splevo.jamopp.diffing.jamoppdiff.ClassChange;
import org.splevo.jamopp.diffing.jamoppdiff.CompilationUnitChange;
import org.splevo.jamopp.diffing.jamoppdiff.ConstructorChange;
import org.splevo.jamopp.diffing.jamoppdiff.EnumChange;
import org.splevo.jamopp.diffing.jamoppdiff.FieldChange;
import org.splevo.jamopp.diffing.jamoppdiff.ImportChange;
import org.splevo.jamopp.diffing.jamoppdiff.InterfaceChange;
import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffFactory;
import org.splevo.jamopp.diffing.jamoppdiff.MethodChange;
import org.splevo.jamopp.diffing.jamoppdiff.PackageChange;
import org.splevo.jamopp.diffing.jamoppdiff.StatementChange;

/**
 * Factory to create custom changes according to the changed value object.
 */
public class JaMoPPChangeFactory extends ComposedSwitch<Diff> {

    /**
     * Composed switch constructor to initialize the sub switches.
     */
    public JaMoPPChangeFactory() {
        addSwitch(new ImportChangeFactory());
        addSwitch(new ClassifierChangeFactory());
        addSwitch(new MembersChangeFactory());
        addSwitch(new StatementsChangeFactory());
        addSwitch(new ContainersChangeFactory());
    }

    /**
     * Sub switch to create import change elements.
     */
    private class ImportChangeFactory extends ImportsSwitch<Diff> {
        @Override
        public Diff caseImport(Import object) {
            ImportChange importChange = JaMoPPDiffFactory.eINSTANCE.createImportChange();
            importChange.setChangedImport(object);
            return importChange;
        }
    }

    /**
     * Sub switch to classifier change elements.
     */
    private class ClassifierChangeFactory extends ClassifiersSwitch<Diff> {

        @Override
        public Diff caseClass(org.emftext.language.java.classifiers.Class classDeclaration) {
            final ClassChange classChange = JaMoPPDiffFactory.eINSTANCE.createClassChange();
            classChange.setChangedClass(classDeclaration);
            return classChange;
        }

        @Override
        public Diff caseEnumeration(Enumeration object) {
            EnumChange enumChange = JaMoPPDiffFactory.eINSTANCE.createEnumChange();
            enumChange.setChangedEnum(object);
            return enumChange;
        }

        @Override
        public Diff caseInterface(Interface object) {
            InterfaceChange interfaceChange = JaMoPPDiffFactory.eINSTANCE.createInterfaceChange();
            interfaceChange.setChangedInterface(object);
            return interfaceChange;
        }

    }

    /**
     * Sub switch to member change elements.
     */
    private class MembersChangeFactory extends MembersSwitch<Diff> {
        @Override
        public Diff caseField(Field field) {
            FieldChange fieldInsert = JaMoPPDiffFactory.eINSTANCE.createFieldChange();
            fieldInsert.setChangedField(field);
            return fieldInsert;
        }

        @Override
        public Diff caseMethod(Method method) {
            MethodChange methodChange = JaMoPPDiffFactory.eINSTANCE.createMethodChange();
            methodChange.setChangedMethod(method);
            return methodChange;
        }

        @Override
        public Diff caseConstructor(Constructor constructor) {
            ConstructorChange change = JaMoPPDiffFactory.eINSTANCE.createConstructorChange();
            change.setChangedConstructor(constructor);
            return change;
        }
    }

    /**
     * Sub switch to statement change elements.
     */
    private class StatementsChangeFactory extends StatementsSwitch<Diff> {
        @Override
        public Diff caseStatement(Statement statement) {
            StatementChange statementChange = JaMoPPDiffFactory.eINSTANCE.createStatementChange();
            statementChange.setChangedStatement(statement);
            return statementChange;
        }
    }

    /**
     * Sub switch to container change elements.
     */
    private class ContainersChangeFactory extends ContainersSwitch<Diff> {
        @Override
        public Diff casePackage(org.emftext.language.java.containers.Package pckg) {
            PackageChange packageChange = JaMoPPDiffFactory.eINSTANCE.createPackageChange();
            packageChange.setChangedPackage(pckg);
            return packageChange;
        }

        @Override
        public Diff caseCompilationUnit(CompilationUnit unit) {
            CompilationUnitChange unitChange = JaMoPPDiffFactory.eINSTANCE.createCompilationUnitChange();
            unitChange.setChangedCompilationUnit(unit);
            return unitChange;
        }
    }

    @Override
    public Diff defaultCase(EObject object) {
        return null;
    }
}