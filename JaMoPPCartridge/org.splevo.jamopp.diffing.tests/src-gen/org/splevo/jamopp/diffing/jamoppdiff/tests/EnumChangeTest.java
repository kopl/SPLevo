/**
 */
package org.splevo.jamopp.diffing.jamoppdiff.tests;

import junit.textui.TestRunner;

import org.splevo.jamopp.diffing.jamoppdiff.EnumChange;
import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Enum Change</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class EnumChangeTest extends JaMoPPDiffTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(EnumChangeTest.class);
	}

	/**
	 * Constructs a new Enum Change test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumChangeTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Enum Change test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EnumChange getFixture() {
		return (EnumChange) fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(JaMoPPDiffFactory.eINSTANCE.createEnumChange());
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

} //EnumChangeTest
