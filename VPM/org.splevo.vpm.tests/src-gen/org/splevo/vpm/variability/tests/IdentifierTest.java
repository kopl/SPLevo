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
package org.splevo.vpm.variability.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.splevo.vpm.variability.Identifier;
import org.splevo.vpm.variability.variabilityFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Identifier</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class IdentifierTest extends TestCase {

	/**
	 * The fixture for this Identifier test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Identifier fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(IdentifierTest.class);
	}

	/**
	 * Constructs a new Identifier test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdentifierTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Identifier test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Identifier fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Identifier test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Identifier getFixture() {
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
		setFixture(variabilityFactory.eINSTANCE.createIdentifier());
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

} //IdentifierTest
