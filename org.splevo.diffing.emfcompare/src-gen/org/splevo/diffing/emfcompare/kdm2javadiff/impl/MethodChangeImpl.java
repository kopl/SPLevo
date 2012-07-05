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
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage;
import org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.MethodDeclarationChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.StatementChange;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodChangeImpl#getMethodDeclarationChanges <em>Method Declaration Changes</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodChangeImpl#getStatementChanges <em>Statement Changes</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.MethodChangeImpl#getClassChange <em>Class Change</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodChangeImpl extends DiffGroupImpl implements MethodChange {
	/**
	 * The cached value of the '{@link #getMethodDeclarationChanges() <em>Method Declaration Changes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodDeclarationChanges()
	 * @generated
	 * @ordered
	 */
	protected EList<MethodDeclarationChange> methodDeclarationChanges;

	/**
	 * The cached value of the '{@link #getStatementChanges() <em>Statement Changes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatementChanges()
	 * @generated
	 * @ordered
	 */
	protected EList<StatementChange> statementChanges;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MethodChangeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KDM2JavaDiffPackage.Literals.METHOD_CHANGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MethodDeclarationChange> getMethodDeclarationChanges() {
		if (methodDeclarationChanges == null) {
			methodDeclarationChanges = new EObjectContainmentWithInverseEList<MethodDeclarationChange>(MethodDeclarationChange.class, this, KDM2JavaDiffPackage.METHOD_CHANGE__METHOD_DECLARATION_CHANGES, KDM2JavaDiffPackage.METHOD_DECLARATION_CHANGE__METHOD_CHANGE);
		}
		return methodDeclarationChanges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StatementChange> getStatementChanges() {
		if (statementChanges == null) {
			statementChanges = new EObjectContainmentWithInverseEList<StatementChange>(StatementChange.class, this, KDM2JavaDiffPackage.METHOD_CHANGE__STATEMENT_CHANGES, KDM2JavaDiffPackage.STATEMENT_CHANGE__METHOD_CHANGE);
		}
		return statementChanges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassChange getClassChange() {
		if (eContainerFeatureID() != KDM2JavaDiffPackage.METHOD_CHANGE__CLASS_CHANGE) return null;
		return (ClassChange)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetClassChange(ClassChange newClassChange, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newClassChange, KDM2JavaDiffPackage.METHOD_CHANGE__CLASS_CHANGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassChange(ClassChange newClassChange) {
		if (newClassChange != eInternalContainer() || (eContainerFeatureID() != KDM2JavaDiffPackage.METHOD_CHANGE__CLASS_CHANGE && newClassChange != null)) {
			if (EcoreUtil.isAncestor(this, newClassChange))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newClassChange != null)
				msgs = ((InternalEObject)newClassChange).eInverseAdd(this, KDM2JavaDiffPackage.CLASS_CHANGE__METHOD_CHANGES, ClassChange.class, msgs);
			msgs = basicSetClassChange(newClassChange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KDM2JavaDiffPackage.METHOD_CHANGE__CLASS_CHANGE, newClassChange, newClassChange));
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
			case KDM2JavaDiffPackage.METHOD_CHANGE__METHOD_DECLARATION_CHANGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMethodDeclarationChanges()).basicAdd(otherEnd, msgs);
			case KDM2JavaDiffPackage.METHOD_CHANGE__STATEMENT_CHANGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getStatementChanges()).basicAdd(otherEnd, msgs);
			case KDM2JavaDiffPackage.METHOD_CHANGE__CLASS_CHANGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetClassChange((ClassChange)otherEnd, msgs);
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
			case KDM2JavaDiffPackage.METHOD_CHANGE__METHOD_DECLARATION_CHANGES:
				return ((InternalEList<?>)getMethodDeclarationChanges()).basicRemove(otherEnd, msgs);
			case KDM2JavaDiffPackage.METHOD_CHANGE__STATEMENT_CHANGES:
				return ((InternalEList<?>)getStatementChanges()).basicRemove(otherEnd, msgs);
			case KDM2JavaDiffPackage.METHOD_CHANGE__CLASS_CHANGE:
				return basicSetClassChange(null, msgs);
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
			case KDM2JavaDiffPackage.METHOD_CHANGE__CLASS_CHANGE:
				return eInternalContainer().eInverseRemove(this, KDM2JavaDiffPackage.CLASS_CHANGE__METHOD_CHANGES, ClassChange.class, msgs);
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
			case KDM2JavaDiffPackage.METHOD_CHANGE__METHOD_DECLARATION_CHANGES:
				return getMethodDeclarationChanges();
			case KDM2JavaDiffPackage.METHOD_CHANGE__STATEMENT_CHANGES:
				return getStatementChanges();
			case KDM2JavaDiffPackage.METHOD_CHANGE__CLASS_CHANGE:
				return getClassChange();
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
			case KDM2JavaDiffPackage.METHOD_CHANGE__METHOD_DECLARATION_CHANGES:
				getMethodDeclarationChanges().clear();
				getMethodDeclarationChanges().addAll((Collection<? extends MethodDeclarationChange>)newValue);
				return;
			case KDM2JavaDiffPackage.METHOD_CHANGE__STATEMENT_CHANGES:
				getStatementChanges().clear();
				getStatementChanges().addAll((Collection<? extends StatementChange>)newValue);
				return;
			case KDM2JavaDiffPackage.METHOD_CHANGE__CLASS_CHANGE:
				setClassChange((ClassChange)newValue);
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
			case KDM2JavaDiffPackage.METHOD_CHANGE__METHOD_DECLARATION_CHANGES:
				getMethodDeclarationChanges().clear();
				return;
			case KDM2JavaDiffPackage.METHOD_CHANGE__STATEMENT_CHANGES:
				getStatementChanges().clear();
				return;
			case KDM2JavaDiffPackage.METHOD_CHANGE__CLASS_CHANGE:
				setClassChange((ClassChange)null);
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
			case KDM2JavaDiffPackage.METHOD_CHANGE__METHOD_DECLARATION_CHANGES:
				return methodDeclarationChanges != null && !methodDeclarationChanges.isEmpty();
			case KDM2JavaDiffPackage.METHOD_CHANGE__STATEMENT_CHANGES:
				return statementChanges != null && !statementChanges.isEmpty();
			case KDM2JavaDiffPackage.METHOD_CHANGE__CLASS_CHANGE:
				return getClassChange() != null;
		}
		return super.eIsSet(featureID);
	}

} //MethodChangeImpl
