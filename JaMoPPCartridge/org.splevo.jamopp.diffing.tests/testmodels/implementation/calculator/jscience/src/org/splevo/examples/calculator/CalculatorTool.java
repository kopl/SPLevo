package org.splevo.examples.calculator;

/**
 * The command line tool to run the calculator.
 */
public class CalculatorTool {

	/**
	 * Executing the calculator tool.
	 *
	 * @param args The command line args provided to the tooling.
	 */
	public static void main(String[] args) {

		if(args.length > 0 && args[0] == "sqrt"){
			String value = "13.25";

			CalculatorSqrt calculator = new CalculatorSqrt();
			String sqrt = calculator.sqrt(value);

			System.out.println(sqrt);

		} else {
			String value1 = "9876543210987654321098765432109876543210";
			String value2 = "1234567891234567891234567891234567891234";

			CalculatorGCD calculator = new CalculatorGCD();
			String gcd = calculator.gcd(value1, value2);

			System.out.println(gcd);
		}
	}
}
