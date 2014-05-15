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

import java.util.List;

import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;

/**
 * The result of a variability mechanism recommendation run.
 */
public class RecommenderResult {

    /**
     * The status the recommender ended with.
     */
    public enum Status {
        SUCCESS, FAILED
    }

    private Status status = Status.SUCCESS;

    private final List<VariationPoint> unassignedVariationPoints = Lists.newArrayList();

    /**
     * Access the list of variation points that could not be automatically assigned with a
     * variability mechnism refactoring.
     *
     * @return The list of vps.
     */
    public List<VariationPoint> getUnassignedVariationPoints() {
        return unassignedVariationPoints;
    }

    /**
     * Get the status of the result.
     *
     * @return The current status.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Set the status of the result.
     *
     * @param status
     *            The status to set.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

}
