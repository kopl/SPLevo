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
package org.splevo.vpm.realization.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Variability Mechanism</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class VariabilityMechanismTest extends TestCase {

	/**
	 * The fixture for this Variability Mechanism test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariabilityMechanism fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(VariabilityMechanismTest.class);
	}

	/**
	 * Constructs a new Variability Mechanism test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariabilityMechanismTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Variability Mechanism test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(VariabilityMechanism fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Variability Mechanism test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariabilityMechanism getFixture() {
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
		setFixture(RealizationFactory.eINSTANCE.createVariabilityMechanism());
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

} //VariabilityMechanismTest
