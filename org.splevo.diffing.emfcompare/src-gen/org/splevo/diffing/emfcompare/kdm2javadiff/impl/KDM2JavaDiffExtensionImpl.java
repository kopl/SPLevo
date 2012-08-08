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

import org.eclipse.emf.compare.diff.metamodel.AbstractDiffExtension;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffPackage;
import org.eclipse.emf.compare.diff.metamodel.DifferenceKind;

import org.eclipse.emf.compare.diff.metamodel.impl.AbstractDiffExtensionImpl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffExtension;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extension</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffExtensionImpl#getSubDiffElements <em>Sub Diff Elements</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffExtensionImpl#getIsHiddenBy <em>Is Hidden By</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffExtensionImpl#isConflicting <em>Conflicting</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffExtensionImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffExtensionImpl#isRemote <em>Remote</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffExtensionImpl#getRequires <em>Requires</em>}</li>
 *   <li>{@link org.splevo.diffing.emfcompare.kdm2javadiff.impl.KDM2JavaDiffExtensionImpl#getRequiredBy <em>Required By</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class KDM2JavaDiffExtensionImpl extends AbstractDiffExtensionImpl implements KDM2JavaDiffExtension {
	/**
	 * The cached value of the '{@link #getSubDiffElements() <em>Sub Diff Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubDiffElements()
	 * @generated
	 * @ordered
	 */
	protected EList<DiffElement> subDiffElements;

	/**
	 * The cached value of the '{@link #getIsHiddenBy() <em>Is Hidden By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsHiddenBy()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractDiffExtension> isHiddenBy;

	/**
	 * The default value of the '{@link #isConflicting() <em>Conflicting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConflicting()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONFLICTING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isConflicting() <em>Conflicting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConflicting()
	 * @generated
	 * @ordered
	 */
	protected boolean conflicting = CONFLICTING_EDEFAULT;

	/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final DifferenceKind KIND_EDEFAULT = DifferenceKind.ADDITION;

	/**
	 * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected DifferenceKind kind = KIND_EDEFAULT;

	/**
	 * The default value of the '{@link #isRemote() <em>Remote</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRemote()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REMOTE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRemote() <em>Remote</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRemote()
	 * @generated
	 * @ordered
	 */
	protected boolean remote = REMOTE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRequires() <em>Requires</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequires()
	 * @generated
	 * @ordered
	 */
	protected EList<DiffElement> requires;

	/**
	 * The cached value of the '{@link #getRequiredBy() <em>Required By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredBy()
	 * @generated
	 * @ordered
	 */
	protected EList<DiffElement> requiredBy;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected KDM2JavaDiffExtensionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KDM2JavaDiffPackage.Literals.KDM2_JAVA_DIFF_EXTENSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DiffElement> getSubDiffElements() {
		if (subDiffElements == null) {
			subDiffElements = new EObjectContainmentEList<DiffElement>(DiffElement.class, this, KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__SUB_DIFF_ELEMENTS);
		}
		return subDiffElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractDiffExtension> getIsHiddenBy() {
		if (isHiddenBy == null) {
			isHiddenBy = new EObjectWithInverseResolvingEList.ManyInverse<AbstractDiffExtension>(AbstractDiffExtension.class, this, KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__IS_HIDDEN_BY, DiffPackage.ABSTRACT_DIFF_EXTENSION__HIDE_ELEMENTS);
		}
		return isHiddenBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isConflicting() {
		return conflicting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DifferenceKind getKind() {
		return kind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRemote() {
		return remote;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemote(boolean newRemote) {
		boolean oldRemote = remote;
		remote = newRemote;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REMOTE, oldRemote, remote));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DiffElement> getRequires() {
		if (requires == null) {
			requires = new EObjectWithInverseResolvingEList.ManyInverse<DiffElement>(DiffElement.class, this, KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REQUIRES, DiffPackage.DIFF_ELEMENT__REQUIRED_BY);
		}
		return requires;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DiffElement> getRequiredBy() {
		if (requiredBy == null) {
			requiredBy = new EObjectWithInverseResolvingEList.ManyInverse<DiffElement>(DiffElement.class, this, KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REQUIRED_BY, DiffPackage.DIFF_ELEMENT__REQUIRES);
		}
		return requiredBy;
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
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__IS_HIDDEN_BY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIsHiddenBy()).basicAdd(otherEnd, msgs);
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REQUIRES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRequires()).basicAdd(otherEnd, msgs);
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REQUIRED_BY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRequiredBy()).basicAdd(otherEnd, msgs);
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
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__SUB_DIFF_ELEMENTS:
				return ((InternalEList<?>)getSubDiffElements()).basicRemove(otherEnd, msgs);
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__IS_HIDDEN_BY:
				return ((InternalEList<?>)getIsHiddenBy()).basicRemove(otherEnd, msgs);
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REQUIRES:
				return ((InternalEList<?>)getRequires()).basicRemove(otherEnd, msgs);
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REQUIRED_BY:
				return ((InternalEList<?>)getRequiredBy()).basicRemove(otherEnd, msgs);
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
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__SUB_DIFF_ELEMENTS:
				return getSubDiffElements();
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__IS_HIDDEN_BY:
				return getIsHiddenBy();
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__CONFLICTING:
				return isConflicting();
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__KIND:
				return getKind();
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REMOTE:
				return isRemote();
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REQUIRES:
				return getRequires();
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REQUIRED_BY:
				return getRequiredBy();
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
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__SUB_DIFF_ELEMENTS:
				getSubDiffElements().clear();
				getSubDiffElements().addAll((Collection<? extends DiffElement>)newValue);
				return;
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__IS_HIDDEN_BY:
				getIsHiddenBy().clear();
				getIsHiddenBy().addAll((Collection<? extends AbstractDiffExtension>)newValue);
				return;
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REMOTE:
				setRemote((Boolean)newValue);
				return;
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REQUIRES:
				getRequires().clear();
				getRequires().addAll((Collection<? extends DiffElement>)newValue);
				return;
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REQUIRED_BY:
				getRequiredBy().clear();
				getRequiredBy().addAll((Collection<? extends DiffElement>)newValue);
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
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__SUB_DIFF_ELEMENTS:
				getSubDiffElements().clear();
				return;
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__IS_HIDDEN_BY:
				getIsHiddenBy().clear();
				return;
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REMOTE:
				setRemote(REMOTE_EDEFAULT);
				return;
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REQUIRES:
				getRequires().clear();
				return;
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REQUIRED_BY:
				getRequiredBy().clear();
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
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__SUB_DIFF_ELEMENTS:
				return subDiffElements != null && !subDiffElements.isEmpty();
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__IS_HIDDEN_BY:
				return isHiddenBy != null && !isHiddenBy.isEmpty();
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__CONFLICTING:
				return conflicting != CONFLICTING_EDEFAULT;
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__KIND:
				return kind != KIND_EDEFAULT;
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REMOTE:
				return remote != REMOTE_EDEFAULT;
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REQUIRES:
				return requires != null && !requires.isEmpty();
			case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REQUIRED_BY:
				return requiredBy != null && !requiredBy.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == DiffElement.class) {
			switch (derivedFeatureID) {
				case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__SUB_DIFF_ELEMENTS: return DiffPackage.DIFF_ELEMENT__SUB_DIFF_ELEMENTS;
				case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__IS_HIDDEN_BY: return DiffPackage.DIFF_ELEMENT__IS_HIDDEN_BY;
				case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__CONFLICTING: return DiffPackage.DIFF_ELEMENT__CONFLICTING;
				case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__KIND: return DiffPackage.DIFF_ELEMENT__KIND;
				case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REMOTE: return DiffPackage.DIFF_ELEMENT__REMOTE;
				case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REQUIRES: return DiffPackage.DIFF_ELEMENT__REQUIRES;
				case KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REQUIRED_BY: return DiffPackage.DIFF_ELEMENT__REQUIRED_BY;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == DiffElement.class) {
			switch (baseFeatureID) {
				case DiffPackage.DIFF_ELEMENT__SUB_DIFF_ELEMENTS: return KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__SUB_DIFF_ELEMENTS;
				case DiffPackage.DIFF_ELEMENT__IS_HIDDEN_BY: return KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__IS_HIDDEN_BY;
				case DiffPackage.DIFF_ELEMENT__CONFLICTING: return KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__CONFLICTING;
				case DiffPackage.DIFF_ELEMENT__KIND: return KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__KIND;
				case DiffPackage.DIFF_ELEMENT__REMOTE: return KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REMOTE;
				case DiffPackage.DIFF_ELEMENT__REQUIRES: return KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REQUIRES;
				case DiffPackage.DIFF_ELEMENT__REQUIRED_BY: return KDM2JavaDiffPackage.KDM2_JAVA_DIFF_EXTENSION__REQUIRED_BY;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (conflicting: ");
		result.append(conflicting);
		result.append(", kind: ");
		result.append(kind);
		result.append(", remote: ");
		result.append(remote);
		result.append(')');
		return result.toString();
	}

} //KDM2JavaDiffExtensionImpl