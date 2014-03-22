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
package org.splevo.vpm.analyzer.mergedecider;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 * Static registry for available merge decider.
 */
public class MergeDeciderRegistry {

    private static List<MergeDecider> deciders = Lists.newArrayList();

    /**
     * Register a new merge decider.
     *
     * Note: If a merge decider instance has already been registered, nothing is done.<br>
     *
     * @param mergeDecider
     *            The merge decider to register.
     */
    public static void registerMergeDecider(MergeDecider mergeDecider) {
        if (isValid(mergeDecider) && isNotRegistered(mergeDecider)) {
            deciders.add(mergeDecider);
        }
    }

    /**
     * Get the list of registered merge decider.
     *
     * The list will be ordered according to the merge decider's class name.
     *
     * @return The current list.
     */
    public static List<MergeDecider> getMergeDecider() {
        Collections.sort(deciders, new Comparator<MergeDecider>() {
            @Override
            public int compare(MergeDecider c1, MergeDecider c2) {
                String name1 = c1.getClass().getSimpleName();
                String name2 = c2.getClass().getSimpleName();
                return Strings.nullToEmpty(name1).compareTo(name2);
            }
        });
        return deciders;
    }

    /**
     * Ensure the merge decider is not null and all required attributes set.
     *
     * @param mergeDecider
     *            The merge decider to test.
     * @return True/false if it is valid or not.
     */
    private static boolean isValid(MergeDecider mergeDecider) {
        if (mergeDecider == null) {
            return false;
        }
        return true;
    }

    private static boolean isNotRegistered(MergeDecider mergeDecider) {
        for (MergeDecider existingDecider : deciders) {
            if (existingDecider.getClass().equals(mergeDecider.getClass())) {
                return false;
            }
        }
        return true;
    }

}
