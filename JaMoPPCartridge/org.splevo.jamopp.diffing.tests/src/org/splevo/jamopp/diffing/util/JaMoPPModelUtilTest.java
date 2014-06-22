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
package org.splevo.jamopp.diffing.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersFactory;
import org.junit.Test;

/**
 * Test the utility functions.
 */
public class JaMoPPModelUtilTest {

    /**
     * Test for the utility method to build a path string for a package containment.
     */
    @Test
    public void testBuildPackagePath() {
        CompilationUnit cu = ContainersFactory.eINSTANCE.createCompilationUnit();
        cu.getNamespaces().add("java");
        cu.getNamespaces().add("lang");

        org.emftext.language.java.classifiers.Class bigIntClass = ClassifiersFactory.eINSTANCE.createClass();
        bigIntClass.setName("BigInteger");

        cu.getClassifiers().add(bigIntClass);

        String packagePath = JaMoPPModelUtil.buildNamespacePath(bigIntClass);

        assertThat("Package should match", packagePath, is("java.lang"));

    }

}
