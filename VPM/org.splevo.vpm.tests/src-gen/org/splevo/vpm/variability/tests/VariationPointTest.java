/**
 */
package org.splevo.vpm.variability.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.variabilityFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Variation Point</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class VariationPointTest extends TestCase {

	/**
     * The fixture for this Variation Point test case.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected VariationPoint fixture = null;

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
     * Sets the fixture for this Variation Point test case.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void setFixture(VariationPoint fixture) {
        this.fixture = fixture;
    }

	/**
     * Returns the fixture for this Variation Point test case.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected VariationPoint getFixture() {
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

} //VariationPointTest
