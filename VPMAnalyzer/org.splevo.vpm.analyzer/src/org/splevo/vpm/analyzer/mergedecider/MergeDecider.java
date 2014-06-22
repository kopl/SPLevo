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

import org.splevo.vpm.variability.VariationPoint;

/**
 * A MergeDecider is able to decide if two variation points can technically be merged or not.
 *
 * This interface can be implemented to support this decision for specific technologies.
 */
public interface MergeDecider {

    /**
     * Decide about two variation points if they can be merged or not.
     *
     * The decision can be of three types:
     * <ol>
     * <li>TRUE: VPs can definitely being merged</li>
     * <li>FALSE: VPs can not be merged or it is not possible to decide about.</li>
     * </ol>
     *
     * @param vp1
     *            The first variation point to check.
     * @param vp2
     *            The second variation point to check.
     * @return True/False as described above.
     */
    public boolean canBeMerged(VariationPoint vp1, VariationPoint vp2);

}
