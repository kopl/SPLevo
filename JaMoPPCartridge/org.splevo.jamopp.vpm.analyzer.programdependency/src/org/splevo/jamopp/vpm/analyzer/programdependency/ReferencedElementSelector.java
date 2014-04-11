/*******************************************************************************
 * Copyright (c) 2013
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.jamopp.vpm.analyzer.programdependency;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.instantiations.NewConstructorCall;
import org.emftext.language.java.references.Argumentable;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.Reference;
import org.emftext.language.java.references.ReferenceableElement;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Return;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElement;
import org.emftext.language.java.variables.LocalVariable;

/**
 * A provider for the program structure analyzer to return information specific for JaMoPPSoftware
 * elements
 *
 */
public class ReferencedElementSelector {

    private static Logger logger = Logger.getLogger(ReferencedElementSelector.class);

    /**
     * Get the referable child nodes of a JaMoPP element.
     *
     * @param commentable
     *            The JaMoPP element to find the child elements for.
     * @return The list of referable children.
     */
    public List<Commentable> getReferencedElements(Commentable commentable) {
        List<Commentable> referencedElements = new ArrayList<Commentable>();
        if (isNullOrProxy(commentable)) {
            return referencedElements;
        }

        if (commentable instanceof Import) {
            referencedElements.add(commentable);

        } else if (commentable instanceof Return) {
            Return returnStmt = (Return) commentable;
            Expression returnValue = (Expression) returnStmt.getReturnValue();
            referencedElements.addAll(getReferencedElements(returnValue));

        } else if (commentable instanceof LocalVariableStatement) {
            LocalVariableStatement lvs = (LocalVariableStatement) commentable;
            LocalVariable variable = lvs.getVariable();
            if (variable != null) {

                // check the variables type
                referencedElements.addAll(getReferencedElements(variable.getTypeReference()));

                // check the value
                referencedElements.addAll(getReferencedElements(variable.getInitialValue()));
            } else {
                logger.warn("VariableStatement without variable: " + lvs);
                // additional local variables must not be checked
                // as they do not introduce any new references
            }
            referencedElements.add(lvs.getVariable());

        } else if (commentable instanceof ExpressionStatement) {
            ExpressionStatement stmt = (ExpressionStatement) commentable;
            Expression expression = stmt.getExpression();
            referencedElements.addAll(getReferencedElements(expression));

        } else if (commentable instanceof IdentifierReference) {
            IdentifierReference reference = (IdentifierReference) commentable;
            if (reference.getTarget() != null) {
                referencedElements.add(reference.getTarget());
            }
            Reference next = reference.getNext();
            referencedElements.addAll(getReferencedElements(next));

        } else if (commentable instanceof MethodCall) {
            MethodCall methodCall = (MethodCall) commentable;
            if (methodCall.getTarget() != null) {
                referencedElements.add(methodCall.getTarget());
            }
            for (Expression expression : methodCall.getArguments()) {
                referencedElements.addAll(getReferencedElements(expression));
            }
        }

        for (Commentable element : referencedElements) {
            if (isNullOrProxy(element)) {
                referencedElements.remove(element);
            }

        }

        return referencedElements;
    }

    /**
     * Get the referenced elements of an expression.
     *
     * @param expression
     *            The expression to analyze.
     * @return The list of identified elements.
     */
    private List<Commentable> getReferencedElements(Expression expression) {
        List<Commentable> referencedElements = new ArrayList<Commentable>();
        if (isNullOrProxy(expression)) {
            return referencedElements;
        }

        if (expression instanceof IdentifierReference) {
            IdentifierReference ref = (IdentifierReference) expression;
            ReferenceableElement target = ref.getTarget();
            if (!isNullOrProxy(target)) {
                referencedElements.add(target);
            }
            // handle next
            Reference next = ref.getNext();
            referencedElements.addAll(getReferencedElements(next));

        } else if (expression instanceof NewConstructorCall) {
            NewConstructorCall call = (NewConstructorCall) expression;
            TypeReference typeReference = call.getTypeReference();
            referencedElements.addAll(getReferencedElements(typeReference));

            // TODO handle references in anonymous class
            // call.getAnonymousClass()

        } else if (expression instanceof Reference) {
            Reference reference = (Reference) expression;
            referencedElements.addAll(getReferencedElements(reference));
        }

        return referencedElements;
    }

    /**
     * Get the referenced elements of an expression.
     *
     * @param reference
     *            The reference to analyze.
     * @return The list of identified elements.
     */
    private List<Commentable> getReferencedElements(Reference reference) {
        List<Commentable> referencedElements = new ArrayList<Commentable>();
        if (isNullOrProxy(reference)) {
            return referencedElements;
        }

        if (reference instanceof MethodCall) {
            MethodCall call = (MethodCall) reference;
            referencedElements.addAll(getReferencedElements((Argumentable) call));
            ReferenceableElement target = call.getTarget();
            if (target != null) {
                TypedElement method = (TypedElement) target;
                TypeReference declaringType = method.getTypeReference();
                if (!isNullOrProxy(declaringType)) {
                    referencedElements.add(target);
                }

            }
        }

        return referencedElements;
    }

    /**
     * Get the referenced elements of an expression.
     *
     * @param argumentable
     *            The argumentable element to analyze.
     * @return The list of identified elements.
     */
    private List<Commentable> getReferencedElements(Argumentable argumentable) {
        List<Commentable> referencedElements = new ArrayList<Commentable>();
        if (isNullOrProxy(argumentable)) {
            return referencedElements;
        }

        for (Expression argument : argumentable.getArguments()) {
            referencedElements.addAll(getReferencedElements(argument));
        }

        return referencedElements;
    }

    /**
     * Get the referenced elements of an expression.
     *
     * @param typeReference
     *            The type reference to analyze.
     * @return The list of identified elements.
     */
    private List<Commentable> getReferencedElements(TypeReference typeReference) {
        List<Commentable> referencedElements = new ArrayList<Commentable>();
        if (isNullOrProxy(typeReference)) {
            return referencedElements;
        }

        Type type = typeReference.getTarget();

        CompilationUnit cu = typeReference.getContainingCompilationUnit();
        EList<ClassifierImport> imports = cu.getChildrenByType(ClassifierImport.class);
        for (ClassifierImport classifierImport : imports) {
            if (classifierImport.getClassifier().equals(type)) {
                referencedElements.add(classifierImport);
            }
        }

        return referencedElements;
    }

    private boolean isNullOrProxy(Commentable commentable) {
        return commentable == null || commentable.eIsProxy();
    }

}
