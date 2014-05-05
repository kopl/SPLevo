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
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.util.ImportsSwitch;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.members.util.MembersSwitch;
import org.emftext.language.java.statements.Statement;

import com.google.common.collect.Lists;

/**
 * Switch to decide about which elements to return as referenced elements for a given JaMoPP
 * element.
 */
public class RobillardReferenceSelectorSwitch extends ComposedSwitch<List<Commentable>> {

    /** The logger for this class. */
    @SuppressWarnings("unused")
    private Logger logger = Logger.getLogger(RobillardReferenceSelectorSwitch.class);

    /** Constructor to set up the sub switches. */
    public RobillardReferenceSelectorSwitch() {
        addSwitch(new MembersReferencedElementsSwitch(this));
        addSwitch(new ImportReferencedElementsSwitch());
    }

    /** Switch to decide about referenced elements for member elements. */
    private class ImportReferencedElementsSwitch extends ImportsSwitch<List<Commentable>> {

        @Override
        public List<Commentable> caseImport(Import object) {
            return Lists.newArrayList((Commentable) object);
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
    }
}
