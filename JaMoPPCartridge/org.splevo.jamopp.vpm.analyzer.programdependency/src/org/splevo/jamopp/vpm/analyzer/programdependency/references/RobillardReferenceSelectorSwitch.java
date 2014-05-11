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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.util.ClassifiersSwitch;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.util.ContainersSwitch;
import org.emftext.language.java.expressions.AssignmentExpression;
import org.emftext.language.java.expressions.CastExpression;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.InstanceOfExpression;
import org.emftext.language.java.expressions.util.ExpressionsSwitch;
import org.emftext.language.java.instantiations.NewConstructorCall;
import org.emftext.language.java.instantiations.util.InstantiationsSwitch;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.members.util.MembersSwitch;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.ReferenceableElement;
import org.emftext.language.java.references.util.ReferencesSwitch;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Return;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.statements.util.StatementsSwitch;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.variables.LocalVariable;
import org.splevo.vpm.analyzer.VPMAnalyzerUtil;

import com.google.common.collect.Lists;

/**
 * Switch to decide about which elements to return as referenced elements for a given JaMoPP
 * element.
 *
 * Robillar et al. consider references between methods, fields and classes. For methods, they
 * consider "references defined in the method body", which means statements and expressions.
 *
 * According to these rules, this switch collects classes, fields, and methods. For the later, the
 * contained statements and expressions respectively the elements referenced by them are indexed.
 * The JaMoPP model explicitly classifies constructors. They are assumed to be treat the same way as
 * methods. For fields, also the additional fields must be respected.
 */
public class RobillardReferenceSelectorSwitch extends ComposedSwitch<List<Commentable>> {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(RobillardReferenceSelectorSwitch.class);

