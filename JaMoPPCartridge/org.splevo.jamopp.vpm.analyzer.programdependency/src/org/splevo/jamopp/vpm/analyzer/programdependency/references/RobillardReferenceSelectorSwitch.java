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
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.classifiers.util.ClassifiersSwitch;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.util.ContainersSwitch;
import org.emftext.language.java.expressions.AssignmentExpression;
import org.emftext.language.java.expressions.CastExpression;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.ExpressionList;
import org.emftext.language.java.expressions.InstanceOfExpression;
import org.emftext.language.java.expressions.util.ExpressionsSwitch;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.util.ImportsSwitch;
import org.emftext.language.java.instantiations.NewConstructorCall;
import org.emftext.language.java.instantiations.util.InstantiationsSwitch;
import org.emftext.language.java.members.AdditionalField;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.members.util.MembersSwitch;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.parameters.util.ParametersSwitch;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.ReferenceableElement;
import org.emftext.language.java.references.util.ReferencesSwitch;
import org.emftext.language.java.statements.CatchBlock;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.ForLoop;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Return;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.statements.TryBlock;
import org.emftext.language.java.statements.WhileLoop;
import org.emftext.language.java.statements.util.StatementsSwitch;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.util.TypesSwitch;
import org.emftext.language.java.variables.AdditionalLocalVariable;
import org.emftext.language.java.variables.LocalVariable;
import org.splevo.jamopp.util.JaMoPPElementUtil;
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
 *
 * DesignDecision Minimum Reference Granularity: To fine grained elements (e.g. expressions and
 * references) are processed but the next parent element on an upper level (minimum statement)
 * updates the references source afterwards.
 */
public class RobillardReferenceSelectorSwitch extends ComposedSwitch<List<Reference>> {

    /** The logger for this class. */
    @SuppressWarnings("unused")
    private Logger logger = Logger.getLogger(RobillardReferenceSelectorSwitch.class);

    private boolean extendedMode = true;

    /**
     * Constructor to set up the sub switches.
     *
     * @param extendedMode
     *            Flag to decide about running the selector in extended or original mode.
     */
    public RobillardReferenceSelectorSwitch(boolean extendedMode) {
        this.extendedMode = extendedMode;
        addSwitch(new ClassifiersReferencedElementsSwitch(this));
        addSwitch(new MembersReferencedElementsSwitch(this));
        addSwitch(new StatementsReferencedElementsSwitch(this));
        addSwitch(new ReferencesReferencedElementsSwitch(this));
        addSwitch(new InstantiationsReferencedElementsSwitch(this));
        addSwitch(new ExpressionsReferencedElementsSwitch(this));
        addSwitch(new ContainersReferencedElementsSwitch(this));
        addSwitch(new TypesReferencedElementsSwitch(this));
        if (extendedMode) {
            addSwitch(new ImportReferencedElementsSwitch());
            addSwitch(new ParametersReferencedElementsSwitch(this));
        }
    }

    /**
     * Update the source of a list of references.
     *
     * @param source
     *            The new source to set.
     * @param references
     *            The references to update.
     */
    private void updateSource(Commentable source, List<Reference> references) {
        for (Reference reference : references) {
            reference.setSource(source);
        }
    }

    /**
     * Update the reference type of a list of references.
     *
     * @param type
     *            The new type to set.
     * @param references
     *            The references to update.
     */
    private void updateType(ReferenceType type, List<Reference> references) {
        for (Reference reference : references) {
            reference.setType(type);
        }
    }

    /**
     * Update the reference type of a list of references but only those references having the old
     * type set.
     *
     * @param oldType
     *            The old type to replace.
     * @param newType
     *            The new type to set.
     * @param references
     *            The references to update.
     */
    private void updateType(ReferenceType oldType, ReferenceType newType, List<Reference> references) {
        for (Reference reference : references) {
            if (reference.getType() == oldType) {
                reference.setType(newType);
            }
        }
    }

    /**
     * Wrapper for the super method implementation ensuring that at least an empty list is always
     * returned.
     *
     * {@inheritDoc}
     */
    @Override
    public List<Reference> doSwitch(EObject eObject) {
        List<Reference> elements = null;
        if (!VPMAnalyzerUtil.isNullOrProxy(eObject)) {
            elements = super.doSwitch(eObject);
        }

        if (elements == null) {
            elements = Lists.newArrayList();
        }

        return elements;
    }

    /**
     * Switch to decide about referenced elements for container elements.
     */
    private class ContainersReferencedElementsSwitch extends ContainersSwitch<List<Reference>> {

