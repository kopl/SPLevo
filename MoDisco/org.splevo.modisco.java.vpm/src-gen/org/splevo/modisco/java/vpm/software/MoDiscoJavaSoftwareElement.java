/**
 */
package org.splevo.modisco.java.vpm.software;

import org.eclipse.gmt.modisco.java.ASTNode;

import org.splevo.vpm.software.JavaSoftwareElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mo Disco Java Software Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A referencing object to the original MoDisco software element.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.modisco.java.vpm.software.MoDiscoJavaSoftwareElement#getAstNode <em>Ast Node</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.modisco.java.vpm.software.softwarePackage#getMoDiscoJavaSoftwareElement()
 * @model
 * @generated
 */
public interface MoDiscoJavaSoftwareElement extends JavaSoftwareElement {
    /**
     * Returns the value of the '<em><b>Ast Node</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The MoDisco ASTNode representing the software element itself.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Ast Node</em>' reference.
     * @see #setAstNode(ASTNode)
     * @see org.splevo.modisco.java.vpm.software.softwarePackage#getMoDiscoJavaSoftwareElement_AstNode()
     * @model required="true"
     * @generated
     */
    ASTNode getAstNode();

    /**
     * Sets the value of the '{@link org.splevo.modisco.java.vpm.software.MoDiscoJavaSoftwareElement#getAstNode <em>Ast Node</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ast Node</em>' reference.
     * @see #getAstNode()
     * @generated
     */
    void setAstNode(ASTNode value);

} // MoDiscoJavaSoftwareElement
