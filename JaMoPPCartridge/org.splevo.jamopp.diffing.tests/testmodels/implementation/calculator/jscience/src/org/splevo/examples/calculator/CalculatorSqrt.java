package org.splevo.examples.calculator;

import org.jscience.mathematics.number.FloatingPoint;

/**
 * Calculator to get the positive square root of
 * a floating point number.
 * This makes use of the FloatingPoint class of the
 * JScience mathematics library (www.jscience.org)
 */
public class CalculatorSqrt {

	/**
	 * Calculate the square root (sqrt) of a floating point number.
	 * @param value1 The number to get the square root for.
	 * @return The calculated square root.
	 */
	public String sqrt(String value1){

		FloatingPoint floatingPointValue = FloatingPoint.valueOf(value1);
		FloatingPoint sqrt = floatingPointValue.sqrt();

		return sqrt.toString();
	}

}
