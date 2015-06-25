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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.collect.Lists;

/**
 * Utility class for EObject replacements.
 */
public class ReplacementUtil {

    private static final Logger LOGGER = Logger.getLogger(ReplacementUtil.class);

    /**
     * Replaces all cross references to the given EObject from the same resource set with references
     * to the given replacement. This method does not consider containment references.
     * 
     * @param original
     *            The EObject to be replaced.
     * @param replacement
     *            The replacement for the given EObject.
     * @param additionalResourceSets
     *            Additional resource sets to be searched for cross references.
     */
    public static void replaceCrossReferences(EObject original, EObject replacement, ResourceSet... additionalResourceSets) {
        Set<Setting> crossReferences = new HashSet<Setting>();
        ArrayList<ResourceSet> resourceSets = new ArrayList<ResourceSet>();
        addResourceSetIfNotNull(original, resourceSets);
        addResourceSetIfNotNull(replacement, resourceSets);
        resourceSets.addAll(Lists.newArrayList(additionalResourceSets));
        for (ResourceSet rs : resourceSets) {  
            crossReferences.addAll(EcoreUtil.UsageCrossReferencer.find(original, rs));
        }
        
        for (Setting crossReference : crossReferences) {
            EObject referencingObject = crossReference.getEObject();
            EStructuralFeature referencingFeature = crossReference.getEStructuralFeature();

            if (referencingFeature instanceof EReference && ((EReference) referencingFeature).isContainment()) {
                LOGGER.warn(String.format("There should not be a containment reference from %s (%s) to %s.",
                        referencingObject.toString(), referencingFeature.getName(), original.toString()));
                continue;
            }

            if (referencingFeature.getUpperBound() > 1 || referencingFeature.getUpperBound() == -1) {
                Object value = referencingObject.eGet(referencingFeature);
                if (value instanceof List) {
                    @SuppressWarnings("unchecked")
                    List<EObject> valueList = (List<EObject>) value;
                    for (int i = 0; i < valueList.size(); ++i) {
                        if (valueList.get(i) == original) {
                            valueList.set(i, replacement);
                        }
                    }
                } else {
                    LOGGER.warn(String.format("We got a feature with upper bound %d that is no list but %s",
                            referencingFeature.getUpperBound(), value.getClass().getSimpleName()));
                }
            } else {
                referencingObject.eSet(referencingFeature, replacement);
            }
        }
    }

    private static void addResourceSetIfNotNull(EObject obj, Collection<ResourceSet> rs) {
        if (obj.eResource() != null && obj.eResource().getResourceSet() != null) {
            rs.add(obj.eResource().getResourceSet());
        }
    }
    
}
