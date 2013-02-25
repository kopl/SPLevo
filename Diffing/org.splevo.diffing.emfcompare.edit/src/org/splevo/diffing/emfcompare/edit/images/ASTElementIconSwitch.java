package org.splevo.diffing.emfcompare.edit.images;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.AnonymousClassDeclaration;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.IfStatement;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.InterfaceDeclaration;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;

/**
 * Model element switch to decide for an icon to load.
 * @author Benjamin Klatt
 *
 */
class ASTElementIconSwitch extends JavaSwitch<String>{
    
    @Override
    public String caseFieldDeclaration(FieldDeclaration fieldDecl) {
        
        if(fieldDecl != null && fieldDecl.getModifier() != null){
            switch (fieldDecl.getModifier().getVisibility()) {
            case PUBLIC:
                return "ast_modisco/field/public";
            case PRIVATE:
                return "ast_modisco/field/private";
            case PROTECTED:
                return "ast_modisco/field/protected";
            case NONE:
            default:
                return "ast_modisco/field/default";
            }
        }
        return "ast_modisco/field/fieldDeclaration";
    }
    
    @Override
    public String caseAbstractMethodDeclaration(AbstractMethodDeclaration method) {
        if(method != null && method.getModifier() != null){
            switch (method.getModifier().getVisibility()) {
            case PUBLIC:
                return "ast_modisco/methods/public";
            case PRIVATE:
                return "ast_modisco/methods/private";
            case PROTECTED:
                return "ast_modisco/methods/protected";
            case NONE:
            default:
                return "ast_modisco/methods/default";
            }
        }
        return "ast_modisco/methods/methodDeclaration";
    }
    
    @Override
    public String caseInterfaceDeclaration(InterfaceDeclaration interfaceDecl) {
        return "ast_modisco/interfaces/interface";
    }
    
    @Override
    public String caseImportDeclaration(ImportDeclaration importDecl) {
        if(importDecl.isStatic()){
            return "ast_modisco/importStatic";
        } else {
            return "ast_modisco/import";
        }
    }
    
    @Override
    public String caseAnonymousClassDeclaration(AnonymousClassDeclaration object) {
        return "ast_modisco/class/anonymousClass";
    }
    
    @Override
    public String caseAbstractTypeDeclaration(AbstractTypeDeclaration object) {
        return "ast_modisco/class/class";
    }
    
    @Override
    public String casePackage(Package object) {
        return "ast_modisco/package";
    }
    
    @Override
    public String caseBlock(Block object) {
        return "ast_modisco/statements/block";
    }
    
    @Override
    public String caseIfStatement(IfStatement object) {
        return "ast_modisco/statements/if";
    }
    
    @Override
    public String caseStatement(Statement object) {
        return "ast/statement-single";
    }
    
    @Override
    public String defaultCase(EObject object) {
        return "ast/default";
    }
    
}