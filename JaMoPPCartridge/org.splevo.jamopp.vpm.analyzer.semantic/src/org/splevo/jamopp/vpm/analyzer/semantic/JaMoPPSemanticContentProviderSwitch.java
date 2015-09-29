/*******************************************************************************
 * Copyright (c) 2015
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.jamopp.vpm.analyzer.semantic;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.eclipse.emf.ecore.util.Switch;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.commons.NamedElement;
import org.emftext.language.java.commons.util.CommonsSwitch;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.imports.StaticMemberImport;
import org.emftext.language.java.imports.util.ImportsSwitch;
import org.emftext.language.java.references.ElementReference;
import org.emftext.language.java.references.StringReference;
import org.emftext.language.java.references.util.ReferencesSwitch;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Jump;
import org.emftext.language.java.statements.util.StatementsSwitch;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Content provider switch that determines the terms used in a given EObject.
 */
public class JaMoPPSemanticContentProviderSwitch extends ComposedSwitch<Iterable<String>> {

    /**
     * Constructs the content switch.
     * 
     * @param onlyUseName
     *            If set to True, only the name of NamedElements will be used to provide the
     *            content. Otherwise, additional information such as the name of the method in a
     *            MethodCall will be used.
     */
    public JaMoPPSemanticContentProviderSwitch(boolean onlyUseName) {
        addSwitch(new CommonsContentProviderSwitch());
        if (!onlyUseName) {
            addSwitch(new ImportContentProviderSwitch());
            addSwitch(new ReferencesContentProviderSwitch());
            addSwitch(new StatementContentProviderSwitch());
        }
    }

    @Override
    public Iterable<String> doSwitch(EObject eObject) {
        if (eObject == null) {
            return Lists.newArrayList();
        }
        Iterable<String> result = super.doSwitch(eObject);
        if (result == null) {
            return Lists.newArrayList();
        }
        return Iterables.filter(result, Predicates.notNull());
    }

    @Override
    protected Iterable<String> doSwitch(EClass theEClass, EObject theEObject) {
        List<EClass> typesToCheck = Lists.newLinkedList(theEClass.getEAllSuperTypes());
        typesToCheck.add(0, theEClass);
        typesToCheck.remove(CommonsPackage.eINSTANCE.getCommentable());
        for (EClass eclass : typesToCheck) {
            Switch<Iterable<String>> delegate = findDelegate(eclass.getEPackage());
            if (delegate != null) {
                Iterable<String> result = delegatedDoSwitch(delegate, eclass, theEObject);
                if (result != null) {
                    return result;
                }
            }
        }
        return defaultCase(theEObject);
    }

    private Iterable<String> doInternalSwitch(EClass clazz, EObject eObject) {
        return doSwitch(clazz, eObject);
    }

    private Iterable<String> doInternalSwitch(Commentable... elements) {
        return doInternalSwitch(Lists.newArrayList(elements));
    }

    private Iterable<String> doInternalSwitch(Iterable<? extends Commentable> elements) {
        return Iterables.concat(Iterables.filter(
                Iterables.transform(elements, new Function<Commentable, Iterable<String>>() {
                    @Override
                    public Iterable<String> apply(Commentable input) {
                        return doSwitch(input);
                    }
                }), Predicates.notNull()));
    }

    private <T> Iterable<T> asIterable(T... elements) {
        return Lists.newArrayList(elements);
    }

    /**
     * Content switch for EObjects from the Commons package.
     */
    private class CommonsContentProviderSwitch extends CommonsSwitch<Iterable<String>> {

        @Override
        public Iterable<String> caseNamedElement(NamedElement object) {
            return asIterable(object.getName());
        }

    }

    /**
     * Content switch for EObjects from the Import package.
     */
    private class ImportContentProviderSwitch extends ImportsSwitch<Iterable<String>> {

        @Override
        public Iterable<String> caseClassifierImport(ClassifierImport object) {
            return doInternalSwitch(object.getClassifier());
        }

        @Override
        public Iterable<String> caseStaticMemberImport(StaticMemberImport object) {
            return doInternalSwitch(object.getStatic());
        }

    }

    /**
     * Content switch for EObjects from the Statement package.
     */
    private class StatementContentProviderSwitch extends StatementsSwitch<Iterable<String>> {

        @Override
        public Iterable<String> caseJump(Jump object) {
            return doInternalSwitch(object.getTarget());
        }

        @Override
        public Iterable<String> caseBlock(Block object) {
            if ("block".equalsIgnoreCase(object.getName())) {
                return Lists.newArrayList();
            }
            return doInternalSwitch(CommonsPackage.eINSTANCE.getNamedElement(), object);
        }

    }

    /**
     * Content switch for EObjects from the Reference package.
     */
    private class ReferencesContentProviderSwitch extends ReferencesSwitch<Iterable<String>> {

        @Override
        public Iterable<String> caseStringReference(StringReference object) {
            return asIterable(object.getValue());
        }

        @Override
        public Iterable<String> caseElementReference(ElementReference object) {
            return asIterable(object.getTarget().getName());
        }

    }

}
