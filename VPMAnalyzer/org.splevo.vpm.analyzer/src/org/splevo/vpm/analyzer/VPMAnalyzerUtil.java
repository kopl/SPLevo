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
package org.splevo.vpm.analyzer;

import org.eclipse.emf.ecore.EObject;

/**
 * Utility class for common processing done by VPM analyzers.
 */
public final class VPMAnalyzerUtil {

    /** Disable constructor for utility class. */
    private VPMAnalyzerUtil() {
    }

    /**
     * Check if an element is null or a proxy but not full instantiated element.
     *
     * @param element
     *            The element to check.
     * @return True if the element is either null or an unresolved proxy.
     */
    public static boolean isNullOrProxy(EObject element) {
        return element == null || element.eIsProxy();
    }

}
