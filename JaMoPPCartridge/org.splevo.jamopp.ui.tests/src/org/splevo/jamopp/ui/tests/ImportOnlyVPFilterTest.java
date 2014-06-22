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
package org.splevo.jamopp.ui.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.emftext.language.java.types.TypesFactory;
import org.emftext.language.java.types.Void;
import org.junit.Test;
import org.splevo.jamopp.ui.vpexplorer.filter.ImportOnlyVPFilter;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.jamopp.vpm.software.softwareFactory;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.variabilityFactory;

/**
 * Test case for a valid filtering.
 */
public class ImportOnlyVPFilterTest {

    /**
     * Test filtering because of an invalid variation point location
     */
    @Test
    public void testFilterVPLocation() {

        Void voidElement = TypesFactory.eINSTANCE.createVoid();
        JaMoPPSoftwareElement jamoppElement = softwareFactory.eINSTANCE.createJaMoPPSoftwareElement();
        jamoppElement.setJamoppElement(voidElement);
        VariationPoint vp = variabilityFactory.eINSTANCE.createVariationPoint();
        vp.setLocation(jamoppElement);

        boolean select = new ImportOnlyVPFilter().select(null, null, vp);

        assertThat("VP should be excluded", select, is(false));

    }

}
