/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.compare.diff.metamodel.impl.DiffGroupImpl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.ImportDeclarationChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage;
import org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Compilation Unit Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.CompilationUnitChangeImpl#getClassChanges <em>Class Changes</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.CompilationUnitChangeImpl#getImportDeclarationChanges <em>Import Declaration Changes</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.CompilationUnitChangeImpl#getPackageChange <em>Package Change</em>}</li>
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
	public PackageChange getPackageChange() {
		if (eContainerFeatureID() != KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__PACKAGE_CHANGE) return null;
		return (PackageChange)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPackageChange(PackageChange newPackageChange, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPackageChange, KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__PACKAGE_CHANGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackageChange(PackageChange newPackageChange) {
		if (newPackageChange != eInternalContainer() || (eContainerFeatureID() != KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__PACKAGE_CHANGE && newPackageChange != null)) {
			if (EcoreUtil.isAncestor(this, newPackageChange))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPackageChange != null)
				msgs = ((InternalEObject)newPackageChange).eInverseAdd(this, KDM2JavaDiffPackage.PACKAGE_CHANGE__COMPILATION_UNIT_CHANGES, PackageChange.class, msgs);
			msgs = basicSetPackageChange(newPackageChange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__PACKAGE_CHANGE, newPackageChange, newPackageChange));
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
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__PACKAGE_CHANGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPackageChange((PackageChange)otherEnd, msgs);
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
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__PACKAGE_CHANGE:
				return basicSetPackageChange(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__PACKAGE_CHANGE:
				return eInternalContainer().eInverseRemove(this, KDM2JavaDiffPackage.PACKAGE_CHANGE__COMPILATION_UNIT_CHANGES, PackageChange.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
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
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__PACKAGE_CHANGE:
				return getPackageChange();
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
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__PACKAGE_CHANGE:
				setPackageChange((PackageChange)newValue);
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
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__PACKAGE_CHANGE:
				setPackageChange((PackageChange)null);
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
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__PACKAGE_CHANGE:
				return getPackageChange() != null;
		}
		return super.eIsSet(featureID);
	}

} //CompilationUnitChangeImpl
