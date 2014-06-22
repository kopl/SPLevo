package org.splevo.examples.calculator;

import org.jscience.mathematics.number.LargeInteger;

/**
 * The calculator to perform numeric calculations.
 * This makes use of the LargeInteger class of the
 * JScience mathematics library (www.jscience.org)
 */
public class CalculatorGCD {

	/**
	 * Calculate the greatest common denominator (divider) (GCD) of an integer
	 * @param value1 The first number to get the gcd.
	 * @param value2 The second number to get the gcd.
	 * @return
	 */
	public String gcd(String value1, String value2){

		LargeInteger integerValue1 = LargeInteger.valueOf(value1);
		LargeInteger integerValue2 = LargeInteger.valueOf(value2);
		LargeInteger gcd = integerValue1.gcd(integerValue2);

		return gcd.toString();
	}
}
