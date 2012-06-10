/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff.tests;

import junit.textui.TestRunner;

import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffFactory;
import org.splevo.diffing.emfcompare.kdm2javadiff.StatementMove;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Statement Move</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class StatementMoveTest extends KDM2JavaDiffExtensionTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(StatementMoveTest.class);
	}

	/**
	 * Constructs a new Statement Move test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatementMoveTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Statement Move test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected StatementMove getFixture() {
		return (StatementMove)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(KDM2JavaDiffFactory.eINSTANCE.createStatementMove());
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

} //StatementMoveTest
