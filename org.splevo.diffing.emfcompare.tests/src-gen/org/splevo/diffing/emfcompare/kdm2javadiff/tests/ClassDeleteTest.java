/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff.tests;

import junit.textui.TestRunner;

import org.splevo.diffing.emfcompare.kdm2javadiff.ClassDelete;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Class Delete</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ClassDeleteTest extends KDM2JavaDiffExtensionTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ClassDeleteTest.class);
	}

	/**
	 * Constructs a new Class Delete test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassDeleteTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Class Delete test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ClassDelete getFixture() {
		return (ClassDelete)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(KDM2JavaDiffFactory.eINSTANCE.createClassDelete());
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

} //ClassDeleteTest
