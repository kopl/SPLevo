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
 *    Christian Busch
 *******************************************************************************/
package org.splevo.vpm.analyzer.config;

/**
 * A simple range info container. From and to are defined as inclusive values.
 * 
 * @param <T>
 *            A subtype of Number and Comparable to be used as value type of the range.
 */
public class Range<T extends Number & Comparable<? super T>> {

    private final T lower;
    private final T upper;

    /**
     * Creating a range and ensuring from and to to be in the right order.
     * 
     * @param lower
     *            The lower value
     * @param upper
     *            The upper value
     */
    public Range(T lower, T upper) {
        if (lower.compareTo(upper) <= 0) {
            this.lower = lower;
            this.upper = upper;
        } else {
            throw new IllegalArgumentException("The lower bound (" + lower
                    + ") has to be smaller than or equal to the upper bound (" + upper + ").");
        }
    }

    /**
     * Get the lower end of the range.
     * 
     * @return The from value
     */
    public T getLower() {
        return lower;
    }

    /**
     * Get the upper end of the range.
     * 
     * @return The to value
     */
    public T getUpper() {
        return upper;
    }
}