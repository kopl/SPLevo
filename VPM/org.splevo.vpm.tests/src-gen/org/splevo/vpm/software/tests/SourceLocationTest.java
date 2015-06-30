/**
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Benjamin Klatt
 */
package org.splevo.vpm.software.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.splevo.vpm.software.SoftwareFactory;
import org.splevo.vpm.software.SourceLocation;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Source Location</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class SourceLocationTest extends TestCase {

	/**
	 * The fixture for this Source Location test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SourceLocation fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(SourceLocationTest.class);
	}

	/**
	 * Constructs a new Source Location test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SourceLocationTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Source Location test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(SourceLocation fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Source Location test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SourceLocation getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(SoftwareFactory.eINSTANCE.createSourceLocation());
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

} //SourceLocationTest
