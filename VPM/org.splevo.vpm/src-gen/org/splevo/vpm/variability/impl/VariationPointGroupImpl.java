/**
 */
package org.splevo.vpm.variability.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.featuremodel.Feature;
import org.splevo.vpm.variability.CustomizableDescriptionHaving;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variation Point Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointGroupImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointGroupImpl#getVariationPoints <em>Variation Points</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointGroupImpl#getModel <em>Model</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointGroupImpl#getFeature <em>Feature</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VariationPointGroupImpl extends CustomizableNameHavingImpl implements VariationPointGroup {
    /**
     * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected String description = DESCRIPTION_EDEFAULT;

    /**
     * The cached value of the '{@link #getVariationPoints() <em>Variation Points</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVariationPoints()
     * @generated
     * @ordered
     */
    protected EList<VariationPoint> variationPoints;

    /**
     * The cached value of the '{@link #getFeature() <em>Feature</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFeature()
     * @generated
     * @ordered
     */
    protected Feature feature;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected VariationPointGroupImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return variabilityPackage.Literals.VARIATION_POINT_GROUP;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    variabilityPackage.VARIATION_POINT_GROUP__DESCRIPTION, oldDescription, description));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<VariationPoint> getVariationPoints() {
        if (variationPoints == null) {
            variationPoints = new EObjectContainmentWithInverseEList<VariationPoint>(VariationPoint.class, this,
                    variabilityPackage.VARIATION_POINT_GROUP__VARIATION_POINTS,
                    variabilityPackage.VARIATION_POINT__GROUP);
        }
        return variationPoints;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VariationPointModel getModel() {
        if (eContainerFeatureID() != variabilityPackage.VARIATION_POINT_GROUP__MODEL)
            return null;
        return (VariationPointModel) eInternalContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetModel(VariationPointModel newModel, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject) newModel, variabilityPackage.VARIATION_POINT_GROUP__MODEL, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setModel(VariationPointModel newModel) {
        if (newModel != eInternalContainer()
                || (eContainerFeatureID() != variabilityPackage.VARIATION_POINT_GROUP__MODEL && newModel != null)) {
            if (EcoreUtil.isAncestor(this, newModel))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newModel != null)
                msgs = ((InternalEObject) newModel).eInverseAdd(this,
                        variabilityPackage.VARIATION_POINT_MODEL__VARIATION_POINT_GROUPS, VariationPointModel.class,
                        msgs);
            msgs = basicSetModel(newModel, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, variabilityPackage.VARIATION_POINT_GROUP__MODEL,
                    newModel, newModel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Feature getFeature() {
        if (feature != null && feature.eIsProxy()) {
            InternalEObject oldFeature = (InternalEObject) feature;
            feature = (Feature) eResolveProxy(oldFeature);
            if (feature != oldFeature) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            variabilityPackage.VARIATION_POINT_GROUP__FEATURE, oldFeature, feature));
            }
        }
        return feature;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Feature basicGetFeature() {
        return feature;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFeature(Feature newFeature) {
        Feature oldFeature = feature;
        feature = newFeature;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, variabilityPackage.VARIATION_POINT_GROUP__FEATURE,
                    oldFeature, feature));
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
        case variabilityPackage.VARIATION_POINT_GROUP__VARIATION_POINTS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getVariationPoints()).basicAdd(otherEnd, msgs);
        case variabilityPackage.VARIATION_POINT_GROUP__MODEL:
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            return basicSetModel((VariationPointModel) otherEnd, msgs);
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
        case variabilityPackage.VARIATION_POINT_GROUP__VARIATION_POINTS:
            return ((InternalEList<?>) getVariationPoints()).basicRemove(otherEnd, msgs);
        case variabilityPackage.VARIATION_POINT_GROUP__MODEL:
            return basicSetModel(null, msgs);
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
        case variabilityPackage.VARIATION_POINT_GROUP__MODEL:
            return eInternalContainer().eInverseRemove(this,
                    variabilityPackage.VARIATION_POINT_MODEL__VARIATION_POINT_GROUPS, VariationPointModel.class, msgs);
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
        case variabilityPackage.VARIATION_POINT_GROUP__DESCRIPTION:
            return getDescription();
        case variabilityPackage.VARIATION_POINT_GROUP__VARIATION_POINTS:
            return getVariationPoints();
        case variabilityPackage.VARIATION_POINT_GROUP__MODEL:
            return getModel();
        case variabilityPackage.VARIATION_POINT_GROUP__FEATURE:
            if (resolve)
                return getFeature();
            return basicGetFeature();
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
        case variabilityPackage.VARIATION_POINT_GROUP__DESCRIPTION:
            setDescription((String) newValue);
            return;
        case variabilityPackage.VARIATION_POINT_GROUP__VARIATION_POINTS:
            getVariationPoints().clear();
            getVariationPoints().addAll((Collection<? extends VariationPoint>) newValue);
            return;
        case variabilityPackage.VARIATION_POINT_GROUP__MODEL:
            setModel((VariationPointModel) newValue);
            return;
        case variabilityPackage.VARIATION_POINT_GROUP__FEATURE:
            setFeature((Feature) newValue);
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
        case variabilityPackage.VARIATION_POINT_GROUP__DESCRIPTION:
            setDescription(DESCRIPTION_EDEFAULT);
            return;
        case variabilityPackage.VARIATION_POINT_GROUP__VARIATION_POINTS:
            getVariationPoints().clear();
            return;
        case variabilityPackage.VARIATION_POINT_GROUP__MODEL:
            setModel((VariationPointModel) null);
            return;
        case variabilityPackage.VARIATION_POINT_GROUP__FEATURE:
            setFeature((Feature) null);
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
        case variabilityPackage.VARIATION_POINT_GROUP__DESCRIPTION:
            return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
        case variabilityPackage.VARIATION_POINT_GROUP__VARIATION_POINTS:
            return variationPoints != null && !variationPoints.isEmpty();
        case variabilityPackage.VARIATION_POINT_GROUP__MODEL:
            return getModel() != null;
        case variabilityPackage.VARIATION_POINT_GROUP__FEATURE:
            return feature != null;
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
        if (baseClass == CustomizableDescriptionHaving.class) {
            switch (derivedFeatureID) {
            case variabilityPackage.VARIATION_POINT_GROUP__DESCRIPTION:
                return variabilityPackage.CUSTOMIZABLE_DESCRIPTION_HAVING__DESCRIPTION;
            default:
                return -1;
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
        if (baseClass == CustomizableDescriptionHaving.class) {
            switch (baseFeatureID) {
            case variabilityPackage.CUSTOMIZABLE_DESCRIPTION_HAVING__DESCRIPTION:
                return variabilityPackage.VARIATION_POINT_GROUP__DESCRIPTION;
            default:
                return -1;
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
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (description: ");
        result.append(description);
        result.append(')');
        return result.toString();
    }

} //VariationPointGroupImpl
