/**
 */
package org.splevo.jamopp.diffing.jamoppdiff.tests;

import junit.textui.TestRunner;

import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffFactory;
import org.splevo.jamopp.diffing.jamoppdiff.PackageChange;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Package Change</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class PackageChangeTest extends JaMoPPDiffTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(PackageChangeTest.class);
	}

	/**
	 * Constructs a new Package Change test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageChangeTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Package Change test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected PackageChange getFixture() {
		return (PackageChange) fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(JaMoPPDiffFactory.eINSTANCE.createPackageChange());
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

} //PackageChangeTest
