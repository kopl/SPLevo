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
	private double lowerBoundary;
	
	/** The maximum value. */
	private double upperBoundary;
	
	/** The amount of digits after the dot. */
	private int numFractionalDigits;

	/**
	 * This is the main constructor of this class.
	 * 
	 * @param label
	 *            The configurations' text label.
	 * @param explanation
	 *            A detailed description of the configuration.
	 * @param defaultValue
	 *            The default value for the configuration.
	 * @param stepSize Sets the size of the steps in the UI for increasing / decreasing the value stepwise.
	 * @param lowerBoundary The minimum value.
	 * @param upperBoundary The maximum value.
	 * @param numFractionalDigits The amount of digits after the dot.
	 */
	public NumericConfiguration(String label, String explanation,
			double defaultValue, double stepSize, double lowerBoundary, double upperBoundary, int numFractionalDigits) {
		super(label, explanation, defaultValue);
		this.stepSize = stepSize;
		this.lowerBoundary = lowerBoundary;
		this.upperBoundary = upperBoundary;
		this.numFractionalDigits = numFractionalDigits;
	}
	
	/**
	 * @return The step size.
	 */
	public double getStepSize() {
		return stepSize;
	}
	
	/**
	 * @param stepSize The step size.
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
	 * @param lowerBoundary The minimum value.
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
	 * @param upperBoundary The maximum value.
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
	 * @param numFractionalDigits The amount of fractional digits.
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