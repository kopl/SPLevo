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

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.commons.CommonsPackage;
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
    public void testIfAnyCommentableIsNullTheyAreNotClones() {
        Commentable commentable = mock(Commentable.class);

        when(commentable.eClass()).thenReturn(CommonsPackage.Literals.COMMENTABLE);

        boolean isClone = structuralDetector.isClone(null, commentable);
        assertThat("If the first commentable is null", isClone, is(false));

        isClone = structuralDetector.isClone(commentable, null);
        assertThat("If the second commentable is null", isClone, is(false));
    }

    @Test
    public void testSelfIsClone() {
        Commentable commentable = mock(Commentable.class);

        when(commentable.eClass()).thenReturn(CommonsPackage.Literals.COMMENTABLE);

        boolean isClone = structuralDetector.isClone(commentable, commentable);
        assertThat("If both commentables are the same object", isClone, is(true));
    }

    @Test
    public void testDifferentEClassesAreNotClone() {
        AnonymousClass anonClass = mock(AnonymousClass.class);
        Enumeration enummer = mock(Enumeration.class);

        when(anonClass.eClass()).thenReturn(ClassifiersPackage.Literals.ANONYMOUS_CLASS);
        when(enummer.eClass()).thenReturn(ClassifiersPackage.Literals.ENUMERATION);

        boolean isClone = structuralDetector.isClone(anonClass, enummer);
        assertThat("For commentables of different types", isClone, is(false));
    }

    @Test
    public void testRenamedVariableIsStructuralClone() {
        VariableImpl variable1 = mock(VariableImpl.class);
        VariableImpl variable2 = mock(VariableImpl.class);

        when(variable1.eClass()).thenReturn(VariablesPackage.Literals.VARIABLE);
        when(variable2.eClass()).thenReturn(VariablesPackage.Literals.VARIABLE);

        @SuppressWarnings("unchecked")
        TreeIterator<EObject> iter = mock(TreeIterator.class);
        when(iter.hasNext()).thenReturn(false);

        when(variable1.eAllContents()).thenReturn(iter);
        when(variable2.eAllContents()).thenReturn(iter);

        when(variable1.getName()).thenReturn("a");
        when(variable2.getName()).thenReturn("b");

        boolean isClone = structuralDetector.isClone(variable1, variable2);
        assertThat("For a renamed variable with a structural detector", isClone, is(true));
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
        when(variable2.eClass()).thenReturn(VariablesPackage.Literals.LOCAL_VARIABLE);

        when(variable1.getName()).thenReturn("a");
        when(variable2.getName()).thenReturn("a");

        boolean isClone = exactDetector.isClone(variable1, variable2);
        assertThat("For a renamed variable with an excact detector", isClone, is(true));
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
        when(variable2.eClass()).thenReturn(VariablesPackage.Literals.LOCAL_VARIABLE);

        when(variable1.getName()).thenReturn("a");
        when(variable2.getName()).thenReturn("b");

        boolean isClone = exactDetector.isClone(variable1, variable2);
        assertThat("For a renamed variable with an excact detector", isClone, is(false));
    }

    @Test
    public void testNullParametersShouldThrowException() {
        @SuppressWarnings("unchecked")
        List<Commentable> list = mock(List.class);

        boolean isClone = structuralDetector.isClone(null, list);
        assertThat("For null values", isClone, is(false));

        isClone = structuralDetector.isClone(list, null);
        assertThat("For null values", isClone, is(false));
    }

    @Test
    public void testListsOfSizeZeroAreNotClones() {
        @SuppressWarnings("unchecked")
        List<Commentable> list1 = mock(List.class);
        @SuppressWarnings("unchecked")
        List<Commentable> list2 = mock(List.class);

        when(list1.size()).thenReturn(0);
        when(list2.size()).thenReturn(1);

        boolean isClone = structuralDetector.isClone(list1, list2);
        assertThat("If list1 size is 0", isClone, is(false));

        isClone = structuralDetector.isClone(list2, list1);
        assertThat("If list2 size is 0", isClone, is(false));

        isClone = structuralDetector.isClone(list1, list1);
        assertThat("If both lists size is 0", isClone, is(false));

    }

    @Test
    public void testListsOfCommentablesOfDifferentSizesAreNotClones() {
        @SuppressWarnings("unchecked")
        List<Commentable> list1 = mock(List.class);
        @SuppressWarnings("unchecked")
        List<Commentable> list2 = mock(List.class);

        when(list1.size()).thenReturn(1);
        when(list2.size()).thenReturn(2);

        boolean isClone = structuralDetector.isClone(list1, list2);
        assertThat("For lists of different length ", isClone, is(false));
    }

    @Test
    public void testIdenticalListsOfCommentablesAreClones() {
        @SuppressWarnings("unchecked")
        List<Commentable> list = mock(List.class);

        @SuppressWarnings("unchecked")
        Iterator<Commentable> iter = mock(Iterator.class);

        Commentable commentable = mock(Commentable.class);

        when(list.size()).thenReturn(1);
        when(list.iterator()).thenReturn(iter);

        when(iter.hasNext()).thenReturn(true, false);
        when(iter.next()).thenReturn(commentable);

        when(commentable.eClass()).thenReturn(CommonsPackage.Literals.COMMENTABLE);

        boolean isClone = structuralDetector.isClone(list, list);
        assertThat("For two identical, nonempty lists of commenables", isClone, is(true));
    }

    @Test
    public void testListsOfDifferentCommentablesAreNotClones() {
        @SuppressWarnings("unchecked")
        List<Commentable> list1 = mock(List.class);
        @SuppressWarnings("unchecked")
        List<Commentable> list2 = mock(List.class);

        @SuppressWarnings("unchecked")
        Iterator<Commentable> iter1 = mock(Iterator.class);
        @SuppressWarnings("unchecked")
        Iterator<Commentable> iter2 = mock(Iterator.class);

        AnonymousClass anonClass = mock(AnonymousClass.class);
        Enumeration enummer = mock(Enumeration.class);

        when(list1.size()).thenReturn(1);
        when(list2.size()).thenReturn(1);

        when(list1.iterator()).thenReturn(iter1);
        when(list2.iterator()).thenReturn(iter2);

        when(iter1.hasNext()).thenReturn(true, false);
        when(iter2.hasNext()).thenReturn(true, false);

        when(iter1.next()).thenReturn(anonClass);
        when(iter2.next()).thenReturn(enummer);

        when(anonClass.eClass()).thenReturn(ClassifiersPackage.Literals.ANONYMOUS_CLASS);
        when(enummer.eClass()).thenReturn(ClassifiersPackage.Literals.ENUMERATION);

        boolean isClone = structuralDetector.isClone(list1, list2);
        assertThat("For two lists of different Commentables", isClone, is(false));
    }
}
