package org.splevo.vpm.analyzer.programstructure;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.AbstractMethodInvocation;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.EnumDeclaration;
import org.eclipse.gmt.modisco.java.ExpressionStatement;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.IfStatement;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.NamedElement;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.PackageAccess;
import org.eclipse.gmt.modisco.java.ReturnStatement;
import org.eclipse.gmt.modisco.java.SingleVariableAccess;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;

/**
 * A model element switch providing the logic to lookup referring AST nodes for each AST node type.
 * 
 * TODO: Check for statements manipulating the same variable -> might be part of the data flow
 * analyzer?!?
 * 
 * @author Benjamin Klatt
 * 
 */
public class ReferringASTNodeSwitch extends JavaSwitch<List<ASTNode>> {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(ReferringASTNodeSwitch.class);

    /**
     * Find AST nodes accessing the imported elements in the same compilation unit.
     * 
     * @param importDeclaration
     *            The import declaration to find influenced elements for.
     * @return The detected influenced AST nodes.
     */
    @Override
    public List<ASTNode> caseImportDeclaration(ImportDeclaration importDeclaration) {
        List<ASTNode> referringASTNodes = new ArrayList<ASTNode>();

        NamedElement importedElement = importDeclaration.getImportedElement();

        if (importedElement instanceof AbstractTypeDeclaration) {
            AbstractTypeDeclaration type = (AbstractTypeDeclaration) importedElement;

            for (TypeAccess typeAccess : type.getUsagesInTypeAccess()) {
                EObject typeAccessContainer = typeAccess.eContainer();
                if (typeAccessContainer instanceof ASTNode) {

                    ASTNode accessingNode = (ASTNode) typeAccessContainer;
                    EcoreUtil.resolveAll(accessingNode);
                    if (importDeclaration.getOriginalCompilationUnit().equals(
                            accessingNode.getOriginalCompilationUnit())) {
                        referringASTNodes.add(accessingNode);
                    
                    } else if (accessingNode.getOriginalCompilationUnit() == null) {
                        logger.warn("[ImportDeclaration] TypeAccess container has no compilation unit: "
                                + typeAccessContainer);
                    }

                } else {
                    logger.warn("[ImportDeclaration] TypeAccess container is not an ASTNode " + typeAccessContainer);
                }
            }
        } else {
            logger.warn("[ImportDeclaration] Yet not supported imported Element " + importedElement);
        }

        return referringASTNodes;
    }

    /**
     * Find all ASTNodes referring one of the variable fragments declared in a variable declaration
     * statement.
     * 
     * A forward program slice to find all influenced elements.
     * 
     * @param variableDeclarationStatement
     *            The statement to find influenced elements for.
     * @return The detected AST nodes.
     */
    @Override
    public List<ASTNode> caseVariableDeclarationStatement(VariableDeclarationStatement variableDeclarationStatement) {
        List<ASTNode> referringASTNodes = new ArrayList<ASTNode>();
        for (VariableDeclarationFragment fragment : variableDeclarationStatement.getFragments()) {
            referringASTNodes.addAll(getAccessesForFragment(fragment));
        }
        return referringASTNodes;
    }

    /**
     * Get the references for all declared variable fragments. {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseFieldDeclaration(FieldDeclaration fieldDeclaration) {
        List<ASTNode> referringASTNodes = new ArrayList<ASTNode>();
        for (VariableDeclarationFragment varDeclFragment : fieldDeclaration.getFragments()) {
            referringASTNodes.addAll(getAccessesForFragment(varDeclFragment));
        }
        return referringASTNodes;
    }

    /**
     * Add all usages in imports and type accesses, more precisely their eContainers, to the list of
     * referring AST nodes. {@inheritDoc}
     */
    @Override
    public List<ASTNode> caseClassDeclaration(ClassDeclaration decl) {
        List<ASTNode> referringASTNodes = new ArrayList<ASTNode>();
        referringASTNodes.addAll(decl.getUsagesInImports());
        for (TypeAccess typeAccess : decl.getUsagesInTypeAccess()) {
            if (typeAccess.eContainer() instanceof ASTNode) {
                referringASTNodes.add((ASTNode) typeAccess.eContainer());
            } else {
                logger.warn("Unsupported type of class access container: " + typeAccess.eContainer());
            }
        }
        return referringASTNodes;
    }

