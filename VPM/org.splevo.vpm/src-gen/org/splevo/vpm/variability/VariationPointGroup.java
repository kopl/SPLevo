package org.splevo.vpm.variability;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.featuremodel.Feature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variation Point Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A variation point group combines variation points which belong together. 
 * While a variation point represents a location in the code which contains a variability and defines the different alternatives (variants) for this location, a group defines all variation points that contribute to a single software option (feature).
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.vpm.variability.VariationPointGroup#getVariationPoints <em>Variation Points</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.VariationPointGroup#getGroupId <em>Group Id</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.VariationPointGroup#getModel <em>Model</em>}</li>
 *   <li>{@link org.splevo.vpm.variability.VariationPointGroup#getFeature <em>Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.vpm.variability.variabilityPackage#getVariationPointGroup()
 * @model
 * @generated
 */
public interface VariationPointGroup extends EObject {
    /**
     * Returns the value of the '<em><b>Variation Points</b></em>' containment reference list.
     * The list contents are of type {@link org.splevo.vpm.variability.VariationPoint}.
     * It is bidirectional and its opposite is '{@link org.splevo.vpm.variability.VariationPoint#getGroup <em>Group</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The variation points which are members of the variation point group.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Variation Points</em>' containment reference list.
     * @see org.splevo.vpm.variability.variabilityPackage#getVariationPointGroup_VariationPoints()
     * @see org.splevo.vpm.variability.VariationPoint#getGroup
     * @model opposite="group" containment="true" required="true"
     * @generated
     */
    EList<VariationPoint> getVariationPoints();

    /**
     * Returns the value of the '<em><b>Group Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The identifier for the group. 
     * It represents an external, configurable identifier for the group which can be used also for the realization (e.g. as name of a configuration parameter)
     * <!-- end-model-doc -->
     * @return the value of the '<em>Group Id</em>' attribute.
     * @see #setGroupId(String)
     * @see org.splevo.vpm.variability.variabilityPackage#getVariationPointGroup_GroupId()
     * @model required="true"
     * @generated
     */
    String getGroupId();

    /**
     * Sets the value of the '{@link org.splevo.vpm.variability.VariationPointGroup#getGroupId <em>Group Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Group Id</em>' attribute.
     * @see #getGroupId()
     * @generated
     */
    void setGroupId(String value);

    /**
     * Returns the value of the '<em><b>Model</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.splevo.vpm.variability.VariationPointModel#getVariationPointGroups <em>Variation Point Groups</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Model</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Model</em>' container reference.
     * @see #setModel(VariationPointModel)
     * @see org.splevo.vpm.variability.variabilityPackage#getVariationPointGroup_Model()
     * @see org.splevo.vpm.variability.VariationPointModel#getVariationPointGroups
     * @model opposite="variationPointGroups" required="true" transient="false"
     * @generated
     */
    VariationPointModel getModel();

    /**
     * Sets the value of the '{@link org.splevo.vpm.variability.VariationPointGroup#getModel <em>Model</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Model</em>' container reference.
     * @see #getModel()
     * @generated
     */
    void setModel(VariationPointModel value);

    /**
     * Returns the value of the '<em><b>Feature</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Feature</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Feature</em>' reference.
     * @see #setFeature(Feature)
     * @see org.splevo.vpm.variability.variabilityPackage#getVariationPointGroup_Feature()
     * @model
     * @generated
     */
    Feature getFeature();

    /**
     * Sets the value of the '{@link org.splevo.vpm.variability.VariationPointGroup#getFeature <em>Feature</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Feature</em>' reference.
     * @see #getFeature()
     * @generated
     */
    void setFeature(Feature value);

} // VariationPointGroup
