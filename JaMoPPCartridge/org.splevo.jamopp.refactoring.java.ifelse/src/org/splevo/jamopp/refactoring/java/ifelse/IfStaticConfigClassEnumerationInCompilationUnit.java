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
package org.splevo.jamopp.refactoring.java.ifelse;

import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.containers.CompilationUnit;

/**
 * The code base container must contain all enumerations from the variants. Therefore, this
 * refactoring merges the enumerations from all variants into the base, if there are no
 * interferences.
 */
public class IfStaticConfigClassEnumerationInCompilationUnit extends IfStaticConfigClassEnumerationBase<CompilationUnit> {

    private static final String REFACTORING_NAME = "IF with Static Configuration Class: Enumeration in CompilationUnit";
    private static final String REFACTORING_ID =
            "org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassEnumerationInMemberContainer";

    /**
     * Constructs the refactoring.
     */
    public IfStaticConfigClassEnumerationInCompilationUnit() {
        super(CompilationUnit.class, REFACTORING_NAME);
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }

    @Override
    protected void addToVPLocation(CompilationUnit location, Enumeration enumeration) {
        location.getClassifiers().add(enumeration);
    }

}
