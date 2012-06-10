/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff.tests;

import junit.textui.TestRunner;

import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffFactory;
import org.splevo.diffing.emfcompare.kdm2javadiff.StatementInsert;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Statement Insert</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class StatementInsertTest extends KDM2JavaDiffExtensionTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(StatementInsertTest.class);
	}

	/**
	 * Constructs a new Statement Insert test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatementInsertTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Statement Insert test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected StatementInsert getFixture() {
		return (StatementInsert)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(KDM2JavaDiffFactory.eINSTANCE.createStatementInsert());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //StatementInsertTest
