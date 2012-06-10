/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff.tests;

import junit.textui.TestRunner;

import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffFactory;
import org.splevo.diffing.emfcompare.kdm2javadiff.StatementOrderChange;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Statement Order Change</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class StatementOrderChangeTest extends KDM2JavaDiffExtensionTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(StatementOrderChangeTest.class);
	}

	/**
	 * Constructs a new Statement Order Change test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatementOrderChangeTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Statement Order Change test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected StatementOrderChange getFixture() {
		return (StatementOrderChange)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(KDM2JavaDiffFactory.eINSTANCE.createStatementOrderChange());
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

} //StatementOrderChangeTest
