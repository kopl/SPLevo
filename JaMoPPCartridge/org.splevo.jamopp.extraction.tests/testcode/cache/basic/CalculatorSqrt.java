package org.splevo.examples.calculator;

import org.jscience.mathematics.number.FloatingPoint;

public class CalculatorSqrt {

    @Deprecated
	public String sqrt(String value1){

		FloatingPoint floatingPointValue = FloatingPoint.valueOf(value1);
		FloatingPoint sqrt = floatingPointValue.sqrt();

		return sqrt.toString();
	}

}
