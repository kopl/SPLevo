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
package org.splevo.jamopp.vpm.analyzer.programdependency.references;

/**
 * A registry for available reference selectors.
 *
 * The registry currently manages a hard coded list of selectors.
 */
public class ReferenceSelectorRegistry {

    public static final String ROBILLARD_SELECTOR = "Robillard";
    public static final String ROBILLARD_EXTENDED_SELECTOR = "Robillard Extended";
    public static final String ROBILLARD_EXTENDED_SHARED_ACCESS_SELECTOR = "Robillard Extended (SharedAccess)";


    /**
     * Get the reference selector for a specific selector id.
     *
     * @param id
     *            The id to get the selector for.
     * @return The found selector.
     */
    public static ReferenceSelector getReferenceSelector(String id) {

        if (ROBILLARD_SELECTOR.equals(id)) {
            return new RobillardReferenceSelector(false, false);
        } else if (ROBILLARD_EXTENDED_SELECTOR.equals(id)) {
            return new RobillardReferenceSelector(true, false);

        } else if (ROBILLARD_EXTENDED_SHARED_ACCESS_SELECTOR.equals(id)) {
            return new RobillardReferenceSelector(true, true);
        } else {
            throw new IllegalArgumentException("Non existing reference selector requested: " + id);
        }

    }

    /**
     * Get the ids of the available selectors.
     *
     * @return An array of the unique ids.
     */
    public static String[] getAvailableSelectorIds() {
        return new String[] { ROBILLARD_EXTENDED_SELECTOR, ROBILLARD_SELECTOR };
    }
}
