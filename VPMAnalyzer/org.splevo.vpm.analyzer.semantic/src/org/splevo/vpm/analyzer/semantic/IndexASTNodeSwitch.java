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
    
	private StringBuilder sb;

    public IndexASTNodeSwitch(){
    	clear();
    }
    
    public void clear(){
    	sb = new StringBuilder();
    }
    
    public String getContent(){
    	return sb.toString();
    }

    @Override
    public List<ASTNode> caseImportDeclaration(ImportDeclaration importDeclaration) {
    	String content = importDeclaration.getImportedElement().getName();
    	sb.append(content + " ");
    	return new ArrayList<ASTNode>();
    }

    @Override
    public List<ASTNode> caseVariableDeclarationStatement(VariableDeclarationStatement variableDeclarationStatement) {
    	EList<VariableDeclarationFragment> fragments = variableDeclarationStatement.getFragments();
    	for (VariableDeclarationFragment fragment : fragments) {
    		sb.append(fragment.getName() + " ");
		}
    	return new ArrayList<ASTNode>();
    }

    @Override
    public List<ASTNode> caseFieldDeclaration(FieldDeclaration fieldDeclaration) {
    	String content = fieldDeclaration.getName();
    	sb.append(content + " ");
    	return new ArrayList<ASTNode>();
    }
    
    @Override
    public List<ASTNode> caseClassDeclaration(ClassDeclaration decl) {
    	String content = decl.getName();
    	sb.append(content + " ");
    	return new ArrayList<ASTNode>();
    }
    
    @Override
    public List<ASTNode> caseMethodDeclaration(MethodDeclaration decl) {
    	String content = decl.getName();
    	sb.append(content + " ");
    	return new ArrayList<ASTNode>();
    }
    
    @Override
    public List<ASTNode> caseEnumDeclaration(EnumDeclaration decl) {
    	String content = decl.getName();
    	sb.append(content + " ");
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
    	sb.append(content + " ");
    	return new ArrayList<ASTNode>();
    }
    
    @Override
    public List<ASTNode> caseExpressionStatement(ExpressionStatement stmt) {
    	String content = stmt.getExpression().getOriginalCompilationUnit().getName();
    	sb.append(content + " ");
        return new ArrayList<ASTNode>();
    }

    @Override
    public List<ASTNode> caseReturnStatement(ReturnStatement returnStatement) {
    	String content = returnStatement.getExpression().getOriginalCompilationUnit().getName();
    	sb.append(content + " ");
        return new ArrayList<ASTNode>();
    }

    @Override
    public List<ASTNode> defaultCase(EObject object) {
    	logger.error("Invalid ASTNode found. (" + object.toString() + ")");
        return new ArrayList<ASTNode>();
    }
}
