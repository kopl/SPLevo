/**
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Benjamin Klatt
 */
package org.splevo.vpm.variability.util;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.splevo.vpm.variability.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.splevo.vpm.variability.variabilityPackage
 * @generated
 */
public class variabilityValidator extends EObjectValidator {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final variabilityValidator INSTANCE = new variabilityValidator();

    /**
     * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.common.util.Diagnostic#getSource()
     * @see org.eclipse.emf.common.util.Diagnostic#getCode()
     * @generated
     */
    public static final String DIAGNOSTIC_SOURCE = "org.splevo.vpm.variability";

    /**
     * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'All Validators Succeed' of 'Variation Point'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final int VARIATION_POINT__ALL_VALIDATORS_SUCCEED = 1;

    /**
     * A constant with a fixed name that can be used as the base value for additional hand written constants.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 1;

    /**
     * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public variabilityValidator() {
        super();
    }

    /**
     * Returns the package of this validator switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EPackage getEPackage() {
        return variabilityPackage.eINSTANCE;
    }

    /**
     * Calls <code>validateXXX</code> for the corresponding classifier of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
        switch (classifierID) {
        case variabilityPackage.VARIATION_POINT:
            return validateVariationPoint((VariationPoint) value, diagnostics, context);
        case variabilityPackage.VARIANT:
            return validateVariant((Variant) value, diagnostics, context);
        case variabilityPackage.VARIATION_POINT_MODEL:
            return validateVariationPointModel((VariationPointModel) value, diagnostics, context);
        case variabilityPackage.VARIATION_POINT_GROUP:
            return validateVariationPointGroup((VariationPointGroup) value, diagnostics, context);
        case variabilityPackage.CUSTOMIZABLE_DESCRIPTION_HAVING:
            return validateCustomizableDescriptionHaving((CustomizableDescriptionHaving) value, diagnostics, context);
        case variabilityPackage.CUSTOMIZABLE_NAME_HAVING:
            return validateCustomizableNameHaving((CustomizableNameHaving) value, diagnostics, context);
        case variabilityPackage.IDENTIFIER:
            return validateIdentifier((Identifier) value, diagnostics, context);
        case variabilityPackage.EXTENSIBLE:
            return validateExtensible((Extensible) value, diagnostics, context);
        case variabilityPackage.VARIABILITY_TYPE:
            return validateVariabilityType((VariabilityType) value, diagnostics, context);
        case variabilityPackage.BINDING_TIME:
            return validateBindingTime((BindingTime) value, diagnostics, context);
        default:
            return true;
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateVariationPoint(VariationPoint variationPoint, DiagnosticChain diagnostics,
            Map<Object, Object> context) {
        if (!validate_NoCircularContainment(variationPoint, diagnostics, context))
            return false;
        boolean result = validate_EveryMultiplicityConforms(variationPoint, diagnostics, context);
        if (result || diagnostics != null)
            result &= validate_EveryDataValueConforms(variationPoint, diagnostics, context);
        if (result || diagnostics != null)
            result &= validate_EveryReferenceIsContained(variationPoint, diagnostics, context);
        if (result || diagnostics != null)
            result &= validate_EveryBidirectionalReferenceIsPaired(variationPoint, diagnostics, context);
        if (result || diagnostics != null)
            result &= validate_EveryProxyResolves(variationPoint, diagnostics, context);
        if (result || diagnostics != null)
            result &= validate_UniqueID(variationPoint, diagnostics, context);
        if (result || diagnostics != null)
            result &= validate_EveryKeyUnique(variationPoint, diagnostics, context);
        if (result || diagnostics != null)
            result &= validate_EveryMapEntryUnique(variationPoint, diagnostics, context);
        if (result || diagnostics != null)
            result &= validateVariationPoint_allValidatorsSucceed(variationPoint, diagnostics, context);
        return result;
    }

    /**
     * Validates the allValidatorsSucceed constraint of '<em>Variation Point</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateVariationPoint_allValidatorsSucceed(VariationPoint variationPoint,
            DiagnosticChain diagnostics, Map<Object, Object> context) {
        return variationPoint.allValidatorsSucceed(diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateVariant(Variant variant, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(variant, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateVariationPointModel(VariationPointModel variationPointModel, DiagnosticChain diagnostics,
            Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(variationPointModel, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateVariationPointGroup(VariationPointGroup variationPointGroup, DiagnosticChain diagnostics,
            Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(variationPointGroup, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateCustomizableDescriptionHaving(CustomizableDescriptionHaving customizableDescriptionHaving,
            DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(customizableDescriptionHaving, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateCustomizableNameHaving(CustomizableNameHaving customizableNameHaving,
            DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(customizableNameHaving, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateIdentifier(Identifier identifier, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(identifier, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateExtensible(Extensible extensible, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateVariabilityType(VariabilityType variabilityType, DiagnosticChain diagnostics,
            Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateBindingTime(BindingTime bindingTime, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        // TODO
        // Specialize this to return a resource locator for messages specific to this validator.
        // Ensure that you remove @generated or mark it @generated NOT
        return super.getResourceLocator();
    }

} //variabilityValidator
