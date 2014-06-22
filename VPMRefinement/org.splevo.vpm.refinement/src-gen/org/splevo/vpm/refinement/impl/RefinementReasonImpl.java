/**
 *  Copyright (c) 2014
 *  
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *      Benjamin Klatt
 */
package org.splevo.vpm.refinement.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementPackage;
import org.splevo.vpm.refinement.RefinementReason;
import org.splevo.vpm.variability.VariationPoint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reason</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.vpm.refinement.impl.RefinementReasonImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.splevo.vpm.refinement.impl.RefinementReasonImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.splevo.vpm.refinement.impl.RefinementReasonImpl#getRefinement <em>Refinement</em>}</li>
 *   <li>{@link org.splevo.vpm.refinement.impl.RefinementReasonImpl#getReason <em>Reason</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RefinementReasonImpl extends EObjectImpl implements RefinementReason {
    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected VariationPoint source;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected VariationPoint target;

    /**
     * The default value of the '{@link #getReason() <em>Reason</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReason()
     * @generated
     * @ordered
     */
    protected static final String REASON_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getReason() <em>Reason</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReason()
     * @generated
     * @ordered
     */
    protected String reason = REASON_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RefinementReasonImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RefinementPackage.Literals.REFINEMENT_REASON;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VariationPoint getSource() {
        if (source != null && source.eIsProxy()) {
            InternalEObject oldSource = (InternalEObject) source;
            source = (VariationPoint) eResolveProxy(oldSource);
            if (source != oldSource) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            RefinementPackage.REFINEMENT_REASON__SOURCE, oldSource, source));
            }
        }
        return source;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VariationPoint basicGetSource() {
        return source;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSource(VariationPoint newSource) {
        VariationPoint oldSource = source;
        source = newSource;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RefinementPackage.REFINEMENT_REASON__SOURCE,
                    oldSource, source));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VariationPoint getTarget() {
        if (target != null && target.eIsProxy()) {
            InternalEObject oldTarget = (InternalEObject) target;
            target = (VariationPoint) eResolveProxy(oldTarget);
            if (target != oldTarget) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            RefinementPackage.REFINEMENT_REASON__TARGET, oldTarget, target));
            }
        }
        return target;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VariationPoint basicGetTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTarget(VariationPoint newTarget) {
        VariationPoint oldTarget = target;
        target = newTarget;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RefinementPackage.REFINEMENT_REASON__TARGET,
                    oldTarget, target));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Refinement getRefinement() {
        if (eContainerFeatureID() != RefinementPackage.REFINEMENT_REASON__REFINEMENT)
            return null;
        return (Refinement) eInternalContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRefinement(Refinement newRefinement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject) newRefinement, RefinementPackage.REFINEMENT_REASON__REFINEMENT,
                msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRefinement(Refinement newRefinement) {
        if (newRefinement != eInternalContainer()
                || (eContainerFeatureID() != RefinementPackage.REFINEMENT_REASON__REFINEMENT && newRefinement != null)) {
            if (EcoreUtil.isAncestor(this, newRefinement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newRefinement != null)
                msgs = ((InternalEObject) newRefinement).eInverseAdd(this, RefinementPackage.REFINEMENT__REASONS,
                        Refinement.class, msgs);
            msgs = basicSetRefinement(newRefinement, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RefinementPackage.REFINEMENT_REASON__REFINEMENT,
                    newRefinement, newRefinement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getReason() {
        return reason;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setReason(String newReason) {
        String oldReason = reason;
        reason = newReason;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RefinementPackage.REFINEMENT_REASON__REASON,
                    oldReason, reason));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case RefinementPackage.REFINEMENT_REASON__REFINEMENT:
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            return basicSetRefinement((Refinement) otherEnd, msgs);
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
        case RefinementPackage.REFINEMENT_REASON__REFINEMENT:
            return basicSetRefinement(null, msgs);
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
        case RefinementPackage.REFINEMENT_REASON__REFINEMENT:
            return eInternalContainer().eInverseRemove(this, RefinementPackage.REFINEMENT__REASONS, Refinement.class,
                    msgs);
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
        case RefinementPackage.REFINEMENT_REASON__SOURCE:
            if (resolve)
                return getSource();
            return basicGetSource();
        case RefinementPackage.REFINEMENT_REASON__TARGET:
            if (resolve)
                return getTarget();
            return basicGetTarget();
        case RefinementPackage.REFINEMENT_REASON__REFINEMENT:
            return getRefinement();
        case RefinementPackage.REFINEMENT_REASON__REASON:
            return getReason();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case RefinementPackage.REFINEMENT_REASON__SOURCE:
            setSource((VariationPoint) newValue);
            return;
        case RefinementPackage.REFINEMENT_REASON__TARGET:
            setTarget((VariationPoint) newValue);
            return;
        case RefinementPackage.REFINEMENT_REASON__REFINEMENT:
            setRefinement((Refinement) newValue);
            return;
        case RefinementPackage.REFINEMENT_REASON__REASON:
            setReason((String) newValue);
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
        case RefinementPackage.REFINEMENT_REASON__SOURCE:
            setSource((VariationPoint) null);
            return;
        case RefinementPackage.REFINEMENT_REASON__TARGET:
            setTarget((VariationPoint) null);
            return;
        case RefinementPackage.REFINEMENT_REASON__REFINEMENT:
            setRefinement((Refinement) null);
            return;
        case RefinementPackage.REFINEMENT_REASON__REASON:
            setReason(REASON_EDEFAULT);
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
        case RefinementPackage.REFINEMENT_REASON__SOURCE:
            return source != null;
        case RefinementPackage.REFINEMENT_REASON__TARGET:
            return target != null;
        case RefinementPackage.REFINEMENT_REASON__REFINEMENT:
            return getRefinement() != null;
        case RefinementPackage.REFINEMENT_REASON__REASON:
            return REASON_EDEFAULT == null ? reason != null : !REASON_EDEFAULT.equals(reason);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (reason: ");
        result.append(reason);
        result.append(')');
        return result.toString();
    }

} //RefinementReasonImpl