        private RobillardReferenceSelectorSwitch parentSwitch;

        public ContainersReferencedElementsSwitch(RobillardReferenceSelectorSwitch parentSwitch) {
            this.parentSwitch = parentSwitch;
        }

        /**
         * Process all classifiers contained in a compilation unit. If a specific element should be
         * included or not (depending on the selectors current mode) is decided in the according
         * case method to not duplicate the elements' type checks.
         */
        @Override
        public List<Reference> caseCompilationUnit(CompilationUnit cu) {
            List<Reference> references = Lists.newArrayList();
            for (ConcreteClassifier cc : cu.getClassifiers()) {
                references.addAll(parentSwitch.doSwitch(cc));
            }
            return references;
        }
    }

    /**
     * Switch to decide about referenced elements for type elements.
     */
    private class TypesReferencedElementsSwitch extends TypesSwitch<List<Reference>> {

        @SuppressWarnings("unused")
        private RobillardReferenceSelectorSwitch parentSwitch;

        public TypesReferencedElementsSwitch(RobillardReferenceSelectorSwitch parentSwitch) {
            this.parentSwitch = parentSwitch;
        }

        @Override
        public List<Reference> caseTypeReference(TypeReference reference) {
            List<Reference> references = Lists.newArrayList();
            Commentable refererrer = (Commentable) reference.eContainer();
            Type target = reference.getTarget();
            references.add(new Reference(refererrer, target, ReferenceType.SuperType));
            return references;
        }

    }

    /**
     * Switch to decide about referenced elements for import elements.
     */
    private class ImportReferencedElementsSwitch extends ImportsSwitch<List<Reference>> {

        @Override
        public List<Reference> caseImport(Import importDecl) {
            return Lists.newArrayList(new Reference(importDecl));
        }

    }

    /**
     * Switch to decide about referenced elements for member elements.
     */
    private class ClassifiersReferencedElementsSwitch extends ClassifiersSwitch<List<Reference>> {

        private RobillardReferenceSelectorSwitch parentSwitch;

        public ClassifiersReferencedElementsSwitch(RobillardReferenceSelectorSwitch parentSwitch) {
            this.parentSwitch = parentSwitch;
        }

        @Override
        public List<Reference> caseClass(Class classObject) {

            ArrayList<Reference> references = Lists.newArrayList();

            for (Member member : classObject.getMembers()) {

                if (member instanceof Field || member instanceof Method || member instanceof Constructor) {
                    references.add(new Reference(classObject, member, ReferenceType.Declares));
                }

                references.addAll(parentSwitch.doSwitch(member));
            }

            TypeReference extendsReference = classObject.getExtends();
            if (extendsReference != null) {
                references.add(new Reference(classObject, extendsReference.getTarget(), ReferenceType.SuperType));
            }

            for (TypeReference implementsReference : classObject.getImplements()) {
                references.add(new Reference(classObject, implementsReference.getTarget(), ReferenceType.SuperType));
            }

            references.add(new Reference(classObject));
            return references;
        }

        @Override
        public List<Reference> caseInterface(Interface interfaceObject) {

            if (extendedMode) {
                ArrayList<Reference> references = Lists.newArrayList();
                for (Member member : interfaceObject.getMembers()) {
                    references.addAll(parentSwitch.doSwitch(member));
                }
                updateType(ReferenceType.Declares, references);

                for (TypeReference typeRef : interfaceObject.getExtends()) {
                    references.add(new Reference(interfaceObject, typeRef.getTarget(), ReferenceType.SuperType));
                }

                references.add(new Reference(interfaceObject));
                return references;
            } else {
                return null;
            }
        }

        @Override
        public List<Reference> caseEnumeration(Enumeration enumeration) {

            if (extendedMode) {
                ArrayList<Reference> references = Lists.newArrayList();
                for (Member member : enumeration.getMembers()) {
                    references.addAll(parentSwitch.doSwitch(member));
                }
                updateType(ReferenceType.Declares, references);

                for (TypeReference typeRef : enumeration.getImplements()) {
                    references.add(new Reference(enumeration, typeRef.getTarget(), ReferenceType.SuperType));
                }

                references.add(new Reference(enumeration));
                return references;
            } else {
                return null;
            }
        }
    }

    /**
     * Switch to decide about referenced elements for member elements.
     */
    private class MembersReferencedElementsSwitch extends MembersSwitch<List<Reference>> {

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
        public List<Reference> caseConstructor(Constructor constructor) {
            ArrayList<Reference> references = Lists.newArrayList();
            for (Statement statement : constructor.getStatements()) {
                references.addAll(parentSwitch.doSwitch(statement));
            }
            references.add(new Reference(constructor));
            return references;
        }

