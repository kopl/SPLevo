package org.splevo.diffing.emfcompare.util;

import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.ImportDelete;
import org.splevo.diffing.emfcompare.kdm2javadiff.ImportInsert;
import org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange;

/**
 * Utility class to handle changes types.
 * 
 * @author Benjamin Klatt
 * 
 */
public class ChangeUtil {

	/**
	 * Get the compilation unit of a statement change.
	 * 
	 * @param classDeclarationChange
	 *            The class declaration change to get the class declaration for.
	 * @return The identified class declaration.
	 */
	public static ClassDeclaration getClassDeclaration(ClassDeclarationChange classDeclarationChange) {

		ClassDeclaration classDecl = null;

		if (classDeclarationChange.getClassLeft() != null) {
			classDecl = classDeclarationChange.getClassLeft();
			
		} else if (classDeclarationChange.getClassRight() != null) {
			classDecl = classDeclarationChange.getClassRight();
		}
		
		return classDecl;
	}

	/**
	 * Get the compilation unit of a statement change.
	 * 
	 * @param methodChange
	 *            The method change to get the class declaration for.
	 * @return The identified class declaration.
	 */
	public static ClassDeclaration getClassDeclaration(MethodChange methodChange) {

		ClassDeclaration classDecl = null;

		
		// TODO finish class declaration detection from method declaration changes
		if(methodChange.getMethodDeclarationChanges().size() > 0) {
			MethodDeclarationChange methodDeclChange = methodChange.getMethodDeclarationChanges().iterator().next();
			if(methodDeclChange.getMethodDeclarationLeft() != null){
				AbstractTypeDeclaration abstractTypeDeclaration = methodDeclChange.getMethodDeclarationLeft().getAbstractTypeDeclaration();
				if(abstractTypeDeclaration != null && abstractTypeDeclaration instanceof ClassDeclaration){
					classDecl = (ClassDeclaration) abstractTypeDeclaration;
				}
			} else if(methodDeclChange.getMethodDeclarationRight() != null){
				AbstractTypeDeclaration abstractTypeDeclaration = methodDeclChange.getMethodDeclarationRight().getAbstractTypeDeclaration();
				if(abstractTypeDeclaration != null && abstractTypeDeclaration instanceof ClassDeclaration){
					classDecl = (ClassDeclaration) abstractTypeDeclaration;
				}
			}
			
		// try to get the class declaration from the first sub diff element
		} else if(methodChange.getSubDiffElements().size() > 0){
			classDecl = getClassDeclaration(methodChange.getSubDiffElements().iterator().next());
		}
		
		
		return classDecl;
	}

	/**
	 * Get the compilation unit of a statement change.
	 * 
	 * @param statementChange
	 *            The statement change to get the class declaration for.
	 * @return The identified class declaration.
	 */
	public static ClassDeclaration getClassDeclaration(StatementChange statementChange) {

		ClassDeclaration classDecl = null;
		
		
		
		return classDecl;
	}

	/**
	 * Base get class declaration method for an arbitrary diff element.
	 * This method returns null because a generic diff element does not have 
	 * a class declaration. This method has to be overloaded for more specific change
	 * types which are linked with a class declaration.
	 * 
	 * @param diffElement The generic diff element.
	 * @return The identified class declaration.
	 */
	public static ClassDeclaration getClassDeclaration(DiffElement diffElement) {
		
		if(diffElement instanceof ClassDeclarationChange) {
			return getClassDeclaration((ClassDeclarationChange) diffElement);
			
		} else if(diffElement instanceof MethodChange) {
			return getClassDeclaration((MethodChange) diffElement);
			
		} else if(diffElement instanceof StatementChange) {
			return getClassDeclaration((StatementChange) diffElement);
		}
		
		return null;
	}

