/**
 */
package org.splevo.vpm.variability;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.featuremodel.Feature;
import org.splevo.vpm.software.SoftwareElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.vpm.variability.Variant#getChildFeature <em>Child Feature</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.Variant#getImplementingElements <em>Implementing Elements</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.Variant#getLeading <em>Leading</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.Variant#getVariantId <em>Variant Id</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.Variant#getVariationPoint <em>Variation Point</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.vpm.variability.variabilityPackage#getVariant()
 * @model
 * @generated
 */
public interface Variant extends EObject {
    /**
     * Returns the value of the '<em><b>Child Feature</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Child Feature</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Child Feature</em>' reference.
     * @see #setChildFeature(Feature)
     * @see org.splevo.vpm.variability.variabilityPackage#getVariant_ChildFeature()
     * @model
     * @generated
     */
    Feature getChildFeature();

    /**
     * Sets the value of the '{@link org.splevo.vpm.variability.Variant#getChildFeature <em>Child Feature</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Child Feature</em>' reference.
     * @see #getChildFeature()
     * @generated
     */
    void setChildFeature(Feature value);

    /**
     * Returns the value of the '<em><b>Implementing Elements</b></em>' reference list.
     * The list contents are of type {@link org.splevo.vpm.software.SoftwareElement}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Software Entities</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Implementing Elements</em>' reference list.
     * @see org.splevo.vpm.variability.variabilityPackage#getVariant_ImplementingElements()
     * @model required="true"
     * @generated
     */
    EList<SoftwareElement> getImplementingElements();

    /**
     * Returns the value of the '<em><b>Leading</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Flag identifying if the variant is one of the leading variantes.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Leading</em>' attribute.
     * @see #setLeading(Boolean)
     * @see org.splevo.vpm.variability.variabilityPackage#getVariant_Leading()
     * @model required="true"
     * @generated
     */
    Boolean getLeading();

    /**
     * Sets the value of the '{@link org.splevo.vpm.variability.Variant#getLeading <em>Leading</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Leading</em>' attribute.
     * @see #getLeading()
     * @generated
     */
    void setLeading(Boolean value);

    /**
     * Returns the value of the '<em><b>Variant Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The external configurable and processable identifier of a variant.
     * This can be used e.g. to identifier a variant within the realization technique, e.g. a value of a configuration parameter.
     * All variants of variation points in the same variation point group, that contribute to the same alternative of a variable product feature should have the same variant ID.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Variant Id</em>' attribute.
     * @see #setVariantId(String)
     * @see org.splevo.vpm.variability.variabilityPackage#getVariant_VariantId()
     * @model required="true"
     * @generated
     */
    String getVariantId();

    /**
     * Sets the value of the '{@link org.splevo.vpm.variability.Variant#getVariantId <em>Variant Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Variant Id</em>' attribute.
     * @see #getVariantId()
     * @generated
     */
    void setVariantId(String value);

    /**
     * Returns the value of the '<em><b>Variation Point</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.splevo.vpm.variability.VariationPoint#getVariants <em>Variants</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variation Point</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Variation Point</em>' container reference.
     * @see #setVariationPoint(VariationPoint)
     * @see org.splevo.vpm.variability.variabilityPackage#getVariant_VariationPoint()
     * @see org.splevo.vpm.variability.VariationPoint#getVariants
     * @model opposite="variants" required="true" transient="false"
     * @generated
     */
    VariationPoint getVariationPoint();

    /**
     * Sets the value of the '{@link org.splevo.vpm.variability.Variant#getVariationPoint <em>Variation Point</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Variation Point</em>' container reference.
     * @see #getVariationPoint()
     * @generated
     */
    void setVariationPoint(VariationPoint value);

} // Variant