        /**
         * References for class specific methods which include implemented method bodies (a list of
         * statements). This method uses the common caseMethod() implementation and processes all
         * contained statements in addition.
         *
         * {@inheritDoc}
         */
        @Override
        public List<Reference> caseClassMethod(ClassMethod method) {
            ArrayList<Reference> references = Lists.newArrayList();

            List<Reference> commonMethodReferences = caseMethod(method);
            references.addAll(commonMethodReferences);

            for (Statement statement : method.getStatements()) {
                references.addAll(parentSwitch.doSwitch(statement));
            }

            references.add(new Reference(method));
            return references;
        }

        /**
         * Reference collection for all type of methods (interface + class).
         *
         * Return types are referenced by the method. Parameters are not, as no nested dependencies
         * are considered (e.g. changed method with changed parameter inside does not make sense on
         * the granularity level currently inspected).
         *
         * {@inheritDoc}
         */
        @Override
        public List<Reference> caseMethod(Method method) {
            ArrayList<Reference> references = Lists.newArrayList();

            if (extendedMode) {
                for (Parameter parameter : method.getParameters()) {
                    references.addAll(parentSwitch.doSwitch(parameter));
                }

                Type type = method.getTypeReference().getTarget();
                references.add(new Reference(method, type, ReferenceType.Typed));
                Import importDecl = JaMoPPElementUtil.checkForImport(method, type);
                if (importDecl != null) {
                    references.add(new Reference(method, importDecl, ReferenceType.Import));
                }
            }

            references.add(new Reference(method));
            return references;
        }

        /**
         * Get the field and additional field elements. The initial value is not considered as it is
         * not considered by Robillard.
         *
         * {@inheritDoc}
         */
        @Override
        public List<Reference> caseField(Field field) {

            ArrayList<Reference> references = Lists.newArrayList();

            if (extendedMode) {

                // values
                Expression initialValue = field.getInitialValue();
                references.addAll(parentSwitch.doSwitch(initialValue));
                updateSource(field, references);

                // type
                Type type = field.getTypeReference().getTarget();
                references.add(new Reference(field, type, ReferenceType.Typed));
                Import importDecl = JaMoPPElementUtil.checkForImport(field, type);
                if (importDecl != null) {
                    references.add(new Reference(field, importDecl, ReferenceType.Import));
                }

            }

            // element itself
            references.add(new Reference(field));
            for (AdditionalField addField : field.getAdditionalFields()) {
                references.add(new Reference(addField));
            }
            return references;
        }
    }

    /**
     * Switch to decide about referenced elements for statement elements.
     */
    private class StatementsReferencedElementsSwitch extends StatementsSwitch<List<Reference>> {

        private RobillardReferenceSelectorSwitch parentSwitch;

        public StatementsReferencedElementsSwitch(RobillardReferenceSelectorSwitch parentSwitch) {
            this.parentSwitch = parentSwitch;
        }

        @Override
        public List<Reference> caseReturn(Return returnStmt) {
            List<Reference> refElements = parentSwitch.doSwitch(returnStmt.getReturnValue());
            updateSource(returnStmt, refElements);
            return refElements;
        }

        @Override
        public List<Reference> caseTryBlock(TryBlock block) {
            ArrayList<Reference> refElements = Lists.newArrayList();
            for (Statement statement : block.getStatements()) {
                refElements.addAll(parentSwitch.doSwitch(statement));
            }
            for (CatchBlock catchBlock : block.getCatcheBlocks()) {
                refElements.addAll(caseCatchBlock(catchBlock));
            }
            return refElements;
        }

        /**
         * The exception defined and catched by the catch block is a parameter and parameters are
         * not considered by robillard.
         *
         * {@inheritDoc}
         */
        @Override
        public List<Reference> caseCatchBlock(CatchBlock block) {
            ArrayList<Reference> references = Lists.newArrayList();
            for (Statement statement : block.getStatements()) {
                references.addAll(parentSwitch.doSwitch(statement));
            }
            references.addAll(parentSwitch.doSwitch(block.getParameter()));
            return references;
        }

        @Override
        public List<Reference> caseCondition(Condition condition) {

            List<Reference> refElements = parentSwitch.doSwitch(condition.getCondition());
            updateSource(condition, refElements);

            refElements.addAll(parentSwitch.doSwitch(condition.getElseStatement()));
            refElements.addAll(parentSwitch.doSwitch(condition.getStatement()));
            return refElements;
        }

