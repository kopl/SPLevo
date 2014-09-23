/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *    Daniel Kojic
 *    Christian Busch
 *******************************************************************************/
package org.splevo.vpm.analyzer.config;

/**
 * This class is built for integer numerical inputs.
 * 
 */
public class NumericConfiguration extends AbstractVPMAnalyzerConfiguration<Integer> {

    /** Sets the size of the steps in the UI for increasing / decreasing the value stepwise. */
    private int stepSize;

    /** The minimum value. */
    private int lowerBoundary;

    /** The maximum value. */
    private int upperBoundary;

    /**
     * This is the main constructor of this class.
     * 
     * @param id
     *            The unique identifier of the configuration option.
     * @param label
     *            The configurations' text label.
     * @param explanation
     *            A detailed description of the configuration.
     * @param defaultValue
     *            The default value for the configuration.
     * @param stepSize
     *            Sets the size of the steps in the UI for increasing / decreasing the value
     *            stepwise.
     * @param range
     *            The allowed value. Unlimited in case of null
     */
    public NumericConfiguration(String id, String label, String explanation, int defaultValue, int stepSize,
            Range<Integer> range) {
        super(id, label, explanation, defaultValue);
        this.stepSize = stepSize;
        if (range == null) {
            throw new IllegalArgumentException("Range must not be null.");
        }
        this.lowerBoundary = range.getLower();
        this.upperBoundary = range.getUpper();
    }

    /**
     * @return The step size.
     */
    public int getStepSize() {
        return stepSize;
    }

    /**
     * @param stepSize
     *            The step size.
     */
    public void setStepSize(int stepSize) {
        this.stepSize = stepSize;
    }

    /**
     * @return The minimum value.
     */
    public int getLowerBoundary() {
        return lowerBoundary;
    }

    /**
     * @param lowerBoundary
     *            The minimum value.
     */
    public void setLowerBoundary(int lowerBoundary) {
        this.lowerBoundary = lowerBoundary;
    }

    /**
     * @return The maximum value.
     */
    public int getUpperBoundary() {
        return upperBoundary;
    }

    /**
     * @param upperBoundary
     *            The maximum value.
     */
    public void setUpperBoundary(int upperBoundary) {
        this.upperBoundary = upperBoundary;
    }

}
