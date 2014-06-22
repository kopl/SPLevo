/**
 */
package org.splevo.vpm.refinement.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementModel;
import org.splevo.vpm.refinement.RefinementPackage;
import org.splevo.vpm.refinement.RefinementReason;
import org.splevo.vpm.refinement.RefinementType;
import org.splevo.vpm.variability.VariationPoint;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Refinement</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.vpm.refinement.impl.RefinementImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.splevo.vpm.refinement.impl.RefinementImpl#getVariationPoints <em>Variation Points</em>}</li>
 *   <li>{@link org.splevo.vpm.refinement.impl.RefinementImpl#getRefinementModel <em>Refinement Model</em>}</li>
 *   <li>{@link org.splevo.vpm.refinement.impl.RefinementImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.splevo.vpm.refinement.impl.RefinementImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.splevo.vpm.refinement.impl.RefinementImpl#getSubRefinements <em>Sub Refinements</em>}</li>
 *   <li>{@link org.splevo.vpm.refinement.impl.RefinementImpl#getReasons <em>Reasons</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RefinementImpl extends EObjectImpl implements Refinement {
    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final RefinementType TYPE_EDEFAULT = RefinementType.GROUPING;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected RefinementType type = TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getVariationPoints() <em>Variation Points</em>}' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getVariationPoints()
     * @generated
     * @ordered
     */
    protected EList<VariationPoint> variationPoints;

    /**
     * The default value of the '{@link #getSource() <em>Source</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected static final String SOURCE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected String source = SOURCE_EDEFAULT;

    /**
     * The cached value of the '{@link #getSubRefinements() <em>Sub Refinements</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSubRefinements()
     * @generated
     * @ordered
     */
    protected EList<Refinement> subRefinements;

    /**
     * The cached value of the '{@link #getReasons() <em>Reasons</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReasons()
     * @generated
     * @ordered
     */
    protected EList<RefinementReason> reasons;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected RefinementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RefinementPackage.Literals.REFINEMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public RefinementType getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setType(RefinementType newType) {
        RefinementType oldType = type;
        type = newType == null ? TYPE_EDEFAULT : newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RefinementPackage.REFINEMENT__TYPE, oldType, type));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<VariationPoint> getVariationPoints() {
        if (variationPoints == null) {
            variationPoints = new EObjectResolvingEList<VariationPoint>(VariationPoint.class, this,
                    RefinementPackage.REFINEMENT__VARIATION_POINTS);
        }
        return variationPoints;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public RefinementModel getRefinementModel() {
        if (eContainerFeatureID() != RefinementPackage.REFINEMENT__REFINEMENT_MODEL)
            return null;
        return (RefinementModel) eInternalContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRefinementModel(RefinementModel newRefinementModel, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject) newRefinementModel, RefinementPackage.REFINEMENT__REFINEMENT_MODEL,
                msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setRefinementModel(RefinementModel newRefinementModel) {
        if (newRefinementModel != eInternalContainer()
                || (eContainerFeatureID() != RefinementPackage.REFINEMENT__REFINEMENT_MODEL && newRefinementModel != null)) {
            if (EcoreUtil.isAncestor(this, newRefinementModel))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newRefinementModel != null)
                msgs = ((InternalEObject) newRefinementModel).eInverseAdd(this,
                        RefinementPackage.REFINEMENT_MODEL__REFINEMENTS, RefinementModel.class, msgs);
            msgs = basicSetRefinementModel(newRefinementModel, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RefinementPackage.REFINEMENT__REFINEMENT_MODEL,
                    newRefinementModel, newRefinementModel));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String getSource() {
        return source;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setSource(String newSource) {
        String oldSource = source;
        source = newSource;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RefinementPackage.REFINEMENT__SOURCE, oldSource,
                    source));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Refinement getParent() {
        if (eContainerFeatureID() != RefinementPackage.REFINEMENT__PARENT)
            return null;
        return (Refinement) eInternalContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParent(Refinement newParent, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject) newParent, RefinementPackage.REFINEMENT__PARENT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParent(Refinement newParent) {
        if (newParent != eInternalContainer()
                || (eContainerFeatureID() != RefinementPackage.REFINEMENT__PARENT && newParent != null)) {
            if (EcoreUtil.isAncestor(this, newParent))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newParent != null)
                msgs = ((InternalEObject) newParent).eInverseAdd(this, RefinementPackage.REFINEMENT__SUB_REFINEMENTS,
                        Refinement.class, msgs);
            msgs = basicSetParent(newParent, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RefinementPackage.REFINEMENT__PARENT, newParent,
                    newParent));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Refinement> getSubRefinements() {
        if (subRefinements == null) {
            subRefinements = new EObjectContainmentWithInverseEList<Refinement>(Refinement.class, this,
                    RefinementPackage.REFINEMENT__SUB_REFINEMENTS, RefinementPackage.REFINEMENT__PARENT);
        }
        return subRefinements;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<RefinementReason> getReasons() {
        if (reasons == null) {
            reasons = new EObjectContainmentWithInverseEList<RefinementReason>(RefinementReason.class, this,
                    RefinementPackage.REFINEMENT__REASONS, RefinementPackage.REFINEMENT_REASON__REFINEMENT);
        }
        return reasons;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case RefinementPackage.REFINEMENT__REFINEMENT_MODEL:
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            return basicSetRefinementModel((RefinementModel) otherEnd, msgs);
        case RefinementPackage.REFINEMENT__PARENT:
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            return basicSetParent((Refinement) otherEnd, msgs);
        case RefinementPackage.REFINEMENT__SUB_REFINEMENTS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getSubRefinements()).basicAdd(otherEnd, msgs);
        case RefinementPackage.REFINEMENT__REASONS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getReasons()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case RefinementPackage.REFINEMENT__REFINEMENT_MODEL:
            return basicSetRefinementModel(null, msgs);
        case RefinementPackage.REFINEMENT__PARENT:
            return basicSetParent(null, msgs);
        case RefinementPackage.REFINEMENT__SUB_REFINEMENTS:
            return ((InternalEList<?>) getSubRefinements()).basicRemove(otherEnd, msgs);
        case RefinementPackage.REFINEMENT__REASONS:
            return ((InternalEList<?>) getReasons()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID()) {
        case RefinementPackage.REFINEMENT__REFINEMENT_MODEL:
            return eInternalContainer().eInverseRemove(this, RefinementPackage.REFINEMENT_MODEL__REFINEMENTS,
                    RefinementModel.class, msgs);
        case RefinementPackage.REFINEMENT__PARENT:
            return eInternalContainer().eInverseRemove(this, RefinementPackage.REFINEMENT__SUB_REFINEMENTS,
                    Refinement.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case RefinementPackage.REFINEMENT__TYPE:
            return getType();
        case RefinementPackage.REFINEMENT__VARIATION_POINTS:
            return getVariationPoints();
        case RefinementPackage.REFINEMENT__REFINEMENT_MODEL:
            return getRefinementModel();
        case RefinementPackage.REFINEMENT__SOURCE:
            return getSource();
        case RefinementPackage.REFINEMENT__PARENT:
            return getParent();
        case RefinementPackage.REFINEMENT__SUB_REFINEMENTS:
            return getSubRefinements();
        case RefinementPackage.REFINEMENT__REASONS:
            return getReasons();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case RefinementPackage.REFINEMENT__TYPE:
            setType((RefinementType) newValue);
            return;
        case RefinementPackage.REFINEMENT__VARIATION_POINTS:
            getVariationPoints().clear();
            getVariationPoints().addAll((Collection<? extends VariationPoint>) newValue);
            return;
        case RefinementPackage.REFINEMENT__REFINEMENT_MODEL:
            setRefinementModel((RefinementModel) newValue);
            return;
        case RefinementPackage.REFINEMENT__SOURCE:
            setSource((String) newValue);
            return;
        case RefinementPackage.REFINEMENT__PARENT:
            setParent((Refinement) newValue);
            return;
        case RefinementPackage.REFINEMENT__SUB_REFINEMENTS:
            getSubRefinements().clear();
            getSubRefinements().addAll((Collection<? extends Refinement>) newValue);
            return;
        case RefinementPackage.REFINEMENT__REASONS:
            getReasons().clear();
            getReasons().addAll((Collection<? extends RefinementReason>) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case RefinementPackage.REFINEMENT__TYPE:
            setType(TYPE_EDEFAULT);
            return;
        case RefinementPackage.REFINEMENT__VARIATION_POINTS:
            getVariationPoints().clear();
            return;
        case RefinementPackage.REFINEMENT__REFINEMENT_MODEL:
            setRefinementModel((RefinementModel) null);
            return;
        case RefinementPackage.REFINEMENT__SOURCE:
            setSource(SOURCE_EDEFAULT);
            return;
        case RefinementPackage.REFINEMENT__PARENT:
            setParent((Refinement) null);
            return;
        case RefinementPackage.REFINEMENT__SUB_REFINEMENTS:
            getSubRefinements().clear();
            return;
        case RefinementPackage.REFINEMENT__REASONS:
            getReasons().clear();
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case RefinementPackage.REFINEMENT__TYPE:
            return type != TYPE_EDEFAULT;
        case RefinementPackage.REFINEMENT__VARIATION_POINTS:
            return variationPoints != null && !variationPoints.isEmpty();
        case RefinementPackage.REFINEMENT__REFINEMENT_MODEL:
            return getRefinementModel() != null;
        case RefinementPackage.REFINEMENT__SOURCE:
            return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
        case RefinementPackage.REFINEMENT__PARENT:
            return getParent() != null;
        case RefinementPackage.REFINEMENT__SUB_REFINEMENTS:
            return subRefinements != null && !subRefinements.isEmpty();
        case RefinementPackage.REFINEMENT__REASONS:
            return reasons != null && !reasons.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (type: ");
        result.append(type);
        result.append(", source: ");
        result.append(source);
        result.append(')');
        return result.toString();
    }

} // RefinementImpl
