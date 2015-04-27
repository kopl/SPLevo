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
package org.splevo.vpm.refinement;

import org.eclipse.emf.ecore.EObject;
import org.splevo.vpm.variability.VariationPoint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reason</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.splevo.vpm.refinement.RefinementReason#getSource <em>Source</em>}</li>
 *   <li>{@link org.splevo.vpm.refinement.RefinementReason#getTarget <em>Target</em>}</li>
 *   <li>{@link org.splevo.vpm.refinement.RefinementReason#getRefinement <em>Refinement</em>}</li>
 *   <li>{@link org.splevo.vpm.refinement.RefinementReason#getReason <em>Reason</em>}</li>
 * </ul>
 *
 * @see org.splevo.vpm.refinement.RefinementPackage#getRefinementReason()
 * @model
 * @generated
 */
public interface RefinementReason extends EObject {
    /**
     * Returns the value of the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' reference.
     * @see #setSource(VariationPoint)
     * @see org.splevo.vpm.refinement.RefinementPackage#getRefinementReason_Source()
     * @model required="true"
     * @generated
     */
    VariationPoint getSource();

    /**
     * Sets the value of the '{@link org.splevo.vpm.refinement.RefinementReason#getSource <em>Source</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' reference.
     * @see #getSource()
     * @generated
     */
    void setSource(VariationPoint value);

    /**
     * Returns the value of the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(VariationPoint)
     * @see org.splevo.vpm.refinement.RefinementPackage#getRefinementReason_Target()
     * @model required="true"
     * @generated
     */
    VariationPoint getTarget();

    /**
     * Sets the value of the '{@link org.splevo.vpm.refinement.RefinementReason#getTarget <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(VariationPoint value);

    /**
     * Returns the value of the '<em><b>Refinement</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.splevo.vpm.refinement.Refinement#getReasons <em>Reasons</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Refinement</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Refinement</em>' container reference.
     * @see #setRefinement(Refinement)
     * @see org.splevo.vpm.refinement.RefinementPackage#getRefinementReason_Refinement()
     * @see org.splevo.vpm.refinement.Refinement#getReasons
     * @model opposite="reasons" required="true" transient="false"
     * @generated
     */
    Refinement getRefinement();

    /**
     * Sets the value of the '{@link org.splevo.vpm.refinement.RefinementReason#getRefinement <em>Refinement</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Refinement</em>' container reference.
     * @see #getRefinement()
     * @generated
     */
    void setRefinement(Refinement value);

    /**
     * Returns the value of the '<em><b>Reason</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reason</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Reason</em>' attribute.
     * @see #setReason(String)
     * @see org.splevo.vpm.refinement.RefinementPackage#getRefinementReason_Reason()
     * @model required="true"
     * @generated
     */
    String getReason();

    /**
     * Sets the value of the '{@link org.splevo.vpm.refinement.RefinementReason#getReason <em>Reason</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Reason</em>' attribute.
     * @see #getReason()
     * @generated
     */
    void setReason(String value);

} // RefinementReason
