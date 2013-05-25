package org.splevo.vpm.analyzer.semantic;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.EnumDeclaration;
import org.eclipse.gmt.modisco.java.ExpressionStatement;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.IfStatement;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.ReturnStatement;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;

/**
 * 
 * @author Daniel Kojic
 * 
 */
public class IndexASTNodeSwitch extends JavaSwitch<List<ASTNode>> {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(IndexASTNodeSwitch.class);
    
    /** Add the data to this indexer´s index. */
	private Indexer indexer;

    public IndexASTNodeSwitch(){
    	indexer = Indexer.getInstance();
    }

    @Override
    public List<ASTNode> caseImportDeclaration(ImportDeclaration importDeclaration) {
    	String content = importDeclaration.getImportedElement().getName();
    	System.out.println("IMPORT: " + content);
    	return new ArrayList<ASTNode>();
    }

    @Override
    public List<ASTNode> caseVariableDeclarationStatement(VariableDeclarationStatement variableDeclarationStatement) {
    	EList<VariableDeclarationFragment> fragments = variableDeclarationStatement.getFragments();
    	for (VariableDeclarationFragment fragment : fragments) {
			System.out.println("VARIABLE: " + fragment.getName());
		}
    	return new ArrayList<ASTNode>();
    }

    @Override
    public List<ASTNode> caseFieldDeclaration(FieldDeclaration fieldDeclaration) {
    	String content = fieldDeclaration.getName();
    	System.out.println("FIELD: " + content);
    	return new ArrayList<ASTNode>();
    }
    
    @Override
    public List<ASTNode> caseClassDeclaration(ClassDeclaration decl) {
    	String content = decl.getName();
    	System.out.println("CLASS: " + content);
    	return new ArrayList<ASTNode>();
    }
    
    @Override
    public List<ASTNode> caseMethodDeclaration(MethodDeclaration decl) {
    	String content = decl.getName();
    	System.out.println("METHOD: " + content);
    	return new ArrayList<ASTNode>();
    }
    
    @Override
    public List<ASTNode> caseEnumDeclaration(EnumDeclaration decl) {
    	String content = decl.getName();
    	System.out.println("ENUM: " + content);
    	return new ArrayList<ASTNode>();
    }

    @Override
    public List<ASTNode> caseIfStatement(IfStatement ifStatement) {
    	// TODO
    	return new ArrayList<ASTNode>();
    }

    @Override
    public List<ASTNode> casePackage(Package packageNode) {
    	String content = packageNode.getName();
    	System.out.println("PACKAGE: " + content);
    	return new ArrayList<ASTNode>();
    }
    
    @Override
    public List<ASTNode> caseExpressionStatement(ExpressionStatement stmt) {
    	String content = stmt.getExpression().getOriginalCompilationUnit().getName();
    	System.out.println("EXPRESSION: " + content);
        return new ArrayList<ASTNode>();
    }

    @Override
    public List<ASTNode> caseReturnStatement(ReturnStatement returnStatement) {
    	String content = returnStatement.getExpression().getOriginalCompilationUnit().getName();
    	System.out.println("RETURN: " + content);
        return new ArrayList<ASTNode>();
    }

    @Override
    public List<ASTNode> defaultCase(EObject object) {
    	logger.error("Invalid ASTNode found. (" + object.toString() + ")");
        return new ArrayList<ASTNode>();
    }
}
