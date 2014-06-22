/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.jamopp.vpm.analyzer.programdependency.references;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.expressions.AssignmentExpression;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.util.ExpressionsSwitch;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.util.ImportsSwitch;
import org.emftext.language.java.instantiations.NewConstructorCall;
import org.emftext.language.java.instantiations.util.InstantiationsSwitch;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.members.util.MembersSwitch;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.util.ReferencesSwitch;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Return;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.util.StatementsSwitch;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.util.TypesSwitch;
import org.emftext.language.java.variables.LocalVariable;
import org.splevo.vpm.analyzer.VPMAnalyzerUtil;

import com.google.common.collect.Lists;

/**
 * Switch to decide about which elements to return as referenced elements for a given JaMoPP
 * element.
 */
public class DefaultReferenceSelectorSwitch extends ComposedSwitch<List<Commentable>> {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(DefaultReferenceSelectorSwitch.class);

    /** Constructor to set up the sub switches. */
    public DefaultReferenceSelectorSwitch() {
        addSwitch(new StatementsReferencedElementsSwitch(this));
        addSwitch(new ExpressionsReferencedElementsSwitch(this));
        addSwitch(new MembersReferencedElementsSwitch(this));
        addSwitch(new ImportReferencedElementsSwitch());
        addSwitch(new ReferencesReferencedElementsSwitch(this));
        addSwitch(new InstantiationsReferencedElementsSwitch(this));
        addSwitch(new TypesReferencedElementsSwitch());
    }

    /**
     * Wrapper for the super method implementation ensuring that at least an empty list is always
     * returned.
     *
     * {@inheritDoc}
     */
    @Override
    public List<Commentable> doSwitch(EObject eObject) {
        List<Commentable> elements = null;
        if (!VPMAnalyzerUtil.isNullOrProxy(eObject)) {
            elements = super.doSwitch(eObject);
        }

        if (elements == null) {
            elements = Lists.newArrayList();
        }

        return elements;
    }

    /**
     * Switch to decide about referenced elements for import elements.
     */
    private class ImportReferencedElementsSwitch extends ImportsSwitch<List<Commentable>> {

        @Override
        public List<Commentable> caseImport(Import object) {
            return Lists.newArrayList((Commentable) object);
        }

    }

    /**
     * Switch to decide about referenced elements for reference elements.
     */
    private class TypesReferencedElementsSwitch extends TypesSwitch<List<Commentable>> {

        /**
         * For type references, not only the type itself, but also according import statements in
         * the same compilation unit are detected as they are part of the reference.
         */
        @Override
        public List<Commentable> caseTypeReference(TypeReference typeReference) {
            Type type = typeReference.getTarget();

            ArrayList<Commentable> refElements = Lists.newArrayList((Commentable) type);

            CompilationUnit cu = typeReference.getContainingCompilationUnit();
            EList<ClassifierImport> imports = cu.getChildrenByType(ClassifierImport.class);
            for (ClassifierImport classifierImport : imports) {
                if (classifierImport.getClassifier().equals(type)) {
                    refElements.add(classifierImport);
                }
            }

            return refElements;
        }
    }

    /**
     * Switch to decide about referenced elements for reference elements.
     */
    private class ReferencesReferencedElementsSwitch extends ReferencesSwitch<List<Commentable>> {

        private DefaultReferenceSelectorSwitch parentSwitch;

        public ReferencesReferencedElementsSwitch(DefaultReferenceSelectorSwitch parentSwitch) {
            this.parentSwitch = parentSwitch;
        }

        @Override
        public List<Commentable> caseMethodCall(MethodCall methodCall) {
            ArrayList<Commentable> refElements = Lists.newArrayList();
            for (Expression expression : methodCall.getArguments()) {
                refElements.addAll(parentSwitch.doSwitch(expression));
            }
            refElements.addAll(parentSwitch.doSwitch(methodCall.getTarget()));
            return refElements;
        }

        @Override
        public List<Commentable> caseIdentifierReference(IdentifierReference reference) {
            ArrayList<Commentable> refElements = Lists.newArrayList((Commentable) reference.getTarget());
            if (reference.getNext() != null) {
                refElements.addAll(parentSwitch.doSwitch(reference.getNext()));
            }
            return refElements;
        }
    }

