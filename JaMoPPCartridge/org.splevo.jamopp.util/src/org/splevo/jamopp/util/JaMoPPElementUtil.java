/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.jamopp.util;

import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.commons.NamedElement;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Return;

/**
 * Utility class to handle JaMoPP elements.
 */
public final class JaMoPPElementUtil {

    /** Disable constructor for utility class. */
    private JaMoPPElementUtil() {
    }

    /**
     * Get the name for a JaMoPP element.<br>
     * This is the name in case of {@link NamedElement}s, otherwise null will be returned.<br>
     *
     * If you want to always get a string representation, check out
     * {@link JaMoPPElementUtil#getLabel(Commentable)}.
     *
     * @param element
     *            The JaMoPP element to get the name for.
     * @return The name attribute of an element.
     */
    public static String getName(Commentable element) {
        if (element == null) {
            return null;
        }
        if (element instanceof NamedElement) {
            return ((NamedElement) element).getName();
        } else {
            return null;
        }
    }

    /**
     * Get the name of a JaMoPP element.<br>
     * This will always return a representative String for the element.<br>
     * The main purpose of the method is to get a human readable representation.
     *
     * @param element
     *            The JaMoPP element to get the label for.
     * @return The String representation.
     */
    public static String getLabel(Commentable element) {
        if (element == null) {
            return "NULL";

        } else if (element instanceof CompilationUnit) {
            CompilationUnit cu = (CompilationUnit) element;
            String name = cu.getName();
            if (name != null) {
                return name.substring(cu.getNamespaces().size());
            } else {
                return "CompilationUnit (no name)";
            }

        } else if (element instanceof LocalVariableStatement) {
            LocalVariableStatement statement = (LocalVariableStatement) element;
            return "Variable Declaration: " + statement.getVariable().getName();

        } else if (element instanceof Return) {
            return "Return Statement";

        } else if (element instanceof ClassifierImport) {
            ClassifierImport importDecl = (ClassifierImport) element;
            return "Import: " + importDecl.getClassifier().getName();

        } else if (element instanceof Import) {
            return "Import";

        } else if (element instanceof MethodCall) {
            MethodCall invocation = (MethodCall) element;
            return "Method Invocation: " + invocation.getTarget().getName() + "()";

        } else if (element instanceof Method) {
            Method method = (Method) element;
            return method.getName() + "()";

        } else if (element instanceof Block && element.eContainer() instanceof Method) {
            Method method = (Method) element.eContainer();
            return method.getName() + "()";

        } else if (element instanceof NamedElement) {
            return ((NamedElement) element).getName();
        }

        return "JaMoPP Element " + element.getClass().getSimpleName();
    }

}
