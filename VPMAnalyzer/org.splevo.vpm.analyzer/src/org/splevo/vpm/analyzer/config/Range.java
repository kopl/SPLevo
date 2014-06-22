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
package org.splevo.vpm.analyzer.config;

/**
 * A simple range info container. From and to are defined as inclusive values.
 */
public class Range {

    private double lower = 0;
    private double upper = 0;

    /**
     * Creating a range and ensuring from and to to be in the right order.
     *
     * @param lower
     *            The lower value
     * @param upper
     *            The upper value
     */
    public Range(double lower, double upper) {
        if (upper < lower) {
            this.lower = upper;
            this.upper = lower;
        } else {
            this.lower = lower;
            this.upper = upper;
        }
    }

    /**
     * Get the lower end of the range.
     *
     * @return The from value
     */
    public double getLower() {
        return lower;
    }

    /**
     * Get the upper end of the range.
     *
     * @return The to value
     */
    public double getUpper() {
        return upper;
    }
}