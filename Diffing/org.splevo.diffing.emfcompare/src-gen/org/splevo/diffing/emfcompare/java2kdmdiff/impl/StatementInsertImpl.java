/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff.impl;

import org.eclipse.emf.compare.diff.metamodel.DifferenceKind;
import org.eclipse.emf.ecore.EClass;

import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffPackage;
import org.splevo.diffing.emfcompare.java2kdmdiff.StatementInsert;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Statement Insert</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class StatementInsertImpl extends StatementChangeImpl implements StatementInsert {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected StatementInsertImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Java2KDMDiffPackage.Literals.STATEMENT_INSERT;
    }

    /**
     * <!-- begin-user-doc -->
     * The difference kind of a statement insert is always DifferenceKind.ADDITION.
     * <!-- end-user-doc -->
     * {@inheritDoc}
     * @generated NOT
     */
    @Override
    public DifferenceKind getKind() {
        return DifferenceKind.ADDITION;
    }

} //StatementInsertImpl