    /** Constructor to set up the sub switches. */
    public RobillardReferenceSelectorSwitch() {
        addSwitch(new ClassifiersReferencedElementsSwitch(this));
        addSwitch(new MembersReferencedElementsSwitch(this));
        addSwitch(new StatementsReferencedElementsSwitch(this));
        addSwitch(new ReferencesReferencedElementsSwitch(this));
        addSwitch(new InstantiationsReferencedElementsSwitch(this));
        addSwitch(new ExpressionsReferencedElementsSwitch(this));
        addSwitch(new ContainersReferencedElementsSwitch(this));
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
     * Switch to decide about referenced elements for member elements.
     */
    private class ContainersReferencedElementsSwitch extends ContainersSwitch<List<Commentable>> {

        private RobillardReferenceSelectorSwitch parentSwitch;

        public ContainersReferencedElementsSwitch(RobillardReferenceSelectorSwitch parentSwitch) {
            this.parentSwitch = parentSwitch;
        }

        @Override
        public List<Commentable> caseCompilationUnit(CompilationUnit cu) {
            return parentSwitch.doSwitch(cu.getContainedClass());
        }
    }

    /**
     * Switch to decide about referenced elements for member elements.
     */
    private class ClassifiersReferencedElementsSwitch extends ClassifiersSwitch<List<Commentable>> {

        private RobillardReferenceSelectorSwitch parentSwitch;

        public ClassifiersReferencedElementsSwitch(RobillardReferenceSelectorSwitch parentSwitch) {
            this.parentSwitch = parentSwitch;
        }

        @Override
        public List<Commentable> caseClass(Class classObject) {

            ArrayList<Commentable> refElements = Lists.newArrayList((Commentable) classObject);

            for (Member member : classObject.getMembers()) {
                refElements.addAll(parentSwitch.doSwitch(member));
            }

            TypeReference extendsReference = classObject.getExtends();
            if (extendsReference != null) {
                refElements.add(extendsReference.getTarget());
            }

            return refElements;
        }
    }

    /**
     * Switch to decide about referenced elements for member elements.
     */
    private class MembersReferencedElementsSwitch extends MembersSwitch<List<Commentable>> {

        private RobillardReferenceSelectorSwitch parentSwitch;

        public MembersReferencedElementsSwitch(RobillardReferenceSelectorSwitch parentSwitch) {
            this.parentSwitch = parentSwitch;
        }

        /**
         * Scan the statements contained in the constructor as done for methods. However, the
         * constructor itself is not respected by Robillard. Instead, constructor calls are treated
         * as part of the "create" relationship.
         */
        @Override
        public List<Commentable> caseConstructor(Constructor constructor) {
            ArrayList<Commentable> refElements = Lists.newArrayList();
            for (Statement statement : constructor.getStatements()) {
                refElements.addAll(parentSwitch.doSwitch(statement));
            }
            return refElements;
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
        public List<Commentable> caseMethod(Method object) {
            return Lists.newArrayList((Commentable) object);
        }

        @Override
        public List<Commentable> caseField(Field field) {
            ArrayList<Commentable> refElements = Lists.newArrayList((Commentable) field);
            refElements.addAll(field.getAdditionalFields());
            refElements.addAll(parentSwitch.doSwitch(field.getInitialValue()));
            return refElements;
        }
    }

    /**
     * Switch to decide about referenced elements for statement elements.
     */
    private class StatementsReferencedElementsSwitch extends StatementsSwitch<List<Commentable>> {

        private RobillardReferenceSelectorSwitch parentSwitch;

        public StatementsReferencedElementsSwitch(RobillardReferenceSelectorSwitch parentSwitch) {
            this.parentSwitch = parentSwitch;
        }

        @Override
        public List<Commentable> caseReturn(Return returnStmt) {
            ArrayList<Commentable> refElements = Lists.newArrayList();
            refElements.addAll(parentSwitch.doSwitch(returnStmt.getReturnValue()));
            return refElements;
        }

        @Override
        public List<Commentable> caseCondition(Condition condition) {
            ArrayList<Commentable> refElements = Lists.newArrayList();
            refElements.addAll(parentSwitch.doSwitch(condition.getCondition()));
            refElements.addAll(parentSwitch.doSwitch(condition.getElseStatement()));
            refElements.addAll(parentSwitch.doSwitch(condition.getStatement()));
            return refElements;
        }

        @Override
        public List<Commentable> caseStatementListContainer(StatementListContainer container) {
            ArrayList<Commentable> refElements = Lists.newArrayList();
            for (Statement statement : container.getStatements()) {
                refElements.addAll(parentSwitch.doSwitch(statement));
            }
            return refElements;
        }

        @Override
        public List<Commentable> caseExpressionStatement(ExpressionStatement stmt) {
            return parentSwitch.doSwitch(stmt.getExpression());
        }

        /**
         * Robillard et al do not consider references to variables. So the variable itself is not
         * indexed. They also do not care about the type of the declaration. Thus, only the intial
         * value is considered as it might contained a class instantiation or another reference.
         */
        @Override
        public List<Commentable> caseLocalVariableStatement(LocalVariableStatement lvs) {
            ArrayList<Commentable> refElements = Lists.newArrayList();

            LocalVariable variable = lvs.getVariable();
            if (variable != null) {
                refElements.addAll(parentSwitch.doSwitch(variable.getInitialValue()));
            } else {
                logger.warn("VariableStatement without variable: " + lvs);
            }

            return refElements;
        }

    }

    /**
     * Switch to decide about referenced elements for reference elements.
     */
    private class ExpressionsReferencedElementsSwitch extends ExpressionsSwitch<List<Commentable>> {

        private RobillardReferenceSelectorSwitch parentSwitch;

        public ExpressionsReferencedElementsSwitch(RobillardReferenceSelectorSwitch parentSwitch) {
            this.parentSwitch = parentSwitch;
        }

        @Override
        public List<Commentable> caseAssignmentExpression(AssignmentExpression exp) {
            ArrayList<Commentable> refElements = Lists.newArrayList();
            refElements.addAll(parentSwitch.doSwitch(exp.getChild()));
            refElements.addAll(parentSwitch.doSwitch(exp.getValue()));
            return refElements;
        }

        @Override
        public List<Commentable> caseCastExpression(CastExpression exp) {
            ArrayList<Commentable> refElements = Lists.newArrayList();
            refElements.add(exp.getAlternativeType());
            return refElements;
        }

        @Override
        public List<Commentable> caseInstanceOfExpression(InstanceOfExpression iof) {
            ArrayList<Commentable> refElements = Lists.newArrayList();
            refElements.add(iof.getTypeReference().getTarget());
            return refElements;
        }

    }

    /**
     * Switch to decide about referenced elements for reference elements.
     */
    private class ReferencesReferencedElementsSwitch extends ReferencesSwitch<List<Commentable>> {

        private RobillardReferenceSelectorSwitch parentSwitch;

        public ReferencesReferencedElementsSwitch(RobillardReferenceSelectorSwitch parentSwitch) {
            this.parentSwitch = parentSwitch;
        }

        /**
         * Register the called method as well as possible content from the arguments, as they might
         * contain additional identifier references or method calls.
         */
        @Override
        public List<Commentable> caseMethodCall(MethodCall methodCall) {
            ArrayList<Commentable> refElements = Lists.newArrayList((Commentable) methodCall.getTarget());
            for (Expression expression : methodCall.getArguments()) {
                refElements.addAll(parentSwitch.doSwitch(expression));
            }
            return refElements;
        }

        @Override
        public List<Commentable> caseIdentifierReference(IdentifierReference reference) {

            ReferenceableElement target = reference.getTarget();
            if (!(target instanceof Field || target instanceof org.emftext.language.java.classifiers.Class || target instanceof Method)) {
                return Lists.newArrayList();
            }

            ArrayList<Commentable> refElements = Lists.newArrayList((Commentable) target);
            if (reference.getNext() != null) {
                refElements.addAll(parentSwitch.doSwitch(reference.getNext()));
            }
            return refElements;
        }
    }

    /**
     * Switch to decide about referenced elements for instantiating elements.
     */
    private class InstantiationsReferencedElementsSwitch extends InstantiationsSwitch<List<Commentable>> {

        private RobillardReferenceSelectorSwitch parentSwitch;

        public InstantiationsReferencedElementsSwitch(RobillardReferenceSelectorSwitch parentSwitch) {
            this.parentSwitch = parentSwitch;
        }

        @Override
        public List<Commentable> caseNewConstructorCall(NewConstructorCall call) {
            ArrayList<Commentable> refElements = Lists.newArrayList();
            for (Expression expression : call.getArguments()) {
                refElements.addAll(parentSwitch.doSwitch(expression));
            }
            refElements.add(call.getTypeReference().getTarget());
            return refElements;
        }
    }
}
