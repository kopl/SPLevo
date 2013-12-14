/**
 */
package org.splevo.jamopp.diffing.jamoppdiff.tests;

import junit.textui.TestRunner;

import org.splevo.jamopp.diffing.jamoppdiff.FieldChange;
import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Field Change</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class FieldChangeTest extends JaMoPPDiffTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(FieldChangeTest.class);
	}

	/**
	 * Constructs a new Field Change test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldChangeTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Field Change test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected FieldChange getFixture() {
		return (FieldChange) fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(JaMoPPDiffFactory.eINSTANCE.createFieldChange());
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

} //FieldChangeTest
