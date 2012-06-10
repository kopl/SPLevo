/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff.tests;

import junit.textui.TestRunner;

import org.splevo.diffing.emfcompare.kdm2javadiff.ImportDelete;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Import Delete</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ImportDeleteTest extends KDM2JavaDiffExtensionTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ImportDeleteTest.class);
	}

	/**
	 * Constructs a new Import Delete test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportDeleteTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Import Delete test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ImportDelete getFixture() {
		return (ImportDelete)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(KDM2JavaDiffFactory.eINSTANCE.createImportDelete());
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

} //ImportDeleteTest