        @Override
        public java.util.List<Reference> caseForLoop(ForLoop loop) {
            List<Reference> refElements = parentSwitch.doSwitch(loop.getInit());
            refElements.addAll(parentSwitch.doSwitch(loop.getCondition()));
            updateSource(loop, refElements);

            refElements.addAll(parentSwitch.doSwitch(loop.getStatement()));
            return refElements;
        }

        @Override
        public List<Reference> caseWhileLoop(WhileLoop loop) {
            List<Reference> refElements = parentSwitch.doSwitch(loop.getCondition());
            updateSource(loop, refElements);

            refElements.addAll(parentSwitch.doSwitch(loop.getStatement()));
            return refElements;
        }

        @Override
        public List<Reference> caseStatementListContainer(StatementListContainer container) {
            ArrayList<Reference> references = Lists.newArrayList();
            for (Statement statement : container.getStatements()) {
                references.addAll(parentSwitch.doSwitch(statement));
            }
            return references;
        }

        @Override
        public List<Reference> caseExpressionStatement(ExpressionStatement stmt) {
            List<Reference> references = parentSwitch.doSwitch(stmt.getExpression());
            updateSource(stmt, references);
            return references;
        }

        /**
         * Robillard et al do not consider references to variables. So the variable itself and
         * additional local variables are not indexed. They also do not care about the type of the
         * declaration. Thus, only the intial value is considered as it might contained a class
         * instantiation or another reference.
         */
        @Override
        public List<Reference> caseLocalVariableStatement(LocalVariableStatement lvs) {
            LocalVariable variable = lvs.getVariable();
            List<Reference> refElements = parentSwitch.doSwitch(variable.getInitialValue());
            updateSource(lvs, refElements);
            if (extendedMode) {
                refElements.add(new Reference(lvs, variable, ReferenceType.Declares));
                for (AdditionalLocalVariable var : variable.getAdditionalLocalVariables()) {
                    refElements.add(new Reference(lvs, var, ReferenceType.Declares));
                }
                Type varType = variable.getTypeReference().getTarget();
                refElements.add(new Reference(lvs, varType, ReferenceType.Typed));
                Import importDecl = JaMoPPElementUtil.checkForImport(lvs, varType);
                if (importDecl != null) {
                    refElements.add(new Reference(lvs, importDecl, ReferenceType.Import));
                }
            }
            return refElements;
        }

    }

    /**
     * Switch to decide about referenced elements for reference elements.
     */
    private class ExpressionsReferencedElementsSwitch extends ExpressionsSwitch<List<Reference>> {

        private RobillardReferenceSelectorSwitch parentSwitch;

        public ExpressionsReferencedElementsSwitch(RobillardReferenceSelectorSwitch parentSwitch) {
            this.parentSwitch = parentSwitch;
        }

        @Override
        public List<Reference> caseExpressionList(ExpressionList exp) {
            ArrayList<Reference> refElements = Lists.newArrayList();
            for (Expression expression : exp.getExpressions()) {
                refElements.addAll(parentSwitch.doSwitch(expression));
            }
            return refElements;
        }

        @Override
        public List<Reference> caseAssignmentExpression(AssignmentExpression exp) {
            ArrayList<Reference> references = Lists.newArrayList();
            references.addAll(parentSwitch.doSwitch(exp.getChild()));
            updateType(ReferenceType.Reads, ReferenceType.Writes, references);
            references.addAll(parentSwitch.doSwitch(exp.getValue()));
            return references;
        }

        @Override
        public List<Reference> caseCastExpression(CastExpression exp) {
            ArrayList<Reference> refElements = Lists.newArrayList();
            refElements.add(new Reference(exp, exp.getAlternativeType(), ReferenceType.Checks));
            return refElements;
        }

        @Override
        public List<Reference> caseInstanceOfExpression(InstanceOfExpression iof) {
            ArrayList<Reference> refElements = Lists.newArrayList();
            refElements.add(new Reference(iof, iof.getTypeReference().getTarget(), ReferenceType.Checks));
            return refElements;
        }
    }

    /**
     * Switch to decide about referenced elements for reference elements.
     */
    private class ParametersReferencedElementsSwitch extends ParametersSwitch<List<Reference>> {

        @SuppressWarnings("unused")
        private RobillardReferenceSelectorSwitch parentSwitch;

        public ParametersReferencedElementsSwitch(RobillardReferenceSelectorSwitch parentSwitch) {
            this.parentSwitch = parentSwitch;
        }

