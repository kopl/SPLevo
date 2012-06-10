/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>kdm2javadiff</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class KDM2JavaDiffTests extends TestSuite {

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
		TestSuite suite = new KDM2JavaDiffTests("kdm2javadiff Tests");
		suite.addTestSuite(StatementOrderChangeTest.class);
		suite.addTestSuite(StatementInsertTest.class);
		suite.addTestSuite(StatementDeleteTest.class);
		suite.addTestSuite(StatementMoveTest.class);
		suite.addTestSuite(ClassInsertTest.class);
		suite.addTestSuite(ClassDeleteTest.class);
		suite.addTestSuite(ClassModifierChangeTest.class);
		suite.addTestSuite(ImportInsertTest.class);
		suite.addTestSuite(ImportDeleteTest.class);
		suite.addTestSuite(ClassChangeTest.class);
		suite.addTestSuite(MethodChangeTest.class);
		suite.addTestSuite(MethodModifierChangeTest.class);
		suite.addTestSuite(ReturnTypeChangeTest.class);
		suite.addTestSuite(MethodParameterChangeTest.class);
		suite.addTestSuite(MethodInsertTest.class);
		suite.addTestSuite(MethodDeleteTest.class);
		suite.addTestSuite(CompilationUnitChangeTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KDM2JavaDiffTests(String name) {
		super(name);
	}

} //KDM2JavaDiffTests
