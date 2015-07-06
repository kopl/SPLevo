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
package org.splevo.refactoring;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.splevo.commons.emf.ReplacementUtil;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Fully-automated variability refactoring. Fully-automated means that all refactoring steps are
 * carried out by this routine and the user does not have to specify additional information.
 */
public abstract class FullyAutomatedVariabilityRefactoring implements VariabilityRefactoring {

    private final Map<EObject, EObject> replacements = new HashMap<EObject, EObject>();
    private final Map<String, Set<EObject>> variantSpecificelements = new HashMap<String, Set<EObject>>();

    @Override
    public List<Resource> refactor(VariationPoint variationPoint, Map<String, Object> refactoringConfigurations) {
        List<Resource> changedResources = refactorFullyAutomated(variationPoint, refactoringConfigurations);
        fixVPMAfterRefactoring(variationPoint);
        return changedResources;
    }

    /**
     * Perform the refactoring in a fully-automated way.
     * 
     * @param variationPoint
     *            The variation point to be refactored.
     * @param refactoringConfigurations
     *            The refactoring configuration to consider.
     * @return The changed resources.
     */
    protected abstract List<Resource> refactorFullyAutomated(VariationPoint variationPoint,
            Map<String, Object> refactoringConfigurations);

    /**
     * Create a concrete, technology specific SoftwareElement instance for the given EObject.
     * 
     * @param eobject
     *            The EObject to be encapsulated in a SoftwareElement object.
     * @return The concrete SoftwareElement instance.
     */
    protected abstract SoftwareElement createSoftwareElement(EObject eobject);

    /**
     * Clones a given EObject. Use this method if you intend to move a variant-specific element.
     * E.g. if you want to copy an integration copy statement to the leading copy, use this method.
     * It ensures that the variation point model remains valid after the refactoring.
     * 
     * @param eobject
     *            The EObject to be cloned.
     * @param <T>
     *            The exact type of the EObject to be cloned.
     * @return The clone of the given EObject.
     */
    protected <T extends EObject> T clone(T eobject) {
        T copy = EcoreUtil.copy(eobject);
        registerReplacement(eobject, copy);
        return copy;
    }

    /**
     * Register a multi to one replacement. This means that multiple elements are replaced by a
     * single one. If the replacement is contained in the originals, the corresponding entry will be
     * ignored.
     * 
     * @param originals
     *            The element that have been replaced.
     * @param replacement
     *            The element that replaced the originals.
     */
    protected void registerReplacement(Iterable<? extends EObject> originals, EObject replacement) {
        Iterable<? extends EObject> filteredOriginals = Iterables.filter(originals,
                Predicates.not(Predicates.equalTo(replacement)));
        for (EObject original : filteredOriginals) {
            registerReplacement(original, replacement);
        }
    }

    /**
     * Register a new variant-specific EObject. If you create additional elements during the
     * refactorings that correspond to a variant, use this method to register them. They will be
     * added to the VPM later. It is possible to call this method multiple times for the same
     * EObject.
     * 
     * @param eobject
     *            The newly created element.
     * @param variantId
     *            The ID of a variant to which the created element belongs to.
     */
    protected void registerVariantSpecificNewEObject(EObject eobject, String variantId) {
        if (!variantSpecificelements.containsKey(variantId)) {
            variantSpecificelements.put(variantId, new HashSet<EObject>());
        }
        variantSpecificelements.get(variantId).add(eobject);
    }

    private void registerReplacement(EObject original, EObject replacement) {
        replacements.put(original, replacement);
    }

    private static Iterable<EObject> getAllParents(EObject eobject, boolean includeSelf) {
        List<EObject> parents = Lists.newArrayList();
        if (includeSelf) {
            parents.add(eobject);
        }
        EObject target = eobject.eContainer();
        while (target != null) {
            parents.add(target);
            EObject newTarget = eobject.eContainer();
            if (newTarget == target) {
                break;
            }
            target = newTarget;
        }
        return parents;
    }

    /**
     * DTO for the replacements partitioning.
     */
    private static class PartitionedReplacements {
        private final List<Map.Entry<EObject, EObject>> apply = Lists.newArrayList();
        private final List<Map.Entry<EObject, EObject>> delete = Lists.newArrayList();

        public List<Map.Entry<EObject, EObject>> getApply() {
            return apply;
        }

        public List<Map.Entry<EObject, EObject>> getDelete() {
            return delete;
        }
    }

    private PartitionedReplacements partitionReplacements() {
        final Set<EObject> allNewVariantSpecificElements = Sets.newHashSet(Iterables.concat(variantSpecificelements
                .values()));
        Predicate<Map.Entry<EObject, EObject>> partitionPredicate = new Predicate<Map.Entry<EObject, EObject>>() {
            @Override
            public boolean apply(Entry<EObject, EObject> arg0) {
                Set<EObject> parents = Sets.newHashSet(getAllParents(arg0.getValue(), true));
                return Sets.intersection(parents, allNewVariantSpecificElements).size() < 1;
            }
        };

        PartitionedReplacements partitionedRefinements = new PartitionedReplacements();
        for (Map.Entry<EObject, EObject> replacement : replacements.entrySet()) {
            if (partitionPredicate.apply(replacement)) {
                partitionedRefinements.getApply().add(replacement);
            } else {
                partitionedRefinements.getDelete().add(replacement);
            }
        }
        return partitionedRefinements;
    }

    private void removeSoftwareElement(EObject eobject, VariationPoint variationPoint) {
        for (Variant variant : variationPoint.getVariants()) {
            Iterator<SoftwareElement> swElementIterator = variant.getImplementingElements().iterator();
            while (swElementIterator.hasNext()) {
                if (swElementIterator.next().getWrappedElement() == eobject) {
                    swElementIterator.remove();
                    return;
                }
            }
        }
    }

    private void fixVPMAfterRefactoring(VariationPoint variationPoint) {

        PartitionedReplacements partitionedReplacements = partitionReplacements();

        for (Map.Entry<EObject, EObject> replacement : partitionedReplacements.getApply()) {
            // TODO we possibly could restrict the cross reference check to the VPM
            ReplacementUtil.replaceCrossReferences(replacement.getKey(), replacement.getValue(), variationPoint
                    .eResource().getResourceSet());
        }

        for (Map.Entry<EObject, EObject> replacement : partitionedReplacements.getDelete()) {
            removeSoftwareElement(replacement.getKey(), variationPoint);
        }

        for (Map.Entry<String, Set<EObject>> variantSpecifics : variantSpecificelements.entrySet()) {
            final String variantId = variantSpecifics.getKey();
            Variant variant = Iterables.find(variationPoint.getVariants(), new Predicate<Variant>() {
                @Override
                public boolean apply(Variant arg0) {
                    return variantId.equals(arg0.getId());
                }
            });
            if (variant == null) {
                // error
                continue;
            }

            for (EObject eobject : variantSpecifics.getValue()) {
                SoftwareElement swElement = createSoftwareElement(eobject);
                variant.getImplementingElements().add(swElement);
            }
        }

    }
}
