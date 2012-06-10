/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff.tests;

import junit.textui.TestRunner;

import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffFactory;
import org.splevo.diffing.emfcompare.kdm2javadiff.StatementDelete;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Statement Delete</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class StatementDeleteTest extends KDM2JavaDiffExtensionTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(StatementDeleteTest.class);
	}

	/**
	 * Constructs a new Statement Delete test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatementDeleteTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Statement Delete test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected StatementDelete getFixture() {
		return (StatementDelete)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(KDM2JavaDiffFactory.eINSTANCE.createStatementDelete());
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

} //StatementDeleteTest