        @Override
        public List<Reference> caseParameter(Parameter parameter) {
            ArrayList<Reference> refElements = Lists.newArrayList();
            Type type = parameter.getTypeReference().getTarget();
            refElements.add(new Reference(parameter, type, ReferenceType.Typed));
            Import importDecl = JaMoPPElementUtil.checkForImport(parameter, type);
            if (importDecl != null) {
                refElements.add(new Reference(parameter, importDecl, ReferenceType.Import));
            }
            return refElements;
        }
    }

    /**
     * Switch to decide about referenced elements for reference elements.
     */
    private class ReferencesReferencedElementsSwitch extends ReferencesSwitch<List<Reference>> {

        private RobillardReferenceSelectorSwitch parentSwitch;

        public ReferencesReferencedElementsSwitch(RobillardReferenceSelectorSwitch parentSwitch) {
            this.parentSwitch = parentSwitch;
        }

        /**
         * Register the called method as well as possible content from the arguments, as they might
         * contain additional identifier references or method calls.
         */
        @Override
        public List<Reference> caseMethodCall(MethodCall call) {
            ArrayList<Reference> refElements = Lists.newArrayList(new Reference(call, call.getTarget(),
                    ReferenceType.Calls));
            for (Expression expression : call.getArguments()) {
                refElements.addAll(parentSwitch.doSwitch(expression));
            }
            updateSource(call, refElements);
            return refElements;
        }

        @Override
        public List<Reference> caseIdentifierReference(IdentifierReference identifierRef) {

            ArrayList<Reference> references = Lists.newArrayList();
            ReferenceableElement target = identifierRef.getTarget();

            if (target instanceof Field || target instanceof AdditionalField) {
                if (identifierRef.getNext() == null) {
                    references.add(new Reference(identifierRef, target, ReferenceType.Reads));
                } else if (extendedMode
                        && identifierRef.getNext() instanceof MethodCall
                        || (identifierRef.getNext() instanceof IdentifierReference && (((IdentifierReference) identifierRef
                                .getNext()).getTarget() instanceof Field || ((IdentifierReference) identifierRef
                                .getNext()).getTarget() instanceof AdditionalField))) {
                    references.add(new Reference(identifierRef, target, ReferenceType.Modifies));
                }

            } else if (target instanceof Method) {
                references.add(new Reference(identifierRef, target, ReferenceType.Calls));

            } else if (extendedMode) {

                if (target instanceof Type) {
                    Import importDecl = JaMoPPElementUtil.checkForImport(identifierRef, (Type) target);
                    if (importDecl != null) {
                        references.add(new Reference(identifierRef, importDecl, ReferenceType.Import));
                    }
                } else if (target instanceof LocalVariable || target instanceof AdditionalLocalVariable
                        || target instanceof Parameter) {

                    if (identifierRef.getNext() == null) {
                        references.add(new Reference(identifierRef, target, ReferenceType.Reads));
                    } else if (identifierRef.getNext() instanceof MethodCall
                            || (identifierRef.getNext() instanceof IdentifierReference
                                    && ((IdentifierReference) identifierRef.getNext()).getTarget() instanceof Field || ((IdentifierReference) identifierRef
                                        .getNext()).getTarget() instanceof AdditionalField)) {
                        references.add(new Reference(identifierRef, target, ReferenceType.Modifies));
                    }
                }

            }

            if (identifierRef.getNext() != null) {
                references.addAll(parentSwitch.doSwitch(identifierRef.getNext()));
            }
            return references;
        }
    }

    /**
     * Switch to decide about referenced elements for instantiating elements.
     */
    private class InstantiationsReferencedElementsSwitch extends InstantiationsSwitch<List<Reference>> {

        private RobillardReferenceSelectorSwitch parentSwitch;

        public InstantiationsReferencedElementsSwitch(RobillardReferenceSelectorSwitch parentSwitch) {
            this.parentSwitch = parentSwitch;
        }

        @Override
        public List<Reference> caseNewConstructorCall(NewConstructorCall call) {
            List<Reference> references = Lists.newArrayList();
            for (Expression expression : call.getArguments()) {
                references.addAll(parentSwitch.doSwitch(expression));
            }

            Constructor constructor = JaMoPPElementUtil.getConstructor(call);
            if (constructor != null) {
                references.add(new Reference(call, constructor, ReferenceType.Calls));
            }

            Type type = call.getReferencedType();
            references.add(new Reference(call, type, ReferenceType.Creates));
            if (extendedMode) {
                Import importDecl = JaMoPPElementUtil.checkForImport(call, type);
                if (importDecl != null) {
                    references.add(new Reference(call, importDecl, ReferenceType.Import));
                }
            }
            return references;
        }
    }
}
