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

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.StatementsFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.jamopp.vpm.software.softwareFactory;
import org.splevo.vpm.analyzer.semantic.extensionpoint.SemanticContent;
import org.splevo.vpm.analyzer.semantic.extensionpoint.UnsupportedSoftwareElementException;

import com.google.common.collect.Lists;

/**
 * Test for the semantic content provider for JaMoPP elements.
 */
public class JaMoPPSemanticContentProviderTest {

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void setUp() {
        // set up a basic logging configuration for the test environment
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

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

    /**
     * Test the extraction of compilation units content.
     *
     * @throws Exception
     *             Failed test code loading.
     */
    @Test
    public void testProvideCompilationUnitContent() throws Exception {

        String relativePath = "testcode/CompilationUnit/";

        JaMoPPSemanticContentProvider provider = new JaMoPPSemanticContentProvider();

        String pathA = new File(relativePath).getCanonicalPath();
        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        ResourceSet setA = extractor.extractSoftwareModel(Lists.newArrayList(pathA), new NullProgressMonitor());
        Resource resource = setA.getResources().get(0);
        EObject eObject = resource.getContents().get(0);

        JaMoPPSoftwareElement element = softwareFactory.eINSTANCE.createJaMoPPSoftwareElement();
        element.setJamoppElement((Commentable) eObject);

        SemanticContent relevantContent = provider.getRelevantContent(element, false);
        assertThat(relevantContent.getCode().size(), is(4));
        assertThat(relevantContent.getCode(), hasItem("MyClass"));
        assertThat(relevantContent.getCode(), hasItem("MyClass.java"));
        assertThat(relevantContent.getCode(), hasItem("doSth"));
        assertThat(relevantContent.getCode(), hasItem("param1"));
    }

}
