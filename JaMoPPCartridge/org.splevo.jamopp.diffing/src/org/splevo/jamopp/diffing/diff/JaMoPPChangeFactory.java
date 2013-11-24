package org.splevo.jamopp.diffing.diff;

import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.classifiers.util.ClassifiersSwitch;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.util.ContainersSwitch;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.util.ImportsSwitch;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.members.util.MembersSwitch;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.util.StatementsSwitch;
import org.splevo.jamopp.diffing.jamoppdiff.ClassChange;
import org.splevo.jamopp.diffing.jamoppdiff.CompilationUnitChange;
import org.splevo.jamopp.diffing.jamoppdiff.EnumChange;
import org.splevo.jamopp.diffing.jamoppdiff.FieldChange;
import org.splevo.jamopp.diffing.jamoppdiff.ImportChange;
import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffFactory;
import org.splevo.jamopp.diffing.jamoppdiff.MethodChange;
import org.splevo.jamopp.diffing.jamoppdiff.PackageChange;
import org.splevo.jamopp.diffing.jamoppdiff.StatementChange;

/**
 * Factory to create custom changes according to the changed value object.
 */
public class JaMoPPChangeFactory extends ComposedSwitch<Diff>{
	
	public JaMoPPChangeFactory() {
		addSwitch(new ImportChangeFactory());
		addSwitch(new ClassifierChangeFactory());
		addSwitch(new MembersChangeFactory());
		addSwitch(new StatementsChangeFactory());
		addSwitch(new ContainersChangeFactory());
	}
	
	private class ImportChangeFactory extends ImportsSwitch<Diff> {
		@Override
		public Diff caseImport(Import object) {
	        ImportChange importChange = JaMoPPDiffFactory.eINSTANCE.createImportChange();
	        importChange.setChangedImport(object);
	        return importChange;
		}
	}
	
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
		
	}
	
	private class MembersChangeFactory extends MembersSwitch<Diff>{
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
	}
	
	private class StatementsChangeFactory extends StatementsSwitch<Diff> {
		@Override
		public Diff caseStatement(Statement statement) {
	        StatementChange statementChange = JaMoPPDiffFactory.eINSTANCE.createStatementChange();
	        statementChange.setChangedStatement(statement);
	        return statementChange;
		}
	}
	
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