/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff.tests;

import junit.textui.TestRunner;

import org.splevo.diffing.emfcompare.kdm2javadiff.ClassInsert;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Class Insert</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ClassInsertTest extends KDM2JavaDiffExtensionTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ClassInsertTest.class);
	}

	/**
	 * Constructs a new Class Insert test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassInsertTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Class Insert test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ClassInsert getFixture() {
		return (ClassInsert)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(KDM2JavaDiffFactory.eINSTANCE.createClassInsert());
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

} //ClassInsertTest