    /**
     * Switch to decide about referenced elements for statement elements.
     */
    private class InstantiationsReferencedElementsSwitch extends InstantiationsSwitch<List<Commentable>> {

        private DefaultReferenceSelectorSwitch parentSwitch;

        public InstantiationsReferencedElementsSwitch(DefaultReferenceSelectorSwitch parentSwitch) {
            this.parentSwitch = parentSwitch;
        }

        @Override
        public List<Commentable> caseNewConstructorCall(NewConstructorCall call) {
            ArrayList<Commentable> refElements = Lists.newArrayList();
            for (Expression expression : call.getArguments()) {
                refElements.addAll(parentSwitch.doSwitch(expression));
            }
            refElements.addAll(parentSwitch.doSwitch(call.getTypeReference()));
            return refElements;
        }
    }

    /**
     * Switch to decide about referenced elements for statement elements.
     */
    private class StatementsReferencedElementsSwitch extends StatementsSwitch<List<Commentable>> {

        private DefaultReferenceSelectorSwitch parentSwitch;

        public StatementsReferencedElementsSwitch(DefaultReferenceSelectorSwitch parentSwitch) {
            this.parentSwitch = parentSwitch;
        }

        @Override
        public List<Commentable> caseReturn(Return returnStmt) {
            ArrayList<Commentable> refElements = Lists.newArrayList((Commentable) returnStmt);
            refElements.addAll(parentSwitch.doSwitch(returnStmt.getReturnValue()));
            return refElements;
        }

        @Override
        public List<Commentable> caseLocalVariableStatement(LocalVariableStatement lvs) {
            ArrayList<Commentable> refElements = Lists.newArrayList();

            LocalVariable variable = lvs.getVariable();
            if (variable != null) {
                refElements.add(lvs.getVariable());
                refElements.addAll(parentSwitch.doSwitch(variable.getTypeReference()));
                refElements.addAll(parentSwitch.doSwitch(variable.getInitialValue()));
                refElements.addAll(variable.getAdditionalLocalVariables());
            } else {
                logger.warn("VariableStatement without variable: " + lvs);
            }

            return refElements;
        }

        @Override
        public List<Commentable> caseExpressionStatement(ExpressionStatement stmt) {
            return parentSwitch.doSwitch(stmt.getExpression());
        }
    }

    /**
     * Switch to decide about referenced elements for statement elements.
     */
    private class ExpressionsReferencedElementsSwitch extends ExpressionsSwitch<List<Commentable>> {

        private DefaultReferenceSelectorSwitch parentSwitch;

        public ExpressionsReferencedElementsSwitch(DefaultReferenceSelectorSwitch parentSwitch) {
            this.parentSwitch = parentSwitch;
        }

        @Override
        public List<Commentable> caseAssignmentExpression(AssignmentExpression exp) {
            ArrayList<Commentable> refElements = Lists.newArrayList();
            refElements.addAll(parentSwitch.doSwitch(exp.getChild()));
            refElements.addAll(parentSwitch.doSwitch(exp.getValue()));
            return refElements;
        }
    }

    /**
     * Switch to decide about referenced elements for member elements.
     */
    private class MembersReferencedElementsSwitch extends MembersSwitch<List<Commentable>> {

        private DefaultReferenceSelectorSwitch parentSwitch;

        public MembersReferencedElementsSwitch(DefaultReferenceSelectorSwitch parentSwitch) {
            this.parentSwitch = parentSwitch;
        }

        /**
         * Return the method itself and scan the included statements for additional references.
         */
        @Override
        public List<Commentable> caseClassMethod(ClassMethod method) {
            ArrayList<Commentable> refElements = Lists.newArrayList((Commentable) method);
            for (Statement statement : method.getStatements()) {
                refElements.addAll(parentSwitch.doSwitch(statement));
            }
            return refElements;
        }

        @Override
        public List<Commentable> caseConstructor(Constructor constructor) {
            ArrayList<Commentable> refElements = Lists.newArrayList((Commentable) constructor);
            for (Statement statement : constructor.getStatements()) {
                refElements.addAll(parentSwitch.doSwitch(statement));
            }
            return refElements;
        }

        @Override
        public List<Commentable> caseMethod(Method object) {
            return Lists.newArrayList((Commentable) object);
        }
    }
}
