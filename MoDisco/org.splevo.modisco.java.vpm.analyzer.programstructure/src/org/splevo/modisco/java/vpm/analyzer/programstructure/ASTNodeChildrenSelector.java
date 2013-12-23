package org.splevo.modisco.java.vpm.analyzer.programstructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.ArrayAccess;
import org.eclipse.gmt.modisco.java.ArrayCreation;
import org.eclipse.gmt.modisco.java.ArrayInitializer;
import org.eclipse.gmt.modisco.java.AssertStatement;
import org.eclipse.gmt.modisco.java.Assignment;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.BooleanLiteral;
import org.eclipse.gmt.modisco.java.BreakStatement;
import org.eclipse.gmt.modisco.java.CastExpression;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.ClassInstanceCreation;
import org.eclipse.gmt.modisco.java.ConditionalExpression;
import org.eclipse.gmt.modisco.java.ConstructorDeclaration;
import org.eclipse.gmt.modisco.java.ConstructorInvocation;
import org.eclipse.gmt.modisco.java.EnhancedForStatement;
import org.eclipse.gmt.modisco.java.EnumConstantDeclaration;
import org.eclipse.gmt.modisco.java.EnumDeclaration;
import org.eclipse.gmt.modisco.java.ExpressionStatement;
import org.eclipse.gmt.modisco.java.FieldAccess;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.ForStatement;
import org.eclipse.gmt.modisco.java.IfStatement;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.InfixExpression;
import org.eclipse.gmt.modisco.java.InstanceofExpression;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.MethodInvocation;
import org.eclipse.gmt.modisco.java.NullLiteral;
import org.eclipse.gmt.modisco.java.NumberLiteral;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.ParenthesizedExpression;
import org.eclipse.gmt.modisco.java.PostfixExpression;
import org.eclipse.gmt.modisco.java.PrefixExpression;
import org.eclipse.gmt.modisco.java.ReturnStatement;
import org.eclipse.gmt.modisco.java.SingleVariableAccess;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.StringLiteral;
import org.eclipse.gmt.modisco.java.SuperConstructorInvocation;
import org.eclipse.gmt.modisco.java.SuperMethodInvocation;
import org.eclipse.gmt.modisco.java.SwitchCase;
import org.eclipse.gmt.modisco.java.SwitchStatement;
import org.eclipse.gmt.modisco.java.ThisExpression;
import org.eclipse.gmt.modisco.java.ThrowStatement;
import org.eclipse.gmt.modisco.java.TryStatement;
import org.eclipse.gmt.modisco.java.TypeLiteral;
import org.eclipse.gmt.modisco.java.VariableDeclarationExpression;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;
import org.eclipse.gmt.modisco.java.WhileStatement;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;

/**
 * EMF model switch to traverse AST nodes to return a list of sub AST nodes to be indexed.
 * It only returns the direct children of an AST node and does not recursively collect
 * all nodes of the according AST sub tree.
 *
 * Standard elements of the java language are not included in the returns:
 * <ul>
 *  <li>Modifiers</li>
 *  <li>TypeAccesses</li>
 * </ul>
 *
 * @author Benjamin Klatt
 *
 */
@SuppressWarnings("restriction")
public class ASTNodeChildrenSelector extends JavaSwitch<List<ASTNode>> {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(ASTNodeChildrenSelector.class);

    /**
     * Do switch method enhanced to globally remove all null values.
     * {@inheritDoc}
     */
    @Override
    protected List<ASTNode> doSwitch(EClass eClass, EObject eObject) {
        List<ASTNode> nodes = super.doSwitch(eClass, eObject);
        nodes.removeAll(Collections.singleton(null));
        return nodes;
    }

