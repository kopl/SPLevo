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
package org.splevo.jamopp.diffing.scope;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.util.ContainersSwitch;
import org.emftext.language.java.members.util.MembersSwitch;
import org.emftext.language.java.references.util.ReferencesSwitch;
import org.emftext.language.java.types.PrimitiveType;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.util.TypesSwitch;
import org.emftext.language.java.variables.Variable;
import org.emftext.language.java.variables.util.VariablesSwitch;
import org.splevo.jamopp.diffing.util.JaMoPPModelUtil;

/**
 * Internal class to switch between the different element types and check if they should be ignored.
 */
public class IgnoreSwitch extends ComposedSwitch<Boolean> {

    /** The packages to be ignored. */
    private List<String> ignorePackages = new ArrayList<String>();

    /**
     * Instantiates a new ignore switch. Constructor initializes the sub switches for specific model
     * packages.
     *
     * @param ignorePackages
     *            the packages to ignore
     */
    public IgnoreSwitch(List<String> ignorePackages) {
        this.ignorePackages.addAll(ignorePackages);
        addSwitch(new TypesIgnoreSwitch());
        addSwitch(new MembersIgnoreSwitch());
        addSwitch(new VariablesIgnoreSwitch());
        addSwitch(new ContainersIgnoreSwitch());
        addSwitch(new ReferencesIgnoreSwitch());
    }

    /**
     * Sub-switch for type elements.
     */
    private class TypesIgnoreSwitch extends TypesSwitch<Boolean> {

        /**
         * Check a type declaration whether it is located in one of the packages to ignore.
         *
         * A Type includes Classifier (Class, Interface, Enumeration, etc.)
         *
         * @param object
         *            The type to check.
         * @return the boolean
         */
        @Override
        public Boolean caseType(Type object) {
            String packagePath = JaMoPPModelUtil.buildNamespacePath(object);
            return shouldBeIgnored(packagePath);
        }

        @Override
        public Boolean casePrimitiveType(PrimitiveType object) {
            return Boolean.TRUE;
        }

        @Override
        public Boolean caseTypeReference(TypeReference object) {
            return composedDoSwitch(object.eContainer());
        }

    }

    /**
     * Sub-switch for member elements.
     */
    private class MembersIgnoreSwitch extends MembersSwitch<Boolean> {

        /**
         * Members are either contained by a parent element which is in scope or not, or it can be
         * ignored if it has no container (which is not valid).<br>
         * {@inheritDoc}
         */
        @Override
        public Boolean defaultCase(EObject object) {
            if (object.eContainer() != null) {
                return composedDoSwitch(object.eContainer());
            } else {
                return Boolean.TRUE;
            }
        }
    }

    /**
     * Sub-switch for variable elements.
     */
    private class VariablesIgnoreSwitch extends VariablesSwitch<Boolean> {
        @Override
        public Boolean caseVariable(Variable object) {
            return composedDoSwitch(object.eContainer());
        }
    }

    /**
     * Sub-switch for container elements.
     */
    private class ContainersIgnoreSwitch extends ContainersSwitch<Boolean> {
        @Override
        public Boolean casePackage(org.emftext.language.java.containers.Package object) {
            String packagePath = JaMoPPModelUtil.buildNamespacePath(object);
            return shouldBeIgnored(packagePath);
        }

        @Override
        public Boolean caseCompilationUnit(CompilationUnit object) {
            String packagePath = JaMoPPModelUtil.buildNamespacePath(object);
            return shouldBeIgnored(packagePath);
        }
    }

    /**
     * Sub-switch for reference elements.
     */
    private class ReferencesIgnoreSwitch extends ReferencesSwitch<Boolean> {
        @Override
        public Boolean defaultCase(EObject object) {
            return composedDoSwitch(object.eContainer());
        }
    }

    /**
     * The default case is to not ignore the supplied element.
     *
     * @param object
     *            The object to check.
     * @return Whether to ignore it (always false).
     */
    @Override
    public Boolean defaultCase(EObject object) {
        return Boolean.FALSE;
    }

    /**
     * Method to make sure the outer composed switch is invoked.
     *
     * @param eObject
     *            The model element to perform the switch on.
     * @return The result of the outer switch.
     */
    public Boolean composedDoSwitch(EObject eObject) {
        return doSwitch(eObject);
    }

    /**
     * Check a package path whether it matches one of the ignore package patterns.
     *
     * @param packagePath
     *            the package path to check
     * @return true/false whether it should be ignored or not.
     */
    public Boolean shouldBeIgnored(String packagePath) {

        for (String regex : ignorePackages) {
            if (packagePath.matches(regex)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}