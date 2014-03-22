/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.vpm.refinement;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.splevo.vpm.variability.VariationPoint;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Refinement</b></em>'. <!--
 * end-user-doc -->
 * 
 * <!-- begin-model-doc --> A refinement identifies a refinement within a variation point model.
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.splevo.vpm.refinement.Refinement#getType <em>Type</em>}</li>
 * <li>{@link org.splevo.vpm.refinement.Refinement#getVariationPoints <em>Variation Points</em>}</li>
 * <li>{@link org.splevo.vpm.refinement.Refinement#getRefinementModel <em>Refinement Model</em>}</li>
 * <li>{@link org.splevo.vpm.refinement.Refinement#getSource <em>Source</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.splevo.vpm.refinement.RefinementPackage#getRefinement()
 * @model
 * @generated
 */
public interface Refinement extends EObject {
    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute. The default value is
     * <code>"GROUPING"</code>. The literals are from the enumeration
     * {@link org.splevo.vpm.refinement.RefinementType}. <!-- begin-user-doc --> <!-- end-user-doc
     * --> <!-- begin-model-doc --> The refinement type defines how the refinement can or must be
     * handled. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Type</em>' attribute.
     * @see org.splevo.vpm.refinement.RefinementType
     * @see #setType(RefinementType)
     * @see org.splevo.vpm.refinement.RefinementPackage#getRefinement_Type()
     * @model default="GROUPING" required="true"
     * @generated
     */
    RefinementType getType();

    /**
     * Sets the value of the '{@link org.splevo.vpm.refinement.Refinement#getType <em>Type</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Type</em>' attribute.
     * @see org.splevo.vpm.refinement.RefinementType
     * @see #getType()
     * @generated
     */
    void setType(RefinementType value);

    /**
     * Returns the value of the '<em><b>Variation Points</b></em>' reference list. The list contents
     * are of type {@link org.splevo.vpm.variability.VariationPoint}. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The variation points to be merged. <!--
     * end-model-doc -->
     * 
     * @return the value of the '<em>Variation Points</em>' reference list.
     * @see org.splevo.vpm.refinement.RefinementPackage#getRefinement_VariationPoints()
     * @model
     * @generated
     */
    EList<VariationPoint> getVariationPoints();

    /**
     * Returns the value of the '<em><b>Refinement Model</b></em>' container reference. It is
     * bidirectional and its opposite is '
     * {@link org.splevo.vpm.refinement.RefinementModel#getRefinements <em>Refinements</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The analyzer that led to
     * this refinement. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Refinement Model</em>' container reference.
     * @see #setRefinementModel(RefinementModel)
     * @see org.splevo.vpm.refinement.RefinementPackage#getRefinement_RefinementModel()
     * @see org.splevo.vpm.refinement.RefinementModel#getRefinements
     * @model opposite="refinements" required="true" transient="false"
     * @generated
     */
    RefinementModel getRefinementModel();

    /**
     * Sets the value of the '{@link org.splevo.vpm.refinement.Refinement#getRefinementModel
     * <em>Refinement Model</em>}' container reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Refinement Model</em>' container reference.
     * @see #getRefinementModel()
     * @generated
     */
    void setRefinementModel(RefinementModel value);

    /**
     * Returns the value of the '<em><b>Source</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> A textual explanation why the refinement is
     * recommended. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Source</em>' attribute.
     * @see #setSource(String)
     * @see org.splevo.vpm.refinement.RefinementPackage#getRefinement_Source()
     * @model
     * @generated
     */
    String getSource();

    /**
     * Sets the value of the '{@link org.splevo.vpm.refinement.Refinement#getSource <em>Source</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Source</em>' attribute.
     * @see #getSource()
     * @generated
     */
    void setSource(String value);

} // Refinement
