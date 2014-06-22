/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic - initial API and implementation and/or initial documentation
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.diffing;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * A registry for available content providers.
 */
public class DifferRegistry {

    private static List<Differ> differs = Lists.newArrayList();

    /**
     * Register a new differ.
     *
     * Note: If a differ instance has already been registered, nothing is done.<br>
     * Instance and id checking can be done.
     *
     * @param differ
     *            The provider itself.
     */
    public static void registerDiffer(Differ differ) {
        if (isValid(differ) && isNotRegistered(differ)) {
            differs.add(differ);
        }
    }

    /**
     * Get the list of registered content providers.
     *
     * The list will be ordered according to the differs order id.
     *
     * @return The current list.
     */
    public static List<Differ> getDiffer() {
        Collections.sort(differs, new Comparator<Differ>() {
            @Override
            public int compare(Differ d1, Differ d2) {
                return d1.getOrderId() - d2.getOrderId();
            }
        });
        return differs;
    }

    /**
     * Get a differ for a specific id.
     *
     * @param id
     *            The id to get the differ for.
     * @return The differ or null if non exists for the provided id.
     */
    public static Differ getDifferById(String id) {
        for (Differ differ : differs) {
            if (differ.getId().equals(id)) {
                return differ;
            }
        }
        return null;
    }

    /**
     * Ensure the differ is not null and all required attributes set.
     *
     * @param differ
     *            The differ to test.
     * @return True/false if it is valid or not.
     */
    private static boolean isValid(Differ differ) {
        if (differ == null || differ.getId() == null) {
            return false;
        }
        return true;
    }

    private static boolean isNotRegistered(Differ differ) {
        for (Differ existingDiffer : differs) {
            if (existingDiffer.getId().equals(differ.getId())) {
                return false;
            }
        }
        return true;
    }

}
