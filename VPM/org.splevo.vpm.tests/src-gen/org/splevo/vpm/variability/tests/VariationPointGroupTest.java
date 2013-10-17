/**
 */
package org.splevo.vpm.variability.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.variabilityFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Variation Point Group</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class VariationPointGroupTest extends TestCase {

    /**
     * The fixture for this Variation Point Group test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected VariationPointGroup fixture = null;

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
     * Sets the fixture for this Variation Point Group test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setFixture(VariationPointGroup fixture) {
        this.fixture = fixture;
    }

    /**
     * Returns the fixture for this Variation Point Group test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected VariationPointGroup getFixture() {
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

} //VariationPointGroupTest
