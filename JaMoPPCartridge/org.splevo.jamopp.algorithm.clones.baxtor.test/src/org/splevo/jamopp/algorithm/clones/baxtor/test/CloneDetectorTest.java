/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christian Busch
 *******************************************************************************/
package org.splevo.jamopp.algorithm.clones.baxtor.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.variables.VariablesPackage;
import org.emftext.language.java.variables.impl.LocalVariableImpl;
import org.emftext.language.java.variables.impl.VariableImpl;
import org.junit.Before;
import org.junit.Test;
import org.splevo.jamopp.algorithm.clones.baxtor.CloneDetectionType;
import org.splevo.jamopp.algorithm.clones.baxtor.CloneDetector;

public class CloneDetectorTest {

    private CloneDetector structuralDetector;
    private CloneDetector exactDetector;

    @Before
    public void intialize() {
        structuralDetector = new CloneDetector(CloneDetectionType.STRUCTURAL);
        exactDetector = new CloneDetector(CloneDetectionType.EXACT);
    }

    @Test
    public void testSelfIsClone() {
        Commentable commentable = mock(Commentable.class);

        boolean result = structuralDetector.isClone(commentable, commentable);
        assertThat("an commentable is a clone of itself", result, is(true));
    }

    @Test
    public void testRenamedVariableIsStructuralClone() {
        VariableImpl variable1 = mock(VariableImpl.class);
        VariableImpl variable2 = mock(VariableImpl.class);

        @SuppressWarnings("unchecked")
        TreeIterator<EObject> iter = mock(TreeIterator.class);
        when(iter.hasNext()).thenReturn(false);

        when(variable1.eAllContents()).thenReturn(iter);
        when(variable2.eAllContents()).thenReturn(iter);

        when(variable1.getName()).thenReturn("a");
        when(variable2.getName()).thenReturn("b");

        boolean result = structuralDetector.isClone(variable1, variable2);
        assertThat("a renamed variable resembles a structural clone", result, is(true));
    }

    @Test
    public void testVariableWithSameNameIsExactClone() {
        LocalVariableImpl variable1 = mock(LocalVariableImpl.class);
        LocalVariableImpl variable2 = mock(LocalVariableImpl.class);

        @SuppressWarnings("unchecked")
        TreeIterator<EObject> iter = mock(TreeIterator.class);
        when(iter.hasNext()).thenReturn(false);

        when(variable1.eAllContents()).thenReturn(iter);
        when(variable2.eAllContents()).thenReturn(iter);

        when(variable1.eClass()).thenReturn(VariablesPackage.Literals.LOCAL_VARIABLE);

        when(variable1.getName()).thenReturn("a");
        when(variable2.getName()).thenReturn("a");

        boolean result = exactDetector.isClone(variable1, variable2);
        assertThat("a renamed variable doesn't resemble an exact clone", result, is(true));
    }

    @Test
    public void testRenamedVariableIsNoExactClone() {
        LocalVariableImpl variable1 = mock(LocalVariableImpl.class);
        LocalVariableImpl variable2 = mock(LocalVariableImpl.class);

        @SuppressWarnings("unchecked")
        TreeIterator<EObject> iter = mock(TreeIterator.class);
        when(iter.hasNext()).thenReturn(false);

        when(variable1.eAllContents()).thenReturn(iter);
        when(variable2.eAllContents()).thenReturn(iter);

        when(variable1.eClass()).thenReturn(VariablesPackage.Literals.LOCAL_VARIABLE);

        when(variable1.getName()).thenReturn("a");
        when(variable2.getName()).thenReturn("b");

        boolean result = exactDetector.isClone(variable1, variable2);
        assertThat("a renamed variable doesn't resemble an exact clone", result, is(false));
    }
}