    /**
     * Add the return statement itself and recursively process the
     * expression of the return statement.
     *
     * @param returnStatement The return statement to traverse.
     * @return The statement itself and the enclosed sub AST nodes.
     */
    @Override
    public List<ASTNode> caseReturnStatement(ReturnStatement returnStatement) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.add(returnStatement.getExpression());
        return childNodes;
    }

    /**
     * Add the fragments and annotations of a variable declaration statement.
     *
     * @param varDeclStmt The variable declaration statement to traverse.
     * @return The statement's fragments and annotations.
     */
    @Override
    public List<ASTNode> caseVariableDeclarationStatement(VariableDeclarationStatement varDeclStmt) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.addAll(varDeclStmt.getFragments());
        childNodes.addAll(varDeclStmt.getAnnotations());
        return childNodes;
    }

    @Override
    public List<ASTNode> caseVariableDeclarationExpression(VariableDeclarationExpression exp) {
        return new ArrayList<ASTNode>(exp.getFragments());
    }

    @Override
    public List<ASTNode> caseClassDeclaration(ClassDeclaration classDecl) {
        return new ArrayList<ASTNode>(classDecl.getBodyDeclarations());
    }

    /**
     * Get the fragments initializer expression as sub element.
     * {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseVariableDeclarationFragment(VariableDeclarationFragment varDeclFrgmt) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.add(varDeclFrgmt.getInitializer());
        return childNodes;
    }

    /**
     * Return the arguments of the class instance creation as it's sub nodes.
     * {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseClassInstanceCreation(ClassInstanceCreation instanceCreation) {
        return new ArrayList<ASTNode>(instanceCreation.getArguments());
    }

    @Override
    public List<ASTNode> caseArrayCreation(ArrayCreation creation) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.addAll(creation.getDimensions());
        childNodes.add(creation.getInitializer());
        return childNodes;
    }

    @Override
    public List<ASTNode> caseEnumConstantDeclaration(EnumConstantDeclaration decl) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.addAll(decl.getArguments());
        childNodes.add(decl.getInitializer());
        return childNodes;
    }

    /**
     * Return the arguments of the method invocation as it's sub nodes.
     * {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseMethodInvocation(MethodInvocation invocation) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.addAll(invocation.getArguments());
        childNodes.addAll(invocation.getTypeArguments());
        return childNodes;
    }

    @Override
    public List<ASTNode> caseSuperMethodInvocation(SuperMethodInvocation invocation) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.addAll(invocation.getArguments());
        childNodes.addAll(invocation.getTypeArguments());
        return childNodes;
    }

    @Override
    public List<ASTNode> caseSuperConstructorInvocation(SuperConstructorInvocation invocation) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.addAll(invocation.getArguments());
        childNodes.addAll(invocation.getTypeArguments());
        return childNodes;
    }

    @Override
    public List<ASTNode> caseConstructorInvocation(ConstructorInvocation invocation) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.addAll(invocation.getArguments());
        childNodes.addAll(invocation.getTypeArguments());
        return childNodes;
    }

    /**
     * Return the expression of the expression statement as sub element.
     * {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseExpressionStatement(ExpressionStatement expressionStatement) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.add(expressionStatement.getExpression());
        return childNodes;
    }

    /**
     * Get the conditional expression, the then statement, and the else statement
     * as sub nodes.
     * {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseIfStatement(IfStatement stmt) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.add(stmt.getExpression());
        childNodes.add(stmt.getThenStatement());
        childNodes.add(stmt.getElseStatement());
        return childNodes;
    }

    @Override
    public List<ASTNode> caseSwitchStatement(SwitchStatement stmt) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.add(stmt.getExpression());
        childNodes.addAll(stmt.getStatements());
        return childNodes;
    }

    @Override
    public List<ASTNode> caseSwitchCase(SwitchCase switchCase) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.add(switchCase.getExpression());
        return childNodes;
    }

    @Override
    public List<ASTNode> caseBreakStatement(BreakStatement object) {
        return new ArrayList<ASTNode>();
    }

    @Override
    public List<ASTNode> caseAssertStatement(AssertStatement stmt) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.add(stmt.getExpression());
        childNodes.add(stmt.getMessage());
        return childNodes;
    }

    @Override
    public List<ASTNode> caseForStatement(ForStatement stmt) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.add(stmt.getExpression());
        childNodes.add(stmt.getBody());
        childNodes.addAll(stmt.getInitializers());
        childNodes.addAll(stmt.getUpdaters());
        return childNodes;
    }

    @Override
    public List<ASTNode> caseWhileStatement(WhileStatement stmt) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.add(stmt.getExpression());
        childNodes.add(stmt.getBody());
        return childNodes;
    }

    @Override
    public List<ASTNode> caseEnhancedForStatement(EnhancedForStatement stmt) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.add(stmt.getBody());
        childNodes.add(stmt.getExpression());
        childNodes.add(stmt.getParameter());
        return childNodes;
    }

    /**
     * Get the sub packages and contained classes, interfaces, and enumerations
     * as child nodes.
     * {@inheritDoc}
     */
    @Override
    public List<ASTNode> casePackage(Package packageNode) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.addAll(packageNode.getOwnedPackages());
        childNodes.addAll(packageNode.getOwnedElements());
        return childNodes;
    }

    /**
     * Get the parameters, type parameters, and the body of the method declaration
     * to the list of sub AST nodes.
     * {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseMethodDeclaration(MethodDeclaration methodDeclaration) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.addAll(methodDeclaration.getParameters());
        childNodes.addAll(methodDeclaration.getTypeParameters());
        childNodes.add(methodDeclaration.getBody());
        return childNodes;
    }

    /**
     * Get the parameters, type parameters, and the body of the constructor declaration
     * to the list of sub AST nodes.
     * {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseConstructorDeclaration(ConstructorDeclaration decl) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.addAll(decl.getParameters());
        childNodes.addAll(decl.getTypeParameters());
        childNodes.add(decl.getBody());
        return childNodes;
    }

    /**
     * Get the fragments of a field as sub ast nodes.
     * {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseFieldDeclaration(FieldDeclaration fieldDeclaration) {
        return new ArrayList<ASTNode>(fieldDeclaration.getFragments());
    }

    /**
     * Get the enum constants and the body of the enum declaration as sub AST nodes.
     * {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseEnumDeclaration(EnumDeclaration decl) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.addAll(decl.getEnumConstants());
        childNodes.addAll(decl.getBodyDeclarations());
        return childNodes;
    }

    /**
     * Get the contained statements of the block as sub node elements.
     * {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseBlock(Block block) {
        return new ArrayList<ASTNode>(block.getStatements());
    }

    /**
     * Get the left and right hand side elements as sub ast nodes
     * for the assignment.
     * {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseAssignment(Assignment assignment) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.add(assignment.getLeftHandSide());
        childNodes.add(assignment.getRightHandSide());
        return childNodes;
    }

    /**
     * Get the extended operands, left hand and right hand side expressions
     * as sub ast node elements for the expression.
     * {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseInfixExpression(InfixExpression exp) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.addAll(exp.getExtendedOperands());
        childNodes.add(exp.getLeftOperand());
        childNodes.add(exp.getRightOperand());
        return childNodes;
    }

    @Override
    public List<ASTNode> caseConditionalExpression(ConditionalExpression exp) {
        List<ASTNode> childNodes = new ArrayList<ASTNode>();
        childNodes.add(exp.getExpression());
        childNodes.add(exp.getElseExpression());
        childNodes.add(exp.getThenExpression());
        return childNodes;

    }

    @Override
    public List<ASTNode> caseInstanceofExpression(InstanceofExpression exp) {
        return new ArrayList<ASTNode>(Collections.singleton(exp.getLeftOperand()));
    }

    @Override
    public List<ASTNode> casePrefixExpression(PrefixExpression exp) {
        return new ArrayList<ASTNode>(Collections.singleton(exp.getOperand()));
    }

    @Override
    public List<ASTNode> casePostfixExpression(PostfixExpression exp) {
        return new ArrayList<ASTNode>(Collections.singleton(exp.getOperand()));
    }

    /**
     * This expression has no children to return.
     * {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseThisExpression(ThisExpression object) {
        return new ArrayList<ASTNode>();
    }

    @Override
    public List<ASTNode> caseCastExpression(CastExpression castExp) {
        return new ArrayList<ASTNode>(Collections.singleton(castExp.getExpression()));
    }

    @Override
    public List<ASTNode> caseParenthesizedExpression(ParenthesizedExpression pExp) {
        return new ArrayList<ASTNode>(Collections.singleton(pExp.getExpression()));
    }

    @Override
    public List<ASTNode> caseSingleVariableDeclaration(SingleVariableDeclaration decl) {
        return new ArrayList<ASTNode>(Collections.singleton(decl.getInitializer()));
    }

    @Override
    public List<ASTNode> caseTryStatement(TryStatement stmt) {
        return new ArrayList<ASTNode>(Collections.singleton(stmt.getBody()));
    }

    @Override
    public List<ASTNode> caseThrowStatement(ThrowStatement stmt) {
        return new ArrayList<ASTNode>(Collections.singleton(stmt.getExpression()));
    }

    @Override
    public List<ASTNode> caseArrayAccess(ArrayAccess access) {
        return new ArrayList<ASTNode>(Collections.singleton(access.getIndex()));
    }

    @Override
    public List<ASTNode> caseArrayInitializer(ArrayInitializer initializer) {
        return new ArrayList<ASTNode>(initializer.getExpressions());
    }

    /**
     * A single variable access has no children of interest so an empty list is returned.
     * {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseSingleVariableAccess(SingleVariableAccess object) {
        return new ArrayList<ASTNode>();
    }

    /**
     * A single variable access has no children of interest so an empty list is returned.
     * {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseFieldAccess(FieldAccess access) {
        return new ArrayList<ASTNode>();
    }

    /**
     * An import declaration has no children of interest so an empty list is returned.
     * {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseImportDeclaration(ImportDeclaration importDeclaration) {
        return new ArrayList<ASTNode>();
    }

    /**
     * Literals have no children to return.
     * {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseTypeLiteral(TypeLiteral typeLiteral) {
        return new ArrayList<ASTNode>();
    }

    /**
     * Literals have no children to return.
     * {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseNullLiteral(NullLiteral object) {
        return new ArrayList<ASTNode>();
    }

    /**
     * Literals have no children to return.
     * {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseBooleanLiteral(BooleanLiteral object) {
        return new ArrayList<ASTNode>();
    }

    /**
     * Literals have no children to return.
     * {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseStringLiteral(StringLiteral object) {
        return new ArrayList<ASTNode>();
    }

    /**
     * Literals have no children to return.
     * {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseNumberLiteral(NumberLiteral object) {
        return new ArrayList<ASTNode>();
    }

    @Override
    public List<ASTNode> defaultCase(EObject object) {
        logger.warn("[ASTNodeChildrenSelector] Yet not handled AST node: " + object.toString());
        return new ArrayList<ASTNode>();
    }

}
