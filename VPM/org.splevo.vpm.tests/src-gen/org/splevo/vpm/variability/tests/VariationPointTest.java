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

import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.variabilityFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Variation Point</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link org.splevo.vpm.variability.VariationPoint#allValidatorsSucceed(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>All Validators Succeed</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class VariationPointTest extends IdentifierTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(VariationPointTest.class);
	}

	/**
	 * Constructs a new Variation Point test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariationPointTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Variation Point test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected VariationPoint getFixture() {
		return (VariationPoint) fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(variabilityFactory.eINSTANCE.createVariationPoint());
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
	 * Tests the '{@link org.splevo.vpm.variability.VariationPoint#allValidatorsSucceed(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>All Validators Succeed</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.splevo.vpm.variability.VariationPoint#allValidatorsSucceed(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	public void testAllValidatorsSucceed__DiagnosticChain_Map() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

} //VariationPointTest