    @Override
    public List<ASTNode> caseMethodDeclaration(MethodDeclaration decl) {
        List<ASTNode> referringASTNodes = new ArrayList<ASTNode>();
        referringASTNodes.addAll(decl.getUsagesInImports());
        for (AbstractMethodInvocation invocation : decl.getUsages()) {
            if (invocation.eContainer() instanceof ASTNode) {
                referringASTNodes.add((ASTNode) invocation.eContainer());
            } else {
                logger.warn("Unsupported type of method invocation container: " + invocation.eContainer());
            }
        }
        return referringASTNodes;
    }

    @Override
    public List<ASTNode> caseEnumDeclaration(EnumDeclaration decl) {
        List<ASTNode> referringASTNodes = new ArrayList<ASTNode>();
        referringASTNodes.addAll(decl.getUsagesInImports());
        for (TypeAccess typeAccess : decl.getUsagesInTypeAccess()) {
            if (typeAccess.eContainer() instanceof ASTNode) {
                referringASTNodes.add((ASTNode) typeAccess.eContainer());
            } else {
                logger.warn("Unsupported type of enum access container: " + typeAccess.eContainer());
            }
        }
        return referringASTNodes;
    }

    /**
     * An if statement is referenced by none other element.
     * 
     * 
     * @param ifStatement
     *            The return statement to get referring AST nodes for.
     * @return An empty list because of no referring elements for if statements.
     */
    @Override
    public List<ASTNode> caseIfStatement(IfStatement ifStatement) {
        return new ArrayList<ASTNode>();
    }

    /**
     * Add all usages in imports and package access, more precisely their eContainers, to the list
     * of referring AST nodes. {@inheritDoc}
     */
    @Override
    public List<ASTNode> casePackage(Package packageNode) {
        List<ASTNode> referringASTNodes = new ArrayList<ASTNode>();
        referringASTNodes.addAll(packageNode.getUsagesInImports());
        for (PackageAccess packageAccess : packageNode.getUsagesInPackageAccess()) {
            if (packageAccess.eContainer() instanceof ASTNode) {
                referringASTNodes.add((ASTNode) packageAccess.eContainer());
            } else {
                logger.warn("Unsupported type of package access container: " + packageAccess.eContainer());
            }
        }
        return referringASTNodes;
    }

    /**
     * A return statement is referenced by none other element.
     * 
     * @param stmt
     *            The statement to get referring AST nodes for.
     * @return An empty list because of no referring elements for expression statements.
     */
    @Override
    public List<ASTNode> caseExpressionStatement(ExpressionStatement stmt) {
        return new ArrayList<ASTNode>();
    }

    /**
     * A return statement is referenced by none other element.
     * 
     * Only the caller of the return statement's method is influenced by the changed return value.
     * However, this is not part of the static structure program analyzer.
     * 
     * @param returnStatement
     *            The return statement to get referring AST nodes for.
     * @return An empty list because of no referring elements for return statements.
     */
    @Override
    public List<ASTNode> caseReturnStatement(ReturnStatement returnStatement) {
        return new ArrayList<ASTNode>();
    }

    @Override
    public List<ASTNode> defaultCase(EObject object) {
        logger.warn("[RefferingASTNodeTraverser] Yet not handled ast node: " + object.toString());
        return new ArrayList<ASTNode>();
    }

    /**
     * Get the accesses for a variable declaration fragment.
     * 
     * @param fragment
     *            The fragment to look up for.
     * @return The list of identified accesses.
     */
    private List<ASTNode> getAccessesForFragment(VariableDeclarationFragment fragment) {
        List<ASTNode> referringASTNodes = new ArrayList<ASTNode>();
        for (SingleVariableAccess variableAccess : fragment.getUsageInVariableAccess()) {
            EObject variableAccessContainer = variableAccess.eContainer();
            if (variableAccessContainer instanceof ASTNode) {

                ASTNode accessingNode = (ASTNode) variableAccessContainer;
                referringASTNodes.add(accessingNode);
            } else {
                logger.warn("[VarDeclStmt] TypeAccess container is not an ASTNode " + variableAccessContainer);
            }
        }
        return referringASTNodes;
    }
}
