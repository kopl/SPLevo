/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff.tests;

import junit.textui.TestRunner;

import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffFactory;
import org.splevo.diffing.emfcompare.kdm2javadiff.MethodDelete;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Method Delete</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class MethodDeleteTest extends KDM2JavaDiffExtensionTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(MethodDeleteTest.class);
	}

	/**
	 * Constructs a new Method Delete test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodDeleteTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Method Delete test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected MethodDelete getFixture() {
		return (MethodDelete)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(KDM2JavaDiffFactory.eINSTANCE.createMethodDelete());
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

} //MethodDeleteTest
