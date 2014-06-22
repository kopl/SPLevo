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
 *******************************************************************************/
package org.splevo.vpm.analyzer.config;

/**
 * This class is built for numerical inputs (integer & floats).
 *
 * @author Daniel Kojic
 *
 */
public class NumericConfiguration extends AbstractVPMAnalyzerConfiguration<Double> {

    /** Sets the size of the steps in the UI for increasing / decreasing the value stepwise. */
    private double stepSize;

    /** The minimum value. */
    private double lowerBoundary = 0;

    /** The maximum value. */
    private double upperBoundary = 0;

    /** The amount of digits after the dot. */
    private int numFractionalDigits;

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
     * @param numFractionalDigits
     *            The amount of digits after the dot.
     */
    public NumericConfiguration(String id, String label, String explanation, double defaultValue, double stepSize,
            Range range, int numFractionalDigits) {
        super(id, label, explanation, defaultValue);
        this.stepSize = stepSize;
        if (range != null) {
            this.lowerBoundary = range.getLower();
            this.upperBoundary = range.getUpper();
        }
        this.numFractionalDigits = numFractionalDigits;
    }

    /**
     * @return The step size.
     */
    public double getStepSize() {
        return stepSize;
    }

    /**
     * @param stepSize
     *            The step size.
     */
    public void setStepSize(double stepSize) {
        this.stepSize = stepSize;
    }

    /**
     * @return The minimum value.
     */
    public double getLowerBoundary() {
        return lowerBoundary;
    }

    /**
     * @param lowerBoundary
     *            The minimum value.
     */
    public void setLowerBoundary(double lowerBoundary) {
        this.lowerBoundary = lowerBoundary;
    }

    /**
     * @return The maximum value.
     */
    public double getUpperBoundary() {
        return upperBoundary;
    }

    /**
     * @param upperBoundary
     *            The maximum value.
     */
    public void setUpperBoundary(double upperBoundary) {
        this.upperBoundary = upperBoundary;
    }

    /**
     * @return The amount of fractional digits.
     */
    public int getNumFractionalDigits() {
        return numFractionalDigits;
    }

    /**
     * @param numFractionalDigits
     *            The amount of fractional digits.
     */
    public void setNumFractionalDigits(int numFractionalDigits) {
        this.numFractionalDigits = numFractionalDigits;
    }

    /**
     * Gets the current value as integer value. Rounded correctly as in Math.round(...).
     *
     * @return The integer value.
     */
    public Integer getIntegerValue() {
        return (int) (getCurrentValue() + 0.5d);
    }
}
