/**
 */
package org.splevo.jamopp.vpm.software.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test suite for the '<em><b>JaMoPPVPM</b></em>'
 * model. <!-- end-user-doc -->
 * 
 * @generated
 */
public class JaMoPPVPMAllTests extends TestSuite {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new JaMoPPVPMAllTests("JaMoPPVPM Tests");
		suite.addTest(softwareTests.suite());
		return suite;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public JaMoPPVPMAllTests(String name) {
		super(name);
	}

} // JaMoPPVPMAllTests
