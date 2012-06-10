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
import org.splevo.diffing.emfcompare.kdm2javadiff.ClassDeclarationChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage;
import org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ClassChangeImpl#getClassDeclaractionChanges <em>Class Declaraction Changes</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ClassChangeImpl#getMethodChanges <em>Method Changes</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.ClassChangeImpl#getCompilationUnitChange <em>Compilation Unit Change</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassChangeImpl extends DiffGroupImpl implements ClassChange {
	/**
	 * The cached value of the '{@link #getClassDeclaractionChanges() <em>Class Declaraction Changes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassDeclaractionChanges()
	 * @generated
	 * @ordered
	 */
	protected EList<ClassDeclarationChange> classDeclaractionChanges;

	/**
	 * The cached value of the '{@link #getMethodChanges() <em>Method Changes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodChanges()
	 * @generated
	 * @ordered
	 */
	protected EList<MethodChange> methodChanges;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassChangeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KDM2JavaDiffPackage.Literals.CLASS_CHANGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassDeclarationChange> getClassDeclaractionChanges() {
		if (classDeclaractionChanges == null) {
			classDeclaractionChanges = new EObjectContainmentWithInverseEList<ClassDeclarationChange>(ClassDeclarationChange.class, this, KDM2JavaDiffPackage.CLASS_CHANGE__CLASS_DECLARACTION_CHANGES, KDM2JavaDiffPackage.CLASS_DECLARATION_CHANGE__CLASS_CHANGE);
		}
		return classDeclaractionChanges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MethodChange> getMethodChanges() {
		if (methodChanges == null) {
			methodChanges = new EObjectContainmentWithInverseEList<MethodChange>(MethodChange.class, this, KDM2JavaDiffPackage.CLASS_CHANGE__METHOD_CHANGES, KDM2JavaDiffPackage.METHOD_CHANGE__CLASS_CHANGE);
		}
		return methodChanges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompilationUnitChange getCompilationUnitChange() {
		if (eContainerFeatureID() != KDM2JavaDiffPackage.CLASS_CHANGE__COMPILATION_UNIT_CHANGE) return null;
		return (CompilationUnitChange)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCompilationUnitChange(CompilationUnitChange newCompilationUnitChange, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCompilationUnitChange, KDM2JavaDiffPackage.CLASS_CHANGE__COMPILATION_UNIT_CHANGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompilationUnitChange(CompilationUnitChange newCompilationUnitChange) {
		if (newCompilationUnitChange != eInternalContainer() || (eContainerFeatureID() != KDM2JavaDiffPackage.CLASS_CHANGE__COMPILATION_UNIT_CHANGE && newCompilationUnitChange != null)) {
			if (EcoreUtil.isAncestor(this, newCompilationUnitChange))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCompilationUnitChange != null)
				msgs = ((InternalEObject)newCompilationUnitChange).eInverseAdd(this, KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__CLASS_CHANGES, CompilationUnitChange.class, msgs);
			msgs = basicSetCompilationUnitChange(newCompilationUnitChange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KDM2JavaDiffPackage.CLASS_CHANGE__COMPILATION_UNIT_CHANGE, newCompilationUnitChange, newCompilationUnitChange));
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
			case KDM2JavaDiffPackage.CLASS_CHANGE__CLASS_DECLARACTION_CHANGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getClassDeclaractionChanges()).basicAdd(otherEnd, msgs);
			case KDM2JavaDiffPackage.CLASS_CHANGE__METHOD_CHANGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMethodChanges()).basicAdd(otherEnd, msgs);
			case KDM2JavaDiffPackage.CLASS_CHANGE__COMPILATION_UNIT_CHANGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCompilationUnitChange((CompilationUnitChange)otherEnd, msgs);
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
			case KDM2JavaDiffPackage.CLASS_CHANGE__CLASS_DECLARACTION_CHANGES:
				return ((InternalEList<?>)getClassDeclaractionChanges()).basicRemove(otherEnd, msgs);
			case KDM2JavaDiffPackage.CLASS_CHANGE__METHOD_CHANGES:
				return ((InternalEList<?>)getMethodChanges()).basicRemove(otherEnd, msgs);
			case KDM2JavaDiffPackage.CLASS_CHANGE__COMPILATION_UNIT_CHANGE:
				return basicSetCompilationUnitChange(null, msgs);
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
			case KDM2JavaDiffPackage.CLASS_CHANGE__COMPILATION_UNIT_CHANGE:
				return eInternalContainer().eInverseRemove(this, KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE__CLASS_CHANGES, CompilationUnitChange.class, msgs);
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
			case KDM2JavaDiffPackage.CLASS_CHANGE__CLASS_DECLARACTION_CHANGES:
				return getClassDeclaractionChanges();
			case KDM2JavaDiffPackage.CLASS_CHANGE__METHOD_CHANGES:
				return getMethodChanges();
			case KDM2JavaDiffPackage.CLASS_CHANGE__COMPILATION_UNIT_CHANGE:
				return getCompilationUnitChange();
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
			case KDM2JavaDiffPackage.CLASS_CHANGE__CLASS_DECLARACTION_CHANGES:
				getClassDeclaractionChanges().clear();
				getClassDeclaractionChanges().addAll((Collection<? extends ClassDeclarationChange>)newValue);
				return;
			case KDM2JavaDiffPackage.CLASS_CHANGE__METHOD_CHANGES:
				getMethodChanges().clear();
				getMethodChanges().addAll((Collection<? extends MethodChange>)newValue);
				return;
			case KDM2JavaDiffPackage.CLASS_CHANGE__COMPILATION_UNIT_CHANGE:
				setCompilationUnitChange((CompilationUnitChange)newValue);
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
			case KDM2JavaDiffPackage.CLASS_CHANGE__CLASS_DECLARACTION_CHANGES:
				getClassDeclaractionChanges().clear();
				return;
			case KDM2JavaDiffPackage.CLASS_CHANGE__METHOD_CHANGES:
				getMethodChanges().clear();
				return;
			case KDM2JavaDiffPackage.CLASS_CHANGE__COMPILATION_UNIT_CHANGE:
				setCompilationUnitChange((CompilationUnitChange)null);
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
			case KDM2JavaDiffPackage.CLASS_CHANGE__CLASS_DECLARACTION_CHANGES:
				return classDeclaractionChanges != null && !classDeclaractionChanges.isEmpty();
			case KDM2JavaDiffPackage.CLASS_CHANGE__METHOD_CHANGES:
				return methodChanges != null && !methodChanges.isEmpty();
			case KDM2JavaDiffPackage.CLASS_CHANGE__COMPILATION_UNIT_CHANGE:
				return getCompilationUnitChange() != null;
		}
		return super.eIsSet(featureID);
	}

} //ClassChangeImpl
