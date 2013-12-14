/**
 */
package org.splevo.jamopp.diffing.jamoppdiff.tests;

import junit.textui.TestRunner;

import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffFactory;
import org.splevo.jamopp.diffing.jamoppdiff.StatementChange;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Statement Change</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class StatementChangeTest extends JaMoPPDiffTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(StatementChangeTest.class);
	}

	/**
	 * Constructs a new Statement Change test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatementChangeTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Statement Change test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected StatementChange getFixture() {
		return (StatementChange) fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(JaMoPPDiffFactory.eINSTANCE.createStatementChange());
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

} //StatementChangeTest
