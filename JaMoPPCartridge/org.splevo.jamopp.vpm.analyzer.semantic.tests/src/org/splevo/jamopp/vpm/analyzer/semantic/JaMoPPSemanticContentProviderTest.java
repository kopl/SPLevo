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
package org.splevo.jamopp.vpm.analyzer.semantic;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.StatementsFactory;
import org.junit.Test;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.jamopp.vpm.software.softwareFactory;
import org.splevo.vpm.analyzer.semantic.extensionpoint.SemanticContent;
import org.splevo.vpm.analyzer.semantic.extensionpoint.UnsupportedSoftwareElementException;

/**
 * Test for the semantic content provider for JaMoPP elements.
 */
public class JaMoPPSemanticContentProviderTest {

    /**
     * Test the returned semantic content for a block element.
     *
     * @throws UnsupportedSoftwareElementException
     *             A failed test.
     */
    @Test
    public void testGetRelevantContent() throws UnsupportedSoftwareElementException {

        JaMoPPSemanticContentProvider provider = new JaMoPPSemanticContentProvider();

        JaMoPPSoftwareElement element = softwareFactory.eINSTANCE.createJaMoPPSoftwareElement();
        Block myBlock = StatementsFactory.eINSTANCE.createBlock();
        myBlock.setName("Block");
        element.setJamoppElement(myBlock);

        SemanticContent relevantContent = provider.getRelevantContent(element, false);

        List<String> code = relevantContent.getCode();
        assertThat(code.size(), is(0));

    }

}
