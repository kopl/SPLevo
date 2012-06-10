/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff.tests;

import junit.textui.TestRunner;

import org.splevo.diffing.emfcompare.kdm2javadiff.ImportInsert;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Import Insert</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ImportInsertTest extends KDM2JavaDiffExtensionTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ImportInsertTest.class);
	}

	/**
	 * Constructs a new Import Insert test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportInsertTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Import Insert test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ImportInsert getFixture() {
		return (ImportInsert)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(KDM2JavaDiffFactory.eINSTANCE.createImportInsert());
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

} //ImportInsertTest
