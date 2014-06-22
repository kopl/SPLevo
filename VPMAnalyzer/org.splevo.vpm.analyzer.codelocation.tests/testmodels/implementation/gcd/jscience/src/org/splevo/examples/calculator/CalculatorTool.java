package org.splevo.examples.calculator;

/**
 * The command line tool to run the calculator.
 * 
 * @author Benjamin Klatt
 *
 */
public class CalculatorTool {

	/**
	 * Executing the calculator tool.
	 * 
	 * @param args The command line args provided to the tooling.
	 */
	public static void main(String[] args) {
		
		String value1 = "9876543210987654321098765432109876543210"; 
		String value2 = "1234567891234567891234567891234567891234"; 
		
		Calculator calculator = new Calculator();
		String gcd = calculator.gcd(value1, value2);
		
		System.out.println(gcd);
	}
}
