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
package org.splevo.commons.emf;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for the {@link SPLevoResourceSet}.
 */
public class SPLevoResourceSetTest {

    private SPLevoResourceSet rs;

    /**
     * Initializes the test case.
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
    }

    /**
     * Prepares a test run.
     */
    @Before
    public void setUp() {
        rs = new SPLevoResourceSet();
    }

    /**
     * Tests that a file URI containing spaces is not altered by the resource set if no platform
     * URIs are required.
     */
    @Test
    public void testFileURIWithSpace() {
        URI testURI = URI.createFileURI("/ab c/def.xmi");
        Resource r = rs.createResource(testURI);

        URI actualURI = r.getURI();

        assertEquals(testURI, actualURI);
    }

    /**
     * Tests that a file URI containing segments is not altered by the resource set if no platform
     * URIs are required.
     */
    @Test
    public void testFileURIWithSegment() {
        URI testURI = URI.createFileURI("/ab c/def.xmi");
        testURI = URI.createURI(testURI.toString() + "#ABC123", true, URI.FRAGMENT_LAST_SEPARATOR);
        Resource r = rs.createResource(testURI);

        URI actualURI = r.getURI();

        assertEquals(testURI, actualURI);
    }

}
