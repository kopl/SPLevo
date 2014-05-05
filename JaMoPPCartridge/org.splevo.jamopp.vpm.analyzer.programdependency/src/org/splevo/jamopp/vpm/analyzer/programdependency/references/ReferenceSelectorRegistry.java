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

    public static final String DEFAULT_SELECTOR = "Default";
    public static final String ROBILLARD_SELECTOR = "Robillard";

    /**
     * Get the reference selector for a specific selecor id.
     *
     * @param id
     *            The id to get the selector for.
     * @return The found selector.
     */
    public static ReferenceSelector getReferenceSelector(String id) {

        if (DEFAULT_SELECTOR.equals(id)) {
            return new DefaultReferenceSelector();
        } else if (ROBILLARD_SELECTOR.equals(id)) {
            return new RobillardReferenceSelector();
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
        return new String[] { DEFAULT_SELECTOR, ROBILLARD_SELECTOR };
    }
}
