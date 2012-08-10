/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>java2kdmdiff</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class Java2KDMDiffTests extends TestSuite {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new Java2KDMDiffTests("java2kdmdiff Tests");
		suite.addTestSuite(StatementChangeTest.class);
		suite.addTestSuite(ImportInsertTest.class);
		suite.addTestSuite(ImportDeleteTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Java2KDMDiffTests(String name) {
		super(name);
	}

} //Java2KDMDiffTests
