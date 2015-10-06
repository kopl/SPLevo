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
package org.splevo.jamopp.refactoring.java.ifelse.tests;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.eclipse.emf.common.util.Diagnostic;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.members.MembersFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.refactoring.java.ifelse.IfStaticConfigClassEnumerationInCompilationUnit;
import org.splevo.jamopp.refactoring.java.ifelse.tests.util.RefactoringTestUtil;
import org.splevo.jamopp.vpm.software.JaMoPPJavaSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Contains the tests for the {@link IfStaticConfigClassEnumerationInCompilationUnitTest} class.
 */
public class IfStaticConfigClassEnumerationInCompilationUnitTest {

    protected VariabilityRefactoring refactoring;
    
    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void init() {
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * Prepares a single test run. Initializes the test subject.
     */
    @Before
    public void setup() {
        refactoring = new IfStaticConfigClassEnumerationInCompilationUnit();
    }
    
    /**
     * Tests whether the canBeApplied method returns true for applicable variation points.
     */
    @Test
    public void testIfCanBeAppliedWithValidVP() {
        Commentable location = ContainersFactory.eINSTANCE.createCompilationUnit();
        Enumeration implEl1 = ClassifiersFactory.eINSTANCE.createEnumeration();
        Enumeration implEl2 = ClassifiersFactory.eINSTANCE.createEnumeration();
        
        implEl1.setName("A");
        implEl2.setName("B");
        
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTXOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        assertThat(refactoring.canBeAppliedTo(vpMock).getSeverity(), is(Diagnostic.OK));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have a location
     * that is not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvalidLocation() {
        Commentable location = MembersFactory.eINSTANCE.createClassMethod();
        Enumeration implEl1 = ClassifiersFactory.eINSTANCE.createEnumeration();
        Enumeration implEl2 = ClassifiersFactory.eINSTANCE.createEnumeration();
        
        implEl1.setName("A");
        implEl2.setName("B");
        
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTXOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        assertThat(refactoring.canBeAppliedTo(vpMock).getSeverity(), is(Diagnostic.ERROR));
    }

    /**
     * Tests whether the canBeApplied method returns false for variation points that have variants
     * with implementing elements that are not supported by the refactoring.
     */
    @Test
    public void testIfCanBeAppliedWithInvalidVariantElements() {
        Commentable location = ContainersFactory.eINSTANCE.createCompilationUnit();
        Enumeration implEl1 = ClassifiersFactory.eINSTANCE.createEnumeration();
        Interface implEl2 = ClassifiersFactory.eINSTANCE.createInterface();
        
        implEl1.setName("A");
        implEl2.setName("B");
        
        VariationPoint vpMock = RefactoringTestUtil.getSimpleVPMock(VariabilityType.OPTXOR, Extensible.NO,
                BindingTime.COMPILE_TIME, location, implEl1, implEl2);

        assertThat(refactoring.canBeAppliedTo(vpMock).getSeverity(), is(Diagnostic.ERROR));
    }

    /**
     * Tests whether the refactoring merges the constants of two enumerations correctly. The
     * generated enumeration must contain the constants from all variants.
     * 
     * @throws Exception
     *             In case of an unexpected exception.
     */
    @Test
    public void testRefactorCaseEnumerationAddEnumConstant() throws Exception {
        VariationPoint vp = RefactoringTestUtil.getEnumerationInCUAddEnumConstantCase(VariabilityType.OPTXOR);
        refactoring.refactor(vp, null);

        // location has one member (the enumeration)
        CompilationUnit vpLocation = (CompilationUnit) ((JaMoPPJavaSoftwareElement) vp.getLocation()).getJamoppElement();
        assertThat(vpLocation.getClassifiers().size(), equalTo(1));

        // member is an enumeration
        assertThat(vpLocation.getClassifiers().get(0), instanceOf(Enumeration.class));

        Enumeration enumeration = (Enumeration) vpLocation.getClassifiers().get(0);

        // enumeration has the correct name and the two constants from the base and the integration
        assertThat(enumeration.getName(), equalTo("TestClass"));
        assertThat(enumeration.getConstants().size(), equalTo(2));
        assertThat(enumeration.getConstants().get(0).getName(), anyOf(equalTo("A"), equalTo("B")));
        assertThat(enumeration.getConstants().get(1).getName(), anyOf(equalTo("A"), equalTo("B")));
        assertThat(enumeration.getConstants().get(0).getName(),
                not(equalTo(enumeration.getConstants().get(1).getName())));
        
        // verify correct VPM
        RefactoringTestUtil.assertValidVPM(vp);
    }
}
