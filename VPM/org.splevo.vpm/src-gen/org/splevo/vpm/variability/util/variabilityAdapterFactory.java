/**
 */
package org.splevo.vpm.variability.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.splevo.vpm.variability.*;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.splevo.vpm.variability.variabilityPackage
 * @generated
 */
public class variabilityAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static variabilityPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public variabilityAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = variabilityPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected variabilitySwitch<Adapter> modelSwitch = new variabilitySwitch<Adapter>() {
        @Override
        public Adapter caseVariationPoint(VariationPoint object) {
            return createVariationPointAdapter();
        }

        @Override
        public Adapter caseVariant(Variant object) {
            return createVariantAdapter();
        }

        @Override
        public Adapter caseVariationPointModel(VariationPointModel object) {
            return createVariationPointModelAdapter();
        }

        @Override
        public Adapter caseVariationPointGroup(VariationPointGroup object) {
            return createVariationPointGroupAdapter();
        }

        @Override
        public Adapter caseCustomizableDescriptionHaving(CustomizableDescriptionHaving object) {
            return createCustomizableDescriptionHavingAdapter();
        }

        @Override
        public Adapter caseCustomizableNameHaving(CustomizableNameHaving object) {
            return createCustomizableNameHavingAdapter();
        }

        @Override
        public Adapter caseIdentifier(Identifier object) {
            return createIdentifierAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '{@link org.splevo.vpm.variability.VariationPoint <em>Variation Point</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.splevo.vpm.variability.VariationPoint
     * @generated
     */
    public Adapter createVariationPointAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.splevo.vpm.variability.Variant <em>Variant</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.splevo.vpm.variability.Variant
     * @generated
     */
    public Adapter createVariantAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.splevo.vpm.variability.VariationPointModel <em>Variation Point Model</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.splevo.vpm.variability.VariationPointModel
     * @generated
     */
    public Adapter createVariationPointModelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.splevo.vpm.variability.VariationPointGroup <em>Variation Point Group</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.splevo.vpm.variability.VariationPointGroup
     * @generated
     */
    public Adapter createVariationPointGroupAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.splevo.vpm.variability.CustomizableDescriptionHaving <em>Customizable Description Having</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.splevo.vpm.variability.CustomizableDescriptionHaving
     * @generated
     */
    public Adapter createCustomizableDescriptionHavingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.splevo.vpm.variability.CustomizableNameHaving <em>Customizable Name Having</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.splevo.vpm.variability.CustomizableNameHaving
     * @generated
     */
    public Adapter createCustomizableNameHavingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.splevo.vpm.variability.Identifier <em>Identifier</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.splevo.vpm.variability.Identifier
     * @generated
     */
    public Adapter createIdentifierAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //variabilityAdapterFactory
