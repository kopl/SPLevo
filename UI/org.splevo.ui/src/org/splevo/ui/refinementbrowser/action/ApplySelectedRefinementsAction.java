/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.ui.refinementbrowser.action;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.splevo.ui.SPLevoUIPlugin;
import org.splevo.ui.refinementbrowser.VPMRefinementBrowser;
import org.splevo.vpm.refinement.Refinement;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * An action to trigger the application of selected refinements to a variation point model.
 */
public class ApplySelectedRefinementsAction extends ApplyRefinementsAction {

    /**
     * Constructs the action.
     * @param vpmRefinementBrowser The refinement browser to determine the selected refinements.
     * @param actionText The textual description for the action.
     */
    public ApplySelectedRefinementsAction(VPMRefinementBrowser vpmRefinementBrowser, String actionText) {
        super(vpmRefinementBrowser, actionText);
    }
    
    @Override
    protected List<Refinement> getRefinementsFromRefinementBrowser() {
        final List<Refinement> selectedRefinements = this.vpmRefinementBrowser.getSelectedRefinementsFromMainView();
        
        /**
         * We have to repair the selected refinements in the following situation:
         * Context: Group A contains Merges M1 and M2
         * Scenario: User selects A and M1
         * 
         * Fixes to apply:
         * - F1: M1 has to be removed from the refinements to apply because it is already contained in A
         * - F2: M2 has to be removed from A as this is the users choice
         * - F3: the variation points of M2 have to be added to A in order to do a correct grouping
         * 
         */
        
        // F1
        Iterable<Refinement> relevantSelectedRefinements = Iterables.filter(
                selectedRefinements, new Predicate<Refinement>() {
            @Override
            public boolean apply(final Refinement selectedRefinement) {
                return !Iterables.any(selectedRefinements, new Predicate<Refinement>() {
                    @Override
                    public boolean apply(final Refinement possibleParent) {
                        return possibleParent.getSubRefinements().contains(selectedRefinement);
                    }
                });
            } });
        
        // F2 and F3
        Iterable<Refinement> fixedRelevantSelectedRefinements = Iterables.transform(
                relevantSelectedRefinements, new Function<Refinement, Refinement>() {
            @Override
            public Refinement apply(Refinement arg0) {
                for (Iterator<Refinement> iter = arg0.getSubRefinements().iterator(); iter.hasNext();) {
                    Refinement r = iter.next();
                    if (!selectedRefinements.contains(r)) {
                        arg0.getVariationPoints().addAll(r.getVariationPoints());
                        iter.remove();
                    }
                }
                return arg0;
            } });
        
        return Lists.newArrayList(fixedRelevantSelectedRefinements);
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return SPLevoUIPlugin.getImageDescriptor("icons/apply-refinements-selected.gif");
    }

}
