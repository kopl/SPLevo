/**
 */
package org.splevo.vpm.variability.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Variation Point Model</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class VariationPointModelTest extends TestCase {

    /**
     * The fixture for this Variation Point Model test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected VariationPointModel fixture = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args) {
        TestRunner.run(VariationPointModelTest.class);
    }

    /**
     * Constructs a new Variation Point Model test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VariationPointModelTest(String name) {
        super(name);
    }

    /**
     * Sets the fixture for this Variation Point Model test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setFixture(VariationPointModel fixture) {
        this.fixture = fixture;
    }

    /**
     * Returns the fixture for this Variation Point Model test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected VariationPointModel getFixture() {
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
        setFixture(variabilityFactory.eINSTANCE.createVariationPointModel());
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

} //VariationPointModelTest
