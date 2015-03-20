/*******************************************************************************
 * Copyright (c) 2015
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.fm.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.splevo.fm.builder.FeatureModelBuilderRegistry.InnerFeatureModelBuilderRegistry;

/**
 * Test to verify the specific methods of the FeatureModelBuilderRegistry.
 */
public class InnerFeatureModelBuilderRegistryTest {
    
    private InnerFeatureModelBuilderRegistry subject;
    
    /**
     * Sets up the test subject.
     */
    @Before
    public void setUp() {
        subject = FeatureModelBuilderRegistry.getInstance();
    }

    /**
     * Reverts all effects of a run test. 
     */
    @After
    public void tearDown() {
        FeatureModelBuilderRegistry.getInstance().getElements().clear();
    }

    /**
     * Tests the simplest case of the GetIdByLabel method (only the matching item is registered).
     */
    @Test
    public void testGetIdByLabelBasic() {
        FeatureModelBuilder<Object> fmBuilderMock = createFeatureModelBuilderMock("0", "a");
        subject.registerElement(fmBuilderMock);
        
        assertEquals("0", subject.getIdByLabel("a"));
    }
    
    /**
     * Tests the GetIdByLabel method for multiple registered items.
     */
    @Test
    public void testGetIdByLabelWithMultipleItems() {
        subject.registerElement(createFeatureModelBuilderMock("0", "a"));
        subject.registerElement(createFeatureModelBuilderMock("1", "b"));
        subject.registerElement(createFeatureModelBuilderMock("2", "c"));
        
        assertEquals("1", subject.getIdByLabel("b"));
    }
    
    /**
     * Tests the GetIdByLabel method for an empty registry.
     */
    @Test
    public void testGetIdByLabelReturnsNullOnEmptyRegistry() {        
        assertNull(subject.getIdByLabel("a"));
    }
    
    /**
     * Tests the GetIdByLabel method for a missing label.
     */
    @Test
    public void testGetIdByLabelReturnsNullOnMissingLabel() {   
        subject.registerElement(createFeatureModelBuilderMock("0", "a"));
        assertNull(subject.getIdByLabel("b"));
    }
    
    private static FeatureModelBuilder<Object> createFeatureModelBuilderMock(String id, String label) {
        @SuppressWarnings("unchecked")
        FeatureModelBuilder<Object> fmBuilderMock = mock(FeatureModelBuilder.class);
        when(fmBuilderMock.getId()).thenReturn(id);
        when(fmBuilderMock.getLabel()).thenReturn(label);
        return fmBuilderMock;
    }

}
