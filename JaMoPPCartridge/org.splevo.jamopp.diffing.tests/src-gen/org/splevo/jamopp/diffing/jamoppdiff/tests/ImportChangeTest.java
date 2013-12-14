/**
 */
package org.splevo.jamopp.diffing.jamoppdiff.tests;

import junit.textui.TestRunner;

import org.splevo.jamopp.diffing.jamoppdiff.ImportChange;
import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Import Change</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ImportChangeTest extends JaMoPPDiffTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ImportChangeTest.class);
	}

	/**
	 * Constructs a new Import Change test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportChangeTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Import Change test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ImportChange getFixture() {
		return (ImportChange) fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(JaMoPPDiffFactory.eINSTANCE.createImportChange());
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

} //ImportChangeTest
