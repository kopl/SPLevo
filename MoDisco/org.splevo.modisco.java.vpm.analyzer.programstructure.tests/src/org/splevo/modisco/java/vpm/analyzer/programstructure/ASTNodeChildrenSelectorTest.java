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
package org.splevo.modisco.java.vpm.analyzer.programstructure;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.junit.Test;

/**
 * Test case to validate children selector model switch.
 *
 * @author Benjamin Klatt
 *
 */
public class ASTNodeChildrenSelectorTest {

    /**
     * Create an ast node children selector and prove it agains a list of different ast nodes.
     */
    @SuppressWarnings("restriction")
    @Test
    public void testDoSwitchEClassEObject() {
        ASTNodeChildrenSelector astNodeTraverser = new ASTNodeChildrenSelector();

        ClassDeclaration classDeclaration = JavaFactory.eINSTANCE.createClassDeclaration();
        classDeclaration.getBodyDeclarations().add(JavaFactory.eINSTANCE.createMethodDeclaration());

        List<ASTNode> classDeclarationChilds = astNodeTraverser.doSwitch(classDeclaration);
        assertEquals("Wrong number of children", 1, classDeclarationChilds.size());
    }
}
