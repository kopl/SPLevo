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
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Return;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.variables.LocalVariable;

/**
 * A provider for the program structure analyzer to return information specific for JaMoPPSoftware
 * elements
 *
 */
public class ReferencedElementSelector {

    private static Logger logger = Logger.getLogger(RefereableElementSelector.class);

    /**
     * Get the referable child nodes of a JaMoPP element.
     *
     * @param commentable
     *            The JaMoPP element to find the child elements for.
     * @return The list of referable children.
     */
    public List<Commentable> getReferencedElements(Commentable commentable) {
        List<Commentable> referencedElements = new ArrayList<Commentable>();

        if (commentable instanceof Return) {
            Return returnStmt = (Return) commentable;
            return getReferencedElements((Expression) returnStmt.getReturnValue());
        }

        if (commentable instanceof Import) {
            referencedElements.add(commentable);
            return referencedElements;
        }

        if (commentable instanceof LocalVariableStatement) {
            LocalVariableStatement lvs = (LocalVariableStatement) commentable;
            LocalVariable var = lvs.getVariable();

            if (var != null) {

                // check the variables type
                referencedElements.addAll(getReferencedElements(var.getTypeReference()));

                // check the value
                referencedElements.addAll(getReferencedElements(var.getInitialValue()));
            } else {
                logger.warn("VariableStatement without variable: " + lvs);
                // additional local variables must not be checked
                // as they do not introduce any new references
            }

            return referencedElements;
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
    public List<Commentable> getReferencedElements(Expression expression) {
        List<Commentable> referencedElements = new ArrayList<Commentable>();

        if (expression instanceof IdentifierReference) {
            IdentifierReference ref = (IdentifierReference) expression;
            referencedElements.add(ref.getTarget());

            // handle next
            referencedElements.addAll(getReferencedElements(ref.getNext()));
        }

        if (expression instanceof NewConstructorCall) {
            NewConstructorCall call = (NewConstructorCall) expression;
            referencedElements.addAll(getReferencedElements(call.getTypeReference()));

            // TODO handle references in anonymous class
            // call.getAnonymousClass()

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
    public List<Commentable> getReferencedElements(Reference reference) {
        List<Commentable> referencedElements = new ArrayList<Commentable>();

        if (reference instanceof MethodCall) {
            MethodCall call = (MethodCall) reference;
            referencedElements.addAll(getReferencedElements((Argumentable) call));
            if (call.getTarget() != null) {
                referencedElements.add(call.getTarget());
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
    public List<Commentable> getReferencedElements(Argumentable argumentable) {
        List<Commentable> referencedElements = new ArrayList<Commentable>();

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
    public List<Commentable> getReferencedElements(TypeReference typeReference) {
        List<Commentable> referencedElements = new ArrayList<Commentable>();

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

}
