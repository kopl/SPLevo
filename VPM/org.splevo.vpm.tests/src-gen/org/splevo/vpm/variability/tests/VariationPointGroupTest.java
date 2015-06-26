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

import junit.textui.TestRunner;

import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.variabilityFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Variation Point Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link org.splevo.vpm.variability.VariationPointGroup#isRefactored() <em>Is Refactored</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class VariationPointGroupTest extends CustomizableNameHavingTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(VariationPointGroupTest.class);
	}

	/**
	 * Constructs a new Variation Point Group test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariationPointGroupTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Variation Point Group test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected VariationPointGroup getFixture() {
		return (VariationPointGroup) fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(variabilityFactory.eINSTANCE.createVariationPointGroup());
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

	/**
	 * Tests the '{@link org.splevo.vpm.variability.VariationPointGroup#isRefactored() <em>Is Refactored</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.vpm.variability.VariationPointGroup#isRefactored()
	 * @generated
	 */
	public void testIsRefactored() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

} //VariationPointGroupTest
