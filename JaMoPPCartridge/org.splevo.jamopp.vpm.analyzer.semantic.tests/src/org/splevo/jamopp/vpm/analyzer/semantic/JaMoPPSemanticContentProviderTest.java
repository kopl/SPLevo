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

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.extraction.SoftwareModelExtractionException;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.jamopp.vpm.software.softwareFactory;
import org.splevo.vpm.analyzer.semantic.extensionpoint.SemanticContent;
import org.splevo.vpm.analyzer.semantic.extensionpoint.UnsupportedSoftwareElementException;
import org.splevo.vpm.software.SoftwareElement;

import com.google.common.collect.Lists;

/**
 * Test for the semantic content provider for JaMoPP elements.
 */
public class JaMoPPSemanticContentProviderTest {

    private JaMoPPSemanticContentProvider provider;

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void init() {
        // set up a basic logging configuration for the test environment
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * Initializes the test subject.
     */
    @Before
    public void setUp() {
        provider = new JaMoPPSemanticContentProvider();
    }

    /**
     * Test the returned semantic content for a block element.
     * 
     * @throws UnsupportedSoftwareElementException
     *             A failed test.
     */
    @Test
    public void testGetRelevantContent() throws UnsupportedSoftwareElementException {
        JaMoPPSoftwareElement element = softwareFactory.eINSTANCE.createJaMoPPSoftwareElement();
        Block myBlock = StatementsFactory.eINSTANCE.createBlock();
        myBlock.setName("Block");
        element.setJamoppElement(myBlock);

        SemanticContent relevantContent = provider.getRelevantContent(element, false);

        Collection<String> code = relevantContent.getCode();
        assertThat(code.size(), equalTo(0));

    }

    /**
     * Test the extraction of compilation units content.
     * 
     * @throws Exception
     *             Failed test code loading.
     */
    @Test
    public void testProvideCompilationUnitContent() throws Exception {
        SoftwareElement element = constructSoftwareElement("CompilationUnit");
        String[] expected = { "MyClass", "MyClass.java", "doSth", "param1" };

        Collection<String> actual = provider.getRelevantContent(element, false).getCode();

        assertThat(actual, hasItems(expected));
        assertThat(actual.size(), equalTo(expected.length));
    }

    /**
     * Test the extended extraction of compilation unit contents. The extraction is extended because
     * not only names of elements but also references to elements are considered.
     * 
     * @throws SoftwareModelExtractionException
     *             Failed execution of test subject.
     * @throws IOException
     *             Failed test code loading.
     * @throws UnsupportedSoftwareElementException
     *             Failed test code loading.
     */
    @Test
    public void testProvideCompilationUnitContentExtended() throws UnsupportedSoftwareElementException,
            SoftwareModelExtractionException, IOException {
        SoftwareElement element = constructSoftwareElement("ExtendedDetection");
        String[] expected = { "OtherClass.java", "OtherClass", "aMethod", "aNumber", "Arrays", "asList", "System",
                "out", "println", "qq123" };

        Collection<String> actual = provider.getRelevantContent(element, false).getCode();

        assertThat(actual, hasItems(expected));
        assertThat(actual.size(), equalTo(expected.length));
    }

    private SoftwareElement constructSoftwareElement(String name) throws IOException, SoftwareModelExtractionException {
        String relativePath = "testcode/" + name + "/";

        String pathA = new File(relativePath).getCanonicalPath();
        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        ResourceSet setA = extractor.extractSoftwareModel(Lists.newArrayList(pathA), new NullProgressMonitor());
        Resource resource = setA.getResources().get(0);
        EObject eObject = resource.getContents().get(0);

        JaMoPPSoftwareElement element = softwareFactory.eINSTANCE.createJaMoPPSoftwareElement();
        element.setJamoppElement((Commentable) eObject);
        return element;
    }

}
