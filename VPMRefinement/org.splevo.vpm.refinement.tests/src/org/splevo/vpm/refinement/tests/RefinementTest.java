/**
 */
package org.splevo.vpm.refinement.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Refinement</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class RefinementTest extends TestCase {

    /**
     * The fixture for this Refinement test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Refinement fixture = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args) {
        TestRunner.run(RefinementTest.class);
    }

    /**
     * Constructs a new Refinement test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RefinementTest(String name) {
        super(name);
    }

    /**
     * Sets the fixture for this Refinement test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setFixture(Refinement fixture) {
        this.fixture = fixture;
    }

    /**
     * Returns the fixture for this Refinement test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Refinement getFixture() {
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
        setFixture(RefinementFactory.eINSTANCE.createRefinement());
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

} //RefinementTest
