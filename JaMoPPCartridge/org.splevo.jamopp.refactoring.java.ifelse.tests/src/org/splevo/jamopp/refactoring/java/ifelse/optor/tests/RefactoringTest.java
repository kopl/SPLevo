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
 *    Daniel Kojic - updated javadoc
 *******************************************************************************/
package org.splevo.jamopp.refactoring.java.ifelse.optor.tests;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.members.EnumConstant;
import org.emftext.language.java.members.MembersFactory;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * General tests for the refactorings.
 */
public class RefactoringTest {

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void setUp() {
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * Tests whether the container-reference of an enum constant is not null when adding it to an
     * enumeration.
     */
    @Test
    public void testConnectEnumeration() {

        Enumeration myEnumeration = ClassifiersFactory.eINSTANCE.createEnumeration();
        EnumConstant constant = MembersFactory.eINSTANCE.createEnumConstant();
        myEnumeration.getConstants().add(constant);

        assertThat(constant.eContainer(), notNullValue());
    }
}
