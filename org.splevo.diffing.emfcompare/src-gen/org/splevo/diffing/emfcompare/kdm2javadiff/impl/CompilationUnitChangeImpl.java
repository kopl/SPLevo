/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.compare.diff.metamodel.impl.DiffGroupImpl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Compilation Unit Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.CompilationUnitChangeImpl#getClassChanges <em>Class Changes</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.CompilationUnitChangeImpl#getImportDeclarationChanges <em>Import Declaration Changes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CompilationUnitChangeImpl extends DiffGroupImpl implements CompilationUnitChange {
	/**
	 * The cached value of the '{@link #getClassChanges() <em>Class Changes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassChanges()
	 * @generated
	 * @ordered
	 */
	protected EList<ClassChange> classChanges;

	/**
	 * The cached value of the '{@link #getImportDeclarationChanges() <em>Import Declaration Changes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportDeclarationChanges()
	 * @generated
	 * @ordered
	 */
	protected EList<ImportDeclarationChange> importDeclarationChanges;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompilationUnitChangeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KDM2JavaDiffPackage.Literals.COMPILATION_UNIT_CHANGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassChange> getClassChanges() {
		if (classChanges == null) {
			classChanges = new EObjectContainmentWithInverseEList<ClassChange>(ClassChange.class, this, KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__CLASS_CHANGES, KDM2JavaDiffPackage.CLASS_CHANGE__COMPILATION_UNIT_CHANGE);
		}
		return classChanges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ImportDeclarationChange> getImportDeclarationChanges() {
		if (importDeclarationChanges == null) {
			importDeclarationChanges = new EObjectContainmentWithInverseEList<ImportDeclarationChange>(ImportDeclarationChange.class, this, KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__IMPORT_DECLARATION_CHANGES, KDM2JavaDiffPackage.IMPORT_DECLARATION_CHANGE__COMPILATION_UNIT_CHANGE);
		}
		return importDeclarationChanges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__CLASS_CHANGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getClassChanges()).basicAdd(otherEnd, msgs);
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__IMPORT_DECLARATION_CHANGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getImportDeclarationChanges()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__CLASS_CHANGES:
				return ((InternalEList<?>)getClassChanges()).basicRemove(otherEnd, msgs);
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__IMPORT_DECLARATION_CHANGES:
				return ((InternalEList<?>)getImportDeclarationChanges()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__CLASS_CHANGES:
				return getClassChanges();
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__IMPORT_DECLARATION_CHANGES:
				return getImportDeclarationChanges();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__CLASS_CHANGES:
				getClassChanges().clear();
				getClassChanges().addAll((Collection<? extends ClassChange>)newValue);
				return;
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__IMPORT_DECLARATION_CHANGES:
				getImportDeclarationChanges().clear();
				getImportDeclarationChanges().addAll((Collection<? extends ImportDeclarationChange>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__CLASS_CHANGES:
				getClassChanges().clear();
				return;
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__IMPORT_DECLARATION_CHANGES:
				getImportDeclarationChanges().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__CLASS_CHANGES:
				return classChanges != null && !classChanges.isEmpty();
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__IMPORT_DECLARATION_CHANGES:
				return importDeclarationChanges != null && !importDeclarationChanges.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CompilationUnitChangeImpl
