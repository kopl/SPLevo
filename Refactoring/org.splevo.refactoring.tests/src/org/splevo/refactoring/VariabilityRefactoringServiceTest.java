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
package org.splevo.refactoring;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityFactory;

import com.google.common.collect.Lists;

/**
 * Tests for the variability refactoring service.
 */
public class VariabilityRefactoringServiceTest {

    /**
     * Test that the recommendation recommends reasonable refactorings.
     */
    @Test
    public void testRecommendMechanisms() {

        VariabilityRefactoring refactoring = buildRefactoring("REFACTORING1");
        VariabilityRefactoring refactoring2 = buildRefactoring("REFACTORING2");
        List<VariabilityRefactoring> refactorings = Lists.newArrayList(refactoring, refactoring2);

        List<VariabilityMechanism> mechanisms = Lists.newArrayList(null, refactoring2.getVariabilityMechanism());
        VariationPointModel model = crateVPMWithOneGroup(mechanisms);

        VariabilityRefactoringService service = new VariabilityRefactoringService();
        RecommenderResult result = service.recommendMechanisms(model, refactorings);

        assertNoUnassignedVP(result);
        assertExpectedMechanismsAssigned(model, refactorings);
    }

    /**
     * Create a variation point model with one group and as much VPs as entries in the provided
     * mechanisms list. Each entry in the list is assigned to one created VP.
     *
     * @param mechnisms
     *            The mechanisms to assing. Null is allowed.
     * @return The new VPM.
     */
    private VariationPointModel crateVPMWithOneGroup(List<VariabilityMechanism> mechnisms) {
        VariationPointModel model = variabilityFactory.eINSTANCE.createVariationPointModel();
        VariationPointGroup group = variabilityFactory.eINSTANCE.createVariationPointGroup();
        model.getVariationPointGroups().add(group);

        for (VariabilityMechanism mechanism : mechnisms) {
            VariationPoint vp = variabilityFactory.eINSTANCE.createVariationPoint();
            vp.setVariabilityMechanism(mechanism);
            group.getVariationPoints().add(vp);
        }

        return model;
    }

    private void assertExpectedMechanismsAssigned(VariationPointModel model, List<VariabilityRefactoring> refactorings) {
        VariationPointGroup group = model.getVariationPointGroups().get(0);

        for (int i = 0; i < refactorings.size(); i++) {
            VariationPoint vp = group.getVariationPoints().get(i);
            VariabilityMechanism refMechanism = refactorings.get(i).getVariabilityMechanism();
            VariabilityMechanism vpMechanism = vp.getVariabilityMechanism();
            assertThat(vpMechanism.getRefactoringID(), equalTo(refMechanism.getRefactoringID()));
        }
    }

    private void assertNoUnassignedVP(RecommenderResult result) {
        assertThat(result.getUnassignedVariationPoints().size(), is(0));
    }

    private VariabilityRefactoring buildRefactoring(String refactoringID) {
        VariabilityMechanism mechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        mechanism.setRefactoringID(refactoringID);
        VariabilityRefactoring refactoring = mock(VariabilityRefactoring.class);
        when(refactoring.getVariabilityMechanism()).thenReturn(mechanism);
        when(refactoring.getId()).thenReturn(refactoringID);
        return refactoring;
    }

}
