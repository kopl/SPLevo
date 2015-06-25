/**
 */
package org.splevo.vpm.variability.impl;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.osgi.framework.BundleException;
import org.splevo.commons.eclipse.ExtensionHelper;
import org.splevo.vpm.VPMValidator;
import org.splevo.vpm.VPMValidator.VPMValidationResult;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.CustomizableDescriptionHaving;
import org.splevo.vpm.variability.CustomizableNameHaving;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.variabilityPackage;
import org.splevo.vpm.variability.util.variabilityValidator;

import com.google.common.collect.Lists;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variation Point</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointImpl#getVariants <em>Variants</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointImpl#getVariabilityType <em>Variability Type</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointImpl#getBindingTime <em>Binding Time</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointImpl#getExtensibility <em>Extensibility</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.impl.VariationPointImpl#getVariabilityMechanism <em>Variability Mechanism</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VariationPointImpl extends IdentifierImpl implements VariationPoint {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

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
     * The cached value of the '{@link #getVariants() <em>Variants</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVariants()
     * @generated
     * @ordered
     */
    protected EList<Variant> variants;

    /**
     * The cached value of the '{@link #getLocation() <em>Location</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLocation()
     * @generated
     * @ordered
     */
    protected SoftwareElement location;

    /**
     * The default value of the '{@link #getVariabilityType() <em>Variability Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVariabilityType()
     * @generated
     * @ordered
     */
    protected static final VariabilityType VARIABILITY_TYPE_EDEFAULT = VariabilityType.XOR;

    /**
     * The cached value of the '{@link #getVariabilityType() <em>Variability Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVariabilityType()
     * @generated
     * @ordered
     */
    protected VariabilityType variabilityType = VARIABILITY_TYPE_EDEFAULT;

    /**
     * The default value of the '{@link #getBindingTime() <em>Binding Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBindingTime()
     * @generated
     * @ordered
     */
    protected static final BindingTime BINDING_TIME_EDEFAULT = BindingTime.LOAD_TIME;

    /**
     * The cached value of the '{@link #getBindingTime() <em>Binding Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBindingTime()
     * @generated
     * @ordered
     */
    protected BindingTime bindingTime = BINDING_TIME_EDEFAULT;

    /**
     * The default value of the '{@link #getExtensibility() <em>Extensibility</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExtensibility()
     * @generated
     * @ordered
     */
    protected static final Extensible EXTENSIBILITY_EDEFAULT = Extensible.NO;

    /**
     * The cached value of the '{@link #getExtensibility() <em>Extensibility</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExtensibility()
     * @generated
     * @ordered
     */
    protected Extensible extensibility = EXTENSIBILITY_EDEFAULT;

    /**
     * The cached value of the '{@link #getVariabilityMechanism() <em>Variability Mechanism</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVariabilityMechanism()
     * @generated
     * @ordered
     */
    protected VariabilityMechanism variabilityMechanism;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected VariationPointImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return variabilityPackage.Literals.VARIATION_POINT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, variabilityPackage.VARIATION_POINT__NAME, oldName,
                    name));
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
            eNotify(new ENotificationImpl(this, Notification.SET, variabilityPackage.VARIATION_POINT__DESCRIPTION,
                    oldDescription, description));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Variant> getVariants() {
        if (variants == null) {
            variants = new EObjectContainmentWithInverseEList<Variant>(Variant.class, this,
                    variabilityPackage.VARIATION_POINT__VARIANTS, variabilityPackage.VARIANT__VARIATION_POINT);
        }
        return variants;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SoftwareElement getLocation() {
        if (location != null && location.eIsProxy()) {
            InternalEObject oldLocation = (InternalEObject) location;
            location = (SoftwareElement) eResolveProxy(oldLocation);
            if (location != oldLocation) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            variabilityPackage.VARIATION_POINT__LOCATION, oldLocation, location));
            }
        }
        return location;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SoftwareElement basicGetLocation() {
        return location;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLocation(SoftwareElement newLocation) {
        SoftwareElement oldLocation = location;
        location = newLocation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, variabilityPackage.VARIATION_POINT__LOCATION,
                    oldLocation, location));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VariationPointGroup getGroup() {
        if (eContainerFeatureID() != variabilityPackage.VARIATION_POINT__GROUP)
            return null;
        return (VariationPointGroup) eInternalContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetGroup(VariationPointGroup newGroup, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject) newGroup, variabilityPackage.VARIATION_POINT__GROUP, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGroup(VariationPointGroup newGroup) {
        if (newGroup != eInternalContainer()
                || (eContainerFeatureID() != variabilityPackage.VARIATION_POINT__GROUP && newGroup != null)) {
            if (EcoreUtil.isAncestor(this, newGroup))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newGroup != null)
                msgs = ((InternalEObject) newGroup).eInverseAdd(this,
                        variabilityPackage.VARIATION_POINT_GROUP__VARIATION_POINTS, VariationPointGroup.class, msgs);
            msgs = basicSetGroup(newGroup, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, variabilityPackage.VARIATION_POINT__GROUP, newGroup,
                    newGroup));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VariabilityType getVariabilityType() {
        return variabilityType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVariabilityType(VariabilityType newVariabilityType) {
        VariabilityType oldVariabilityType = variabilityType;
        variabilityType = newVariabilityType == null ? VARIABILITY_TYPE_EDEFAULT : newVariabilityType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, variabilityPackage.VARIATION_POINT__VARIABILITY_TYPE,
                    oldVariabilityType, variabilityType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BindingTime getBindingTime() {
        return bindingTime;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBindingTime(BindingTime newBindingTime) {
        BindingTime oldBindingTime = bindingTime;
        bindingTime = newBindingTime == null ? BINDING_TIME_EDEFAULT : newBindingTime;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, variabilityPackage.VARIATION_POINT__BINDING_TIME,
                    oldBindingTime, bindingTime));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Extensible getExtensibility() {
        return extensibility;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExtensibility(Extensible newExtensibility) {
        Extensible oldExtensibility = extensibility;
        extensibility = newExtensibility == null ? EXTENSIBILITY_EDEFAULT : newExtensibility;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, variabilityPackage.VARIATION_POINT__EXTENSIBILITY,
                    oldExtensibility, extensibility));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VariabilityMechanism getVariabilityMechanism() {
        return variabilityMechanism;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetVariabilityMechanism(VariabilityMechanism newVariabilityMechanism,
            NotificationChain msgs) {
        VariabilityMechanism oldVariabilityMechanism = variabilityMechanism;
        variabilityMechanism = newVariabilityMechanism;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    variabilityPackage.VARIATION_POINT__VARIABILITY_MECHANISM, oldVariabilityMechanism,
                    newVariabilityMechanism);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVariabilityMechanism(VariabilityMechanism newVariabilityMechanism) {
        if (newVariabilityMechanism != variabilityMechanism) {
            NotificationChain msgs = null;
            if (variabilityMechanism != null)
                msgs = ((InternalEObject) variabilityMechanism).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                        - variabilityPackage.VARIATION_POINT__VARIABILITY_MECHANISM, null, msgs);
            if (newVariabilityMechanism != null)
                msgs = ((InternalEObject) newVariabilityMechanism).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                        - variabilityPackage.VARIATION_POINT__VARIABILITY_MECHANISM, null, msgs);
            msgs = basicSetVariabilityMechanism(newVariabilityMechanism, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    variabilityPackage.VARIATION_POINT__VARIABILITY_MECHANISM, newVariabilityMechanism,
                    newVariabilityMechanism));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated not
     */
    public boolean allValidatorsSucceed(DiagnosticChain chain, Map<?, ?> context) {
        boolean result = true;

        Iterable<VPMValidator> validators = Lists.newArrayList();
        try {
            validators = ExtensionHelper.getAllRegisteredExtensions("org.spelvo.vpm.vpmvalidators", VPMValidator.class);
        } catch (BundleException e) {
            // validation aborted
            if (chain != null) {
                chain.add(Diagnostic.CANCEL_INSTANCE);
            }
            return true;
        }

        for (VPMValidator validator : validators) {
            VPMValidationResult validationResult = validator.validate(this);
            if (!validationResult.isValid()) {
                if (chain != null) {
                    chain.add(new BasicDiagnostic(Diagnostic.ERROR, variabilityValidator.DIAGNOSTIC_SOURCE,
                            variabilityValidator.VARIATION_POINT__ALL_VALIDATORS_SUCCEED,
                            validationResult.getMessage(), new Object[] { this }));
                }
                result = false;
            }
        }

        return result;
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
        case variabilityPackage.VARIATION_POINT__VARIANTS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getVariants()).basicAdd(otherEnd, msgs);
        case variabilityPackage.VARIATION_POINT__GROUP:
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            return basicSetGroup((VariationPointGroup) otherEnd, msgs);
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
        case variabilityPackage.VARIATION_POINT__VARIANTS:
            return ((InternalEList<?>) getVariants()).basicRemove(otherEnd, msgs);
        case variabilityPackage.VARIATION_POINT__GROUP:
            return basicSetGroup(null, msgs);
        case variabilityPackage.VARIATION_POINT__VARIABILITY_MECHANISM:
            return basicSetVariabilityMechanism(null, msgs);
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
        case variabilityPackage.VARIATION_POINT__GROUP:
            return eInternalContainer().eInverseRemove(this,
                    variabilityPackage.VARIATION_POINT_GROUP__VARIATION_POINTS, VariationPointGroup.class, msgs);
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
        case variabilityPackage.VARIATION_POINT__NAME:
            return getName();
        case variabilityPackage.VARIATION_POINT__DESCRIPTION:
            return getDescription();
        case variabilityPackage.VARIATION_POINT__VARIANTS:
            return getVariants();
        case variabilityPackage.VARIATION_POINT__LOCATION:
            if (resolve)
                return getLocation();
            return basicGetLocation();
        case variabilityPackage.VARIATION_POINT__GROUP:
            return getGroup();
        case variabilityPackage.VARIATION_POINT__VARIABILITY_TYPE:
            return getVariabilityType();
        case variabilityPackage.VARIATION_POINT__BINDING_TIME:
            return getBindingTime();
        case variabilityPackage.VARIATION_POINT__EXTENSIBILITY:
            return getExtensibility();
        case variabilityPackage.VARIATION_POINT__VARIABILITY_MECHANISM:
            return getVariabilityMechanism();
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
        case variabilityPackage.VARIATION_POINT__NAME:
            setName((String) newValue);
            return;
        case variabilityPackage.VARIATION_POINT__DESCRIPTION:
            setDescription((String) newValue);
            return;
        case variabilityPackage.VARIATION_POINT__VARIANTS:
            getVariants().clear();
            getVariants().addAll((Collection<? extends Variant>) newValue);
            return;
        case variabilityPackage.VARIATION_POINT__LOCATION:
            setLocation((SoftwareElement) newValue);
            return;
        case variabilityPackage.VARIATION_POINT__GROUP:
            setGroup((VariationPointGroup) newValue);
            return;
        case variabilityPackage.VARIATION_POINT__VARIABILITY_TYPE:
            setVariabilityType((VariabilityType) newValue);
            return;
        case variabilityPackage.VARIATION_POINT__BINDING_TIME:
            setBindingTime((BindingTime) newValue);
            return;
        case variabilityPackage.VARIATION_POINT__EXTENSIBILITY:
            setExtensibility((Extensible) newValue);
            return;
        case variabilityPackage.VARIATION_POINT__VARIABILITY_MECHANISM:
            setVariabilityMechanism((VariabilityMechanism) newValue);
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
        case variabilityPackage.VARIATION_POINT__NAME:
            setName(NAME_EDEFAULT);
            return;
        case variabilityPackage.VARIATION_POINT__DESCRIPTION:
            setDescription(DESCRIPTION_EDEFAULT);
            return;
        case variabilityPackage.VARIATION_POINT__VARIANTS:
            getVariants().clear();
            return;
        case variabilityPackage.VARIATION_POINT__LOCATION:
            setLocation((SoftwareElement) null);
            return;
        case variabilityPackage.VARIATION_POINT__GROUP:
            setGroup((VariationPointGroup) null);
            return;
        case variabilityPackage.VARIATION_POINT__VARIABILITY_TYPE:
            setVariabilityType(VARIABILITY_TYPE_EDEFAULT);
            return;
        case variabilityPackage.VARIATION_POINT__BINDING_TIME:
            setBindingTime(BINDING_TIME_EDEFAULT);
            return;
        case variabilityPackage.VARIATION_POINT__EXTENSIBILITY:
            setExtensibility(EXTENSIBILITY_EDEFAULT);
            return;
        case variabilityPackage.VARIATION_POINT__VARIABILITY_MECHANISM:
            setVariabilityMechanism((VariabilityMechanism) null);
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
        case variabilityPackage.VARIATION_POINT__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
        case variabilityPackage.VARIATION_POINT__DESCRIPTION:
            return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
        case variabilityPackage.VARIATION_POINT__VARIANTS:
            return variants != null && !variants.isEmpty();
        case variabilityPackage.VARIATION_POINT__LOCATION:
            return location != null;
        case variabilityPackage.VARIATION_POINT__GROUP:
            return getGroup() != null;
        case variabilityPackage.VARIATION_POINT__VARIABILITY_TYPE:
            return variabilityType != VARIABILITY_TYPE_EDEFAULT;
        case variabilityPackage.VARIATION_POINT__BINDING_TIME:
            return bindingTime != BINDING_TIME_EDEFAULT;
        case variabilityPackage.VARIATION_POINT__EXTENSIBILITY:
            return extensibility != EXTENSIBILITY_EDEFAULT;
        case variabilityPackage.VARIATION_POINT__VARIABILITY_MECHANISM:
            return variabilityMechanism != null;
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
        if (baseClass == CustomizableNameHaving.class) {
            switch (derivedFeatureID) {
            case variabilityPackage.VARIATION_POINT__NAME:
                return variabilityPackage.CUSTOMIZABLE_NAME_HAVING__NAME;
            default:
                return -1;
            }
        }
        if (baseClass == CustomizableDescriptionHaving.class) {
            switch (derivedFeatureID) {
            case variabilityPackage.VARIATION_POINT__DESCRIPTION:
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
        if (baseClass == CustomizableNameHaving.class) {
            switch (baseFeatureID) {
            case variabilityPackage.CUSTOMIZABLE_NAME_HAVING__NAME:
                return variabilityPackage.VARIATION_POINT__NAME;
            default:
                return -1;
            }
        }
        if (baseClass == CustomizableDescriptionHaving.class) {
            switch (baseFeatureID) {
            case variabilityPackage.CUSTOMIZABLE_DESCRIPTION_HAVING__DESCRIPTION:
                return variabilityPackage.VARIATION_POINT__DESCRIPTION;
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
        result.append(" (name: ");
        result.append(name);
        result.append(", description: ");
        result.append(description);
        result.append(", variabilityType: ");
        result.append(variabilityType);
        result.append(", bindingTime: ");
        result.append(bindingTime);
        result.append(", extensibility: ");
        result.append(extensibility);
        result.append(')');
        return result.toString();
    }

} //VariationPointImpl