	/**
	 * Get the compilation unit of a class declaration change.
	 * 
	 * @param classDeclarationChange
	 *            The class declaration change to get the compilation unit for.
	 * @return The identified compilation unit.
	 */
	public static CompilationUnit getCompilationUnit(ImportDeclarationChange importDeclarationChange) {

		CompilationUnit compUnit = null;

		if(importDeclarationChange instanceof ImportDelete){
			ImportDelete importDelete = (ImportDelete) importDeclarationChange;
			compUnit = importDelete.getImportLeft().getOriginalCompilationUnit();
		} else if(importDeclarationChange instanceof ImportInsert){
			ImportInsert importInsert = (ImportInsert) importDeclarationChange;
			compUnit = importInsert.getImportRight().getOriginalCompilationUnit();
		}
		
		return compUnit;
	}

	/**
	 * Get the compilation unit of a class declaration change.
	 * 
	 * @param classDeclarationChange
	 *            The class declaration change to get the compilation unit for.
	 * @return The identified compilation unit.
	 */
	public static CompilationUnit getCompilationUnit(ClassDeclarationChange classDeclarationChange) {

		CompilationUnit compUnit = null;

		if (classDeclarationChange.getClassLeft() != null
				&& classDeclarationChange.getClassLeft().getOriginalCompilationUnit() != null) {
			compUnit = classDeclarationChange.getClassLeft().getOriginalCompilationUnit();
			
		} else if (classDeclarationChange.getClassRight() != null
				&& classDeclarationChange.getClassRight().getOriginalCompilationUnit() != null) {
			compUnit = classDeclarationChange.getClassRight().getOriginalCompilationUnit();
		}
		
		return compUnit;
	}

	/**
	 * Get the compilation unit of a method declaration change.
	 * 
	 * @param methodDeclarationChange
	 *            The method declaration change to get the compilation unit for.
	 * @return The identified compilation unit.
	 */
	public static CompilationUnit getCompilationUnit(MethodDeclarationChange methodDeclarationChange) {

		CompilationUnit compUnit = null;
		
		if (methodDeclarationChange.getMethodDeclarationLeft() != null
				&& methodDeclarationChange.getMethodDeclarationLeft().getOriginalCompilationUnit() != null) {
			compUnit = methodDeclarationChange.getMethodDeclarationLeft().getOriginalCompilationUnit();
			
		} else if (methodDeclarationChange.getMethodDeclarationRight() != null
				&& methodDeclarationChange.getMethodDeclarationRight().getOriginalCompilationUnit() != null) {
			compUnit = methodDeclarationChange.getMethodDeclarationRight().getOriginalCompilationUnit();
		}
		
		return compUnit;
	}

	/**
	 * Get the compilation unit of a statement change.
	 * 
	 * @param statementChange
	 *            The statement change to get the compilation unit for.
	 * @return The identified compilation unit.
	 */
	public static CompilationUnit getCompilationUnit(StatementChange statementChange) {

		CompilationUnit compUnit = null;
		
		if(statementChange.getStatementLeft() != null
				&& statementChange.getStatementLeft().getOriginalCompilationUnit() != null){
			compUnit = statementChange.getStatementLeft().getOriginalCompilationUnit();
			
		} else if(statementChange.getStatementRight() != null
				&& statementChange.getStatementRight().getOriginalCompilationUnit() != null){
			compUnit = statementChange.getStatementRight().getOriginalCompilationUnit();
		}
		
		return compUnit;
	}

	/**
	 * Base get compilation unit method for an arbitrary diff element.
	 * This method returns null because a generic diff element does not have 
	 * a compilation unit. This method has to be overloaded for more specific change
	 * types which are linked with a compilation unit.
	 * 
	 * @param diffElement The generic diff element.
	 * @return The identified compilation unit.
	 */
	public static CompilationUnit getCompilationUnit(DiffElement diffElement) {
		
		if(diffElement instanceof StatementChange) {
			return getCompilationUnit((StatementChange) diffElement);
			
		} else if(diffElement instanceof MethodDeclarationChange) {
			return getCompilationUnit((MethodDeclarationChange) diffElement);
		
		} else if(diffElement instanceof ClassDeclarationChange) {
			return getCompilationUnit((ClassDeclarationChange) diffElement);
			
		} else if(diffElement instanceof ImportDeclarationChange) {
			return getCompilationUnit((ImportDeclarationChange) diffElement);
		}
		
		return null;
	}

}
