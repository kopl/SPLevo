package org.splevo.examples.calculator;

import java.math.BigInteger;

/**
 * The calculator to perform numeric calculations.
 */
public class CalculatorGCD {

	/**
	 * Calculate the greatest common denominator (divider) (GCD) of an integer
	 * @param value1 The first number to get the gcd.
	 * @param value2 The second number to get the gcd.
	 * @return
	 */
	public String gcd(String value1, String value2){

		BigInteger integerValue1 = new BigInteger(value1);
		BigInteger integerValue2 = new BigInteger(value2);
		BigInteger gcd = integerValue1.gcd(integerValue2);

		return gcd.toString();
	}
}
